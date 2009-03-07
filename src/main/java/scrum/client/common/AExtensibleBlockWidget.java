package scrum.client.common;

import ilarkesto.gwt.client.GwtLogger;

public abstract class AExtensibleBlockWidget<O extends Object> extends ABlockWidget<O> {

	private boolean initializingExtension;
	private boolean initializedExtension;

	protected abstract void onCollapsedInitialization();

	protected abstract void onCollapsedUpdate();

	protected abstract void onExtendedInitialization();

	protected abstract void onExtendedUpdate();

	@Override
	protected final void onBlockInitialization() {
		onCollapsedInitialization();
	}

	@Override
	protected final void onBlockUpdate() {
		if (isSelected()) {
			ensureExtendedInitialized();
			onExtendedUpdate();
		} else {
			onCollapsedUpdate();
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
		onExtendedInitialization();
		initializedExtension = true;
		initializingExtension = false;
	}

}
