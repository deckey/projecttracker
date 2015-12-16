package tapestry.projecttracker.data;

import java.util.List;
import java.util.Set;
import tapestry.projecttracker.entities.Log;
import tapestry.projecttracker.entities.Member;
import tapestry.projecttracker.entities.Project;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public interface MemberDAO {

    /**
     * Adds a new Member instance to DB
     * @param member Member entity instance
     */
    public void addMember(Member member);

    /**
     * Update (merge) entity to DB
     * @param member Member entity instance
     * @return Member that was stored in DB
     */
    public Member updateMember(Member member);

    /**
     * Delete Member instance from DB by it's Id
     * @param id Unique member's id
     */
    public void deleteMember(Integer id);

    /**
     * Get all members from DB
     * @return List of Member instances from DB
     */
    public List<Member> getAllMembers();

    /**
     * Get member by it's id
     * @param id Unique member's id
     * @return Member that was found in DB
     */
    public Member getMemberById(Integer id);

    /**
     * Get member from DB by it's username
     * @param username String as unique username
     * @return Member that was found in DB
     */
    public Member getMemberByUsername(String username);

    /**
     * Check if Member exists in DB with username and password
     * @param uName String to check as username
     * @param pWord String to check as password
     * @return Member that was found in DB
     */
    public Member validateMember(String uName, String pWord);

    
    /**
     * Remove assigned projects from specified member
     * @param member Member instance
     * @return Member with removed assigned projects
     */
    public Member removeAssignedProjects(Member member);
    
    /**
     * Get all logs created by specified member
     * @param member Member instance
     * @return List of logs made by member
     */
    public List<Log> getLogsByMember(Member member);
    
    /**
     * Delete all logs from specified log list
     * @param logList List of logs to delete
     */
    public void deleteMemberLogs(List<Log> logList);
}
