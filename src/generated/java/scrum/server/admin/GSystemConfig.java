// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.EntityGenerator










package scrum.server.admin;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.core.logging.Log;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;

public abstract class GSystemConfig
            extends AEntity
            implements ilarkesto.auth.ViewProtected<scrum.server.admin.User>, ilarkesto.auth.EditProtected<scrum.server.admin.User>, java.lang.Comparable<SystemConfig> {

    // --- AEntity ---

    public final SystemConfigDao getDao() {
        return systemConfigDao;
    }

    protected void repairDeadDatob(ADatob datob) {
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("url", this.url);
        properties.put("adminEmail", this.adminEmail);
        properties.put("googleAnalyticsId", this.googleAnalyticsId);
        properties.put("smtpServer", this.smtpServer);
        properties.put("smtpPort", this.smtpPort);
        properties.put("smtpTls", this.smtpTls);
        properties.put("smtpUser", this.smtpUser);
        properties.put("smtpPassword", this.smtpPassword);
        properties.put("smtpFrom", this.smtpFrom);
        properties.put("loginPageLogoUrl", this.loginPageLogoUrl);
        properties.put("loginPageMessage", this.loginPageMessage);
        properties.put("registerPageMessage", this.registerPageMessage);
        properties.put("aboutPageMessage", this.aboutPageMessage);
        properties.put("userEmailMandatory", this.userEmailMandatory);
        properties.put("registrationDisabled", this.registrationDisabled);
        properties.put("defaultUserPassword", this.defaultUserPassword);
    }

    public int compareTo(SystemConfig other) {
        return toString().toLowerCase().compareTo(other.toString().toLowerCase());
    }

    private static final ilarkesto.core.logging.Log LOG = ilarkesto.core.logging.Log.get(GSystemConfig.class);

    public static final String TYPE = "systemConfig";

    // -----------------------------------------------------------
    // - url
    // -----------------------------------------------------------

    private java.lang.String url;

    public final java.lang.String getUrl() {
        return url;
    }

    public final void setUrl(java.lang.String url) {
        url = prepareUrl(url);
        if (isUrl(url)) return;
        this.url = url;
        fireModified("url="+url);
    }

    protected java.lang.String prepareUrl(java.lang.String url) {
        url = Str.removeUnreadableChars(url);
        return url;
    }

    public final boolean isUrlSet() {
        return this.url != null;
    }

    public final boolean isUrl(java.lang.String url) {
        if (this.url == null && url == null) return true;
        return this.url != null && this.url.equals(url);
    }

    protected final void updateUrl(Object value) {
        setUrl((java.lang.String)value);
    }

    // -----------------------------------------------------------
    // - adminEmail
    // -----------------------------------------------------------

    private java.lang.String adminEmail;

    public final java.lang.String getAdminEmail() {
        return adminEmail;
    }

    public final void setAdminEmail(java.lang.String adminEmail) {
        adminEmail = prepareAdminEmail(adminEmail);
        if (isAdminEmail(adminEmail)) return;
        this.adminEmail = adminEmail;
        fireModified("adminEmail="+adminEmail);
    }

    protected java.lang.String prepareAdminEmail(java.lang.String adminEmail) {
        adminEmail = Str.removeUnreadableChars(adminEmail);
        return adminEmail;
    }

    public final boolean isAdminEmailSet() {
        return this.adminEmail != null;
    }

    public final boolean isAdminEmail(java.lang.String adminEmail) {
        if (this.adminEmail == null && adminEmail == null) return true;
        return this.adminEmail != null && this.adminEmail.equals(adminEmail);
    }

    protected final void updateAdminEmail(Object value) {
        setAdminEmail((java.lang.String)value);
    }

    // -----------------------------------------------------------
    // - googleAnalyticsId
    // -----------------------------------------------------------

    private java.lang.String googleAnalyticsId;

    public final java.lang.String getGoogleAnalyticsId() {
        return googleAnalyticsId;
    }

    public final void setGoogleAnalyticsId(java.lang.String googleAnalyticsId) {
        googleAnalyticsId = prepareGoogleAnalyticsId(googleAnalyticsId);
        if (isGoogleAnalyticsId(googleAnalyticsId)) return;
        this.googleAnalyticsId = googleAnalyticsId;
        fireModified("googleAnalyticsId="+googleAnalyticsId);
    }

    protected java.lang.String prepareGoogleAnalyticsId(java.lang.String googleAnalyticsId) {
        googleAnalyticsId = Str.removeUnreadableChars(googleAnalyticsId);
        return googleAnalyticsId;
    }

    public final boolean isGoogleAnalyticsIdSet() {
        return this.googleAnalyticsId != null;
    }

    public final boolean isGoogleAnalyticsId(java.lang.String googleAnalyticsId) {
        if (this.googleAnalyticsId == null && googleAnalyticsId == null) return true;
        return this.googleAnalyticsId != null && this.googleAnalyticsId.equals(googleAnalyticsId);
    }

    protected final void updateGoogleAnalyticsId(Object value) {
        setGoogleAnalyticsId((java.lang.String)value);
    }

    // -----------------------------------------------------------
    // - smtpServer
    // -----------------------------------------------------------

    private java.lang.String smtpServer;

    public final java.lang.String getSmtpServer() {
        return smtpServer;
    }

    public final void setSmtpServer(java.lang.String smtpServer) {
        smtpServer = prepareSmtpServer(smtpServer);
        if (isSmtpServer(smtpServer)) return;
        this.smtpServer = smtpServer;
        fireModified("smtpServer="+smtpServer);
    }

    protected java.lang.String prepareSmtpServer(java.lang.String smtpServer) {
        smtpServer = Str.removeUnreadableChars(smtpServer);
        return smtpServer;
    }

    public final boolean isSmtpServerSet() {
        return this.smtpServer != null;
    }

    public final boolean isSmtpServer(java.lang.String smtpServer) {
        if (this.smtpServer == null && smtpServer == null) return true;
        return this.smtpServer != null && this.smtpServer.equals(smtpServer);
    }

    protected final void updateSmtpServer(Object value) {
        setSmtpServer((java.lang.String)value);
    }

    // -----------------------------------------------------------
    // - smtpPort
    // -----------------------------------------------------------

    private java.lang.Integer smtpPort;

    public final java.lang.Integer getSmtpPort() {
        return smtpPort;
    }

    public final void setSmtpPort(java.lang.Integer smtpPort) {
        smtpPort = prepareSmtpPort(smtpPort);
        if (isSmtpPort(smtpPort)) return;
        this.smtpPort = smtpPort;
        fireModified("smtpPort="+smtpPort);
    }

    protected java.lang.Integer prepareSmtpPort(java.lang.Integer smtpPort) {
        return smtpPort;
    }

    public final boolean isSmtpPortSet() {
        return this.smtpPort != null;
    }

    public final boolean isSmtpPort(java.lang.Integer smtpPort) {
        if (this.smtpPort == null && smtpPort == null) return true;
        return this.smtpPort != null && this.smtpPort.equals(smtpPort);
    }

    protected final void updateSmtpPort(Object value) {
        setSmtpPort((java.lang.Integer)value);
    }

    // -----------------------------------------------------------
    // - smtpTls
    // -----------------------------------------------------------

    private boolean smtpTls;

    public final boolean isSmtpTls() {
        return smtpTls;
    }

    public final void setSmtpTls(boolean smtpTls) {
        smtpTls = prepareSmtpTls(smtpTls);
        if (isSmtpTls(smtpTls)) return;
        this.smtpTls = smtpTls;
        fireModified("smtpTls="+smtpTls);
    }

    protected boolean prepareSmtpTls(boolean smtpTls) {
        return smtpTls;
    }

    public final boolean isSmtpTls(boolean smtpTls) {
        return this.smtpTls == smtpTls;
    }

    protected final void updateSmtpTls(Object value) {
        setSmtpTls((Boolean)value);
    }

    // -----------------------------------------------------------
    // - smtpUser
    // -----------------------------------------------------------

    private java.lang.String smtpUser;

    public final java.lang.String getSmtpUser() {
        return smtpUser;
    }

    public final void setSmtpUser(java.lang.String smtpUser) {
        smtpUser = prepareSmtpUser(smtpUser);
        if (isSmtpUser(smtpUser)) return;
        this.smtpUser = smtpUser;
        fireModified("smtpUser="+smtpUser);
    }

    protected java.lang.String prepareSmtpUser(java.lang.String smtpUser) {
        smtpUser = Str.removeUnreadableChars(smtpUser);
        return smtpUser;
    }

    public final boolean isSmtpUserSet() {
        return this.smtpUser != null;
    }

    public final boolean isSmtpUser(java.lang.String smtpUser) {
        if (this.smtpUser == null && smtpUser == null) return true;
        return this.smtpUser != null && this.smtpUser.equals(smtpUser);
    }

    protected final void updateSmtpUser(Object value) {
        setSmtpUser((java.lang.String)value);
    }

    // -----------------------------------------------------------
    // - smtpPassword
    // -----------------------------------------------------------

    private java.lang.String smtpPassword;

    public final java.lang.String getSmtpPassword() {
        return smtpPassword;
    }

    public final void setSmtpPassword(java.lang.String smtpPassword) {
        smtpPassword = prepareSmtpPassword(smtpPassword);
        if (isSmtpPassword(smtpPassword)) return;
        this.smtpPassword = smtpPassword;
        fireModified("smtpPassword="+smtpPassword);
    }

    protected java.lang.String prepareSmtpPassword(java.lang.String smtpPassword) {
        smtpPassword = Str.removeUnreadableChars(smtpPassword);
        return smtpPassword;
    }

    public final boolean isSmtpPasswordSet() {
        return this.smtpPassword != null;
    }

    public final boolean isSmtpPassword(java.lang.String smtpPassword) {
        if (this.smtpPassword == null && smtpPassword == null) return true;
        return this.smtpPassword != null && this.smtpPassword.equals(smtpPassword);
    }

    protected final void updateSmtpPassword(Object value) {
        setSmtpPassword((java.lang.String)value);
    }

    // -----------------------------------------------------------
    // - smtpFrom
    // -----------------------------------------------------------

    private java.lang.String smtpFrom;

    public final java.lang.String getSmtpFrom() {
        return smtpFrom;
    }

    public final void setSmtpFrom(java.lang.String smtpFrom) {
        smtpFrom = prepareSmtpFrom(smtpFrom);
        if (isSmtpFrom(smtpFrom)) return;
        this.smtpFrom = smtpFrom;
        fireModified("smtpFrom="+smtpFrom);
    }

    protected java.lang.String prepareSmtpFrom(java.lang.String smtpFrom) {
        smtpFrom = Str.removeUnreadableChars(smtpFrom);
        return smtpFrom;
    }

    public final boolean isSmtpFromSet() {
        return this.smtpFrom != null;
    }

    public final boolean isSmtpFrom(java.lang.String smtpFrom) {
        if (this.smtpFrom == null && smtpFrom == null) return true;
        return this.smtpFrom != null && this.smtpFrom.equals(smtpFrom);
    }

    protected final void updateSmtpFrom(Object value) {
        setSmtpFrom((java.lang.String)value);
    }

    // -----------------------------------------------------------
    // - loginPageLogoUrl
    // -----------------------------------------------------------

    private java.lang.String loginPageLogoUrl;

    public final java.lang.String getLoginPageLogoUrl() {
        return loginPageLogoUrl;
    }

    public final void setLoginPageLogoUrl(java.lang.String loginPageLogoUrl) {
        loginPageLogoUrl = prepareLoginPageLogoUrl(loginPageLogoUrl);
        if (isLoginPageLogoUrl(loginPageLogoUrl)) return;
        this.loginPageLogoUrl = loginPageLogoUrl;
        fireModified("loginPageLogoUrl="+loginPageLogoUrl);
    }

    protected java.lang.String prepareLoginPageLogoUrl(java.lang.String loginPageLogoUrl) {
        loginPageLogoUrl = Str.removeUnreadableChars(loginPageLogoUrl);
        return loginPageLogoUrl;
    }

    public final boolean isLoginPageLogoUrlSet() {
        return this.loginPageLogoUrl != null;
    }

    public final boolean isLoginPageLogoUrl(java.lang.String loginPageLogoUrl) {
        if (this.loginPageLogoUrl == null && loginPageLogoUrl == null) return true;
        return this.loginPageLogoUrl != null && this.loginPageLogoUrl.equals(loginPageLogoUrl);
    }

    protected final void updateLoginPageLogoUrl(Object value) {
        setLoginPageLogoUrl((java.lang.String)value);
    }

    // -----------------------------------------------------------
    // - loginPageMessage
    // -----------------------------------------------------------

    private java.lang.String loginPageMessage;

    public final java.lang.String getLoginPageMessage() {
        return loginPageMessage;
    }

    public final void setLoginPageMessage(java.lang.String loginPageMessage) {
        loginPageMessage = prepareLoginPageMessage(loginPageMessage);
        if (isLoginPageMessage(loginPageMessage)) return;
        this.loginPageMessage = loginPageMessage;
        fireModified("loginPageMessage="+loginPageMessage);
    }

    protected java.lang.String prepareLoginPageMessage(java.lang.String loginPageMessage) {
        loginPageMessage = Str.removeUnreadableChars(loginPageMessage);
        return loginPageMessage;
    }

    public final boolean isLoginPageMessageSet() {
        return this.loginPageMessage != null;
    }

    public final boolean isLoginPageMessage(java.lang.String loginPageMessage) {
        if (this.loginPageMessage == null && loginPageMessage == null) return true;
        return this.loginPageMessage != null && this.loginPageMessage.equals(loginPageMessage);
    }

    protected final void updateLoginPageMessage(Object value) {
        setLoginPageMessage((java.lang.String)value);
    }

    // -----------------------------------------------------------
    // - registerPageMessage
    // -----------------------------------------------------------

    private java.lang.String registerPageMessage;

    public final java.lang.String getRegisterPageMessage() {
        return registerPageMessage;
    }

    public final void setRegisterPageMessage(java.lang.String registerPageMessage) {
        registerPageMessage = prepareRegisterPageMessage(registerPageMessage);
        if (isRegisterPageMessage(registerPageMessage)) return;
        this.registerPageMessage = registerPageMessage;
        fireModified("registerPageMessage="+registerPageMessage);
    }

    protected java.lang.String prepareRegisterPageMessage(java.lang.String registerPageMessage) {
        registerPageMessage = Str.removeUnreadableChars(registerPageMessage);
        return registerPageMessage;
    }

    public final boolean isRegisterPageMessageSet() {
        return this.registerPageMessage != null;
    }

    public final boolean isRegisterPageMessage(java.lang.String registerPageMessage) {
        if (this.registerPageMessage == null && registerPageMessage == null) return true;
        return this.registerPageMessage != null && this.registerPageMessage.equals(registerPageMessage);
    }

    protected final void updateRegisterPageMessage(Object value) {
        setRegisterPageMessage((java.lang.String)value);
    }

    // -----------------------------------------------------------
    // - aboutPageMessage
    // -----------------------------------------------------------

    private java.lang.String aboutPageMessage;

    public final java.lang.String getAboutPageMessage() {
        return aboutPageMessage;
    }

    public final void setAboutPageMessage(java.lang.String aboutPageMessage) {
        aboutPageMessage = prepareAboutPageMessage(aboutPageMessage);
        if (isAboutPageMessage(aboutPageMessage)) return;
        this.aboutPageMessage = aboutPageMessage;
        fireModified("aboutPageMessage="+aboutPageMessage);
    }

    protected java.lang.String prepareAboutPageMessage(java.lang.String aboutPageMessage) {
        aboutPageMessage = Str.removeUnreadableChars(aboutPageMessage);
        return aboutPageMessage;
    }

    public final boolean isAboutPageMessageSet() {
        return this.aboutPageMessage != null;
    }

    public final boolean isAboutPageMessage(java.lang.String aboutPageMessage) {
        if (this.aboutPageMessage == null && aboutPageMessage == null) return true;
        return this.aboutPageMessage != null && this.aboutPageMessage.equals(aboutPageMessage);
    }

    protected final void updateAboutPageMessage(Object value) {
        setAboutPageMessage((java.lang.String)value);
    }

    // -----------------------------------------------------------
    // - userEmailMandatory
    // -----------------------------------------------------------

    private boolean userEmailMandatory;

    public final boolean isUserEmailMandatory() {
        return userEmailMandatory;
    }

    public final void setUserEmailMandatory(boolean userEmailMandatory) {
        userEmailMandatory = prepareUserEmailMandatory(userEmailMandatory);
        if (isUserEmailMandatory(userEmailMandatory)) return;
        this.userEmailMandatory = userEmailMandatory;
        fireModified("userEmailMandatory="+userEmailMandatory);
    }

    protected boolean prepareUserEmailMandatory(boolean userEmailMandatory) {
        return userEmailMandatory;
    }

    public final boolean isUserEmailMandatory(boolean userEmailMandatory) {
        return this.userEmailMandatory == userEmailMandatory;
    }

    protected final void updateUserEmailMandatory(Object value) {
        setUserEmailMandatory((Boolean)value);
    }

    // -----------------------------------------------------------
    // - registrationDisabled
    // -----------------------------------------------------------

    private boolean registrationDisabled;

    public final boolean isRegistrationDisabled() {
        return registrationDisabled;
    }

    public final void setRegistrationDisabled(boolean registrationDisabled) {
        registrationDisabled = prepareRegistrationDisabled(registrationDisabled);
        if (isRegistrationDisabled(registrationDisabled)) return;
        this.registrationDisabled = registrationDisabled;
        fireModified("registrationDisabled="+registrationDisabled);
    }

    protected boolean prepareRegistrationDisabled(boolean registrationDisabled) {
        return registrationDisabled;
    }

    public final boolean isRegistrationDisabled(boolean registrationDisabled) {
        return this.registrationDisabled == registrationDisabled;
    }

    protected final void updateRegistrationDisabled(Object value) {
        setRegistrationDisabled((Boolean)value);
    }

    // -----------------------------------------------------------
    // - defaultUserPassword
    // -----------------------------------------------------------

    private java.lang.String defaultUserPassword;

    public final java.lang.String getDefaultUserPassword() {
        return defaultUserPassword;
    }

    public final void setDefaultUserPassword(java.lang.String defaultUserPassword) {
        defaultUserPassword = prepareDefaultUserPassword(defaultUserPassword);
        if (isDefaultUserPassword(defaultUserPassword)) return;
        this.defaultUserPassword = defaultUserPassword;
        fireModified("defaultUserPassword="+defaultUserPassword);
    }

    protected java.lang.String prepareDefaultUserPassword(java.lang.String defaultUserPassword) {
        defaultUserPassword = Str.removeUnreadableChars(defaultUserPassword);
        return defaultUserPassword;
    }

    public final boolean isDefaultUserPasswordSet() {
        return this.defaultUserPassword != null;
    }

    public final boolean isDefaultUserPassword(java.lang.String defaultUserPassword) {
        if (this.defaultUserPassword == null && defaultUserPassword == null) return true;
        return this.defaultUserPassword != null && this.defaultUserPassword.equals(defaultUserPassword);
    }

    protected final void updateDefaultUserPassword(Object value) {
        setDefaultUserPassword((java.lang.String)value);
    }

    public void updateProperties(Map<?, ?> properties) {
        for (Map.Entry entry : properties.entrySet()) {
            String property = (String) entry.getKey();
            if (property.equals("id")) continue;
            Object value = entry.getValue();
            if (property.equals("url")) updateUrl(value);
            if (property.equals("adminEmail")) updateAdminEmail(value);
            if (property.equals("googleAnalyticsId")) updateGoogleAnalyticsId(value);
            if (property.equals("smtpServer")) updateSmtpServer(value);
            if (property.equals("smtpPort")) updateSmtpPort(value);
            if (property.equals("smtpTls")) updateSmtpTls(value);
            if (property.equals("smtpUser")) updateSmtpUser(value);
            if (property.equals("smtpPassword")) updateSmtpPassword(value);
            if (property.equals("smtpFrom")) updateSmtpFrom(value);
            if (property.equals("loginPageLogoUrl")) updateLoginPageLogoUrl(value);
            if (property.equals("loginPageMessage")) updateLoginPageMessage(value);
            if (property.equals("registerPageMessage")) updateRegisterPageMessage(value);
            if (property.equals("aboutPageMessage")) updateAboutPageMessage(value);
            if (property.equals("userEmailMandatory")) updateUserEmailMandatory(value);
            if (property.equals("registrationDisabled")) updateRegistrationDisabled(value);
            if (property.equals("defaultUserPassword")) updateDefaultUserPassword(value);
        }
    }

    protected void repairDeadReferences(String entityId) {
        super.repairDeadReferences(entityId);
    }

    // --- ensure integrity ---

    public void ensureIntegrity() {
        super.ensureIntegrity();
    }

    static SystemConfigDao systemConfigDao;

    public static final void setSystemConfigDao(SystemConfigDao systemConfigDao) {
        GSystemConfig.systemConfigDao = systemConfigDao;
    }

}