package tapestry.projecttracker.pages.edit;

import tapestry.projecttracker.pages.delete.DeleteProject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.annotation.security.RolesAllowed;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import tapestry.projecttracker.data.ActivityDAO;
import tapestry.projecttracker.data.MemberDAO;
import tapestry.projecttracker.data.ProjectDAO;
import tapestry.projecttracker.encoders.MemberEncoder;
import tapestry.projecttracker.entities.Activity;
import tapestry.projecttracker.entities.Member;
import tapestry.projecttracker.entities.Project;
import tapestry.projecttracker.pages.view.ViewProject;
import tapestry.projecttracker.prop.ProjectCategory;
import tapestry.projecttracker.prop.ProjectStatus;
import tapestry.projecttracker.services.ProtectedPage;

/**
 *
 * Page for editing Project instances
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
@ProtectedPage
@RolesAllowed(value = {"Administrator", "Supervisor"})
public class EditProject {

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

    /* Services */
    @Inject
    private ActivityDAO activityDao;

    @InjectPage
    private DeleteProject deletePage;

    @InjectComponent("projectEditForm")
    private Form form;

    @Inject
    private MemberDAO memberDao;

    @Inject
    private ProjectDAO projectDao;

    @InjectPage
    private ViewProject viewProjectPage;

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
     * Get dropdown list of ProjectStatus types (Active, Completed ...)
     *
     * @return List of ProjectStatus enum values
     */
    public ProjectStatus[] getStatuses() {
        ProjectStatus[] statuses = ProjectStatus.values();
        return statuses;
    }

    /**
     * Format the Date instance as 'Fri 05, 2015'
     *
     * @param dateToFormat Date instance to format
     * @return String with formatted date
     */
    public String getDateFormat(Date dateToFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat("MMM d, yyyy");
        return formatter.format(dateToFormat);
    }

    /**
     * Set active project for the page to render properly
     *
     * @param project Project instance to show
     */
    public void set(Project project) {
        this.project = project;
    }

    /* Page rendering */
    void onPrepare() {
        members = memberDao.getAllMembers();
        projects = projectDao.getAllProjects();
        if (selectedMembers == null) {
            selectedMembers = new ArrayList<>();
        }
        selectedMembers = project.getAssignedMembers();
        Collections.sort(selectedMembers);
    }

    void onActivate(Project project) {
        this.project = project;
    }

    Project onPassivate() {
        return project;
    }

    /**
     * Return sorted list of assigned members 
     * @return SortedSet of Member instances
     */
    public SortedSet<Member> getSortedAssignedMembers() {
        return new TreeSet(project.getAssignedMembers());
    }

    /**
     * Check if logged in member is Administrator
     * @return True if member is not Admin
     */
    public boolean getLoggedInRole() {
        return (loggedInMember.getMemberRole().name() != "Administrator") ? true : false;
    }

    /* Form validation */
    void onValidateFromProjectSelectForm() {
        System.out.println("VALIDATION CALLED.... " + ": PROJECT : " + project);
        if (project == null) {
            project = projects.get(0);
        }
    }
    void onValidateFromProjectEditForm() {
        System.out.println("EDIT FORM: VALIDATION... " + ": PROJECT : " + project);
        // validation queries like
        // if user.getFirstName()== null || ....
        if (project.getProjectStart().after(project.getProjectDue())) {
            form.recordError("Due date can not be set before start date!");
        }
    }
    @CommitAfter
    Object onSuccessFromProjectEditForm() {
        System.out.println("EDIT FORM: SUCCESS... UPDATED PROJECT..." + project);
        viewProjectPage.set(projectDao.updateProject(project));

//      ACTIVITY RECORD
        Activity activity = activityDao.recordActivity(loggedInMember, ("updated " + project + " project "));

        return viewProjectPage;
    }

    Object onDeleteProject(Project project) {
        deletePage.set(project);
        return deletePage;
    }
}
