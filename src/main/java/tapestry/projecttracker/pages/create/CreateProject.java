package tapestry.projecttracker.pages.create;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.beaneditor.Validate;
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
 * Page for creating new projects 
 * 
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
@ProtectedPage
@RolesAllowed(value = {"Administrator", "Supervisor"})
public class CreateProject {

    /* Properties */ 
    @Property
    private Project project;

    @Property
    private List<Project> projects;

    @Property
    private List<Member> members;

    @Property
    @Persist
    private List<Member> selectedMembers;

    @SessionState
    private Member loggedInMember;
    
    /* Services */ 
    @Inject
    private ProjectDAO projectDao;

    @Inject
    private MemberDAO memberDao;

    @Inject
    private ActivityDAO activityDao;

    @InjectComponent("addProjectForm")
    private Form form;

    @InjectPage
    private ViewProject viewProjectPage;

    /*PROJECT FIELDS*/
    @Property
    @Validate("required")
    private String projectTitle;

    @Property
    @Validate("required")
    private String projectClient;

    @Property
    private String projectDescription;

    @Property
    @Validate("required")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date projectStart;

    @Property
    @Validate("required")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date projectDue;

    @Property
    @Enumerated(EnumType.STRING)
    private ProjectCategory projectCategory = ProjectCategory.Other;

    @Property
    @Enumerated(EnumType.STRING)
    private ProjectStatus projectStatus = ProjectStatus.Active;

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
     *Get dropdown list of ProjectStatus types (Active, Completed ...)
     *
     * @return List of ProjectStatus enum values
     */
    public ProjectStatus[] getStatuses() {
        ProjectStatus[] statuses = ProjectStatus.values();
        return statuses;
    }

    /* Page rendering */ 
    void onPrepare() {
        members = memberDao.getAllMembers();
        selectedMembers = new ArrayList<>();
        projects = projectDao.getAllProjects();
        if (selectedMembers == null) {
            selectedMembers = new ArrayList<>();
        }
        if (projects == null) {
            projects = new ArrayList<>();
        }
    }

    
    /* Form validation */ 
    void onSubmitFromAddProjectForm() {
        System.out.println("ADD PROJECT FORM: SUBMITTED...");
    }

    void onValidateFromAddProjectForm() {
        System.out.println("ADD PROJECT FORM: VALIDATING...");
        if (projectDue.before(projectStart)) {
            form.recordError("Due date can not be before start date!");
            return;
        }
        for (Project prj : projects) {
            if (projectTitle.equals(prj.getProjectTitle())) {
                form.recordError("Project named '" + projectTitle + "' already exists!");
                return;
            }
        }
    }

    @CommitAfter
    Object onSuccessFromAddProjectForm() {
        System.out.println("ADD PROJECT FORM: SUCCESS...");
        Project newProject = projectDao.updateProject(new Project(projectTitle, projectClient, projectStart, projectDue, projectCategory, projectStatus, selectedMembers));
//      ACTIVITY RECORD
        Activity activity = activityDao.recordActivity(loggedInMember, ("created project " + newProject.getProjectTitle()));
        viewProjectPage.set(newProject);
        viewProjectPage.setSuccessAlert("New project " + projectTitle + " successfully created!");
        return viewProjectPage;
    }
}
