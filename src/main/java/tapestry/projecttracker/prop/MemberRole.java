package tapestry.projecttracker.prop;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public enum MemberRole {

    /**
     * Admin - full control create / edit projects and members
     */
    Administrator,
    /**
     * Supervisor - can create / edit projects
     */
    Supervisor,
    /**
     * Member - view projects/members, log time on a project
     */
    Member;
}
