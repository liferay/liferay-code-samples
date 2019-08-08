package com.liferay.code.samples.portal.modules.applications.portlets.configurable_portlet.configuration;

import aQute.bnd.annotation.metatype.Meta;

// This annotation register this interface as a configuration with a specific id.
// With this information, Liferay will create automatically the UI by default under
//     Control Panel > Configuration > System Settings > Third Party
@Meta.OCD(
        //ID must be the fully qualified class name
        id = "com.liferay.code.samples.portal.modules.applications.portlets.configuration_portlet.PersonalTaskConfiguration",
        //Adds info to support internazionalization on the configuration UI.
        localization = "content/Language", name = "personal-task-configuration"
)
public interface PersonalTaskPortletConfiguration {

    // META.AD annotation configures the metadata for each field. These annotations are processed by the bndtools
    @Meta.AD(
            deflt = "dd/MM/yyyy", //Default Value
            //Keys in @Meta.OCD.localization file to localize form labels (name, description, etc...)
            name="personal-task-configuration.date-format-pattern.name",
            description = "personal-task-configuration.date-format-pattern.description"
    )
    String dateFormat();

}
