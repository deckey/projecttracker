/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author Dejan Ivanovic
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
    
    
    @Inject
    public Activity() {
    }

    public Activity(String activityAction) {
        this.activityAction = activityAction;
    }

    public Activity(Integer activityMemberId, String activityAction, Date activityDate, String activityOutput) {
        this.activityMemberId = activityMemberId;
        this.activityAction = activityAction;
        this.activityDate = activityDate;
        this.activityOutput = activityOutput;
    }
    
    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }
    
    public Integer getActivityMemberId() {
        return activityMemberId;
    }

    public void setActivityMemberId(Integer activityMemberId) {
        this.activityMemberId = activityMemberId;
    }

    public String getActivityAction() {
        return activityAction;
    }

    public void setActivityAction(String activityAction) {
        this.activityAction = activityAction;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }    

    public String getActivityOutput() {
        return activityOutput;
    }

    public void setActivityOutput(String activityOutput) {
        this.activityOutput = activityOutput;
    }
}
