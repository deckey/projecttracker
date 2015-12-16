
/* 
Header class holding login form, log and logged in member info
 */
package tapestry.projecttracker.components;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;
import tapestry.projecttracker.data.MemberDAO;
import tapestry.projecttracker.entities.Member;
import tapestry.projecttracker.pages.Index;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class Header {

    @Inject
    private MemberDAO memberDao;

    @SessionState
    @Property
    private Member loggedInMember;

    @Inject
    private ComponentResources componentResources;

    Object onGoHome() {
        return Index.class;
    }

    /**
     * Returns logged in user's full name
     * @return Logged in user's name
     */
    public String getUser() {
        return loggedInMember.getMemberName();
    }

    /**
     * Checks if user is logged in
     * @return Boolean 
     */
    public boolean getLoggedIn() {
        return (loggedInMember.getMemberUsername() == null) ? true : false;
    }

    Object onLogout() {
        componentResources.discardPersistentFieldChanges();
        loggedInMember = null;
        return Index.class;
    }

}
