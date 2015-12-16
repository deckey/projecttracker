package tapestry.projecttracker.pages.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;
import tapestry.projecttracker.data.ProjectDAO;
import tapestry.projecttracker.entities.Member;
import tapestry.projecttracker.entities.Project;
import tapestry.projecttracker.prop.ProjectStatus;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class ViewProjects {

    /* Properties */
    @Property
    private BeanModel<Project> gridArchiveModel;

    @Property
    private BeanModel<Project> gridModel;

    @SessionState
    @Property
    private Member loggedInMember;

    @Property
    private Project project;

    @Property
    private List<Project> projects;

//    Services 
    @Inject
    private BeanModelSource beanModelSource;

    @Inject
    private Messages messages;

    @Inject
    private ProjectDAO projectDao;

//    Page rendering 
    void setupRender() {

        /* Draw grid tables for projects - grid model */
        gridModel = beanModelSource.createDisplayModel(Project.class, messages);
        gridModel.include("projectTitle", "projectClient", "projectCategory", "projectStatus", "projectStart", "projectDue", "projectCreationDate");
        gridModel.get("projectClient").sortable(false);
        gridModel.get("projectStatus").sortable(false);
        // Column titles:
        gridModel.get("projectTitle").label("Project name");
        gridModel.get("projectClient").label("Company");
        gridModel.get("projectStatus").label("Status");
        gridModel.get("projectStart").label("Start date");
        gridModel.get("projectDue").label("Due date");
        gridModel.get("projectCreationDate").label("Created on");

        /* Draw grid tables for projects - archive model */
        gridArchiveModel = beanModelSource.createDisplayModel(Project.class, messages);
        gridArchiveModel.include("projectTitle", "projectClient", "projectDescription", "projectCategory", "projectStart", "projectEnd");
        gridArchiveModel.get("projectClient").sortable(false);
        gridArchiveModel.get("projectDescription").sortable(false);
        gridArchiveModel.get("projectCategory").sortable(false);

        // Column titles:
        gridArchiveModel.get("projectTitle").label("Project name");
        gridArchiveModel.get("projectClient").label("Company");
        gridArchiveModel.get("projectDescription").label("Description");
        gridArchiveModel.get("projectCategory").label("Category");
        gridArchiveModel.get("projectStart").label("Start date");
        gridArchiveModel.get("projectEnd").label("Completed on");

    }

    void onActivate() {
        if (projects == null) {
            projects = new ArrayList<>();
        }
        projects = projectDao.getAllProjects();
    }

    /**
     * Check if logged in user is Administrator or Supervisor
     *
     * @return True if user is Admin or Sup
     */
    public boolean getLoggedInRole() {
        return (loggedInMember.getMemberRole().name() != "Member") ? true : false;
    }

    /**
     * Get projects for logged in member
     *
     * @return List of projects member is assigned to
     */
    public List<Project> getUserProjects() {
        List<Project> allProjects = projectDao.getAllProjects();
        List<Project> userProjects = new ArrayList<>();

        //for each project in the list, for each member in project's assignment list
        //if logged in member is found, put that project in it's 'user projects'
        for (Project prj : allProjects) {
            for (Member mem : prj.getAssignedMembers()) {
                if (mem.getMemberId() == loggedInMember.getMemberId()) {
                    userProjects.add(prj);
                }
            }
        }
        return userProjects;
    }

    /**
     * Get recent projects from the member
     *
     * @return List of projects, sorted by creation date and picking 5 most
     * recent ones
     */
    public List<Project> getRecentProjects() {
        List<Project> recentProjects;
        List<Project> allProjects = projectDao.getAllProjects();

        // compare projects by creation date
        Collections.sort(allProjects, new Comparator<Project>() {
            public int compare(Project p1, Project p2) {
                return p2.getProjectCreationDate().compareTo(p1.getProjectCreationDate());
            }
        });

        // if project is not Active, remove it from the list
        Iterator it = allProjects.iterator();
        while (it.hasNext()) {
            Project recent = (Project) it.next();
            if (recent.getProjectStatus() != ProjectStatus.Active) {
                it.remove();
            }
        }

        // if less than 5 projects, display them all
        if (allProjects.size() < 5) {
            recentProjects = allProjects.subList(0, allProjects.size());
        } else {
            // otherwise, get top 5
            recentProjects = allProjects.subList(0, 5);
        }
        return recentProjects;
    }

    /**
     * Get archived projects from the member
     * 
     * @return List of projects with status set to Archived
     */
    public List<Project> getArchivedProjects() {
        List<Project> archivedProjects = projectDao.getAllProjects();
        Iterator it = archivedProjects.iterator();
        while (it.hasNext()) {
            Project archived = (Project) it.next();
            if (archived.getProjectStatus() != ProjectStatus.Archived) {
                it.remove();
            }
        }
        return archivedProjects;
    }
}
