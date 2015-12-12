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

    @Inject
    public Log() {
    }

    public Log(Integer logMemberId, Integer logProjectId, String logComment, double logTime, WorkType logWork) {
        this.logMemberId = logMemberId;
        this.logProjectId = logProjectId;
        this.logComment = logComment;
        this.logTime = logTime;
        this.logWork = logWork;
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Integer getLogMemberId() {
        return logMemberId;
    }

    public void setLogMemberId(Integer logMemberId) {
        this.logMemberId = logMemberId;
    }

    public Integer getLogProjectId() {
        return logProjectId;
    }

    public void setLogProjectId(Integer logProjectId) {
        this.logProjectId = logProjectId;
    }

    public String getLogComment() {
        return logComment;
    }

    public void setLogComment(String logComment) {
        this.logComment = logComment;
    }

    public Date getLogAdded() {
        return logAdded;
    }

    public void setLogAdded(Date logAdded) {
        this.logAdded = logAdded;
    }

    public double getLogTime() {
        return logTime;
    }

    public void setLogTime(double logTime) {
        this.logTime = logTime;
    }

    public WorkType getLogWork() {
        return logWork;
    }

    public void setLogWork(WorkType logWork) {
        this.logWork = logWork;
    }

    public String getLogMemberName() {
        return logMemberName;
    }

    public void setLogMemberName(String logMemberName) {
        this.logMemberName = logMemberName;
    }

}
