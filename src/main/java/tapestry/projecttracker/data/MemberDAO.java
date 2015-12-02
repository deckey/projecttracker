
package tapestry.projecttracker.data;

import tapestry.projecttracker.entities.Member;


public interface MemberDAO {

    public Member getMemberById(Integer id);

    public Member getMemberByUsername(String username);

    public Member validateMember(String uName, String pWord);
    
    public boolean checkIfMemberExists(String uName);
}
