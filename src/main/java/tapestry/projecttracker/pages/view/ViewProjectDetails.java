/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.projecttracker.pages.view;

import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;
import tapestry.projecttracker.data.ProjectDAO;
import tapestry.projecttracker.entities.Member;
import tapestry.projecttracker.entities.Project;
import tapestry.projecttracker.pages.edit.EditProject;

/**
 *
 * @author dejan
 */
public class ViewProjectDetails {

    @Inject
    private ProjectDAO projectDao;

    @Property
    private Project project;
    
    @SessionState
    private Member loggedInMember;

    @Property
    private List<Project> projects;

    @InjectPage
    private EditProject editProjectPage;

    public void set(Project project) {
        this.project = project;
    }

    void onPrepare() {
        projects = projectDao.getAllProjects();
    }

    void onActivate(Project project) {
        this.project = project;
        if (projects == null) {
            projects = new ArrayList<>();
        }
    }

    Project onPassivate() {
        return project;
    }

    Object onEditProject() {
        editProjectPage.set(project);
        return editProjectPage;

    }

    void onValidateFromProjectSelectForm() {
        System.out.println("VALIDATION CALLED.... " + ": PROJECT : " + project);
        if (project == null) {
            project = projects.get(0);
        }
    }

    public boolean getLoggedInRole() {
        return (loggedInMember.getMemberRole().name() != "Administrator") ? true : false;
    }
}
