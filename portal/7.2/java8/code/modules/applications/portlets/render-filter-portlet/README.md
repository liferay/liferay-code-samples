## Application - GenericPortlet + RenderFilter + Declarative Services 

This application simply shows how to use `PortletFilter`s to intercept, adapt or modify the request or responses around the execution of a particular `Portlet`.

The example uses `RenderFilter`s, one of the types of `PortletFilter`, to modify data and monitor the time spent in the render phase of the portlet. As the example uses two filters, it also demonstrates how to configure the order of execution of both filters.

It consists of:

- one portlet serving as MVC controller
- a `EncodingPersonEmailsRenderFilter` filter, which obfuscates some data (in this case the emails) before it is rendered to the client
- a second filter, `MemberListStatsRenderFilter`, which measures the time that takes to render the portlet (this illustrates how to add code before and after the execution of the portlet).
- JSP page for the view allowing to call the respective command based on the session value     

For more information please see the documentation for:

- [Liferay MVC Portlet](https://portal.liferay.dev/docs/7-2/appdev/-/knowledge_base/a/liferay-mvc-portlet)
- [Using Filters to intercept, adapt or modify data around Portlet execution tutorial](/docs/7-2/tutorials/-/knowledge_base/t/render_filter_portlet)
