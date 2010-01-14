package scrum.client.common;

import ilarkesto.gwt.client.AGwtEntity;
import ilarkesto.gwt.client.Gwt;

import java.util.Map;

import scrum.client.ComponentManager;

public abstract class AScrumGwtEntity extends AGwtEntity {

	protected static final ComponentManager cm = ComponentManager.get();

	public AScrumGwtEntity() {}

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
