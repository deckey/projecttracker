/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.projecttracker.pages.view;

import java.util.List;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import tapestry.projecttracker.entities.Member;
import tapestry.projecttracker.pages.Index;

/**
 *
 * @author dejan
 */
public class ViewMembers {

    @Property
    private List<Member> members;

    @Property
    private Member member;

    @Inject
    private Session dbs;

    @SessionState
    @Property
    private Member loggedInMember;

    public boolean getLoggedInRole() {
        return (loggedInMember.getMemberRole().name() == "Administrator") ? true : false;
    }

    void onActivate() {
        members = dbs.createCriteria(Member.class).list();
    }
}
