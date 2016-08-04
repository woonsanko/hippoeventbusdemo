package org.example.hippoeventbusdemo.eventhandler.listener;

import javax.jcr.Session;

import org.onehippo.cms7.event.HippoEvent;
import org.onehippo.cms7.event.HippoEventConstants;
import org.onehippo.cms7.services.eventbus.Subscribe;
import org.onehippo.repository.events.HippoWorkflowEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleLoggingPublicationEventListener {

    private static Logger log = LoggerFactory.getLogger(SimpleLoggingPublicationEventListener.class);

    private final Session session;

    public SimpleLoggingPublicationEventListener(final Session session) {
        this.session = session;
    }

    @Subscribe
    public void handleEvent(HippoEvent<?> event) {
        final String category = event.category();

        if (HippoEventConstants.CATEGORY_WORKFLOW.equals(category)) {
            final HippoWorkflowEvent workflowEvent = new HippoWorkflowEvent(event);
            final String interaction = workflowEvent.interaction();
            final String action = workflowEvent.action();
            final String subjectId = workflowEvent.subjectId();
            final String subjectPath = workflowEvent.subjectPath();

            log.info("Workflow event captured. interaction: '{}', action: '{}', subjectId: '{}', subjectPath: '{}'.",
                    interaction, action, subjectId, subjectPath);
        }
    }
}
