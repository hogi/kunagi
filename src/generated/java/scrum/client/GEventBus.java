// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.gwt.GwtEventBusGenerator










package scrum.client;

import java.util.*;

public abstract class GEventBus
            extends ilarkesto.gwt.client.AEventBus {

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
    }

    // --- automatic adding / removing ---

    public void addListener(Object listener) {
        if (listener instanceof ServerDataReceivedListener) addServerDataReceivedListener((ServerDataReceivedListener)listener);
        if (listener instanceof LoginListener) addLoginListener((LoginListener)listener);
        if (listener instanceof LogoutListener) addLogoutListener((LogoutListener)listener);
        if (listener instanceof ProjectOpenedListener) addProjectOpenedListener((ProjectOpenedListener)listener);
        if (listener instanceof ProjectClosedListener) addProjectClosedListener((ProjectClosedListener)listener);
    }

    public void removeListener(Object listener) {
        if (listener instanceof ServerDataReceivedListener) removeServerDataReceivedListener((ServerDataReceivedListener)listener);
        if (listener instanceof LoginListener) removeLoginListener((LoginListener)listener);
        if (listener instanceof LogoutListener) removeLogoutListener((LogoutListener)listener);
        if (listener instanceof ProjectOpenedListener) removeProjectOpenedListener((ProjectOpenedListener)listener);
        if (listener instanceof ProjectClosedListener) removeProjectClosedListener((ProjectClosedListener)listener);
    }

}