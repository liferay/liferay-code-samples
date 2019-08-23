# Application demonstrating now to use `FreeMarker` in the view layer

## Overview

This application shows how to use `FreeMarker` instead of `JSP` as view layer framework.

## What it does

It simply displays a welcome message that comes from a `FreeMarker` template. The purpose if the sample is to demonstrate how Liferay's bridge to `FreeMarker` takes care of processing those templates and filling the model.

It has one empty portlet class extending `FreeMarkerPortlet` that simply dispatches the requests to the view layer implemented as `FreeMarker` templates. It also has one view template.

## Other frameworks and libraries used in this sample

- `MVC Portlet` - for the portlet _(it would work the same with any other portlet framework)_
- `OSGi Declrative Services` - to register portlet filters as OSGi components

## How to build it

The [How to build a code sample](https://github.com/liferay/liferay-code-samples/blob/master/portal#how-to-build-a-code-sample) section of the Liferay's Portal samples `README` has detailed instructions how to build a sample using your favorite build tool.

## How to deploy it

This sample can be deployed by coping the resulting module `jar` file found

- For `Gradle` builds in `path/to/sample/build/libs` folder
- For `Maven` builds in `path/to/sample/target` folder

to Liferay Portal's `deploy` folder.

**This module requires Liferay's bridge to `FreeMarker` to be installed in order to be properly resolved. The bridge is
not installed by default in Liferay Portal 7.2.0 GA1. If you deploy this example make sure to also deploy the [com.liferay.util.bridges-6.0.2.jar](https://repository.liferay.com/nexus/content/groups/public/com/liferay/portal/com.liferay.util.bridges/6.0.2/)**

If you are deploying to a different Liferay Portal version please check the `pom` file in the [respective BOM](https://repository.liferay.com/nexus/content/groups/public/com/liferay/portal/release.portal.bom/) to find out which version of `com.liferay.util.bridges` module is compatible with your Liferay Portal version.

## How to configure it

There is no configuration needed. Use the applications menu to add the sample to a page and experiment with it.

## Related documentation

- [FreeMarker documentation](https://freemarker.apache.org/docs/index.html)
- [FreeMarker Portlet Template](https://portal.liferay.dev/docs/7-2/reference/-/knowledge_base/r/freemarker-portlet-template)
- [FreeMarker Taglib Macros](https://portal.liferay.dev/docs/7-2/reference/-/knowledge_base/r/freemarker-taglib-macros)
- [Liferay MVC Portlet](https://portal.liferay.dev/docs/7-2/appdev/-/knowledge_base/a/liferay-mvc-portlet)
