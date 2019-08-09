<%@ page
		import="com.liferay.code.samples.portal.modules.applications.portlets.configurable_portlet.portlet.TaskListPortlet" %><%@ page
		import="com.liferay.code.samples.portal.modules.applications.portlets.configurable_portlet.model.PersonalTask" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<%@ include file="/init.jsp" %>

<h3><liferay-ui:message key="task-list-portlet.caption"/></h3>


<!-- list of task, rendered using clay components.
In this case a list https://next.clayui.com/docs/css/components/list.html#1  -->
<ul class="list-group show-quick-actions-on-hover">

	<c:if test='<%= request.getAttribute(TaskListPortlet.TASK_LIST_ATTRIBUTE) != null %>'>

		<h4 class="list-group-header-title"><liferay-ui:message key="task-list-portlet.title"/></h4>

		<c:forEach var="task" items="<%= request.getAttribute(TaskListPortlet.TASK_LIST_ATTRIBUTE) %>" varStatus="status">
			<li class="list-group-item list-group-item-flex">
				<div class="autofit-col">
					<c:choose>
						<c:when test='${task.getPriority().toString().equals("HIGH")}'>
							<c:set var="priorityicon" value="angle-up"></c:set>
						</c:when>
						<c:when test='${task.getPriority().toString().equals("LOW")}'>
							<c:set var="priorityicon" value="angle-down"></c:set>
						</c:when>
						<c:otherwise>
							<c:set var="priorityicon" value="angle-right"></c:set>
						</c:otherwise>
					</c:choose>
					<div class="sticker sticker-secondary">
						<span class="inline-item">
							<svg class='lexicon-icon lexicon-icon-<c:out value="${priorityicon}"/>' focusable="false"
								 role="presentation" aria-details='priority-<c:out value="${status.index}"/>'>
								<use xlink:href="<%=iconsPath %>#<c:out value="${priorityicon}"/>" />
							</svg>
						</span>
					</div>
					<span id="priority-<c:out value="${status.index}"/>" style="visibility: hidden">Priority ${task.priority}</span>
				</div>
				<div class="autofit-col autofit-col-expand">
					<p class="list-group-title text-truncate"><c:out value="${task.title}"/></p>
					<p class="list-group-subtitle text-truncate">
						<span class="inline-item">
							<svg class='lexicon-icon lexicon-icon-calendar' focusable="false" role="presentation">
								<use xlink:href="<%=iconsPath %>#calendar" />
							</svg>
						</span>
						<%-- We use javatime taglib to format dueDate according to the pattern set in personalTaskDateFormat
						     The result is set on dueDate attribute in scope -->
						<javatime:format var="dueDate" value="${task.dueDate}" pattern="${personalTaskDateFormat}" />
						<c:out value="${dueDate}"/></p>
				</div>
			</li>
		</c:forEach>

	</c:if>

</ul>

