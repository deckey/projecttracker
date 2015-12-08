package tapestry.projecttracker.pages.create;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import tapestry.projecttracker.data.MemberDAO;
import tapestry.projecttracker.data.ProjectDAO;
import tapestry.projecttracker.encoders.MemberEncoder;
import tapestry.projecttracker.entities.Member;
import tapestry.projecttracker.entities.Project;
import tapestry.projecttracker.pages.view.ViewProjects;
import tapestry.projecttracker.prop.ProjectCategory;
import tapestry.projecttracker.prop.ProjectStatus;
import tapestry.projecttracker.services.ProtectedPage;

@ProtectedPage
@RolesAllowed(value = {"Administrator"})
public class CreateProject {

    @Property
    private Project project;
    @Inject
    private ProjectDAO projectDao;
    @Property
    private List<Project> projects;

    @Inject
    private MemberDAO memberDao;

    @Property
    private List<Member> members;

    @Property
    @Persist
    private Set<Member> selectedMembers;

    @InjectComponent("addProjectForm")
    private Form form;

    /*PROJECT PROPERTIES*/
    @Property
    @Validate("required")
    private String projectTitle;

    @Property
    @Validate("required")
    private String projectClient;

    @Property
    @Validate("required")
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
    @Validate("required")
    @Enumerated(EnumType.STRING)
    private ProjectCategory projectCategory = ProjectCategory.Other;

    @Property
    @Validate("required")
    @Enumerated(EnumType.STRING)
    private ProjectStatus projectStatus = ProjectStatus.Active;

    @Property
    private final MemberEncoder memberEncoder = new MemberEncoder(memberDao);

    public ProjectCategory[] getCategories() {
        ProjectCategory[] categories = ProjectCategory.values();
        return categories;
    }

    public ProjectStatus[] getStatuses() {
        ProjectStatus[] statuses = ProjectStatus.values();
        return statuses;
    }

    void onPrepare() {
        members = memberDao.getAllMembers();
        if (selectedMembers == null) {
            selectedMembers = new HashSet<>();
        }
    }

    void onSetupRender() {
        System.out.println("PREPARING RENDER ...");
        System.out.println("RENDER PREPARED ...");
    }

    void onValidateFromAddProjectForm() {
        System.out.println("VALIDATING INPUT...");
        if (projectDue.before(projectStart)) {
            form.recordError("Project due date can not be before start date!");
        }
    }

    @CommitAfter
    Object onSuccessFromAddProjectForm() {
        System.out.println("SUBMITTING FORM...");
        Project newProject = new Project(projectTitle, projectDescription, projectClient, projectStart, projectDue, projectCategory, projectStatus, selectedMembers);
        projectDao.addProject(newProject);
        System.out.println("FORM SUBMITTED....");
//        projectDao.addProject(project);
        return ViewProjects.class;
    }
}
