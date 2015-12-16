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
import tapestry.projecttracker.prop.MemberStatus;
import tapestry.projecttracker.prop.ProjectStatus;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class ViewDashboard {

    /* Properties */
    private List<Member> activeMembers;
    private List<Project> activeProjects;

    @Property
    private List<Activity> activities = new ArrayList<>();

    @Property
    private BeanModel<Activity> activityGridModel;

    @SessionState
    private Member loggedInMember;

    @Property
    private Integer memberCount;

    private List<Member> members;

    @Property
    private Integer projectCount;

    private List<Project> projects;

    /* Fields */
    @Property
    private double hourCount;

    @Property
    private Activity activity;

    /* Services */
    @Inject
    private ActivityDAO activityDao;

    @Inject
    private MemberDAO memberDao;

    @Inject
    private ProjectDAO projectDao;

    @Inject
    private BeanModelSource beanModelSource;

    @Inject
    private Messages messages;

    /**
     * Empty constructor
     */
    public ViewDashboard() {
    }

    /**
     * Calculate total hours spent on all projects
     *
     * @return Number of hours (double)
     */
    public double getHoursOnAllProjects() {
        double totalHours = 0;

        for (Project project : this.projects) {
            totalHours += project.getProjectTime();
        }
        return totalHours;
    }

    /**
     * Check if logged in member is Administrator
     *
     * @return True if logged in member is Admin
     */
    public boolean getLoggedInRole() {
        return (loggedInMember.getMemberRole().equals(MemberRole.Administrator)) ? true : false;
    }

    /* Page render context */
    void setupRender() {
        // setup grid model for activity
        activityGridModel = beanModelSource.createDisplayModel(Activity.class, messages);
        activityGridModel.include("activityOutput");
        activityGridModel.get("activityOutput").sortable(false);
        activityGridModel.get("activityOutput").label("Latest activities");
    }

    void onActivate() {
        // get all projects, members and activities, init activeProjects as empty
        this.activeProjects = new ArrayList<>();
        this.activeMembers = new ArrayList<>();
        this.members = memberDao.getAllMembers();
        this.projects = projectDao.getAllProjects();
        this.activities = activityDao.getAllActivities();

        //sort activities to as latest first (on top)
        Collections.sort(activities, new Comparator<Activity>() {
            public int compare(Activity act1, Activity act2) {
                return act1.getActivityDate().after(act2.getActivityDate()) ? 1 : -1;
            }
        });

        // display only last 10 activities (others remain in DB)
        if (activities.size() > 10) {
            activities = activities.subList(0, 10);
        }

        // check if member is active and put it on Active members list
        for (Member mem : members) {
            if (mem.getMemberStatus().equals(MemberStatus.Active)) {
                activeMembers.add(mem);
            }
        }
        this.memberCount = activeMembers.size();

        // check if project is active and put it on Active projects list
        for (Project prj : projects) {
            if (prj.getProjectStatus().equals(ProjectStatus.Active)) {
                activeProjects.add(prj);
            }
        }
        this.projectCount = activeProjects.size();

        // get total hours on all projects
        this.hourCount = getHoursOnAllProjects();

    }

    @CommitAfter
    void onDeleteActivity(Integer id) {
        activityDao.deleteActivity(id);
    }
}
