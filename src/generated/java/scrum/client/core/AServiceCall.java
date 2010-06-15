package scrum.client.core;

import ilarkesto.core.scope.Scope;
import ilarkesto.core.service.ServiceCall;
import scrum.client.DataTransferObject;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class AServiceCall implements ServiceCall {

	protected ServiceCaller serviceCaller = Scope.get().getComponent(ServiceCaller.class);

	public void execute() {
		execute(null);
	}

	protected class DefaultCallback implements AsyncCallback<DataTransferObject> {

		private Runnable returnHandler;

		public DefaultCallback(Runnable returnHandler) {
			this.returnHandler = returnHandler;
		}

		public void onSuccess(DataTransferObject data) {
			serviceCaller.onServiceCallSuccess(data);
			if (returnHandler != null) returnHandler.run();
		}

		public void onFailure(Throwable ex) {
			String serviceLabel = AServiceCall.this.toString();
			serviceCaller.onServiceCallReturn();
			serviceCaller.onServiceCallFailure(AServiceCall.this, ex);
		}

	}

}
