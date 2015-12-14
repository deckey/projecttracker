/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.projecttracker.pages.delete;

import java.util.List;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import tapestry.projecttracker.data.MemberDAO;
import tapestry.projecttracker.entities.Log;
import tapestry.projecttracker.entities.Member;
import tapestry.projecttracker.pages.view.ViewMember;

/**
 *
 * @author Dejan Ivanovic
 */
public class DeleteMember {

    @Property
    private Member member;

    @Property
    private List<Member> members;

    @Inject
    private MemberDAO memberDao;

    @InjectPage
    private ViewMember viewMemberPage;

    @SessionState
    private Member loggedInMember;

    public void set(Member member) {
        this.member = member;
    }

    void onActivate(Member member) {
        this.member = member;
        members = memberDao.getAllMembers();
    }

    Member onPassivate() {
        return member;
    }

    @CommitAfter
    Object onDeleteMember(Integer id) {
        memberDao.removeAssignedProjects(member);
//        IF DELETING YOURSELF
        if (member.getMemberId() == loggedInMember.getMemberId()) {
            viewMemberPage.set(member);
            viewMemberPage.setErrorAlert("You can not delete yourself!");
            return viewMemberPage;
//     DELETING OTHER MEMBER
        } else {
//            DELETING LOGS FIRST THEN MEMBER 
            List<Log> logList = memberDao.getLogsByMember(member);
            memberDao.deleteMemberLogs(logList);
            memberDao.deleteMember(id);
            viewMemberPage.setSuccessAlert("Member " + member.getMemberName() + " successfully deleted!");
            viewMemberPage.set(members.get(0));
            return viewMemberPage;
        }
    }
}
