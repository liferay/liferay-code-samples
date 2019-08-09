package com.liferay.code.samples.portal.modules.applications.portlets.configurable_portlet.service;


import com.liferay.code.samples.portal.modules.applications.portlets.configurable_portlet.model.PersonalTask;
import com.liferay.code.samples.portal.modules.applications.portlets.configurable_portlet.model.PersonalTask.Priority;
import org.osgi.service.component.annotations.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.Collection;

import static java.time.DayOfWeek.*;

/**
 * Component that represents a service to load a list of personal tasks
 *
 * In a real case scenario, this would probably be a service built with ServiceBuilder. For sake of simplicity,
 * we use a Dummy service which simply returns a harcoded list of <code>PersonalTask</code>s.
 */
@Component(
        property = "model.class.name=com.liferay.code.samples.portal.modules.applications.portlets.resource_command.model.PersonalTask",
        service = TaskListService.class
)
public class TaskListMockService implements TaskListService {

    @Override
    public Collection<PersonalTask> loadPersonalTasks() {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        LocalDate thisWeek = today.with(SUNDAY);
        LocalDate endOfWorkingWeek = today.with(FRIDAY);
        LocalDate endOfMonth = today.with(TemporalAdjusters.lastDayOfMonth());

        return Arrays.asList(
                new PersonalTask("Review new Liferay tutorials", Priority.MEDIUM, today),
                new PersonalTask("Write a Community Blog post", Priority.MEDIUM, thisWeek),
                new PersonalTask("Weekly team sync", Priority.HIGH, today.with(TemporalAdjusters.next(WEDNESDAY))),
                new PersonalTask("Send monthly report", Priority.MEDIUM, endOfMonth),
                new PersonalTask("Contribute a code sample to Liferay's docs", Priority.HIGH, endOfWorkingWeek),
                new PersonalTask("Attend the local LSUG meet up", Priority.MEDIUM, tomorrow),
                new PersonalTask("Improve code comments in old samples", Priority.LOW, endOfMonth)
        );
    }

}
