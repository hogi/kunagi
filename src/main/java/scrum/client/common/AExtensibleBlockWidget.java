package scrum.client.common;

import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.GwtLogger;

import com.google.gwt.user.client.ui.Widget;

public abstract class AExtensibleBlockWidget<O extends Object> extends ABlockWidget<O> {

	private boolean initializingExtension;
	private boolean initializedExtension;
	private Widget body;

	protected abstract void onCollapsedInitialization();

	protected abstract void onUpdateHead();

	protected abstract Widget onExtendedInitialization();

	protected Widget onUpdateBody() {
		Gwt.update(body);
		return body;
	}

	@Override
	protected final void onBlockInitialization() {
		onCollapsedInitialization();
	}

	@Override
	protected final void onBlockUpdate() {
		if (isExtended()) {
			ensureExtendedInitialized();
			onUpdateHead();
			body = onUpdateBody();
			setContent(body);
		} else {
			onUpdateHead();
		}
	}

	private void ensureExtendedInitialized() {
		if (initializingExtension)
			throw new RuntimeException("Extension initializing. Don't call update() within onInitailization(): "
					+ toString());
		if (!initializedExtension) initializeExtended();

	}

	private void initializeExtended() {
		if (initializedExtension) throw new RuntimeException("Extension already initialized: " + toString());
		if (initializingExtension) throw new RuntimeException("Extension already initializing: " + toString());
		initializingExtension = true;
		GwtLogger.DEBUG("Initializing extension: " + toString());
		body = onExtendedInitialization();
		setContent(body);
		initializedExtension = true;
		initializingExtension = false;
	}

}
