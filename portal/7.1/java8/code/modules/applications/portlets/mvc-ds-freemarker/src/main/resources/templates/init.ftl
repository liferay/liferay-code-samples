<#--
/**
 * Copyright 2000-present Liferay, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
-->


<#--
    =========================
    NOTE FOR DEVELOPERS
    =========================
    Liferay Portal's servlet container is pre-configured to understand 
    and render Freemarker templates. One can use existing tag libraries in 
    templates almost the same way it is done in JSP pages. See 
    https://freemarker.apache.org/docs/pgui_misc_servlet.html#autoid_63
    for more information about how custom tags will can be accessed as 
    user-defined directives in Freemarker
-->


<#--
    Import needed TLDs
-->
<#assign aui = PortletJspTagLibs["/META-INF/liferay-aui.tld"] />
<#assign liferay_portlet = PortletJspTagLibs["/META-INF/liferay-portlet-ext.tld"] />
<#assign liferay_security = PortletJspTagLibs["/META-INF/liferay-security.tld"] />
<#assign liferay_theme = PortletJspTagLibs["/META-INF/liferay-theme.tld"] />
<#assign liferay_ui = PortletJspTagLibs["/META-INF/liferay-ui.tld"] />
<#assign liferay_util = PortletJspTagLibs["/META-INF/liferay-util.tld"] />
<#assign portlet = PortletJspTagLibs["/META-INF/liferay-portlet.tld"] />

<#-- The Freemarket alternative for JSP's '<liferay-theme:defineObjects />' -->
<@liferay_theme["defineObjects"] />

<#-- The Freemarket alternative for JSP's '<portlet:defineObjects />' -->
<@portlet["defineObjects"] />