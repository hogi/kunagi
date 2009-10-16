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
		cm.getUi().getWorkspace().update();
	}

	public void deactivateSystemMessage() {
		systemMessage.setActive(false);
		cm.getApp().callUpdateSystemMessage(systemMessage);
		cm.getUi().getWorkspace().update();
	}

	public SystemMessage getSystemMessage() {
		return systemMessage;
	}

}