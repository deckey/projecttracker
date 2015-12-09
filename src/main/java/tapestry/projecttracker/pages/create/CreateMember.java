package tapestry.projecttracker.pages.create;

import java.util.List;
import javax.annotation.security.RolesAllowed;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.BeanModelSource;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
import org.apache.tapestry5.services.ajax.JavaScriptCallback;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import tapestry.projecttracker.data.MemberDAO;
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

    @Property
    private List<Member> members;

    @Property
    private Member member;

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

    @CommitAfter
    void onValidateFromAddMemberForm() {
        System.out.println("VALIDATING INPUT...");
        Member newMember = new Member(memberName, memberUsername, memberPassword, memberRole, memberSpecialty, memberStatus);
        memberDao.addMember(newMember);
    }

    @CommitAfter
    void onActionFromDeleteMember(Integer id) {
        System.out.println("DELETING MEMBER... " + memberDao.getMemberById(id).getMemberName());
        memberDao.deleteMember(id);
        System.out.println("...DELETED");
    }
}
