/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.projecttracker.data;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.runtime.ComponentEventException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import tapestry.projecttracker.entities.Member;

/**
 *
 * @author dejan
 */
public class MemberIMPL implements MemberDAO {

    @Inject
    private Session dbs;

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
    public boolean validateMember(String uName, String pWord) {
        System.out.println("VALIDATING MEMBER: " + uName + " : " + pWord);
        Member member = getMemberByUsername(uName);
        if (member == null) {
            return false;
        } else {
            if (member.getMemberUsername().equals(uName)) {
                if (member.getMemberPassword().equals(pWord)) {
                    return true;
                }
            }
        }
        return false;
    }
}
