package com.liferay.code.samples.portal.modules.applications.portlets.resource_command.model;

import java.time.LocalDate;

/**
 * Simple class to represent a PersonalTask, identified by a title, a due date and a priority.
 */
public class PersonalTask {

    public PersonalTask(String title) {
        this.createdAt = LocalDate.now();
        this.title = title;
    }

    public PersonalTask(String title, Priority priority, LocalDate dueDate) {
        this(title);
        this.dueDate = dueDate;
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }


    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    private final String title;
    private final LocalDate createdAt;
    private LocalDate dueDate;
    private Priority priority;

    public enum Priority {
        HIGH,
        MEDIUM,
        LOW
    }
}