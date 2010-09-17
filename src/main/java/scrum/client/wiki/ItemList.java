package scrum.client.wiki;

import ilarkesto.gwt.client.Gwt;

import java.util.ArrayList;
import java.util.List;

public class ItemList extends AWikiElement {

	private List<Item> items = new ArrayList<Item>();
	boolean ordered;

	public ItemList(boolean ordered) {
		super();
		this.ordered = ordered;
	}

	public void add(Paragraph item) {
		items.add(new Item(item));
	}

	public void add(Paragraph item, String leadingSpaces, boolean ordered) {
		if (leadingSpaces.length() > 0) {
			Item lastItem = items.get(items.size() - 1);
			if (lastItem.list == null) {
				lastItem.list = new ItemList(ordered);
				lastItem.list.add(item);
			} else {
				lastItem.list.add(item, leadingSpaces.substring(1), ordered);
			}
			return;
		}
		items.add(new Item(item));
	}

	@Override
	String toHtml(HtmlContext context) {
		StringBuilder sb = new StringBuilder();
		sb.append(ordered ? "<ol>" : "<ul>");
		for (Item item : items) {
			sb.append("<li>");
			sb.append(item.p.toHtml(context));
			if (item.containsList()) {
				sb.append(item.list.toHtml(context));
			}
			sb.append("</li>");
		}
		sb.append(ordered ? "</ol>" : "</ul>");
		return sb.toString();
	}

	public List<Item> getItems() {
		return items;
	}

	public boolean isOrdered() {
		return ordered;
	}

	@Override
	public String toString() {
		return "ItemList(" + Gwt.toString(items) + ")";
	}

	public class Item {

		Paragraph p;
		ItemList list;

		public Item(Paragraph p) {
			super();
			this.p = p;
		}

		public Paragraph getParagraph() {
			return p;
		}

		public ItemList getList() {
			return list;
		}

		public boolean containsList() {
			return list != null;
		}

		@Override
		public String toString() {
			return containsList() ? "Item(" + p + "," + list + ")" : p.toString();
		}
	}

}
