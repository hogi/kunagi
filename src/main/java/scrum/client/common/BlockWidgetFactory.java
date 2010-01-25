package scrum.client.common;

public interface BlockWidgetFactory<O extends Object> {

	public abstract ABlockWidget<O> createBlock();

}
