package tapestry.projecttracker.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
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

    /**
     * Empty constructor
     */
    @Inject
    public Project() {
    }

    /**
     * Constructor with project name
     * @param projectTitle Name for the project
     */
    public Project(String projectTitle) {
        this.projectTitle = projectTitle;
        this.assignedMembers=new ArrayList<>();
    }

    /**
     * Default constructor
     * @param projectTitle Name for the project
     * @param projectClient Company this project is done for
     * @param projectStart Start date of the project
     * @param projectDue Due date for the project
     * @param projectCategory Category of the project (Game, Animation, VFX...)
     * @param projectStatus Status of the project (Archive, Active, Inactive, Completed)
     * @param assignedMembers List of members working on a project
     */
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

    /**
     * 
     * @return
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "projectId")
    public Integer getProjectId() {
        return projectId;
    }

    /**
     *
     * @param projectId
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    /**
     *
     * @return
     */
    @Column(name = "projectTitle")
    public String getProjectTitle() {
        return projectTitle;
    }

    /**
     *
     * @param projectTitle
     */
    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    /**
     *
     * @return
     */
    @Column(name = "projectDescription")
    public String getProjectDescription() {
        return projectDescription;
    }

    /**
     *
     * @param projectDescription
     */
    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    /**
     *
     * @return
     */
    @Column(name = "projectClient")
    public String getProjectClient() {
        return projectClient;
    }

    /**
     *
     * @param projectClient
     */
    public void setProjectClient(String projectClient) {
        this.projectClient = projectClient;
    }

    /**
     *
     * @return
     */
    @Column(name = "projectCategory")
    @Enumerated(EnumType.STRING)
    public ProjectCategory getProjectCategory() {
        return projectCategory;
    }

    /**
     *
     * @param projectCategory
     */
    public void setProjectCategory(ProjectCategory projectCategory) {
        this.projectCategory = projectCategory;
    }

    /**
     *
     * @return
     */
    @Column(name = "projectStart")
    public Date getProjectStart() {
        return projectStart;
    }

    /**
     *
     * @param projectStart
     */
    public void setProjectStart(Date projectStart) {
        this.projectStart = projectStart;
    }

    /**
     *
     * @return
     */
    @Column(name = "projectEnd")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getProjectEnd() {
        return projectEnd;
    }

    /**
     *
     * @param projectEnd
     */
    public void setProjectEnd(Date projectEnd) {
        this.projectEnd = projectEnd;
    }

    /**
     *
     * @return
     */
    @Column(name = "projectDue")
    public Date getProjectDue() {
        return projectDue;
    }

    /**
     *
     * @param projectDue
     */
    public void setProjectDue(Date projectDue) {
        this.projectDue = projectDue;
    }

    /**
     *
     * @return
     */
    @Column(name = "projectTime")
    public double getProjectTime() {
        return projectTime;
    }

    /**
     *
     * @param projectTime
     */
    public void setProjectTime(double projectTime) {
        this.projectTime = projectTime;
    }

    /**
     *
     * @return
     */
    @Column(name = "projectStatus")
    @Enumerated(EnumType.STRING)
    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    /**
     *
     * @param projectStatus
     */
    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }

    /**
     *
     * @return
     */
    @Column(name = "projectCreationDate")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getProjectCreationDate() {
        return projectCreationDate;
    }

    /**
     *
     * @param projectCreationDate
     */
    public void setProjectCreationDate(Date projectCreationDate) {
        this.projectCreationDate = projectCreationDate;
    }

    /**
     *
     * @return
     */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "project_member", joinColumns = {
        @JoinColumn(name = "projectId")}, inverseJoinColumns = {
        @JoinColumn(name = "memberId")})
    public List<Member> getAssignedMembers() {
        return assignedMembers;
    }

    /**
     *
     * @param assignedMembers
     */
    public void setAssignedMembers(List<Member> assignedMembers) {
        this.assignedMembers = assignedMembers;
    }

    @Override
    public String toString() {
        return this.getProjectTitle(); //To change body of generated methods, choose Tools | Templates.
    }

}
