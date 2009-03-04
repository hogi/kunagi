package scrum.client.dnd;

import scrum.client.common.ABlockWidget;

import com.google.gwt.user.client.ui.Image;

public interface ClipboardSupport {

	String getClipboardLabel();

	Image getClipboardIcon();

	ABlockWidget getClipboardPayload();
}
