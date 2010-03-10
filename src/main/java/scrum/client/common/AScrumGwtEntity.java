package scrum.client.common;

import ilarkesto.gwt.client.AGwtEntity;
import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.editor.AOptionEditorModel;
import ilarkesto.gwt.client.editor.DropdownEditorWidget;

import java.util.List;
import java.util.Map;

import scrum.client.ComponentManager;
import scrum.client.admin.User;
import scrum.client.collaboration.Comment;
import scrum.client.collaboration.Emoticon;

public abstract class AScrumGwtEntity extends AGwtEntity {

	protected static final transient ComponentManager cm = ComponentManager.get();

	public AScrumGwtEntity() {}

	public List<Comment> getComments() {
		return cm.getDao().getCommentsByParent(this);
	}

	public Comment getLatestComment() {
		Comment latest = null;
		for (Comment comment : getComments()) {
			if (latest == null || comment.getDateAndTime().isAfter(latest.getDateAndTime())) latest = comment;
		}
		return latest;
	}

	public List<Emoticon> getEmoticons() {
		return cm.getDao().getEmoticonsByParent(this);
	}

	public void setCurrentUserEmoticon(String emotion) {
		boolean delete = Gwt.isEmpty(emotion);
		Emoticon emoticon = getCurrentUserEmoticon();
		if (emoticon == null) {
			if (!delete) {
				emoticon = new Emoticon(this, emotion);
				cm.getDao().createEmoticon(emoticon);
				return;
			}
		} else {
			if (delete) {
				cm.getDao().deleteEmoticon(emoticon);
			} else {
				emoticon.setEmotion(emotion);
			}
		}
	}

	public Emoticon getCurrentUserEmoticon() {
		User currentUser = cm.getAuth().getUser();
		for (Emoticon emoticon : getEmoticons()) {
			if (emoticon.isOwner(currentUser)) return emoticon;
		}
		return null;
	}

	public DropdownEditorWidget<String> createCurrentUserEmotionEditor() {
		return new DropdownEditorWidget<String>(getCurrentUserEmotionModel(), Emoticon.EMOTION_LABEL_PROVIDER);
	}

	public AOptionEditorModel<String> getCurrentUserEmotionModel() {
		return new AOptionEditorModel<String>() {

			@Override
			public List<String> getOptions() {
				return Emoticon.EMOTIONS;
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

	public String toHtml() {
		return Gwt.escapeHtml(toString());
	}

}
