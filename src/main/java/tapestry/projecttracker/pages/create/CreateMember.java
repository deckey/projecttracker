package tapestry.projecttracker.pages.create;

import java.util.List;
import javax.annotation.security.RolesAllowed;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.alerts.Duration;
import org.apache.tapestry5.alerts.Severity;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;
import tapestry.projecttracker.data.ActivityDAO;
import tapestry.projecttracker.data.MemberDAO;
import tapestry.projecttracker.entities.Activity;
import tapestry.projecttracker.entities.Member;
import tapestry.projecttracker.prop.MemberRole;
import tapestry.projecttracker.prop.MemberSpecialty;
import tapestry.projecttracker.prop.MemberStatus;
import tapestry.projecttracker.services.ProtectedPage;

@ProtectedPage
@RolesAllowed(value = {"Administrator"})
public class CreateMember {

    @Inject
    private MemberDAO memberDao;
    
    @Inject
    private ActivityDAO activityDao;

    @Property
    private List<Member> members;

    @Property
    private Member member;

    @SessionState
    private Member loggedInMember;
    
    @InjectComponent("addMemberForm")
    private Form form;

    @Property
    private BeanModel<Member> membersGridModel;

    @Inject
    private BeanModelSource beanModelSource;
    @Inject
    private Messages messages;

    /*PROJECT PROPERTIES*/
    @Property
    @Validate("required")
    private String memberName;

    @Property
    @Validate("required")
    private String memberUsername;

    @Property
    @Validate("required")
    private String memberPassword;

    @Property
    @Validate("required")
    private MemberRole memberRole;

    @Property
    @Validate("required")
    private MemberSpecialty memberSpecialty;

    @Property
    @Validate("required")
    private MemberStatus memberStatus;

    @Inject
    private AlertManager alertManager;

    public MemberRole[] getRoles() {
        MemberRole[] roles = MemberRole.values();
        return roles;
    }

    public MemberSpecialty[] getSpecialties() {
        MemberSpecialty[] specialties = MemberSpecialty.values();
        return specialties;
    }

    public MemberStatus[] getStatuses() {
        MemberStatus[] statuses = MemberStatus.values();
        return statuses;
    }

    void onPrepare() {
        members = memberDao.getAllMembers();
    }

    void setupRender() {
        membersGridModel = beanModelSource.createDisplayModel(Member.class, messages);
        membersGridModel.get("memberTotalHours").label("Total hours");
    }

    void onActivate() {
    }

    void onSubmitFromAddMemberForm() {
        System.out.println("ADD MEMBER FORM: SUBMITTED...");
    }

    void onValidateFromAddMemberForm() {
        System.out.println("ADD MEMBER FORM: VALIDATING...");
        for (Member mem : members) {
            if (memberUsername.equals(mem.getMemberUsername())) {
                form.recordError("Username '" + memberUsername + "' already exists!");
                return;
            }
        }
    }

    @CommitAfter
    void onSuccessFromAddMemberForm() {
        System.out.println("ADD MEMBER FORM: SUCCESS...");
        Member newMember = new Member(memberName, memberUsername, memberPassword, memberRole, memberSpecialty, memberStatus);
//      ACTIVITY RECORD
        Activity activity = activityDao.recordActivity(loggedInMember, (" created new member  " + newMember.getMemberName()));
        
        alertManager.alert(Duration.TRANSIENT, Severity.SUCCESS, "New member "+ memberName+" successfully created!");
        memberDao.addMember(newMember);
    }

    void onFailureFromAddMemberForm() {
        System.out.println("ADD MEMBER FORM: FAILURE...");
    }
}
