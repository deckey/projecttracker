package tapestry.projecttracker.pages;

import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import tapestry.projecttracker.data.MemberDAO;
import tapestry.projecttracker.data.ProjectDAO;
import tapestry.projecttracker.entities.Log;
import tapestry.projecttracker.entities.Member;
import tapestry.projecttracker.pages.view.ViewDashboard;
import tapestry.projecttracker.prop.MemberStatus;

/**
 * Start page of application ProjectTracker.
 */
public class Index {

//    TEMPORARY to ADD members
    @Property
    private Member member;

    @SessionState
    @Property
    private Member loggedInMember;

    @Property
    @Validate("required")
    private String memberUsername;

    @Property
    @Validate("required")
    private String memberPassword;

    @InjectComponent("loginForm")
    private Form form;

    @Inject
    private MemberDAO memberDao;

    @Inject
    private Session dbs;

    void onValidateFromLoginForm() {
        member = memberDao.validateMember(memberUsername, memberPassword);
        if (member == null) {
            System.out.println("SERVER SIDE VERIFICATION ERROR!");
            form.recordError("Login failed, wrong username or password!");
        } else if (member.getMemberStatus().equals(MemberStatus.Inactive)) {
            form.recordError("User status is INACTIVE, login disabled!");
        } else {
            loggedInMember = member;
        }
    }

    Object onSuccessFromLoginForm() {
        return ViewDashboard.class;
    }

    public String getUser() {
        return loggedInMember.getMemberUsername();
    }

    public boolean getLoggedIn() {
        return (loggedInMember.getMemberUsername() == null) ? true : false;
    }
    @Property
    private Log log;

    @Property
    private List<Log> logs;

    @Inject
    private ProjectDAO projectDao;

    void onActivate() {
        if (logs == null) {
            logs = new ArrayList<>();
        }
        logs = projectDao.getAllLogs();
    }
}
