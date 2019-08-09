package com.liferay.code.samples.portal.modules.applications.portlets.configurable_portlet.configuration;

import com.liferay.portal.kernel.settings.definition.ConfigurationBeanDeclaration;
import org.osgi.service.component.annotations.Component;

/**
 * This class declares a wrapper over the Configuration interface and makes it available as a component
 * within the OSGi container.
 *
 * The <code>ConfigurationBeanDeclaration</code> allows to access configuration values in any of the scopes.
 */
@Component
public class TaskListConfigurationBeanDeclaration implements ConfigurationBeanDeclaration {
    @Override
    public Class<?> getConfigurationBeanClass() {
        return PersonalTaskPortletConfiguration.class;
    }
}
