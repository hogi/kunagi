package scrum.client.collaboration;

import ilarkesto.gwt.client.TableBuilder;
import scrum.client.common.AScrumGwtEntity;
import scrum.client.common.AScrumWidget;

import com.google.gwt.user.client.ui.Widget;

public class ForumItemWidget extends AScrumWidget {

	private ForumSupport entity;

	public ForumItemWidget(ForumSupport entity) {
		super();
		assert entity instanceof AScrumGwtEntity;
		this.entity = entity;
	}

	@Override
	protected Widget onInitialization() {
		return TableBuilder.row(20, new CommentsWidget((AScrumGwtEntity) entity), entity.createForumItemWidget());
	}
}
