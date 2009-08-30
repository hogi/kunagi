package scrum.client.common;

public abstract class BlockWidgetFactory<O extends Object> {

	private ABlockWidget<O> dummy = null;

	public abstract ABlockWidget<O> createBlock();

	public boolean isSameType(Object other) {
		if (dummy == null) dummy = createBlock();
		return dummy.getClass().getName().equals(other.getClass().getName());
	}
}
