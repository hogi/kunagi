package scrum.client.common;

import ilarkesto.core.base.Str;
import ilarkesto.core.base.ToHtmlSupport;
import ilarkesto.core.scope.Scope;
import ilarkesto.gwt.client.AGwtEntity;
import ilarkesto.gwt.client.DateAndTime;
import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.HtmlLabelSupport;
import ilarkesto.gwt.client.editor.AOptionEditorModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import scrum.client.Dao;
import scrum.client.admin.Auth;
import scrum.client.admin.User;
import scrum.client.collaboration.Comment;
import scrum.client.collaboration.Emoticon;
import scrum.client.collaboration.ForumSupport;

public abstract class AScrumGwtEntity extends AGwtEntity implements ToHtmlSupport, HtmlLabelSupport {

	public AScrumGwtEntity() {}

	public List<Comment> getComments() {
		return getDao().getCommentsByParent(this);
	}

	public Comment getLatestComment() {
		Comment latest = null;
		for (Comment comment : getComments()) {
			if (latest == null || comment.getDateAndTime().isAfter(latest.getDateAndTime())) latest = comment;
		}
		return latest;
	}

	public DateAndTime getLatestCommentDateAndTime() {
		Comment latest = getLatestComment();
		return latest == null ? null : latest.getDateAndTime();
	}

	public List<Emoticon> getEmoticons() {
		return getDao().getEmoticonsByParent(this);
	}

	public void setCurrentUserEmoticon(String emotion) {
		boolean delete = Str.isBlank(emotion);
		Emoticon emoticon = getCurrentUserEmoticon();
		if (emoticon == null) {
			if (!delete) {
				emoticon = new Emoticon(this, emotion);
				getDao().createEmoticon(emoticon);
				return;
			}
		} else {
			if (delete) {
				getDao().deleteEmoticon(emoticon);
			} else {
				emoticon.setEmotion(emotion);
			}
		}
	}

	public Emoticon getCurrentUserEmoticon() {
		User currentUser = Scope.get().getComponent(Auth.class).getUser();
		for (Emoticon emoticon : getEmoticons()) {
			if (emoticon.isOwner(currentUser)) return emoticon;
		}
		return null;
	}

	public AOptionEditorModel<String> getCurrentUserEmotionModel() {
		return new AOptionEditorModel<String>() {

			@Override
			public List<String> getOptions() {
				List<String> options = new ArrayList<String>(Emoticon.EMOTIONS_LEGACY);
				options.add(0, "");
				return options;
			}

			@Override
			public void setValue(String value) {
				setCurrentUserEmoticon(value);
			}

			@Override
			public String getValue() {
				Emoticon emoticon = getCurrentUserEmoticon();
				return emoticon == null ? null : emoticon.getEmotion();
			}
		};
	}

	@Override
	public boolean matchesKey(String key) {
		if (this instanceof ReferenceSupport) {
			if (matchesKey(((ReferenceSupport) this).getReference(), key)) return true;
		}
		return super.matchesKey(key);
	}

	public AScrumGwtEntity(Map data) {
		super(data);
	}

	@Override
	public String getHtmlLabel() {
		StringBuilder sb = new StringBuilder();
		if (this instanceof ReferenceSupport) {
			sb.append("<span class='reference'>").append(((ReferenceSupport) this).getReference()).append("</span> ");
		}
		String label;
		if (this instanceof ForumSupport) {
			label = ((ForumSupport) this).getLabel();
		} else {
			label = toString();
		}
		sb.append(Gwt.escapeHtml(label));
		return sb.toString();
	}

	@Override
	public String toHtml() {
		return Gwt.escapeHtml(toString());
	}

	@Override
	protected Dao getDao() {
		return Dao.get();
	}

}
