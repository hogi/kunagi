package scrum.client.collaboration;

import ilarkesto.gwt.client.Gwt;

public class DeleteWikipageAction extends GDeleteWikipageAction {

	public DeleteWikipageAction(scrum.client.collaboration.Wikipage wikipage) {
		super(wikipage);
	}

	@Override
	public String getLabel() {
		return "Delete this Page";
	}

	@Override
	protected void onExecute() {
		if (!Gwt.confirm("Delete wiki page " + wikipage.getName() + "?")) return;
		getDao().deleteWikipage(wikipage);
		addUndo(new Undo());
	}

	class Undo extends ALocalUndo {

		@Override
		public String getLabel() {
			return "Undo Delete Wikipage " + wikipage.getName();
		}

		@Override
		protected void onUndo() {
			getDao().createWikipage(wikipage);
		}

	}

}