package tapestry.projecttracker.pages.delete;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import tapestry.projecttracker.data.ActivityDAO;
import tapestry.projecttracker.data.ProjectDAO;
import tapestry.projecttracker.entities.Activity;
import tapestry.projecttracker.entities.Member;
import tapestry.projecttracker.entities.Project;
import tapestry.projecttracker.pages.view.ViewProjects;

/**
 *
 * Page to confirm deleting of a project
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class DeleteProject {

    /* Properties */
    @Property
    private Project project;
    
    @SessionState
    private Member loggedInMember;
    
    /*Services */
    @Inject
    private ProjectDAO projectDao;

    @Inject
    private ActivityDAO activityDao;

    /**
     * Set active project for the page to render properly
     *
     * @param project Project instance to show
     */
    public void set(Project project) {
        this.project = project;
    }

    /* Page rendering */
    void onActivate(Project project) {
        this.project = project;
        System.out.println("DELETE PAGE:ON ACTIVATE..." + project);
    }

    Project onPassivate() {
        return project;
    }
    
    /* Form submission */
    @CommitAfter
    Object onDeleteProject(Integer id) {
        projectDao.removeAssignedFromProject(project);
        projectDao.deleteProject(id);

//      ACTIVITY RECORD
        Activity activity = activityDao.recordActivity(loggedInMember, ("deleted " + project + " project "));
        return ViewProjects.class;
    }
}
