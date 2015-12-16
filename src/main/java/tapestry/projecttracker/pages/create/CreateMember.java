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

/**
 * Page for creating new members
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
@ProtectedPage
@RolesAllowed(value = {"Administrator"})
public class CreateMember {

    /* Properties */
    @Property
    private List<Member> members;

    @Property
    private Member member;

    @SessionState
    private Member loggedInMember;

    /* Table grid model */
    @Inject
    private BeanModelSource beanModelSource;
    @Property
    private BeanModel<Member> membersGridModel;
    @Inject
    private Messages messages;
    @Inject
    private AlertManager alertManager;

    /* Services */
    @Inject
    private MemberDAO memberDao;

    @Inject
    private ActivityDAO activityDao;

    @InjectComponent("addMemberForm")
    private Form form;

    /*MEMBER FIELDS*/
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

    /**
     * Get dropdown list of MemberRole types (Admin, Sup, Member)
     *
     * @return List of MemberRole enum values
     */
    public MemberRole[] getRoles() {
        MemberRole[] roles = MemberRole.values();
        return roles;
    }

    /**
     * Get dropdown list of MemberSpecialty types (Animation, Supervision,
     * Lighting)
     *
     * @return List of MemberSpecialty enum values
     */
    public MemberSpecialty[] getSpecialties() {
        MemberSpecialty[] specialties = MemberSpecialty.values();
        return specialties;
    }

    /**
     * Get dropdown list of MemberStatus types (Active, Inactive)
     *
     * @return List of MemberStatus enum values
     */
    public MemberStatus[] getStatuses() {
        MemberStatus[] statuses = MemberStatus.values();
        return statuses;
    }

    /* Page rendering methods */
    void onPrepare() {
        members = memberDao.getAllMembers();
    }

    void setupRender() {
        membersGridModel = beanModelSource.createDisplayModel(Member.class, messages);
        membersGridModel.get("memberTotalHours").label("Total hours");
    }

    void onActivate() {
    }

    /* Form validation and submission */
    
    void onValidateFromAddMemberForm() {
        for (Member mem : members) {
            if (memberUsername.equals(mem.getMemberUsername())) {
                form.recordError("Username '" + memberUsername + "' already exists!");
                return;
            }
        }
    }

    @CommitAfter
    void onSuccessFromAddMemberForm() {
        Member newMember = new Member(memberName, memberUsername, memberPassword, memberRole, memberSpecialty, memberStatus);
        
//      ACTIVITY RECORD
        Activity activity = activityDao.recordActivity(loggedInMember, (" created new member  " + newMember.getMemberName()));

        alertManager.alert(Duration.TRANSIENT, Severity.SUCCESS, "New member " + memberName + " successfully created!");
        memberDao.addMember(newMember);
    }
}
