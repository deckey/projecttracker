package tapestry.projecttracker.pages.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.alerts.Duration;
import org.apache.tapestry5.alerts.Severity;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;
import tapestry.projecttracker.data.MemberDAO;
import tapestry.projecttracker.data.ProjectDAO;
import tapestry.projecttracker.encoders.MemberEncoder;
import tapestry.projecttracker.entities.Log;
import tapestry.projecttracker.entities.Member;
import tapestry.projecttracker.entities.Project;
import tapestry.projecttracker.prop.ProjectCategory;
import tapestry.projecttracker.prop.ProjectStatus;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class ViewProject {

    /* Properties */
    @SessionState
    private Member loggedInMember;

    @Property
    private Log log;

    @Property
    private BeanModel<Log> logTableGrid;

    @Property
    private List<Log> logs;

    @Property
    private Member member;

    @Property
    private double memberProjectHours;

    @Property
    private List<Member> members;

    @Property
    private Project project;

    @Property
    private List<Project> projects;

    @Property
    private List<Member> selectedMembers;

    /* Services */
    @Inject
    private AlertManager alertManager;
    @Inject
    private BeanModelSource beanModelSource;
    @Inject
    private MemberDAO memberDao;
    @Inject
    private Messages messages;
    @Inject
    private ProjectDAO projectDao;

    @Property
    private final MemberEncoder memberEncoder = new MemberEncoder(memberDao);

    /**
     * Get dropdown list of ProjectCategory types (Animation, MotionGraphics...)
     *
     * @return List of ProjectCategory enum values
     */
    public ProjectCategory[] getCategories() {
        ProjectCategory[] categories = ProjectCategory.values();
        return categories;
    }

    /**
     * Get dropdown list of ProjectStatus types (Active, Completed ...)
     *
     * @return List of ProjectStatus enum values
     */
    public ProjectStatus[] getStatuses() {
        ProjectStatus[] statuses = ProjectStatus.values();
        return statuses;
    }

    /**
     * Format the Date instance as 'Fri 05, 2015'
     *
     * @param dateToFormat Date instance to format
     * @return String with formatted date
     */
    public String getDateFormat(Date dateToFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat("MMM d, yyyy");
        return formatter.format(dateToFormat);
    }

    /* Page rendering */
    void onPrepare() {
        // get all projects, members and logs
        members = memberDao.getAllMembers();
        projects = projectDao.getAllProjects();
        logs = projectDao.getAllLogs();

        /* Avoid NullPointer exception by initializing empty lists */
        if (selectedMembers == null) {
            selectedMembers = new ArrayList<>();
        }
        if (projects == null) {
            projects = new ArrayList<>();
        }
        if (logs == null) {
            logs = new ArrayList<>();
        }

        /* get and sort members assigned on a project */
        selectedMembers = project.getAssignedMembers();
        Collections.sort(selectedMembers);
    }

    /**
     * Set active project for the page to render properly
     *
     * @param project Project instance to show
     */
    public void set(Project project) {
        this.project = project;
    }

    void setupRender() {
        logTableGrid = beanModelSource.createDisplayModel(Log.class, messages);
        logTableGrid.include("logId", "logMemberId", "logComment", "logAdded", "logTime", "logWork");
        logTableGrid.get("logId").label("Log #");
        logTableGrid.get("logMemberId").label("Added by");
        logTableGrid.get("logComment").label("Comment");
        logTableGrid.get("logComment").sortable(false);
        logTableGrid.get("logAdded").label("Added on");
        logTableGrid.get("logTime").label("Hours logged");
        logTableGrid.get("logWork").label("Work type");
    }

    void onActivate(Project project) {
        this.project = project;
    }

    Project onPassivate() {
        return project;
    }

    /**
     * Sort and return list of assigned members
     *
     * @return Sorted set of members
     */
    public SortedSet<Member> getSortedAssignedMembers() {
        return new TreeSet(project.getAssignedMembers());
    }

    /**
     * Activation context from CreateProject page sending success message
     *
     * @param message Showing project was successfully created
     */
    public void setSuccessAlert(String message) {
        alertManager.alert(Duration.TRANSIENT, Severity.SUCCESS, message);
    }

    /**
     * Check if logged in user is not a Member, being either Admin or Supervisor
     *
     * @return True if user can edit the project
     */
    public boolean getLoggedInRole() {
        return (loggedInMember.getMemberRole().name() != "Member") ? true : false;
    }

    /**
     * Get logs for a current project
     *
     * @return List of logs submitted for this project
     */
    public List<Log> getFilteredLogs() {
        List<Log> filteredLogs = new ArrayList<>();
        for (Log entry : logs) {
            if (entry.getLogProjectId().equals(project.getProjectId())) {
                filteredLogs.add(entry);
            }
        }
        return filteredLogs;
    }

    /**
     * Get total of hours that member spent on a project, calculating sum of log
     * times
     *
     * @return Number of hours (double)
     */
    public double getHoursByMember() {
        List<Log> filteredLogs = new ArrayList<>();
        memberProjectHours = 0;
        for (Log entry : logs) {
            if (entry.getLogProjectId().equals(project.getProjectId())) {
                if (entry.getLogMemberId().equals(member.getMemberId())) {
                    memberProjectHours += entry.getLogTime();
                }
            }
        }
        return memberProjectHours;
    }

    /**
     * Method returning true if the member is assigned to selected project 
     * Used for 'log time' button visibility
     *
     * @return True if user can log time on a current project
     */
    public boolean getCheckProjectMember() {
        System.out.println("SELECTED MEMBERS...." + selectedMembers);
        System.out.println("LOGGED IN MEMBER..." + loggedInMember);
        for (Member mem : selectedMembers) {
            if (mem.getMemberUsername().equals(loggedInMember.getMemberUsername())) {
                System.out.println("LOGGED IN MEMBER FOUND AS ASSIGNED");
                return true;
            }
        }
        return false;
    }

    
    /**
     * Called onDeleteLog event
     * check if user can delete the log, or send alert message
     * @param id Unique log id
     */
    @CommitAfter
    void onDeleteLog(Integer id) {
        Log log = projectDao.getLogById(id);
        /* member owns the log, so it can be deleted */
        if (log.getLogMemberId().equals(loggedInMember.getMemberId())) {
            projectDao.deleteLog(id);
        } else {
            /* alert that log can't be deleted */
            alertManager.alert(Duration.TRANSIENT, Severity.ERROR,
                    "Log can only be deleted by it's creator!");
        }
    }

    /* Form validation */
    void onValidateFromProjectSelectForm() {
        /* If dropdown empty field is selected, show first project on the list */
        if (project == null) {
            project = projects.get(0);
        }
    }
}
