package scrum.client.collaboration;

import java.util.Comparator;

import scrum.client.common.AScrumGwtEntity;

import com.google.gwt.user.client.ui.Widget;

public interface ForumSupport {

	String getLabel();

	String getReference();

	Widget createForumItemWidget();

	public static final Comparator<ForumSupport> COMPARATOR = new Comparator<ForumSupport>() {

		public int compare(ForumSupport aFs, ForumSupport bFs) {
			Comment aComment = ((AScrumGwtEntity) aFs).getLatestComment();
			Comment bComment = ((AScrumGwtEntity) bFs).getLatestComment();
			if (aComment == null && bComment == null) return 0;
			if (aComment == null) return -1;
			if (bComment == null) return 1;
			return bComment.getDateAndTime().compareTo(aComment.getDateAndTime());
		}
	};
}
