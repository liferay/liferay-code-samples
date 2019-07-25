# Application demonstrating now to use MVC `ActionCommand`

## Overview

This simple greeting application demonstrates how to separate the portlet's actions to a individual `ActionCommand`s each in its own class. This is useful approach when portlet has too many or too complex actions to be implement as methods in the portlet class.

## What it does

There is empty `MVCPortlet` that simply passed requests to the view layer (JSP). Initially the view layer displays "Hello stranger" and a form to provide a name. Submitting the form triggers `greet` action which stores the name in the session and from that moment on, the view layer displays "Hello <THE NAME PROVIDED>" plus a form button to forget the name. Clicking that button triggers `forget` action which removes the name from the session and thus the greeting changes to the default one.

Both actions are implemented as individual `ActionCommand`s, registered as OSGi service and attached to the `MVCPortlet`.

## Other frameworks and libraries used in this sample

- `MVC Portlet` - for the portlet _(it would work the same with any other portlet framework)_
- `OSGi Declrative Services` - to register portlet filters as OSGi components
- `JSP` - to build the view layer

## How to build it

The [How to build a code sample](https://github.com/liferay/liferay-code-samples/blob/master/portal/README.md#liferay-code-samples-for-liferay-portal) section of the Liferay's Portal samples `README` has detailed instructions how to build a sample using your favorite build tool.

## How to deploy it

This sample can be deployed by simply coping the resulting module `jar` file found :

- For `Gradle` builds in `path/to/sample/build/libs` folder
- For `Maven` builds in `path/to/sample/target` folder

to Liferay Portal's `deploy` folder. 

It is also possible to configure both `Gradle` and `Maven` to directly deploy to a specific Liferay Portal instance.

## How to configure it

There is no configuration needed. Use the applications menu to add the sample to a page and experiment with it.

## Related documentation

- [Liferay MVC Portlet](https://portal.liferay.dev/docs/7-2/appdev/-/knowledge_base/a/liferay-mvc-portlet)
- [Liferay MVC Portlet - Action commands](https://portal.liferay.dev/docs/7-2/appdev/-/knowledge_base/a/liferay-mvc-portlet#liferay-mvc-command-classes)
- [Declarative Services](https://portal.liferay.dev/docs/7-2/frameworks/-/knowledge_base/f/declarative-services)