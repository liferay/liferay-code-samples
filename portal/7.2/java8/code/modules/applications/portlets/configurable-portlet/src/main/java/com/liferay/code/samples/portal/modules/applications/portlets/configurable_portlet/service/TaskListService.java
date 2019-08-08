package com.liferay.code.samples.portal.modules.applications.portlets.configurable_portlet.service;

import com.liferay.code.samples.portal.modules.applications.portlets.configurable_portlet.model.PersonalTask;

import java.util.Collection;

public interface TaskListService {

    public Collection<PersonalTask> loadPersonalTasks();
}
