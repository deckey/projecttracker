/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author Dejan Ivanovic
 */
public class MainMenu {

    @Inject
    private ComponentResources resources;

    @Property
    private String pageName;

    @SessionState
    @Property
    private Member loggedInMember;

    public boolean getLoggedInRole() {
        return (loggedInMember.getMemberRole().name() == "Administrator") ? true : false;
    }

    public String getClassForPageName() {
        return resources.getPageName().equalsIgnoreCase(pageName)
                ? "active"
                : null;
    }

    public List<String> getPageNames() {
        List<String> pageNames = new ArrayList<>(Arrays.asList("View/Dashboard", "View/Projects", "View/Members"));
        return pageNames;
    }

    public String getPageLabel() {
        List<String> pageNames = getPageNames();
        String[] pageLabels = {"Overview", "Projects", "Members"};
        return pageLabels[pageNames.indexOf(pageName)];
    }

    public String getLinkTitle() {
        String linkTitle = "";
        String currentPage = resources.getPageName();
        System.out.println("CURRENT PAGE: " + currentPage);
        if (currentPage.equals("view/Projects")) {
            linkTitle = "create/CreateProject";

        }
        if (currentPage.equals("view/Members")) {
            linkTitle = "create/CreateMember";
        }
        return linkTitle;
    }

    public boolean getCreationLink() {
        if (loggedInMember.getMemberRole().name() == "Administrator"
                && (resources.getPageName().equals("view/Members") || resources.getPageName().equals("view/Projects"))) {
            return true;
        }
        return false;
    }

    public String getLinkIcon() {
        if (this.getLinkTitle() == "create/CreateProject") {
            return "../images/glyphicons/project.png";
        }
        return "../images/glyphicons/member.png";
    }
}
