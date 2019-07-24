<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>


<%@ page import="java.util.stream.Collectors" %>
<%@ page import="com.liferay.code.samples.portal.modules.applications.portlets.render_filter.portlet.MembersListPortlet" %>
<%@ page import="com.liferay.code.samples.portal.modules.applications.portlets.render_filter.model.Person" %>
<%@ page import="java.util.List" %>


<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
	String iconsPath = themeDisplay.getPathThemeImages() + "/lexicon/icons.svg";
%>