package tapestry.projecttracker.pages.delete;

import java.util.List;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import tapestry.projecttracker.data.ActivityDAO;
import tapestry.projecttracker.data.MemberDAO;
import tapestry.projecttracker.entities.Activity;
import tapestry.projecttracker.entities.Log;
import tapestry.projecttracker.entities.Member;
import tapestry.projecttracker.pages.view.ViewMember;

/**
 * Page to confirm deleting of a member
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class DeleteMember {

    /* Properties */
    @Property
    private Member member;

    @Property
    private List<Member> members;

    @SessionState
    private Member loggedInMember;

    /* Services */
    @Inject
    private MemberDAO memberDao;

    @InjectPage
    private ViewMember viewMemberPage;

    @Inject
    private ActivityDAO activityDao;

    /**
     *
     * Set active member for the page to render properly
     *
     * @param member Member instance to show
     */
    public void set(Member member) {
        this.member = member;
    }

    /* Page rendering */
    void onActivate(Member member) {
        this.member = member;
        members = memberDao.getAllMembers();
    }

    Member onPassivate() {
        return member;
    }

    /* Form submission */
    @CommitAfter
    Object onDeleteMember(Integer id) {
        //REMOVE ASSIGNED PROJECTS FROM THE MEMBER    
        memberDao.removeAssignedProjects(member);

//        IF DELETING YOURSELF
        if (member.getMemberId() == loggedInMember.getMemberId()) {
            viewMemberPage.set(member);
            viewMemberPage.setErrorAlert("You can not delete yourself!");
            return viewMemberPage;

//     DELETING OTHER MEMBER
        } else {
//          DELETING LOGS FIRST THEN MEMBER 
            List<Log> logList = memberDao.getLogsByMember(member);
            memberDao.deleteMemberLogs(logList);
            memberDao.deleteMember(id);

//       RECORD ACTIVITY
            Activity activity = activityDao.recordActivity(loggedInMember, ("deleted " + member + " member "));
            viewMemberPage.setSuccessAlert("Member " + member.getMemberName() + " successfully deleted!");
            viewMemberPage.set(members.get(0));
            
            return viewMemberPage;
        }
    }
}
