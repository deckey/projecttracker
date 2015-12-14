/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.projecttracker.components;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

/**
 *
 * @author Dejan Ivanovic
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
