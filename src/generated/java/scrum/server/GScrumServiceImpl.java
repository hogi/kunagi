









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
        scrum.client.DataTransferObject ret = session.popNextData();
        onServiceMethodExecuted();
        return ret;
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
        scrum.client.DataTransferObject ret = session.popNextData();
        onServiceMethodExecuted();
        return ret;
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
        scrum.client.DataTransferObject ret = session.popNextData();
        onServiceMethodExecuted();
        return ret;
    }

    public scrum.client.DataTransferObject changeProperties(java.lang.String entityId, java.util.Map properties) {
        SessionData session = getSessionData();
        session.getContext().createSubContext("service:changeProperties");
        LOG.debug("changeProperties");
        try {
            getApp().onChangeProperties(session, entityId, properties);
        } catch (Throwable t) {
            handleServiceMethodException("changeProperties",t);
        }
        scrum.client.DataTransferObject ret = session.popNextData();
        onServiceMethodExecuted();
        return ret;
    }

    public scrum.client.DataTransferObject createEntity(java.lang.String type, java.util.Map properties) {
        SessionData session = getSessionData();
        session.getContext().createSubContext("service:createEntity");
        LOG.debug("createEntity");
        try {
            getApp().onCreateEntity(session, type, properties);
        } catch (Throwable t) {
            handleServiceMethodException("createEntity",t);
        }
        scrum.client.DataTransferObject ret = session.popNextData();
        onServiceMethodExecuted();
        return ret;
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
        scrum.client.DataTransferObject ret = session.popNextData();
        onServiceMethodExecuted();
        return ret;
    }

}
