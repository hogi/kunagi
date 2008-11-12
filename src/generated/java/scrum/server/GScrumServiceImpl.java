









// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.GwtServiceImplementationGenerator










package scrum.server;

import java.util.*;
import ilarkesto.logging.*;

public abstract class GScrumServiceImpl
            extends scrum.server.AServiceImpl
            implements scrum.client.ScrumService {

    private static final Logger LOG = Logger.get(GScrumServiceImpl.class);

    public scrum.client.DataTransferObject getProject(java.lang.String projectId) {
        SessionData session = getSessionData();
        session.getContext().createSubContext("service:getProject");
        LOG.debug("getProject");
        try {
            getApp().onGetProject(session, projectId);
        } catch (Throwable t) {
            handleServiceMethodException("getProject",t);
        }
        return session.popNextData();
    }

    public scrum.client.DataTransferObject getImpediments() {
        SessionData session = getSessionData();
        session.getContext().createSubContext("service:getImpediments");
        LOG.debug("getImpediments");
        try {
            getApp().onGetImpediments(session);
        } catch (Throwable t) {
            handleServiceMethodException("getImpediments",t);
        }
        return session.popNextData();
    }

    public scrum.client.DataTransferObject getBacklogItems() {
        SessionData session = getSessionData();
        session.getContext().createSubContext("service:getBacklogItems");
        LOG.debug("getBacklogItems");
        try {
            getApp().onGetBacklogItems(session);
        } catch (Throwable t) {
            handleServiceMethodException("getBacklogItems",t);
        }
        return session.popNextData();
    }

    public scrum.client.DataTransferObject changeProperty(java.lang.String entityId, java.lang.String property, java.lang.String value) {
        SessionData session = getSessionData();
        session.getContext().createSubContext("service:changeProperty");
        LOG.debug("changeProperty");
        try {
            getApp().onChangeProperty(session, entityId, property, value);
        } catch (Throwable t) {
            handleServiceMethodException("changeProperty",t);
        }
        return session.popNextData();
    }

    public scrum.client.DataTransferObject sleep(long millis) {
        SessionData session = getSessionData();
        session.getContext().createSubContext("service:sleep");
        LOG.debug("sleep");
        try {
            getApp().onSleep(session, millis);
        } catch (Throwable t) {
            handleServiceMethodException("sleep",t);
        }
        return session.popNextData();
    }

}
