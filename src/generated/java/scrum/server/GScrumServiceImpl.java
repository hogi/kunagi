









// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.GwtServiceImplementationGenerator










package scrum.server;

import java.util.*;
import ilarkesto.logging.*;

public abstract class GScrumServiceImpl
            extends scrum.server.AServiceImpl
            implements scrum.client.ScrumService {

    private static final Logger LOG = Logger.get(GScrumServiceImpl.class);

    protected abstract void onPing(SessionData session);
    protected abstract void onLogin(SessionData session, java.lang.String username, java.lang.String password);
    protected abstract void onSelectProject(SessionData session, java.lang.String projectId);
    protected abstract void onRequestImpediments(SessionData session);
    protected abstract void onRequestRisks(SessionData session);
    protected abstract void onRequestRequirements(SessionData session);
    protected abstract void onRequestCurrentSprint(SessionData session);
    protected abstract void onChangeProperties(SessionData session, java.lang.String entityId, java.util.Map properties);
    protected abstract void onCreateEntity(SessionData session, java.lang.String type, java.util.Map properties);
    protected abstract void onDeleteEntity(SessionData session, java.lang.String entityId);
    protected abstract void onSleep(SessionData session, long millis);


    public ilarkesto.gwt.client.DataTransferObject ping() {
        LOG.debug("ping");
        SessionData session = getSessionData();
        session.getContext().createSubContext("service:ping");
        try {
            onPing(session);
        } catch (Throwable t) {
            handleServiceMethodException("ping",t);
        }
        ilarkesto.gwt.client.DataTransferObject ret = session.popNextData();
        onServiceMethodExecuted();
        return ret;
    }


    public ilarkesto.gwt.client.DataTransferObject login(java.lang.String username, java.lang.String password) {
        LOG.debug("login");
        SessionData session = getSessionData();
        session.getContext().createSubContext("service:login");
        try {
            onLogin(session, username, password);
        } catch (Throwable t) {
            handleServiceMethodException("login",t);
        }
        ilarkesto.gwt.client.DataTransferObject ret = session.popNextData();
        onServiceMethodExecuted();
        return ret;
    }


    public ilarkesto.gwt.client.DataTransferObject selectProject(java.lang.String projectId) {
        LOG.debug("selectProject");
        SessionData session = getSessionData();
        session.getContext().createSubContext("service:selectProject");
        try {
            onSelectProject(session, projectId);
        } catch (Throwable t) {
            handleServiceMethodException("selectProject",t);
        }
        ilarkesto.gwt.client.DataTransferObject ret = session.popNextData();
        onServiceMethodExecuted();
        return ret;
    }


    public ilarkesto.gwt.client.DataTransferObject requestImpediments() {
        LOG.debug("requestImpediments");
        SessionData session = getSessionData();
        session.getContext().createSubContext("service:requestImpediments");
        try {
            onRequestImpediments(session);
        } catch (Throwable t) {
            handleServiceMethodException("requestImpediments",t);
        }
        ilarkesto.gwt.client.DataTransferObject ret = session.popNextData();
        onServiceMethodExecuted();
        return ret;
    }


    public ilarkesto.gwt.client.DataTransferObject requestRisks() {
        LOG.debug("requestRisks");
        SessionData session = getSessionData();
        session.getContext().createSubContext("service:requestRisks");
        try {
            onRequestRisks(session);
        } catch (Throwable t) {
            handleServiceMethodException("requestRisks",t);
        }
        ilarkesto.gwt.client.DataTransferObject ret = session.popNextData();
        onServiceMethodExecuted();
        return ret;
    }


    public ilarkesto.gwt.client.DataTransferObject requestRequirements() {
        LOG.debug("requestRequirements");
        SessionData session = getSessionData();
        session.getContext().createSubContext("service:requestRequirements");
        try {
            onRequestRequirements(session);
        } catch (Throwable t) {
            handleServiceMethodException("requestRequirements",t);
        }
        ilarkesto.gwt.client.DataTransferObject ret = session.popNextData();
        onServiceMethodExecuted();
        return ret;
    }


    public ilarkesto.gwt.client.DataTransferObject requestCurrentSprint() {
        LOG.debug("requestCurrentSprint");
        SessionData session = getSessionData();
        session.getContext().createSubContext("service:requestCurrentSprint");
        try {
            onRequestCurrentSprint(session);
        } catch (Throwable t) {
            handleServiceMethodException("requestCurrentSprint",t);
        }
        ilarkesto.gwt.client.DataTransferObject ret = session.popNextData();
        onServiceMethodExecuted();
        return ret;
    }


    public ilarkesto.gwt.client.DataTransferObject changeProperties(java.lang.String entityId, java.util.Map properties) {
        LOG.debug("changeProperties");
        SessionData session = getSessionData();
        session.getContext().createSubContext("service:changeProperties");
        try {
            onChangeProperties(session, entityId, properties);
        } catch (Throwable t) {
            handleServiceMethodException("changeProperties",t);
        }
        ilarkesto.gwt.client.DataTransferObject ret = session.popNextData();
        onServiceMethodExecuted();
        return ret;
    }


    public ilarkesto.gwt.client.DataTransferObject createEntity(java.lang.String type, java.util.Map properties) {
        LOG.debug("createEntity");
        SessionData session = getSessionData();
        session.getContext().createSubContext("service:createEntity");
        try {
            onCreateEntity(session, type, properties);
        } catch (Throwable t) {
            handleServiceMethodException("createEntity",t);
        }
        ilarkesto.gwt.client.DataTransferObject ret = session.popNextData();
        onServiceMethodExecuted();
        return ret;
    }


    public ilarkesto.gwt.client.DataTransferObject deleteEntity(java.lang.String entityId) {
        LOG.debug("deleteEntity");
        SessionData session = getSessionData();
        session.getContext().createSubContext("service:deleteEntity");
        try {
            onDeleteEntity(session, entityId);
        } catch (Throwable t) {
            handleServiceMethodException("deleteEntity",t);
        }
        ilarkesto.gwt.client.DataTransferObject ret = session.popNextData();
        onServiceMethodExecuted();
        return ret;
    }


    public ilarkesto.gwt.client.DataTransferObject sleep(long millis) {
        LOG.debug("sleep");
        SessionData session = getSessionData();
        session.getContext().createSubContext("service:sleep");
        try {
            onSleep(session, millis);
        } catch (Throwable t) {
            handleServiceMethodException("sleep",t);
        }
        ilarkesto.gwt.client.DataTransferObject ret = session.popNextData();
        onServiceMethodExecuted();
        return ret;
    }

}
