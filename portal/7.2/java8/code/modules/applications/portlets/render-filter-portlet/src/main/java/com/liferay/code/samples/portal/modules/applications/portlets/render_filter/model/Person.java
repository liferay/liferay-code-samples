package com.liferay.code.samples.portal.modules.applications.portlets.render_filter.model;

/**
 * Simple model class for a Person, containing a Name and an email.
 */
public class Person {

    private final String name;
    private final String email;

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
