
package tapestry.projecttracker.data;

import java.util.List;
import tapestry.projecttracker.entities.Log;
import tapestry.projecttracker.entities.Member;
import tapestry.projecttracker.entities.Project;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public interface ProjectDAO {

    /**
     * Add new Project instance to DB
     * @param project Project instance to be added
     */
    public void addProject(Project project);

    /**
     * Update (merge) Project instance to DB
     * @param project Project instance to be merged
     * @return Project that was updated
     */
    public Project updateProject(Project project);

    /**
     * Delete specified Project instance from DB
     * @param id Unique project id
     */
    public void deleteProject(Integer id);

    /**
     * Get all projects from DB 
     * @return List of all projects 
     */
    public List<Project> getAllProjects();

    /**
     * Get project by specified Id
     * @param id Unique project id
     * @return Project that was found in DB
     */
    public Project getProjectById(Integer id);

    /**
     * Get project by specified title name
     * @param projectTitle String as project name
     * @return Project that was found in DB
     */
    public Project getProjectByTitle(String projectTitle);
    
    /**
     * Check if Project exists in DB
     * @param projectTitle String as project name
     * @return True if the project was found in DB
     */
    public boolean checkIfProjectExists(String projectTitle);

    /**
     * Remove assigned members from specified project
     * @param project Project instance 
     * @return Project instance with removed members (empty assignedMembers property)
     */
    public Project removeAssignedFromProject(Project project);

    /**
     * Get total time logged on a specified project
     * @param project Project instance
     * @return Number of hours logged on a project
     */
    public double getProjectLoggedTime(Project project);

    /**
     * Get all logs of a specified project
     * @param project Project instance
     * @return List of logs for a project
     */
    public List<Log> getLogsByProject(Project project);
    
    /**
     * Add Log instance to DB
     * @param log Log instance to be added
     */
    public void addLog(Log log);

    /**
     * Get all logs from DB
     * @return List of Log instances
     */
    public List<Log> getAllLogs();

    /**
     * Get log by specified id
     * @param id Unique log's id
     * @return Log that was found in DB
     */
    public Log getLogById(Integer id);

    /**
     * Delete log from DB
     * @param id Unique log's id
     */
    public void deleteLog(Integer id);

}
