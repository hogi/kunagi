// // ----------> GENERATED FILE - DON'T TOUCH! <----------

package scrum.server;

public abstract class GScrumServiceImpl extends ilarkesto.gwt.server.AGwtServiceImpl implements scrum.client.ScrumService {

    protected static ilarkesto.core.logging.Log log = ilarkesto.core.logging.Log.get(ScrumServiceImpl.class);

    public abstract void onChangePassword(GwtConversation conversation, String newPassword, String oldPassword);

    public abstract void onLogout(GwtConversation conversation);

    public abstract void onResetPassword(GwtConversation conversation, String userId);

    public abstract void onSendTestEmail(GwtConversation conversation);

    public abstract void onUpdateSystemMessage(GwtConversation conversation, scrum.client.admin.SystemMessage systemMessage);

    public abstract void onRequestComments(GwtConversation conversation, String parentId);

    public abstract void onRequestForum(GwtConversation conversation);

    public abstract void onSetSelectedEntitysIds(GwtConversation conversation, java.util.Set ids);

    public abstract void onPing(GwtConversation conversation);

    public abstract void onChangeProperties(GwtConversation conversation, String entityId, java.util.Map properties);

    public abstract void onCreateEntity(GwtConversation conversation, String type, java.util.Map properties);

    public abstract void onDeleteEntity(GwtConversation conversation, String entityId);

    public abstract void onRequestEntity(GwtConversation conversation, String entityId);

    public abstract void onRequestEntityByReference(GwtConversation conversation, String reference);

    public abstract void onSleep(GwtConversation conversation, long millis);

    public abstract void onActivateRequirementEstimationVoting(GwtConversation conversation, String requirementId);

    public abstract void onRequestRequirementEstimationVotes(GwtConversation conversation, String requirementId);

    public abstract void onRequestImpediments(GwtConversation conversation);

    public abstract void onConvertIssueToStory(GwtConversation conversation, String issueId);

    public abstract void onRequestAcceptedIssues(GwtConversation conversation);

    public abstract void onRequestClosedIssues(GwtConversation conversation);

    public abstract void onRequestReleaseIssues(GwtConversation conversation, String releaseId);

    public abstract void onRequestChanges(GwtConversation conversation, String parentId);

    public abstract void onCloseProject(GwtConversation conversation);

    public abstract void onCreateExampleProject(GwtConversation conversation);

    public abstract void onSelectProject(GwtConversation conversation, String projectId);

    public abstract void onUpdateProjectHomepage(GwtConversation conversation);

    public abstract void onRequestRisks(GwtConversation conversation);

    public abstract void onSearch(GwtConversation conversation, String text);

    public abstract void onSwitchToNextSprint(GwtConversation conversation);

