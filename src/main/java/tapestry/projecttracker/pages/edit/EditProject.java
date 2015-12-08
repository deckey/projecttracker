/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.projecttracker.pages.edit;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.security.RolesAllowed;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import tapestry.projecttracker.data.MemberDAO;
import tapestry.projecttracker.data.ProjectDAO;
import tapestry.projecttracker.encoders.MemberEncoder;
import tapestry.projecttracker.entities.Member;
import tapestry.projecttracker.entities.Project;
import tapestry.projecttracker.pages.view.ViewProjectDetails;
import tapestry.projecttracker.prop.ProjectCategory;
import tapestry.projecttracker.prop.ProjectStatus;
import tapestry.projecttracker.services.ProtectedPage;

@ProtectedPage
@RolesAllowed(value = {"Administrator"})
public class EditProject {

    @Inject
    private ProjectDAO projectDao;

    @Inject
    private MemberDAO memberDao;

    @Property
    private Project project;

    @Property
    private List<Member> members;

    @Property
    private Set<Member> selectedMembers;

    @SessionState
    private Member loggedInMember;

    @InjectPage
    private ViewProjectDetails viewProjectDetailsPage;

    @Property
    private final MemberEncoder memberEncoder = new MemberEncoder(memberDao);
    
    @InjectComponent
    private Form projectEditForm;

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
        selectedMembers = project.getAssignedMembers();
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

//    @CommitAfter
//    void onUpdateProject() {
//        System.out.println("ON UPDATED - PROJECT : " + project);
//        System.out.println("ON UPDATED - PROJECT TITLE: " + projectTitle);
//        //TODO: UPDATE DOESN't WORK - TML TEMPLATE PARAMETERS NOT UPDATING THE JAVA CLASS
////        project.setProjectDue(projectDue);
////        project.setProjectCategory(projectCategory);
////                
////        projectDao.updateProject(project);
////        viewProjectDetailsPage.set(project);
//
//    }

    @CommitAfter
    void onValidateFromProjectEditForm() {
        // validation queries like
        // if user.getFirstName()== null || ....
        try {
            projectDao.updateProject(project);
        } catch (Exception ex) {
            projectEditForm.recordError(ExceptionUtils.getRootCauseMessage(ex));
        }
    }

    public boolean getLoggedInRole() {
        return (loggedInMember.getMemberRole().name() != "Administrator") ? true : false;
    }
}
