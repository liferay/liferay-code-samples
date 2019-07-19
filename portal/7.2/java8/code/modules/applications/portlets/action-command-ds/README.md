## Application - MVC Portlet + ActionCommand + DS + JSP

This simple greeting application demonstrates how to separate the portlet's actions to a separate `ActionCommand`s

It consists of:

- one portlet serving as MVC controller
- two commands `greet` and `forget` which respectively set and remove a session value
- JSP page for the view allowing to call the respective command based on the session value     

For more information please see the documentation for:

- [Liferay MVC Portlet](https://portal.liferay.dev/docs/7-2/appdev/-/knowledge_base/a/liferay-mvc-portlet)
- [Liferay MVC Portlet - Action commands](https://portal.liferay.dev/docs/7-2/appdev/-/knowledge_base/a/liferay-mvc-portlet#liferay-mvc-command-classes)
- [Declarative Services](https://portal.liferay.dev/docs/7-2/frameworks/-/knowledge_base/f/declarative-services)