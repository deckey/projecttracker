
package tapestry.projecttracker.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
@Entity
@Table(name="tbl_activity")
public class Activity {
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="activityId")
    private Integer activityId;
    
    @Column(name="activityMemberId")
    private Integer activityMemberId;
    
    @Column(name="activityAction")
    private String activityAction;
    
    @Column(name="activityDate")
    @Temporal(TemporalType.DATE)
    private Date activityDate;
    
    @Column(name="activityOutput")
    private String activityOutput;
    
    /**
     * Empty constructor
     */
    @Inject
    public Activity() {
    }

    /**
     * Constructor with action as parameter
     * @param activityAction String explaining activity
     */
    public Activity(String activityAction) {
        this.activityAction = activityAction;
    }

    /**
     * Complete constructor
     * @param activityMemberId Member creating the activity
     * @param activityAction String explaining activity action
     * @param activityDate Date and time at which activity was logged
     * @param activityOutput String formatting entry as human readable
     */
    public Activity(Integer activityMemberId, String activityAction, Date activityDate, String activityOutput) {
        this.activityMemberId = activityMemberId;
        this.activityAction = activityAction;
        this.activityDate = activityDate;
        this.activityOutput = activityOutput;
    }
    
    /**
     *
     * @return
     */
    public Integer getActivityId() {
        return activityId;
    }

    /**
     *
     * @param activityId
     */
    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }
    
    /**
     *
     * @return
     */
    public Integer getActivityMemberId() {
        return activityMemberId;
    }

    /**
     *
     * @param activityMemberId
     */
    public void setActivityMemberId(Integer activityMemberId) {
        this.activityMemberId = activityMemberId;
    }

    /**
     *
     * @return
     */
    public String getActivityAction() {
        return activityAction;
    }

    /**
     *
     * @param activityAction
     */
    public void setActivityAction(String activityAction) {
        this.activityAction = activityAction;
    }

    /**
     *
     * @return
     */
    public Date getActivityDate() {
        return activityDate;
    }

    /**
     *
     * @param activityDate
     */
    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }    

    /**
     *
     * @return
     */
    public String getActivityOutput() {
        return activityOutput;
    }

    /**
     *
     * @param activityOutput
     */
    public void setActivityOutput(String activityOutput) {
        this.activityOutput = activityOutput;
    }
}
