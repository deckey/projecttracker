package tapestry.projecttracker.entities;

import java.util.Date;
import java.util.HashSet;
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
import org.apache.tapestry5.beaneditor.Validate;
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
    private Set<Member> assignedMembers;

    @Inject
    public Project() {
    }

    public Project(String projectTitle) {
        this.projectTitle = projectTitle;
        this.assignedMembers=new HashSet<>();
    }

    public Project(String projectTitle, String projectDescription, String projectClient, Date projectStart, Date projectDue, ProjectCategory projectCategory, ProjectStatus projectStatus, Set<Member> assignedMembers) {
        this.projectTitle = projectTitle;
        this.projectDescription = projectDescription;
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

    @Validate("required")
    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    @Validate("required")
    public String getProjectClient() {
        return projectClient;
    }

    public void setProjectClient(String projectClient) {
        this.projectClient = projectClient;
    }

    @Validate("required")
    @Enumerated(EnumType.STRING)
    public ProjectCategory getProjectCategory() {
        return projectCategory;
    }

    public void setProjectCategory(ProjectCategory projectCategory) {
        this.projectCategory = projectCategory;
    }

    @Validate("required")
    public Date getProjectStart() {
        return projectStart;
    }

    public void setProjectStart(Date projectStart) {
        this.projectStart = projectStart;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getProjectEnd() {
        return projectEnd;
    }

    public void setProjectEnd(Date projectEnd) {
        this.projectEnd = projectEnd;
    }

    @Validate("required")
    public Date getProjectDue() {
        return projectDue;
    }

    public void setProjectDue(Date projectDue) {
        this.projectDue = projectDue;
    }

    public double getProjectTime() {
        return projectTime;
    }

    public void setProjectTime(double projectTime) {
        this.projectTime = projectTime;
    }

    @Validate("required")
    @Enumerated(EnumType.STRING)
    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }

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
    public Set<Member> getAssignedMembers() {
        return assignedMembers;
    }

    public void setAssignedMembers(Set<Member> assignedMembers) {
        this.assignedMembers = assignedMembers;
    }

    @Override
    public String toString() {
        return this.projectTitle; //To change body of generated methods, choose Tools | Templates.
    }

}
