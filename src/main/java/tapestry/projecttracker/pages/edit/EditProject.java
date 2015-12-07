/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.projecttracker.pages.edit;

import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import tapestry.projecttracker.data.ProjectDAO;
import tapestry.projecttracker.entities.Member;
import tapestry.projecttracker.entities.Project;
import tapestry.projecttracker.pages.view.ViewProjectDetails;

/**
 *
 * @author dejan
 */
public class EditProject {

    @Inject
    private ProjectDAO projectDao;

    @Property
    private Project project;

    @Persist
    @Property
    private String projectTitle;

    @SessionState
    private Member loggedInMember;

    @InjectPage
    private ViewProjectDetails viewProjectDetailsPage;

    public void set(Project project) {
        this.project = project;
    }

    void onActivate(Project project) {
        this.project = project;
        this.projectTitle = project.getProjectTitle();
    }

    Project onPassivate() {
        return project;
    }

    Object onCanceled() {
        return viewProjectDetailsPage;
    }

    Object onUpdateProject() {
        viewProjectDetailsPage.set(project);
        return viewProjectDetailsPage;
    }

    public boolean getLoggedInRole() {
        return (loggedInMember.getMemberRole().name() != "Administrator") ? true : false;
    }
}
