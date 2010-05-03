package scrum.client.workspace;

import java.util.ArrayList;
import java.util.List;

public class PageSet {

	private List<Page> pages = new ArrayList<Page>();

	public void addPage(Page page) {
		pages.add(page);
	}

	public Page getPageByName(String name) {
		for (Page page : pages) {
			if (page.getName().equals(name)) return page;
		}
		return null;
	}

	public List<Page> getPagesByGroupKey(String groupKey) {
		List<Page> ret = new ArrayList<Page>();
		for (Page page : pages) {
			if (groupKey.equals(page.getGroupKey())) ret.add(page);
		}
		return ret;
	}

}
