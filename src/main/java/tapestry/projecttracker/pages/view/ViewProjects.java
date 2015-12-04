package tapestry.projecttracker.pages.view;

import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.corelib.components.Grid;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;
import tapestry.projecttracker.data.ProjectDAO;
import tapestry.projecttracker.entities.Member;
import tapestry.projecttracker.entities.Project;

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

    @Component
    private Grid archivedProjectsGrid;

    @Inject
    private BeanModelSource beanModelSource;

    @Inject
    private Messages messages;

    void setupRender() {
        /* Draw grid table for archived projects: */
        gridModel = beanModelSource.createDisplayModel(Project.class, messages);
        gridModel.include("projectTitle","projectDescription", "projectClient", "projectStart", "projectEnd", "projectTime");
        gridModel.get("projectTitle").sortable(false);
        gridModel.get("projectClient").sortable(false);
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

}
