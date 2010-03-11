package scrum.client.admin;

import ilarkesto.gwt.client.DateAndTime;
import ilarkesto.gwt.client.editor.ADateAndTimeEditorModel;
import ilarkesto.gwt.client.editor.ATextEditorModel;
import scrum.client.ComponentManager;
import scrum.client.DataTransferObject;
import scrum.client.ServerDataReceivedListener;

public class SystemMessageManager extends GSystemMessageManager implements ServerDataReceivedListener {

	private ComponentManager cm = ComponentManager.get();
	private SystemMessage systemMessage = new SystemMessage();

	public void onServerDataReceived(DataTransferObject data) {
		if (data.systemMessage != null) {
			systemMessage = data.systemMessage;
			log.info("SystemMessage received:", systemMessage);
			cm.getEventBus().fireVisibleDataChanged();
		}
	}

	public void activateSystemMessage() {
		systemMessage.setActive(true);
		app.callUpdateSystemMessage(systemMessage);
		cm.getEventBus().fireVisibleDataChanged();
	}

	public void deactivateSystemMessage() {
		systemMessage.setActive(false);
		app.callUpdateSystemMessage(systemMessage);
		cm.getEventBus().fireVisibleDataChanged();
	}

	public SystemMessage getSystemMessage() {
		return systemMessage;
	}

	public ATextEditorModel systemMessageTextModel = new ATextEditorModel() {

		@Override
		public void setValue(String value) {
			systemMessage.setText(value);
		}

		@Override
		public String getValue() {
			return systemMessage.getText();
		}
	};

	public ADateAndTimeEditorModel systemMessageExpiresModel = new ADateAndTimeEditorModel() {

		@Override
		public DateAndTime getValue() {
			return systemMessage.getExpires();
		}

		@Override
		public void setValue(DateAndTime value) {
			systemMessage.setExpires(value);
		}

	};

}