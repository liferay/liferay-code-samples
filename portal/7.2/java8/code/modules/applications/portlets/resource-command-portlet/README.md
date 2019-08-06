# Application demonstrating now to use MVC `ResourceCommand`

## Overview

This simple application demonstrates the use of a `ResourceCommand` to handle the delivery of Resources from a portlet.

## What it does

The main `TaskListPortlet` simply shows a list of `PersonalTask`s to be done, consisting of a priority, a due date
and a title for the task. The list of tasks are retrieved from a `TaskListMockService` which simply returns a hard coded
list of tasks (in a real case scenario this would probably be a service that retrieves the tasks from DB).

There is a `XLSTaskListMVCResourceCommand` that uses the same service to generate a xls file with the task lists and 
return it as a binary file. 

Both the service and the `MVCResourceCommand` are registered as OSGi services and attached to the `MVCPortlet` using declarative services.
The view layer (implemented in `view.jsp`) shows how to add a button and link to the resourceCommand invocation.

As the example uses Apache POI library (`poi-ooxml`) to generate the `.xls` file, this example also showcases how to 
use and include external libraries in your application.

## Other frameworks and libraries used in this sample

- `MVC Portlet` - for the portlet _(it would work the same with any other portlet framework)_
- `OSGi Declrative Services` - to register portlet filters as OSGi components
- `JSP` - to build the view layer
- `Apache POI` - to generate Office and OpenDocument files (as the Spreadsheet or .xls files)

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
- [Liferay MVC Portlet - Resource Commands](https://portal.liferay.dev/docs/7-1/tutorials/-/knowledge_base/t/mvc-resource-command)
- [Declarative Services](https://portal.liferay.dev/docs/7-2/frameworks/-/knowledge_base/f/declarative-services)
- <a target="_blank" href="https://portal.liferay.dev/docs/7-1/tutorials/-/knowledge_base/t/adding-third-party-libraries-to-a-module#embedding-libraries-in-a-module">Embedding Libraries In A Module</a>