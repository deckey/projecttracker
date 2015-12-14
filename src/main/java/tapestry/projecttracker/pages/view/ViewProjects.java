package tapestry.projecttracker.pages.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
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

public class ViewProjects {

    @SessionState
    @Property
    private Member loggedInMember;

    @Property
    private Project project;

    @Property
    private List<Project> projects;

    @Inject
    private ProjectDAO projectDao;

    /* GRID EDITS */
    @Property
    private BeanModel<Project> gridModel;

    @Property
    private BeanModel<Project> gridArchiveModel;
//
//    @Component
//    private Grid archivedProjectsGrid;

    @Inject
    private BeanModelSource beanModelSource;

    @Inject
    private Messages messages;
//

    void setupRender() {
        /* Draw grid table for archived projects: */
        gridModel = beanModelSource.createDisplayModel(Project.class, messages);
        gridModel.include("projectTitle",  "projectClient","projectCategory", "projectStatus","projectStart", "projectDue", "projectCreationDate");
        gridModel.get("projectClient").sortable(false);
        gridModel.get("projectStatus").sortable(false);
        // Column titles:
        gridModel.get("projectTitle").label("Project name");
        gridModel.get("projectClient").label("Company");
        gridModel.get("projectStatus").label("Status");
        gridModel.get("projectStart").label("Start date");
        gridModel.get("projectDue").label("Due date");
        gridModel.get("projectCreationDate").label("Created on");

        /* ARCHIVE MODEL */
        gridArchiveModel = beanModelSource.createDisplayModel(Project.class, messages);
        gridArchiveModel.include("projectTitle", "projectClient","projectDescription",  "projectCategory", "projectStart", "projectEnd");
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
        /*  */
    }

    void onActivate() {
        if (projects == null) {
            projects = new ArrayList<>();
        }
        projects = projectDao.getAllProjects();
    }

    public boolean getLoggedInRole() {
        return (loggedInMember.getMemberRole().name() != "Member") ? true : false;
    }

    public List<Project> getRecentProjects() {
        List<Project> recentProjects;
        List<Project> allProjects = projectDao.getAllProjects();
        Collections.sort(allProjects, new Comparator<Project>() {
            public int compare(Project p1, Project p2) {
                return p2.getProjectCreationDate().compareTo(p1.getProjectCreationDate());
            }
        });
        Iterator it = allProjects.iterator();
        while (it.hasNext()) {
            Project recent = (Project) it.next();
            if (recent.getProjectStatus() != ProjectStatus.Active) {
                it.remove();
            }
        }
        if (allProjects.size() < 5) {
            recentProjects = allProjects.subList(0, allProjects.size());
        }else{
            recentProjects = allProjects.subList(0, 5);
        }
        return recentProjects;
    }

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
