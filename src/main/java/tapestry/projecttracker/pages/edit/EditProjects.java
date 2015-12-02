
package tapestry.projecttracker.pages.edit;

import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import tapestry.projecttracker.data.ProjectDAO;
import tapestry.projecttracker.entities.Project;

public class EditProjects {

    @Property
    private Project project;

    @Property
    private List<Project> projects;

    @Inject
    private ProjectDAO projectDao;

    @InjectComponent("addProjectForm")
    private BeanEditForm form;

    void onActivate() {
        if (projects == null) {
            projects = new ArrayList<>();
        }
        projects = projectDao.getAllProjects();
    }

    @CommitAfter
    Object onSuccessFromAddProjectForm() {
        projectDao.updateProject(project);
        project = new Project();
        return this;
    }

}
