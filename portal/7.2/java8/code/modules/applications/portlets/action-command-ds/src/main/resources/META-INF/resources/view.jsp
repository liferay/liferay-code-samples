<%@ include file="/init.jsp" %>

<%
	String greeting = (String)renderRequest.getPortletSession().getAttribute("GREETER_MESSAGE");
%>

<liferay-portlet:actionURL name="greet" var="greetURL"/>
<liferay-portlet:actionURL name="forget" var="forgetURL"/>

<h3><liferay-ui:message key="mvc_action_command_ds_jsp_portlet.caption"/></h3>

<c:choose>

    <c:when test = "<%= greeting == null %>">

		<p>Hello stranger! Please tell me your name.</p>
		
		<aui:form action="<%= greetURL %>" method="post" name="fm">
		
			<aui:input name="name" type="text"/>
		
			<aui:button-row>
				<aui:button type="submit"></aui:button>
			</aui:button-row>
		
		</aui:form>

	</c:when>
    
	<c:otherwise>
		<p><%=greeting %></p>

		<aui:form action="<%= forgetURL %>" method="post" name="fm">
			<aui:button-row>
				<aui:button type="submit" value="mvc_action_command_ds_jsp_portlet.forget-me"></aui:button>
			</aui:button-row>
		</aui:form>
		
	</c:otherwise>
 </c:choose>

