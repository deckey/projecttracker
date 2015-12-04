/*

 */
package tapestry.projecttracker.pages.view;

import java.util.List;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import tapestry.projecttracker.data.MemberDAO;
import tapestry.projecttracker.data.ProjectDAO;
import tapestry.projecttracker.entities.Member;
import tapestry.projecttracker.entities.Project;


public class ViewDashboard {

    @Inject
    private MemberDAO memberDao;

    @Inject
    private ProjectDAO projectDao;

    @Property
    private Integer projectCount;
    private List<Project> projects;

    @Property
    private Integer memberCount;
    private List<Member> members;

    @Property
    private double hourCount;

    public ViewDashboard() {

    }

    public double getHoursOnAllProjects() {
        double totalHours = 0;

        for (Project project : this.projects) {
            totalHours += project.getProjectTime();
        }
        return totalHours;
    }

    void onActivate() {

        this.members = memberDao.getAllMembers();
        this.projects = projectDao.getAllProjects();

        this.memberCount = members.size();
        this.projectCount = projects.size();
        this.hourCount = getHoursOnAllProjects();
    }

}
