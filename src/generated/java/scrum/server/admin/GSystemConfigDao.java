// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.DaoGenerator










package scrum.server.admin;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.core.logging.Log;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;
import ilarkesto.fp.*;

public abstract class GSystemConfigDao
            extends ilarkesto.persistence.ADao<SystemConfig> {

    public final String getEntityName() {
        return SystemConfig.TYPE;
    }

    public final Class getEntityClass() {
        return SystemConfig.class;
    }

    public Set<SystemConfig> getEntitiesVisibleForUser(final scrum.server.admin.User user) {
        return getEntities(new Predicate<SystemConfig>() {
            public boolean test(SystemConfig e) {
                return Auth.isVisible(e, user);
            }
        });
    }

    // --- clear caches ---
    public void clearCaches() {
        systemConfigsByUrlCache.clear();
        urlsCache = null;
        systemConfigsByAdminEmailCache.clear();
        adminEmailsCache = null;
        systemConfigsByGoogleAnalyticsIdCache.clear();
        googleAnalyticsIdsCache = null;
        systemConfigsBySmtpServerCache.clear();
        smtpServersCache = null;
        systemConfigsBySmtpPortCache.clear();
        smtpPortsCache = null;
        systemConfigsBySmtpTlsCache.clear();
        systemConfigsBySmtpUserCache.clear();
        smtpUsersCache = null;
        systemConfigsBySmtpPasswordCache.clear();
        smtpPasswordsCache = null;
        systemConfigsBySmtpFromCache.clear();
        smtpFromsCache = null;
        systemConfigsByLoginPageLogoUrlCache.clear();
        loginPageLogoUrlsCache = null;
        systemConfigsByLoginPageMessageCache.clear();
        loginPageMessagesCache = null;
        systemConfigsByRegisterPageMessageCache.clear();
        registerPageMessagesCache = null;
        systemConfigsByAboutPageMessageCache.clear();
        aboutPageMessagesCache = null;
        systemConfigsByUserEmailMandatoryCache.clear();
        systemConfigsByRegistrationDisabledCache.clear();
        systemConfigsByDefaultUserPasswordCache.clear();
        defaultUserPasswordsCache = null;
    }

    @Override
    public void entityDeleted(EntityEvent event) {
        super.entityDeleted(event);
        if (event.getEntity() instanceof SystemConfig) {
            clearCaches();
        }
    }

    @Override
    public void entitySaved(EntityEvent event) {
        super.entitySaved(event);
        if (event.getEntity() instanceof SystemConfig) {
            clearCaches();
        }
    }

    // -----------------------------------------------------------
    // - url
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<SystemConfig>> systemConfigsByUrlCache = new Cache<java.lang.String,Set<SystemConfig>>(
            new Cache.Factory<java.lang.String,Set<SystemConfig>>() {
                public Set<SystemConfig> create(java.lang.String url) {
                    return getEntities(new IsUrl(url));
                }
            });

    public final Set<SystemConfig> getSystemConfigsByUrl(java.lang.String url) {
        return systemConfigsByUrlCache.get(url);
    }
    private Set<java.lang.String> urlsCache;

    public final Set<java.lang.String> getUrls() {
        if (urlsCache == null) {
            urlsCache = new HashSet<java.lang.String>();
            for (SystemConfig e : getEntities()) {
                if (e.isUrlSet()) urlsCache.add(e.getUrl());
            }
        }
        return urlsCache;
    }

    private static class IsUrl implements Predicate<SystemConfig> {

        private java.lang.String value;

        public IsUrl(java.lang.String value) {
            this.value = value;
        }

        public boolean test(SystemConfig e) {
            return e.isUrl(value);
        }

    }

    // -----------------------------------------------------------
    // - adminEmail
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<SystemConfig>> systemConfigsByAdminEmailCache = new Cache<java.lang.String,Set<SystemConfig>>(
            new Cache.Factory<java.lang.String,Set<SystemConfig>>() {
                public Set<SystemConfig> create(java.lang.String adminEmail) {
                    return getEntities(new IsAdminEmail(adminEmail));
                }
            });

    public final Set<SystemConfig> getSystemConfigsByAdminEmail(java.lang.String adminEmail) {
        return systemConfigsByAdminEmailCache.get(adminEmail);
    }
    private Set<java.lang.String> adminEmailsCache;

    public final Set<java.lang.String> getAdminEmails() {
        if (adminEmailsCache == null) {
            adminEmailsCache = new HashSet<java.lang.String>();
            for (SystemConfig e : getEntities()) {
                if (e.isAdminEmailSet()) adminEmailsCache.add(e.getAdminEmail());
            }
        }
        return adminEmailsCache;
    }

    private static class IsAdminEmail implements Predicate<SystemConfig> {

        private java.lang.String value;

        public IsAdminEmail(java.lang.String value) {
            this.value = value;
        }

        public boolean test(SystemConfig e) {
            return e.isAdminEmail(value);
        }

    }

    // -----------------------------------------------------------
    // - googleAnalyticsId
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<SystemConfig>> systemConfigsByGoogleAnalyticsIdCache = new Cache<java.lang.String,Set<SystemConfig>>(
            new Cache.Factory<java.lang.String,Set<SystemConfig>>() {
                public Set<SystemConfig> create(java.lang.String googleAnalyticsId) {
                    return getEntities(new IsGoogleAnalyticsId(googleAnalyticsId));
                }
            });

    public final Set<SystemConfig> getSystemConfigsByGoogleAnalyticsId(java.lang.String googleAnalyticsId) {
        return systemConfigsByGoogleAnalyticsIdCache.get(googleAnalyticsId);
    }
    private Set<java.lang.String> googleAnalyticsIdsCache;

    public final Set<java.lang.String> getGoogleAnalyticsIds() {
        if (googleAnalyticsIdsCache == null) {
            googleAnalyticsIdsCache = new HashSet<java.lang.String>();
            for (SystemConfig e : getEntities()) {
                if (e.isGoogleAnalyticsIdSet()) googleAnalyticsIdsCache.add(e.getGoogleAnalyticsId());
            }
        }
        return googleAnalyticsIdsCache;
    }

    private static class IsGoogleAnalyticsId implements Predicate<SystemConfig> {

        private java.lang.String value;

        public IsGoogleAnalyticsId(java.lang.String value) {
            this.value = value;
        }

        public boolean test(SystemConfig e) {
            return e.isGoogleAnalyticsId(value);
        }

    }

    // -----------------------------------------------------------
    // - smtpServer
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<SystemConfig>> systemConfigsBySmtpServerCache = new Cache<java.lang.String,Set<SystemConfig>>(
            new Cache.Factory<java.lang.String,Set<SystemConfig>>() {
                public Set<SystemConfig> create(java.lang.String smtpServer) {
                    return getEntities(new IsSmtpServer(smtpServer));
                }
            });

    public final Set<SystemConfig> getSystemConfigsBySmtpServer(java.lang.String smtpServer) {
        return systemConfigsBySmtpServerCache.get(smtpServer);
    }
    private Set<java.lang.String> smtpServersCache;

    public final Set<java.lang.String> getSmtpServers() {
        if (smtpServersCache == null) {
            smtpServersCache = new HashSet<java.lang.String>();
            for (SystemConfig e : getEntities()) {
                if (e.isSmtpServerSet()) smtpServersCache.add(e.getSmtpServer());
            }
        }
        return smtpServersCache;
    }

    private static class IsSmtpServer implements Predicate<SystemConfig> {

        private java.lang.String value;

        public IsSmtpServer(java.lang.String value) {
            this.value = value;
        }

        public boolean test(SystemConfig e) {
            return e.isSmtpServer(value);
        }

    }

    // -----------------------------------------------------------
    // - smtpPort
    // -----------------------------------------------------------

    private final Cache<java.lang.Integer,Set<SystemConfig>> systemConfigsBySmtpPortCache = new Cache<java.lang.Integer,Set<SystemConfig>>(
            new Cache.Factory<java.lang.Integer,Set<SystemConfig>>() {
                public Set<SystemConfig> create(java.lang.Integer smtpPort) {
                    return getEntities(new IsSmtpPort(smtpPort));
                }
            });

    public final Set<SystemConfig> getSystemConfigsBySmtpPort(java.lang.Integer smtpPort) {
        return systemConfigsBySmtpPortCache.get(smtpPort);
    }
    private Set<java.lang.Integer> smtpPortsCache;

    public final Set<java.lang.Integer> getSmtpPorts() {
        if (smtpPortsCache == null) {
            smtpPortsCache = new HashSet<java.lang.Integer>();
            for (SystemConfig e : getEntities()) {
                if (e.isSmtpPortSet()) smtpPortsCache.add(e.getSmtpPort());
            }
        }
        return smtpPortsCache;
    }

    private static class IsSmtpPort implements Predicate<SystemConfig> {

        private java.lang.Integer value;

        public IsSmtpPort(java.lang.Integer value) {
            this.value = value;
        }

        public boolean test(SystemConfig e) {
            return e.isSmtpPort(value);
        }

    }

    // -----------------------------------------------------------
    // - smtpTls
    // -----------------------------------------------------------

    private final Cache<Boolean,Set<SystemConfig>> systemConfigsBySmtpTlsCache = new Cache<Boolean,Set<SystemConfig>>(
            new Cache.Factory<Boolean,Set<SystemConfig>>() {
                public Set<SystemConfig> create(Boolean smtpTls) {
                    return getEntities(new IsSmtpTls(smtpTls));
                }
            });

    public final Set<SystemConfig> getSystemConfigsBySmtpTls(boolean smtpTls) {
        return systemConfigsBySmtpTlsCache.get(smtpTls);
    }

    private static class IsSmtpTls implements Predicate<SystemConfig> {

        private boolean value;

        public IsSmtpTls(boolean value) {
            this.value = value;
        }

        public boolean test(SystemConfig e) {
            return value == e.isSmtpTls();
        }

    }

    // -----------------------------------------------------------
    // - smtpUser
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<SystemConfig>> systemConfigsBySmtpUserCache = new Cache<java.lang.String,Set<SystemConfig>>(
            new Cache.Factory<java.lang.String,Set<SystemConfig>>() {
                public Set<SystemConfig> create(java.lang.String smtpUser) {
                    return getEntities(new IsSmtpUser(smtpUser));
                }
            });

    public final Set<SystemConfig> getSystemConfigsBySmtpUser(java.lang.String smtpUser) {
        return systemConfigsBySmtpUserCache.get(smtpUser);
    }
    private Set<java.lang.String> smtpUsersCache;

    public final Set<java.lang.String> getSmtpUsers() {
        if (smtpUsersCache == null) {
            smtpUsersCache = new HashSet<java.lang.String>();
            for (SystemConfig e : getEntities()) {
                if (e.isSmtpUserSet()) smtpUsersCache.add(e.getSmtpUser());
            }
        }
        return smtpUsersCache;
    }

    private static class IsSmtpUser implements Predicate<SystemConfig> {

        private java.lang.String value;

        public IsSmtpUser(java.lang.String value) {
            this.value = value;
        }

        public boolean test(SystemConfig e) {
            return e.isSmtpUser(value);
        }

    }

    // -----------------------------------------------------------
    // - smtpPassword
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<SystemConfig>> systemConfigsBySmtpPasswordCache = new Cache<java.lang.String,Set<SystemConfig>>(
            new Cache.Factory<java.lang.String,Set<SystemConfig>>() {
                public Set<SystemConfig> create(java.lang.String smtpPassword) {
                    return getEntities(new IsSmtpPassword(smtpPassword));
                }
            });

    public final Set<SystemConfig> getSystemConfigsBySmtpPassword(java.lang.String smtpPassword) {
        return systemConfigsBySmtpPasswordCache.get(smtpPassword);
    }
    private Set<java.lang.String> smtpPasswordsCache;

    public final Set<java.lang.String> getSmtpPasswords() {
        if (smtpPasswordsCache == null) {
            smtpPasswordsCache = new HashSet<java.lang.String>();
            for (SystemConfig e : getEntities()) {
                if (e.isSmtpPasswordSet()) smtpPasswordsCache.add(e.getSmtpPassword());
            }
        }
        return smtpPasswordsCache;
    }

    private static class IsSmtpPassword implements Predicate<SystemConfig> {

        private java.lang.String value;

        public IsSmtpPassword(java.lang.String value) {
            this.value = value;
        }

        public boolean test(SystemConfig e) {
            return e.isSmtpPassword(value);
        }

    }

    // -----------------------------------------------------------
    // - smtpFrom
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<SystemConfig>> systemConfigsBySmtpFromCache = new Cache<java.lang.String,Set<SystemConfig>>(
            new Cache.Factory<java.lang.String,Set<SystemConfig>>() {
                public Set<SystemConfig> create(java.lang.String smtpFrom) {
                    return getEntities(new IsSmtpFrom(smtpFrom));
                }
            });

    public final Set<SystemConfig> getSystemConfigsBySmtpFrom(java.lang.String smtpFrom) {
        return systemConfigsBySmtpFromCache.get(smtpFrom);
    }
    private Set<java.lang.String> smtpFromsCache;

    public final Set<java.lang.String> getSmtpFroms() {
        if (smtpFromsCache == null) {
            smtpFromsCache = new HashSet<java.lang.String>();
            for (SystemConfig e : getEntities()) {
                if (e.isSmtpFromSet()) smtpFromsCache.add(e.getSmtpFrom());
            }
        }
        return smtpFromsCache;
    }

    private static class IsSmtpFrom implements Predicate<SystemConfig> {

        private java.lang.String value;

        public IsSmtpFrom(java.lang.String value) {
            this.value = value;
        }

        public boolean test(SystemConfig e) {
            return e.isSmtpFrom(value);
        }

    }

    // -----------------------------------------------------------
    // - loginPageLogoUrl
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<SystemConfig>> systemConfigsByLoginPageLogoUrlCache = new Cache<java.lang.String,Set<SystemConfig>>(
            new Cache.Factory<java.lang.String,Set<SystemConfig>>() {
                public Set<SystemConfig> create(java.lang.String loginPageLogoUrl) {
                    return getEntities(new IsLoginPageLogoUrl(loginPageLogoUrl));
                }
            });

    public final Set<SystemConfig> getSystemConfigsByLoginPageLogoUrl(java.lang.String loginPageLogoUrl) {
        return systemConfigsByLoginPageLogoUrlCache.get(loginPageLogoUrl);
    }
    private Set<java.lang.String> loginPageLogoUrlsCache;

    public final Set<java.lang.String> getLoginPageLogoUrls() {
        if (loginPageLogoUrlsCache == null) {
            loginPageLogoUrlsCache = new HashSet<java.lang.String>();
            for (SystemConfig e : getEntities()) {
                if (e.isLoginPageLogoUrlSet()) loginPageLogoUrlsCache.add(e.getLoginPageLogoUrl());
            }
        }
        return loginPageLogoUrlsCache;
    }

    private static class IsLoginPageLogoUrl implements Predicate<SystemConfig> {

        private java.lang.String value;

        public IsLoginPageLogoUrl(java.lang.String value) {
            this.value = value;
        }

        public boolean test(SystemConfig e) {
            return e.isLoginPageLogoUrl(value);
        }

    }

    // -----------------------------------------------------------
    // - loginPageMessage
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<SystemConfig>> systemConfigsByLoginPageMessageCache = new Cache<java.lang.String,Set<SystemConfig>>(
            new Cache.Factory<java.lang.String,Set<SystemConfig>>() {
                public Set<SystemConfig> create(java.lang.String loginPageMessage) {
                    return getEntities(new IsLoginPageMessage(loginPageMessage));
                }
            });

    public final Set<SystemConfig> getSystemConfigsByLoginPageMessage(java.lang.String loginPageMessage) {
        return systemConfigsByLoginPageMessageCache.get(loginPageMessage);
    }
    private Set<java.lang.String> loginPageMessagesCache;

    public final Set<java.lang.String> getLoginPageMessages() {
        if (loginPageMessagesCache == null) {
            loginPageMessagesCache = new HashSet<java.lang.String>();
            for (SystemConfig e : getEntities()) {
                if (e.isLoginPageMessageSet()) loginPageMessagesCache.add(e.getLoginPageMessage());
            }
        }
        return loginPageMessagesCache;
    }

    private static class IsLoginPageMessage implements Predicate<SystemConfig> {

        private java.lang.String value;

        public IsLoginPageMessage(java.lang.String value) {
            this.value = value;
        }

        public boolean test(SystemConfig e) {
            return e.isLoginPageMessage(value);
        }

    }

    // -----------------------------------------------------------
    // - registerPageMessage
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<SystemConfig>> systemConfigsByRegisterPageMessageCache = new Cache<java.lang.String,Set<SystemConfig>>(
            new Cache.Factory<java.lang.String,Set<SystemConfig>>() {
                public Set<SystemConfig> create(java.lang.String registerPageMessage) {
                    return getEntities(new IsRegisterPageMessage(registerPageMessage));
                }
            });

    public final Set<SystemConfig> getSystemConfigsByRegisterPageMessage(java.lang.String registerPageMessage) {
        return systemConfigsByRegisterPageMessageCache.get(registerPageMessage);
    }
    private Set<java.lang.String> registerPageMessagesCache;

    public final Set<java.lang.String> getRegisterPageMessages() {
        if (registerPageMessagesCache == null) {
            registerPageMessagesCache = new HashSet<java.lang.String>();
            for (SystemConfig e : getEntities()) {
                if (e.isRegisterPageMessageSet()) registerPageMessagesCache.add(e.getRegisterPageMessage());
            }
        }
        return registerPageMessagesCache;
    }

    private static class IsRegisterPageMessage implements Predicate<SystemConfig> {

        private java.lang.String value;

        public IsRegisterPageMessage(java.lang.String value) {
            this.value = value;
        }

        public boolean test(SystemConfig e) {
            return e.isRegisterPageMessage(value);
        }

    }

    // -----------------------------------------------------------
    // - aboutPageMessage
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<SystemConfig>> systemConfigsByAboutPageMessageCache = new Cache<java.lang.String,Set<SystemConfig>>(
            new Cache.Factory<java.lang.String,Set<SystemConfig>>() {
                public Set<SystemConfig> create(java.lang.String aboutPageMessage) {
                    return getEntities(new IsAboutPageMessage(aboutPageMessage));
                }
            });

    public final Set<SystemConfig> getSystemConfigsByAboutPageMessage(java.lang.String aboutPageMessage) {
        return systemConfigsByAboutPageMessageCache.get(aboutPageMessage);
    }
    private Set<java.lang.String> aboutPageMessagesCache;

    public final Set<java.lang.String> getAboutPageMessages() {
        if (aboutPageMessagesCache == null) {
            aboutPageMessagesCache = new HashSet<java.lang.String>();
            for (SystemConfig e : getEntities()) {
                if (e.isAboutPageMessageSet()) aboutPageMessagesCache.add(e.getAboutPageMessage());
            }
        }
        return aboutPageMessagesCache;
    }

    private static class IsAboutPageMessage implements Predicate<SystemConfig> {

        private java.lang.String value;

        public IsAboutPageMessage(java.lang.String value) {
            this.value = value;
        }

        public boolean test(SystemConfig e) {
            return e.isAboutPageMessage(value);
        }

    }

    // -----------------------------------------------------------
    // - userEmailMandatory
    // -----------------------------------------------------------

    private final Cache<Boolean,Set<SystemConfig>> systemConfigsByUserEmailMandatoryCache = new Cache<Boolean,Set<SystemConfig>>(
            new Cache.Factory<Boolean,Set<SystemConfig>>() {
                public Set<SystemConfig> create(Boolean userEmailMandatory) {
                    return getEntities(new IsUserEmailMandatory(userEmailMandatory));
                }
            });

    public final Set<SystemConfig> getSystemConfigsByUserEmailMandatory(boolean userEmailMandatory) {
        return systemConfigsByUserEmailMandatoryCache.get(userEmailMandatory);
    }

    private static class IsUserEmailMandatory implements Predicate<SystemConfig> {

        private boolean value;

        public IsUserEmailMandatory(boolean value) {
            this.value = value;
        }

        public boolean test(SystemConfig e) {
            return value == e.isUserEmailMandatory();
        }

    }

    // -----------------------------------------------------------
    // - registrationDisabled
    // -----------------------------------------------------------

    private final Cache<Boolean,Set<SystemConfig>> systemConfigsByRegistrationDisabledCache = new Cache<Boolean,Set<SystemConfig>>(
            new Cache.Factory<Boolean,Set<SystemConfig>>() {
                public Set<SystemConfig> create(Boolean registrationDisabled) {
                    return getEntities(new IsRegistrationDisabled(registrationDisabled));
                }
            });

    public final Set<SystemConfig> getSystemConfigsByRegistrationDisabled(boolean registrationDisabled) {
        return systemConfigsByRegistrationDisabledCache.get(registrationDisabled);
    }

    private static class IsRegistrationDisabled implements Predicate<SystemConfig> {

        private boolean value;

        public IsRegistrationDisabled(boolean value) {
            this.value = value;
        }

        public boolean test(SystemConfig e) {
            return value == e.isRegistrationDisabled();
        }

    }

    // -----------------------------------------------------------
    // - defaultUserPassword
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<SystemConfig>> systemConfigsByDefaultUserPasswordCache = new Cache<java.lang.String,Set<SystemConfig>>(
            new Cache.Factory<java.lang.String,Set<SystemConfig>>() {
                public Set<SystemConfig> create(java.lang.String defaultUserPassword) {
                    return getEntities(new IsDefaultUserPassword(defaultUserPassword));
                }
            });

    public final Set<SystemConfig> getSystemConfigsByDefaultUserPassword(java.lang.String defaultUserPassword) {
        return systemConfigsByDefaultUserPasswordCache.get(defaultUserPassword);
    }
    private Set<java.lang.String> defaultUserPasswordsCache;

    public final Set<java.lang.String> getDefaultUserPasswords() {
        if (defaultUserPasswordsCache == null) {
            defaultUserPasswordsCache = new HashSet<java.lang.String>();
            for (SystemConfig e : getEntities()) {
                if (e.isDefaultUserPasswordSet()) defaultUserPasswordsCache.add(e.getDefaultUserPassword());
            }
        }
        return defaultUserPasswordsCache;
    }

    private static class IsDefaultUserPassword implements Predicate<SystemConfig> {

        private java.lang.String value;

        public IsDefaultUserPassword(java.lang.String value) {
            this.value = value;
        }

        public boolean test(SystemConfig e) {
            return e.isDefaultUserPassword(value);
        }

    }

    // --- valueObject classes ---
    @Override
    protected Set<Class> getValueObjectClasses() {
        Set<Class> ret = new HashSet<Class>(super.getValueObjectClasses());
        return ret;
    }

    @Override
    public Map<String, Class> getAliases() {
        Map<String, Class> aliases = new HashMap<String, Class>(super.getAliases());
        return aliases;
    }

    // --- dependencies ---

}