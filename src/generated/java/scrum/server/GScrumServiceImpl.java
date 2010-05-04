// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.GwtServiceImplementationGenerator










package scrum.server;

import java.util.*;
import ilarkesto.core.logging.Log;

public abstract class GScrumServiceImpl
            extends ilarkesto.gwt.server.AGwtServiceImpl
            implements scrum.client.ScrumService {

    private static final ilarkesto.core.logging.Log LOG = ilarkesto.core.logging.Log.get(GScrumServiceImpl.class);

    protected abstract void onPing(GwtConversation conversation);
    protected abstract void onLogin(GwtConversation conversation, java.lang.String username, java.lang.String password);
    protected abstract void onLogout(GwtConversation conversation);
    protected abstract void onChangePassword(GwtConversation conversation, java.lang.String oldPassword, java.lang.String newPassword);
    protected abstract void onResetPassword(GwtConversation conversation, java.lang.String userId);
    protected abstract void onSelectProject(GwtConversation conversation, java.lang.String projectId);
    protected abstract void onCloseProject(GwtConversation conversation);
    protected abstract void onSwitchToNextSprint(GwtConversation conversation);
    protected abstract void onRequestImpediments(GwtConversation conversation);
    protected abstract void onRequestAcceptedIssues(GwtConversation conversation);
    protected abstract void onRequestClosedIssues(GwtConversation conversation);
    protected abstract void onRequestReleaseIssues(GwtConversation conversation, java.lang.String releaseId);
    protected abstract void onRequestRisks(GwtConversation conversation);
    protected abstract void onRequestRequirementEstimationVotes(GwtConversation conversation, java.lang.String requirementId);
    protected abstract void onRequestComments(GwtConversation conversation, java.lang.String parentId);
    protected abstract void onRequestChanges(GwtConversation conversation, java.lang.String parentId);
    protected abstract void onChangeProperties(GwtConversation conversation, java.lang.String entityId, java.util.Map properties);
    protected abstract void onCreateEntity(GwtConversation conversation, java.lang.String type, java.util.Map properties);
    protected abstract void onDeleteEntity(GwtConversation conversation, java.lang.String entityId);
    protected abstract void onRequestEntityByReference(GwtConversation conversation, java.lang.String reference);
    protected abstract void onRequestEntity(GwtConversation conversation, java.lang.String entityId);
    protected abstract void onSetSelectedEntitysIds(GwtConversation conversation, java.util.Set ids);
    protected abstract void onSleep(GwtConversation conversation, long millis);
    protected abstract void onUpdateSystemMessage(GwtConversation conversation, scrum.client.admin.SystemMessage systemMessage);
    protected abstract void onSearch(GwtConversation conversation, java.lang.String text);
    protected abstract void onActivateRequirementEstimationVoting(GwtConversation conversation, java.lang.String requirementId);
    protected abstract void onRequestForum(GwtConversation conversation);


    public scrum.client.DataTransferObject ping(int conversationNumber) {
        WebSession session = (WebSession) getSession();
        synchronized(session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:ping");
            context.bindCurrentThread();
            try {
                onPing(conversation);
            } catch (Throwable t) {
                handleServiceMethodException(conversationNumber, "ping",t);
                throw new RuntimeException(t);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }


    public scrum.client.DataTransferObject login(int conversationNumber, java.lang.String username, java.lang.String password) {
        LOG.debug("login");
        WebSession session = (WebSession) getSession();
        synchronized(session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:login");
            context.bindCurrentThread();
            try {
                onLogin(conversation, username, password);
            } catch (Throwable t) {
                handleServiceMethodException(conversationNumber, "login",t);
                throw new RuntimeException(t);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }


    public scrum.client.DataTransferObject logout(int conversationNumber) {
        LOG.debug("logout");
        WebSession session = (WebSession) getSession();
        synchronized(session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:logout");
            context.bindCurrentThread();
            try {
                onLogout(conversation);
            } catch (Throwable t) {
                handleServiceMethodException(conversationNumber, "logout",t);
                throw new RuntimeException(t);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }


    public scrum.client.DataTransferObject changePassword(int conversationNumber, java.lang.String oldPassword, java.lang.String newPassword) {
        LOG.debug("changePassword");
        WebSession session = (WebSession) getSession();
        synchronized(session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:changePassword");
            context.bindCurrentThread();
            try {
                onChangePassword(conversation, oldPassword, newPassword);
            } catch (Throwable t) {
                handleServiceMethodException(conversationNumber, "changePassword",t);
                throw new RuntimeException(t);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }


    public scrum.client.DataTransferObject resetPassword(int conversationNumber, java.lang.String userId) {
        LOG.debug("resetPassword");
        WebSession session = (WebSession) getSession();
        synchronized(session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:resetPassword");
            context.bindCurrentThread();
            try {
                onResetPassword(conversation, userId);
            } catch (Throwable t) {
                handleServiceMethodException(conversationNumber, "resetPassword",t);
                throw new RuntimeException(t);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }


    public scrum.client.DataTransferObject selectProject(int conversationNumber, java.lang.String projectId) {
        LOG.debug("selectProject");
        WebSession session = (WebSession) getSession();
        synchronized(session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:selectProject");
            context.bindCurrentThread();
            try {
                onSelectProject(conversation, projectId);
            } catch (Throwable t) {
                handleServiceMethodException(conversationNumber, "selectProject",t);
                throw new RuntimeException(t);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }


    public scrum.client.DataTransferObject closeProject(int conversationNumber) {
        LOG.debug("closeProject");
        WebSession session = (WebSession) getSession();
        synchronized(session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:closeProject");
            context.bindCurrentThread();
            try {
                onCloseProject(conversation);
            } catch (Throwable t) {
                handleServiceMethodException(conversationNumber, "closeProject",t);
                throw new RuntimeException(t);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }


    public scrum.client.DataTransferObject switchToNextSprint(int conversationNumber) {
        LOG.debug("switchToNextSprint");
        WebSession session = (WebSession) getSession();
        synchronized(session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:switchToNextSprint");
            context.bindCurrentThread();
            try {
                onSwitchToNextSprint(conversation);
            } catch (Throwable t) {
                handleServiceMethodException(conversationNumber, "switchToNextSprint",t);
                throw new RuntimeException(t);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }


    public scrum.client.DataTransferObject requestImpediments(int conversationNumber) {
        LOG.debug("requestImpediments");
        WebSession session = (WebSession) getSession();
        synchronized(session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:requestImpediments");
            context.bindCurrentThread();
            try {
                onRequestImpediments(conversation);
            } catch (Throwable t) {
                handleServiceMethodException(conversationNumber, "requestImpediments",t);
                throw new RuntimeException(t);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }


    public scrum.client.DataTransferObject requestAcceptedIssues(int conversationNumber) {
        LOG.debug("requestAcceptedIssues");
        WebSession session = (WebSession) getSession();
        synchronized(session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:requestAcceptedIssues");
            context.bindCurrentThread();
            try {
                onRequestAcceptedIssues(conversation);
            } catch (Throwable t) {
                handleServiceMethodException(conversationNumber, "requestAcceptedIssues",t);
                throw new RuntimeException(t);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }


    public scrum.client.DataTransferObject requestClosedIssues(int conversationNumber) {
        LOG.debug("requestClosedIssues");
        WebSession session = (WebSession) getSession();
        synchronized(session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:requestClosedIssues");
            context.bindCurrentThread();
            try {
                onRequestClosedIssues(conversation);
            } catch (Throwable t) {
                handleServiceMethodException(conversationNumber, "requestClosedIssues",t);
                throw new RuntimeException(t);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }


    public scrum.client.DataTransferObject requestReleaseIssues(int conversationNumber, java.lang.String releaseId) {
        LOG.debug("requestReleaseIssues");
        WebSession session = (WebSession) getSession();
        synchronized(session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:requestReleaseIssues");
            context.bindCurrentThread();
            try {
                onRequestReleaseIssues(conversation, releaseId);
            } catch (Throwable t) {
                handleServiceMethodException(conversationNumber, "requestReleaseIssues",t);
                throw new RuntimeException(t);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }


    public scrum.client.DataTransferObject requestRisks(int conversationNumber) {
        LOG.debug("requestRisks");
        WebSession session = (WebSession) getSession();
        synchronized(session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:requestRisks");
            context.bindCurrentThread();
            try {
                onRequestRisks(conversation);
            } catch (Throwable t) {
                handleServiceMethodException(conversationNumber, "requestRisks",t);
                throw new RuntimeException(t);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }


    public scrum.client.DataTransferObject requestRequirementEstimationVotes(int conversationNumber, java.lang.String requirementId) {
        LOG.debug("requestRequirementEstimationVotes");
        WebSession session = (WebSession) getSession();
        synchronized(session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:requestRequirementEstimationVotes");
            context.bindCurrentThread();
            try {
                onRequestRequirementEstimationVotes(conversation, requirementId);
            } catch (Throwable t) {
                handleServiceMethodException(conversationNumber, "requestRequirementEstimationVotes",t);
                throw new RuntimeException(t);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }


    public scrum.client.DataTransferObject requestComments(int conversationNumber, java.lang.String parentId) {
        LOG.debug("requestComments");
        WebSession session = (WebSession) getSession();
        synchronized(session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:requestComments");
            context.bindCurrentThread();
            try {
                onRequestComments(conversation, parentId);
            } catch (Throwable t) {
                handleServiceMethodException(conversationNumber, "requestComments",t);
                throw new RuntimeException(t);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }


    public scrum.client.DataTransferObject requestChanges(int conversationNumber, java.lang.String parentId) {
        LOG.debug("requestChanges");
        WebSession session = (WebSession) getSession();
        synchronized(session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:requestChanges");
            context.bindCurrentThread();
            try {
                onRequestChanges(conversation, parentId);
            } catch (Throwable t) {
                handleServiceMethodException(conversationNumber, "requestChanges",t);
                throw new RuntimeException(t);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }


    public scrum.client.DataTransferObject changeProperties(int conversationNumber, java.lang.String entityId, java.util.Map properties) {
        LOG.debug("changeProperties");
        WebSession session = (WebSession) getSession();
        synchronized(session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:changeProperties");
            context.bindCurrentThread();
            try {
                onChangeProperties(conversation, entityId, properties);
            } catch (Throwable t) {
                handleServiceMethodException(conversationNumber, "changeProperties",t);
                throw new RuntimeException(t);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }


    public scrum.client.DataTransferObject createEntity(int conversationNumber, java.lang.String type, java.util.Map properties) {
        LOG.debug("createEntity");
        WebSession session = (WebSession) getSession();
        synchronized(session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:createEntity");
            context.bindCurrentThread();
            try {
                onCreateEntity(conversation, type, properties);
            } catch (Throwable t) {
                handleServiceMethodException(conversationNumber, "createEntity",t);
                throw new RuntimeException(t);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }


    public scrum.client.DataTransferObject deleteEntity(int conversationNumber, java.lang.String entityId) {
        LOG.debug("deleteEntity");
        WebSession session = (WebSession) getSession();
        synchronized(session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:deleteEntity");
            context.bindCurrentThread();
            try {
                onDeleteEntity(conversation, entityId);
            } catch (Throwable t) {
                handleServiceMethodException(conversationNumber, "deleteEntity",t);
                throw new RuntimeException(t);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }


    public scrum.client.DataTransferObject requestEntityByReference(int conversationNumber, java.lang.String reference) {
        LOG.debug("requestEntityByReference");
        WebSession session = (WebSession) getSession();
        synchronized(session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:requestEntityByReference");
            context.bindCurrentThread();
            try {
                onRequestEntityByReference(conversation, reference);
            } catch (Throwable t) {
                handleServiceMethodException(conversationNumber, "requestEntityByReference",t);
                throw new RuntimeException(t);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }


    public scrum.client.DataTransferObject requestEntity(int conversationNumber, java.lang.String entityId) {
        LOG.debug("requestEntity");
        WebSession session = (WebSession) getSession();
        synchronized(session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:requestEntity");
            context.bindCurrentThread();
            try {
                onRequestEntity(conversation, entityId);
            } catch (Throwable t) {
                handleServiceMethodException(conversationNumber, "requestEntity",t);
                throw new RuntimeException(t);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }


    public scrum.client.DataTransferObject setSelectedEntitysIds(int conversationNumber, java.util.Set ids) {
        LOG.debug("setSelectedEntitysIds");
        WebSession session = (WebSession) getSession();
        synchronized(session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:setSelectedEntitysIds");
            context.bindCurrentThread();
            try {
                onSetSelectedEntitysIds(conversation, ids);
            } catch (Throwable t) {
                handleServiceMethodException(conversationNumber, "setSelectedEntitysIds",t);
                throw new RuntimeException(t);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }


    public scrum.client.DataTransferObject sleep(int conversationNumber, long millis) {
        LOG.debug("sleep");
        WebSession session = (WebSession) getSession();
        synchronized(session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:sleep");
            context.bindCurrentThread();
            try {
                onSleep(conversation, millis);
            } catch (Throwable t) {
                handleServiceMethodException(conversationNumber, "sleep",t);
                throw new RuntimeException(t);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }


    public scrum.client.DataTransferObject updateSystemMessage(int conversationNumber, scrum.client.admin.SystemMessage systemMessage) {
        LOG.debug("updateSystemMessage");
        WebSession session = (WebSession) getSession();
        synchronized(session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:updateSystemMessage");
            context.bindCurrentThread();
            try {
                onUpdateSystemMessage(conversation, systemMessage);
            } catch (Throwable t) {
                handleServiceMethodException(conversationNumber, "updateSystemMessage",t);
                throw new RuntimeException(t);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }


    public scrum.client.DataTransferObject search(int conversationNumber, java.lang.String text) {
        LOG.debug("search");
        WebSession session = (WebSession) getSession();
        synchronized(session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:search");
            context.bindCurrentThread();
            try {
                onSearch(conversation, text);
            } catch (Throwable t) {
                handleServiceMethodException(conversationNumber, "search",t);
                throw new RuntimeException(t);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }


    public scrum.client.DataTransferObject activateRequirementEstimationVoting(int conversationNumber, java.lang.String requirementId) {
        LOG.debug("activateRequirementEstimationVoting");
        WebSession session = (WebSession) getSession();
        synchronized(session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:activateRequirementEstimationVoting");
            context.bindCurrentThread();
            try {
                onActivateRequirementEstimationVoting(conversation, requirementId);
            } catch (Throwable t) {
                handleServiceMethodException(conversationNumber, "activateRequirementEstimationVoting",t);
                throw new RuntimeException(t);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }


    public scrum.client.DataTransferObject requestForum(int conversationNumber) {
        LOG.debug("requestForum");
        WebSession session = (WebSession) getSession();
        synchronized(session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:requestForum");
            context.bindCurrentThread();
            try {
                onRequestForum(conversation);
            } catch (Throwable t) {
                handleServiceMethodException(conversationNumber, "requestForum",t);
                throw new RuntimeException(t);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }

}