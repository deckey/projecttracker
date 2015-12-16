
package tapestry.projecttracker.components;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
@Import(stylesheet = {
    "context:css/normalize.css",
    "context:css/bootstrap-custom.css",
    "context:css/main.css"})
public class Layout {

    @Property
    @Parameter(defaultPrefix = BindingConstants.LITERAL)
    private String title;

}
