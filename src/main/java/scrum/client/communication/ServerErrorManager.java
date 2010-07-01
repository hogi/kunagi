package scrum.client.communication;

import java.util.ArrayList;
import java.util.List;

public class ServerErrorManager extends GServerErrorManager implements ServerDataReceivedHandler {

	private List<String> errors = new ArrayList<String>();

	public void onServerDataReceived(ServerDataReceivedEvent event) {
		List<String> serverErrors = event.getData().getErrors();
		if (serverErrors != null) {
			errors.addAll(serverErrors);
			log.info("Errors received:", serverErrors);
		}
	}

	public String popErrorsAsString() {
		if (errors.isEmpty()) return null;
		StringBuilder sb = new StringBuilder();
		for (String error : errors) {
			sb.append(error).append("\n");
		}
		errors.clear();
		return sb.toString();
	}

}
