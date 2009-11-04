package scrum.client;

import scrum.client.common.ElementPredicate;

public abstract class ListPredicate<G> implements ElementPredicate<G> {

	private final String name;
	private boolean enabled = true;

	public ListPredicate(String name, boolean enabled) {
		this.name = name;
		setEnabled(enabled);
	}

	public boolean contains(G element) {
		if (!isEnabled()) return false;
		return test(element);
	}

	protected abstract boolean test(G element);

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public String toString() {
		return name;
	}
}
