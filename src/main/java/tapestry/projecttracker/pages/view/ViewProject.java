/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.projecttracker.pages.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
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

public class ViewProject {

    @Inject
    private ProjectDAO projectDao;

    @Inject
    private MemberDAO memberDao;

    @Property
    private Project project;

    @Property
    private List<Project> projects;

    @Property
    private Member member;

    @Property
    private List<Member> members;

    @Property
    private List<Member> selectedMembers;

    @SessionState
    private Member loggedInMember;

    @Property
    private Log log;

    @Property
    private List<Log> logs;

    @Property
    private BeanModel<Log> logTableGrid;
    @Inject
    private BeanModelSource beanModelSource;
    @Inject
    private Messages messages;

    @Property
    private final MemberEncoder memberEncoder = new MemberEncoder(memberDao);

    public ProjectCategory[] getCategories() {
        ProjectCategory[] categories = ProjectCategory.values();
        return categories;
    }

    public ProjectStatus[] getStatuses() {
        ProjectStatus[] statuses = ProjectStatus.values();
        return statuses;
    }

    public String getStartDateFormat() {
        SimpleDateFormat formatter = new SimpleDateFormat("MMM d, yyyy");
        return formatter.format(project.getProjectStart());
    }

    public String getDueDateFormat() {
        SimpleDateFormat formatter = new SimpleDateFormat("MMM d, yyyy");
        return formatter.format(project.getProjectDue());
    }

    public String getCreationDateFormat() {
        SimpleDateFormat formatter = new SimpleDateFormat("MMM d, yyyy");
        return formatter.format(project.getProjectCreationDate());
    }

    void onPrepare() {
        members = memberDao.getAllMembers();
        projects = projectDao.getAllProjects();
        logs = projectDao.getAllLogs();
        if (selectedMembers == null) {
            selectedMembers = new ArrayList<>();
        }
        if (projects == null) {
            projects = new ArrayList<>();
        }
        if (logs == null) {
            logs = new ArrayList<>();
        }
        selectedMembers = project.getAssignedMembers();
        Collections.sort(selectedMembers);
    }

    public SortedSet<Member> getSortedAssignedMembers() {
        return new TreeSet(project.getAssignedMembers());
    }

    public void set(Project project) {
        this.project = project;
    }

    void onActivate(Project project) {
        this.project = project;
    }

    Project onPassivate() {
        return project;
    }

    void setupRender() {
        logTableGrid = beanModelSource.createDisplayModel(Log.class, messages);
        logTableGrid.include("logId","logMemberId","logComment","logAdded","logTime","logWork");
        logTableGrid.get("logId").label("Log #");
        logTableGrid.get("logMemberId").label("Added by");
        logTableGrid.get("logComment").label("Comment");
        logTableGrid.get("logComment").sortable(false);
        logTableGrid.get("logAdded").label("Added on");
        logTableGrid.get("logTime").label("Hours logged");
        logTableGrid.get("logWork").label("Work type");
    }

    public boolean getLoggedInRole() {
        return (loggedInMember.getMemberRole().name() != "Member") ? true : false;
    }

    void onValidateFromProjectSelectForm() {
        System.out.println("VALIDATION CALLED.... " + ": PROJECT : " + project);
        if (project == null) {
            project = projects.get(0);
        }
    }

    public List<Log> getFilteredLogs() {
        List<Log> filteredLogs = new ArrayList<>();
        for (Log entry : logs) {
            if (entry.getLogProjectId().equals(project.getProjectId())) {
                filteredLogs.add(entry);
            }
        }
        return filteredLogs;
    }

    /* Method returning true if the member is assigned to selected project
        Used for log time button visibility
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

    @CommitAfter
    void onDeleteLog(Integer id) {
        projectDao.deleteLog(id);
    }
}
