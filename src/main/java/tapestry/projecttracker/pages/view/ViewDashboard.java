/*

 */
package tapestry.projecttracker.pages.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;
import tapestry.projecttracker.data.ActivityDAO;
import tapestry.projecttracker.data.MemberDAO;
import tapestry.projecttracker.data.ProjectDAO;
import tapestry.projecttracker.entities.Activity;
import tapestry.projecttracker.entities.Member;
import tapestry.projecttracker.entities.Project;
import tapestry.projecttracker.prop.MemberRole;
import tapestry.projecttracker.prop.ProjectStatus;

public class ViewDashboard {

    @Inject
    private MemberDAO memberDao;

    @Inject
    private ProjectDAO projectDao;

    @Inject
    private ActivityDAO activityDao;

    @Property
    private Integer projectCount;
    private List<Project> projects;
    private List<Project> activeProjects;

    @Property
    private Integer memberCount;
    
    @SessionState
    private Member loggedInMember;

    private List<Member> members;

    @Property
    private double hourCount;

    @Property
    private Activity activity;

    @Property
    private List<Activity> activities = new ArrayList<>();
    /* GRID EDITS */
    @Property
    private BeanModel<Activity> activityGridModel;
    @Inject
    private BeanModelSource beanModelSource;
    @Inject
    private Messages messages;

    public ViewDashboard() {

    }

    public double getHoursOnAllProjects() {
        double totalHours = 0;

        for (Project project : this.projects) {
            totalHours += project.getProjectTime();
        }
        return totalHours;
    }

    public boolean getLoggedInRole() {
        return (loggedInMember.getMemberRole().equals(MemberRole.Administrator)) ? true : false;
    }

    void onActivate() {
        this.activeProjects = new ArrayList<>();
        this.members = memberDao.getAllMembers();
        this.projects = projectDao.getAllProjects();
        this.activities = activityDao.getAllActivities();
        Collections.sort(activities, new Comparator<Activity>() {
            public int compare(Activity act1, Activity act2) {
                return act1.getActivityDate().after(act2.getActivityDate()) ? -1 : 1;
            }
        });

        this.memberCount = members.size();
        for (Project prj : projects) {
            if (prj.getProjectStatus().equals(ProjectStatus.Active)) {
                activeProjects.add(prj);
            }
        }
        this.projectCount = activeProjects.size();
        this.hourCount = getHoursOnAllProjects();
    }

    void setupRender() {
        activityGridModel = beanModelSource.createDisplayModel(Activity.class, messages);
        activityGridModel.include("activityOutput");
        activityGridModel.get("activityOutput").sortable(false);
        activityGridModel.get("activityOutput").label("Latest activity");
    }

    @CommitAfter
    void onDeleteActivity(Integer id) {
        activityDao.deleteActivity(id);
    }
}
