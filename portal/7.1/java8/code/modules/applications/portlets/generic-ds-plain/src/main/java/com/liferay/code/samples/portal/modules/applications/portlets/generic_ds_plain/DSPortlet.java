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

package com.liferay.code.samples.portal.modules.applications.portlets.generic_ds_plain;

import java.io.IOException;
import java.io.PrintWriter;

import javax.portlet.GenericPortlet;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Liferay
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",				// category to which this portlet will be added in applications menu
		"com.liferay.portlet.instanceable=true",							// can more that one istance be added to a page 
		"javax.portlet.name=" + DSPortlet.NAME,								// portlet's name
		"javax.portlet.display-name=GenericPortlet using DS",				// portlet's name to be displayed in applications menu
		"javax.portlet.security-role-ref=power-user,user"					// references to security roles 
	},
	service = Portlet.class
)
public class DSPortlet extends GenericPortlet {

	public static final String NAME = "plain_ds_portlet";

	@Override
	protected void doView(RenderRequest request, RenderResponse response)
		throws IOException, PortletException {

		PrintWriter printWriter = response.getWriter();

		printWriter.print("DS Portlet - Hello World!");
	}

}