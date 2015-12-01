/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.projecttracker.pages;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.SessionState;
import tapestry.projecttracker.entities.Member;

/**
 *
 * @author dejan
 */
public class ViewDashboard {

    @SessionState
    private Member loggedIn;

    @InjectPage
    private Index indexPage;

    Object onActivate() {
        if (loggedIn.getMemberUsername() == "") {
            return Index.class;
        }
        return null;
    }
}
