// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.GwtServiceImplementationGenerator










package scrum.server;

import java.util.*;
import ilarkesto.logging.*;

public abstract class GScrumServiceImpl
            extends ilarkesto.gwt.server.AGwtServiceImpl
            implements scrum.client.ScrumService {

    private static final Logger LOG = Logger.get(GScrumServiceImpl.class);

    protected abstract void onPing(WebSession session);
    protected abstract void onLogin(WebSession session, java.lang.String username, java.lang.String password);
    protected abstract void onLogout(WebSession session);
    protected abstract void onChangePassword(WebSession session, java.lang.String oldPassword, java.lang.String newPassword);
    protected abstract void onResetPassword(WebSession session, java.lang.String userId);
    protected abstract void onSelectProject(WebSession session, java.lang.String projectId);
    protected abstract void onCloseProject(WebSession session);
    protected abstract void onSwitchToNextSprint(WebSession session);
    protected abstract void onRequestImpediments(WebSession session);
    protected abstract void onRequestRisks(WebSession session);
    protected abstract void onRequestRequirements(WebSession session);
    protected abstract void onRequestQualitys(WebSession session);
    protected abstract void onChangeProperties(WebSession session, java.lang.String entityId, java.util.Map properties);
    protected abstract void onCreateEntity(WebSession session, java.lang.String type, java.util.Map properties);
    protected abstract void onDeleteEntity(WebSession session, java.lang.String entityId);
    protected abstract void onRequestEntityByReference(WebSession session, java.lang.String reference);
    protected abstract void onSleep(WebSession session, long millis);


    public scrum.client.DataTransferObject ping() {
        LOG.debug("ping");
        WebSession session = (WebSession) getSession();
        ilarkesto.di.Context context = ilarkesto.di.Context.get();
        context.setName("gwt-srv:ping");
        context.bindCurrentThread();
        try {
            onPing(session);
        } catch (Throwable t) {
            handleServiceMethodException("ping",t);
            throw new RuntimeException(t);
        }
        scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) session.popNextData();
        onServiceMethodExecuted(context);
        return ret;
    }


    public scrum.client.DataTransferObject login(java.lang.String username, java.lang.String password) {
        LOG.debug("login");
        WebSession session = (WebSession) getSession();
        ilarkesto.di.Context context = ilarkesto.di.Context.get();
        context.setName("gwt-srv:login");
        context.bindCurrentThread();
        try {
            onLogin(session, username, password);
        } catch (Throwable t) {
            handleServiceMethodException("login",t);
            throw new RuntimeException(t);
        }
        scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) session.popNextData();
        onServiceMethodExecuted(context);
        return ret;
    }


    public scrum.client.DataTransferObject logout() {
        LOG.debug("logout");
        WebSession session = (WebSession) getSession();
        ilarkesto.di.Context context = ilarkesto.di.Context.get();
        context.setName("gwt-srv:logout");
        context.bindCurrentThread();
        try {
            onLogout(session);
        } catch (Throwable t) {
            handleServiceMethodException("logout",t);
            throw new RuntimeException(t);
        }
        scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) session.popNextData();
        onServiceMethodExecuted(context);
        return ret;
    }


    public scrum.client.DataTransferObject changePassword(java.lang.String oldPassword, java.lang.String newPassword) {
        LOG.debug("changePassword");
        WebSession session = (WebSession) getSession();
        ilarkesto.di.Context context = ilarkesto.di.Context.get();
        context.setName("gwt-srv:changePassword");
        context.bindCurrentThread();
        try {
            onChangePassword(session, oldPassword, newPassword);
        } catch (Throwable t) {
            handleServiceMethodException("changePassword",t);
            throw new RuntimeException(t);
        }
        scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) session.popNextData();
        onServiceMethodExecuted(context);
        return ret;
    }


    public scrum.client.DataTransferObject resetPassword(java.lang.String userId) {
        LOG.debug("resetPassword");
        WebSession session = (WebSession) getSession();
        ilarkesto.di.Context context = ilarkesto.di.Context.get();
        context.setName("gwt-srv:resetPassword");
        context.bindCurrentThread();
        try {
            onResetPassword(session, userId);
        } catch (Throwable t) {
            handleServiceMethodException("resetPassword",t);
            throw new RuntimeException(t);
        }
        scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) session.popNextData();
        onServiceMethodExecuted(context);
        return ret;
    }


    public scrum.client.DataTransferObject selectProject(java.lang.String projectId) {
        LOG.debug("selectProject");
        WebSession session = (WebSession) getSession();
        ilarkesto.di.Context context = ilarkesto.di.Context.get();
        context.setName("gwt-srv:selectProject");
        context.bindCurrentThread();
        try {
            onSelectProject(session, projectId);
        } catch (Throwable t) {
            handleServiceMethodException("selectProject",t);
            throw new RuntimeException(t);
        }
        scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) session.popNextData();
        onServiceMethodExecuted(context);
        return ret;
    }


    public scrum.client.DataTransferObject closeProject() {
        LOG.debug("closeProject");
        WebSession session = (WebSession) getSession();
        ilarkesto.di.Context context = ilarkesto.di.Context.get();
        context.setName("gwt-srv:closeProject");
        context.bindCurrentThread();
        try {
            onCloseProject(session);
        } catch (Throwable t) {
            handleServiceMethodException("closeProject",t);
            throw new RuntimeException(t);
        }
        scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) session.popNextData();
        onServiceMethodExecuted(context);
        return ret;
    }


    public scrum.client.DataTransferObject switchToNextSprint() {
        LOG.debug("switchToNextSprint");
        WebSession session = (WebSession) getSession();
        ilarkesto.di.Context context = ilarkesto.di.Context.get();
        context.setName("gwt-srv:switchToNextSprint");
        context.bindCurrentThread();
        try {
            onSwitchToNextSprint(session);
        } catch (Throwable t) {
            handleServiceMethodException("switchToNextSprint",t);
            throw new RuntimeException(t);
        }
        scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) session.popNextData();
        onServiceMethodExecuted(context);
        return ret;
    }


    public scrum.client.DataTransferObject requestImpediments() {
        LOG.debug("requestImpediments");
        WebSession session = (WebSession) getSession();
        ilarkesto.di.Context context = ilarkesto.di.Context.get();
        context.setName("gwt-srv:requestImpediments");
        context.bindCurrentThread();
        try {
            onRequestImpediments(session);
        } catch (Throwable t) {
            handleServiceMethodException("requestImpediments",t);
            throw new RuntimeException(t);
        }
        scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) session.popNextData();
        onServiceMethodExecuted(context);
        return ret;
    }


    public scrum.client.DataTransferObject requestRisks() {
        LOG.debug("requestRisks");
        WebSession session = (WebSession) getSession();
        ilarkesto.di.Context context = ilarkesto.di.Context.get();
        context.setName("gwt-srv:requestRisks");
        context.bindCurrentThread();
        try {
            onRequestRisks(session);
        } catch (Throwable t) {
            handleServiceMethodException("requestRisks",t);
            throw new RuntimeException(t);
        }
        scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) session.popNextData();
        onServiceMethodExecuted(context);
        return ret;
    }


    public scrum.client.DataTransferObject requestRequirements() {
        LOG.debug("requestRequirements");
        WebSession session = (WebSession) getSession();
        ilarkesto.di.Context context = ilarkesto.di.Context.get();
        context.setName("gwt-srv:requestRequirements");
        context.bindCurrentThread();
        try {
            onRequestRequirements(session);
        } catch (Throwable t) {
            handleServiceMethodException("requestRequirements",t);
            throw new RuntimeException(t);
        }
        scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) session.popNextData();
        onServiceMethodExecuted(context);
        return ret;
    }


    public scrum.client.DataTransferObject requestQualitys() {
        LOG.debug("requestQualitys");
        WebSession session = (WebSession) getSession();
        ilarkesto.di.Context context = ilarkesto.di.Context.get();
        context.setName("gwt-srv:requestQualitys");
        context.bindCurrentThread();
        try {
            onRequestQualitys(session);
        } catch (Throwable t) {
            handleServiceMethodException("requestQualitys",t);
            throw new RuntimeException(t);
        }
        scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) session.popNextData();
        onServiceMethodExecuted(context);
        return ret;
    }


    public scrum.client.DataTransferObject changeProperties(java.lang.String entityId, java.util.Map properties) {
        LOG.debug("changeProperties");
        WebSession session = (WebSession) getSession();
        ilarkesto.di.Context context = ilarkesto.di.Context.get();
        context.setName("gwt-srv:changeProperties");
        context.bindCurrentThread();
        try {
            onChangeProperties(session, entityId, properties);
        } catch (Throwable t) {
            handleServiceMethodException("changeProperties",t);
            throw new RuntimeException(t);
        }
        scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) session.popNextData();
        onServiceMethodExecuted(context);
        return ret;
    }


    public scrum.client.DataTransferObject createEntity(java.lang.String type, java.util.Map properties) {
        LOG.debug("createEntity");
        WebSession session = (WebSession) getSession();
        ilarkesto.di.Context context = ilarkesto.di.Context.get();
        context.setName("gwt-srv:createEntity");
        context.bindCurrentThread();
        try {
            onCreateEntity(session, type, properties);
        } catch (Throwable t) {
            handleServiceMethodException("createEntity",t);
            throw new RuntimeException(t);
        }
        scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) session.popNextData();
        onServiceMethodExecuted(context);
        return ret;
    }


    public scrum.client.DataTransferObject deleteEntity(java.lang.String entityId) {
        LOG.debug("deleteEntity");
        WebSession session = (WebSession) getSession();
        ilarkesto.di.Context context = ilarkesto.di.Context.get();
        context.setName("gwt-srv:deleteEntity");
        context.bindCurrentThread();
        try {
            onDeleteEntity(session, entityId);
        } catch (Throwable t) {
            handleServiceMethodException("deleteEntity",t);
            throw new RuntimeException(t);
        }
        scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) session.popNextData();
        onServiceMethodExecuted(context);
        return ret;
    }


    public scrum.client.DataTransferObject requestEntityByReference(java.lang.String reference) {
        LOG.debug("requestEntityByReference");
        WebSession session = (WebSession) getSession();
        ilarkesto.di.Context context = ilarkesto.di.Context.get();
        context.setName("gwt-srv:requestEntityByReference");
        context.bindCurrentThread();
        try {
            onRequestEntityByReference(session, reference);
        } catch (Throwable t) {
            handleServiceMethodException("requestEntityByReference",t);
            throw new RuntimeException(t);
        }
        scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) session.popNextData();
        onServiceMethodExecuted(context);
        return ret;
    }


    public scrum.client.DataTransferObject sleep(long millis) {
        LOG.debug("sleep");
        WebSession session = (WebSession) getSession();
        ilarkesto.di.Context context = ilarkesto.di.Context.get();
        context.setName("gwt-srv:sleep");
        context.bindCurrentThread();
        try {
            onSleep(session, millis);
        } catch (Throwable t) {
            handleServiceMethodException("sleep",t);
            throw new RuntimeException(t);
        }
        scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) session.popNextData();
        onServiceMethodExecuted(context);
        return ret;
    }

}