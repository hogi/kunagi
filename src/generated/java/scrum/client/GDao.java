// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.GwtDaoGenerator










package scrum.client;

import java.util.*;
import scrum.client.common.*;
import ilarkesto.gwt.client.*;

public abstract class GDao
            extends ilarkesto.gwt.client.AGwtDao {

    // --- Change ---

    private Map<String, scrum.client.journal.Change> changes = new HashMap<String, scrum.client.journal.Change>();

    public final void clearChanges() {
        ilarkesto.core.logging.Log.DEBUG("Clearing Changes");
        changes.clear();
    }

    public final boolean containsChange(scrum.client.journal.Change change) {
        return changes.containsKey(change.getId());
    }

    public final void deleteChange(scrum.client.journal.Change change) {
        changes.remove(change.getId());
        entityDeleted(change);
    }

    public final void createChange(scrum.client.journal.Change change) {
        changes.put(change.getId(), change);
        entityCreated(change);
    }

    private final void updateChange(Map data) {
        String id = (String) data.get("id");
        scrum.client.journal.Change entity = changes.get(id);
        if (entity == null) {
            entity = new scrum.client.journal.Change(data);
            changes.put(id, entity);
            ilarkesto.core.logging.Log.DEBUG("Change received: " + entity.getId() + " ("+entity+")");
        } else {
            entity.updateProperties(data);
            ilarkesto.core.logging.Log.DEBUG("Change updated: " + entity);
        }
        onEntityModifiedRemotely(entity);
    }

    public final scrum.client.journal.Change getChange(String id) {
        scrum.client.journal.Change ret = changes.get(id);
        if (ret == null) throw new RuntimeException("Change does not exist: " + id);
        return ret;
    }

    public final Set<scrum.client.journal.Change> getChanges(Collection<String> ids) {
        Set<scrum.client.journal.Change> ret = new HashSet<scrum.client.journal.Change>();
        for (String id : ids) {
            scrum.client.journal.Change entity = changes.get(id);
            if (entity == null) throw new RuntimeException("Change does not exist: " + id);
            ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.journal.Change> getChanges() {
        return new ArrayList<scrum.client.journal.Change>(changes.values());
    }

    public final List<scrum.client.journal.Change> getChangesByParent(ilarkesto.gwt.client.AGwtEntity parent) {
        List<scrum.client.journal.Change> ret = new ArrayList<scrum.client.journal.Change>();
        for (scrum.client.journal.Change entity : changes.values()) {
            if (entity.isParent(parent)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.journal.Change> getChangesByUser(scrum.client.admin.User user) {
        List<scrum.client.journal.Change> ret = new ArrayList<scrum.client.journal.Change>();
        for (scrum.client.journal.Change entity : changes.values()) {
            if (entity.isUser(user)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.journal.Change> getChangesByDateAndTime(ilarkesto.gwt.client.DateAndTime dateAndTime) {
        List<scrum.client.journal.Change> ret = new ArrayList<scrum.client.journal.Change>();
        for (scrum.client.journal.Change entity : changes.values()) {
            if (entity.isDateAndTime(dateAndTime)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.journal.Change> getChangesByKey(java.lang.String key) {
        List<scrum.client.journal.Change> ret = new ArrayList<scrum.client.journal.Change>();
        for (scrum.client.journal.Change entity : changes.values()) {
            if (entity.isKey(key)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.journal.Change> getChangesByValue(java.lang.String value) {
        List<scrum.client.journal.Change> ret = new ArrayList<scrum.client.journal.Change>();
        for (scrum.client.journal.Change entity : changes.values()) {
            if (entity.isValue(value)) ret.add(entity);
        }
        return ret;
    }

    // --- ChatMessage ---

    private Map<String, scrum.client.collaboration.ChatMessage> chatMessages = new HashMap<String, scrum.client.collaboration.ChatMessage>();

    public final void clearChatMessages() {
        ilarkesto.core.logging.Log.DEBUG("Clearing ChatMessages");
        chatMessages.clear();
    }

    public final boolean containsChatMessage(scrum.client.collaboration.ChatMessage chatMessage) {
        return chatMessages.containsKey(chatMessage.getId());
    }

    public final void deleteChatMessage(scrum.client.collaboration.ChatMessage chatMessage) {
        chatMessages.remove(chatMessage.getId());
        entityDeleted(chatMessage);
    }

    public final void createChatMessage(scrum.client.collaboration.ChatMessage chatMessage) {
        chatMessages.put(chatMessage.getId(), chatMessage);
        entityCreated(chatMessage);
    }

    private final void updateChatMessage(Map data) {
        String id = (String) data.get("id");
        scrum.client.collaboration.ChatMessage entity = chatMessages.get(id);
        if (entity == null) {
            entity = new scrum.client.collaboration.ChatMessage(data);
            chatMessages.put(id, entity);
            ilarkesto.core.logging.Log.DEBUG("ChatMessage received: " + entity.getId() + " ("+entity+")");
        } else {
            entity.updateProperties(data);
            ilarkesto.core.logging.Log.DEBUG("ChatMessage updated: " + entity);
        }
        onEntityModifiedRemotely(entity);
    }

    public final scrum.client.collaboration.ChatMessage getChatMessage(String id) {
        scrum.client.collaboration.ChatMessage ret = chatMessages.get(id);
        if (ret == null) throw new RuntimeException("ChatMessage does not exist: " + id);
        return ret;
    }

    public final Set<scrum.client.collaboration.ChatMessage> getChatMessages(Collection<String> ids) {
        Set<scrum.client.collaboration.ChatMessage> ret = new HashSet<scrum.client.collaboration.ChatMessage>();
        for (String id : ids) {
            scrum.client.collaboration.ChatMessage entity = chatMessages.get(id);
            if (entity == null) throw new RuntimeException("ChatMessage does not exist: " + id);
            ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.collaboration.ChatMessage> getChatMessages() {
        return new ArrayList<scrum.client.collaboration.ChatMessage>(chatMessages.values());
    }

    public final List<scrum.client.collaboration.ChatMessage> getChatMessagesByProject(scrum.client.project.Project project) {
        List<scrum.client.collaboration.ChatMessage> ret = new ArrayList<scrum.client.collaboration.ChatMessage>();
        for (scrum.client.collaboration.ChatMessage entity : chatMessages.values()) {
            if (entity.isProject(project)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.collaboration.ChatMessage> getChatMessagesByAuthor(scrum.client.admin.User author) {
        List<scrum.client.collaboration.ChatMessage> ret = new ArrayList<scrum.client.collaboration.ChatMessage>();
        for (scrum.client.collaboration.ChatMessage entity : chatMessages.values()) {
            if (entity.isAuthor(author)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.collaboration.ChatMessage> getChatMessagesByText(java.lang.String text) {
        List<scrum.client.collaboration.ChatMessage> ret = new ArrayList<scrum.client.collaboration.ChatMessage>();
        for (scrum.client.collaboration.ChatMessage entity : chatMessages.values()) {
            if (entity.isText(text)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.collaboration.ChatMessage> getChatMessagesByDateAndTime(ilarkesto.gwt.client.DateAndTime dateAndTime) {
        List<scrum.client.collaboration.ChatMessage> ret = new ArrayList<scrum.client.collaboration.ChatMessage>();
        for (scrum.client.collaboration.ChatMessage entity : chatMessages.values()) {
            if (entity.isDateAndTime(dateAndTime)) ret.add(entity);
        }
        return ret;
    }

    // --- Comment ---

    private Map<String, scrum.client.collaboration.Comment> comments = new HashMap<String, scrum.client.collaboration.Comment>();

    public final void clearComments() {
        ilarkesto.core.logging.Log.DEBUG("Clearing Comments");
        comments.clear();
    }

    public final boolean containsComment(scrum.client.collaboration.Comment comment) {
        return comments.containsKey(comment.getId());
    }

    public final void deleteComment(scrum.client.collaboration.Comment comment) {
        comments.remove(comment.getId());
        entityDeleted(comment);
    }

    public final void createComment(scrum.client.collaboration.Comment comment) {
        comments.put(comment.getId(), comment);
        entityCreated(comment);
    }

    private final void updateComment(Map data) {
        String id = (String) data.get("id");
        scrum.client.collaboration.Comment entity = comments.get(id);
        if (entity == null) {
            entity = new scrum.client.collaboration.Comment(data);
            comments.put(id, entity);
            ilarkesto.core.logging.Log.DEBUG("Comment received: " + entity.getId() + " ("+entity+")");
        } else {
            entity.updateProperties(data);
            ilarkesto.core.logging.Log.DEBUG("Comment updated: " + entity);
        }
        onEntityModifiedRemotely(entity);
    }

    public final scrum.client.collaboration.Comment getComment(String id) {
        scrum.client.collaboration.Comment ret = comments.get(id);
        if (ret == null) throw new RuntimeException("Comment does not exist: " + id);
        return ret;
    }

    public final Set<scrum.client.collaboration.Comment> getComments(Collection<String> ids) {
        Set<scrum.client.collaboration.Comment> ret = new HashSet<scrum.client.collaboration.Comment>();
        for (String id : ids) {
            scrum.client.collaboration.Comment entity = comments.get(id);
            if (entity == null) throw new RuntimeException("Comment does not exist: " + id);
            ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.collaboration.Comment> getComments() {
        return new ArrayList<scrum.client.collaboration.Comment>(comments.values());
    }

    public final List<scrum.client.collaboration.Comment> getCommentsByParent(ilarkesto.gwt.client.AGwtEntity parent) {
        List<scrum.client.collaboration.Comment> ret = new ArrayList<scrum.client.collaboration.Comment>();
        for (scrum.client.collaboration.Comment entity : comments.values()) {
            if (entity.isParent(parent)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.collaboration.Comment> getCommentsByAuthor(scrum.client.admin.User author) {
        List<scrum.client.collaboration.Comment> ret = new ArrayList<scrum.client.collaboration.Comment>();
        for (scrum.client.collaboration.Comment entity : comments.values()) {
            if (entity.isAuthor(author)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.collaboration.Comment> getCommentsByText(java.lang.String text) {
        List<scrum.client.collaboration.Comment> ret = new ArrayList<scrum.client.collaboration.Comment>();
        for (scrum.client.collaboration.Comment entity : comments.values()) {
            if (entity.isText(text)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.collaboration.Comment> getCommentsByDateAndTime(ilarkesto.gwt.client.DateAndTime dateAndTime) {
        List<scrum.client.collaboration.Comment> ret = new ArrayList<scrum.client.collaboration.Comment>();
        for (scrum.client.collaboration.Comment entity : comments.values()) {
            if (entity.isDateAndTime(dateAndTime)) ret.add(entity);
        }
        return ret;
    }

    // --- Emoticon ---

    private Map<String, scrum.client.collaboration.Emoticon> emoticons = new HashMap<String, scrum.client.collaboration.Emoticon>();

    public final void clearEmoticons() {
        ilarkesto.core.logging.Log.DEBUG("Clearing Emoticons");
        emoticons.clear();
    }

    public final boolean containsEmoticon(scrum.client.collaboration.Emoticon emoticon) {
        return emoticons.containsKey(emoticon.getId());
    }

    public final void deleteEmoticon(scrum.client.collaboration.Emoticon emoticon) {
        emoticons.remove(emoticon.getId());
        entityDeleted(emoticon);
    }

    public final void createEmoticon(scrum.client.collaboration.Emoticon emoticon) {
        emoticons.put(emoticon.getId(), emoticon);
        entityCreated(emoticon);
    }

    private final void updateEmoticon(Map data) {
        String id = (String) data.get("id");
        scrum.client.collaboration.Emoticon entity = emoticons.get(id);
        if (entity == null) {
            entity = new scrum.client.collaboration.Emoticon(data);
            emoticons.put(id, entity);
            ilarkesto.core.logging.Log.DEBUG("Emoticon received: " + entity.getId() + " ("+entity+")");
        } else {
            entity.updateProperties(data);
            ilarkesto.core.logging.Log.DEBUG("Emoticon updated: " + entity);
        }
        onEntityModifiedRemotely(entity);
    }

    public final scrum.client.collaboration.Emoticon getEmoticon(String id) {
        scrum.client.collaboration.Emoticon ret = emoticons.get(id);
        if (ret == null) throw new RuntimeException("Emoticon does not exist: " + id);
        return ret;
    }

    public final Set<scrum.client.collaboration.Emoticon> getEmoticons(Collection<String> ids) {
        Set<scrum.client.collaboration.Emoticon> ret = new HashSet<scrum.client.collaboration.Emoticon>();
        for (String id : ids) {
            scrum.client.collaboration.Emoticon entity = emoticons.get(id);
            if (entity == null) throw new RuntimeException("Emoticon does not exist: " + id);
            ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.collaboration.Emoticon> getEmoticons() {
        return new ArrayList<scrum.client.collaboration.Emoticon>(emoticons.values());
    }

    public final List<scrum.client.collaboration.Emoticon> getEmoticonsByParent(ilarkesto.gwt.client.AGwtEntity parent) {
        List<scrum.client.collaboration.Emoticon> ret = new ArrayList<scrum.client.collaboration.Emoticon>();
        for (scrum.client.collaboration.Emoticon entity : emoticons.values()) {
            if (entity.isParent(parent)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.collaboration.Emoticon> getEmoticonsByOwner(scrum.client.admin.User owner) {
        List<scrum.client.collaboration.Emoticon> ret = new ArrayList<scrum.client.collaboration.Emoticon>();
        for (scrum.client.collaboration.Emoticon entity : emoticons.values()) {
            if (entity.isOwner(owner)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.collaboration.Emoticon> getEmoticonsByEmotion(java.lang.String emotion) {
        List<scrum.client.collaboration.Emoticon> ret = new ArrayList<scrum.client.collaboration.Emoticon>();
        for (scrum.client.collaboration.Emoticon entity : emoticons.values()) {
            if (entity.isEmotion(emotion)) ret.add(entity);
        }
        return ret;
    }

    // --- File ---

    private Map<String, scrum.client.files.File> files = new HashMap<String, scrum.client.files.File>();

    public final void clearFiles() {
        ilarkesto.core.logging.Log.DEBUG("Clearing Files");
        files.clear();
    }

    public final boolean containsFile(scrum.client.files.File file) {
        return files.containsKey(file.getId());
    }

    public final void deleteFile(scrum.client.files.File file) {
        files.remove(file.getId());
        entityDeleted(file);
    }

    public final void createFile(scrum.client.files.File file) {
        files.put(file.getId(), file);
        entityCreated(file);
    }

    private final void updateFile(Map data) {
        String id = (String) data.get("id");
        scrum.client.files.File entity = files.get(id);
        if (entity == null) {
            entity = new scrum.client.files.File(data);
            files.put(id, entity);
            ilarkesto.core.logging.Log.DEBUG("File received: " + entity.getId() + " ("+entity+")");
        } else {
            entity.updateProperties(data);
            ilarkesto.core.logging.Log.DEBUG("File updated: " + entity);
        }
        onEntityModifiedRemotely(entity);
    }

    public final scrum.client.files.File getFile(String id) {
        scrum.client.files.File ret = files.get(id);
        if (ret == null) throw new RuntimeException("File does not exist: " + id);
        return ret;
    }

    public final Set<scrum.client.files.File> getFiles(Collection<String> ids) {
        Set<scrum.client.files.File> ret = new HashSet<scrum.client.files.File>();
        for (String id : ids) {
            scrum.client.files.File entity = files.get(id);
            if (entity == null) throw new RuntimeException("File does not exist: " + id);
            ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.files.File> getFiles() {
        return new ArrayList<scrum.client.files.File>(files.values());
    }

    public final List<scrum.client.files.File> getFilesByProject(scrum.client.project.Project project) {
        List<scrum.client.files.File> ret = new ArrayList<scrum.client.files.File>();
        for (scrum.client.files.File entity : files.values()) {
            if (entity.isProject(project)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.files.File> getFilesByFilename(java.lang.String filename) {
        List<scrum.client.files.File> ret = new ArrayList<scrum.client.files.File>();
        for (scrum.client.files.File entity : files.values()) {
            if (entity.isFilename(filename)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.files.File> getFilesByUploadTime(ilarkesto.gwt.client.DateAndTime uploadTime) {
        List<scrum.client.files.File> ret = new ArrayList<scrum.client.files.File>();
        for (scrum.client.files.File entity : files.values()) {
            if (entity.isUploadTime(uploadTime)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.files.File> getFilesByLabel(java.lang.String label) {
        List<scrum.client.files.File> ret = new ArrayList<scrum.client.files.File>();
        for (scrum.client.files.File entity : files.values()) {
            if (entity.isLabel(label)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.files.File> getFilesByNumber(int number) {
        List<scrum.client.files.File> ret = new ArrayList<scrum.client.files.File>();
        for (scrum.client.files.File entity : files.values()) {
            if (entity.isNumber(number)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.files.File> getFilesByNote(java.lang.String note) {
        List<scrum.client.files.File> ret = new ArrayList<scrum.client.files.File>();
        for (scrum.client.files.File entity : files.values()) {
            if (entity.isNote(note)) ret.add(entity);
        }
        return ret;
    }

    // --- Impediment ---

    private Map<String, scrum.client.impediments.Impediment> impediments = new HashMap<String, scrum.client.impediments.Impediment>();

    public final void clearImpediments() {
        ilarkesto.core.logging.Log.DEBUG("Clearing Impediments");
        impediments.clear();
    }

    public final boolean containsImpediment(scrum.client.impediments.Impediment impediment) {
        return impediments.containsKey(impediment.getId());
    }

    public final void deleteImpediment(scrum.client.impediments.Impediment impediment) {
        impediments.remove(impediment.getId());
        entityDeleted(impediment);
    }

    public final void createImpediment(scrum.client.impediments.Impediment impediment) {
        impediments.put(impediment.getId(), impediment);
        entityCreated(impediment);
    }

    private final void updateImpediment(Map data) {
        String id = (String) data.get("id");
        scrum.client.impediments.Impediment entity = impediments.get(id);
        if (entity == null) {
            entity = new scrum.client.impediments.Impediment(data);
            impediments.put(id, entity);
            ilarkesto.core.logging.Log.DEBUG("Impediment received: " + entity.getId() + " ("+entity+")");
        } else {
            entity.updateProperties(data);
            ilarkesto.core.logging.Log.DEBUG("Impediment updated: " + entity);
        }
        onEntityModifiedRemotely(entity);
    }

    public final scrum.client.impediments.Impediment getImpediment(String id) {
        scrum.client.impediments.Impediment ret = impediments.get(id);
        if (ret == null) throw new RuntimeException("Impediment does not exist: " + id);
        return ret;
    }

    public final Set<scrum.client.impediments.Impediment> getImpediments(Collection<String> ids) {
        Set<scrum.client.impediments.Impediment> ret = new HashSet<scrum.client.impediments.Impediment>();
        for (String id : ids) {
            scrum.client.impediments.Impediment entity = impediments.get(id);
            if (entity == null) throw new RuntimeException("Impediment does not exist: " + id);
            ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.impediments.Impediment> getImpediments() {
        return new ArrayList<scrum.client.impediments.Impediment>(impediments.values());
    }

    public final List<scrum.client.impediments.Impediment> getImpedimentsByProject(scrum.client.project.Project project) {
        List<scrum.client.impediments.Impediment> ret = new ArrayList<scrum.client.impediments.Impediment>();
        for (scrum.client.impediments.Impediment entity : impediments.values()) {
            if (entity.isProject(project)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.impediments.Impediment> getImpedimentsByNumber(int number) {
        List<scrum.client.impediments.Impediment> ret = new ArrayList<scrum.client.impediments.Impediment>();
        for (scrum.client.impediments.Impediment entity : impediments.values()) {
            if (entity.isNumber(number)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.impediments.Impediment> getImpedimentsByLabel(java.lang.String label) {
        List<scrum.client.impediments.Impediment> ret = new ArrayList<scrum.client.impediments.Impediment>();
        for (scrum.client.impediments.Impediment entity : impediments.values()) {
            if (entity.isLabel(label)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.impediments.Impediment> getImpedimentsByDate(ilarkesto.gwt.client.Date date) {
        List<scrum.client.impediments.Impediment> ret = new ArrayList<scrum.client.impediments.Impediment>();
        for (scrum.client.impediments.Impediment entity : impediments.values()) {
            if (entity.isDate(date)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.impediments.Impediment> getImpedimentsByDescription(java.lang.String description) {
        List<scrum.client.impediments.Impediment> ret = new ArrayList<scrum.client.impediments.Impediment>();
        for (scrum.client.impediments.Impediment entity : impediments.values()) {
            if (entity.isDescription(description)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.impediments.Impediment> getImpedimentsBySolution(java.lang.String solution) {
        List<scrum.client.impediments.Impediment> ret = new ArrayList<scrum.client.impediments.Impediment>();
        for (scrum.client.impediments.Impediment entity : impediments.values()) {
            if (entity.isSolution(solution)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.impediments.Impediment> getImpedimentsByClosed(boolean closed) {
        List<scrum.client.impediments.Impediment> ret = new ArrayList<scrum.client.impediments.Impediment>();
        for (scrum.client.impediments.Impediment entity : impediments.values()) {
            if (entity.isClosed(closed)) ret.add(entity);
        }
        return ret;
    }

    // --- Issue ---

    private Map<String, scrum.client.issues.Issue> issues = new HashMap<String, scrum.client.issues.Issue>();

    public final void clearIssues() {
        ilarkesto.core.logging.Log.DEBUG("Clearing Issues");
        issues.clear();
    }

    public final boolean containsIssue(scrum.client.issues.Issue issue) {
        return issues.containsKey(issue.getId());
    }

    public final void deleteIssue(scrum.client.issues.Issue issue) {
        issues.remove(issue.getId());
        entityDeleted(issue);
    }

    public final void createIssue(scrum.client.issues.Issue issue) {
        issues.put(issue.getId(), issue);
        entityCreated(issue);
    }

    private final void updateIssue(Map data) {
        String id = (String) data.get("id");
        scrum.client.issues.Issue entity = issues.get(id);
        if (entity == null) {
            entity = new scrum.client.issues.Issue(data);
            issues.put(id, entity);
            ilarkesto.core.logging.Log.DEBUG("Issue received: " + entity.getId() + " ("+entity+")");
        } else {
            entity.updateProperties(data);
            ilarkesto.core.logging.Log.DEBUG("Issue updated: " + entity);
        }
        onEntityModifiedRemotely(entity);
    }

    public final scrum.client.issues.Issue getIssue(String id) {
        scrum.client.issues.Issue ret = issues.get(id);
        if (ret == null) throw new RuntimeException("Issue does not exist: " + id);
        return ret;
    }

    public final Set<scrum.client.issues.Issue> getIssues(Collection<String> ids) {
        Set<scrum.client.issues.Issue> ret = new HashSet<scrum.client.issues.Issue>();
        for (String id : ids) {
            scrum.client.issues.Issue entity = issues.get(id);
            if (entity == null) throw new RuntimeException("Issue does not exist: " + id);
            ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.issues.Issue> getIssues() {
        return new ArrayList<scrum.client.issues.Issue>(issues.values());
    }

    public final List<scrum.client.issues.Issue> getIssuesByProject(scrum.client.project.Project project) {
        List<scrum.client.issues.Issue> ret = new ArrayList<scrum.client.issues.Issue>();
        for (scrum.client.issues.Issue entity : issues.values()) {
            if (entity.isProject(project)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.issues.Issue> getIssuesByNumber(int number) {
        List<scrum.client.issues.Issue> ret = new ArrayList<scrum.client.issues.Issue>();
        for (scrum.client.issues.Issue entity : issues.values()) {
            if (entity.isNumber(number)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.issues.Issue> getIssuesByType(java.lang.String type) {
        List<scrum.client.issues.Issue> ret = new ArrayList<scrum.client.issues.Issue>();
        for (scrum.client.issues.Issue entity : issues.values()) {
            if (entity.isType(type)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.issues.Issue> getIssuesByDate(ilarkesto.gwt.client.DateAndTime date) {
        List<scrum.client.issues.Issue> ret = new ArrayList<scrum.client.issues.Issue>();
        for (scrum.client.issues.Issue entity : issues.values()) {
            if (entity.isDate(date)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.issues.Issue> getIssuesByCreator(scrum.client.admin.User creator) {
        List<scrum.client.issues.Issue> ret = new ArrayList<scrum.client.issues.Issue>();
        for (scrum.client.issues.Issue entity : issues.values()) {
            if (entity.isCreator(creator)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.issues.Issue> getIssuesByLabel(java.lang.String label) {
        List<scrum.client.issues.Issue> ret = new ArrayList<scrum.client.issues.Issue>();
        for (scrum.client.issues.Issue entity : issues.values()) {
            if (entity.isLabel(label)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.issues.Issue> getIssuesByDescription(java.lang.String description) {
        List<scrum.client.issues.Issue> ret = new ArrayList<scrum.client.issues.Issue>();
        for (scrum.client.issues.Issue entity : issues.values()) {
            if (entity.isDescription(description)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.issues.Issue> getIssuesByStatement(java.lang.String statement) {
        List<scrum.client.issues.Issue> ret = new ArrayList<scrum.client.issues.Issue>();
        for (scrum.client.issues.Issue entity : issues.values()) {
            if (entity.isStatement(statement)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.issues.Issue> getIssuesByAcceptDate(ilarkesto.gwt.client.Date acceptDate) {
        List<scrum.client.issues.Issue> ret = new ArrayList<scrum.client.issues.Issue>();
        for (scrum.client.issues.Issue entity : issues.values()) {
            if (entity.isAcceptDate(acceptDate)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.issues.Issue> getIssuesByUrgent(boolean urgent) {
        List<scrum.client.issues.Issue> ret = new ArrayList<scrum.client.issues.Issue>();
        for (scrum.client.issues.Issue entity : issues.values()) {
            if (entity.isUrgent(urgent)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.issues.Issue> getIssuesByOwner(scrum.client.admin.User owner) {
        List<scrum.client.issues.Issue> ret = new ArrayList<scrum.client.issues.Issue>();
        for (scrum.client.issues.Issue entity : issues.values()) {
            if (entity.isOwner(owner)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.issues.Issue> getIssuesByFixDate(ilarkesto.gwt.client.Date fixDate) {
        List<scrum.client.issues.Issue> ret = new ArrayList<scrum.client.issues.Issue>();
        for (scrum.client.issues.Issue entity : issues.values()) {
            if (entity.isFixDate(fixDate)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.issues.Issue> getIssuesByCloseDate(ilarkesto.gwt.client.Date closeDate) {
        List<scrum.client.issues.Issue> ret = new ArrayList<scrum.client.issues.Issue>();
        for (scrum.client.issues.Issue entity : issues.values()) {
            if (entity.isCloseDate(closeDate)) ret.add(entity);
        }
        return ret;
    }

    // --- Project ---

    private Map<String, scrum.client.project.Project> projects = new HashMap<String, scrum.client.project.Project>();

    public final void clearProjects() {
        ilarkesto.core.logging.Log.DEBUG("Clearing Projects");
        projects.clear();
    }

    public final boolean containsProject(scrum.client.project.Project project) {
        return projects.containsKey(project.getId());
    }

    public final void deleteProject(scrum.client.project.Project project) {
        projects.remove(project.getId());
        entityDeleted(project);
    }

    public final void createProject(scrum.client.project.Project project) {
        projects.put(project.getId(), project);
        entityCreated(project);
    }

    private final void updateProject(Map data) {
        String id = (String) data.get("id");
        scrum.client.project.Project entity = projects.get(id);
        if (entity == null) {
            entity = new scrum.client.project.Project(data);
            projects.put(id, entity);
            ilarkesto.core.logging.Log.DEBUG("Project received: " + entity.getId() + " ("+entity+")");
        } else {
            entity.updateProperties(data);
            ilarkesto.core.logging.Log.DEBUG("Project updated: " + entity);
        }
        onEntityModifiedRemotely(entity);
    }

    public final scrum.client.project.Project getProject(String id) {
        scrum.client.project.Project ret = projects.get(id);
        if (ret == null) throw new RuntimeException("Project does not exist: " + id);
        return ret;
    }

    public final Set<scrum.client.project.Project> getProjects(Collection<String> ids) {
        Set<scrum.client.project.Project> ret = new HashSet<scrum.client.project.Project>();
        for (String id : ids) {
            scrum.client.project.Project entity = projects.get(id);
            if (entity == null) throw new RuntimeException("Project does not exist: " + id);
            ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.Project> getProjects() {
        return new ArrayList<scrum.client.project.Project>(projects.values());
    }

    public final List<scrum.client.project.Project> getProjectsByLabel(java.lang.String label) {
        List<scrum.client.project.Project> ret = new ArrayList<scrum.client.project.Project>();
        for (scrum.client.project.Project entity : projects.values()) {
            if (entity.isLabel(label)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.Project> getProjectsByDescription(java.lang.String description) {
        List<scrum.client.project.Project> ret = new ArrayList<scrum.client.project.Project>();
        for (scrum.client.project.Project entity : projects.values()) {
            if (entity.isDescription(description)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.Project> getProjectsByBegin(ilarkesto.gwt.client.Date begin) {
        List<scrum.client.project.Project> ret = new ArrayList<scrum.client.project.Project>();
        for (scrum.client.project.Project entity : projects.values()) {
            if (entity.isBegin(begin)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.Project> getProjectsByEnd(ilarkesto.gwt.client.Date end) {
        List<scrum.client.project.Project> ret = new ArrayList<scrum.client.project.Project>();
        for (scrum.client.project.Project entity : projects.values()) {
            if (entity.isEnd(end)) ret.add(entity);
        }
        return ret;
    }






    public final List<scrum.client.project.Project> getProjectsByCurrentSprint(scrum.client.sprint.Sprint currentSprint) {
        List<scrum.client.project.Project> ret = new ArrayList<scrum.client.project.Project>();
        for (scrum.client.project.Project entity : projects.values()) {
            if (entity.isCurrentSprint(currentSprint)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.Project> getProjectsByNextSprint(scrum.client.sprint.Sprint nextSprint) {
        List<scrum.client.project.Project> ret = new ArrayList<scrum.client.project.Project>();
        for (scrum.client.project.Project entity : projects.values()) {
            if (entity.isNextSprint(nextSprint)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.Project> getProjectsByVelocity(java.lang.Integer velocity) {
        List<scrum.client.project.Project> ret = new ArrayList<scrum.client.project.Project>();
        for (scrum.client.project.Project entity : projects.values()) {
            if (entity.isVelocity(velocity)) ret.add(entity);
        }
        return ret;
    }



    public final List<scrum.client.project.Project> getProjectsByLastTaskNumber(int lastTaskNumber) {
        List<scrum.client.project.Project> ret = new ArrayList<scrum.client.project.Project>();
        for (scrum.client.project.Project entity : projects.values()) {
            if (entity.isLastTaskNumber(lastTaskNumber)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.Project> getProjectsByLastRequirementNumber(int lastRequirementNumber) {
        List<scrum.client.project.Project> ret = new ArrayList<scrum.client.project.Project>();
        for (scrum.client.project.Project entity : projects.values()) {
            if (entity.isLastRequirementNumber(lastRequirementNumber)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.Project> getProjectsByLastQualityNumber(int lastQualityNumber) {
        List<scrum.client.project.Project> ret = new ArrayList<scrum.client.project.Project>();
        for (scrum.client.project.Project entity : projects.values()) {
            if (entity.isLastQualityNumber(lastQualityNumber)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.Project> getProjectsByLastRiskNumber(int lastRiskNumber) {
        List<scrum.client.project.Project> ret = new ArrayList<scrum.client.project.Project>();
        for (scrum.client.project.Project entity : projects.values()) {
            if (entity.isLastRiskNumber(lastRiskNumber)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.Project> getProjectsByLastIssueNumber(int lastIssueNumber) {
        List<scrum.client.project.Project> ret = new ArrayList<scrum.client.project.Project>();
        for (scrum.client.project.Project entity : projects.values()) {
            if (entity.isLastIssueNumber(lastIssueNumber)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.Project> getProjectsByLastImpedimentNumber(int lastImpedimentNumber) {
        List<scrum.client.project.Project> ret = new ArrayList<scrum.client.project.Project>();
        for (scrum.client.project.Project entity : projects.values()) {
            if (entity.isLastImpedimentNumber(lastImpedimentNumber)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.Project> getProjectsByLastFileNumber(int lastFileNumber) {
        List<scrum.client.project.Project> ret = new ArrayList<scrum.client.project.Project>();
        for (scrum.client.project.Project entity : projects.values()) {
            if (entity.isLastFileNumber(lastFileNumber)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.Project> getProjectsByLastSubjectNumber(int lastSubjectNumber) {
        List<scrum.client.project.Project> ret = new ArrayList<scrum.client.project.Project>();
        for (scrum.client.project.Project entity : projects.values()) {
            if (entity.isLastSubjectNumber(lastSubjectNumber)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.Project> getProjectsByLastEventNumber(int lastEventNumber) {
        List<scrum.client.project.Project> ret = new ArrayList<scrum.client.project.Project>();
        for (scrum.client.project.Project entity : projects.values()) {
            if (entity.isLastEventNumber(lastEventNumber)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.Project> getProjectsByPunishmentFactor(int punishmentFactor) {
        List<scrum.client.project.Project> ret = new ArrayList<scrum.client.project.Project>();
        for (scrum.client.project.Project entity : projects.values()) {
            if (entity.isPunishmentFactor(punishmentFactor)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.Project> getProjectsByPunishmentUnit(java.lang.String punishmentUnit) {
        List<scrum.client.project.Project> ret = new ArrayList<scrum.client.project.Project>();
        for (scrum.client.project.Project entity : projects.values()) {
            if (entity.isPunishmentUnit(punishmentUnit)) ret.add(entity);
        }
        return ret;
    }

    // --- ProjectEvent ---

    private Map<String, scrum.client.journal.ProjectEvent> projectEvents = new HashMap<String, scrum.client.journal.ProjectEvent>();

    public final void clearProjectEvents() {
        ilarkesto.core.logging.Log.DEBUG("Clearing ProjectEvents");
        projectEvents.clear();
    }

    public final boolean containsProjectEvent(scrum.client.journal.ProjectEvent projectEvent) {
        return projectEvents.containsKey(projectEvent.getId());
    }

    public final void deleteProjectEvent(scrum.client.journal.ProjectEvent projectEvent) {
        projectEvents.remove(projectEvent.getId());
        entityDeleted(projectEvent);
    }

    public final void createProjectEvent(scrum.client.journal.ProjectEvent projectEvent) {
        projectEvents.put(projectEvent.getId(), projectEvent);
        entityCreated(projectEvent);
    }

    private final void updateProjectEvent(Map data) {
        String id = (String) data.get("id");
        scrum.client.journal.ProjectEvent entity = projectEvents.get(id);
        if (entity == null) {
            entity = new scrum.client.journal.ProjectEvent(data);
            projectEvents.put(id, entity);
            ilarkesto.core.logging.Log.DEBUG("ProjectEvent received: " + entity.getId() + " ("+entity+")");
        } else {
            entity.updateProperties(data);
            ilarkesto.core.logging.Log.DEBUG("ProjectEvent updated: " + entity);
        }
        onEntityModifiedRemotely(entity);
    }

    public final scrum.client.journal.ProjectEvent getProjectEvent(String id) {
        scrum.client.journal.ProjectEvent ret = projectEvents.get(id);
        if (ret == null) throw new RuntimeException("ProjectEvent does not exist: " + id);
        return ret;
    }

    public final Set<scrum.client.journal.ProjectEvent> getProjectEvents(Collection<String> ids) {
        Set<scrum.client.journal.ProjectEvent> ret = new HashSet<scrum.client.journal.ProjectEvent>();
        for (String id : ids) {
            scrum.client.journal.ProjectEvent entity = projectEvents.get(id);
            if (entity == null) throw new RuntimeException("ProjectEvent does not exist: " + id);
            ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.journal.ProjectEvent> getProjectEvents() {
        return new ArrayList<scrum.client.journal.ProjectEvent>(projectEvents.values());
    }

    public final List<scrum.client.journal.ProjectEvent> getProjectEventsByProject(scrum.client.project.Project project) {
        List<scrum.client.journal.ProjectEvent> ret = new ArrayList<scrum.client.journal.ProjectEvent>();
        for (scrum.client.journal.ProjectEvent entity : projectEvents.values()) {
            if (entity.isProject(project)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.journal.ProjectEvent> getProjectEventsByLabel(java.lang.String label) {
        List<scrum.client.journal.ProjectEvent> ret = new ArrayList<scrum.client.journal.ProjectEvent>();
        for (scrum.client.journal.ProjectEvent entity : projectEvents.values()) {
            if (entity.isLabel(label)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.journal.ProjectEvent> getProjectEventsByDateAndTime(ilarkesto.gwt.client.DateAndTime dateAndTime) {
        List<scrum.client.journal.ProjectEvent> ret = new ArrayList<scrum.client.journal.ProjectEvent>();
        for (scrum.client.journal.ProjectEvent entity : projectEvents.values()) {
            if (entity.isDateAndTime(dateAndTime)) ret.add(entity);
        }
        return ret;
    }

    // --- ProjectUserConfig ---

    private Map<String, scrum.client.admin.ProjectUserConfig> projectUserConfigs = new HashMap<String, scrum.client.admin.ProjectUserConfig>();

    public final void clearProjectUserConfigs() {
        ilarkesto.core.logging.Log.DEBUG("Clearing ProjectUserConfigs");
        projectUserConfigs.clear();
    }

    public final boolean containsProjectUserConfig(scrum.client.admin.ProjectUserConfig projectUserConfig) {
        return projectUserConfigs.containsKey(projectUserConfig.getId());
    }

    public final void deleteProjectUserConfig(scrum.client.admin.ProjectUserConfig projectUserConfig) {
        projectUserConfigs.remove(projectUserConfig.getId());
        entityDeleted(projectUserConfig);
    }

    public final void createProjectUserConfig(scrum.client.admin.ProjectUserConfig projectUserConfig) {
        projectUserConfigs.put(projectUserConfig.getId(), projectUserConfig);
        entityCreated(projectUserConfig);
    }

    private final void updateProjectUserConfig(Map data) {
        String id = (String) data.get("id");
        scrum.client.admin.ProjectUserConfig entity = projectUserConfigs.get(id);
        if (entity == null) {
            entity = new scrum.client.admin.ProjectUserConfig(data);
            projectUserConfigs.put(id, entity);
            ilarkesto.core.logging.Log.DEBUG("ProjectUserConfig received: " + entity.getId() + " ("+entity+")");
        } else {
            entity.updateProperties(data);
            ilarkesto.core.logging.Log.DEBUG("ProjectUserConfig updated: " + entity);
        }
        onEntityModifiedRemotely(entity);
    }

    public final scrum.client.admin.ProjectUserConfig getProjectUserConfig(String id) {
        scrum.client.admin.ProjectUserConfig ret = projectUserConfigs.get(id);
        if (ret == null) throw new RuntimeException("ProjectUserConfig does not exist: " + id);
        return ret;
    }

    public final Set<scrum.client.admin.ProjectUserConfig> getProjectUserConfigs(Collection<String> ids) {
        Set<scrum.client.admin.ProjectUserConfig> ret = new HashSet<scrum.client.admin.ProjectUserConfig>();
        for (String id : ids) {
            scrum.client.admin.ProjectUserConfig entity = projectUserConfigs.get(id);
            if (entity == null) throw new RuntimeException("ProjectUserConfig does not exist: " + id);
            ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.admin.ProjectUserConfig> getProjectUserConfigs() {
        return new ArrayList<scrum.client.admin.ProjectUserConfig>(projectUserConfigs.values());
    }

    public final List<scrum.client.admin.ProjectUserConfig> getProjectUserConfigsByProject(scrum.client.project.Project project) {
        List<scrum.client.admin.ProjectUserConfig> ret = new ArrayList<scrum.client.admin.ProjectUserConfig>();
        for (scrum.client.admin.ProjectUserConfig entity : projectUserConfigs.values()) {
            if (entity.isProject(project)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.admin.ProjectUserConfig> getProjectUserConfigsByUser(scrum.client.admin.User user) {
        List<scrum.client.admin.ProjectUserConfig> ret = new ArrayList<scrum.client.admin.ProjectUserConfig>();
        for (scrum.client.admin.ProjectUserConfig entity : projectUserConfigs.values()) {
            if (entity.isUser(user)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.admin.ProjectUserConfig> getProjectUserConfigsByColor(java.lang.String color) {
        List<scrum.client.admin.ProjectUserConfig> ret = new ArrayList<scrum.client.admin.ProjectUserConfig>();
        for (scrum.client.admin.ProjectUserConfig entity : projectUserConfigs.values()) {
            if (entity.isColor(color)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.admin.ProjectUserConfig> getProjectUserConfigsByMisconducts(int misconducts) {
        List<scrum.client.admin.ProjectUserConfig> ret = new ArrayList<scrum.client.admin.ProjectUserConfig>();
        for (scrum.client.admin.ProjectUserConfig entity : projectUserConfigs.values()) {
            if (entity.isMisconducts(misconducts)) ret.add(entity);
        }
        return ret;
    }

    // --- Quality ---

    private Map<String, scrum.client.project.Quality> qualitys = new HashMap<String, scrum.client.project.Quality>();

    public final void clearQualitys() {
        ilarkesto.core.logging.Log.DEBUG("Clearing Qualitys");
        qualitys.clear();
    }

    public final boolean containsQuality(scrum.client.project.Quality quality) {
        return qualitys.containsKey(quality.getId());
    }

    public final void deleteQuality(scrum.client.project.Quality quality) {
        qualitys.remove(quality.getId());
        entityDeleted(quality);
    }

    public final void createQuality(scrum.client.project.Quality quality) {
        qualitys.put(quality.getId(), quality);
        entityCreated(quality);
    }

    private final void updateQuality(Map data) {
        String id = (String) data.get("id");
        scrum.client.project.Quality entity = qualitys.get(id);
        if (entity == null) {
            entity = new scrum.client.project.Quality(data);
            qualitys.put(id, entity);
            ilarkesto.core.logging.Log.DEBUG("Quality received: " + entity.getId() + " ("+entity+")");
        } else {
            entity.updateProperties(data);
            ilarkesto.core.logging.Log.DEBUG("Quality updated: " + entity);
        }
        onEntityModifiedRemotely(entity);
    }

    public final scrum.client.project.Quality getQuality(String id) {
        scrum.client.project.Quality ret = qualitys.get(id);
        if (ret == null) throw new RuntimeException("Quality does not exist: " + id);
        return ret;
    }

    public final Set<scrum.client.project.Quality> getQualitys(Collection<String> ids) {
        Set<scrum.client.project.Quality> ret = new HashSet<scrum.client.project.Quality>();
        for (String id : ids) {
            scrum.client.project.Quality entity = qualitys.get(id);
            if (entity == null) throw new RuntimeException("Quality does not exist: " + id);
            ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.Quality> getQualitys() {
        return new ArrayList<scrum.client.project.Quality>(qualitys.values());
    }

    public final List<scrum.client.project.Quality> getQualitysByProject(scrum.client.project.Project project) {
        List<scrum.client.project.Quality> ret = new ArrayList<scrum.client.project.Quality>();
        for (scrum.client.project.Quality entity : qualitys.values()) {
            if (entity.isProject(project)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.Quality> getQualitysByNumber(int number) {
        List<scrum.client.project.Quality> ret = new ArrayList<scrum.client.project.Quality>();
        for (scrum.client.project.Quality entity : qualitys.values()) {
            if (entity.isNumber(number)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.Quality> getQualitysByLabel(java.lang.String label) {
        List<scrum.client.project.Quality> ret = new ArrayList<scrum.client.project.Quality>();
        for (scrum.client.project.Quality entity : qualitys.values()) {
            if (entity.isLabel(label)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.Quality> getQualitysByDescription(java.lang.String description) {
        List<scrum.client.project.Quality> ret = new ArrayList<scrum.client.project.Quality>();
        for (scrum.client.project.Quality entity : qualitys.values()) {
            if (entity.isDescription(description)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.Quality> getQualitysByTestDescription(java.lang.String testDescription) {
        List<scrum.client.project.Quality> ret = new ArrayList<scrum.client.project.Quality>();
        for (scrum.client.project.Quality entity : qualitys.values()) {
            if (entity.isTestDescription(testDescription)) ret.add(entity);
        }
        return ret;
    }

    // --- Release ---

    private Map<String, scrum.client.release.Release> releases = new HashMap<String, scrum.client.release.Release>();

    public final void clearReleases() {
        ilarkesto.core.logging.Log.DEBUG("Clearing Releases");
        releases.clear();
    }

    public final boolean containsRelease(scrum.client.release.Release release) {
        return releases.containsKey(release.getId());
    }

    public final void deleteRelease(scrum.client.release.Release release) {
        releases.remove(release.getId());
        entityDeleted(release);
    }

    public final void createRelease(scrum.client.release.Release release) {
        releases.put(release.getId(), release);
        entityCreated(release);
    }

    private final void updateRelease(Map data) {
        String id = (String) data.get("id");
        scrum.client.release.Release entity = releases.get(id);
        if (entity == null) {
            entity = new scrum.client.release.Release(data);
            releases.put(id, entity);
            ilarkesto.core.logging.Log.DEBUG("Release received: " + entity.getId() + " ("+entity+")");
        } else {
            entity.updateProperties(data);
            ilarkesto.core.logging.Log.DEBUG("Release updated: " + entity);
        }
        onEntityModifiedRemotely(entity);
    }

    public final scrum.client.release.Release getRelease(String id) {
        scrum.client.release.Release ret = releases.get(id);
        if (ret == null) throw new RuntimeException("Release does not exist: " + id);
        return ret;
    }

    public final Set<scrum.client.release.Release> getReleases(Collection<String> ids) {
        Set<scrum.client.release.Release> ret = new HashSet<scrum.client.release.Release>();
        for (String id : ids) {
            scrum.client.release.Release entity = releases.get(id);
            if (entity == null) throw new RuntimeException("Release does not exist: " + id);
            ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.release.Release> getReleases() {
        return new ArrayList<scrum.client.release.Release>(releases.values());
    }

    public final List<scrum.client.release.Release> getReleasesByProject(scrum.client.project.Project project) {
        List<scrum.client.release.Release> ret = new ArrayList<scrum.client.release.Release>();
        for (scrum.client.release.Release entity : releases.values()) {
            if (entity.isProject(project)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.release.Release> getReleasesByLabel(java.lang.String label) {
        List<scrum.client.release.Release> ret = new ArrayList<scrum.client.release.Release>();
        for (scrum.client.release.Release entity : releases.values()) {
            if (entity.isLabel(label)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.release.Release> getReleasesByPublicationDate(ilarkesto.gwt.client.Date publicationDate) {
        List<scrum.client.release.Release> ret = new ArrayList<scrum.client.release.Release>();
        for (scrum.client.release.Release entity : releases.values()) {
            if (entity.isPublicationDate(publicationDate)) ret.add(entity);
        }
        return ret;
    }

    // --- Requirement ---

    private Map<String, scrum.client.project.Requirement> requirements = new HashMap<String, scrum.client.project.Requirement>();

    public final void clearRequirements() {
        ilarkesto.core.logging.Log.DEBUG("Clearing Requirements");
        requirements.clear();
    }

    public final boolean containsRequirement(scrum.client.project.Requirement requirement) {
        return requirements.containsKey(requirement.getId());
    }

    public final void deleteRequirement(scrum.client.project.Requirement requirement) {
        requirements.remove(requirement.getId());
        entityDeleted(requirement);
    }

    public final void createRequirement(scrum.client.project.Requirement requirement) {
        requirements.put(requirement.getId(), requirement);
        entityCreated(requirement);
    }

    private final void updateRequirement(Map data) {
        String id = (String) data.get("id");
        scrum.client.project.Requirement entity = requirements.get(id);
        if (entity == null) {
            entity = new scrum.client.project.Requirement(data);
            requirements.put(id, entity);
            ilarkesto.core.logging.Log.DEBUG("Requirement received: " + entity.getId() + " ("+entity+")");
        } else {
            entity.updateProperties(data);
            ilarkesto.core.logging.Log.DEBUG("Requirement updated: " + entity);
        }
        onEntityModifiedRemotely(entity);
    }

    public final scrum.client.project.Requirement getRequirement(String id) {
        scrum.client.project.Requirement ret = requirements.get(id);
        if (ret == null) throw new RuntimeException("Requirement does not exist: " + id);
        return ret;
    }

    public final Set<scrum.client.project.Requirement> getRequirements(Collection<String> ids) {
        Set<scrum.client.project.Requirement> ret = new HashSet<scrum.client.project.Requirement>();
        for (String id : ids) {
            scrum.client.project.Requirement entity = requirements.get(id);
            if (entity == null) throw new RuntimeException("Requirement does not exist: " + id);
            ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.Requirement> getRequirements() {
        return new ArrayList<scrum.client.project.Requirement>(requirements.values());
    }

    public final List<scrum.client.project.Requirement> getRequirementsByProject(scrum.client.project.Project project) {
        List<scrum.client.project.Requirement> ret = new ArrayList<scrum.client.project.Requirement>();
        for (scrum.client.project.Requirement entity : requirements.values()) {
            if (entity.isProject(project)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.Requirement> getRequirementsBySprint(scrum.client.sprint.Sprint sprint) {
        List<scrum.client.project.Requirement> ret = new ArrayList<scrum.client.project.Requirement>();
        for (scrum.client.project.Requirement entity : requirements.values()) {
            if (entity.isSprint(sprint)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.Requirement> getRequirementsByNumber(int number) {
        List<scrum.client.project.Requirement> ret = new ArrayList<scrum.client.project.Requirement>();
        for (scrum.client.project.Requirement entity : requirements.values()) {
            if (entity.isNumber(number)) ret.add(entity);
        }
        return ret;
    }


    public final List<scrum.client.project.Requirement> getRequirementsByLabel(java.lang.String label) {
        List<scrum.client.project.Requirement> ret = new ArrayList<scrum.client.project.Requirement>();
        for (scrum.client.project.Requirement entity : requirements.values()) {
            if (entity.isLabel(label)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.Requirement> getRequirementsByDescription(java.lang.String description) {
        List<scrum.client.project.Requirement> ret = new ArrayList<scrum.client.project.Requirement>();
        for (scrum.client.project.Requirement entity : requirements.values()) {
            if (entity.isDescription(description)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.Requirement> getRequirementsByTestDescription(java.lang.String testDescription) {
        List<scrum.client.project.Requirement> ret = new ArrayList<scrum.client.project.Requirement>();
        for (scrum.client.project.Requirement entity : requirements.values()) {
            if (entity.isTestDescription(testDescription)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.Requirement> getRequirementsByEstimatedWork(java.lang.Float estimatedWork) {
        List<scrum.client.project.Requirement> ret = new ArrayList<scrum.client.project.Requirement>();
        for (scrum.client.project.Requirement entity : requirements.values()) {
            if (entity.isEstimatedWork(estimatedWork)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.Requirement> getRequirementsByClosed(boolean closed) {
        List<scrum.client.project.Requirement> ret = new ArrayList<scrum.client.project.Requirement>();
        for (scrum.client.project.Requirement entity : requirements.values()) {
            if (entity.isClosed(closed)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.Requirement> getRequirementsByDirty(boolean dirty) {
        List<scrum.client.project.Requirement> ret = new ArrayList<scrum.client.project.Requirement>();
        for (scrum.client.project.Requirement entity : requirements.values()) {
            if (entity.isDirty(dirty)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.Requirement> getRequirementsByWorkEstimationVotingActive(boolean workEstimationVotingActive) {
        List<scrum.client.project.Requirement> ret = new ArrayList<scrum.client.project.Requirement>();
        for (scrum.client.project.Requirement entity : requirements.values()) {
            if (entity.isWorkEstimationVotingActive(workEstimationVotingActive)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.Requirement> getRequirementsByWorkEstimationVotingShowoff(boolean workEstimationVotingShowoff) {
        List<scrum.client.project.Requirement> ret = new ArrayList<scrum.client.project.Requirement>();
        for (scrum.client.project.Requirement entity : requirements.values()) {
            if (entity.isWorkEstimationVotingShowoff(workEstimationVotingShowoff)) ret.add(entity);
        }
        return ret;
    }

    // --- RequirementEstimationVote ---

    private Map<String, scrum.client.estimation.RequirementEstimationVote> requirementEstimationVotes = new HashMap<String, scrum.client.estimation.RequirementEstimationVote>();

    public final void clearRequirementEstimationVotes() {
        ilarkesto.core.logging.Log.DEBUG("Clearing RequirementEstimationVotes");
        requirementEstimationVotes.clear();
    }

    public final boolean containsRequirementEstimationVote(scrum.client.estimation.RequirementEstimationVote requirementEstimationVote) {
        return requirementEstimationVotes.containsKey(requirementEstimationVote.getId());
    }

    public final void deleteRequirementEstimationVote(scrum.client.estimation.RequirementEstimationVote requirementEstimationVote) {
        requirementEstimationVotes.remove(requirementEstimationVote.getId());
        entityDeleted(requirementEstimationVote);
    }

    public final void createRequirementEstimationVote(scrum.client.estimation.RequirementEstimationVote requirementEstimationVote) {
        requirementEstimationVotes.put(requirementEstimationVote.getId(), requirementEstimationVote);
        entityCreated(requirementEstimationVote);
    }

    private final void updateRequirementEstimationVote(Map data) {
        String id = (String) data.get("id");
        scrum.client.estimation.RequirementEstimationVote entity = requirementEstimationVotes.get(id);
        if (entity == null) {
            entity = new scrum.client.estimation.RequirementEstimationVote(data);
            requirementEstimationVotes.put(id, entity);
            ilarkesto.core.logging.Log.DEBUG("RequirementEstimationVote received: " + entity.getId() + " ("+entity+")");
        } else {
            entity.updateProperties(data);
            ilarkesto.core.logging.Log.DEBUG("RequirementEstimationVote updated: " + entity);
        }
        onEntityModifiedRemotely(entity);
    }

    public final scrum.client.estimation.RequirementEstimationVote getRequirementEstimationVote(String id) {
        scrum.client.estimation.RequirementEstimationVote ret = requirementEstimationVotes.get(id);
        if (ret == null) throw new RuntimeException("RequirementEstimationVote does not exist: " + id);
        return ret;
    }

    public final Set<scrum.client.estimation.RequirementEstimationVote> getRequirementEstimationVotes(Collection<String> ids) {
        Set<scrum.client.estimation.RequirementEstimationVote> ret = new HashSet<scrum.client.estimation.RequirementEstimationVote>();
        for (String id : ids) {
            scrum.client.estimation.RequirementEstimationVote entity = requirementEstimationVotes.get(id);
            if (entity == null) throw new RuntimeException("RequirementEstimationVote does not exist: " + id);
            ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.estimation.RequirementEstimationVote> getRequirementEstimationVotes() {
        return new ArrayList<scrum.client.estimation.RequirementEstimationVote>(requirementEstimationVotes.values());
    }

    public final List<scrum.client.estimation.RequirementEstimationVote> getRequirementEstimationVotesByRequirement(scrum.client.project.Requirement requirement) {
        List<scrum.client.estimation.RequirementEstimationVote> ret = new ArrayList<scrum.client.estimation.RequirementEstimationVote>();
        for (scrum.client.estimation.RequirementEstimationVote entity : requirementEstimationVotes.values()) {
            if (entity.isRequirement(requirement)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.estimation.RequirementEstimationVote> getRequirementEstimationVotesByUser(scrum.client.admin.User user) {
        List<scrum.client.estimation.RequirementEstimationVote> ret = new ArrayList<scrum.client.estimation.RequirementEstimationVote>();
        for (scrum.client.estimation.RequirementEstimationVote entity : requirementEstimationVotes.values()) {
            if (entity.isUser(user)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.estimation.RequirementEstimationVote> getRequirementEstimationVotesByEstimatedWork(java.lang.Float estimatedWork) {
        List<scrum.client.estimation.RequirementEstimationVote> ret = new ArrayList<scrum.client.estimation.RequirementEstimationVote>();
        for (scrum.client.estimation.RequirementEstimationVote entity : requirementEstimationVotes.values()) {
            if (entity.isEstimatedWork(estimatedWork)) ret.add(entity);
        }
        return ret;
    }

    // --- Risk ---

    private Map<String, scrum.client.risks.Risk> risks = new HashMap<String, scrum.client.risks.Risk>();

    public final void clearRisks() {
        ilarkesto.core.logging.Log.DEBUG("Clearing Risks");
        risks.clear();
    }

    public final boolean containsRisk(scrum.client.risks.Risk risk) {
        return risks.containsKey(risk.getId());
    }

    public final void deleteRisk(scrum.client.risks.Risk risk) {
        risks.remove(risk.getId());
        entityDeleted(risk);
    }

    public final void createRisk(scrum.client.risks.Risk risk) {
        risks.put(risk.getId(), risk);
        entityCreated(risk);
    }

    private final void updateRisk(Map data) {
        String id = (String) data.get("id");
        scrum.client.risks.Risk entity = risks.get(id);
        if (entity == null) {
            entity = new scrum.client.risks.Risk(data);
            risks.put(id, entity);
            ilarkesto.core.logging.Log.DEBUG("Risk received: " + entity.getId() + " ("+entity+")");
        } else {
            entity.updateProperties(data);
            ilarkesto.core.logging.Log.DEBUG("Risk updated: " + entity);
        }
        onEntityModifiedRemotely(entity);
    }

    public final scrum.client.risks.Risk getRisk(String id) {
        scrum.client.risks.Risk ret = risks.get(id);
        if (ret == null) throw new RuntimeException("Risk does not exist: " + id);
        return ret;
    }

    public final Set<scrum.client.risks.Risk> getRisks(Collection<String> ids) {
        Set<scrum.client.risks.Risk> ret = new HashSet<scrum.client.risks.Risk>();
        for (String id : ids) {
            scrum.client.risks.Risk entity = risks.get(id);
            if (entity == null) throw new RuntimeException("Risk does not exist: " + id);
            ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.risks.Risk> getRisks() {
        return new ArrayList<scrum.client.risks.Risk>(risks.values());
    }

    public final List<scrum.client.risks.Risk> getRisksByProject(scrum.client.project.Project project) {
        List<scrum.client.risks.Risk> ret = new ArrayList<scrum.client.risks.Risk>();
        for (scrum.client.risks.Risk entity : risks.values()) {
            if (entity.isProject(project)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.risks.Risk> getRisksByNumber(int number) {
        List<scrum.client.risks.Risk> ret = new ArrayList<scrum.client.risks.Risk>();
        for (scrum.client.risks.Risk entity : risks.values()) {
            if (entity.isNumber(number)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.risks.Risk> getRisksByLabel(java.lang.String label) {
        List<scrum.client.risks.Risk> ret = new ArrayList<scrum.client.risks.Risk>();
        for (scrum.client.risks.Risk entity : risks.values()) {
            if (entity.isLabel(label)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.risks.Risk> getRisksByDescription(java.lang.String description) {
        List<scrum.client.risks.Risk> ret = new ArrayList<scrum.client.risks.Risk>();
        for (scrum.client.risks.Risk entity : risks.values()) {
            if (entity.isDescription(description)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.risks.Risk> getRisksByProbabilityMitigation(java.lang.String probabilityMitigation) {
        List<scrum.client.risks.Risk> ret = new ArrayList<scrum.client.risks.Risk>();
        for (scrum.client.risks.Risk entity : risks.values()) {
            if (entity.isProbabilityMitigation(probabilityMitigation)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.risks.Risk> getRisksByImpactMitigation(java.lang.String impactMitigation) {
        List<scrum.client.risks.Risk> ret = new ArrayList<scrum.client.risks.Risk>();
        for (scrum.client.risks.Risk entity : risks.values()) {
            if (entity.isImpactMitigation(impactMitigation)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.risks.Risk> getRisksByProbability(int probability) {
        List<scrum.client.risks.Risk> ret = new ArrayList<scrum.client.risks.Risk>();
        for (scrum.client.risks.Risk entity : risks.values()) {
            if (entity.isProbability(probability)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.risks.Risk> getRisksByImpact(int impact) {
        List<scrum.client.risks.Risk> ret = new ArrayList<scrum.client.risks.Risk>();
        for (scrum.client.risks.Risk entity : risks.values()) {
            if (entity.isImpact(impact)) ret.add(entity);
        }
        return ret;
    }

    // --- SimpleEvent ---

    private Map<String, scrum.client.calendar.SimpleEvent> simpleEvents = new HashMap<String, scrum.client.calendar.SimpleEvent>();

    public final void clearSimpleEvents() {
        ilarkesto.core.logging.Log.DEBUG("Clearing SimpleEvents");
        simpleEvents.clear();
    }

    public final boolean containsSimpleEvent(scrum.client.calendar.SimpleEvent simpleEvent) {
        return simpleEvents.containsKey(simpleEvent.getId());
    }

    public final void deleteSimpleEvent(scrum.client.calendar.SimpleEvent simpleEvent) {
        simpleEvents.remove(simpleEvent.getId());
        entityDeleted(simpleEvent);
    }

    public final void createSimpleEvent(scrum.client.calendar.SimpleEvent simpleEvent) {
        simpleEvents.put(simpleEvent.getId(), simpleEvent);
        entityCreated(simpleEvent);
    }

    private final void updateSimpleEvent(Map data) {
        String id = (String) data.get("id");
        scrum.client.calendar.SimpleEvent entity = simpleEvents.get(id);
        if (entity == null) {
            entity = new scrum.client.calendar.SimpleEvent(data);
            simpleEvents.put(id, entity);
            ilarkesto.core.logging.Log.DEBUG("SimpleEvent received: " + entity.getId() + " ("+entity+")");
        } else {
            entity.updateProperties(data);
            ilarkesto.core.logging.Log.DEBUG("SimpleEvent updated: " + entity);
        }
        onEntityModifiedRemotely(entity);
    }

    public final scrum.client.calendar.SimpleEvent getSimpleEvent(String id) {
        scrum.client.calendar.SimpleEvent ret = simpleEvents.get(id);
        if (ret == null) throw new RuntimeException("SimpleEvent does not exist: " + id);
        return ret;
    }

    public final Set<scrum.client.calendar.SimpleEvent> getSimpleEvents(Collection<String> ids) {
        Set<scrum.client.calendar.SimpleEvent> ret = new HashSet<scrum.client.calendar.SimpleEvent>();
        for (String id : ids) {
            scrum.client.calendar.SimpleEvent entity = simpleEvents.get(id);
            if (entity == null) throw new RuntimeException("SimpleEvent does not exist: " + id);
            ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.calendar.SimpleEvent> getSimpleEvents() {
        return new ArrayList<scrum.client.calendar.SimpleEvent>(simpleEvents.values());
    }

    public final List<scrum.client.calendar.SimpleEvent> getSimpleEventsByProject(scrum.client.project.Project project) {
        List<scrum.client.calendar.SimpleEvent> ret = new ArrayList<scrum.client.calendar.SimpleEvent>();
        for (scrum.client.calendar.SimpleEvent entity : simpleEvents.values()) {
            if (entity.isProject(project)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.calendar.SimpleEvent> getSimpleEventsByLabel(java.lang.String label) {
        List<scrum.client.calendar.SimpleEvent> ret = new ArrayList<scrum.client.calendar.SimpleEvent>();
        for (scrum.client.calendar.SimpleEvent entity : simpleEvents.values()) {
            if (entity.isLabel(label)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.calendar.SimpleEvent> getSimpleEventsByNumber(int number) {
        List<scrum.client.calendar.SimpleEvent> ret = new ArrayList<scrum.client.calendar.SimpleEvent>();
        for (scrum.client.calendar.SimpleEvent entity : simpleEvents.values()) {
            if (entity.isNumber(number)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.calendar.SimpleEvent> getSimpleEventsByDate(ilarkesto.gwt.client.Date date) {
        List<scrum.client.calendar.SimpleEvent> ret = new ArrayList<scrum.client.calendar.SimpleEvent>();
        for (scrum.client.calendar.SimpleEvent entity : simpleEvents.values()) {
            if (entity.isDate(date)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.calendar.SimpleEvent> getSimpleEventsByTime(ilarkesto.gwt.client.Time time) {
        List<scrum.client.calendar.SimpleEvent> ret = new ArrayList<scrum.client.calendar.SimpleEvent>();
        for (scrum.client.calendar.SimpleEvent entity : simpleEvents.values()) {
            if (entity.isTime(time)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.calendar.SimpleEvent> getSimpleEventsByLocation(java.lang.String location) {
        List<scrum.client.calendar.SimpleEvent> ret = new ArrayList<scrum.client.calendar.SimpleEvent>();
        for (scrum.client.calendar.SimpleEvent entity : simpleEvents.values()) {
            if (entity.isLocation(location)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.calendar.SimpleEvent> getSimpleEventsByDuration(java.lang.Integer duration) {
        List<scrum.client.calendar.SimpleEvent> ret = new ArrayList<scrum.client.calendar.SimpleEvent>();
        for (scrum.client.calendar.SimpleEvent entity : simpleEvents.values()) {
            if (entity.isDuration(duration)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.calendar.SimpleEvent> getSimpleEventsByAgenda(java.lang.String agenda) {
        List<scrum.client.calendar.SimpleEvent> ret = new ArrayList<scrum.client.calendar.SimpleEvent>();
        for (scrum.client.calendar.SimpleEvent entity : simpleEvents.values()) {
            if (entity.isAgenda(agenda)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.calendar.SimpleEvent> getSimpleEventsByNote(java.lang.String note) {
        List<scrum.client.calendar.SimpleEvent> ret = new ArrayList<scrum.client.calendar.SimpleEvent>();
        for (scrum.client.calendar.SimpleEvent entity : simpleEvents.values()) {
            if (entity.isNote(note)) ret.add(entity);
        }
        return ret;
    }

    // --- Sprint ---

    private Map<String, scrum.client.sprint.Sprint> sprints = new HashMap<String, scrum.client.sprint.Sprint>();

    public final void clearSprints() {
        ilarkesto.core.logging.Log.DEBUG("Clearing Sprints");
        sprints.clear();
    }

    public final boolean containsSprint(scrum.client.sprint.Sprint sprint) {
        return sprints.containsKey(sprint.getId());
    }

    public final void deleteSprint(scrum.client.sprint.Sprint sprint) {
        sprints.remove(sprint.getId());
        entityDeleted(sprint);
    }

    public final void createSprint(scrum.client.sprint.Sprint sprint) {
        sprints.put(sprint.getId(), sprint);
        entityCreated(sprint);
    }

    private final void updateSprint(Map data) {
        String id = (String) data.get("id");
        scrum.client.sprint.Sprint entity = sprints.get(id);
        if (entity == null) {
            entity = new scrum.client.sprint.Sprint(data);
            sprints.put(id, entity);
            ilarkesto.core.logging.Log.DEBUG("Sprint received: " + entity.getId() + " ("+entity+")");
        } else {
            entity.updateProperties(data);
            ilarkesto.core.logging.Log.DEBUG("Sprint updated: " + entity);
        }
        onEntityModifiedRemotely(entity);
    }

    public final scrum.client.sprint.Sprint getSprint(String id) {
        scrum.client.sprint.Sprint ret = sprints.get(id);
        if (ret == null) throw new RuntimeException("Sprint does not exist: " + id);
        return ret;
    }

    public final Set<scrum.client.sprint.Sprint> getSprints(Collection<String> ids) {
        Set<scrum.client.sprint.Sprint> ret = new HashSet<scrum.client.sprint.Sprint>();
        for (String id : ids) {
            scrum.client.sprint.Sprint entity = sprints.get(id);
            if (entity == null) throw new RuntimeException("Sprint does not exist: " + id);
            ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.sprint.Sprint> getSprints() {
        return new ArrayList<scrum.client.sprint.Sprint>(sprints.values());
    }

    public final List<scrum.client.sprint.Sprint> getSprintsByProject(scrum.client.project.Project project) {
        List<scrum.client.sprint.Sprint> ret = new ArrayList<scrum.client.sprint.Sprint>();
        for (scrum.client.sprint.Sprint entity : sprints.values()) {
            if (entity.isProject(project)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.sprint.Sprint> getSprintsByLabel(java.lang.String label) {
        List<scrum.client.sprint.Sprint> ret = new ArrayList<scrum.client.sprint.Sprint>();
        for (scrum.client.sprint.Sprint entity : sprints.values()) {
            if (entity.isLabel(label)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.sprint.Sprint> getSprintsByGoal(java.lang.String goal) {
        List<scrum.client.sprint.Sprint> ret = new ArrayList<scrum.client.sprint.Sprint>();
        for (scrum.client.sprint.Sprint entity : sprints.values()) {
            if (entity.isGoal(goal)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.sprint.Sprint> getSprintsByBegin(ilarkesto.gwt.client.Date begin) {
        List<scrum.client.sprint.Sprint> ret = new ArrayList<scrum.client.sprint.Sprint>();
        for (scrum.client.sprint.Sprint entity : sprints.values()) {
            if (entity.isBegin(begin)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.sprint.Sprint> getSprintsByEnd(ilarkesto.gwt.client.Date end) {
        List<scrum.client.sprint.Sprint> ret = new ArrayList<scrum.client.sprint.Sprint>();
        for (scrum.client.sprint.Sprint entity : sprints.values()) {
            if (entity.isEnd(end)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.sprint.Sprint> getSprintsByVelocity(java.lang.Float velocity) {
        List<scrum.client.sprint.Sprint> ret = new ArrayList<scrum.client.sprint.Sprint>();
        for (scrum.client.sprint.Sprint entity : sprints.values()) {
            if (entity.isVelocity(velocity)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.sprint.Sprint> getSprintsByCompletedRequirementLabels(java.lang.String completedRequirementLabels) {
        List<scrum.client.sprint.Sprint> ret = new ArrayList<scrum.client.sprint.Sprint>();
        for (scrum.client.sprint.Sprint entity : sprints.values()) {
            if (entity.isCompletedRequirementLabels(completedRequirementLabels)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.sprint.Sprint> getSprintsByPlanningNote(java.lang.String planningNote) {
        List<scrum.client.sprint.Sprint> ret = new ArrayList<scrum.client.sprint.Sprint>();
        for (scrum.client.sprint.Sprint entity : sprints.values()) {
            if (entity.isPlanningNote(planningNote)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.sprint.Sprint> getSprintsByReviewNote(java.lang.String reviewNote) {
        List<scrum.client.sprint.Sprint> ret = new ArrayList<scrum.client.sprint.Sprint>();
        for (scrum.client.sprint.Sprint entity : sprints.values()) {
            if (entity.isReviewNote(reviewNote)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.sprint.Sprint> getSprintsByRetrospectiveNote(java.lang.String retrospectiveNote) {
        List<scrum.client.sprint.Sprint> ret = new ArrayList<scrum.client.sprint.Sprint>();
        for (scrum.client.sprint.Sprint entity : sprints.values()) {
            if (entity.isRetrospectiveNote(retrospectiveNote)) ret.add(entity);
        }
        return ret;
    }

    // --- Subject ---

    private Map<String, scrum.client.collaboration.Subject> subjects = new HashMap<String, scrum.client.collaboration.Subject>();

    public final void clearSubjects() {
        ilarkesto.core.logging.Log.DEBUG("Clearing Subjects");
        subjects.clear();
    }

    public final boolean containsSubject(scrum.client.collaboration.Subject subject) {
        return subjects.containsKey(subject.getId());
    }

    public final void deleteSubject(scrum.client.collaboration.Subject subject) {
        subjects.remove(subject.getId());
        entityDeleted(subject);
    }

    public final void createSubject(scrum.client.collaboration.Subject subject) {
        subjects.put(subject.getId(), subject);
        entityCreated(subject);
    }

    private final void updateSubject(Map data) {
        String id = (String) data.get("id");
        scrum.client.collaboration.Subject entity = subjects.get(id);
        if (entity == null) {
            entity = new scrum.client.collaboration.Subject(data);
            subjects.put(id, entity);
            ilarkesto.core.logging.Log.DEBUG("Subject received: " + entity.getId() + " ("+entity+")");
        } else {
            entity.updateProperties(data);
            ilarkesto.core.logging.Log.DEBUG("Subject updated: " + entity);
        }
        onEntityModifiedRemotely(entity);
    }

    public final scrum.client.collaboration.Subject getSubject(String id) {
        scrum.client.collaboration.Subject ret = subjects.get(id);
        if (ret == null) throw new RuntimeException("Subject does not exist: " + id);
        return ret;
    }

    public final Set<scrum.client.collaboration.Subject> getSubjects(Collection<String> ids) {
        Set<scrum.client.collaboration.Subject> ret = new HashSet<scrum.client.collaboration.Subject>();
        for (String id : ids) {
            scrum.client.collaboration.Subject entity = subjects.get(id);
            if (entity == null) throw new RuntimeException("Subject does not exist: " + id);
            ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.collaboration.Subject> getSubjects() {
        return new ArrayList<scrum.client.collaboration.Subject>(subjects.values());
    }

    public final List<scrum.client.collaboration.Subject> getSubjectsByProject(scrum.client.project.Project project) {
        List<scrum.client.collaboration.Subject> ret = new ArrayList<scrum.client.collaboration.Subject>();
        for (scrum.client.collaboration.Subject entity : subjects.values()) {
            if (entity.isProject(project)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.collaboration.Subject> getSubjectsByLabel(java.lang.String label) {
        List<scrum.client.collaboration.Subject> ret = new ArrayList<scrum.client.collaboration.Subject>();
        for (scrum.client.collaboration.Subject entity : subjects.values()) {
            if (entity.isLabel(label)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.collaboration.Subject> getSubjectsByText(java.lang.String text) {
        List<scrum.client.collaboration.Subject> ret = new ArrayList<scrum.client.collaboration.Subject>();
        for (scrum.client.collaboration.Subject entity : subjects.values()) {
            if (entity.isText(text)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.collaboration.Subject> getSubjectsByNumber(int number) {
        List<scrum.client.collaboration.Subject> ret = new ArrayList<scrum.client.collaboration.Subject>();
        for (scrum.client.collaboration.Subject entity : subjects.values()) {
            if (entity.isNumber(number)) ret.add(entity);
        }
        return ret;
    }

    // --- Task ---

    private Map<String, scrum.client.sprint.Task> tasks = new HashMap<String, scrum.client.sprint.Task>();

    public final void clearTasks() {
        ilarkesto.core.logging.Log.DEBUG("Clearing Tasks");
        tasks.clear();
    }

    public final boolean containsTask(scrum.client.sprint.Task task) {
        return tasks.containsKey(task.getId());
    }

    public final void deleteTask(scrum.client.sprint.Task task) {
        tasks.remove(task.getId());
        entityDeleted(task);
    }

    public final void createTask(scrum.client.sprint.Task task) {
        tasks.put(task.getId(), task);
        entityCreated(task);
    }

    private final void updateTask(Map data) {
        String id = (String) data.get("id");
        scrum.client.sprint.Task entity = tasks.get(id);
        if (entity == null) {
            entity = new scrum.client.sprint.Task(data);
            tasks.put(id, entity);
            ilarkesto.core.logging.Log.DEBUG("Task received: " + entity.getId() + " ("+entity+")");
        } else {
            entity.updateProperties(data);
            ilarkesto.core.logging.Log.DEBUG("Task updated: " + entity);
        }
        onEntityModifiedRemotely(entity);
    }

    public final scrum.client.sprint.Task getTask(String id) {
        scrum.client.sprint.Task ret = tasks.get(id);
        if (ret == null) throw new RuntimeException("Task does not exist: " + id);
        return ret;
    }

    public final Set<scrum.client.sprint.Task> getTasks(Collection<String> ids) {
        Set<scrum.client.sprint.Task> ret = new HashSet<scrum.client.sprint.Task>();
        for (String id : ids) {
            scrum.client.sprint.Task entity = tasks.get(id);
            if (entity == null) throw new RuntimeException("Task does not exist: " + id);
            ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.sprint.Task> getTasks() {
        return new ArrayList<scrum.client.sprint.Task>(tasks.values());
    }

    public final List<scrum.client.sprint.Task> getTasksByRequirement(scrum.client.project.Requirement requirement) {
        List<scrum.client.sprint.Task> ret = new ArrayList<scrum.client.sprint.Task>();
        for (scrum.client.sprint.Task entity : tasks.values()) {
            if (entity.isRequirement(requirement)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.sprint.Task> getTasksByNumber(int number) {
        List<scrum.client.sprint.Task> ret = new ArrayList<scrum.client.sprint.Task>();
        for (scrum.client.sprint.Task entity : tasks.values()) {
            if (entity.isNumber(number)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.sprint.Task> getTasksByLabel(java.lang.String label) {
        List<scrum.client.sprint.Task> ret = new ArrayList<scrum.client.sprint.Task>();
        for (scrum.client.sprint.Task entity : tasks.values()) {
            if (entity.isLabel(label)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.sprint.Task> getTasksByDescription(java.lang.String description) {
        List<scrum.client.sprint.Task> ret = new ArrayList<scrum.client.sprint.Task>();
        for (scrum.client.sprint.Task entity : tasks.values()) {
            if (entity.isDescription(description)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.sprint.Task> getTasksByRemainingWork(int remainingWork) {
        List<scrum.client.sprint.Task> ret = new ArrayList<scrum.client.sprint.Task>();
        for (scrum.client.sprint.Task entity : tasks.values()) {
            if (entity.isRemainingWork(remainingWork)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.sprint.Task> getTasksByBurnedWork(int burnedWork) {
        List<scrum.client.sprint.Task> ret = new ArrayList<scrum.client.sprint.Task>();
        for (scrum.client.sprint.Task entity : tasks.values()) {
            if (entity.isBurnedWork(burnedWork)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.sprint.Task> getTasksByOwner(scrum.client.admin.User owner) {
        List<scrum.client.sprint.Task> ret = new ArrayList<scrum.client.sprint.Task>();
        for (scrum.client.sprint.Task entity : tasks.values()) {
            if (entity.isOwner(owner)) ret.add(entity);
        }
        return ret;
    }

    // --- User ---

    private Map<String, scrum.client.admin.User> users = new HashMap<String, scrum.client.admin.User>();

    public final void clearUsers() {
        ilarkesto.core.logging.Log.DEBUG("Clearing Users");
        users.clear();
    }

    public final boolean containsUser(scrum.client.admin.User user) {
        return users.containsKey(user.getId());
    }

    public final void deleteUser(scrum.client.admin.User user) {
        users.remove(user.getId());
        entityDeleted(user);
    }

    public final void createUser(scrum.client.admin.User user) {
        users.put(user.getId(), user);
        entityCreated(user);
    }

    private final void updateUser(Map data) {
        String id = (String) data.get("id");
        scrum.client.admin.User entity = users.get(id);
        if (entity == null) {
            entity = new scrum.client.admin.User(data);
            users.put(id, entity);
            ilarkesto.core.logging.Log.DEBUG("User received: " + entity.getId() + " ("+entity+")");
        } else {
            entity.updateProperties(data);
            ilarkesto.core.logging.Log.DEBUG("User updated: " + entity);
        }
        onEntityModifiedRemotely(entity);
    }

    public final scrum.client.admin.User getUser(String id) {
        scrum.client.admin.User ret = users.get(id);
        if (ret == null) throw new RuntimeException("User does not exist: " + id);
        return ret;
    }

    public final Set<scrum.client.admin.User> getUsers(Collection<String> ids) {
        Set<scrum.client.admin.User> ret = new HashSet<scrum.client.admin.User>();
        for (String id : ids) {
            scrum.client.admin.User entity = users.get(id);
            if (entity == null) throw new RuntimeException("User does not exist: " + id);
            ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.admin.User> getUsers() {
        return new ArrayList<scrum.client.admin.User>(users.values());
    }

    public final List<scrum.client.admin.User> getUsersByName(java.lang.String name) {
        List<scrum.client.admin.User> ret = new ArrayList<scrum.client.admin.User>();
        for (scrum.client.admin.User entity : users.values()) {
            if (entity.isName(name)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.admin.User> getUsersByAdmin(boolean admin) {
        List<scrum.client.admin.User> ret = new ArrayList<scrum.client.admin.User>();
        for (scrum.client.admin.User entity : users.values()) {
            if (entity.isAdmin(admin)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.admin.User> getUsersByEmail(java.lang.String email) {
        List<scrum.client.admin.User> ret = new ArrayList<scrum.client.admin.User>();
        for (scrum.client.admin.User entity : users.values()) {
            if (entity.isEmail(email)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.admin.User> getUsersByCurrentProject(scrum.client.project.Project currentProject) {
        List<scrum.client.admin.User> ret = new ArrayList<scrum.client.admin.User>();
        for (scrum.client.admin.User entity : users.values()) {
            if (entity.isCurrentProject(currentProject)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.admin.User> getUsersByColor(java.lang.String color) {
        List<scrum.client.admin.User> ret = new ArrayList<scrum.client.admin.User>();
        for (scrum.client.admin.User entity : users.values()) {
            if (entity.isColor(color)) ret.add(entity);
        }
        return ret;
    }

    // --- Wikipage ---

    private Map<String, scrum.client.collaboration.Wikipage> wikipages = new HashMap<String, scrum.client.collaboration.Wikipage>();

    public final void clearWikipages() {
        ilarkesto.core.logging.Log.DEBUG("Clearing Wikipages");
        wikipages.clear();
    }

    public final boolean containsWikipage(scrum.client.collaboration.Wikipage wikipage) {
        return wikipages.containsKey(wikipage.getId());
    }

    public final void deleteWikipage(scrum.client.collaboration.Wikipage wikipage) {
        wikipages.remove(wikipage.getId());
        entityDeleted(wikipage);
    }

    public final void createWikipage(scrum.client.collaboration.Wikipage wikipage) {
        wikipages.put(wikipage.getId(), wikipage);
        entityCreated(wikipage);
    }

    private final void updateWikipage(Map data) {
        String id = (String) data.get("id");
        scrum.client.collaboration.Wikipage entity = wikipages.get(id);
        if (entity == null) {
            entity = new scrum.client.collaboration.Wikipage(data);
            wikipages.put(id, entity);
            ilarkesto.core.logging.Log.DEBUG("Wikipage received: " + entity.getId() + " ("+entity+")");
        } else {
            entity.updateProperties(data);
            ilarkesto.core.logging.Log.DEBUG("Wikipage updated: " + entity);
        }
        onEntityModifiedRemotely(entity);
    }

    public final scrum.client.collaboration.Wikipage getWikipage(String id) {
        scrum.client.collaboration.Wikipage ret = wikipages.get(id);
        if (ret == null) throw new RuntimeException("Wikipage does not exist: " + id);
        return ret;
    }

    public final Set<scrum.client.collaboration.Wikipage> getWikipages(Collection<String> ids) {
        Set<scrum.client.collaboration.Wikipage> ret = new HashSet<scrum.client.collaboration.Wikipage>();
        for (String id : ids) {
            scrum.client.collaboration.Wikipage entity = wikipages.get(id);
            if (entity == null) throw new RuntimeException("Wikipage does not exist: " + id);
            ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.collaboration.Wikipage> getWikipages() {
        return new ArrayList<scrum.client.collaboration.Wikipage>(wikipages.values());
    }

    public final List<scrum.client.collaboration.Wikipage> getWikipagesByProject(scrum.client.project.Project project) {
        List<scrum.client.collaboration.Wikipage> ret = new ArrayList<scrum.client.collaboration.Wikipage>();
        for (scrum.client.collaboration.Wikipage entity : wikipages.values()) {
            if (entity.isProject(project)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.collaboration.Wikipage> getWikipagesByName(java.lang.String name) {
        List<scrum.client.collaboration.Wikipage> ret = new ArrayList<scrum.client.collaboration.Wikipage>();
        for (scrum.client.collaboration.Wikipage entity : wikipages.values()) {
            if (entity.isName(name)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.collaboration.Wikipage> getWikipagesByText(java.lang.String text) {
        List<scrum.client.collaboration.Wikipage> ret = new ArrayList<scrum.client.collaboration.Wikipage>();
        for (scrum.client.collaboration.Wikipage entity : wikipages.values()) {
            if (entity.isText(text)) ret.add(entity);
        }
        return ret;
    }

    public final void clearAllEntities() {
            clearChanges();
            clearChatMessages();
            clearComments();
            clearEmoticons();
            clearFiles();
            clearImpediments();
            clearIssues();
            clearProjects();
            clearProjectEvents();
            clearProjectUserConfigs();
            clearQualitys();
            clearReleases();
            clearRequirements();
            clearRequirementEstimationVotes();
            clearRisks();
            clearSimpleEvents();
            clearSprints();
            clearSubjects();
            clearTasks();
            clearUsers();
            clearWikipages();
    }

    private Collection<Map<String, ? extends AGwtEntity>> entityMaps;

    @Override
    protected final Collection<Map<String, ? extends AGwtEntity>> getEntityMaps() {
        if (entityMaps == null) {
            entityMaps = new ArrayList<Map<String, ? extends AGwtEntity>>();
            entityMaps.add(changes);
            entityMaps.add(chatMessages);
            entityMaps.add(comments);
            entityMaps.add(emoticons);
            entityMaps.add(files);
            entityMaps.add(impediments);
            entityMaps.add(issues);
            entityMaps.add(projects);
            entityMaps.add(projectEvents);
            entityMaps.add(projectUserConfigs);
            entityMaps.add(qualitys);
            entityMaps.add(releases);
            entityMaps.add(requirements);
            entityMaps.add(requirementEstimationVotes);
            entityMaps.add(risks);
            entityMaps.add(simpleEvents);
            entityMaps.add(sprints);
            entityMaps.add(subjects);
            entityMaps.add(tasks);
            entityMaps.add(users);
            entityMaps.add(wikipages);
        }
        return entityMaps;
    }

    @Override
    protected final void updateLocalEntity(String type, Map data) {
        if (type.equals(scrum.client.journal.Change.ENTITY_TYPE)) {
            updateChange(data);
            return;
        }
        if (type.equals(scrum.client.collaboration.ChatMessage.ENTITY_TYPE)) {
            updateChatMessage(data);
            return;
        }
        if (type.equals(scrum.client.collaboration.Comment.ENTITY_TYPE)) {
            updateComment(data);
            return;
        }
        if (type.equals(scrum.client.collaboration.Emoticon.ENTITY_TYPE)) {
            updateEmoticon(data);
            return;
        }
        if (type.equals(scrum.client.files.File.ENTITY_TYPE)) {
            updateFile(data);
            return;
        }
        if (type.equals(scrum.client.impediments.Impediment.ENTITY_TYPE)) {
            updateImpediment(data);
            return;
        }
        if (type.equals(scrum.client.issues.Issue.ENTITY_TYPE)) {
            updateIssue(data);
            return;
        }
        if (type.equals(scrum.client.project.Project.ENTITY_TYPE)) {
            updateProject(data);
            return;
        }
        if (type.equals(scrum.client.journal.ProjectEvent.ENTITY_TYPE)) {
            updateProjectEvent(data);
            return;
        }
        if (type.equals(scrum.client.admin.ProjectUserConfig.ENTITY_TYPE)) {
            updateProjectUserConfig(data);
            return;
        }
        if (type.equals(scrum.client.project.Quality.ENTITY_TYPE)) {
            updateQuality(data);
            return;
        }
        if (type.equals(scrum.client.release.Release.ENTITY_TYPE)) {
            updateRelease(data);
            return;
        }
        if (type.equals(scrum.client.project.Requirement.ENTITY_TYPE)) {
            updateRequirement(data);
            return;
        }
        if (type.equals(scrum.client.estimation.RequirementEstimationVote.ENTITY_TYPE)) {
            updateRequirementEstimationVote(data);
            return;
        }
        if (type.equals(scrum.client.risks.Risk.ENTITY_TYPE)) {
            updateRisk(data);
            return;
        }
        if (type.equals(scrum.client.calendar.SimpleEvent.ENTITY_TYPE)) {
            updateSimpleEvent(data);
            return;
        }
        if (type.equals(scrum.client.sprint.Sprint.ENTITY_TYPE)) {
            updateSprint(data);
            return;
        }
        if (type.equals(scrum.client.collaboration.Subject.ENTITY_TYPE)) {
            updateSubject(data);
            return;
        }
        if (type.equals(scrum.client.sprint.Task.ENTITY_TYPE)) {
            updateTask(data);
            return;
        }
        if (type.equals(scrum.client.admin.User.ENTITY_TYPE)) {
            updateUser(data);
            return;
        }
        if (type.equals(scrum.client.collaboration.Wikipage.ENTITY_TYPE)) {
            updateWikipage(data);
            return;
        }
       throw new RuntimeException("Unsupported type: " + type);
    }

}