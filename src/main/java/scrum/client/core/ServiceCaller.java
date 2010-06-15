package scrum.client.core;

import ilarkesto.core.scope.Scope;
import scrum.client.Dao;
import scrum.client.DataTransferObject;
import scrum.client.ScrumGwtApplication;
import scrum.client.ScrumService;
import scrum.client.ScrumServiceAsync;
import scrum.client.common.AScrumWidget;
import scrum.client.communication.ServerDataReceivedEvent;

public class ServiceCaller extends GServiceCaller {

	private int activeServiceCallCount;
	private AScrumWidget statusWidget;
	private ScrumServiceAsync scrumService;
	protected int conversationNumber = -1;

	public final ScrumServiceAsync getService() {
		if (scrumService == null) {
			scrumService = com.google.gwt.core.client.GWT.create(ScrumService.class);
			((com.google.gwt.user.client.rpc.ServiceDefTarget) scrumService)
					.setServiceEntryPoint(com.google.gwt.core.client.GWT.getModuleBaseURL() + "scrum");
		}
		return scrumService;
	}

	public void onServiceCallSuccess(DataTransferObject data) {
		onServiceCallReturn();

		if (data.conversationNumber != null) {
			conversationNumber = data.conversationNumber;
			log.info("conversatioNumber received:", conversationNumber);
		}
		Scope.get().getComponent(Dao.class).handleDataFromServer(data);

		ScrumGwtApplication app = ScrumGwtApplication.get();
		if (data.applicationInfo != null) {
			app.applicationInfo = data.applicationInfo;
			log.debug("applicationInfo:", data.applicationInfo);
			// Scope.get().putComponent(data.applicationInfo);
		} else {
			assert app.applicationInfo != null;
		}

		new ServerDataReceivedEvent(data).fireInCurrentScope();
	}

	public void onServiceCallFailure(AServiceCall serviceCall, Throwable ex) {
		onServiceCallReturn();
		log.error("Service call failed:", serviceCall);
		ScrumGwtApplication.get().handleCommunicationError(ex);
	}

	public void onServiceCall() {
		activeServiceCallCount++;
		if (statusWidget != null) statusWidget.update();
	}

	public void onServiceCallReturn() {
		activeServiceCallCount--;
		if (activeServiceCallCount < 0) activeServiceCallCount = 0;
		if (statusWidget != null) statusWidget.update();
	}

	public int getActiveServiceCallCount() {
		return activeServiceCallCount;
	}

	public int getConversationNumber() {
		return conversationNumber;
	}

	public void resetConversation() {
		conversationNumber = -1;
	}

	public void setStatusWidget(AScrumWidget statusWidget) {
		this.statusWidget = statusWidget;
	}

}
