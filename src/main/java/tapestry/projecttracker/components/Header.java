//TODO: Get user persistant to all pages and displayed in header bar
//TODO: Mockup secondary pages for adding and removing projects/members
//TODO: Setup access levels for Admin and Member

/* 
Header class combining components for member details and logo
This class holds main menu navigation
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

public class Header {

    @Inject
    private MemberDAO memberDao;
    
    @Inject
    private ComponentResources resources;
    
    @Property
    private String pageName;
    
    @SessionState
    @Property
    private Member loggedInMember;

    Object onGoHome(){
        return Index.class;
    }
    public String getUser(){
        return loggedInMember.getMemberUsername();
    }
}
