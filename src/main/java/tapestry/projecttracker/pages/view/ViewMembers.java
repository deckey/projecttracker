/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.projecttracker.pages.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;
import tapestry.projecttracker.data.MemberDAO;
import tapestry.projecttracker.entities.Member;
import tapestry.projecttracker.entities.Project;
import tapestry.projecttracker.prop.MemberStatus;
import tapestry.projecttracker.prop.ProjectStatus;

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
    private MemberDAO memberDao;

    @SessionState
    @Property
    private Member loggedInMember;

    /* GRID EDITS */
    @Property
    private BeanModel<Member> activeMembersGridModel;
    @Property
    private BeanModel<Member> allMembersGridModel;
    @Inject
    private BeanModelSource beanModelSource;
    @Inject
    private Messages messages;

    public boolean getLoggedInRole() {
        return (loggedInMember.getMemberRole().name() == "Administrator") ? true : false;
    }

    public List<Member> getActiveMembers() {
        // SORT MEMBERS BY TOTAL HOURS AND DISPLAY ONLY TOP 5
        List<Member> recentMembers;
        List<Member> allMembers = memberDao.getAllMembers();
        Collections.sort(allMembers, new Comparator<Member>() {
            public int compare(Member m1, Member m2) {
                return m2.getMemberTotalHours() > m1.getMemberTotalHours()? 1 : -1;
            }
        });
        Iterator it = allMembers.iterator();
        while (it.hasNext()) {
            Member activeMember = (Member) it.next();
            if (activeMember.getMemberStatus() != MemberStatus.Active) {
                it.remove();
            }
        }
        if (allMembers.size() < 5) {
            recentMembers = allMembers.subList(0, allMembers.size());
        } else {
            recentMembers = allMembers.subList(0, 5);
        }

        return recentMembers;
    }

    void setupRender() {
        activeMembersGridModel = beanModelSource.createDisplayModel(Member.class, messages);
        activeMembersGridModel.include("memberName", "memberUsername", "MemberRole", "MemberSpecialty", "MemberTotalHours");
        activeMembersGridModel.get("memberName").label("Team member");
        activeMembersGridModel.get("memberUsername").label("Username");
        activeMembersGridModel.get("memberUsername").sortable(false);
        activeMembersGridModel.get("memberRole").label("Role");
        activeMembersGridModel.get("memberRole").sortable(false);
        activeMembersGridModel.get("memberSpecialty").label("Specialty");
        activeMembersGridModel.get("memberSpecialty").sortable(false);
        activeMembersGridModel.get("memberTotalHours").label("Hours logged");

        allMembersGridModel = beanModelSource.createDisplayModel(Member.class, messages);
        allMembersGridModel.include("memberName", "memberUsername", "MemberRole", "MemberSpecialty", "MemberStatus");
        allMembersGridModel.get("memberName").label("Team member");
        allMembersGridModel.get("memberUsername").label("Username");
        allMembersGridModel.get("memberUsername").sortable(false);
        allMembersGridModel.get("memberRole").label("Role");
        allMembersGridModel.get("memberRole").sortable(false);
        allMembersGridModel.get("memberSpecialty").label("Specialty");
        allMembersGridModel.get("memberSpecialty").sortable(false);
        allMembersGridModel.get("memberStatus").label("Current status");
    }

    void onActivate() {
        members = memberDao.getAllMembers();
    }
}
