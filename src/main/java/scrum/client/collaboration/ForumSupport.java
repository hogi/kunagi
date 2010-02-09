package scrum.client.collaboration;

import java.util.Comparator;

import com.google.gwt.user.client.ui.Widget;

public interface ForumSupport {

	String getLabel();

	String getReference();

	Widget createForumItemWidget();

	public static final Comparator<ForumSupport> COMPARATOR = new Comparator<ForumSupport>() {

		public int compare(ForumSupport a, ForumSupport b) {
			return a.toString().compareTo(b.toString());
		}
	};
}
