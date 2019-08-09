# Application demonstrating now to make portlets configurable

## Overview

This simple application demonstrates how to enable some configuration that can be used to tweak how the portlet behaves.

## What it does



The `PersonalTaskPortletSystemConfiguration` interface defines the attributes that can be configured. This interface is used by Liferay to create automatically the form in the Control Panel that will allow the user to configure the settings.

The `TaskListConfigurationBeanDeclaration` is the component that allows the OSGi container to make available a Bean with the configured values to any component that needs to read it. 

The main `TaskListPortlet`, reads the configuration (through a `ConfigurationProvider`) and makes the configured values available to the response, toghether with the a list of `PersonalTask`s to be done, consisting of a priority, a due date and a title for the task. 

The view layer (implemented in `view.jsp`) shows the list and uses an external taglib that provides support for Java8 DateTime API to format the date according to the configured pattern. 

As the example uses an external JSP taglib, it also showcases how to use and include external libraries in your application.

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
- [Configurable Applications](https://portal.liferay.dev/docs/7-2/frameworks/-/knowledge_base/f/configurable-applications)
- [Declarative Services](https://portal.liferay.dev/docs/7-2/frameworks/-/knowledge_base/f/declarative-services)
- <a target="_blank" href="https://github.com/sargue/java-time-jsptags">Sargue's Java 8 java.time JSP taglib on GitHub</a>