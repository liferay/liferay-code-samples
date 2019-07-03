## Application - Liferay MVC portlet + DS + Freemarker 

Demonstrates how to build a portlet using:

- Liferay's MVC framework
- Liferay's bridge to Freemarker
- OSGi's Declarative Services for wiring the service
- Freemarker templates for rendering
- SASS/CSS for styling
- Language bundles for internationalization

### Deployment

This module requires Liferay's bridge to Freemarker to be installed in order to be properly resolved. The bridge is
not installed by default in Liferay Portal 7.1.3 GA4. If you deploy this example make sure to also deploy the [com.liferay.util.bridges-5.0.1.jar](https://repository.liferay.com/nexus/content/groups/public/com/liferay/portal/com.liferay.util.bridges/5.0.1/)

If you are deploying to a different Liferay Portal version please check the `pom` file in the [respective BOM](https://repository.liferay.com/nexus/content/groups/public/com/liferay/portal/release.portal.bom/) to find out which version of `com.liferay.util.bridges` module is compatible with your Liferay Portal version.
