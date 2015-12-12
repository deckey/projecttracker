/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.projecttracker.pages.create;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import tapestry.projecttracker.data.MemberDAO;
import tapestry.projecttracker.data.ProjectDAO;
import tapestry.projecttracker.entities.Log;
import tapestry.projecttracker.entities.Member;
import tapestry.projecttracker.entities.Project;
import tapestry.projecttracker.pages.view.ViewProject;
import tapestry.projecttracker.prop.WorkType;

/**
 *
 * @author Dejan Ivanovic
 */
public class CreateLog {

    @Property
    private Project project;
    @Property
    private List<Project> projects;

    @Inject
    private ProjectDAO projectDao;
    @Inject
    private MemberDAO memberDao;

    @Property
    private Member member;
    @Property
    private List<Member> members;

    @SessionState
    private Member loggedInMember;
    @Property
    private List<Member> selectedMembers;

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

    public WorkType[] getWorkTypes() {
        WorkType[] workTypes = WorkType.values();
        return workTypes;
    }

    void onPrepare() {
        members = memberDao.getAllMembers();
        projects = projectDao.getAllProjects();
       
        if (selectedMembers == null) {
            selectedMembers = new ArrayList<>();
        }
        if(projects==null){
            projects = new ArrayList<>();
        }
        
        Collections.sort(selectedMembers);
    }

    public void set(Project project) {
        this.project = project;
    }

    void onActivate(Project project) {
        this.project = project;
        selectedMembers = project.getAssignedMembers();
    }

    Project onPassivate() {
        return project;
    }

    public SortedSet<Member> getSortedAssignedMembers() {
        return new TreeSet(project.getAssignedMembers());
    }
    
   
    public boolean getLoggedInRole() {
        return (loggedInMember.getMemberRole().name() != "Member") ? true : false;
    }

    @CommitAfter
    Object onSubmitFromLogTimeForm() {
        Log newLog = new Log(loggedInMember.getMemberId(), project.getProjectId(), logComment, logTime, logWork);
        projectDao.addLog(newLog);
        project.setProjectTime(projectDao.getProjectLoggedTime(project));
        viewProjectPage.set(project);
        return viewProjectPage;
    }

    /* Method returning true if the member is assigned to selected project
        Used for log time button visibility
     */
    public boolean getCheckProjectMember() {
        for (Member mem : selectedMembers) {
            if (mem.getMemberUsername().equals(loggedInMember.getMemberUsername())) {
                return true;
            }
        }
        return false;
    }
}
