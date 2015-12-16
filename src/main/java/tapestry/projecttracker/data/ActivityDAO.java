
package tapestry.projecttracker.data;

import java.util.List;
import tapestry.projecttracker.entities.Activity;
import tapestry.projecttracker.entities.Member;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public interface ActivityDAO {
    
    /**
     * Method to record user activity, explained as 'action'
     * @param member Member doing the activity
     * @param action Action that was done 
     * @return Activity that was stored
     */
    public Activity recordActivity(Member member, String action);
    
    /**
     * Get all activities as a list
     * @return List of activities from DB
     */
    public List<Activity> getAllActivities();
    
    /**
     * Delete activity by it's ID
     * @param id Activity unique id
     */
    public void deleteActivity(Integer id);
}
