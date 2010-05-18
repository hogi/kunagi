package scrum.client.release;

import ilarkesto.gwt.client.Date;

public class ReleaseReleaseAction extends GReleaseReleaseAction {

	public ReleaseReleaseAction(scrum.client.release.Release release) {
		super(release);
	}

	@Override
	public String getLabel() {
		return "Mark as published";
	}

	@Override
	protected void onExecute() {
		Date previousDate = release.getReleaseDate();
		release.setReleaseDate(Date.today());
		release.setReleased(true);
		addUndo(new Undo(previousDate));
	}

	@Override
	public boolean isExecutable() {
		if (release.isReleased()) return false;
		return true;
	}

	class Undo extends ALocalUndo {

		private Date date;

		public Undo(Date date) {
			super();
			this.date = date;
		}

		@Override
		public String getLabel() {
			return "Undo Mark as published " + release.getReference() + " " + release.getLabel();
		}

		@Override
		protected void onUndo() {
			release.setReleaseDate(date);
			release.setReleased(false);
		}

	}

}