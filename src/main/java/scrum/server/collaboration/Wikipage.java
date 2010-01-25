package scrum.server.collaboration;


public class Wikipage extends GWikipage {

	@Override
	public String toString() {
		return "Wiki-Page [[" + getName() + "]]";
	}

}