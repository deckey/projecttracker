package tapestry.projecttracker.pages.create;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import tapestry.projecttracker.data.ActivityDAO;
import tapestry.projecttracker.data.MemberDAO;
import tapestry.projecttracker.data.ProjectDAO;
import tapestry.projecttracker.entities.Activity;
import tapestry.projecttracker.entities.Log;
import tapestry.projecttracker.entities.Member;
import tapestry.projecttracker.entities.Project;
import tapestry.projecttracker.pages.view.ViewProject;
import tapestry.projecttracker.prop.WorkType;

/**
 * Page for creating logs on a project
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class CreateLog {

    /* Properties */
    @Property
    private Project project;
    @Property
    private List<Project> projects;
    @Property
    private Member member;
    @Property
    private List<Member> members;
    @SessionState
    private Member loggedInMember;

    /* Services */
    @Inject
    private ProjectDAO projectDao;
    @Inject
    private MemberDAO memberDao;
    @Inject
    private ActivityDAO activityDao;
    @InjectPage
    private ViewProject viewProjectPage;

    @Property
    @Validate("required")
    private String logComment;

    @Property
    @Validate("required")
    private WorkType logWork;

    @Property
    @Validate("required")
    private double logTime;

    /**
     * Format the Date instance as 'Fri 05, 2015'
     *
     * @param dateToFormat
     * @return String with formatted date
     */
    public String getDateFormat(Date dateToFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat("MMM d, yyyy");
        return formatter.format(dateToFormat);
    }

    /**
     * Get dropdown list of WorkType values e.g. Animation, Lighting, Storyboard
     *
     * @return List of WorkType enum values
     */
    public WorkType[] getWorkTypes() {
        WorkType[] workTypes = WorkType.values();
        return workTypes;
    }

    /**
     * Get all members and projects as lists
     */
    void onPrepare() {
        members = memberDao.getAllMembers();
        projects = projectDao.getAllProjects();

        if (projects == null) {
            projects = new ArrayList<>();
        }
    }

    /**
     * Get Role of the logged in member
     *
     * @return True if logged in member is either Admin or Supervisor
     */
    public boolean getLoggedInRole() {
        return (loggedInMember.getMemberRole().name() != "Member") ? true : false;
    }

   
    /**
     * Set active project for the page to render properly
     * @param project Project instance to show
     */
    public void set(Project project) {
        this.project = project;
    }

    void onActivate(Project project) {
        this.project = project;
    }

    Project onPassivate() {
        return project;
    }

    /* Creating log form validation */
    @CommitAfter
    Object onSubmitFromLogTimeForm() {
        Log newLog = new Log(loggedInMember.getMemberId(), project.getProjectId(), logComment, logTime, logWork);
        projectDao.addLog(newLog);

//      ACTIVITY RECORD
        Activity activity = activityDao.recordActivity(loggedInMember, ("logged " + newLog.getLogTime() + " hours of " + newLog.getLogWork() + " on " + project));

        project.setProjectTime(projectDao.getProjectLoggedTime(project));
        viewProjectPage.set(project);
        return viewProjectPage;
    }
}
