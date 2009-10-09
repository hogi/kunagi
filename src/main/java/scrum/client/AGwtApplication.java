package scrum.client;

import ilarkesto.gwt.client.AComponentManager;
import ilarkesto.gwt.client.ADataTransferObject;
import ilarkesto.gwt.client.GwtLogger;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class AGwtApplication implements EntryPoint {

	protected static AGwtApplication singleton;

	protected abstract void handleCommunicationError(Throwable ex);

	public AGwtApplication() {
		if (singleton != null) throw new RuntimeException("GWT application already instantiated: " + singleton);
		singleton = this;
		GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {

			public void onUncaughtException(Throwable ex) {
				ex.printStackTrace();
				GwtLogger.DEBUG("ERROR", ex);
				throw new RuntimeException(ex);
			}
		});
	}

	protected void handleDataFromServer(ADataTransferObject data) {
		AComponentManager.get().getDao().handleDataFromServer(data);
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

	public static AGwtApplication get() {
		return singleton;
	}

	protected class DefaultCallback<T extends ADataTransferObject> implements AsyncCallback<T> {

		private Runnable successAction;

		public DefaultCallback() {}

		public DefaultCallback(Runnable successAction) {
			this.successAction = successAction;
		}

		public void onSuccess(T data) {
			handleDataFromServer(data);
			if (successAction != null) successAction.run();
		}

		public void onFailure(Throwable ex) {
			handleCommunicationError(ex);
		}

	}

}
