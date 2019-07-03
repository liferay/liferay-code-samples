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

package com.liferay.code.samples.portal.modules.applications.portlets.mvc_ds_freemarker;

import com.liferay.util.bridges.freemarker.FreeMarkerPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author Liferay
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.css-class-wrapper=portlet-freemarker",			// CSS class to be added to the portlet's HTML element
		"com.liferay.portlet.display-category=category.sample",				// category to which this portlet will be added in applications menu
		"com.liferay.portlet.header-portlet-css=/css/main.css",				// portlet specific CSS
		"com.liferay.portlet.instanceable=true",							// can more that one istance be added to a page 
		"javax.portlet.name=" + BlankFreemarkerPortlet.NAME,				// portlet's name
		"javax.portlet.init-param.template-path=/",							// relative path to all display templates
		"javax.portlet.init-param.view-template=/templates/view.ftl",		// the path to default view Freemarker template
		"javax.portlet.resource-bundle=content.Language",					// the resource bundle file for I18N
		"javax.portlet.security-role-ref=power-user,user",					// references to security roles 
		"javax.portlet.version=3.0"											// opt-in to use Portlet 3.0 runtime functionality
	},
	service = Portlet.class
)
public class BlankFreemarkerPortlet extends FreeMarkerPortlet {

	public static final String NAME = "mvc_ds_freemarker_portlet";

}