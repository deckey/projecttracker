
package tapestry.projecttracker.pages.create;

import java.util.List;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import tapestry.projecttracker.data.MemberDAO;
import tapestry.projecttracker.entities.Member;


public class CreateMember {
    
    @Inject
    private MemberDAO memberDao;
    
    @Property
    private Member member;
    
    @Property
    private List<Member> members;
    
    
}
