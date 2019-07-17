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
/**
 * Filter that keeps track of how many times the portlet has been rendered and how long it takes to render.
 *
 * This filter illustrates how a RenderFilter can be used for actions that require processing before and after the
 * actual render of the portlet's response.
 *
 * In this case two <code>LongAdder</code> are used to keep track of <code>hits</code> and <code>accumulatedTimeMs</code>
 * spent rendering the portlet. As the Filter is instanced once, we can use fields to store both values, but we need to
 * be aware of concurrent updates (Thread Safety)
 *
 * Note that although LongAdders are threadsafe, there is eventual consistency between both values. There is a chance
 * that when you check for accumulated time another thread has updated the <code>hits</code> counter but has not updated
 * the <code>accumulatedTimeMs</code>.
 *
 */
public class MembersListStatsRenderFilter implements RenderFilter {

    //Thread safe - accumulator that keeps the number of times the portlet has been rendered
    private final LongAdder hits = new LongAdder();

    //Thread safe accumulator that keeps total time spent rendering the portlet.
    private final LongAdder accumulatedTimeMs = new LongAdder();

    @Override
    public void doFilter(RenderRequest request, RenderResponse response, FilterChain chain) throws IOException, PortletException {

        //Before invoking the portlet
        long startTime = System.nanoTime();

        // Invoke the rest of the filters in the chain
        //  (it also invokes the Portlet render method if this is the last filter in the chain
        chain.doFilter(request, response);

        //after invoking the portlet, we update the hits counter and accumulatedRenderTime
        //Note that, although both fields are threadSafe (can be updated concurrently),
        // the operation of updating the two is not atomic,
        // to make it atomic, we should add a synchronized block with the following three lines, but it could impact performace
        long renderTime = (System.nanoTime() - startTime) / 1000;
        hits.increment();
        accumulatedTimeMs.add(renderTime);

        if (LOG.isInfoEnabled()) {
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
