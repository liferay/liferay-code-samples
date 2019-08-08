package com.liferay.code.samples.portal.modules.applications.portlets.configurable_portlet.portlet;

import com.liferay.code.samples.portal.modules.applications.portlets.configurable_portlet.service.TaskListService;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.IOException;

/**
 * Simple portlet that renders a list of Personal Tasks
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

	public void render(RenderRequest request, RenderResponse response) throws IOException, PortletException {
		request.setAttribute(TASK_LIST_ATTRIBUTE, taskListService.loadPersonalTasks());
		super.render(request, response);
	}

	@Reference
	private TaskListService taskListService;
}