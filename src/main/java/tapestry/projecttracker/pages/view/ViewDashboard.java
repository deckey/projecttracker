/*

 */
package tapestry.projecttracker.pages.view;

import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import tapestry.projecttracker.data.MemberDAO;
import tapestry.projecttracker.data.ProjectDAO;
import tapestry.projecttracker.entities.Member;
import tapestry.projecttracker.entities.Project;
import tapestry.projecttracker.prop.ProjectStatus;


public class ViewDashboard {

    @Inject
    private MemberDAO memberDao;

    @Inject
    private ProjectDAO projectDao;

    @Property
    private Integer projectCount;
    private List<Project> projects;
    private List<Project> activeProjects;

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
        this.activeProjects=new ArrayList<>();
        this.members = memberDao.getAllMembers();
        this.projects = projectDao.getAllProjects();

        this.memberCount = members.size();
        for(Project prj : projects){
            if(prj.getProjectStatus().equals(ProjectStatus.Active)){
                activeProjects.add(prj);
            }
        }
        this.projectCount=activeProjects.size();
        this.hourCount = getHoursOnAllProjects();
    }

}
