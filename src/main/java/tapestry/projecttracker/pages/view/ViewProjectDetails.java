/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.projecttracker.pages.view;

import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import tapestry.projecttracker.data.ProjectDAO;
import tapestry.projecttracker.entities.Project;

/**
 *
 * @author dejan
 */
public class ViewProjectDetails {

    @Inject
    private ProjectDAO projectDao;

    @Property
    private Project project;

    @Property
    private List<Project> projects;

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

    void onValidateFromProjectSelectForm() {
        System.out.println("VALIDATION CALLED.... " + ": PROJECT : " + project);
        if (project == null) {
            project = projects.get(0);
        }
    }
    
}
