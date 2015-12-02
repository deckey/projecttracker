
package tapestry.projecttracker.data;

import java.util.List;
import tapestry.projecttracker.entities.Member;


public interface MemberDAO {
    
    public Member addMember(Member member);
    
    public List<Member> getAllMembers();

    public Member getMemberById(Integer id);

    public Member getMemberByUsername(String username);

    public Member validateMember(String uName, String pWord);
    
    public boolean checkIfMemberExists(String uName);
}
