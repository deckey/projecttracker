/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.projecttracker.pages;

import java.util.List;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import tapestry.projecttracker.entities.Member;

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

    void onActivate() {
        members = dbs.createCriteria(Member.class).list();
    }
}
