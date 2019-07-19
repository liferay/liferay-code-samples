package com.liferay.code.samples.portal.modules.applications.portlets.action_command_ds.command;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;

import com.liferay.code.samples.portal.modules.applications.portlets.action_command_ds.portlet.ActionCommandPortlet;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;

@Component(
        immediate = true,
        property = {
            "javax.portlet.name=" + ActionCommandPortlet.NAME,		// which portlet this command is attached to
            "mvc.command.name=forget"								// the name of the command
        },
        service = MVCActionCommand.class
)
public class ForgetCommand implements MVCActionCommand {

    @Override
    public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {

        actionRequest.getPortletSession().removeAttribute("GREETER_MESSAGE");
        
        return true;
    }

}
