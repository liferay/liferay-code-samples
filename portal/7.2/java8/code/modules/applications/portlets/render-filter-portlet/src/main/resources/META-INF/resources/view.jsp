<%@ page import="java.util.stream.Collectors" %>
<%@ page
		import="com.liferay.code.samples.portal.modules.applications.portlets.render_filter.portlet.MembersListPortlet" %>
<%@ page import="com.liferay.code.samples.portal.modules.applications.portlets.render_filter.model.Person" %>
<%@ page import="java.util.List" %>
<%@ include file="/init.jsp" %>

<liferay-portlet:actionURL name="<%= MembersListPortlet.LOAD_USERS_ACTION %>" var="loadURL"/>

	<!-- list of users, rendered using clay components.
		 In this case a list https://next.clayui.com/docs/css/components/list.html#1  -->
	<ul class="list-group show-quick-actions-on-hover">
		<li clas="list-group-header">
			<h3 class="list-group-header-title"><liferay-ui:message key="render_filter_portlet.caption"/></h3>
		</li>

		<c:if test='<%= renderRequest.getAttribute(MembersListPortlet.MEMBERLIST_ATTRIBUTE) != null %>'>
		<c:forEach var="user" items="<%= renderRequest.getAttribute(MembersListPortlet.MEMBERLIST_ATTRIBUTE) %>">
		<li class="list-group-item list-group-item-flex">
			<div class="autofit-col">
				<div class="sticker sticker-secondary">
					<span class="inline-item">
						<svg class="lexicon-icon lexicon-icon-users" focusable="false" role="presentation">
							<use xlink:href="path/to/icons.svg#users" />
						</svg>
					</span>
				</div>
			</div>
			<div class="autofit-col autofit-col-expand">
				<p class="list-group-title text-truncate"><c:out value="${user.name}"/></p>
				<p class="list-group-subtitle text-truncate"><c:out value="${user.email}"/></p>
			</div>
		</li>
		</c:forEach>
		</c:if>
	</ul>

<aui:button
		type="button" primary="true"
		href="<%= loadURL %>"
		value="render_filter_portlet.button.load_users"/>
