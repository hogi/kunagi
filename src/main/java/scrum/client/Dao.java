package scrum.client;

import ilarkesto.core.scope.Scope;
import ilarkesto.gwt.client.ADataTransferObject;
import ilarkesto.gwt.client.AGwtEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import scrum.client.admin.SystemConfig;
import scrum.client.calendar.SimpleEvent;
import scrum.client.collaboration.Chat;
import scrum.client.collaboration.ChatMessage;
import scrum.client.collaboration.Subject;
import scrum.client.collaboration.Wikipage;
import scrum.client.common.AScrumGwtEntity;
import scrum.client.core.ChangePropertiesServiceCall;
import scrum.client.core.CreateEntityServiceCall;
import scrum.client.core.DeleteEntityServiceCall;
import scrum.client.core.RequestEntityByReferenceServiceCall;
import scrum.client.files.File;
import scrum.client.files.FileUploadedEvent;
import scrum.client.impediments.Impediment;
import scrum.client.issues.Issue;
import scrum.client.pr.BlogEntry;
import scrum.client.project.Quality;
import scrum.client.project.Requirement;
import scrum.client.release.Release;
import scrum.client.risks.Risk;
import scrum.client.sprint.Task;
import scrum.client.workspace.VisibleDataChangedEvent;

import com.google.gwt.user.client.Timer;

public class Dao extends GDao {

	private static final Dao INSTANCE = new Dao();

	private EntityChangeCache cache = new EntityChangeCache();
	private ScrumGwtApplication app;

	private Dao() {}

	public void setApp(ScrumGwtApplication app) {
		this.app = app;
	}

	public void clearProjectEntities() {
		clearChatMessages();
		clearImpediments();
		clearQualitys();
		clearRequirements();
		clearRisks();
		clearSprints();
		clearTasks();
		clearComments();
		clearWikipages();
		clearIssues();
		clearSimpleEvents();
		clearProjectUserConfigs();
		clearProjectEvents();
	}

	public SystemConfig getSystemConfig() {
		List<SystemConfig> configs = getSystemConfigs();
		return configs.size() > 0 ? configs.get(0) : null;
	}

	public void requestEntityByReference(String reference) {
		new RequestEntityByReferenceServiceCall(reference).execute();
	}

	public AScrumGwtEntity getEntityByReference(String reference) {

		if (reference.length() > 4 && reference.startsWith("[[")) {
			String pageName = reference.substring(2, reference.length() - 2);
			for (Wikipage e : getWikipages()) {
				if (e.isName(pageName)) return e;
			}
			return null;
		}

		int number = Integer.parseInt(reference.substring(Requirement.REFERENCE_PREFIX.length()));
		if (reference.startsWith(Requirement.REFERENCE_PREFIX)) {
			for (Requirement e : getRequirements()) {
				if (e.isNumber(number)) return e;
			}
			return null;
		} else if (reference.startsWith(Task.REFERENCE_PREFIX)) {
			for (Task e : getTasks()) {
				if (e.isNumber(number)) return e;
			}
			return null;
		} else if (reference.startsWith(Quality.REFERENCE_PREFIX)) {
			for (Quality e : getQualitys()) {
				if (e.isNumber(number)) return e;
			}
			return null;
		} else if (reference.startsWith(Issue.REFERENCE_PREFIX)) {
			for (Issue e : getIssues()) {
				if (e.isNumber(number)) return e;
			}
			return null;
		} else if (reference.startsWith(Risk.REFERENCE_PREFIX)) {
			for (Risk e : getRisks()) {
				if (e.isNumber(number)) return e;
			}
			return null;
		} else if (reference.startsWith(Impediment.REFERENCE_PREFIX)) {
			for (Impediment e : getImpediments()) {
				if (e.isNumber(number)) return e;
			}
			return null;
		} else if (reference.startsWith(File.REFERENCE_PREFIX)) {
			for (File e : getFiles()) {
				if (e.isNumber(number)) return e;
			}
			return null;
		} else if (reference.startsWith(Subject.REFERENCE_PREFIX)) {
			for (Subject e : getSubjects()) {
				if (e.isNumber(number)) return e;
			}
			return null;
		} else if (reference.startsWith(SimpleEvent.REFERENCE_PREFIX)) {
			for (SimpleEvent e : getSimpleEvents()) {
				if (e.isNumber(number)) return e;
			}
			return null;
		} else if (reference.startsWith(Release.REFERENCE_PREFIX)) {
			for (Release e : getReleases()) {
				if (e.isNumber(number)) return e;
			}
			return null;
		} else if (reference.startsWith(BlogEntry.REFERENCE_PREFIX)) {
			for (BlogEntry e : getBlogEntrys()) {
				if (e.isNumber(number)) return e;
			}
			return null;
		} else {
			throw new RuntimeException("Unsupported entity reference: " + reference);
		}
	}

	@Override
	public void handleDataFromServer(ADataTransferObject data) {
		super.handleDataFromServer(data);
		if (data.containsEntities() || data.containsDeletedEntities()) {
			new VisibleDataChangedEvent().fireInCurrentScope();
		}
	}

	// --- remote events (incoming from server) ---

	@Override
	protected void onEntityModifiedRemotely(AGwtEntity entity) {
		if (entity instanceof ChatMessage) {
			Scope.get().getComponent(Chat.class).addChatMessage((ChatMessage) entity);
		}
		if (entity instanceof File) {
			new FileUploadedEvent((File) entity).fireInCurrentScope();
		}
	}

	@Override
	protected void onEntityDeletedRemotely(AGwtEntity entity) {}

	// --- local events ---

	@Override
	protected void onEntityCreatedLocaly(AGwtEntity entity, Runnable successAction) {
		new CreateEntityServiceCall(entity.getEntityType(), entity.createPropertiesMap()).execute(successAction);
	}

	@Override
	protected void onEntityDeletedLocaly(AGwtEntity entity) {
		new DeleteEntityServiceCall(entity.getId()).execute();
	}

	@Override
	protected void onEntityPropertyChangedLocaly(AGwtEntity entity, String property, Object value) {
		cache.put(entity.getId(), property, value);
	}

	// --- ---

	public static Dao get() {
		return INSTANCE;
	}

	private class EntityChangeCache extends Timer {

		private Map<String, Map> entityProperties = new HashMap<String, Map>(3);

		public void put(String entityId, String property, Object value) {
			Map entity = entityProperties.get(entityId);
			if (entity == null) {
				entity = new HashMap();
				entityProperties.put(entityId, entity);
			}
			entity.put(property, value);
			schedule(1000);
		}

		private void flush() {
			for (Map.Entry<String, Map> entry : entityProperties.entrySet()) {
				new ChangePropertiesServiceCall(entry.getKey(), entry.getValue()).execute();
			}
			entityProperties = new HashMap<String, Map>(3);
		}

		@Override
		public void run() {
			flush();
		}
	}
}
