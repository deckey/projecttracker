/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.projecttracker.data;

import java.util.List;
import tapestry.projecttracker.entities.Log;
import tapestry.projecttracker.entities.Member;
import tapestry.projecttracker.entities.Project;

/**
 *
 * @author dejan
 */
public interface ProjectDAO {

    public void addProject(Project project);

    public Project updateProject(Project project);

    public void deleteProject(Integer id);

    public List<Project> getAllProjects();

    public Project getProjectById(Integer id);

    public Project getProjectByTitle(String projectTitle);
    
    public boolean checkIfProjectExists(String projectTitle);

    public Project removeAssignedFromProject(Project project);

    public double getProjectLoggedTime(Project project);

    public List<Log> getLogsByProject(Project project);
    
    public void addLog(Log log);

    public List<Log> getAllLogs();

    public Log getLogById(Integer id);

    public void deleteLog(Integer id);

}
