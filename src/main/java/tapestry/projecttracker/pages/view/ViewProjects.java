package tapestry.projecttracker.pages.view;

import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;
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

    void onActivate() {
        if (projects == null) {
            projects = new ArrayList<>();
        }
        projects = projectDao.getAllProjects();
    }

    public boolean getLoggedInRole() {
        return (loggedInMember.getMemberRole().name() == "Administrator") ? true : false;
    }

}
