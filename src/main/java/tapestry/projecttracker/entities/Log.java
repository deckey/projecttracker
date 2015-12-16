package tapestry.projecttracker.entities;

import java.io.Serializable;
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
import javax.persistence.TemporalType;
import org.apache.tapestry5.ioc.annotations.Inject;
import tapestry.projecttracker.prop.WorkType;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
@Entity
@Table(name = "tbl_log")
public class Log implements Serializable {

    @Column(name = "logId")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer logId;

    @Column(name = "logMemberId")
    private Integer logMemberId;

    @Column(name = "logProjectId")
    private Integer logProjectId;

    @Column(name = "logComment")
    private String logComment;

    @Column(name = "logAdded")
    @Temporal(TemporalType.DATE)
    private Date logAdded;

    @Column(name = "logTime")
    private double logTime;

    @Column(name = "logWork")
    @Enumerated(EnumType.STRING)
    private WorkType logWork;

    @Column(name = "logMemberName")
    private String logMemberName;

    /**
     * Empty constructor
     */
    @Inject
    public Log() {
    }

    /**
     * Default constructor 
     * @param logMemberId Member id of log creator
     * @param logProjectId Project id of log's project
     * @param logComment String to comment log entry
     * @param logTime Hours logged
     * @param logWork Type of work that is logged
     */
    public Log(Integer logMemberId, Integer logProjectId, String logComment, double logTime, WorkType logWork) {
        this.logMemberId = logMemberId;
        this.logProjectId = logProjectId;
        this.logComment = logComment;
        this.logTime = logTime;
        this.logWork = logWork;
    }

    /**
     *
     * @return
     */
    public Integer getLogId() {
        return logId;
    }

    /**
     *
     * @param logId
     */
    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    /**
     *
     * @return
     */
    public Integer getLogMemberId() {
        return logMemberId;
    }

    /**
     *
     * @param logMemberId
     */
    public void setLogMemberId(Integer logMemberId) {
        this.logMemberId = logMemberId;
    }

    /**
     *
     * @return
     */
    public Integer getLogProjectId() {
        return logProjectId;
    }

    /**
     *
     * @param logProjectId
     */
    public void setLogProjectId(Integer logProjectId) {
        this.logProjectId = logProjectId;
    }

    /**
     *
     * @return
     */
    public String getLogComment() {
        return logComment;
    }

    /**
     *
     * @param logComment
     */
    public void setLogComment(String logComment) {
        this.logComment = logComment;
    }

    /**
     *
     * @return
     */
    public Date getLogAdded() {
        return logAdded;
    }

    /**
     *
     * @param logAdded
     */
    public void setLogAdded(Date logAdded) {
        this.logAdded = logAdded;
    }

    /**
     *
     * @return
     */
    public double getLogTime() {
        return logTime;
    }

    /**
     *
     * @param logTime
     */
    public void setLogTime(double logTime) {
        this.logTime = logTime;
    }

    /**
     *
     * @return
     */
    public WorkType getLogWork() {
        return logWork;
    }

    /**
     *
     * @param logWork
     */
    public void setLogWork(WorkType logWork) {
        this.logWork = logWork;
    }

    /**
     *
     * @return
     */
    public String getLogMemberName() {
        return logMemberName;
    }

    /**
     *
     * @param logMemberName
     */
    public void setLogMemberName(String logMemberName) {
        this.logMemberName = logMemberName;
    }
}
