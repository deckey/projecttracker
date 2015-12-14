/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.projecttracker.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import tapestry.projecttracker.entities.Log;
import tapestry.projecttracker.entities.Member;
import tapestry.projecttracker.entities.Project;

/**
 *
 * @author dejan
 */
public class MemberIMPL implements MemberDAO {

    @Inject
    private Session dbs;
    
    @Inject
    private ProjectDAO projectDao;

    @Override
    public void addMember(Member member) {
        dbs.persist(member);
    }

    @Override
    public Member updateMember(Member member) {
        return (Member) dbs.merge(member);
    }

    @Override
    public void deleteMember(Integer id) {
        Member member = getMemberById(id);
        dbs.delete(member);
    }

    @Override
    public List<Member> getAllMembers() {
        List<Member> members = dbs.createCriteria(Member.class).list();
        Collections.sort(members);
        return members;
    }

    @Override
    public Member getMemberById(Integer id) {
        Member member = (Member) dbs.createCriteria(Member.class)
                .add(Restrictions.eq("memberId", id))
                .uniqueResult();
        return member;
    }

    @Override
    public Member getMemberByUsername(String username) {
        Member member = (Member) dbs.createCriteria(Member.class)
                .add(Restrictions.eq("memberUsername", username))
                .uniqueResult();
        return member;
    }

    @Override
    public Member validateMember(String uName, String pWord) {
        System.out.println("VALIDATING MEMBER: " + uName + " : " + pWord);
        Member member = getMemberByUsername(uName);
        if (member != null) {
            if (member.getMemberUsername().equals(uName)) {
                if (member.getMemberPassword().equals(pWord)) {
                    return member;
                }
            }
        }
        return null;
    }

    @Override
    public boolean checkIfMemberExists(String uName) {
        Member member = getMemberByUsername(uName);
        return (member == null) ? false : true;
    }

    @Override
    public Member removeAssignedProjects(Member member) {
        Set<Project> emptyList = new HashSet<>();
        member.setAssignedProjects(emptyList);
        return member;
    }

    @Override
    public List<Log> getLogsByMember(Member member) {
        List<Log> logs = new ArrayList<>();
        Integer memberId = member.getMemberId();
        logs = dbs.createCriteria(Log.class).add(Restrictions.eq("logMemberId", memberId)).list();
        if (logs == null) {
            return null;
        }
        return logs;
    }

    @Override
    public void deleteMemberLogs(List<Log> logList) {
        for(Log entry:logList){
            // REMOVE LOGGED HOURS FROM A PROJECT BEFORE DELETING MEMBER
            Project project = projectDao.getProjectById(entry.getLogProjectId());
            project.setProjectTime(project.getProjectTime()-entry.getLogTime());
            // REMOVE LOG 
            dbs.delete(entry);
        }
    }
    
    

}
