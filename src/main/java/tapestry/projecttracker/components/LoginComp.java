/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.projecttracker.components;

import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;
import tapestry.projecttracker.data.MemberDAO;
import tapestry.projecttracker.entities.Member;
import tapestry.projecttracker.pages.ViewDashboard;

/**
 * Login component handling user authentication and authorization
 *
 * @author dejan
 */
public class LoginComp {

    @SessionState
    @Property
    private Member member;

    @Persist
    @Property
    @Validate("required")
    private String memberUsername;

    @Persist
    @Property
    @Validate("required")
    private String memberPassword;

    @InjectComponent("loginForm")
    private Form form;

    @Inject
    private MemberDAO memberDao;

    void cleanupRender() {
        form.clearErrors();
    }

    void onValidateFromLoginForm() {

        if (!memberDao.validateMember(memberUsername, memberPassword)) {
            System.out.println("SERVER SIDE VERIFICATION ERROR!");
            form.recordError("User named " + memberUsername + " doesn't exist!");
        }
        //TODO: Member authentication 
    }

    Object onSuccessFromLoginForm() {
        return ViewDashboard.class;
    }
}
