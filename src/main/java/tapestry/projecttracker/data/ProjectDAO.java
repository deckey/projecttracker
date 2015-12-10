/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.projecttracker.data;

import java.util.List;
import tapestry.projecttracker.entities.Member;
import tapestry.projecttracker.entities.Project;

/**
 *
 * @author dejan
 */
public interface ProjectDAO {
    
    public void addProject(Project project);
    
    public Project updateProject(Project project);

    public List<Project> getAllProjects();

    public Project getProjectById(Integer id);
    
    public Project getProjectByTitle(String projectTitle);
    
    public boolean checkIfProjectExists(String projectTitle);

}
