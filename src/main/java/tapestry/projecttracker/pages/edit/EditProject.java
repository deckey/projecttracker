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
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import tapestry.projecttracker.data.ProjectDAO;
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

    @InjectPage
    private ViewProjectDetails viewProjectDetailsPage;

    @InjectComponent
    private Form updateForm;

    void set(Project project) {
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

    @CommitAfter
    void onValidateFromUpdateForm() {
        project.setProjectTitle(this.projectTitle);
        projectDao.updateProject(project);
    }

    Object onUpdateProject() {
        viewProjectDetailsPage.set(project);
        return viewProjectDetailsPage;
    }

}
