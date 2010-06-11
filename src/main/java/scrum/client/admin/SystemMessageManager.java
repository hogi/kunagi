package scrum.client.admin;

import ilarkesto.gwt.client.DateAndTime;
import ilarkesto.gwt.client.editor.ADateAndTimeEditorModel;
import ilarkesto.gwt.client.editor.ATextEditorModel;
import scrum.client.DataTransferObject;
import scrum.client.communication.ServerDataReceivedEvent;
import scrum.client.communication.ServerDataReceivedHandler;
import scrum.client.workspace.VisibleDataChangedEvent;

public class SystemMessageManager extends GSystemMessageManager implements ServerDataReceivedHandler {

	private SystemMessage systemMessage = new SystemMessage();

	public void onServerDataReceived(ServerDataReceivedEvent event) {
		DataTransferObject data = event.getData();
		if (data.systemMessage != null) {
			systemMessage = data.systemMessage;
			log.info("SystemMessage received:", systemMessage);
			new VisibleDataChangedEvent().fireInCurrentScope();
		}
	}

	public void activateSystemMessage() {
		systemMessage.setActive(true);
		app.callUpdateSystemMessage(systemMessage);
		new VisibleDataChangedEvent().fireInCurrentScope();
	}

	public void deactivateSystemMessage() {
		systemMessage.setActive(false);
		app.callUpdateSystemMessage(systemMessage);
		new VisibleDataChangedEvent().fireInCurrentScope();
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