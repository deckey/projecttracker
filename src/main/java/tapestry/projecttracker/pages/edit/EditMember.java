package tapestry.projecttracker.pages.edit;

import tapestry.projecttracker.pages.delete.DeleteProject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import tapestry.projecttracker.data.MemberDAO;
import tapestry.projecttracker.data.ProjectDAO;
import tapestry.projecttracker.encoders.MemberEncoder;
import tapestry.projecttracker.entities.Member;
import tapestry.projecttracker.entities.Project;
import tapestry.projecttracker.pages.view.ViewMember;
import tapestry.projecttracker.pages.view.ViewProject;
import tapestry.projecttracker.prop.MemberRole;
import tapestry.projecttracker.prop.MemberSpecialty;
import tapestry.projecttracker.prop.MemberStatus;
import tapestry.projecttracker.prop.ProjectCategory;
import tapestry.projecttracker.services.ProtectedPage;

/**
 *
 * Page for editing Member instances
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
@ProtectedPage
@RolesAllowed(value = {"Administrator", "Supervisor", "Member"})
public class EditMember {

    /* Properties */
    @SessionState
    private Member loggedInMember;

    @Property
    private Member member;

    @Property
    private List<Member> members;

    @Property
    private Project project;

    @Property
    private List<Project> projects;

    @Property
    private List<Member> selectedMembers;

    /* FIELDS */
    @Property
    private String memberPassword;
    @Property
    private MemberStatus memberStatus;
    @Property
    private MemberSpecialty memberSpecialty;
    @Property
    private String passwordFormat;
    @Property
    private String repeatPassword = "";

    /*Services */
    @InjectPage
    private DeleteProject deletePage;

    @InjectComponent("memberEditForm")
    private Form form;

    @Inject
    private MemberDAO memberDao;

    @Inject
    private ProjectDAO projectDao;

    @InjectPage
    private ViewProject viewProjectPage;

    @InjectPage
    private ViewMember viewMemberPage;

    @Property
    private final MemberEncoder memberEncoder = new MemberEncoder(memberDao);

    /**
     * Get dropdown list of ProjectCategory types (Animation, MotionGraphics...)
     *
     * @return List of ProjectCategory enum values
     */
    public ProjectCategory[] getCategories() {
        ProjectCategory[] categories = ProjectCategory.values();
        return categories;
    }

    /**
     * Get dropdown list of MemberSpecialty types (Supervision, Modeling,
     * Rigging ...)
     *
     * @return List of MemberSpecialty enum values
     */
    public MemberSpecialty[] getSpecialties() {
        MemberSpecialty[] specialties = MemberSpecialty.values();
        return specialties;
    }

    /**
     * Get dropdown list of ProjectStatus types (Active, Completed ...)
     *
     * @return List of ProjectStatus enum values
     */
    public MemberStatus[] getStatuses() {
        MemberStatus[] statuses = MemberStatus.values();
        return statuses;
    }

    /**
     * Get dropdown list of MemberRole types (Supervisor, Administrator, Member)
     *
     * @return List of MemberRole enum values
     */
    public MemberRole[] getRoles() {
        MemberRole[] roles = MemberRole.values();
        return roles;
    }

    /**
     * Set active member for the page to render properly
     *
     * @param member Member instance to show
     */
    public void set(Member member) {
        this.member = member;
    }

    /* Page rendering */
    void onPrepare() {
        // Get all members and projects 
        members = memberDao.getAllMembers();
        projects = projectDao.getAllProjects();
    }

    Object onActivate(Member member) {
        this.member = member;
        passwordFormat = member.getMemberPassword().replaceAll(".", "*");
        if (loggedInMember.getMemberRole().equals(MemberRole.Administrator)) {
            return null;
        } else if (loggedInMember.getMemberId() == this.member.getMemberId()) {
            return null;
        } else {
            viewMemberPage.set(member);
            return viewMemberPage;
        }
    }

    Member onPassivate() {
        return member;
    }
    
    //Other page methods
    
    /**
     * Check if the user is Administrator or that page is his/her profile
     * @return True if the user can edit the page
     */
    public boolean getLoggedInRole() {
        if ((loggedInMember.getMemberRole().equals(MemberRole.Administrator))
                || (loggedInMember.getMemberId() == (this.member.getMemberId()))) {
            return true;
        }
        return false;
    }

    /**
     * Check if the logged in member is Administrator
     * @return True if user is Admin
     */
    public boolean getUserAdmin() {
        return loggedInMember.getMemberRole().equals(MemberRole.Administrator);
    }

    /**
     * Check if both passwords match for confirmation
     * @param pass1 String of 1st password entry
     * @param pass2 String of 2nd password entry
     * @return True if both passwords are equal
     */
    public boolean checkPassword(String pass1, String pass2) {
        return pass1.equals(pass2);
    }
    
    /* Form validation */

    @CommitAfter
    Object onSuccessFromMemberEditForm() {
        if (!checkPassword(memberPassword, repeatPassword)) {
            form.recordError("Passwords don't match!");
            return null;
        }
        memberDao.updateMember(member);
        viewMemberPage.set(member);
        return viewMemberPage;
    }
}
