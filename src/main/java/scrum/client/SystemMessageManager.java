package scrum.client;

import scrum.client.admin.SystemMessage;
import scrum.client.common.AScrumComponent;

public class SystemMessageManager extends AScrumComponent implements ServerDataReceivedListener {

	private SystemMessage systemMessage = new SystemMessage();

	public void onServerDataReceived(DataTransferObject data) {
		if (data.systemMessage != null) {
			systemMessage = data.systemMessage;
			cm.getUi().getWorkspace().update();
		}
	}

	public void activateSystemMessage() {
		systemMessage.setActive(true);
		cm.getApp().callUpdateSystemMessage(systemMessage);
		cm.getEventBus().fireVisibleDataChanged();
	}

	public void deactivateSystemMessage() {
		systemMessage.setActive(false);
		cm.getApp().callUpdateSystemMessage(systemMessage);
		cm.getEventBus().fireVisibleDataChanged();
	}

	public SystemMessage getSystemMessage() {
		return systemMessage;
	}

	public void onServerDataReceived(DataTransferObject data) {
		if (data.systemMessage != null) {
			systemMessage = data.systemMessage;
			cm.getEventBus().fireVisibleDataChanged();
		}
	}

}
