package com.liferay.code.samples.portal.modules.applications.portlets.configurable_portlet.configuration;

import aQute.bnd.annotation.metatype.Meta;

/**
 * This interface represents the attributes of the Portlet configuration in its (default) System Scope.
 *
 * Each of the methods defined in the interface will be automatically converted in one configuration property.
 *
 * The <code>@Meta.OCD</code> annotation registers this interface as a configuration with a specific id.
 * With this information, Liferay Portal will create automatically a UI with a form under Control Panel >
 * Configuration > System Settings > Third Party adding a field for each of the methods annotated with
 * <code>@Meta.AD</code>
 *
 * Both annotations are processed by the bndtools.
 */
@Meta.OCD(
        //ID must be the fully qualified class name
        id = "com.liferay.code.samples.portal.modules.applications.portlets.configuration_portlet.PersonalTaskConfiguration",
        //Adds info to support internazionalization on the configuration UI.
        localization = "content/Language", name = "personal-task-configuration.title"
)
public interface PersonalTaskPortletConfiguration {

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    // META.AD annotation configures the metadata for each field. These annotations are processed by the bndtools
    @Meta.AD(
            deflt = DEFAULT_DATE_FORMAT, //Default Value
            //Keys in @Meta.OCD.localization file to localize form labels (name, description, etc...)
            name="personal-task-configuration.date-format-pattern.name",
            description = "personal-task-configuration.date-format-pattern.description"
    )
    String dateFormat();

}
