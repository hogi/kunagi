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

public abstract class GUser
            extends scrum.client.common.AScrumGwtEntity {

    protected scrum.client.Dao getDao() {
        return scrum.client.Dao.get();
    }

    public GUser() {
    }

    public GUser(Map data) {
        super(data);
        updateProperties(data);
    }

    public static final String ENTITY_TYPE = "user";

    @Override
    public final String getEntityType() {
        return ENTITY_TYPE;
    }

    // --- name ---

    private java.lang.String name ;

    public final java.lang.String getName() {
        return this.name ;
    }

    public final User setName(java.lang.String name) {
        if (isName(name)) return (User)this;
        if (name != null && getDao().getUserByName(name) != null) throw new RuntimeException("\"" + name + "\" already exists.");
        this.name = name ;
        propertyChanged("name", this.name);
        return (User)this;
    }

    public final boolean isName(java.lang.String name) {
        return equals(this.name, name);
    }

    private transient NameModel nameModel;

    public NameModel getNameModel() {
        if (nameModel == null) nameModel = createNameModel();
        return nameModel;
    }

    protected NameModel createNameModel() { return new NameModel(); }

    protected class NameModel extends ilarkesto.gwt.client.editor.ATextEditorModel {

        @Override
        public java.lang.String getValue() {
            return getName();
        }

        @Override
        public void setValue(java.lang.String value) {
            setName(value);
        }

        @Override
        public boolean isMandatory() { return true; }

        @Override
        protected void onChangeValue(java.lang.String oldValue, java.lang.String newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

    // --- admin ---

    private boolean admin ;

    public final boolean isAdmin() {
        return this.admin ;
    }

    public final User setAdmin(boolean admin) {
        if (isAdmin(admin)) return (User)this;
        this.admin = admin ;
        propertyChanged("admin", this.admin);
        return (User)this;
    }

    public final boolean isAdmin(boolean admin) {
        return equals(this.admin, admin);
    }

    // --- emailVerified ---

    private boolean emailVerified ;

    public final boolean isEmailVerified() {
        return this.emailVerified ;
    }

    public final User setEmailVerified(boolean emailVerified) {
        if (isEmailVerified(emailVerified)) return (User)this;
        this.emailVerified = emailVerified ;
        propertyChanged("emailVerified", this.emailVerified);
        return (User)this;
    }

    public final boolean isEmailVerified(boolean emailVerified) {
        return equals(this.emailVerified, emailVerified);
    }

    // --- email ---

    private java.lang.String email ;

    public final java.lang.String getEmail() {
        return this.email ;
    }

    public final User setEmail(java.lang.String email) {
        if (isEmail(email)) return (User)this;
        if (email != null && getDao().getUserByEmail(email) != null) throw new RuntimeException("\"" + email + "\" already exists.");
        this.email = email ;
        propertyChanged("email", this.email);
        return (User)this;
    }

    public final boolean isEmail(java.lang.String email) {
        return equals(this.email, email);
    }

    private transient EmailModel emailModel;

    public EmailModel getEmailModel() {
        if (emailModel == null) emailModel = createEmailModel();
        return emailModel;
    }

    protected EmailModel createEmailModel() { return new EmailModel(); }

    protected class EmailModel extends ilarkesto.gwt.client.editor.ATextEditorModel {

        @Override
        public java.lang.String getValue() {
            return getEmail();
        }

        @Override
        public void setValue(java.lang.String value) {
            setEmail(value);
        }

        @Override
        protected void onChangeValue(java.lang.String oldValue, java.lang.String newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

    // --- currentProject ---

    private String currentProjectId;

    public final scrum.client.project.Project getCurrentProject() {
        if (currentProjectId == null) return null;
        return getDao().getProject(this.currentProjectId);
    }

    public final boolean isCurrentProjectSet() {
        return currentProjectId != null;
    }

    public final User setCurrentProject(scrum.client.project.Project currentProject) {
        String id = currentProject == null ? null : currentProject.getId();
        if (equals(this.currentProjectId, id)) return (User) this;
        this.currentProjectId = id;
        propertyChanged("currentProjectId", this.currentProjectId);
        return (User)this;
    }

    public final boolean isCurrentProject(scrum.client.project.Project currentProject) {
        return equals(this.currentProjectId, currentProject);
    }

    // --- color ---

    private java.lang.String color ;

    public final java.lang.String getColor() {
        return this.color ;
    }

    public final User setColor(java.lang.String color) {
        if (isColor(color)) return (User)this;
        this.color = color ;
        propertyChanged("color", this.color);
        return (User)this;
    }

    public final boolean isColor(java.lang.String color) {
        return equals(this.color, color);
    }

    private transient ColorModel colorModel;

    public ColorModel getColorModel() {
        if (colorModel == null) colorModel = createColorModel();
        return colorModel;
    }

    protected ColorModel createColorModel() { return new ColorModel(); }

    protected class ColorModel extends ilarkesto.gwt.client.editor.ATextEditorModel {

        @Override
        public java.lang.String getValue() {
            return getColor();
        }

        @Override
        public void setValue(java.lang.String value) {
            setColor(value);
        }

        @Override
        protected void onChangeValue(java.lang.String oldValue, java.lang.String newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

    // --- lastLoginDateAndTime ---

    private ilarkesto.gwt.client.DateAndTime lastLoginDateAndTime ;

    public final ilarkesto.gwt.client.DateAndTime getLastLoginDateAndTime() {
        return this.lastLoginDateAndTime ;
    }

    public final User setLastLoginDateAndTime(ilarkesto.gwt.client.DateAndTime lastLoginDateAndTime) {
        if (isLastLoginDateAndTime(lastLoginDateAndTime)) return (User)this;
        this.lastLoginDateAndTime = lastLoginDateAndTime ;
        propertyChanged("lastLoginDateAndTime", this.lastLoginDateAndTime);
        return (User)this;
    }

    public final boolean isLastLoginDateAndTime(ilarkesto.gwt.client.DateAndTime lastLoginDateAndTime) {
        return equals(this.lastLoginDateAndTime, lastLoginDateAndTime);
    }

    private transient LastLoginDateAndTimeModel lastLoginDateAndTimeModel;

    public LastLoginDateAndTimeModel getLastLoginDateAndTimeModel() {
        if (lastLoginDateAndTimeModel == null) lastLoginDateAndTimeModel = createLastLoginDateAndTimeModel();
        return lastLoginDateAndTimeModel;
    }

    protected LastLoginDateAndTimeModel createLastLoginDateAndTimeModel() { return new LastLoginDateAndTimeModel(); }

    protected class LastLoginDateAndTimeModel extends ilarkesto.gwt.client.editor.ADateAndTimeEditorModel {

        @Override
        public ilarkesto.gwt.client.DateAndTime getValue() {
            return getLastLoginDateAndTime();
        }

        @Override
        public void setValue(ilarkesto.gwt.client.DateAndTime value) {
            setLastLoginDateAndTime(value);
        }

        @Override
        protected void onChangeValue(ilarkesto.gwt.client.DateAndTime oldValue, ilarkesto.gwt.client.DateAndTime newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

    // --- registrationDateAndTime ---

    private ilarkesto.gwt.client.DateAndTime registrationDateAndTime ;

    public final ilarkesto.gwt.client.DateAndTime getRegistrationDateAndTime() {
        return this.registrationDateAndTime ;
    }

    public final User setRegistrationDateAndTime(ilarkesto.gwt.client.DateAndTime registrationDateAndTime) {
        if (isRegistrationDateAndTime(registrationDateAndTime)) return (User)this;
        this.registrationDateAndTime = registrationDateAndTime ;
        propertyChanged("registrationDateAndTime", this.registrationDateAndTime);
        return (User)this;
    }

    public final boolean isRegistrationDateAndTime(ilarkesto.gwt.client.DateAndTime registrationDateAndTime) {
        return equals(this.registrationDateAndTime, registrationDateAndTime);
    }

    private transient RegistrationDateAndTimeModel registrationDateAndTimeModel;

    public RegistrationDateAndTimeModel getRegistrationDateAndTimeModel() {
        if (registrationDateAndTimeModel == null) registrationDateAndTimeModel = createRegistrationDateAndTimeModel();
        return registrationDateAndTimeModel;
    }

    protected RegistrationDateAndTimeModel createRegistrationDateAndTimeModel() { return new RegistrationDateAndTimeModel(); }

    protected class RegistrationDateAndTimeModel extends ilarkesto.gwt.client.editor.ADateAndTimeEditorModel {

        @Override
        public ilarkesto.gwt.client.DateAndTime getValue() {
            return getRegistrationDateAndTime();
        }

        @Override
        public void setValue(ilarkesto.gwt.client.DateAndTime value) {
            setRegistrationDateAndTime(value);
        }

        @Override
        protected void onChangeValue(ilarkesto.gwt.client.DateAndTime oldValue, ilarkesto.gwt.client.DateAndTime newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

    // --- disabled ---

    private boolean disabled ;

    public final boolean isDisabled() {
        return this.disabled ;
    }

    public final User setDisabled(boolean disabled) {
        if (isDisabled(disabled)) return (User)this;
        this.disabled = disabled ;
        propertyChanged("disabled", this.disabled);
        return (User)this;
    }

    public final boolean isDisabled(boolean disabled) {
        return equals(this.disabled, disabled);
    }

    // --- update properties by map ---

    public void updateProperties(Map props) {
        name  = (java.lang.String) props.get("name");
        admin  = (Boolean) props.get("admin");
        emailVerified  = (Boolean) props.get("emailVerified");
        email  = (java.lang.String) props.get("email");
        currentProjectId = (String) props.get("currentProjectId");
        color  = (java.lang.String) props.get("color");
        String lastLoginDateAndTimeAsString = (String) props.get("lastLoginDateAndTime");
        lastLoginDateAndTime  =  lastLoginDateAndTimeAsString == null ? null : new ilarkesto.gwt.client.DateAndTime(lastLoginDateAndTimeAsString);
        String registrationDateAndTimeAsString = (String) props.get("registrationDateAndTime");
        registrationDateAndTime  =  registrationDateAndTimeAsString == null ? null : new ilarkesto.gwt.client.DateAndTime(registrationDateAndTimeAsString);
        disabled  = (Boolean) props.get("disabled");
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("name", this.name);
        properties.put("admin", this.admin);
        properties.put("emailVerified", this.emailVerified);
        properties.put("email", this.email);
        properties.put("currentProjectId", this.currentProjectId);
        properties.put("color", this.color);
        properties.put("lastLoginDateAndTime", this.lastLoginDateAndTime == null ? null : this.lastLoginDateAndTime.toString());
        properties.put("registrationDateAndTime", this.registrationDateAndTime == null ? null : this.registrationDateAndTime.toString());
        properties.put("disabled", this.disabled);
    }

    @Override
    public boolean matchesKey(String key) {
        if (super.matchesKey(key)) return true;
        if (matchesKey(getName(), key)) return true;
        if (matchesKey(getEmail(), key)) return true;
        return false;
    }

}