<%@ include file="/init.jsp" %>

<%
	String greeting = (String)renderRequest.getAttribute("GREETER_MESSAGE");
	if (greeting == null) greeting = "Hello stranger!";
%>
<liferay-portlet:actionURL name="greet" var="greetURL"/>

<h3><liferay-ui:message key="mvc_action_command_ds_jsp_portlet.caption"/></h3>

<p><%=greeting %></p>

<aui:form action="<%= greetURL %>" method="post" name="fm">

	<aui:input name="name" type="text"/>

	<aui:button-row>
		<aui:button type="submit"></aui:button>
	</aui:button-row>

</aui:form>