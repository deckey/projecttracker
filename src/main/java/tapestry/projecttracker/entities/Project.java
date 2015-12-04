package tapestry.projecttracker.entities;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.ioc.annotations.Inject;
import tapestry.projecttracker.prop.ProjectCategory;
import tapestry.projecttracker.prop.ProjectStatus;

@Entity
@Table(name = "tbl_project")
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "projectId")
    private Integer projectId;

    @Validate("required")
    private String projectTitle;

    @Validate("required")
    private String projectDescription;

    @Validate("required")
    private String projectClient;

    @Enumerated(EnumType.STRING)
    private ProjectCategory projectCategory;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date projectStart;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date projectEnd;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date projectDue;

    private double projectTime;

    @Validate("required")
    @Enumerated(EnumType.STRING)
    private ProjectStatus projectStatus;

    @Inject
    public Project() {
    }

    public Project(String projectTitle, String projectDescription, String projectClient, ProjectCategory category, Date projectStart, Date projectEnd, Date projectDue, double projectTime, ProjectStatus status) {
        this.projectTitle = projectTitle;
        this.projectDescription = projectDescription;
        this.projectClient = projectClient;
        this.projectCategory = category;
        this.projectStart = projectStart;
        this.projectEnd = projectEnd;
        this.projectDue = projectDue;
        this.projectTime = projectTime;
        this.projectStatus = status;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getProjectClient() {
        return projectClient;
    }

    public void setProjectClient(String projectClient) {
        this.projectClient = projectClient;
    }

    public ProjectCategory getProjectCategory() {
        return projectCategory;
    }

    public void setProjectCategory(ProjectCategory projectCategory) {
        this.projectCategory = projectCategory;
    }

    public Date getProjectStart() {
        return projectStart;
    }

    public void setProjectStart(Date projectStart) {
        this.projectStart = projectStart;
    }

    public Date getProjectEnd() {
        return projectEnd;
    }

    public void setProjectEnd(Date projectEnd) {
        this.projectEnd = projectEnd;
    }

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

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }

    @Override
    public String toString() {
        return this.projectTitle; //To change body of generated methods, choose Tools | Templates.
    }

    
}
