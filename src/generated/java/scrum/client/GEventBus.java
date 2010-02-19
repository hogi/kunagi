// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.gwt.GwtEventBusGenerator










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

    // --- Login ---

    private Set<LoginListener> loginListeners = new HashSet<LoginListener>();

    public void addLoginListener(LoginListener listener) {
        loginListeners.add(listener);
    }

    public void removeLoginListener(LoginListener listener) {
        loginListeners.remove(listener);
    }

    public void fireLogin() {
        log.debug("Event fired: Login");
        for (LoginListener listener : loginListeners) {
            listener.onLogin();
        }
        for (Object component : ilarkesto.core.scope.Scope.get().getAllComponents()) {
            if (component instanceof LoginListener) {
                log.debug("  notifying component:", component);
                ((LoginListener)component).onLogin();
            }
        }
    }

    // --- Logout ---

    private Set<LogoutListener> logoutListeners = new HashSet<LogoutListener>();

    public void addLogoutListener(LogoutListener listener) {
        logoutListeners.add(listener);
    }

    public void removeLogoutListener(LogoutListener listener) {
        logoutListeners.remove(listener);
    }

    public void fireLogout() {
        log.debug("Event fired: Logout");
        for (LogoutListener listener : logoutListeners) {
            listener.onLogout();
        }
        for (Object component : ilarkesto.core.scope.Scope.get().getAllComponents()) {
            if (component instanceof LogoutListener) {
                log.debug("  notifying component:", component);
                ((LogoutListener)component).onLogout();
            }
        }
    }

    // --- ProjectOpened ---

    private Set<ProjectOpenedListener> projectOpenedListeners = new HashSet<ProjectOpenedListener>();

    public void addProjectOpenedListener(ProjectOpenedListener listener) {
        projectOpenedListeners.add(listener);
    }

    public void removeProjectOpenedListener(ProjectOpenedListener listener) {
        projectOpenedListeners.remove(listener);
    }

    public void fireProjectOpened() {
        log.debug("Event fired: ProjectOpened");
        for (ProjectOpenedListener listener : projectOpenedListeners) {
            listener.onProjectOpened();
        }
        for (Object component : ilarkesto.core.scope.Scope.get().getAllComponents()) {
            if (component instanceof ProjectOpenedListener) {
                log.debug("  notifying component:", component);
                ((ProjectOpenedListener)component).onProjectOpened();
            }
        }
    }

    // --- ProjectClosed ---

    private Set<ProjectClosedListener> projectClosedListeners = new HashSet<ProjectClosedListener>();

    public void addProjectClosedListener(ProjectClosedListener listener) {
        projectClosedListeners.add(listener);
    }

    public void removeProjectClosedListener(ProjectClosedListener listener) {
        projectClosedListeners.remove(listener);
    }

    public void fireProjectClosed() {
        log.debug("Event fired: ProjectClosed");
        for (ProjectClosedListener listener : projectClosedListeners) {
            listener.onProjectClosed();
        }
        for (Object component : ilarkesto.core.scope.Scope.get().getAllComponents()) {
            if (component instanceof ProjectClosedListener) {
                log.debug("  notifying component:", component);
                ((ProjectClosedListener)component).onProjectClosed();
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
        if (listener instanceof LoginListener) addLoginListener((LoginListener)listener);
        if (listener instanceof LogoutListener) addLogoutListener((LogoutListener)listener);
        if (listener instanceof ProjectOpenedListener) addProjectOpenedListener((ProjectOpenedListener)listener);
        if (listener instanceof ProjectClosedListener) addProjectClosedListener((ProjectClosedListener)listener);
        if (listener instanceof VisibleDataChangedListener) addVisibleDataChangedListener((VisibleDataChangedListener)listener);
        if (listener instanceof BlockExpandedListener) addBlockExpandedListener((BlockExpandedListener)listener);
        if (listener instanceof BlockCollapsedListener) addBlockCollapsedListener((BlockCollapsedListener)listener);
        if (listener instanceof SearchResultsChangedListener) addSearchResultsChangedListener((SearchResultsChangedListener)listener);
        if (listener instanceof FileUploadedListener) addFileUploadedListener((FileUploadedListener)listener);
    }

    public void removeListener(Object listener) {
        if (listener instanceof ApplicationStartListener) removeApplicationStartListener((ApplicationStartListener)listener);
        if (listener instanceof ServerDataReceivedListener) removeServerDataReceivedListener((ServerDataReceivedListener)listener);
        if (listener instanceof LoginListener) removeLoginListener((LoginListener)listener);
        if (listener instanceof LogoutListener) removeLogoutListener((LogoutListener)listener);
        if (listener instanceof ProjectOpenedListener) removeProjectOpenedListener((ProjectOpenedListener)listener);
        if (listener instanceof ProjectClosedListener) removeProjectClosedListener((ProjectClosedListener)listener);
        if (listener instanceof VisibleDataChangedListener) removeVisibleDataChangedListener((VisibleDataChangedListener)listener);
        if (listener instanceof BlockExpandedListener) removeBlockExpandedListener((BlockExpandedListener)listener);
        if (listener instanceof BlockCollapsedListener) removeBlockCollapsedListener((BlockCollapsedListener)listener);
        if (listener instanceof SearchResultsChangedListener) removeSearchResultsChangedListener((SearchResultsChangedListener)listener);
        if (listener instanceof FileUploadedListener) removeFileUploadedListener((FileUploadedListener)listener);
    }

}