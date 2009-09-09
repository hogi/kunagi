package scrum.client.common;

import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.GwtLogger;
import scrum.client.ScrumGwtApplication;
import scrum.client.admin.User;
import scrum.client.project.Project;

import com.google.gwt.user.client.Command;

public abstract class AAction implements Command {

	private AWidget[] widgetsToUpdate;

	public abstract boolean isExecutable();

	public abstract String getLabel();

	protected abstract void onExecute();

	public AAction(AWidget... widgetsToUpdate) {
		this.widgetsToUpdate = widgetsToUpdate;
	}

	public final void execute() {
		GwtLogger.DEBUG("Executing action: " + this);
		onExecute();
		for (AWidget widget : widgetsToUpdate)
			widget.update();
	}

	public String getTooltip() {
		return null;
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

	// --- helper ---

	protected Project getProject() {
		return ScrumGwtApplication.get().getProject();
	}

	protected User getUser() {
		return ScrumGwtApplication.get().getUser();
	}

}
