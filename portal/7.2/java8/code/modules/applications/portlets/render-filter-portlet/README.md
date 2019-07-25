# Application demonstrating now to apply `RenderFilter` to existing `Portlet`

## Overview

This application shows how to use `PortletFilter`s to intercept, adapt or modify the request or responses around the execution of a particular `Portlet`.

**Please note** that for simplicity reasons, both the portlet and the filters are located in the same module in this sample. In really though, __portlet filters are most useful when applied to portlets in 3rd party modules__ which source code is not available or can not be modified. The only information needed to attach a filter to a third party portlet is its name:

```java
@Component(
    immediate = true,
    property = {
        "javax.portlet.name= <THIRD PARTY'S PORTLET NAME>",
    },
    service = PortletFilter.class

public class MyFilter implements RenderFilter
```

## What it does

It displays a list of people's names and e-mails. There is simple `MVCPortlet` which generates a list of `Person` objects and passes it to the view layer (JSP pages).

There are two `RenderFilter`s (both OSGi services) dynamically attached to this portlet: 

- One obfuscates the email addresses. This demonstrates how to post process data returned by the portlet.
- The other one measures the time that it takes to render the portlet and writes it to the logs. This illustrates how to add code before and after the execution of the portlet's render phase.

As the example uses two filters, it also demonstrates how to configure the order of execution of both filters.

## Other frameworks and libraries used in this sample

- `MVC Portlet` - for the portlet _(it would work the same with any other portlet framework)_
- `OSGi Declrative Services` - to register portlet filters as OSGi components
- `JSP` - to build the view layer
- [Clay](https://clayui.com/) components - to display the list with icons

## How to build it

The [How to build a code sample](https://github.com/liferay/liferay-code-samples/blob/master/portal#how-to-build-a-code-sample) section of the Liferay's Portal samples `README` has detailed instructions how to build a sample using your favorite build tool.

## How to deploy it

This sample can be deployed by simply coping the resulting module `jar` file found :

- For `Gradle` builds in `path/to/sample/build/libs` folder
- For `Maven` builds in `path/to/sample/target` folder

to Liferay Portal's `deploy` folder. 

It is also possible to configure both `Gradle` and `Maven` to directly deploy to a specific Liferay Portal instance.

## How to configure it

There is no configuration needed. Use the applications menu to add the sample to a page and experiment with it.

## Related documentation

- [Liferay MVC Portlet](https://portal.liferay.dev/docs/7-1/tutorials/-/knowledge_base/t/liferay-mvc-portlet)
- [Using Filters to intercept, adapt or modify data around Portlet execution tutorial](/docs/7-2/tutorials/-/knowledge_base/t/render_filter_portlet)
