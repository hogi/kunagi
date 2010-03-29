package scrum.mda;

import ilarkesto.base.time.Date;
import ilarkesto.base.time.DateAndTime;
import ilarkesto.base.time.Time;
import ilarkesto.di.app.ApplicationStarter;
import ilarkesto.mda.legacy.AGeneratorApplication;
import ilarkesto.mda.legacy.generator.GwtActionGenerator;
import ilarkesto.mda.legacy.generator.GwtActionTemplateGenerator;
import ilarkesto.mda.legacy.generator.GwtApplicationGenerator;
import ilarkesto.mda.legacy.generator.GwtComponentManagerGenerator;
import ilarkesto.mda.legacy.generator.GwtComponentTemplateGenerator;
import ilarkesto.mda.legacy.generator.GwtDaoGenerator;
import ilarkesto.mda.legacy.generator.GwtEntityGenerator;
import ilarkesto.mda.legacy.generator.GwtEntityTemplateGenerator;
import ilarkesto.mda.legacy.generator.GwtEventBusGenerator;
import ilarkesto.mda.legacy.generator.GwtEventListenerGenerator;
import ilarkesto.mda.legacy.generator.GwtImageBundleGenerator;
import ilarkesto.mda.legacy.generator.GwtServiceAsyncInterfaceGenerator;
import ilarkesto.mda.legacy.generator.GwtServiceImplementationGenerator;
import ilarkesto.mda.legacy.generator.GwtServiceInterfaceGenerator;
import ilarkesto.mda.legacy.model.ActionModel;
import ilarkesto.mda.legacy.model.ApplicationModel;
import ilarkesto.mda.legacy.model.BeanModel;
import ilarkesto.mda.legacy.model.ComponentModel;
import ilarkesto.mda.legacy.model.CompositeModel;
import ilarkesto.mda.legacy.model.DatobModel;
import ilarkesto.mda.legacy.model.EntityModel;
import ilarkesto.mda.legacy.model.EventModel;
import ilarkesto.mda.legacy.model.GwtEventBusModel;
import ilarkesto.mda.legacy.model.GwtServiceModel;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ScrumModelApplication extends AGeneratorApplication {

	public static void main(String[] args) {
		ApplicationStarter.startApplication(ScrumModelApplication.class).generateClasses().shutdown();
	}

	// ------------------
	// --- components ---
	// ------------------

	private ComponentModel gwtComponentsModel;

	public ComponentModel getGwtComponentsModel() {
		if (gwtComponentsModel == null) {
			gwtComponentsModel = new ComponentModel("GwtComponents", getBasePackageName());
			gwtComponentsModel.setGwt(true);
		}
		return gwtComponentsModel;
	}

	// --------------
	// --- events ---
	// --------------

	private GwtEventBusModel gwtEventBusModel;

	public GwtEventBusModel getGwtEventBusModel() {
		if (gwtEventBusModel == null) {
			gwtEventBusModel = new GwtEventBusModel();
			gwtEventBusModel.addEvent("ApplicationStart");
			gwtEventBusModel.addEvent("ServerDataReceived").addParameter("data", "DataTransferObject").setQuiet(true);
			gwtEventBusModel.addEvent("Login");
			gwtEventBusModel.addEvent("VisibleDataChanged");
			gwtEventBusModel.addEvent("BlockExpanded").addParameter("object", Object.class);
			gwtEventBusModel.addEvent("BlockCollapsed").addParameter("object", Object.class);
			gwtEventBusModel.addEvent("SearchResultsChanged");
			gwtEventBusModel.addEvent("FileUploaded").addParameter("file", "scrum.client.files.File");
		}
		return gwtEventBusModel;
	}

	// ---------------
	// --- service ---
	// ---------------

	private GwtServiceModel gwtServiceModel;

	public GwtServiceModel getGwtServiceModel() {
		if (gwtServiceModel == null) {
			gwtServiceModel = createGwtServiceModel("scrum");
			gwtServiceModel.addMethod("ping");
			gwtServiceModel.addMethod("login").addParameter("username", String.class).addParameter("password",
				String.class);
			gwtServiceModel.addMethod("logout");
			gwtServiceModel.addMethod("changePassword").addParameter("oldPassword", String.class).addParameter(
				"newPassword", String.class);
			gwtServiceModel.addMethod("resetPassword").addParameter("userId", String.class);
			gwtServiceModel.addMethod("selectProject").addParameter("projectId", String.class);
			gwtServiceModel.addMethod("closeProject");
			gwtServiceModel.addMethod("switchToNextSprint");
			gwtServiceModel.addMethod("requestImpediments");
			gwtServiceModel.addMethod("requestAcceptedIssues");
			gwtServiceModel.addMethod("requestClosedIssues");
			gwtServiceModel.addMethod("requestRisks");
			gwtServiceModel.addMethod("requestRequirementEstimationVotes").addParameter("requirementId", String.class);
			gwtServiceModel.addMethod("requestComments").addParameter("parentId", String.class);
			gwtServiceModel.addMethod("requestChanges").addParameter("parentId", String.class);
			gwtServiceModel.addMethod("changeProperties").addParameter("entityId", String.class).addParameter(
				"properties", Map.class);
			gwtServiceModel.addMethod("createEntity").addParameter("type", String.class).addParameter("properties",
				Map.class);
			gwtServiceModel.addMethod("deleteEntity").addParameter("entityId", String.class);
			gwtServiceModel.addMethod("requestEntityByReference").addParameter("reference", String.class);
			gwtServiceModel.addMethod("setSelectedEntitysIds").addParameter("ids", Set.class);
			gwtServiceModel.addMethod("sleep").addParameter("millis", long.class);
			gwtServiceModel.addMethod("updateSystemMessage").addParameter("systemMessage",
				"scrum.client.admin.SystemMessage");
			gwtServiceModel.addMethod("search").addParameter("text", String.class);
			gwtServiceModel.addMethod("activateRequirementEstimationVoting")
					.addParameter("requirementId", String.class);
			gwtServiceModel.addMethod("requestForum");

		}
		return gwtServiceModel;
	}

	// -------------------
	// --- application ---
	// -------------------

	private ApplicationModel applicationModel;

	public ApplicationModel getApplicationModel() {
		if (applicationModel == null) {
			applicationModel = createWebApplicationModel("Scrum");
			applicationModel.addDaosAsComposites(getFinalEntityModels(true));
			applicationModel.addGwtService(getGwtServiceModel());

			applicationModel.addAction("SwitchToNextSprint", getBasePackageName() + ".sprint");
			applicationModel.addAction("Login", getBasePackageName() + ".admin");
			applicationModel.addAction("Logout", getBasePackageName() + ".admin");
			applicationModel.addAction("ChangeProject", getBasePackageName() + ".project");
			applicationModel.addAction("RequestClosedIssues", getBasePackageName() + ".issues");
			applicationModel.addAction("ShowSuspendedIssues", getBasePackageName() + ".issues");
			applicationModel.addAction("HideSuspendedIssues", getBasePackageName() + ".issues");
		}
		return applicationModel;
	}

	// ----------------
	// --- entities ---
	// ----------------

	private EntityModel simpleEventModel;

	public EntityModel getSimpleEventModel() {
		if (simpleEventModel == null) {
			simpleEventModel = createEntityModel("SimpleEvent", "calendar");
			simpleEventModel.setGwtSupport(true);
			simpleEventModel.setViewProtected(true);
			simpleEventModel.addReference("project", getProjectModel()).setMaster(true);
			simpleEventModel.addStringProperty("label").setMandatory(true).setSearchable(true);
			simpleEventModel.addProperty("number", int.class).setMandatory(true);
			simpleEventModel.addProperty("date", Date.class);
			simpleEventModel.addProperty("time", Time.class);
			simpleEventModel.addStringProperty("location").setSearchable(true);
			simpleEventModel.addProperty("duration", Integer.class); // minutes
			simpleEventModel.addStringProperty("agenda").setRichtext(true).setSearchable(true);
			simpleEventModel.addStringProperty("note").setRichtext(true).setSearchable(true);
			getApplicationModel().addCreateAction(simpleEventModel);
			simpleEventModel.addAction("DeleteSimpleEvent");
			simpleEventModel.addAction("PublishSimpleEvent");
		}
		return simpleEventModel;
	}

	private EntityModel projectModel;

	public EntityModel getProjectModel() {
		if (projectModel == null) {
			projectModel = createEntityModel("Project", "project");
			projectModel.setViewProtected(true);
			projectModel.setDeleteProtected(true);
			projectModel.setGwtSupport(true);
			projectModel.addPredicate("editable");
			projectModel.addStringProperty("label").setMandatory(true).setSearchable(true);
			projectModel.addStringProperty("description").setRichtext(true).setSearchable(true);
			projectModel.addProperty("begin", Date.class);
			projectModel.addProperty("end", Date.class);
			projectModel.addSetReference("participants", getUserModel());
			projectModel.addSetReference("admins", getUserModel());
			projectModel.addSetReference("productOwners", getUserModel());
			projectModel.addSetReference("scrumMasters", getUserModel());
			projectModel.addSetReference("teamMembers", getUserModel());
			projectModel.addReference("currentSprint", getSprintModel());
			projectModel.addReference("nextSprint", getSprintModel());
			projectModel.addIntegerProperty("velocity");
			projectModel.addListProperty("requirementsOrderIds", String.class);
			projectModel.addListProperty("urgentIssuesOrderIds", String.class);
			projectModel.addProperty("lastTaskNumber", int.class);
			projectModel.addProperty("lastRequirementNumber", int.class);
			projectModel.addProperty("lastQualityNumber", int.class);
			projectModel.addProperty("lastRiskNumber", int.class);
			projectModel.addProperty("lastIssueNumber", int.class);
			projectModel.addProperty("lastImpedimentNumber", int.class);
			projectModel.addProperty("lastFileNumber", int.class);
			projectModel.addProperty("lastSubjectNumber", int.class);
			projectModel.addProperty("lastEventNumber", int.class);
			projectModel.addProperty("punishmentFactor", int.class);
			projectModel.addStringProperty("punishmentUnit");
			getApplicationModel().addCreateAction(projectModel);
			projectModel.addAction("DeleteProject");
			projectModel.addAction("OpenProject");
		}
		return projectModel;
	}

	private EntityModel fileModel;

	public EntityModel getFileModel() {
		if (fileModel == null) {
			fileModel = createEntityModel("File", "files");
			fileModel.setGwtSupport(true);
			fileModel.setViewProtected(true);
			fileModel.addReference("project", getProjectModel()).setMaster(true);
			fileModel.addStringProperty("filename").setEditablePredicate("false").setMandatory(true)
					.setSearchable(true);
			fileModel.addProperty("uploadTime", DateAndTime.class).setEditablePredicate("false").setMandatory(true);
			fileModel.addStringProperty("label").setMandatory(true).setSearchable(true);
			fileModel.addProperty("number", int.class).setMandatory(true);
			fileModel.addStringProperty("note").setRichtext(true).setSearchable(true);
			fileModel.addAction("DeleteFile");
		}
		return fileModel;
	}

	private EntityModel releaseModel;

	public EntityModel getReleaseModel() {
		if (releaseModel == null) {
			releaseModel = createEntityModel("Release", "release");
			releaseModel.setGwtSupport(true);
			releaseModel.setViewProtected(true);
			releaseModel.addReference("project", getProjectModel()).setMaster(true);
			releaseModel.addStringProperty("label").setMandatory(true).setSearchable(true);
			releaseModel.addProperty("publicationDate", Date.class);
		}
		return releaseModel;
	}

	private EntityModel projectSprintSnapshotModel;

	public EntityModel getProjectSprintSnapshotModel() {
		if (projectSprintSnapshotModel == null) {
			projectSprintSnapshotModel = createEntityModel("ProjectSprintSnapshot", "project");
			projectSprintSnapshotModel.addReference("sprint", getSprintModel()).setMaster(true).setUnique(true);
			projectSprintSnapshotModel.addProperty("remainingWork", int.class);
			projectSprintSnapshotModel.addProperty("burnedWork", int.class);
		}
		return projectSprintSnapshotModel;
	}

	private EntityModel requirementModel;

	public EntityModel getRequirementModel() {
		if (requirementModel == null) {
			requirementModel = createEntityModel("Requirement", "project");
			requirementModel.setGwtSupport(true);
			requirementModel.addPredicate("editable");
			requirementModel.addReference("project", getProjectModel()).setMaster(true);
			requirementModel.addReference("sprint", getSprintModel());
			requirementModel.addProperty("number", int.class);
			requirementModel.addSetReference("qualitys", getQualityModel());
			requirementModel.addStringProperty("label").setMandatory(true).setEditablePredicate("editable")
					.setSearchable(true);
			requirementModel.addStringProperty("description").setRichtext(true).setEditablePredicate("editable")
					.setSearchable(true);
			requirementModel.addStringProperty("testDescription").setRichtext(true).setEditablePredicate("editable")
					.setSearchable(true);
			requirementModel.addProperty("estimatedWork", Float.class);
			requirementModel.addProperty("rejectDate", Date.class);
			requirementModel.addProperty("closed", boolean.class);
			requirementModel.addProperty("dirty", boolean.class);
			requirementModel.addProperty("workEstimationVotingActive", boolean.class);
			requirementModel.addProperty("workEstimationVotingShowoff", boolean.class);
			getApplicationModel().addCreateAction(requirementModel);
			requirementModel.addAction("DeleteRequirement");
			requirementModel.addAction("AddRequirementToCurrentSprint");
			requirementModel.addAction("RemoveRequirementFromSprint");
			requirementModel.addAction("SetRequirementDirty");
			requirementModel.addAction("SetRequirementClean");
			requirementModel.addAction("CloseRequirement");
			requirementModel.addAction("RejectRequirement");
			requirementModel.addAction("FixRequirement");
			requirementModel.addAction("ReopenRequirement");
			requirementModel.addAction("StartRequirementEstimationVoting");
			requirementModel.addAction("CloseRequirementEstimationVoting");
			requirementModel.addAction("RequirementEstimationVotingShowoff");
			requirementModel.addAction("ResetRequirementEstimationVoting");
			requirementModel.addAction("RequirementEstimationVote").addParameter("estimatedWork", Float.class);
		}
		return requirementModel;
	}

	private EntityModel requirementEstimationVoteModel;

	public EntityModel getRequirementEstimationVoteModel() {
		if (requirementEstimationVoteModel == null) {
			requirementEstimationVoteModel = createEntityModel("RequirementEstimationVote", "estimation");
			requirementEstimationVoteModel.setGwtSupport(true);
			requirementEstimationVoteModel.addReference("requirement", getRequirementModel()).setMaster(true);
			requirementEstimationVoteModel.addReference("user", getUserModel()).setMaster(true);
			requirementEstimationVoteModel.addProperty("estimatedWork", Float.class);
		}
		return requirementEstimationVoteModel;
	}

	private EntityModel qualityModel;

	public EntityModel getQualityModel() {
		if (qualityModel == null) {
			qualityModel = createEntityModel("Quality", "project");
			qualityModel.setGwtSupport(true);
			qualityModel.addPredicate("editable");
			qualityModel.addReference("project", getProjectModel()).setMaster(true);
			qualityModel.addProperty("number", int.class);
			qualityModel.addStringProperty("label").setMandatory(true).setSearchable(true).setEditablePredicate(
				"editable");
			qualityModel.addStringProperty("description").setRichtext(true).setSearchable(true).setEditablePredicate(
				"editable");
			qualityModel.addStringProperty("testDescription").setRichtext(true).setSearchable(true)
					.setEditablePredicate("editable");
			getApplicationModel().addCreateAction(qualityModel);
			qualityModel.addAction("DeleteQuality");
		}
		return qualityModel;
	}

	private EntityModel sprintModel;

	public EntityModel getSprintModel() {
		if (sprintModel == null) {
			sprintModel = createEntityModel("Sprint", "sprint");
			sprintModel.setGwtSupport(true);
			sprintModel.addPredicate("editable");
			sprintModel.addPredicate("planningEditable");
			sprintModel.addPredicate("reviewEditable");
			sprintModel.addPredicate("retrospectiveEditable");
			sprintModel.addPredicate("datesEditable");
			sprintModel.addReference("project", getProjectModel()).setMaster(true);
			sprintModel.addStringProperty("label").setMandatory(true).setSearchable(true).setEditablePredicate(
				"editable");
			sprintModel.addStringProperty("goal").setRichtext(true).setTemplateAvailable(true).setEditablePredicate(
				"editable").setSearchable(true);
			sprintModel.addProperty("begin", Date.class).setEditablePredicate("datesEditable");
			sprintModel.addProperty("end", Date.class).setEditablePredicate("datesEditable");
			sprintModel.addProperty("velocity", Float.class);
			sprintModel.addStringProperty("completedRequirementLabels").setRichtext(true).setSearchable(true);
			sprintModel.addStringProperty("planningNote").setRichtext(true).setTemplateAvailable(true)
					.setEditablePredicate("planningEditable").setSearchable(true);
			sprintModel.addStringProperty("reviewNote").setRichtext(true).setTemplateAvailable(true)
					.setEditablePredicate("reviewEditable").setSearchable(true);
			sprintModel.addStringProperty("retrospectiveNote").setRichtext(true).setTemplateAvailable(true)
					.setEditablePredicate("retrospectiveEditable").setSearchable(true);
		}
		return sprintModel;
	}

	private EntityModel sprintDaySnapshotModel;

	public EntityModel getSprintDaySnapshotModel() {
		if (sprintDaySnapshotModel == null) {
			sprintDaySnapshotModel = createEntityModel("SprintDaySnapshot", "sprint");
			sprintDaySnapshotModel.addReference("sprint", getSprintModel());
			sprintDaySnapshotModel.addProperty("date", Date.class);
			sprintDaySnapshotModel.addProperty("remainingWork", int.class);
			sprintDaySnapshotModel.addProperty("burnedWork", int.class);
		}
		return sprintDaySnapshotModel;
	}

	private EntityModel taskModel;

	public EntityModel getTaskModel() {
		if (taskModel == null) {
			taskModel = createEntityModel("Task", "sprint");
			taskModel.setGwtSupport(true);
			taskModel.addPredicate("editable");
			taskModel.addReference("requirement", getRequirementModel()).setMaster(true);
			taskModel.addProperty("number", int.class);
			taskModel.addStringProperty("label").setMandatory(true).setEditablePredicate("editable")
					.setSearchable(true);
			taskModel.addStringProperty("description").setRichtext(true).setEditablePredicate("editable")
					.setSearchable(true);
			taskModel.addProperty("remainingWork", int.class).setEditablePredicate("editable");
			taskModel.addProperty("burnedWork", int.class).setEditablePredicate("editable");
			taskModel.addReference("owner", getUserModel()).setEditablePredicate("editable");
			getApplicationModel().addCreateAction(taskModel);
			taskModel.addAction("DeleteTask");
			taskModel.addAction("ClaimTask");
			taskModel.addAction("UnclaimTask");
			taskModel.addAction("CloseTask");
			taskModel.addAction("ReopenTask");
		}
		return taskModel;
	}

	private EntityModel impedimentModel;

	public EntityModel getImpedimentModel() {
		if (impedimentModel == null) {
			impedimentModel = createEntityModel("Impediment", "impediments");
			impedimentModel.setGwtSupport(true);
			impedimentModel.addReference("project", getProjectModel()).setMaster(true);
			impedimentModel.addProperty("number", int.class);
			impedimentModel.addStringProperty("label").setMandatory(true).setSearchable(true);
			impedimentModel.addProperty("date", Date.class).setMandatory(true);
			impedimentModel.addStringProperty("description").setRichtext(true).setSearchable(true);
			impedimentModel.addStringProperty("solution").setRichtext(true).setSearchable(true);
			impedimentModel.addProperty("closed", boolean.class);
			getApplicationModel().addCreateAction(impedimentModel);
			impedimentModel.addAction("DeleteImpediment");
			impedimentModel.addAction("CloseImpediment");
		}
		return impedimentModel;
	}

	private EntityModel riskModel;

	public EntityModel getRiskModel() {
		if (riskModel == null) {
			riskModel = createEntityModel("Risk", "risks");
			riskModel.setGwtSupport(true);
			riskModel.addReference("project", getProjectModel()).setMaster(true);
			riskModel.addPredicate("priorityEditable");
			riskModel.addProperty("number", int.class);
			riskModel.addStringProperty("label").setMandatory(true).setSearchable(true);
			riskModel.addStringProperty("description").setRichtext(true).setSearchable(true);
			riskModel.addStringProperty("probabilityMitigation").setRichtext(true).setSearchable(true);
			riskModel.addStringProperty("impactMitigation").setRichtext(true).setSearchable(true);
			riskModel.addProperty("probability", int.class).setOptionRestricted(true).setEditablePredicate(
				"priorityEditable");
			riskModel.addProperty("impact", int.class).setOptionRestricted(true).setEditablePredicate(
				"priorityEditable");
			getApplicationModel().addCreateAction(riskModel);
			riskModel.addAction("DeleteRisk");
		}
		return riskModel;
	}

	private EntityModel userModel;

	@Override
	public EntityModel getUserModel() {
		if (userModel == null) {
			userModel = createEntityModel("User", "admin");
			userModel.setGwtSupport(true);
			userModel.setSuperbean(super.getUserModel());
			userModel.addStringProperty("name").setMandatory(true).setSearchable(true);
			userModel.addProperty("admin", boolean.class);
			userModel.addStringProperty("email").setSearchable(true);
			userModel.addReference("currentProject", getProjectModel());
			userModel.addStringProperty("color");
			getApplicationModel().addCreateAction(userModel);
			userModel.addAction("DeleteUser");
		}
		return userModel;
	}

	private EntityModel projectUserConfigModel;

	public EntityModel getProjectUserConfigModel() {
		if (projectUserConfigModel == null) {
			projectUserConfigModel = createEntityModel("ProjectUserConfig", "admin");
			projectUserConfigModel.setGwtSupport(true);
			projectUserConfigModel.addPredicate("misconductsEditable");
			projectUserConfigModel.addReference("project", getProjectModel()).setMaster(true);
			projectUserConfigModel.addReference("user", getUserModel()).setMaster(true);
			projectUserConfigModel.addStringProperty("color");
			projectUserConfigModel.addProperty("misconducts", int.class).setEditablePredicate("misconductsEditable");
		}
		return projectUserConfigModel;
	}

	private EntityModel issueModel;

	public EntityModel getIssueModel() {
		if (issueModel == null) {
			issueModel = createEntityModel("Issue", "issues");
			issueModel.setGwtSupport(true);
			issueModel.addReference("project", getProjectModel()).setMaster(true);
			issueModel.addProperty("number", int.class);
			issueModel.addStringProperty("type").setOptionRestricted(true);
			issueModel.addProperty("date", DateAndTime.class).setMandatory(true);
			issueModel.addReference("creator", getUserModel());
			issueModel.addStringProperty("label").setMandatory(true).setSearchable(true);
			issueModel.addStringProperty("description").setRichtext(true).setSearchable(true);
			issueModel.addStringProperty("statement").setRichtext(true).setSearchable(true);
			issueModel.addProperty("acceptDate", Date.class);
			issueModel.addProperty("urgent", boolean.class);
			issueModel.addProperty("severity", int.class).setOptionRestricted(true);
			issueModel.addReference("owner", getUserModel());
			issueModel.addProperty("fixDate", Date.class);
			issueModel.addProperty("closeDate", Date.class);
			issueModel.addProperty("suspendedUntilDate", Date.class);
			getApplicationModel().addCreateAction(issueModel);
			issueModel.addAction("ClaimIssue");
			issueModel.addAction("FixIssue");
			issueModel.addAction("RejectFixIssue");
			issueModel.addAction("DeleteIssue");
			issueModel.addAction("CloseIssue");
			issueModel.addAction("AcceptIssueAsIdea");
			issueModel.addAction("AcceptIssueAsBug");
			issueModel.addAction("ReopenIssue");
			issueModel.addAction("ConvertIssueToRequirement");
			issueModel.addAction("ConvertIssueToQuality");
			issueModel.addAction("SuspendIssue");
			issueModel.addAction("UnsuspendIssue");
		}
		return issueModel;
	}

	private EntityModel chatMessageModel;

	public EntityModel getChatMessageModel() {
		if (chatMessageModel == null) {
			chatMessageModel = createEntityModel("ChatMessage", "collaboration");
			chatMessageModel.setGwtSupport(true);
			chatMessageModel.addReference("project", getProjectModel()).setMaster(true);
			chatMessageModel.addReference("author", getUserModel());
			chatMessageModel.addStringProperty("text").setRichtext(true).setMandatory(true);
			chatMessageModel.addProperty("dateAndTime", DateAndTime.class);
		}
		return chatMessageModel;
	}

	private EntityModel subjectModel;

	public EntityModel getSubjectModel() {
		if (subjectModel == null) {
			subjectModel = createEntityModel("Subject", "collaboration");
			subjectModel.setGwtSupport(true);
			subjectModel.addReference("project", getProjectModel()).setMaster(true);
			subjectModel.addStringProperty("label").setMandatory(true).setSearchable(true);
			subjectModel.addStringProperty("text").setRichtext(true).setSearchable(true);
			subjectModel.addProperty("number", int.class).setMandatory(true);
			getApplicationModel().addCreateAction(subjectModel);
			subjectModel.addAction("DeleteSubject");
		}
		return subjectModel;
	}

	private EntityModel emoticonModel;

	public EntityModel getEmoticonModel() {
		if (emoticonModel == null) {
			emoticonModel = createEntityModel("Emoticon", "collaboration");
			emoticonModel.setGwtSupport(true);
			emoticonModel.addReference("parent", getEntityModel()).setMaster(true);
			emoticonModel.addReference("owner", getUserModel());
			emoticonModel.addStringProperty("emotion");
		}
		return emoticonModel;
	}

	private EntityModel changeModel;

	public EntityModel getChangeModel() {
		if (changeModel == null) {
			changeModel = createEntityModel("Change", "journal");
			changeModel.setGwtSupport(true);
			changeModel.addReference("parent", getEntityModel()).setMaster(true);
			changeModel.addReference("user", getUserModel());
			changeModel.addProperty("dateAndTime", DateAndTime.class).setMandatory(true);
			changeModel.addStringProperty("key");
			changeModel.addStringProperty("oldValue");
			changeModel.addStringProperty("newValue");
			changeModel.addStringProperty("comment").setRichtext(true);
		}
		return changeModel;
	}

	private EntityModel commentModel;

	public EntityModel getCommentModel() {
		if (commentModel == null) {
			commentModel = createEntityModel("Comment", "collaboration");
			commentModel.setGwtSupport(true);
			commentModel.addReference("parent", getEntityModel()).setMaster(true);
			commentModel.addReference("author", getUserModel());
			commentModel.addStringProperty("text").setRichtext(true).setMandatory(true).setSearchable(true);
			commentModel.addProperty("dateAndTime", DateAndTime.class);
		}
		return commentModel;
	}

	private EntityModel wikipageModel;

	public EntityModel getWikipageModel() {
		if (wikipageModel == null) {
			wikipageModel = createEntityModel("Wikipage", "collaboration");
			wikipageModel.setGwtSupport(true);
			wikipageModel.addReference("project", getProjectModel()).setMaster(true);
			wikipageModel.addStringProperty("name").setMandatory(true).setSearchable(true);
			wikipageModel.addStringProperty("text").setRichtext(true).setSearchable(true);
			getApplicationModel().addCreateAction(wikipageModel);
			wikipageModel.addAction("DeleteWikipage");
		}
		return wikipageModel;
	}

	private EntityModel projectEventModel;

	public EntityModel getProjectEventModel() {
		if (projectEventModel == null) {
			projectEventModel = createEntityModel("ProjectEvent", "journal");
			projectEventModel.setGwtSupport(true);
			projectEventModel.addPredicate("editable");
			projectEventModel.addReference("project", getProjectModel()).setMaster(true);
			projectEventModel.addStringProperty("label").setMandatory(true).setSearchable(true).setEditablePredicate(
				"editable").setSearchable(true);
			projectEventModel.addProperty("dateAndTime", DateAndTime.class).setMandatory(true).setEditablePredicate(
				"editable");
		}
		return projectEventModel;
	}

	@Override
	protected String getBasePackageName() {
		return "scrum.server";
	}

	@Override
	protected EntityModel createEntityModel(String name, String packageName) {
		EntityModel model = super.createEntityModel(name, packageName);
		model.setViewProtected(true);
		// model.setEditProtected(true);
		return model;
	}

	@Override
	protected void onBeanGeneration(BeanModel beanModel) {
		super.onBeanGeneration(beanModel);
		if (beanModel instanceof DatobModel) {
			DatobModel datobModel = (DatobModel) beanModel;
			if (datobModel.isGwtSupport()) {
				new GwtEntityGenerator(datobModel, getApplicationModel()).generate();
				new GwtEntityTemplateGenerator(datobModel).generate();
			}
		}
		if (beanModel instanceof EntityModel) {
			EntityModel entityModel = (EntityModel) beanModel;
			generateActions(entityModel.getActions());
		}
	}

	@Override
	protected void onGeneration() {
		super.onGeneration();
		generateActions(getApplicationModel().getActions());
		new GwtServiceInterfaceGenerator(getGwtServiceModel()).generate();
		new GwtServiceAsyncInterfaceGenerator(getGwtServiceModel()).generate();
		new GwtServiceImplementationGenerator(getGwtServiceModel()).generate();
		new GwtApplicationGenerator(getApplicationModel()).generate();
		new GwtDaoGenerator(getApplicationModel(), getFinalEntityModels(false)).generate();
		new GwtImageBundleGenerator("scrum.client.img").generate();
		new GwtEventBusGenerator(getApplicationModel(), getGwtEventBusModel()).generate();
		for (EventModel eventModel : getGwtEventBusModel().getEvents()) {
			new GwtEventListenerGenerator(eventModel, applicationModel).generate();
		}
		new GwtComponentManagerGenerator(getGwtComponentsModel()).generate();
		for (CompositeModel composite : getGwtComponentsModel().getComposites()) {
			new GwtComponentTemplateGenerator(getApplicationModel(), composite).generate();
		}
	}

	private void generateActions(List<ActionModel> actions) {
		for (ActionModel action : actions) {
			new GwtActionGenerator(action).generate();
			new GwtActionTemplateGenerator(action).generate();
		}
	}
}
