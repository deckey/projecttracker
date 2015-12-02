/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.projecttracker.prop;

/**
 *
 * @author dejan
 */
public enum MemberRole {
    /*  
    Admin - full control create / edit projects and members
    Supervisor - can create / edit projects
    Member - view projects/members, log time on a project
    */
    Administrator, Supervisor, Member;
}
