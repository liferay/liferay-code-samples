package com.liferay.code.samples.portal.modules.applications.portlets.render_filter.command;

import com.liferay.code.samples.portal.modules.applications.portlets.render_filter.constants.MembersListPortletKeys;
import com.liferay.code.samples.portal.modules.applications.portlets.render_filter.model.Person;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import org.osgi.service.component.annotations.Component;


import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import java.util.Arrays;
import java.util.List;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + MembersListPortletKeys.MEMBERSLIST_PORTLET_NAME,
                "mvc.command.name=" + MembersListPortletKeys.LOAD_USERS_ACTION
        },
        service = MVCActionCommand.class
)
public class UserListMVCActionCommand implements MVCActionCommand {

    @Override
    public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse)
            throws PortletException {

        //Loads a hard-coded list of users
        // Usually the list of users would be retrieved using a service
        actionRequest.setAttribute(MembersListPortletKeys.MEMBERLIST_ATTRIBUTE, loadUsers());

        return false;
    }

    private List<Person> loadUsers() {
        return Arrays.asList(
                new Person("Sievert Shayne", "Sievert.Shayne@example.com"),
                new Person("Vida Jonas", "Vida.Jonas@example.com"),
                new Person("Nikola Septima", "Nikola.Septima@example.com"),
                new Person("Ericka Merav", "Ericka.Merav@example.com"),
                new Person("Kennet Brandr", "Kennet.Brandr@example.com"));
    }
}
