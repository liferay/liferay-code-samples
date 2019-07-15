package com.liferay.code.samples.portal.modules.applications.portlets.render_filter.portlet;

import com.liferay.code.samples.portal.modules.applications.portlets.render_filter.constants.MembersListPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

import org.osgi.service.component.annotations.Component;

import java.io.IOException;

/**
 * @author davidgomez
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=MembersList",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + MembersListPortletKeys.MEMBERSLIST_PORTLET_NAME,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class MembersListPortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		System.out.println("Portlet.doView");
		renderRequest.setAttribute("RENDER_PROCESS_TIME ", "1 ms");
		renderRequest.setAttribute("RENDER_TIME ", "1 ms");
		renderResponse.setProperty("RENDER_TIME", "9939");
		super.doView(renderRequest, renderResponse);
	}
}