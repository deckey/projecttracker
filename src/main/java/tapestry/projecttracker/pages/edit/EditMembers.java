package tapestry.projecttracker.pages.edit;

import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import tapestry.projecttracker.data.MemberDAO;
import tapestry.projecttracker.entities.Member;
//
//@ProtectedPage
//@RolesAllowed(value = {"Administrator"})

public class EditMembers {

    @Inject
    private MemberDAO memberDao;

    @Persist
    @Property
    private Member member;

    @Property
    private Member rowMember;

    @Property
    private List<Member> members;

    void onActivate() {
        if (members == null) {
            members = new ArrayList<>();
        }
        members = memberDao.getAllMembers();
    }

    @CommitAfter
    void onSuccessFromAddMemberForm() {
        memberDao.addMember(rowMember);
    }

    @CommitAfter
    void onDelete(Integer id) {
        memberDao.deleteMember(id);
    }

    @CommitAfter
    Object onEdit(Member aMember) {
        this.member = aMember;
        System.out.println("MEMBER ARGUMENT IS: " + member.getMemberName());
        System.out.println("THIS MEMBER IS: " + this.member.getMemberName());
        System.out.println("ROW MEMBER IS: " + rowMember.getMemberName());
        return this;
//        this.onActivate(member);
    }

//
//    @CommitAfter
//    Object onActionFromEdit(Member aMember) {
//        this.member = aMember;
//        return this;
//    }
}
