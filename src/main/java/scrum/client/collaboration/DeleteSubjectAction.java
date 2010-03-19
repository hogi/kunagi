package scrum.client.collaboration;

import scrum.client.common.TooltipBuilder;

public class DeleteSubjectAction extends GDeleteSubjectAction {

	public DeleteSubjectAction(scrum.client.collaboration.Subject subject) {
		super(subject);
	}

	@Override
	public String getLabel() {
		return "Delete";
	}

	@Override
	public String getTooltip() {
		TooltipBuilder tb = new TooltipBuilder("Delete this Subject and all comments.");
		if (!subject.getProject().isScrumMaster(getCurrentUser())) tb.addRemark(TooltipBuilder.NOT_SCRUMMASTER);

		return tb.getTooltip();
	}

	@Override
	public boolean isExecutable() {
		return true;
	}

	@Override
	public boolean isPermitted() {
		if (!getCurrentProject().isScrumMaster(getCurrentUser())) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		getDao().deleteSubject(subject);
		addUndo(new Undo());
	}

	class Undo extends ALocalUndo {

		@Override
		public String getLabel() {
			return "Undo Delete " + subject.getReference() + " " + subject.getLabel();
		}

		@Override
		protected void onUndo() {
			getDao().createSubject(subject);
		}

	}

}