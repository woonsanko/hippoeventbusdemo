package org.example.hippoeventbusdemo.eventhandler.registration;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.example.hippoeventbusdemo.eventhandler.listener.SimpleLoggingPublicationEventListener;
import org.onehippo.cms7.services.HippoServiceRegistry;
import org.onehippo.cms7.services.eventbus.HippoEventBus;
import org.onehippo.repository.modules.AbstractReconfigurableDaemonModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventListenerRegisteringDaemonModule extends AbstractReconfigurableDaemonModule {

    private static Logger log = LoggerFactory.getLogger(EventListenerRegisteringDaemonModule.class);

    private SimpleLoggingPublicationEventListener listener;

    @Override
    protected void doConfigure(final Node moduleConfig) throws RepositoryException {
    }

    @Override
    protected void doInitialize(final Session session) throws RepositoryException {
        listener = new SimpleLoggingPublicationEventListener(session);
        log.info("Registering listener: {}", listener);
        HippoServiceRegistry.registerService(listener, HippoEventBus.class);
    }

    @Override
    protected void doShutdown() {
        log.info("Unregistering listener: {}", listener);
        HippoServiceRegistry.unregisterService(listener, HippoEventBus.class);
        listener = null;
    }

}
