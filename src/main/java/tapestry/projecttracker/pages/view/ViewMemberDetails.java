/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.projecttracker.pages.view;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import tapestry.projecttracker.data.MemberDAO;
import tapestry.projecttracker.entities.Member;

/**
 *
 * @author dejan
 */
public class ViewMemberDetails {

    @Property
    private Member member;
    
    @Inject
    private MemberDAO memberDao;
    
    void onActivate(Member member){
        this.member=member;
    }
    Member onPassivate(){
        return this.member;
    }
}
