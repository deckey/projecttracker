
package tapestry.projecttracker.entities;

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
@Table(name="tbl_log")
public class Log {
    
    private Integer logId;
    private Integer logMember;
    private String logComment;
    private Date logAdded;
    private double logTime;
    private WorkType logWork;

    @Inject
    public Log() {
    }

    @Column(name="logId")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    @Column(name="logMember")
    public Integer getLogMember() {
        return logMember;
    }

    public void setLogMember(Integer logMember) {
        this.logMember = logMember;
    }
    
    @Column(name="logComment")
    public String getLogComment() {
        return logComment;
    }

    public void setLogComment(String logComment) {
        this.logComment = logComment;
    }

    @Column(name="logAdded")
    @Temporal(TemporalType.DATE)
    public Date getLogAdded() {
        return logAdded;
    }

    public void setLogAdded(Date logAdded) {
        this.logAdded = logAdded;
    }

    @Column(name="logTime")
    public double getLogTime() {
        return logTime;
    }

    public void setLogTime(double logTime) {
        this.logTime = logTime;
    }

    @Column(name="logWork")
    @Enumerated(EnumType.STRING)
    public WorkType getLogWork() {
        return logWork;
    }

    public void setLogWork(WorkType logWork) {
        this.logWork = logWork;
    }

    
    
}
