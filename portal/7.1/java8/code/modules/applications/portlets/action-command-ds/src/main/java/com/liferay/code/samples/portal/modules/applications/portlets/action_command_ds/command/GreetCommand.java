package com.liferay.code.samples.portal.modules.applications.portlets.action_command_ds.command;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;

import com.liferay.code.samples.portal.modules.applications.portlets.action_command_ds.portlet.ActionCommandPortlet;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;

@Component(
        immediate = true,
        property = {
            "javax.portlet.name=" + ActionCommandPortlet.NAME,		// which portlet this command is attached to
            "mvc.command.name=greet"								// the name of the command
        },
        service = MVCActionCommand.class
)
public class GreetCommand implements MVCActionCommand {

    @Override
    public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {

        String name = ParamUtil.get(actionRequest, "name", "guest");

        if (_log.isInfoEnabled()) {
            _log.info("Hello " + name);
        }

        String greetingMessage = "Hello " + name + "!";

        actionRequest.getPortletSession().setAttribute("GREETER_MESSAGE", greetingMessage);
        
        return true;
    }

    private static final Log _log = LogFactoryUtil.getLog(GreetCommand.class);
}
