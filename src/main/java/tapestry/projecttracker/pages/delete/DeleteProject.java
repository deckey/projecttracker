/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.projecttracker.pages.delete;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import tapestry.projecttracker.data.ProjectDAO;
import tapestry.projecttracker.entities.Project;
import tapestry.projecttracker.pages.view.ViewProjects;

/**
 *
 * @author Dejan Ivanovic
 */
public class DeleteProject {

    @Property
    private Project project;
    
    @Inject
    private ProjectDAO projectDao;
    
    public void set(Project project) {
        this.project = project;
    }

    void onActivate(Project project) {
        this.project = project;
        System.out.println("DELETE PAGE:ON ACTIVATE..."+project);
    }

    Project onPassivate() {
        return project;
    }
    
    @CommitAfter
    Object onDeleteProject(Integer id){
        projectDao.removeAssignedFromProject(project);
        projectDao.deleteProject(id);
        return ViewProjects.class;
    }
}
