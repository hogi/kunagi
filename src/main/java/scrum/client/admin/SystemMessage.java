package scrum.client.admin;

import ilarkesto.gwt.client.DateAndTime;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class SystemMessage implements Serializable, IsSerializable {

	private boolean active = false;

	private String text;

	private DateAndTime expires;

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public DateAndTime getExpires() {
		return expires;
	}

	public String getExpiresAsString() {
		return expires == null ? null : expires.toString();
	}

	public void setExpires(DateAndTime expires) {
		this.expires = expires;
	}

}