    public scrum.client.DataTransferObject changePassword(int conversationNumber, String newPassword, String oldPassword) {
        log.debug("Handling service call: ChangePassword");
        WebSession session = (WebSession) getSession();
        synchronized (session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:ChangePassword");
            context.bindCurrentThread();
            try {
                onChangePassword(conversation, newPassword, oldPassword);
            } catch (Throwable ex) {
                handleServiceMethodException(conversationNumber, "ChangePassword", ex);
                throw new RuntimeException(ex);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }

    public scrum.client.DataTransferObject logout(int conversationNumber) {
        log.debug("Handling service call: Logout");
        WebSession session = (WebSession) getSession();
        synchronized (session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:Logout");
            context.bindCurrentThread();
            try {
                onLogout(conversation);
            } catch (Throwable ex) {
                handleServiceMethodException(conversationNumber, "Logout", ex);
                throw new RuntimeException(ex);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }

    public scrum.client.DataTransferObject resetPassword(int conversationNumber, String userId) {
        log.debug("Handling service call: ResetPassword");
        WebSession session = (WebSession) getSession();
        synchronized (session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:ResetPassword");
            context.bindCurrentThread();
            try {
                onResetPassword(conversation, userId);
            } catch (Throwable ex) {
                handleServiceMethodException(conversationNumber, "ResetPassword", ex);
                throw new RuntimeException(ex);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }

    public scrum.client.DataTransferObject sendTestEmail(int conversationNumber) {
        log.debug("Handling service call: SendTestEmail");
        WebSession session = (WebSession) getSession();
        synchronized (session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:SendTestEmail");
            context.bindCurrentThread();
            try {
                onSendTestEmail(conversation);
            } catch (Throwable ex) {
                handleServiceMethodException(conversationNumber, "SendTestEmail", ex);
                throw new RuntimeException(ex);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }

    public scrum.client.DataTransferObject updateSystemMessage(int conversationNumber, scrum.client.admin.SystemMessage systemMessage) {
        log.debug("Handling service call: UpdateSystemMessage");
        WebSession session = (WebSession) getSession();
        synchronized (session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:UpdateSystemMessage");
            context.bindCurrentThread();
            try {
                onUpdateSystemMessage(conversation, systemMessage);
            } catch (Throwable ex) {
                handleServiceMethodException(conversationNumber, "UpdateSystemMessage", ex);
                throw new RuntimeException(ex);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }

    public scrum.client.DataTransferObject requestComments(int conversationNumber, String parentId) {
        log.debug("Handling service call: RequestComments");
        WebSession session = (WebSession) getSession();
        synchronized (session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:RequestComments");
            context.bindCurrentThread();
            try {
                onRequestComments(conversation, parentId);
            } catch (Throwable ex) {
                handleServiceMethodException(conversationNumber, "RequestComments", ex);
                throw new RuntimeException(ex);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }

    public scrum.client.DataTransferObject requestForum(int conversationNumber) {
        log.debug("Handling service call: RequestForum");
        WebSession session = (WebSession) getSession();
        synchronized (session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:RequestForum");
            context.bindCurrentThread();
            try {
                onRequestForum(conversation);
            } catch (Throwable ex) {
                handleServiceMethodException(conversationNumber, "RequestForum", ex);
                throw new RuntimeException(ex);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }

    public scrum.client.DataTransferObject setSelectedEntitysIds(int conversationNumber, java.util.Set ids) {
        log.debug("Handling service call: SetSelectedEntitysIds");
        WebSession session = (WebSession) getSession();
        synchronized (session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:SetSelectedEntitysIds");
            context.bindCurrentThread();
            try {
                onSetSelectedEntitysIds(conversation, ids);
            } catch (Throwable ex) {
                handleServiceMethodException(conversationNumber, "SetSelectedEntitysIds", ex);
                throw new RuntimeException(ex);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }

    public scrum.client.DataTransferObject ping(int conversationNumber) {
        WebSession session = (WebSession) getSession();
        synchronized (session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:Ping");
            context.bindCurrentThread();
            try {
                onPing(conversation);
            } catch (Throwable ex) {
                handleServiceMethodException(conversationNumber, "Ping", ex);
                throw new RuntimeException(ex);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }

    public scrum.client.DataTransferObject changeProperties(int conversationNumber, String entityId, java.util.Map properties) {
        log.debug("Handling service call: ChangeProperties");
        WebSession session = (WebSession) getSession();
        synchronized (session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:ChangeProperties");
            context.bindCurrentThread();
            try {
                onChangeProperties(conversation, entityId, properties);
            } catch (Throwable ex) {
                handleServiceMethodException(conversationNumber, "ChangeProperties", ex);
                throw new RuntimeException(ex);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }

    public scrum.client.DataTransferObject createEntity(int conversationNumber, String type, java.util.Map properties) {
        log.debug("Handling service call: CreateEntity");
        WebSession session = (WebSession) getSession();
        synchronized (session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:CreateEntity");
            context.bindCurrentThread();
            try {
                onCreateEntity(conversation, type, properties);
            } catch (Throwable ex) {
                handleServiceMethodException(conversationNumber, "CreateEntity", ex);
                throw new RuntimeException(ex);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }

    public scrum.client.DataTransferObject deleteEntity(int conversationNumber, String entityId) {
        log.debug("Handling service call: DeleteEntity");
        WebSession session = (WebSession) getSession();
        synchronized (session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:DeleteEntity");
            context.bindCurrentThread();
            try {
                onDeleteEntity(conversation, entityId);
            } catch (Throwable ex) {
                handleServiceMethodException(conversationNumber, "DeleteEntity", ex);
                throw new RuntimeException(ex);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }

    public scrum.client.DataTransferObject requestEntity(int conversationNumber, String entityId) {
        log.debug("Handling service call: RequestEntity");
        WebSession session = (WebSession) getSession();
        synchronized (session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:RequestEntity");
            context.bindCurrentThread();
            try {
                onRequestEntity(conversation, entityId);
            } catch (Throwable ex) {
                handleServiceMethodException(conversationNumber, "RequestEntity", ex);
                throw new RuntimeException(ex);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }

    public scrum.client.DataTransferObject requestEntityByReference(int conversationNumber, String reference) {
        log.debug("Handling service call: RequestEntityByReference");
        WebSession session = (WebSession) getSession();
        synchronized (session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:RequestEntityByReference");
            context.bindCurrentThread();
            try {
                onRequestEntityByReference(conversation, reference);
            } catch (Throwable ex) {
                handleServiceMethodException(conversationNumber, "RequestEntityByReference", ex);
                throw new RuntimeException(ex);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }

    public scrum.client.DataTransferObject sleep(int conversationNumber, long millis) {
        log.debug("Handling service call: Sleep");
        WebSession session = (WebSession) getSession();
        synchronized (session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:Sleep");
            context.bindCurrentThread();
            try {
                onSleep(conversation, millis);
            } catch (Throwable ex) {
                handleServiceMethodException(conversationNumber, "Sleep", ex);
                throw new RuntimeException(ex);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }

    public scrum.client.DataTransferObject activateRequirementEstimationVoting(int conversationNumber, String requirementId) {
        log.debug("Handling service call: ActivateRequirementEstimationVoting");
        WebSession session = (WebSession) getSession();
        synchronized (session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:ActivateRequirementEstimationVoting");
            context.bindCurrentThread();
            try {
                onActivateRequirementEstimationVoting(conversation, requirementId);
            } catch (Throwable ex) {
                handleServiceMethodException(conversationNumber, "ActivateRequirementEstimationVoting", ex);
                throw new RuntimeException(ex);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }

    public scrum.client.DataTransferObject requestRequirementEstimationVotes(int conversationNumber, String requirementId) {
        log.debug("Handling service call: RequestRequirementEstimationVotes");
        WebSession session = (WebSession) getSession();
        synchronized (session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:RequestRequirementEstimationVotes");
            context.bindCurrentThread();
            try {
                onRequestRequirementEstimationVotes(conversation, requirementId);
            } catch (Throwable ex) {
                handleServiceMethodException(conversationNumber, "RequestRequirementEstimationVotes", ex);
                throw new RuntimeException(ex);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }

    public scrum.client.DataTransferObject requestImpediments(int conversationNumber) {
        log.debug("Handling service call: RequestImpediments");
        WebSession session = (WebSession) getSession();
        synchronized (session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:RequestImpediments");
            context.bindCurrentThread();
            try {
                onRequestImpediments(conversation);
            } catch (Throwable ex) {
                handleServiceMethodException(conversationNumber, "RequestImpediments", ex);
                throw new RuntimeException(ex);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }

    public scrum.client.DataTransferObject convertIssueToStory(int conversationNumber, String issueId) {
        log.debug("Handling service call: ConvertIssueToStory");
        WebSession session = (WebSession) getSession();
        synchronized (session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:ConvertIssueToStory");
            context.bindCurrentThread();
            try {
                onConvertIssueToStory(conversation, issueId);
            } catch (Throwable ex) {
                handleServiceMethodException(conversationNumber, "ConvertIssueToStory", ex);
                throw new RuntimeException(ex);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }

    public scrum.client.DataTransferObject requestAcceptedIssues(int conversationNumber) {
        log.debug("Handling service call: RequestAcceptedIssues");
        WebSession session = (WebSession) getSession();
        synchronized (session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:RequestAcceptedIssues");
            context.bindCurrentThread();
            try {
                onRequestAcceptedIssues(conversation);
            } catch (Throwable ex) {
                handleServiceMethodException(conversationNumber, "RequestAcceptedIssues", ex);
                throw new RuntimeException(ex);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }

    public scrum.client.DataTransferObject requestClosedIssues(int conversationNumber) {
        log.debug("Handling service call: RequestClosedIssues");
        WebSession session = (WebSession) getSession();
        synchronized (session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:RequestClosedIssues");
            context.bindCurrentThread();
            try {
                onRequestClosedIssues(conversation);
            } catch (Throwable ex) {
                handleServiceMethodException(conversationNumber, "RequestClosedIssues", ex);
                throw new RuntimeException(ex);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }

    public scrum.client.DataTransferObject requestReleaseIssues(int conversationNumber, String releaseId) {
        log.debug("Handling service call: RequestReleaseIssues");
        WebSession session = (WebSession) getSession();
        synchronized (session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:RequestReleaseIssues");
            context.bindCurrentThread();
            try {
                onRequestReleaseIssues(conversation, releaseId);
            } catch (Throwable ex) {
                handleServiceMethodException(conversationNumber, "RequestReleaseIssues", ex);
                throw new RuntimeException(ex);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }

    public scrum.client.DataTransferObject requestChanges(int conversationNumber, String parentId) {
        log.debug("Handling service call: RequestChanges");
        WebSession session = (WebSession) getSession();
        synchronized (session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:RequestChanges");
            context.bindCurrentThread();
            try {
                onRequestChanges(conversation, parentId);
            } catch (Throwable ex) {
                handleServiceMethodException(conversationNumber, "RequestChanges", ex);
                throw new RuntimeException(ex);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }

    public scrum.client.DataTransferObject closeProject(int conversationNumber) {
        log.debug("Handling service call: CloseProject");
        WebSession session = (WebSession) getSession();
        synchronized (session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:CloseProject");
            context.bindCurrentThread();
            try {
                onCloseProject(conversation);
            } catch (Throwable ex) {
                handleServiceMethodException(conversationNumber, "CloseProject", ex);
                throw new RuntimeException(ex);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }

    public scrum.client.DataTransferObject createExampleProject(int conversationNumber) {
        log.debug("Handling service call: CreateExampleProject");
        WebSession session = (WebSession) getSession();
        synchronized (session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:CreateExampleProject");
            context.bindCurrentThread();
            try {
                onCreateExampleProject(conversation);
            } catch (Throwable ex) {
                handleServiceMethodException(conversationNumber, "CreateExampleProject", ex);
                throw new RuntimeException(ex);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }

    public scrum.client.DataTransferObject selectProject(int conversationNumber, String projectId) {
        log.debug("Handling service call: SelectProject");
        WebSession session = (WebSession) getSession();
        synchronized (session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:SelectProject");
            context.bindCurrentThread();
            try {
                onSelectProject(conversation, projectId);
            } catch (Throwable ex) {
                handleServiceMethodException(conversationNumber, "SelectProject", ex);
                throw new RuntimeException(ex);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }

    public scrum.client.DataTransferObject updateProjectHomepage(int conversationNumber) {
        log.debug("Handling service call: UpdateProjectHomepage");
        WebSession session = (WebSession) getSession();
        synchronized (session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:UpdateProjectHomepage");
            context.bindCurrentThread();
            try {
                onUpdateProjectHomepage(conversation);
            } catch (Throwable ex) {
                handleServiceMethodException(conversationNumber, "UpdateProjectHomepage", ex);
                throw new RuntimeException(ex);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }

    public scrum.client.DataTransferObject requestRisks(int conversationNumber) {
        log.debug("Handling service call: RequestRisks");
        WebSession session = (WebSession) getSession();
        synchronized (session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:RequestRisks");
            context.bindCurrentThread();
            try {
                onRequestRisks(conversation);
            } catch (Throwable ex) {
                handleServiceMethodException(conversationNumber, "RequestRisks", ex);
                throw new RuntimeException(ex);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }

    public scrum.client.DataTransferObject search(int conversationNumber, String text) {
        log.debug("Handling service call: Search");
        WebSession session = (WebSession) getSession();
        synchronized (session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:Search");
            context.bindCurrentThread();
            try {
                onSearch(conversation, text);
            } catch (Throwable ex) {
                handleServiceMethodException(conversationNumber, "Search", ex);
                throw new RuntimeException(ex);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }

    public scrum.client.DataTransferObject switchToNextSprint(int conversationNumber) {
        log.debug("Handling service call: SwitchToNextSprint");
        WebSession session = (WebSession) getSession();
        synchronized (session) {
            GwtConversation conversation = session.getGwtConversation(conversationNumber);
            ilarkesto.di.Context context = ilarkesto.di.Context.get();
            context.setName("gwt-srv:SwitchToNextSprint");
            context.bindCurrentThread();
            try {
                onSwitchToNextSprint(conversation);
            } catch (Throwable ex) {
                handleServiceMethodException(conversationNumber, "SwitchToNextSprint", ex);
                throw new RuntimeException(ex);
            }
            scrum.client.DataTransferObject ret = (scrum.client.DataTransferObject) conversation.popNextData();
            onServiceMethodExecuted(context);
            return ret;
        }
    }

}

