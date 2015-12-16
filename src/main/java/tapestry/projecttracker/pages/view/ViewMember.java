package tapestry.projecttracker.pages.view;

import java.util.List;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.alerts.Duration;
import org.apache.tapestry5.alerts.Severity;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;
import tapestry.projecttracker.data.MemberDAO;
import tapestry.projecttracker.entities.Member;
import tapestry.projecttracker.pages.edit.EditMember;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class ViewMember {

    /*  Properties */
    @SessionState
    private Member loggedInMember;
    
    @Property
    private Member member;

    @Property
    private List<Member> members;

    @Property
    private String passwordFormat;

    /*  Services */
    @Inject
    private AlertManager alertManager;
    
    @InjectPage
    private EditMember editMemberPage;
    
    @Inject
    private MemberDAO memberDao;


    /**
     * Set active project for the page to render properly
     *
     * @param member Member instance to show
     */
    public void set(Member member) {
        this.member = member;
    }
    /**
     * Check if logged in user is Administrator or if the page is his/her profile
     * @return True if user can edit the profile
     */
    public boolean getLoggedInRole() {
        if ((loggedInMember.getMemberRole().name() == "Administrator")
                || (loggedInMember.getMemberId() == member.getMemberId())) {
            return true;
        }
        return false;
    }


    /**
     * Set error message to display 
     * @param message String of error message
     */
    public void setErrorAlert(String message) {
        alertManager.alert(Duration.TRANSIENT, Severity.ERROR, message);
    }

    /**
     * Set success message to display
     * @param message String of success message
     */
    public void setSuccessAlert(String message) {
        alertManager.alert(Duration.TRANSIENT, Severity.SUCCESS, message);
    }
    
    /* Page render */
    void onActivate(Member member) {
        this.member = member;
        passwordFormat = member.getMemberPassword().replaceAll(".", "*");
        members = memberDao.getAllMembers();
    }
    Member onPassivate() {
        return this.member;
    }
    
    /* Direct user to edit member page*/
    Object onEditMember() {
        editMemberPage.set(member);
        return editMemberPage;
    }
}
