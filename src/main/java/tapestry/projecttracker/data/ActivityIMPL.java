/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.projecttracker.data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import tapestry.projecttracker.entities.Activity;
import tapestry.projecttracker.entities.Member;

/**
 *
 * @author Dejan Ivanovic
 */
public class ActivityIMPL implements ActivityDAO {

    @Inject
    private Session dbs;

    @Inject
    private MemberDAO memberDao;

    @Override
    public Activity recordActivity(Member member, String action) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");

        String output = "" + member + " " 
                + action + " " 
                + " on " + dateFormat.format(date) 
                + " at " + timeFormat.format(date);

        Activity activity = new Activity(member.getMemberId(), action, new Date(), output);
        return (Activity) dbs.merge(activity);
    }


    @Override
    public List<Activity> getAllActivities() {
        return dbs.createCriteria(Activity.class).list();
    }

    @Override
    public void deleteActivity(Integer id) {
        Activity activity = (Activity) dbs.createCriteria(Activity.class).add(Restrictions.eq("activityId", id)).uniqueResult();
        dbs.delete(activity);
    }
    
    
}
