/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.projecttracker.encoders;

import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ValueEncoderFactory;
import tapestry.projecttracker.data.MemberDAO;
import tapestry.projecttracker.entities.Member;

/**
 *
 * @author dejan
 */
public class MemberEncoder implements ValueEncoder<Member>{

    @Inject
    private MemberDAO memberDao;

    public MemberEncoder(MemberDAO memberDao) {
        this.memberDao = memberDao;
    }
    
    @Override
    public String toClient(Member member) {
        // return the given object's username
        return String.valueOf(member.getMemberId());
    }

    @Override
    public Member toValue(String id) {
        // find the member object of the given ID in the database
        return memberDao.getMemberById(Integer.parseInt(id));
    }
}
