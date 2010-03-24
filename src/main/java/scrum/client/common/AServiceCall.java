package scrum.client.common;

import ilarkesto.core.base.Str;
import ilarkesto.core.scope.Scope;
import scrum.client.ScrumGwtApplication;

public abstract class AServiceCall {

	public abstract void execute();

	protected void onSuccess() {}

	protected void onFailure(Throwable ex) {}

	protected final ScrumGwtApplication getService() {
		return (ScrumGwtApplication) Scope.get().getComponent("app");
	}

	@Override
	public String toString() {
		return Str.getSimpleName(getClass());
	}

}
