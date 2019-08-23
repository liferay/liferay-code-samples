package com.liferay.code.samples.portal.modules.applications.portlets.render_filter.portlet;

import com.liferay.code.samples.portal.modules.applications.portlets.render_filter.model.Person;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

import java.util.Arrays;
import java.util.List;

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

	public static final String MEMBERSLIST_PORTLET_NAME 	= "member_list_portlet";
	public static final String MEMBERLIST_ATTRIBUTE 		= "memberlist";
	public static final String LOAD_USERS_ACTION 			= "loadUsers";

	/**
	 * Method that simulates the loading of a list of users and puts it as an attribute of the ActionRequest so that
	 *  it is available during the render phase.
	 *
	 *  In a real example, this user list will be probably fetched from a DB or from another system using a Service.
	 *  To keep this example simple, a hard-coded list of data is returned.
	 *
	 *  Also, in a real scenario, when there is more than one action to be implemented, it is usually delegated through
	 *  an MVCActionCommand (See MVCActionCommand code sample).
	 */
	public void loadUsers(ActionRequest actionRequest, ActionResponse actionResponse) {

		actionRequest.setAttribute(MembersListPortlet.MEMBERLIST_ATTRIBUTE, createStaticUserList());

	}

	private List<Person> createStaticUserList() {
		return Arrays.asList(
				new Person("Sievert Shayne", "Sievert.Shayne@example.org"),
				new Person("Vida Jonas", "Vida.Jonas@example.net"),
				new Person("Nikola Septima", "Nikola.Septima@example.com"),
				new Person("Ericka Merav", "Ericka.Merav@example.dev"),
				new Person("Kennet Brandr", "Kennet.Brandr@example.com"));
	}

}
