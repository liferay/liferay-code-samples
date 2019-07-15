<%@ include file="/init.jsp" %>

<p><%= renderRequest.getAttribute("GREETER_MESSAGE") %></p>

<p>
	<b><liferay-ui:message key="actioncommand.caption"/></b>
</p>

<liferay-portlet:actionURL name="greet" var="greetURL"/>

<aui:form action="<%= greetURL %>" method="post" name="fm">

	<aui:input name="name" type="text"/>

	<aui:button-row>
		<aui:button type="submit"></aui:button>
	</aui:button-row>

</aui:form>