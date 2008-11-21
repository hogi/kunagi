









// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.GwtServiceImplementationGenerator










package scrum.server;

import java.util.*;
import ilarkesto.logging.*;

public abstract class GScrumServiceImpl
            extends scrum.server.AServiceImpl
            implements scrum.client.ScrumService {

    private static final Logger LOG = Logger.get(GScrumServiceImpl.class);

    public scrum.client.DataTransferObject ping() {
        SessionData session = getSessionData();
        session.getContext().createSubContext("service:ping");
        LOG.debug("ping");
        try {
            getApp().onPing(session);
        } catch (Throwable t) {
            handleServiceMethodException("ping",t);
        }
        scrum.client.DataTransferObject ret = session.popNextData();
        onServiceMethodExecuted();
        return ret;
    }

    public scrum.client.DataTransferObject login(java.lang.String username, java.lang.String password) {
        SessionData session = getSessionData();
        session.getContext().createSubContext("service:login");
        LOG.debug("login");
        try {
            getApp().onLogin(session, username, password);
        } catch (Throwable t) {
            handleServiceMethodException("login",t);
        }
        scrum.client.DataTransferObject ret = session.popNextData();
        onServiceMethodExecuted();
        return ret;
    }

    public scrum.client.DataTransferObject selectProject(java.lang.String projectId) {
        SessionData session = getSessionData();
        session.getContext().createSubContext("service:selectProject");
        LOG.debug("selectProject");
        try {
            getApp().onSelectProject(session, projectId);
        } catch (Throwable t) {
            handleServiceMethodException("selectProject",t);
        }
        scrum.client.DataTransferObject ret = session.popNextData();
        onServiceMethodExecuted();
        return ret;
    }

    public scrum.client.DataTransferObject requestImpediments() {
        SessionData session = getSessionData();
        session.getContext().createSubContext("service:requestImpediments");
        LOG.debug("requestImpediments");
        try {
            getApp().onRequestImpediments(session);
        } catch (Throwable t) {
            handleServiceMethodException("requestImpediments",t);
        }
        scrum.client.DataTransferObject ret = session.popNextData();
        onServiceMethodExecuted();
        return ret;
    }

    public scrum.client.DataTransferObject requestBacklogItems() {
        SessionData session = getSessionData();
        session.getContext().createSubContext("service:requestBacklogItems");
        LOG.debug("requestBacklogItems");
        try {
            getApp().onRequestBacklogItems(session);
        } catch (Throwable t) {
            handleServiceMethodException("requestBacklogItems",t);
        }
        scrum.client.DataTransferObject ret = session.popNextData();
        onServiceMethodExecuted();
        return ret;
    }

    public scrum.client.DataTransferObject requestCurrentSprint() {
        SessionData session = getSessionData();
        session.getContext().createSubContext("service:requestCurrentSprint");
        LOG.debug("requestCurrentSprint");
        try {
            getApp().onRequestCurrentSprint(session);
        } catch (Throwable t) {
            handleServiceMethodException("requestCurrentSprint",t);
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

    public scrum.client.DataTransferObject deleteEntity(java.lang.String entityId) {
        SessionData session = getSessionData();
        session.getContext().createSubContext("service:deleteEntity");
        LOG.debug("deleteEntity");
        try {
            getApp().onDeleteEntity(session, entityId);
        } catch (Throwable t) {
            handleServiceMethodException("deleteEntity",t);
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
