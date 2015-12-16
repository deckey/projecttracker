
package tapestry.projecttracker.encoders;

import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ValueEncoderFactory;
import tapestry.projecttracker.data.MemberDAO;
import tapestry.projecttracker.entities.Member;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class MemberEncoder implements ValueEncoder<Member>{

    @Inject
    private MemberDAO memberDao;

    /**
     * Constructor
     * @param memberDao default constructor parameter
     */
    public MemberEncoder(MemberDAO memberDao) {
        this.memberDao = memberDao;
    }
    
    /**
     * Override of toClient() method
     * @param member Member instance
     * @return String representation of the member
     */
    @Override
    public String toClient(Member member) {
        // return the given object's username
        return String.valueOf(member.getMemberId());
    }

    /**
     * Override of toValue() method
     * @param id Member unique id as string
     * @return Member instance found in DB
     */
    @Override
    public Member toValue(String id) {
        // find the member object of the given ID in the database
        return memberDao.getMemberById(Integer.parseInt(id));
    }
}
