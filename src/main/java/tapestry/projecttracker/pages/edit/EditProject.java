/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.projecttracker.pages.edit;

import tapestry.projecttracker.pages.delete.DeleteProject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
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

@ProtectedPage
@RolesAllowed(value = {"Administrator", "Supervisor"})
public class EditProject {

    @Inject
    private ProjectDAO projectDao;

    @Inject
    private MemberDAO memberDao;

    @Property
    private Project project;

    @InjectPage
    private ViewProject viewProjectPage;
    
    @InjectPage
    private DeleteProject deletePage;

    @Property
    private List<Project> projects;

    @Property
    private Member member;

    @Property
    private List<Member> members;

    @Property
    private List<Member> selectedMembers;

    @Inject
    private ActivityDAO activityDao;
    
    @SessionState
    private Member loggedInMember;

    @Property
    private final MemberEncoder memberEncoder = new MemberEncoder(memberDao);

    @InjectComponent("projectEditForm")
    private Form form;

    public ProjectCategory[] getCategories() {
        ProjectCategory[] categories = ProjectCategory.values();
        return categories;
    }

    public ProjectStatus[] getStatuses() {
        ProjectStatus[] statuses = ProjectStatus.values();
        return statuses;
    }

    public String getStartDateFormat() {
        SimpleDateFormat formatter = new SimpleDateFormat("MMM d, yyyy");
        return formatter.format(project.getProjectStart());
    }

    public String getDueDateFormat() {
        SimpleDateFormat formatter = new SimpleDateFormat("MMM d, yyyy");
        return formatter.format(project.getProjectDue());
    }

    public String getCreationDateFormat() {
        SimpleDateFormat formatter = new SimpleDateFormat("MMM d, yyyy");
        return formatter.format(project.getProjectCreationDate());
    }

    void onPrepare() {
        members = memberDao.getAllMembers();
        projects = projectDao.getAllProjects();
        if (selectedMembers == null) {
            selectedMembers = new ArrayList<>();
        }
        selectedMembers = project.getAssignedMembers();
        Collections.sort(selectedMembers);
    }

    public SortedSet<Member> getSortedAssignedMembers() {
        return new TreeSet(project.getAssignedMembers());
    }

    public void set(Project project) {
        this.project = project;
    }

    void onActivate(Project project) {
        this.project = project;
    }

    Project onPassivate() {
        return project;
    }

    public boolean getLoggedInRole() {
        return (loggedInMember.getMemberRole().name() != "Administrator") ? true : false;
    }

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
        Activity activity = activityDao.recordActivity(loggedInMember, ("updated " + project +" project "));
        
        return viewProjectPage;
    }
    
    Object onDeleteProject(Project project){
        deletePage.set(project);
        return deletePage;
    }
}
