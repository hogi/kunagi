package scrum.client.collaboration;

public class CreateWikipageAction extends GCreateWikipageAction {

	private String pageName;

	public CreateWikipageAction(String pageName) {
		this.pageName = pageName;
	}

	@Override
	public String getLabel() {
		return "Create this Page";
	}

	@Override
	protected void onExecute() {
		getCurrentProject().createNewWikipage(pageName);
	}

}