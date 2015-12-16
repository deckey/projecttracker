/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.projecttracker.entities;

import java.util.HashSet;
import tapestry.projecttracker.prop.MemberStatus;
import tapestry.projecttracker.prop.MemberSpecialty;
import tapestry.projecttracker.prop.MemberRole;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
@Entity
@Table(name = "tbl_member")
public class Member implements java.io.Serializable, Comparable<Member> {

    private Integer memberId;
    private String memberName;
    private String memberUsername;
    private String memberPassword;
    private MemberRole memberRole;
    private MemberSpecialty memberSpecialty;
    private MemberStatus memberStatus;
    private double memberTotalHours;
    private Set<Project> assignedProjects = new HashSet<>();

    /**
     * Empty constructor
     */
    @Inject
    public Member() {
    }

    /**
     * Constructor by member full name
     * @param memberName Full name of the member
     */
    public Member(String memberName) {
        this.memberName = memberName;
    }

    /**
     * Constructor with name and assigned projects
     * @param memberName Full name of the member
     * @param assignedProjects Set of projects to assign to new member
     */
    public Member(String memberName, Set<Project> assignedProjects) {
        this.memberName = memberName;
        this.assignedProjects = assignedProjects;
    }

    /**
     * Default constructor 
     * @param memberName Full name of the member
     * @param memberUsername Username for the member
     * @param memberPassword Password for the member
     * @param memberRole Role (Administrator, Member, Supervisor)
     * @param memberSpecialty Specialty type (Animation, Lighting...)
     * @param memberStatus Active or Inactive status
     */
    public Member(String memberName, String memberUsername, String memberPassword, MemberRole memberRole, MemberSpecialty memberSpecialty, MemberStatus memberStatus) {
        this.memberName = memberName;
        this.memberUsername = memberUsername;
        this.memberPassword = memberPassword;
        this.memberRole = memberRole;
        this.memberSpecialty = memberSpecialty;
        this.memberStatus = memberStatus;
        this.memberTotalHours = 0;
    }

    /**
     *
     * @return
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "memberId")
    public Integer getMemberId() {
        return memberId;
    }

    /**
     *
     * @param memberId
     */
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    /**
     *
     * @return
     */
    @Column(name = "memberName")
    public String getMemberName() {
        return memberName;
    }

    /**
     *
     * @param memberName
     */
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    /**
     *
     * @return
     */
    @Column(name = "memberUsername")
    public String getMemberUsername() {
        return memberUsername;
    }

    /**
     *
     * @param memberUsername
     */
    public void setMemberUsername(String memberUsername) {
        this.memberUsername = memberUsername;
    }

    /**
     *
     * @return
     */
    @Column(name = "memberPassword")
    public String getMemberPassword() {
        return memberPassword;
    }

    /**
     *
     * @param memberPassword
     */
    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }

    /**
     *
     * @return
     */
    @Column(name = "memberRole")
    @Enumerated(EnumType.STRING)
    public MemberRole getMemberRole() {
        return memberRole;
    }

    /**
     *
     * @param memberRole
     */
    public void setMemberRole(MemberRole memberRole) {
        this.memberRole = memberRole;
    }

    /**
     *
     * @return
     */
    @Column(name = "memberSpecialty")
    @Enumerated(EnumType.STRING)
    public MemberSpecialty getMemberSpecialty() {
        return memberSpecialty;
    }

    /**
     *
     * @param memberSpecialty
     */
    public void setMemberSpecialty(MemberSpecialty memberSpecialty) {
        this.memberSpecialty = memberSpecialty;
    }

    /**
     *
     * @return
     */
    @Column(name = "memberStatus")
    @Enumerated(EnumType.STRING)
    public MemberStatus getMemberStatus() {
        return memberStatus;
    }

    /**
     *
     * @param memberStatus
     */
    public void setMemberStatus(MemberStatus memberStatus) {
        this.memberStatus = memberStatus;
    }

    /**
     *
     * @return
     */
    @Column(name = "memberTotalHours")
    public double getMemberTotalHours() {
        return memberTotalHours;
    }

    /**
     *
     * @param memberTotalHours
     */
    public void setMemberTotalHours(double memberTotalHours) {
        this.memberTotalHours = memberTotalHours;
    }

    /**
     *
     * @return
     */
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "assignedMembers")
    public Set<Project> getAssignedProjects() {
        return this.assignedProjects;
    }

    /**
     *
     * @param assignedProjects
     */
    public void setAssignedProjects(Set<Project> assignedProjects) {
        this.assignedProjects = assignedProjects;
    }

    @Override
    public String toString() {
        return this.getMemberName(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int compareTo(Member m1) {
        return this.getMemberName().compareTo(m1.getMemberName());
    }

}
