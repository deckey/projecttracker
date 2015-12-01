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
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import tapestry.projecttracker.data.MemberDAO;

/**
 *
 * @author dejan
 */
@Import(stylesheet = {
    "context:css/bootstrap-custom.css",
    "context:css/main.css"})
public class Header {

    @Inject
    private MemberDAO memberDao;
    @Inject
    private ComponentResources resources;

    @Property
    private String pageName;

    public String getClassForPageName() {
        return resources.getPageName().equalsIgnoreCase(pageName)
                ? "active"
                : null;
    }

    public List<String> getPageNames() {
        List<String> pageNames = new ArrayList<>(Arrays.asList("ViewDashboard", "ViewProjects", "ViewMembers"));
        return pageNames;
    }

    public String getPageLabel() {
        List<String> pageNames = getPageNames();
        String[] pageLabels = {"Overview", "Projects", "Members"};
        return pageLabels[pageNames.indexOf(pageName)];
    }
}
