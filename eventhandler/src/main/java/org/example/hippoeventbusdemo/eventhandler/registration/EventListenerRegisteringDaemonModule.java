package org.example.hippoeventbusdemo.eventhandler.registration;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.example.hippoeventbusdemo.eventhandler.listener.SimpleLoggingPublicationEventListener;
import org.onehippo.cms7.services.HippoServiceRegistry;
import org.onehippo.cms7.services.eventbus.HippoEventBus;
import org.onehippo.repository.modules.AbstractReconfigurableDaemonModule;

public class EventListenerRegisteringDaemonModule extends AbstractReconfigurableDaemonModule {

    private SimpleLoggingPublicationEventListener listener;

    @Override
    protected void doConfigure(final Node moduleConfig) throws RepositoryException {
    }

    @Override
    protected void doInitialize(final Session session) throws RepositoryException {
        listener = new SimpleLoggingPublicationEventListener(session);
        HippoServiceRegistry.registerService(listener, HippoEventBus.class); 
    }

    @Override
    protected void doShutdown() {
        HippoServiceRegistry.unregisterService(listener, HippoEventBus.class);
        listener = null;
    }

}
