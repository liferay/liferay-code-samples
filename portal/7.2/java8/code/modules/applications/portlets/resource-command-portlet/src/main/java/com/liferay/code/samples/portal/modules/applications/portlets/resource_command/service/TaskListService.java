package com.liferay.code.samples.portal.modules.applications.portlets.resource_command.service;

import com.liferay.code.samples.portal.modules.applications.portlets.resource_command.model.PersonalTask;

import java.util.Collection;

public interface TaskListService {

    public Collection<PersonalTask> loadPersonalTasks();
}
