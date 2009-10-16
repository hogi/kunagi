package scrum.client.collaboration;

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
		cm.getDao().deleteWikipage(wikipage);
	}

}