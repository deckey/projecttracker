/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author dejan
 */
public class ViewMember {

    @Property
    private Member member;

    @Property
    private List<Member> members;

    @Inject
    private MemberDAO memberDao;

    @SessionState
    private Member loggedInMember;

    @Property
    private String passwordFormat;

    @InjectPage
    private EditMember editMemberPage;

    @Inject
    private AlertManager alertManager;

    public void set(Member member) {
        this.member = member;
    }

    public void setErrorAlert(String message) {
        alertManager.alert(Duration.TRANSIENT, Severity.ERROR, message);
    }

    public void setSuccessAlert(String message) {
        alertManager.alert(Duration.TRANSIENT, Severity.SUCCESS, message);
    }

    void onActivate(Member member) {
        this.member = member;
        passwordFormat = member.getMemberPassword().replaceAll(".", "*");
        members = memberDao.getAllMembers();
    }

    Member onPassivate() {
        return this.member;
    }

    public boolean getLoggedInRole() {
        if ((loggedInMember.getMemberRole().name() == "Administrator")
                || (loggedInMember.getMemberId() == member.getMemberId())) {
            return true;
        }
        return false;
    }

    Object onEditMember() {
        editMemberPage.set(member);
        return editMemberPage;
    }
}
