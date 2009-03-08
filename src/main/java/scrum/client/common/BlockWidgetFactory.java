package scrum.client.common;

public interface BlockWidgetFactory<O extends Object> {

	ABlockWidget<O> createBlock();

}
