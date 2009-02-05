package scrum.client;


import ilarkesto.gwt.client.DataTransferObject;

import java.util.HashMap;
import java.util.Map;

import scrum.client.common.AGwtDao;
import scrum.client.common.AGwtEntity;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class AGwtApplication implements EntryPoint {

	protected static AGwtApplication singleton;

	protected String entityIdBase;
	private int entityIdCounter;
	private Map<String, ? extends AGwtEntity> entities = new HashMap<String, AGwtEntity>();

	protected abstract void handleCommunicationError(Throwable ex);

	public abstract AGwtDao getDao();

	public AGwtApplication() {
		if (singleton != null) throw new RuntimeException("GWT application already instantiated: " + singleton);
		singleton = this;
	}

	protected void handleDataFromServer(DataTransferObject data) {
		getDao().handleDataFromServer(data);
	}

	public final String getNewEntityId() {
		if (entityIdBase == null) throw new RuntimeException("No entityIdBase received.");
		return entityIdBase + ++entityIdCounter;
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

	public static AGwtApplication get() {
		return singleton;
	}

	protected class DefaultCallback implements AsyncCallback<DataTransferObject> {

		private Runnable successAction;

		public DefaultCallback() {}

		public DefaultCallback(Runnable successAction) {
			this.successAction = successAction;
		}

		public void onSuccess(DataTransferObject data) {
			handleDataFromServer(data);
			if (successAction != null) successAction.run();
		}

		public void onFailure(Throwable ex) {
			handleCommunicationError(ex);
		}

	}

}
