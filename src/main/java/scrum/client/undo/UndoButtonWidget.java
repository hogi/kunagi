package scrum.client.undo;

import ilarkesto.gwt.client.Gwt;
import scrum.client.common.DropdownMenuButtonWidget;

import com.google.gwt.user.client.ui.Widget;

public class UndoButtonWidget extends DropdownMenuButtonWidget {

	private UndoManager undoManager;

	@Override
	protected Widget onInitialization() {
		Widget widget = super.onInitialization();
		setLabel("Undo V");
		return Gwt.createDiv("UndoButtonWidget", widget);
	}

	@Override
	protected void onUpdate() {
		clear();
		if (undoManager != null) {
			for (AUndoOperation operation : undoManager.getOperations()) {
				addAction(new UndoAction(undoManager, operation));
			}
		}
		super.onUpdate();
	}

	public void setUndoManager(UndoManager undoManager) {
		this.undoManager = undoManager;
	}

}
