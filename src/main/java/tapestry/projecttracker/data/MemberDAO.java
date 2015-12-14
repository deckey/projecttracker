package tapestry.projecttracker.data;

import java.util.List;
import java.util.Set;
import tapestry.projecttracker.entities.Log;
import tapestry.projecttracker.entities.Member;
import tapestry.projecttracker.entities.Project;

public interface MemberDAO {

    public void addMember(Member member);

    public Member updateMember(Member member);

    public void deleteMember(Integer id);

    public List<Member> getAllMembers();

    public Member getMemberById(Integer id);

    public Member getMemberByUsername(String username);

    public Member validateMember(String uName, String pWord);

    public boolean checkIfMemberExists(String uName);
    
    public Member removeAssignedProjects(Member member);
    
    public List<Log> getLogsByMember(Member member);
    
    public void deleteMemberLogs(List<Log> logList);
}
