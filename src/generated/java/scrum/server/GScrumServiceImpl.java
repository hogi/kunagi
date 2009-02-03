









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
        LOG.debug("ping");
        SessionData session = getSessionData();
        session.getContext().createSubContext("service:ping");
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
        LOG.debug("login");
        SessionData session = getSessionData();
        session.getContext().createSubContext("service:login");
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
        LOG.debug("selectProject");
        SessionData session = getSessionData();
        session.getContext().createSubContext("service:selectProject");
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
        LOG.debug("requestImpediments");
        SessionData session = getSessionData();
        session.getContext().createSubContext("service:requestImpediments");
        try {
            getApp().onRequestImpediments(session);
        } catch (Throwable t) {
            handleServiceMethodException("requestImpediments",t);
        }
        scrum.client.DataTransferObject ret = session.popNextData();
        onServiceMethodExecuted();
        return ret;
    }

    public scrum.client.DataTransferObject requestRequirements() {
        LOG.debug("requestRequirements");
        SessionData session = getSessionData();
        session.getContext().createSubContext("service:requestRequirements");
        try {
            getApp().onRequestRequirements(session);
        } catch (Throwable t) {
            handleServiceMethodException("requestRequirements",t);
        }
        scrum.client.DataTransferObject ret = session.popNextData();
        onServiceMethodExecuted();
        return ret;
    }

    public scrum.client.DataTransferObject requestCurrentSprint() {
        LOG.debug("requestCurrentSprint");
        SessionData session = getSessionData();
        session.getContext().createSubContext("service:requestCurrentSprint");
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
        LOG.debug("changeProperties");
        SessionData session = getSessionData();
        session.getContext().createSubContext("service:changeProperties");
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
        LOG.debug("createEntity");
        SessionData session = getSessionData();
        session.getContext().createSubContext("service:createEntity");
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
        LOG.debug("deleteEntity");
        SessionData session = getSessionData();
        session.getContext().createSubContext("service:deleteEntity");
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
        LOG.debug("sleep");
        SessionData session = getSessionData();
        session.getContext().createSubContext("service:sleep");
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
