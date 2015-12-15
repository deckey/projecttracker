/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.projecttracker.data;

import java.util.List;
import tapestry.projecttracker.entities.Activity;
import tapestry.projecttracker.entities.Member;

/**
 *
 * @author Dejan Ivanovic
 */
public interface ActivityDAO {
    
    public Activity recordActivity(Member member, String action);
    
    public String formatActivity(Activity activity, String actionMessage);
    
    public List<Activity> getAllActivities();
    
    public void deleteActivity(Integer id);
}
