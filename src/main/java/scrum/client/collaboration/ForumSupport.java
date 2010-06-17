package scrum.client.collaboration;

import ilarkesto.gwt.client.DateAndTime;

import java.util.Comparator;

import scrum.client.common.AScrumGwtEntity;

import com.google.gwt.user.client.ui.Widget;

public interface ForumSupport {

	String getLabel();

	String getReference();

	Widget createForumItemWidget();

	public static final Comparator<ForumSupport> COMPARATOR = new Comparator<ForumSupport>() {

		public int compare(ForumSupport aFs, ForumSupport bFs) {
			DateAndTime aTime = ((AScrumGwtEntity) aFs).getLatestCommentDateAndTime();
			DateAndTime bTime = ((AScrumGwtEntity) bFs).getLatestCommentDateAndTime();
			if (aTime == null && bTime == null) return 0;
			if (aTime == null) return -1;
			if (bTime == null) return 1;
			return bTime.compareTo(aTime);
		}
	};
}
