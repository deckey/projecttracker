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
 * Index page of Project Tracker application
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class Index {

    /* Properties */
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

    @Property
    private Log log;

    @Property
    private List<Log> logs;
    
    /* Services */
    @Inject
    private ProjectDAO projectDao;
    
    @InjectComponent("loginForm")
    private Form form;

    @Inject
    private MemberDAO memberDao;
    
    /* Page start and render */ 
    void onActivate() {
        if (logs == null) {
            logs = new ArrayList<>();
        }
        logs = projectDao.getAllLogs();
    }
    
    /* Form validation */ 
    
    
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
    
    /* Methods */

    /**
     *
     * @return
     */
    public String getUser() {
        return loggedInMember.getMemberUsername();
    }

    /**
     *
     * @return
     */
    public boolean getLoggedIn() {
        return (loggedInMember.getMemberUsername() == null) ? true : false;
    }
}
