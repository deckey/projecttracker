/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.projecttracker.entities;

import tapestry.projecttracker.prop.MemberStatus;
import tapestry.projecttracker.prop.MemberSpecialty;
import tapestry.projecttracker.prop.MemberRole;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author dejan
 */
@Entity
@Table(name = "tbl_member")
public class Member implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "memberId")
    private Integer memberId;

    @Column(name = "memberName")
    @Validate("required")
    private String memberName;

    @Column(name = "memberRole")
    @Validate("required")
    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;

    @Column(name = "memberSpecialty")
    @Validate("required")
    @Enumerated(EnumType.STRING)
    private MemberSpecialty memberSpecialty;

    @Column(name = "memberStatus")
    @Validate("required")
    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;

    //Access parameters:
    @Column(name = "memberUsername")
    @Validate("required")
    private String memberUsername;

    @Column(name = "memberPassword")
    @Validate("required")
    private String memberPassword;

    @Inject
    public Member() {
    }

    public Member(String memberName, MemberRole memberRole, MemberSpecialty memberSpecialty, MemberStatus memberStatus, String memberUsername, String memberPassword) {
        this.memberName = memberName;
        this.memberRole = memberRole;
        this.memberSpecialty = memberSpecialty;
        this.memberStatus = memberStatus;
        this.memberUsername = memberUsername;
        this.memberPassword = memberPassword;
    }
    

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public MemberRole getMemberRole() {
        return memberRole;
    }

    public void setMemberRole(MemberRole memberRole) {
        this.memberRole = memberRole;
    }

    public MemberSpecialty getMemberSpecialty() {
        return memberSpecialty;
    }

    public void setMemberSpecialty(MemberSpecialty memberSpecialty) {
        this.memberSpecialty = memberSpecialty;
    }

    public MemberStatus getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(MemberStatus memberStatus) {
        this.memberStatus = memberStatus;
    }

    public String getMemberUsername() {
        return memberUsername;
    }

    public void setMemberUsername(String memberUsername) {
        this.memberUsername = memberUsername;
    }

    public String getMemberPassword() {
        return memberPassword;
    }

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }
}
