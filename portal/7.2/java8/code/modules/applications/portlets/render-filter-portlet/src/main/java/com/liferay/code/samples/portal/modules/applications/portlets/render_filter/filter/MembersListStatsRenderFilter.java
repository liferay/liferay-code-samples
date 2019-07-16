package com.liferay.code.samples.portal.modules.applications.portlets.render_filter.filter;


import com.liferay.code.samples.portal.modules.applications.portlets.render_filter.constants.MembersListPortletKeys;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.filter.FilterChain;
import javax.portlet.filter.FilterConfig;
import javax.portlet.filter.PortletFilter;
import javax.portlet.filter.RenderFilter;
import java.io.IOException;
import java.util.concurrent.atomic.LongAdder;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + MembersListPortletKeys.MEMBERSLIST_PORTLET_NAME
        },
        service = PortletFilter.class
)
public class MembersListStatsRenderFilter implements RenderFilter {

    //Thread safe - coaccumulator that keeps the number of times the portlet has been rendered
    private static LongAdder hits = new LongAdder();

    //Thread safe accumulator that keeps total time spent rendering the portlet.
    private static LongAdder accumulatedTimeMs = new LongAdder();

    @Override
    public void doFilter(RenderRequest request, RenderResponse response, FilterChain chain) throws IOException, PortletException {

        System.out.println("MemberListRenderFilter ");

        //Before invoking the portlet
        long startTime = System.nanoTime();


        chain.doFilter(request, response);

        //after invoking the portlet
        long renderTime = (System.nanoTime() - startTime) / 1000;
        hits.increment();
        accumulatedTimeMs.add(renderTime);

        if (true || LOG.isInfoEnabled()) {
            long totalHits = hits.longValue();
            long averageRenderTimeNs = accumulatedTimeMs.longValue() / totalHits;
            LOG.info("Portlet " + MembersListPortletKeys.MEMBERSLIST_PORTLET_NAME + " rendered in " + renderTime + " ms");
            LOG.info("Portlet " + MembersListPortletKeys.MEMBERSLIST_PORTLET_NAME + " rendered " + hits.longValue()
                    + " with an average " + averageRenderTimeNs + " ns render time");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws PortletException {

    }

    @Override
    public void destroy() {

    }

    private static final Log LOG = LogFactoryUtil.getLog(MembersListStatsRenderFilter.class);
}
