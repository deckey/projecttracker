package tapestry.projecttracker.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.tapestry5.ioc.annotations.Inject;
import tapestry.projecttracker.prop.ProjectCategory;
import tapestry.projecttracker.prop.ProjectStatus;

@Entity
@Table(name = "tbl_project")
public class Project implements java.io.Serializable {

    private Integer projectId;
    private String projectTitle;
    private String projectDescription;
    private String projectClient;
    private ProjectCategory projectCategory;
    private Date projectStart;
    private Date projectEnd;
    private Date projectDue;
    private double projectTime;
    private ProjectStatus projectStatus;
    private Date projectCreationDate;
    private List<Member> assignedMembers;

    @Inject
    public Project() {
    }

    public Project(String projectTitle) {
        this.projectTitle = projectTitle;
        this.assignedMembers=new ArrayList<>();
    }

    public Project(String projectTitle, String projectClient, Date projectStart, Date projectDue, ProjectCategory projectCategory, ProjectStatus projectStatus, List<Member> assignedMembers) {
        this.projectTitle = projectTitle;
        this.projectDescription = "No description provided ...";
        this.projectClient = projectClient;
        this.projectCategory = projectCategory;
        this.projectStart = projectStart;
        this.projectDue = projectDue;
        this.projectStatus = projectStatus;
        this.projectCreationDate = new Date();
        this.assignedMembers = assignedMembers;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "projectId")
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @Column(name = "projectTitle")
    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    @Column(name = "projectDescription")
    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    @Column(name = "projectClient")
    public String getProjectClient() {
        return projectClient;
    }

    public void setProjectClient(String projectClient) {
        this.projectClient = projectClient;
    }

    @Column(name = "projectCategory")
    @Enumerated(EnumType.STRING)
    public ProjectCategory getProjectCategory() {
        return projectCategory;
    }

    public void setProjectCategory(ProjectCategory projectCategory) {
        this.projectCategory = projectCategory;
    }

    @Column(name = "projectStart")
    public Date getProjectStart() {
        return projectStart;
    }

    public void setProjectStart(Date projectStart) {
        this.projectStart = projectStart;
    }

    @Column(name = "projectEnd")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getProjectEnd() {
        return projectEnd;
    }

    public void setProjectEnd(Date projectEnd) {
        this.projectEnd = projectEnd;
    }

    @Column(name = "projectDue")
    public Date getProjectDue() {
        return projectDue;
    }

    public void setProjectDue(Date projectDue) {
        this.projectDue = projectDue;
    }

    @Column(name = "projectTime")
    public double getProjectTime() {
        return projectTime;
    }

    public void setProjectTime(double projectTime) {
        this.projectTime = projectTime;
    }

    @Column(name = "projectStatus")
    @Enumerated(EnumType.STRING)
    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }

    @Column(name = "projectCreationDate")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getProjectCreationDate() {
        return projectCreationDate;
    }

    public void setProjectCreationDate(Date projectCreationDate) {
        this.projectCreationDate = projectCreationDate;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "project_member", joinColumns = {
        @JoinColumn(name = "projectId")}, inverseJoinColumns = {
        @JoinColumn(name = "memberId")})
    public List<Member> getAssignedMembers() {
        return assignedMembers;
    }

    public void setAssignedMembers(List<Member> assignedMembers) {
        this.assignedMembers = assignedMembers;
    }

    @Override
    public String toString() {
        return this.projectTitle; //To change body of generated methods, choose Tools | Templates.
    }

}
