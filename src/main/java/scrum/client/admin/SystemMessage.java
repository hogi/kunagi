package scrum.client.admin;

import ilarkesto.gwt.client.DateAndTime;
import ilarkesto.gwt.client.TimePeriod;

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
		if (expires == null) return null;
		TimePeriod timePeriod = expires.getPeriodFromNow();
		if (!timePeriod.isPositive()) return null;
		return "in " + timePeriod.toShortestString();
	}

	public void setExpires(DateAndTime expires) {
		this.expires = expires;
	}

}
