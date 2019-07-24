package com.liferay.code.samples.portal.modules.applications.portlets.render_filter.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

import org.osgi.service.component.annotations.Component;

import java.io.IOException;

@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + MembersListPortlet.MEMBERSLIST_PORTLET_NAME,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class MembersListPortlet extends MVCPortlet {

	public static final String MEMBERSLIST_PORTLET_NAME 	= "render_filter_portlet";
	public static final String MEMBERLIST_ATTRIBUTE 		= "memberlist";
	public static final String LOAD_USERS_ACTION 			= "loadUsers";

}