package tapestry.projecttracker.pages.edit;

import java.util.List;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import tapestry.projecttracker.data.MemberDAO;
import tapestry.projecttracker.entities.Member;

public class EditMembers {

    @Property
    private List<Member> members;

    @Property
    private Member member;

    @Inject
    private MemberDAO memberDao;

    void onActivate() {
        members = memberDao.getAllMembers();
    }

    @CommitAfter
    void onSuccessFromAddMemberForm() {
        memberDao.addMember(member);

    }
}
