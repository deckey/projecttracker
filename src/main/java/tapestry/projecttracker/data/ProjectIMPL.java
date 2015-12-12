/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.projecttracker.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import tapestry.projecttracker.entities.Log;
import tapestry.projecttracker.entities.Project;

/**
 *
 * @author dejan
 */
public class ProjectIMPL implements ProjectDAO {

    @Inject
    private Session dbs;
    
    @Inject
    private MemberDAO memberDao;

    @Override
    public void addProject(Project project) {
        dbs.persist(project);
    }

    @Override
    public Project updateProject(Project project) {
        return (Project) dbs.merge(project);
    }

    @Override
    public List<Project> getAllProjects() {
        return dbs.createCriteria(Project.class).list();
    }

    @Override
    public Project getProjectById(Integer id) {
        return (Project) dbs.createCriteria(Project.class)
                .add(Restrictions.eq("projectId", id))
                .uniqueResult();
    }

    @Override
    public Project getProjectByTitle(String projectTitle) {
        return (Project) dbs.createCriteria(Project.class)
                .add(Restrictions.eq("projectTitle", projectTitle))
                .uniqueResult();
    }

    @Override
    public boolean checkIfProjectExists(String projectTitle) {
        Long rows = (Long) dbs.createCriteria(Project.class)
                .add(Restrictions.eq("projectTitle", projectTitle))
                .setProjection(Projections.rowCount()).uniqueResult();
        return rows != 0;
    }

    @Override
    public List<Log> getLogsByProject(Project project) {
        List<Log> logs = new ArrayList<>();
        Integer projectId = project.getProjectId();
        logs = dbs.createCriteria(Log.class).add(Restrictions.eq("logProjectId", projectId)).list();
        if (logs == null) {
            return null;
        }
        return logs;
    }

    @Override
    public double getProjectLoggedTime(Project project) {
        List<Log> logs = getLogsByProject(project);
        double logTimeSum = 0;
        for (Log entry : logs) {
            logTimeSum += entry.getLogTime();
        }
        return logTimeSum;
    }

    @Override
    public void addLog(Log log) {
        log.setLogAdded(new Date());
        log.setLogMemberName(memberDao.getMemberById(log.getLogMemberId()).getMemberName());
        dbs.persist(log);
        System.out.println("PROJECT DAO...NEW LOG CREATED...");
    }

    @Override
    public List<Log> getAllLogs() {
        return dbs.createCriteria(Log.class).list();
    }

    @Override
    public void deleteLog(Integer id) {
        Log log = (Log) dbs.createCriteria(Log.class).add(Restrictions.eq("logId", id)).uniqueResult();
        Project project = getProjectById(log.getLogProjectId());
        project.setProjectTime(project.getProjectTime()-log.getLogTime());
        dbs.delete(log);
    }
}
