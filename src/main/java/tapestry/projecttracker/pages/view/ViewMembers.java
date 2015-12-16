
package tapestry.projecttracker.pages.view;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.alerts.Duration;
import org.apache.tapestry5.alerts.Severity;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;
import tapestry.projecttracker.data.MemberDAO;
import tapestry.projecttracker.entities.Member;
import tapestry.projecttracker.prop.MemberStatus;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class ViewMembers {
    
    /* Properties */ 
    @Property
    private BeanModel<Member> activeMembersGridModel;
    
    @Property
    private BeanModel<Member> allMembersGridModel;
    
    @SessionState
    @Property
    private Member loggedInMember;
    
    @Property
    private Member member;
    
    @Property
    private List<Member> members;

    /* Services */
    @Inject
    private AlertManager alertManager;

    @Inject
    private BeanModelSource beanModelSource;
    
    @Inject
    private MemberDAO memberDao;

    @Inject
    private Messages messages;

    /**
     * Check if logged in user is Administrator 
     * @return True if user is Admin
     */
    public boolean getLoggedInRole() {
        return (loggedInMember.getMemberRole().name() == "Administrator") ? true : false;
    }

    /**
     * Get list of active members, sort members by total hours and return top 5
     * @return List of members with most hours logged
     */
    public List<Member> getActiveMembers() {
        List<Member> recentMembers; 
        List<Member> allMembers = memberDao.getAllMembers();
        
        // sort by comparing number of hours logged
        Collections.sort(allMembers, new Comparator<Member>() {
            public int compare(Member m1, Member m2) {
                return m2.getMemberTotalHours() > m1.getMemberTotalHours()? 1 : -1;
            }
        });
        
        // if member is not active remove it from the list
        Iterator it = allMembers.iterator();
        while (it.hasNext()) {
            Member activeMember = (Member) it.next();
            if (activeMember.getMemberStatus() != MemberStatus.Active) {
                it.remove();
            }
        }
        
        // if number of members is less than 5, get all of them
        if (allMembers.size() < 5) {
            recentMembers = allMembers.subList(0, allMembers.size());
        } else {
            // get top five members
            recentMembers = allMembers.subList(0, 5);
        }
        return recentMembers;
    }
    
    /* Page render */ 
    void setupRender() {
        // grid models setup - active members grid
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

        // grid models setup - all members grid
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
