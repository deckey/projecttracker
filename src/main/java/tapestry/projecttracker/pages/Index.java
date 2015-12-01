package tapestry.projecttracker.pages;

import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import tapestry.projecttracker.entities.Member;

/**
 * Start page of application ProjectTracker.
 */
@Import(stylesheet = {
    "context:css/bootstrap-custom.css",
    "context:css/main.css"})
public class Index {

//    TEMPORARY to ADD members
    @Property
    private Member member;

    @Inject
    private Session dbs;

//    Object onActivate() {
//        return ViewDashboard.class;
//    }
//    @CommitAfter
//    Object onSuccessFromAddMemberForm() {
//        dbs.persist(member);
//        return ViewMembers.class;
//    }

}
