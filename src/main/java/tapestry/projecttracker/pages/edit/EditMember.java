/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.projecttracker.pages.edit;

import tapestry.projecttracker.pages.delete.DeleteProject;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import tapestry.projecttracker.data.MemberDAO;
import tapestry.projecttracker.data.ProjectDAO;
import tapestry.projecttracker.encoders.MemberEncoder;
import tapestry.projecttracker.entities.Member;
import tapestry.projecttracker.entities.Project;
import tapestry.projecttracker.pages.view.ViewMember;
import tapestry.projecttracker.pages.view.ViewProject;
import tapestry.projecttracker.prop.MemberRole;
import tapestry.projecttracker.prop.MemberSpecialty;
import tapestry.projecttracker.prop.MemberStatus;
import tapestry.projecttracker.prop.ProjectCategory;
import tapestry.projecttracker.prop.ProjectStatus;
import tapestry.projecttracker.services.ProtectedPage;

@ProtectedPage
@RolesAllowed(value = {"Administrator", "Supervisor", "Member"})
public class EditMember {

    @Inject
    private ProjectDAO projectDao;

    @Inject
    private MemberDAO memberDao;

    @Property
    private Project project;

    @InjectPage
    private ViewProject viewProjectPage;

    @InjectPage
    private ViewMember viewMemberPage;

    @InjectPage
    private DeleteProject deletePage;

    @Property
    private List<Project> projects;

    @Property
    private Member member;

    @Property
    private List<Member> members;

    @Property
    private List<Member> selectedMembers;

    @SessionState
    private Member loggedInMember;

    @Property
    private String memberPassword;
    
    @Property
    private String passwordFormat;

    @Property
    private String repeatPassword="";

    @Property
    private final MemberEncoder memberEncoder = new MemberEncoder(memberDao);

    @InjectComponent("memberEditForm")
    private Form form;

    @Property
    private MemberSpecialty memberSpecialty;

    @Property
    private MemberStatus memberStatus;

    public ProjectCategory[] getCategories() {
        ProjectCategory[] categories = ProjectCategory.values();
        return categories;
    }

    public MemberSpecialty[] getSpecialties() {
        MemberSpecialty[] specialties = MemberSpecialty.values();
        return specialties;
    }

    public MemberStatus[] getStatuses() {
        MemberStatus[] statuses = MemberStatus.values();
        return statuses;
    }

    public MemberRole[] getRoles() {
        MemberRole[] roles = MemberRole.values();
        return roles;
    }

    public String getStartDateFormat() {
        SimpleDateFormat formatter = new SimpleDateFormat("MMM d, yyyy");
        return formatter.format(project.getProjectStart());
    }

    public String getDueDateFormat() {
        SimpleDateFormat formatter = new SimpleDateFormat("MMM d, yyyy");
        return formatter.format(project.getProjectDue());
    }

    public String getCreationDateFormat() {
        SimpleDateFormat formatter = new SimpleDateFormat("MMM d, yyyy");
        return formatter.format(project.getProjectCreationDate());
    }

    void onPrepare() {
        members = memberDao.getAllMembers();
        projects = projectDao.getAllProjects();
    }

    public void set(Member member) {
        this.member = member;
    }

    Object onActivate(Member member) {
        this.member = member;
        passwordFormat = member.getMemberPassword().replaceAll(".", "*");
        if (loggedInMember.getMemberRole().equals(MemberRole.Administrator)) {
            return null;
        } else if (loggedInMember.getMemberId() == this.member.getMemberId()) {
            return null;
        } else {
            viewMemberPage.set(member);
            return viewMemberPage;
        }
    }

    Member onPassivate() {
        return member;
    }

    Object checkOwnerShip() {
        if (!getLoggedInRole()) {
            return this;
        } else {
            viewMemberPage.set(member);
            return viewMemberPage;
        }
    }

    public boolean getLoggedInRole() {
        if ((loggedInMember.getMemberRole().equals(MemberRole.Administrator))
                || (loggedInMember.getMemberId() == (this.member.getMemberId()))) {
            return true;
        }
        return false;
    }

    public boolean getUserAdmin() {
        return loggedInMember.getMemberRole().equals(MemberRole.Administrator);
    }

    public boolean checkPassword(String pass1, String pass2) {
        System.out.println("CHECKING PASSWORD...."+"string1:"+pass1+" : String2:"+pass2);
        return pass1.equals(pass2);
    }

    @CommitAfter
    Object onSuccessFromMemberEditForm() {
        if(!checkPassword(memberPassword, repeatPassword)){
            form.recordError("Passwords don't match!");
            return null;
        }
        memberDao.updateMember(member);
        viewMemberPage.set(member);
        return viewMemberPage;
    }
}
