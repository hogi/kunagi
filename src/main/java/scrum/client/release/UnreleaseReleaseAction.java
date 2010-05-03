package scrum.client.release;

import ilarkesto.gwt.client.Date;

public class UnreleaseReleaseAction extends GUnreleaseReleaseAction {

	public UnreleaseReleaseAction(scrum.client.release.Release release) {
		super(release);
	}

	@Override
	public String getLabel() {
		return "Mark as planned";
	}

	@Override
	public boolean isExecutable() {
		if (!release.isReleased()) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		Date previousDate = release.getReleaseDate();
		release.setReleaseDate(Date.today());
		release.setReleased(false);
		addUndo(new Undo(previousDate));
	}

	class Undo extends ALocalUndo {

		private Date date;

		public Undo(Date date) {
			super();
			this.date = date;
		}

		@Override
		public String getLabel() {
			return "Undo Mark as planned " + release.getReference() + " " + release.getLabel();
		}

		@Override
		protected void onUndo() {
			release.setReleaseDate(date);
			release.setReleased(true);
		}

	}
}