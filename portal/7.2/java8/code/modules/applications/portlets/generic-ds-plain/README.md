# Application demonstrating now build `GenericPortlet` and register it with OSGi Declarative Services

## Overview

This simple greeting application demonstrates how to build a standard (JSR286) compatible portlet's and register it with OSGi Declarative Services **annotations instead of writng `portlet.xml`**

## What it does

The application has one very simple, standards compliant `GenericPortlet` that prints "Hello world". Its only purpose is to demonstrate how DS annotations can simplify portlet development.

## Other frameworks and libraries used in this sample

- `OSGi Declrative Services` - to register portlet filters as OSGi components

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

- [Declarative Services](https://portal.liferay.dev/docs/7-2/frameworks/-/knowledge_base/f/declarative-services)