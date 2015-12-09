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
 * @author dejan
 */
@Entity
@Table(name = "tbl_member")
public class Member implements java.io.Serializable {

    private Integer memberId;
    private String memberName;
    private String memberUsername;
    private String memberPassword;
    private MemberRole memberRole;
    private MemberSpecialty memberSpecialty;
    private MemberStatus memberStatus;
    private double memberTotalHours;
    private Set<Project> assignedProjects = new HashSet<>();

    @Inject
    public Member() {
    }

    public Member(String memberName) {
        this.memberName = memberName;
    }

    public Member(String memberName, Set<Project> assignedProjects) {
        this.memberName = memberName;
        this.assignedProjects = assignedProjects;
    }

    public Member(String memberName, String memberUsername, String memberPassword, MemberRole memberRole, MemberSpecialty memberSpecialty, MemberStatus memberStatus) {
        this.memberName = memberName;
        this.memberUsername = memberUsername;
        this.memberPassword = memberPassword;
        this.memberRole = memberRole;
        this.memberSpecialty = memberSpecialty;
        this.memberStatus = memberStatus;
        this.memberTotalHours = 0;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "memberId")
    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    @Column(name = "memberName")
    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    @Column(name = "memberUsername")
    public String getMemberUsername() {
        return memberUsername;
    }

    public void setMemberUsername(String memberUsername) {
        this.memberUsername = memberUsername;
    }

    @Column(name = "memberPassword")
    public String getMemberPassword() {
        return memberPassword;
    }

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }

    @Column(name = "memberRole")
    @Enumerated(EnumType.STRING)
    public MemberRole getMemberRole() {
        return memberRole;
    }

    public void setMemberRole(MemberRole memberRole) {
        this.memberRole = memberRole;
    }

    @Column(name = "memberSpecialty")
    @Enumerated(EnumType.STRING)
    public MemberSpecialty getMemberSpecialty() {
        return memberSpecialty;
    }

    public void setMemberSpecialty(MemberSpecialty memberSpecialty) {
        this.memberSpecialty = memberSpecialty;
    }

    @Column(name = "memberStatus")
    @Enumerated(EnumType.STRING)
    public MemberStatus getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(MemberStatus memberStatus) {
        this.memberStatus = memberStatus;
    }

    @Column(name = "memberTotalHours")
    public double getMemberTotalHours() {
        return memberTotalHours;
    }

    public void setMemberTotalHours(double memberTotalHours) {
        this.memberTotalHours = memberTotalHours;
    }

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "assignedMembers")

    public Set<Project> getAssignedProjects() {
        return this.assignedProjects;
    }

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

}
