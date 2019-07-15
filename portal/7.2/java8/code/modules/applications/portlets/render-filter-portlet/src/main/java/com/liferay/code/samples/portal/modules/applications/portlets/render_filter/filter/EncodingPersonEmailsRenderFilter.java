package com.liferay.code.samples.portal.modules.applications.portlets.render_filter.filter;

import com.liferay.code.samples.portal.modules.applications.portlets.render_filter.constants.MembersListPortletKeys;
import com.liferay.code.samples.portal.modules.applications.portlets.render_filter.model.Person;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.filter.FilterChain;
import javax.portlet.filter.FilterConfig;
import javax.portlet.filter.PortletFilter;
import javax.portlet.filter.RenderFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component(
        immediate = true,
        property = "javax.portlet.name=" + MembersListPortletKeys.MEMBERSLIST_PORTLET_NAME,
        service = PortletFilter.class
)
public class EncodingPersonEmailsRenderFilter implements RenderFilter {
    @Override
    public void doFilter(RenderRequest request, RenderResponse response, FilterChain chain)
            throws IOException, PortletException {

        //This is executed before the portlet render
        Optional.ofNullable((List<Person>)request.getAttribute(MembersListPortletKeys.MEMBERLIST_ATTRIBUTE))
                .ifPresent(personList -> request.setAttribute(MembersListPortletKeys.MEMBERLIST_ATTRIBUTE, encodeEmails(personList)));

        // Invoke the rest of the filters in the chain
        //  (it also invokes the Portlet render method if this is the last filter in the chain
        chain.doFilter(request, response);

    }

    private List<Person> encodeEmails(List<Person> list) {
        return list.stream()
                .map(this::encodePersonEmail)
                .collect(Collectors.toList());
    }

    private Person encodePersonEmail(Person person) {
        return new Person(person.getName(),
                          person.getEmail().replaceFirst("(.+)(...)@(...)(.*)", "$1...@...$4"));

    }

    @Override
    public void init(FilterConfig filterConfig) throws PortletException {
    }

    @Override
    public void destroy() {
    }
}
