// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.GwtEventBusGenerator










package scrum.client;

import java.util.*;

public abstract class GEventBus
            extends ilarkesto.gwt.client.AEventBus {

    // --- ApplicationStart ---

    private Set<ApplicationStartListener> applicationStartListeners = new HashSet<ApplicationStartListener>();

    public void addApplicationStartListener(ApplicationStartListener listener) {
        applicationStartListeners.add(listener);
    }

    public void removeApplicationStartListener(ApplicationStartListener listener) {
        applicationStartListeners.remove(listener);
    }

    public void fireApplicationStart() {
        log.debug("Event fired: ApplicationStart");
        for (ApplicationStartListener listener : applicationStartListeners) {
            listener.onApplicationStart();
        }
        for (Object component : ilarkesto.core.scope.Scope.get().getAllComponents()) {
            if (component instanceof ApplicationStartListener) {
                log.debug("  notifying component:", component);
                ((ApplicationStartListener)component).onApplicationStart();
            }
        }
    }

    // --- ServerDataReceived ---

    private Set<ServerDataReceivedListener> serverDataReceivedListeners = new HashSet<ServerDataReceivedListener>();

    public void addServerDataReceivedListener(ServerDataReceivedListener listener) {
        serverDataReceivedListeners.add(listener);
    }

    public void removeServerDataReceivedListener(ServerDataReceivedListener listener) {
        serverDataReceivedListeners.remove(listener);
    }

    public void fireServerDataReceived(DataTransferObject data) {
        for (ServerDataReceivedListener listener : serverDataReceivedListeners) {
            listener.onServerDataReceived(data);
        }
        for (Object component : ilarkesto.core.scope.Scope.get().getAllComponents()) {
            if (component instanceof ServerDataReceivedListener) {
                ((ServerDataReceivedListener)component).onServerDataReceived(data);
            }
        }
    }

    // --- VisibleDataChanged ---

    private Set<VisibleDataChangedListener> visibleDataChangedListeners = new HashSet<VisibleDataChangedListener>();

    public void addVisibleDataChangedListener(VisibleDataChangedListener listener) {
        visibleDataChangedListeners.add(listener);
    }

    public void removeVisibleDataChangedListener(VisibleDataChangedListener listener) {
        visibleDataChangedListeners.remove(listener);
    }

    public void fireVisibleDataChanged() {
        log.debug("Event fired: VisibleDataChanged");
        for (VisibleDataChangedListener listener : visibleDataChangedListeners) {
            listener.onVisibleDataChanged();
        }
        for (Object component : ilarkesto.core.scope.Scope.get().getAllComponents()) {
            if (component instanceof VisibleDataChangedListener) {
                log.debug("  notifying component:", component);
                ((VisibleDataChangedListener)component).onVisibleDataChanged();
            }
        }
    }

    // --- BlockExpanded ---

    private Set<BlockExpandedListener> blockExpandedListeners = new HashSet<BlockExpandedListener>();

    public void addBlockExpandedListener(BlockExpandedListener listener) {
        blockExpandedListeners.add(listener);
    }

    public void removeBlockExpandedListener(BlockExpandedListener listener) {
        blockExpandedListeners.remove(listener);
    }

    public void fireBlockExpanded(java.lang.Object object) {
        log.debug("Event fired: BlockExpanded");
        for (BlockExpandedListener listener : blockExpandedListeners) {
            listener.onBlockExpanded(object);
        }
        for (Object component : ilarkesto.core.scope.Scope.get().getAllComponents()) {
            if (component instanceof BlockExpandedListener) {
                log.debug("  notifying component:", component);
                ((BlockExpandedListener)component).onBlockExpanded(object);
            }
        }
    }

    // --- BlockCollapsed ---

    private Set<BlockCollapsedListener> blockCollapsedListeners = new HashSet<BlockCollapsedListener>();

    public void addBlockCollapsedListener(BlockCollapsedListener listener) {
        blockCollapsedListeners.add(listener);
    }

    public void removeBlockCollapsedListener(BlockCollapsedListener listener) {
        blockCollapsedListeners.remove(listener);
    }

    public void fireBlockCollapsed(java.lang.Object object) {
        log.debug("Event fired: BlockCollapsed");
        for (BlockCollapsedListener listener : blockCollapsedListeners) {
            listener.onBlockCollapsed(object);
        }
        for (Object component : ilarkesto.core.scope.Scope.get().getAllComponents()) {
            if (component instanceof BlockCollapsedListener) {
                log.debug("  notifying component:", component);
                ((BlockCollapsedListener)component).onBlockCollapsed(object);
            }
        }
    }

    // --- SearchResultsChanged ---

    private Set<SearchResultsChangedListener> searchResultsChangedListeners = new HashSet<SearchResultsChangedListener>();

    public void addSearchResultsChangedListener(SearchResultsChangedListener listener) {
        searchResultsChangedListeners.add(listener);
    }

    public void removeSearchResultsChangedListener(SearchResultsChangedListener listener) {
        searchResultsChangedListeners.remove(listener);
    }

    public void fireSearchResultsChanged() {
        log.debug("Event fired: SearchResultsChanged");
        for (SearchResultsChangedListener listener : searchResultsChangedListeners) {
            listener.onSearchResultsChanged();
        }
        for (Object component : ilarkesto.core.scope.Scope.get().getAllComponents()) {
            if (component instanceof SearchResultsChangedListener) {
                log.debug("  notifying component:", component);
                ((SearchResultsChangedListener)component).onSearchResultsChanged();
            }
        }
    }

    // --- FileUploaded ---

    private Set<FileUploadedListener> fileUploadedListeners = new HashSet<FileUploadedListener>();

    public void addFileUploadedListener(FileUploadedListener listener) {
        fileUploadedListeners.add(listener);
    }

    public void removeFileUploadedListener(FileUploadedListener listener) {
        fileUploadedListeners.remove(listener);
    }

    public void fireFileUploaded(scrum.client.files.File file) {
        log.debug("Event fired: FileUploaded");
        for (FileUploadedListener listener : fileUploadedListeners) {
            listener.onFileUploaded(file);
        }
        for (Object component : ilarkesto.core.scope.Scope.get().getAllComponents()) {
            if (component instanceof FileUploadedListener) {
                log.debug("  notifying component:", component);
                ((FileUploadedListener)component).onFileUploaded(file);
            }
        }
    }

    // --- automatic adding / removing ---

    public void addListener(Object listener) {
        if (listener instanceof ApplicationStartListener) addApplicationStartListener((ApplicationStartListener)listener);
        if (listener instanceof ServerDataReceivedListener) addServerDataReceivedListener((ServerDataReceivedListener)listener);
        if (listener instanceof VisibleDataChangedListener) addVisibleDataChangedListener((VisibleDataChangedListener)listener);
        if (listener instanceof BlockExpandedListener) addBlockExpandedListener((BlockExpandedListener)listener);
        if (listener instanceof BlockCollapsedListener) addBlockCollapsedListener((BlockCollapsedListener)listener);
        if (listener instanceof SearchResultsChangedListener) addSearchResultsChangedListener((SearchResultsChangedListener)listener);
        if (listener instanceof FileUploadedListener) addFileUploadedListener((FileUploadedListener)listener);
    }

    public void removeListener(Object listener) {
        if (listener instanceof ApplicationStartListener) removeApplicationStartListener((ApplicationStartListener)listener);
        if (listener instanceof ServerDataReceivedListener) removeServerDataReceivedListener((ServerDataReceivedListener)listener);
        if (listener instanceof VisibleDataChangedListener) removeVisibleDataChangedListener((VisibleDataChangedListener)listener);
        if (listener instanceof BlockExpandedListener) removeBlockExpandedListener((BlockExpandedListener)listener);
        if (listener instanceof BlockCollapsedListener) removeBlockCollapsedListener((BlockCollapsedListener)listener);
        if (listener instanceof SearchResultsChangedListener) removeSearchResultsChangedListener((SearchResultsChangedListener)listener);
        if (listener instanceof FileUploadedListener) removeFileUploadedListener((FileUploadedListener)listener);
    }

}