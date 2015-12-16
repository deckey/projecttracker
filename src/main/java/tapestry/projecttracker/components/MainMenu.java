package tapestry.projecttracker.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;
import tapestry.projecttracker.entities.Member;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class MainMenu {

    @Inject
    private ComponentResources resources;

    @Property
    private String pageName;

    @SessionState
    @Property
    private Member loggedInMember;

    /**
     * Check if logged in member is Administrator
     * 
     * @return Boolean - Admin or not
     */
    public boolean getLoggedInRole() {
        return (loggedInMember.getMemberRole().name() == "Administrator") ? true : false;
    }

    /**
     * Get name of the active page
     *
     * @return String of active page name
     */
    public String getClassForPageName() {
        return resources.getPageName().equalsIgnoreCase(pageName)
                ? "active"
                : null;
    }

    /**
     * Get list of all pages for main menu
     *
     * @return String list of page names
     */
    public List<String> getPageNames() {
        List<String> pageNames = new ArrayList<>(Arrays.asList("View/Dashboard", "View/Projects", "View/Members"));
        return pageNames;
    }

    /**
     * Get labels for main menu links
     *
     * @return String list of page labels
     */
    public String getPageLabel() {
        List<String> pageNames = getPageNames();
        String[] pageLabels = {"Overview", "Projects", "Members"};
        // override each menu page name with label
        return pageLabels[pageNames.indexOf(pageName)];
    }

    /**
     * Get tooltips for main menu links
     *
     * @return String for a link tooltip
     */
    public String getPageTooltip() {
        List<String> pageNames = getPageNames();
        String[] pageTooltips = {"View dashboard", "View project list", "View members"};
        // override each menu link tooltip with custom tooltips
        return pageTooltips[pageNames.indexOf(pageName)];
    }

    /**
     * Get link title for creation link icons
     * 
     * @return String as name of the link
     */
    public String getLinkTitle() {
        String linkTitle = "";
        String currentPage = resources.getPageName();
        if (currentPage.equals("view/Projects")) {
            linkTitle = "create/CreateProject";
        }
        if (currentPage.equals("view/Members")) {
            linkTitle = "create/CreateMember";
        }
        return linkTitle;
    }

    /**
     * Get links for creation icons
     *
     * @return boolean if icons should be created
     */
    public boolean getCreationLink() {
        // decide if the creation button icon should be placed
        // case: Admin or Supervisor on a ViewProjects page
        if ((loggedInMember.getMemberRole().name() == "Administrator" || loggedInMember.getMemberRole().name() == "Supervisor")
                && resources.getPageName().equals("view/Projects")) {
            return true;
        }
        // case: Admin on a ViewProjects or ViewMembers page
        if ((loggedInMember.getMemberRole().name() == "Administrator")
                && (resources.getPageName().equals("view/Projects") || resources.getPageName().equals("view/Members"))) {
            return true;
        }
        return false;
    }

    /**
     * Get image paths for creation icons
     *
     * @return String of image icon path
     */
    public String getLinkIcon() {
        if (this.getLinkTitle() == "create/CreateProject") {
            return "../images/glyphicons/project.png";
        }
        return "../images/glyphicons/member.png";
    }

    /**
     * Get tooltips for creation icons
     *
     * @return String for a tooltip
     */
    public String getTooltip() {
        // only 2 options, so if tooltip is not for the project, it must be for the member
        if (this.getLinkTitle() == "create/CreateProject") {
            return "Create new project";
        }
        return "Add new member";
    }
}
