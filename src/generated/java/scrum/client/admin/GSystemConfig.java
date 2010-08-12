// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.GwtEntityGenerator










package scrum.client.admin;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.core.logging.Log;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;
import scrum.client.common.*;
import ilarkesto.gwt.client.*;

public abstract class GSystemConfig
            extends scrum.client.common.AScrumGwtEntity {

    protected scrum.client.Dao getDao() {
        return scrum.client.Dao.get();
    }

    public GSystemConfig() {
    }

    public GSystemConfig(Map data) {
        super(data);
        updateProperties(data);
    }

    public static final String ENTITY_TYPE = "systemConfig";

    @Override
    public final String getEntityType() {
        return ENTITY_TYPE;
    }

    // --- url ---

    private java.lang.String url ;

    public final java.lang.String getUrl() {
        return this.url ;
    }

    public final SystemConfig setUrl(java.lang.String url) {
        if (isUrl(url)) return (SystemConfig)this;
        this.url = url ;
        propertyChanged("url", this.url);
        return (SystemConfig)this;
    }

    public final boolean isUrl(java.lang.String url) {
        return equals(this.url, url);
    }

    private transient UrlModel urlModel;

    public UrlModel getUrlModel() {
        if (urlModel == null) urlModel = createUrlModel();
        return urlModel;
    }

    protected UrlModel createUrlModel() { return new UrlModel(); }

    protected class UrlModel extends ilarkesto.gwt.client.editor.ATextEditorModel {

        @Override
        public String getId() {
            return "SystemConfig_url";
        }

        @Override
        public java.lang.String getValue() {
            return getUrl();
        }

        @Override
        public void setValue(java.lang.String value) {
            setUrl(value);
        }

        @Override
        protected void onChangeValue(java.lang.String oldValue, java.lang.String newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

    // --- adminEmail ---

    private java.lang.String adminEmail ;

    public final java.lang.String getAdminEmail() {
        return this.adminEmail ;
    }

    public final SystemConfig setAdminEmail(java.lang.String adminEmail) {
        if (isAdminEmail(adminEmail)) return (SystemConfig)this;
        this.adminEmail = adminEmail ;
        propertyChanged("adminEmail", this.adminEmail);
        return (SystemConfig)this;
    }

    public final boolean isAdminEmail(java.lang.String adminEmail) {
        return equals(this.adminEmail, adminEmail);
    }

    private transient AdminEmailModel adminEmailModel;

    public AdminEmailModel getAdminEmailModel() {
        if (adminEmailModel == null) adminEmailModel = createAdminEmailModel();
        return adminEmailModel;
    }

    protected AdminEmailModel createAdminEmailModel() { return new AdminEmailModel(); }

    protected class AdminEmailModel extends ilarkesto.gwt.client.editor.ATextEditorModel {

        @Override
        public String getId() {
            return "SystemConfig_adminEmail";
        }

        @Override
        public java.lang.String getValue() {
            return getAdminEmail();
        }

        @Override
        public void setValue(java.lang.String value) {
            setAdminEmail(value);
        }

        @Override
        protected void onChangeValue(java.lang.String oldValue, java.lang.String newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

    // --- googleAnalyticsId ---

    private java.lang.String googleAnalyticsId ;

    public final java.lang.String getGoogleAnalyticsId() {
        return this.googleAnalyticsId ;
    }

    public final SystemConfig setGoogleAnalyticsId(java.lang.String googleAnalyticsId) {
        if (isGoogleAnalyticsId(googleAnalyticsId)) return (SystemConfig)this;
        this.googleAnalyticsId = googleAnalyticsId ;
        propertyChanged("googleAnalyticsId", this.googleAnalyticsId);
        return (SystemConfig)this;
    }

    public final boolean isGoogleAnalyticsId(java.lang.String googleAnalyticsId) {
        return equals(this.googleAnalyticsId, googleAnalyticsId);
    }

    private transient GoogleAnalyticsIdModel googleAnalyticsIdModel;

    public GoogleAnalyticsIdModel getGoogleAnalyticsIdModel() {
        if (googleAnalyticsIdModel == null) googleAnalyticsIdModel = createGoogleAnalyticsIdModel();
        return googleAnalyticsIdModel;
    }

    protected GoogleAnalyticsIdModel createGoogleAnalyticsIdModel() { return new GoogleAnalyticsIdModel(); }

    protected class GoogleAnalyticsIdModel extends ilarkesto.gwt.client.editor.ATextEditorModel {

        @Override
        public String getId() {
            return "SystemConfig_googleAnalyticsId";
        }

        @Override
        public java.lang.String getValue() {
            return getGoogleAnalyticsId();
        }

        @Override
        public void setValue(java.lang.String value) {
            setGoogleAnalyticsId(value);
        }

        @Override
        protected void onChangeValue(java.lang.String oldValue, java.lang.String newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

    // --- smtpServer ---

    private java.lang.String smtpServer ;

    public final java.lang.String getSmtpServer() {
        return this.smtpServer ;
    }

    public final SystemConfig setSmtpServer(java.lang.String smtpServer) {
        if (isSmtpServer(smtpServer)) return (SystemConfig)this;
        this.smtpServer = smtpServer ;
        propertyChanged("smtpServer", this.smtpServer);
        return (SystemConfig)this;
    }

    public final boolean isSmtpServer(java.lang.String smtpServer) {
        return equals(this.smtpServer, smtpServer);
    }

    private transient SmtpServerModel smtpServerModel;

    public SmtpServerModel getSmtpServerModel() {
        if (smtpServerModel == null) smtpServerModel = createSmtpServerModel();
        return smtpServerModel;
    }

    protected SmtpServerModel createSmtpServerModel() { return new SmtpServerModel(); }

    protected class SmtpServerModel extends ilarkesto.gwt.client.editor.ATextEditorModel {

        @Override
        public String getId() {
            return "SystemConfig_smtpServer";
        }

        @Override
        public java.lang.String getValue() {
            return getSmtpServer();
        }

        @Override
        public void setValue(java.lang.String value) {
            setSmtpServer(value);
        }

        @Override
        protected void onChangeValue(java.lang.String oldValue, java.lang.String newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

    // --- smtpPort ---

    private java.lang.Integer smtpPort ;

    public final java.lang.Integer getSmtpPort() {
        return this.smtpPort ;
    }

    public final SystemConfig setSmtpPort(java.lang.Integer smtpPort) {
        if (isSmtpPort(smtpPort)) return (SystemConfig)this;
        this.smtpPort = smtpPort ;
        propertyChanged("smtpPort", this.smtpPort);
        return (SystemConfig)this;
    }

    public final boolean isSmtpPort(java.lang.Integer smtpPort) {
        return equals(this.smtpPort, smtpPort);
    }

    private transient SmtpPortModel smtpPortModel;

    public SmtpPortModel getSmtpPortModel() {
        if (smtpPortModel == null) smtpPortModel = createSmtpPortModel();
        return smtpPortModel;
    }

    protected SmtpPortModel createSmtpPortModel() { return new SmtpPortModel(); }

    protected class SmtpPortModel extends ilarkesto.gwt.client.editor.AIntegerEditorModel {

        @Override
        public String getId() {
            return "SystemConfig_smtpPort";
        }

        @Override
        public java.lang.Integer getValue() {
            return getSmtpPort();
        }

        @Override
        public void setValue(java.lang.Integer value) {
            setSmtpPort(value);
        }

            @Override
            public void increment() {
                setSmtpPort(getSmtpPort() + 1);
            }

            @Override
            public void decrement() {
                setSmtpPort(getSmtpPort() - 1);
            }

        @Override
        protected void onChangeValue(java.lang.Integer oldValue, java.lang.Integer newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

    // --- smtpTls ---

    private boolean smtpTls ;

    public final boolean isSmtpTls() {
        return this.smtpTls ;
    }

    public final SystemConfig setSmtpTls(boolean smtpTls) {
        if (isSmtpTls(smtpTls)) return (SystemConfig)this;
        this.smtpTls = smtpTls ;
        propertyChanged("smtpTls", this.smtpTls);
        return (SystemConfig)this;
    }

    public final boolean isSmtpTls(boolean smtpTls) {
        return equals(this.smtpTls, smtpTls);
    }

    private transient SmtpTlsModel smtpTlsModel;

    public SmtpTlsModel getSmtpTlsModel() {
        if (smtpTlsModel == null) smtpTlsModel = createSmtpTlsModel();
        return smtpTlsModel;
    }

    protected SmtpTlsModel createSmtpTlsModel() { return new SmtpTlsModel(); }

    protected class SmtpTlsModel extends ilarkesto.gwt.client.editor.ABooleanEditorModel {

        @Override
        public String getId() {
            return "SystemConfig_smtpTls";
        }

        @Override
        public java.lang.Boolean getValue() {
            return isSmtpTls();
        }

        @Override
        public void setValue(java.lang.Boolean value) {
            setSmtpTls(value);
        }

        @Override
        protected void onChangeValue(java.lang.Boolean oldValue, java.lang.Boolean newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

    // --- smtpUser ---

    private java.lang.String smtpUser ;

    public final java.lang.String getSmtpUser() {
        return this.smtpUser ;
    }

    public final SystemConfig setSmtpUser(java.lang.String smtpUser) {
        if (isSmtpUser(smtpUser)) return (SystemConfig)this;
        this.smtpUser = smtpUser ;
        propertyChanged("smtpUser", this.smtpUser);
        return (SystemConfig)this;
    }

    public final boolean isSmtpUser(java.lang.String smtpUser) {
        return equals(this.smtpUser, smtpUser);
    }

    private transient SmtpUserModel smtpUserModel;

    public SmtpUserModel getSmtpUserModel() {
        if (smtpUserModel == null) smtpUserModel = createSmtpUserModel();
        return smtpUserModel;
    }

    protected SmtpUserModel createSmtpUserModel() { return new SmtpUserModel(); }

    protected class SmtpUserModel extends ilarkesto.gwt.client.editor.ATextEditorModel {

        @Override
        public String getId() {
            return "SystemConfig_smtpUser";
        }

        @Override
        public java.lang.String getValue() {
            return getSmtpUser();
        }

        @Override
        public void setValue(java.lang.String value) {
            setSmtpUser(value);
        }

        @Override
        protected void onChangeValue(java.lang.String oldValue, java.lang.String newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

    // --- smtpPassword ---

    private java.lang.String smtpPassword ;

    public final java.lang.String getSmtpPassword() {
        return this.smtpPassword ;
    }

    public final SystemConfig setSmtpPassword(java.lang.String smtpPassword) {
        if (isSmtpPassword(smtpPassword)) return (SystemConfig)this;
        this.smtpPassword = smtpPassword ;
        propertyChanged("smtpPassword", this.smtpPassword);
        return (SystemConfig)this;
    }

    public final boolean isSmtpPassword(java.lang.String smtpPassword) {
        return equals(this.smtpPassword, smtpPassword);
    }

    private transient SmtpPasswordModel smtpPasswordModel;

    public SmtpPasswordModel getSmtpPasswordModel() {
        if (smtpPasswordModel == null) smtpPasswordModel = createSmtpPasswordModel();
        return smtpPasswordModel;
    }

    protected SmtpPasswordModel createSmtpPasswordModel() { return new SmtpPasswordModel(); }

    protected class SmtpPasswordModel extends ilarkesto.gwt.client.editor.ATextEditorModel {

        @Override
        public String getId() {
            return "SystemConfig_smtpPassword";
        }

        @Override
        public java.lang.String getValue() {
            return getSmtpPassword();
        }

        @Override
        public void setValue(java.lang.String value) {
            setSmtpPassword(value);
        }

        @Override
        protected void onChangeValue(java.lang.String oldValue, java.lang.String newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

    // --- smtpFrom ---

    private java.lang.String smtpFrom ;

    public final java.lang.String getSmtpFrom() {
        return this.smtpFrom ;
    }

    public final SystemConfig setSmtpFrom(java.lang.String smtpFrom) {
        if (isSmtpFrom(smtpFrom)) return (SystemConfig)this;
        this.smtpFrom = smtpFrom ;
        propertyChanged("smtpFrom", this.smtpFrom);
        return (SystemConfig)this;
    }

    public final boolean isSmtpFrom(java.lang.String smtpFrom) {
        return equals(this.smtpFrom, smtpFrom);
    }

    private transient SmtpFromModel smtpFromModel;

    public SmtpFromModel getSmtpFromModel() {
        if (smtpFromModel == null) smtpFromModel = createSmtpFromModel();
        return smtpFromModel;
    }

    protected SmtpFromModel createSmtpFromModel() { return new SmtpFromModel(); }

    protected class SmtpFromModel extends ilarkesto.gwt.client.editor.ATextEditorModel {

        @Override
        public String getId() {
            return "SystemConfig_smtpFrom";
        }

        @Override
        public java.lang.String getValue() {
            return getSmtpFrom();
        }

        @Override
        public void setValue(java.lang.String value) {
            setSmtpFrom(value);
        }

        @Override
        protected void onChangeValue(java.lang.String oldValue, java.lang.String newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

    // --- loginPageMessage ---

    private java.lang.String loginPageMessage ;

    public final java.lang.String getLoginPageMessage() {
        return this.loginPageMessage ;
    }

    public final SystemConfig setLoginPageMessage(java.lang.String loginPageMessage) {
        if (isLoginPageMessage(loginPageMessage)) return (SystemConfig)this;
        this.loginPageMessage = loginPageMessage ;
        propertyChanged("loginPageMessage", this.loginPageMessage);
        return (SystemConfig)this;
    }

    public final boolean isLoginPageMessage(java.lang.String loginPageMessage) {
        return equals(this.loginPageMessage, loginPageMessage);
    }

    private transient LoginPageMessageModel loginPageMessageModel;

    public LoginPageMessageModel getLoginPageMessageModel() {
        if (loginPageMessageModel == null) loginPageMessageModel = createLoginPageMessageModel();
        return loginPageMessageModel;
    }

    protected LoginPageMessageModel createLoginPageMessageModel() { return new LoginPageMessageModel(); }

    protected class LoginPageMessageModel extends ilarkesto.gwt.client.editor.ATextEditorModel {

        @Override
        public String getId() {
            return "SystemConfig_loginPageMessage";
        }

        @Override
        public java.lang.String getValue() {
            return getLoginPageMessage();
        }

        @Override
        public void setValue(java.lang.String value) {
            setLoginPageMessage(value);
        }

        @Override
        public boolean isRichtext() { return true; }

        @Override
        protected void onChangeValue(java.lang.String oldValue, java.lang.String newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

    // --- registerPageMessage ---

    private java.lang.String registerPageMessage ;

    public final java.lang.String getRegisterPageMessage() {
        return this.registerPageMessage ;
    }

    public final SystemConfig setRegisterPageMessage(java.lang.String registerPageMessage) {
        if (isRegisterPageMessage(registerPageMessage)) return (SystemConfig)this;
        this.registerPageMessage = registerPageMessage ;
        propertyChanged("registerPageMessage", this.registerPageMessage);
        return (SystemConfig)this;
    }

    public final boolean isRegisterPageMessage(java.lang.String registerPageMessage) {
        return equals(this.registerPageMessage, registerPageMessage);
    }

    private transient RegisterPageMessageModel registerPageMessageModel;

    public RegisterPageMessageModel getRegisterPageMessageModel() {
        if (registerPageMessageModel == null) registerPageMessageModel = createRegisterPageMessageModel();
        return registerPageMessageModel;
    }

    protected RegisterPageMessageModel createRegisterPageMessageModel() { return new RegisterPageMessageModel(); }

    protected class RegisterPageMessageModel extends ilarkesto.gwt.client.editor.ATextEditorModel {

        @Override
        public String getId() {
            return "SystemConfig_registerPageMessage";
        }

        @Override
        public java.lang.String getValue() {
            return getRegisterPageMessage();
        }

        @Override
        public void setValue(java.lang.String value) {
            setRegisterPageMessage(value);
        }

        @Override
        public boolean isRichtext() { return true; }

        @Override
        protected void onChangeValue(java.lang.String oldValue, java.lang.String newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

    // --- aboutPageMessage ---

    private java.lang.String aboutPageMessage ;

    public final java.lang.String getAboutPageMessage() {
        return this.aboutPageMessage ;
    }

    public final SystemConfig setAboutPageMessage(java.lang.String aboutPageMessage) {
        if (isAboutPageMessage(aboutPageMessage)) return (SystemConfig)this;
        this.aboutPageMessage = aboutPageMessage ;
        propertyChanged("aboutPageMessage", this.aboutPageMessage);
        return (SystemConfig)this;
    }

    public final boolean isAboutPageMessage(java.lang.String aboutPageMessage) {
        return equals(this.aboutPageMessage, aboutPageMessage);
    }

    private transient AboutPageMessageModel aboutPageMessageModel;

    public AboutPageMessageModel getAboutPageMessageModel() {
        if (aboutPageMessageModel == null) aboutPageMessageModel = createAboutPageMessageModel();
        return aboutPageMessageModel;
    }

    protected AboutPageMessageModel createAboutPageMessageModel() { return new AboutPageMessageModel(); }

    protected class AboutPageMessageModel extends ilarkesto.gwt.client.editor.ATextEditorModel {

        @Override
        public String getId() {
            return "SystemConfig_aboutPageMessage";
        }

        @Override
        public java.lang.String getValue() {
            return getAboutPageMessage();
        }

        @Override
        public void setValue(java.lang.String value) {
            setAboutPageMessage(value);
        }

        @Override
        public boolean isRichtext() { return true; }

        @Override
        protected void onChangeValue(java.lang.String oldValue, java.lang.String newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

    // --- userEmailMandatory ---

    private boolean userEmailMandatory ;

    public final boolean isUserEmailMandatory() {
        return this.userEmailMandatory ;
    }

    public final SystemConfig setUserEmailMandatory(boolean userEmailMandatory) {
        if (isUserEmailMandatory(userEmailMandatory)) return (SystemConfig)this;
        this.userEmailMandatory = userEmailMandatory ;
        propertyChanged("userEmailMandatory", this.userEmailMandatory);
        return (SystemConfig)this;
    }

    public final boolean isUserEmailMandatory(boolean userEmailMandatory) {
        return equals(this.userEmailMandatory, userEmailMandatory);
    }

    private transient UserEmailMandatoryModel userEmailMandatoryModel;

    public UserEmailMandatoryModel getUserEmailMandatoryModel() {
        if (userEmailMandatoryModel == null) userEmailMandatoryModel = createUserEmailMandatoryModel();
        return userEmailMandatoryModel;
    }

    protected UserEmailMandatoryModel createUserEmailMandatoryModel() { return new UserEmailMandatoryModel(); }

    protected class UserEmailMandatoryModel extends ilarkesto.gwt.client.editor.ABooleanEditorModel {

        @Override
        public String getId() {
            return "SystemConfig_userEmailMandatory";
        }

        @Override
        public java.lang.Boolean getValue() {
            return isUserEmailMandatory();
        }

        @Override
        public void setValue(java.lang.Boolean value) {
            setUserEmailMandatory(value);
        }

        @Override
        protected void onChangeValue(java.lang.Boolean oldValue, java.lang.Boolean newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

    // --- registrationDisabled ---

    private boolean registrationDisabled ;

    public final boolean isRegistrationDisabled() {
        return this.registrationDisabled ;
    }

    public final SystemConfig setRegistrationDisabled(boolean registrationDisabled) {
        if (isRegistrationDisabled(registrationDisabled)) return (SystemConfig)this;
        this.registrationDisabled = registrationDisabled ;
        propertyChanged("registrationDisabled", this.registrationDisabled);
        return (SystemConfig)this;
    }

    public final boolean isRegistrationDisabled(boolean registrationDisabled) {
        return equals(this.registrationDisabled, registrationDisabled);
    }

    private transient RegistrationDisabledModel registrationDisabledModel;

    public RegistrationDisabledModel getRegistrationDisabledModel() {
        if (registrationDisabledModel == null) registrationDisabledModel = createRegistrationDisabledModel();
        return registrationDisabledModel;
    }

    protected RegistrationDisabledModel createRegistrationDisabledModel() { return new RegistrationDisabledModel(); }

    protected class RegistrationDisabledModel extends ilarkesto.gwt.client.editor.ABooleanEditorModel {

        @Override
        public String getId() {
            return "SystemConfig_registrationDisabled";
        }

        @Override
        public java.lang.Boolean getValue() {
            return isRegistrationDisabled();
        }

        @Override
        public void setValue(java.lang.Boolean value) {
            setRegistrationDisabled(value);
        }

        @Override
        protected void onChangeValue(java.lang.Boolean oldValue, java.lang.Boolean newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

    // --- defaultUserPassword ---

    private java.lang.String defaultUserPassword ;

    public final java.lang.String getDefaultUserPassword() {
        return this.defaultUserPassword ;
    }

    public final SystemConfig setDefaultUserPassword(java.lang.String defaultUserPassword) {
        if (isDefaultUserPassword(defaultUserPassword)) return (SystemConfig)this;
        this.defaultUserPassword = defaultUserPassword ;
        propertyChanged("defaultUserPassword", this.defaultUserPassword);
        return (SystemConfig)this;
    }

    public final boolean isDefaultUserPassword(java.lang.String defaultUserPassword) {
        return equals(this.defaultUserPassword, defaultUserPassword);
    }

    private transient DefaultUserPasswordModel defaultUserPasswordModel;

    public DefaultUserPasswordModel getDefaultUserPasswordModel() {
        if (defaultUserPasswordModel == null) defaultUserPasswordModel = createDefaultUserPasswordModel();
        return defaultUserPasswordModel;
    }

    protected DefaultUserPasswordModel createDefaultUserPasswordModel() { return new DefaultUserPasswordModel(); }

    protected class DefaultUserPasswordModel extends ilarkesto.gwt.client.editor.ATextEditorModel {

        @Override
        public String getId() {
            return "SystemConfig_defaultUserPassword";
        }

        @Override
        public java.lang.String getValue() {
            return getDefaultUserPassword();
        }

        @Override
        public void setValue(java.lang.String value) {
            setDefaultUserPassword(value);
        }

        @Override
        protected void onChangeValue(java.lang.String oldValue, java.lang.String newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

    // --- update properties by map ---

    public void updateProperties(Map props) {
        url  = (java.lang.String) props.get("url");
        adminEmail  = (java.lang.String) props.get("adminEmail");
        googleAnalyticsId  = (java.lang.String) props.get("googleAnalyticsId");
        smtpServer  = (java.lang.String) props.get("smtpServer");
        smtpPort  = (java.lang.Integer) props.get("smtpPort");
        smtpTls  = (Boolean) props.get("smtpTls");
        smtpUser  = (java.lang.String) props.get("smtpUser");
        smtpPassword  = (java.lang.String) props.get("smtpPassword");
        smtpFrom  = (java.lang.String) props.get("smtpFrom");
        loginPageMessage  = (java.lang.String) props.get("loginPageMessage");
        registerPageMessage  = (java.lang.String) props.get("registerPageMessage");
        aboutPageMessage  = (java.lang.String) props.get("aboutPageMessage");
        userEmailMandatory  = (Boolean) props.get("userEmailMandatory");
        registrationDisabled  = (Boolean) props.get("registrationDisabled");
        defaultUserPassword  = (java.lang.String) props.get("defaultUserPassword");
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
        properties.put("loginPageMessage", this.loginPageMessage);
        properties.put("registerPageMessage", this.registerPageMessage);
        properties.put("aboutPageMessage", this.aboutPageMessage);
        properties.put("userEmailMandatory", this.userEmailMandatory);
        properties.put("registrationDisabled", this.registrationDisabled);
        properties.put("defaultUserPassword", this.defaultUserPassword);
    }

}