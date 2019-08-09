package com.liferay.code.samples.portal.modules.applications.portlets.configurable_portlet.portlet;

import com.liferay.code.samples.portal.modules.applications.portlets.configurable_portlet.configuration.PersonalTaskPortletSystemConfiguration;
import com.liferay.code.samples.portal.modules.applications.portlets.configurable_portlet.service.TaskListService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.IOException;

/**
 * Simple portlet that renders a list of Personal Tasks, and reads some of the configuration values
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=ConfigurablePortlet"  ,
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + TaskListPortlet.RESOURCE_COMMAND_PORTLET_NAME,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class TaskListPortlet extends MVCPortlet {
	public static final String RESOURCE_COMMAND_PORTLET_NAME = "configurable_tasklist_mvc_portlet";
	public static final String TASK_LIST_ATTRIBUTE = "personal_task_list";
	public static final String DATE_FORMAT_PATTERN_ATTRIBUTTE = "personalTaskDateFormat";

	public void render(RenderRequest request, RenderResponse response) throws IOException, PortletException {

		try {
			PersonalTaskPortletSystemConfiguration config = configurationProvider.getSystemConfiguration(PersonalTaskPortletSystemConfiguration.class);
			request.setAttribute(DATE_FORMAT_PATTERN_ATTRIBUTTE, config.dateFormat());
			LOGGER.debug("Setting " + config.dateFormat() + " as date format pattern");
		} catch (ConfigurationException ex) {
			LOGGER.warn("Error reading configuration for TaskListPortlet. Using default configuration. Error : " + ex.getMessage());
			request.setAttribute(DATE_FORMAT_PATTERN_ATTRIBUTTE, PersonalTaskPortletSystemConfiguration.DEFAULT_DATE_FORMAT);
		}


		request.setAttribute(TASK_LIST_ATTRIBUTE, taskListService.loadPersonalTasks());
		super.render(request, response);
	}

	/**
	 * This allows to inject the Liferay's ConfigurationProvider which will allow to access the Configs in different
	 * scopes.
	 */
	@Reference
	public void setConfigurationProvider(ConfigurationProvider configurationProvider) {
		this.configurationProvider = configurationProvider;
	}

	private ConfigurationProvider configurationProvider;

	@Reference
	private TaskListService taskListService;

	private static final Log LOGGER = LogFactoryUtil.getLog(TaskListPortlet.class);
}