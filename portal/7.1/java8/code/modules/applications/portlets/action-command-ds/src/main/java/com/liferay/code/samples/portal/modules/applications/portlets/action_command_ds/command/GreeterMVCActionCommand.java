package com.liferay.code.samples.portal.modules.applications.portlets.action_command_ds.command;

import com.liferay.code.samples.portal.modules.applications.portlets.action_command_ds.portlet.ActionCommandPortlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + ActionCommandPortlet.NAME,
                "mvc.command.name=greet"
        },
        service = MVCActionCommand.class
)
public class GreeterMVCActionCommand implements MVCActionCommand {

    @Override
    public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {

        String name = ParamUtil.get(actionRequest, "name", "guest");

        if (_log.isInfoEnabled()) {
            _log.info("Hello " + name);
        }

        String greetingMessage = "Hello " + name + "!";

        actionRequest.setAttribute("GREETER_MESSAGE", greetingMessage);
        SessionMessages.add(actionRequest, "greetingMessage", greetingMessage);

        return true;
    }

    private static final Log _log = LogFactoryUtil.getLog(GreeterMVCActionCommand.class);
}
