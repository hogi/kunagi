package scrum.mda;

import ilarkesto.base.time.Date;
import ilarkesto.base.time.DateAndTime;
import ilarkesto.base.time.Time;
import ilarkesto.mda.legacy.AGeneratorApplication;
import ilarkesto.mda.legacy.generator.GwtActionGenerator;
import ilarkesto.mda.legacy.generator.GwtActionTemplateGenerator;
import ilarkesto.mda.legacy.generator.GwtApplicationGenerator;
import ilarkesto.mda.legacy.generator.GwtDaoGenerator;
import ilarkesto.mda.legacy.generator.GwtEntityGenerator;
import ilarkesto.mda.legacy.generator.GwtEntityTemplateGenerator;
import ilarkesto.mda.legacy.generator.GwtImageBundleGenerator;
import ilarkesto.mda.legacy.model.ActionModel;
import ilarkesto.mda.legacy.model.ApplicationModel;
import ilarkesto.mda.legacy.model.BeanModel;
import ilarkesto.mda.legacy.model.DatobModel;
import ilarkesto.mda.legacy.model.EntityModel;

import java.util.List;

public class ScrumModelApplication extends AGeneratorApplication {

	public static void main(String[] args) {
		ScrumModeller.main(args);
		// ApplicationStarter.startApplication(ScrumModelApplication.class).generateClasses().shutdown();
	}

	// -------------------
	// --- application ---
	// -------------------

	private ApplicationModel applicationModel;

	public ApplicationModel getApplicationModel() {
		if (applicationModel == null) {
			applicationModel = createWebApplicationModel("Scrum");
			applicationModel.addDaosAsComposites(getFinalEntityModels(true));

			applicationModel.addAction("SwitchToNextSprint", getBasePackageName() + ".sprint");
			applicationModel.addAction("Login", getBasePackageName() + ".admin");
			applicationModel.addAction("Logout", getBasePackageName() + ".admin");
			applicationModel.addAction("RequestNewPassword", getBasePackageName() + ".admin");
			applicationModel.addAction("SendTestEmail", getBasePackageName() + ".admin");
			applicationModel.addAction("ChangeProject", getBasePackageName() + ".project");
			applicationModel.addAction("CreateExampleProject", getBasePackageName() + ".project");
			applicationModel.addAction("RequestClosedIssues", getBasePackageName() + ".issues");
			applicationModel.addAction("ShowSuspendedIssues", getBasePackageName() + ".issues");
			applicationModel.addAction("HideSuspendedIssues", getBasePackageName() + ".issues");
		}
		return applicationModel;
	}

	// ----------------
	// --- entities ---
	// ----------------

	private EntityModel systemConfigModel;

	public EntityModel getSystemConfigModel() {
		if (systemConfigModel == null) {
			systemConfigModel = createEntityModel("SystemConfig", "admin");
			systemConfigModel.setGwtSupport(true);
			systemConfigModel.setViewProtected(true);
			systemConfigModel.setEditProtected(true);
			systemConfigModel.addStringProperty("url");
			systemConfigModel.addStringProperty("adminEmail");
			systemConfigModel.addStringProperty("googleAnalyticsId");
			systemConfigModel.addStringProperty("smtpServer");
			systemConfigModel.addProperty("smtpPort", Integer.class);
			systemConfigModel.addProperty("smtpTls", boolean.class);
			systemConfigModel.addStringProperty("smtpUser");
			systemConfigModel.addStringProperty("smtpPassword");
			systemConfigModel.addStringProperty("smtpFrom");
			systemConfigModel.addStringProperty("loginPageMessage").setRichtext(true);
			systemConfigModel.addStringProperty("registerPageMessage").setRichtext(true);
			systemConfigModel.addStringProperty("aboutPageMessage").setRichtext(true);
			systemConfigModel.addProperty("userEmailMandatory", boolean.class);
			systemConfigModel.addProperty("registrationDisabled", boolean.class);
			systemConfigModel.addStringProperty("defaultUserPassword");
			autowire(systemConfigModel);
		}
		return systemConfigModel;
	}

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
			projectModel
					.addStringProperty("label")
					.setMandatory(true)
					.setSearchable(true)
					.setUnique(true)
					.setTooltip(
						"This is the project name that should be chosen for humans to clearly identify the project.");
			projectModel
					.addStringProperty("vision")
					.setRichtext(true)
					.setSearchable(true)
					.setTooltip(
						"This is a vision that should state the purpose and aim of the project. "
								+ "It should focus be used to focus the participant's work on a common goal "
								+ "that is simple, measurable, achievable, relevant, and time-bound.");
			projectModel
					.addStringProperty("shortDescription")
					.setSearchable(true)
					.setTooltip(
						"This is a project description in a sentence. It can, for example, be used in "
								+ "the homepage metatag or inserted descriptions, where space is limited to one line.");
			projectModel
					.addStringProperty("description")
					.setRichtext(true)
					.setSearchable(true)
					.setTooltip(
						"This is a product description in a paragraph. It is can be used to give a short introduction "
								+ "about the product, summing up all essential features.");
			projectModel.addStringProperty("longDescription").setRichtext(true).setSearchable(true)
					.setTooltip("This is a full lenth description that takes as much space as it needs.");
			projectModel.addProperty("begin", Date.class);
			projectModel.addProperty("end", Date.class);
			projectModel.addSetReference("participants", getUserModel()).setTooltip(
				"The project's participants are all stakeholders. "
						+ "Speaking in Scrum terms, they are both pigs and chickens "
						+ "that are allowed to log into the project and browse all information available.");
			projectModel.addSetReference("admins", getUserModel()).setTooltip(
				"The admins can change the project properties and are of no direct relevance to the Scrum idea. "
						+ "An admin is the person to turn to when administrating Kunagi program data.");
			projectModel.addSetReference("productOwners", getUserModel()).setTooltip(
				"The Product Owner is the person in charge of defining and priotizing the product requirements "
						+ "by managing the Product Backlog. He represents the customer to the Team. "
						+ "Ideally, the Product Owner should not be a Team Member.");
			projectModel.addSetReference("scrumMasters", getUserModel()).setTooltip(
				"The Scrum Master ensures cooperation between all Scrum roles, "
						+ "shields the Team agains adverse influences and removies impediments.");
			projectModel.addSetReference("teamMembers", getUserModel()).setTooltip(
				"The Team ideally consists of around 7 members and is self-organized and cross-functional.");
			projectModel.addReference("currentSprint", getSprintModel());
			projectModel.addReference("nextSprint", getSprintModel());
			projectModel.addIntegerProperty("velocity");
			projectModel.addListProperty("requirementsOrderIds", String.class);
			projectModel.addListProperty("urgentIssuesOrderIds", String.class);
			projectModel.addProperty("lastSprintNumber", int.class);
			projectModel.addProperty("lastTaskNumber", int.class);
			projectModel.addProperty("lastRequirementNumber", int.class);
			projectModel.addProperty("lastQualityNumber", int.class);
			projectModel.addProperty("lastRiskNumber", int.class);
			projectModel.addProperty("lastIssueNumber", int.class);
			projectModel.addProperty("lastImpedimentNumber", int.class);
			projectModel.addProperty("lastFileNumber", int.class);
			projectModel.addProperty("lastSubjectNumber", int.class);
			projectModel.addProperty("lastEventNumber", int.class);
			projectModel.addProperty("lastReleaseNumber", int.class);
			projectModel.addProperty("lastBlogEntryNumber", int.class);
			projectModel.addProperty("punishmentFactor", int.class);
			projectModel.addStringProperty("punishmentUnit");
			projectModel.addStringProperty("homepageDir");
			projectModel.addProperty("autoUpdateHomepage", boolean.class);
			projectModel.addProperty("lastOpenedDateAndTime", DateAndTime.class);
			getApplicationModel().addCreateAction(projectModel);
			projectModel.addAction("DeleteProject");
			projectModel.addAction("OpenProject");
			projectModel.addAction("UpdateProjectHomepage");
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
			fileModel
					.addStringProperty("label")
					.setMandatory(true)
					.setSearchable(true)
					.setTooltip(
						"The label is used to provide a human readable name for the uploaded file. "
								+ "It may (and probably should) differ from the filename.");
			fileModel.addProperty("number", int.class).setMandatory(true);
			fileModel
					.addStringProperty("note")
					.setRichtext(true)
					.setSearchable(true)
					.setTooltip(
						"Use this to give additional information on this uploaded file useful for other project members.");
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
			releaseModel.addReference("parentRelease", getReleaseModel());
			releaseModel.addSetReference("sprints", getSprintModel()).setTooltip(
				"Releases in Scrum usually only make sense after the completion of Sprints. "
						+ "Therefore, one (public) release contains the results of a number of Sprints. "
						+ "This is the list of Sprints that were undertaken to build this release.");
			releaseModel.addProperty("number", int.class).setMandatory(true);
			releaseModel
					.addStringProperty("label")
					.setMandatory(true)
					.setSearchable(true)
					.setTooltip(
						"This is the codename of the release, used to refer to it by project members and users.");
			releaseModel
					.addStringProperty("note")
					.setRichtext(true)
					.setSearchable(true)
					.setTooltip(
						"The development notes contain information useful for "
								+ "project participants and stakeholders while working on the release.");
			releaseModel.addProperty("releaseDate", Date.class).setTooltip(
				"The intended or actual release date for planned and finished releases, respectively.");
			releaseModel.addProperty("released", boolean.class);
			releaseModel
					.addStringProperty("releaseNotes")
					.setRichtext(true)
					.setSearchable(true)
					.setTooltip(
						"The release notes contain a description used to articulate the changes "
								+ "that this release contains to the public. "
								+ "It should be done in human readable format and an informative manner.");
			releaseModel.addStringProperty("scmTag").setSearchable(true)
					.setTooltip("The tag used in content management systems for this release.");
			getApplicationModel().addCreateAction(releaseModel);
			releaseModel.addAction("DeleteRelease");
			releaseModel.addAction("ReleaseRelease");
			releaseModel.addAction("UnreleaseRelease");
			releaseModel.addAction("CreateBugfixRelease");
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
			requirementModel.addReference("issue", getIssueModel());
			requirementModel.addProperty("number", int.class);
			requirementModel.addSetReference("qualitys", getQualityModel()).setTooltip(
				"Qualities are non-functional or cross-story requirements. "
						+ "Qualities linked to Stories indicate that not only the Story's requirements, "
						+ "but also the Quality's requirements need to be met in order to complete a Story.");
			requirementModel.addStringProperty("label").setMandatory(true).setEditablePredicate("editable")
					.setSearchable(true).setTooltip(createLabelTooltipText("Story"));
			requirementModel
					.addStringProperty("description")
					.setRichtext(true)
					.setEditablePredicate("editable")
					.setSearchable(true)
					.setTooltip(
						"The description of a Story should make what the label cannot: "
								+ "It should provide information on what is and what is not part of it. "
								+ "Ideally, it is given in terms of a user story: "
								+ "'As a [user] I want [function] so that [value].'");
			requirementModel
					.addStringProperty("testDescription")
					.setRichtext(true)
					.setEditablePredicate("editable")
					.setSearchable(true)
					.setTooltip(
						"The Test contains requirements that have to be met by the Team "
								+ "in order for the Story to be considered done.");
			requirementModel.addProperty("estimatedWork", Float.class).setTooltip(
				"The estimated work gives a relative estimation of effort that needs to be put into the Story to complete it. "
						+ "The bigger the Story the less important the accuracy of the estimation. "
						+ "Big Stories (Epics) close to being worked on should be split to be smaller.");
			requirementModel.addProperty("rejectDate", Date.class);
			requirementModel.addProperty("closed", boolean.class);
			requirementModel.addProperty("dirty", boolean.class);
			requirementModel.addProperty("workEstimationVotingActive", boolean.class);
			requirementModel.addProperty("workEstimationVotingShowoff", boolean.class);
			requirementModel.addListProperty("tasksOrderIds", String.class);
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
			qualityModel.addStringProperty("label").setMandatory(true).setSearchable(true)
					.setEditablePredicate("editable").setTooltip(createLabelTooltipText("Quality"));
			qualityModel
					.addStringProperty("description")
					.setRichtext(true)
					.setSearchable(true)
					.setEditablePredicate("editable")
					.setTooltip(
						"The desctiption of a Quality should give detailled information on "
								+ "what the Quality is about and how it affects Storys that reference it.");
			qualityModel
					.addStringProperty("testDescription")
					.setRichtext(true)
					.setSearchable(true)
					.setEditablePredicate("editable")
					.setTooltip(
						"The Test contains requirements that have to be met by the Team "
								+ "in order for a Story that references this Quality to be considered done.");
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
			sprintModel.addProperty("number", int.class);
			sprintModel.addReference("project", getProjectModel()).setMaster(true);
			sprintModel.addStringProperty("label").setMandatory(true).setSearchable(true)
					.setEditablePredicate("editable").setTooltip(createLabelTooltipText("Sprint"));
			sprintModel
					.addStringProperty("goal")
					.setRichtext(true)
					.setTemplateAvailable(true)
					.setEditablePredicate("editable")
					.setSearchable(true)
					.setTooltip(
						"The goal is used to summarize the purpose of this Sprint. "
								+ "Naturally, it should be a description on why it is important that the Stories "
								+ "currently high in the Product Backlog should be realized next.");
			sprintModel.addProperty("begin", Date.class).setEditablePredicate("datesEditable")
					.setTooltip("The date the Team starts working on the Sprint.");
			sprintModel
					.addProperty("end", Date.class)
					.setEditablePredicate("datesEditable")
					.setTooltip(
						"The date by which the Team will finish working on this Sprint. "
								+ "A Sprint Review meeting should be scheduled to present results.");
			sprintModel.addProperty("velocity", Float.class);
			sprintModel.addStringProperty("completedRequirementLabels").setRichtext(true).setSearchable(true);
			sprintModel
					.addStringProperty("planningNote")
					.setRichtext(true)
					.setTemplateAvailable(true)
					.setEditablePredicate("planningEditable")
					.setSearchable(true)
					.setTooltip(
						"Things that come up during Sprint Planning that might affect the Sprint or be of interest for stakeholders "
								+ "(illness, vacation and other influences; discussion results, agreements, etc.");
			sprintModel
					.addStringProperty("reviewNote")
					.setRichtext(true)
					.setTemplateAvailable(true)
					.setEditablePredicate("reviewEditable")
					.setSearchable(true)
					.setTooltip(
						"Things that come up during the Sprint Review that might be of interest for stakeholders.");
			sprintModel
					.addStringProperty("retrospectiveNote")
					.setRichtext(true)
					.setTemplateAvailable(true)
					.setEditablePredicate("retrospectiveEditable")
					.setSearchable(true)
					.setTooltip(
						"Things that come up during Sprint Retrospectives that are important in the future "
								+ "or might be of interest for stakeholders.");
			sprintModel.addSetReference("productOwners", getUserModel());
			sprintModel.addSetReference("scrumMasters", getUserModel());
			sprintModel.addSetReference("teamMembers", getUserModel());
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
					.setSearchable(true).setTooltip(createLabelTooltipText("Task"));
			taskModel
					.addStringProperty("description")
					.setRichtext(true)
					.setEditablePredicate("editable")
					.setSearchable(true)
					.setTooltip(
						"The description of a Task may be used to give information that is important, "
								+ "but cannot be inferred from the Label. "
								+ "As Tasks are small units of work, the Label might be sufficient.");
			taskModel
					.addProperty("remainingWork", int.class)
					.setEditablePredicate("editable")
					.setTooltip(
						"The remaining time needed to get this Task done. If the remaining time is high, "
								+ "it might be an indication (but is not necessarily the case) that "
								+ "splitting the Task is a good idea.");
			taskModel.addProperty("burnedWork", int.class).setEditablePredicate("editable")
					.setTooltip("Time already invested working on this Task.");
			taskModel.addReference("owner", getUserModel()).setEditablePredicate("editable")
					.setTooltip("The Team member working on the Task.");
			taskModel.addReference("impediment", getImpedimentModel()).setEditablePredicate("editable")
					.setTooltip("Blocked by Impediment.");
			getApplicationModel().addCreateAction(taskModel);
			taskModel.addAction("DeleteTask");
			taskModel.addAction("ClaimTask");
			taskModel.addAction("UnclaimTask");
			taskModel.addAction("CloseTask");
			taskModel.addAction("ReopenTask");
			taskModel.addAction("CreateTaskImpediment");
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
			impedimentModel.addStringProperty("label").setMandatory(true).setSearchable(true)
					.setTooltip(createLabelTooltipText("Impediment"));
			impedimentModel.addProperty("date", Date.class).setMandatory(true)
					.setTooltip("The date the Impediment came up.");
			impedimentModel
					.addStringProperty("description")
					.setRichtext(true)
					.setSearchable(true)
					.setTooltip(
						"The detailed description of the Impediment that explains problems, "
								+ "states who is affected and how, gives background information and solution hints.");
			impedimentModel
					.addStringProperty("solution")
					.setRichtext(true)
					.setSearchable(true)
					.setTooltip(
						"As soon as a solution is found, it can be documented here, so that people affected by "
								+ "or holding stake in the Impediment can read it up.");
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
			riskModel.addStringProperty("label").setMandatory(true).setSearchable(true)
					.setTooltip(createLabelTooltipText("Risk"));
			riskModel
					.addStringProperty("description")
					.setRichtext(true)
					.setSearchable(true)
					.setTooltip(
						"The description of a Risk should give additional information like "
								+ "why or when it might occur, what would be affected and how; "
								+ "reasons, preconditions and damage that might result.");
			riskModel
					.addStringProperty("probabilityMitigation")
					.setRichtext(true)
					.setSearchable(true)
					.setTooltip(
						"The mitigation plans that should be put into practice in order to "
								+ "mitigate the probability of a problem arising.");
			riskModel
					.addStringProperty("impactMitigation")
					.setRichtext(true)
					.setSearchable(true)
					.setTooltip(
						"The mitigation plans that should be put into practice in order to "
								+ "mitigate the impact, should a problem arise.");
			riskModel
					.addProperty("probability", int.class)
					.setOptionRestricted(true)
					.setEditablePredicate("priorityEditable")
					.setTooltip(
						"How probable is it (concidering the description and implemented minigation plans) "
								+ "that this Risk turns into a problem?");
			riskModel
					.addProperty("impact", int.class)
					.setOptionRestricted(true)
					.setEditablePredicate("priorityEditable")
					.setTooltip(
						"How high is the impact (concidering the decription and the implemented mitigation plans), "
								+ "should a problem arise?");
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
			userModel.setViewProtected(true);
			userModel.setEditProtected(true);
			userModel.addStringProperty("name").setMandatory(true).setSearchable(true).setUnique(true);
			userModel.addProperty("admin", boolean.class);
			userModel.addProperty("emailVerified", boolean.class);
			userModel.addStringProperty("email").setSearchable(true).setUnique(true);
			userModel.addReference("currentProject", getProjectModel());
			userModel.addStringProperty("color");
			userModel.addProperty("lastLoginDateAndTime", DateAndTime.class);
			userModel.addProperty("registrationDateAndTime", DateAndTime.class);
			userModel.addProperty("disabled", boolean.class);
			userModel.addProperty("hideUserGuideBlog", boolean.class);
			userModel.addProperty("hideUserGuideCalendar", boolean.class);
			userModel.addProperty("hideUserGuideFiles", boolean.class);
			userModel.addProperty("hideUserGuideForum", boolean.class);
			userModel.addProperty("hideUserGuideImpediments", boolean.class);
			userModel.addProperty("hideUserGuideIssues", boolean.class);
			userModel.addProperty("hideUserGuideJournal", boolean.class);
			userModel.addProperty("hideUserGuideNextSprint", boolean.class);
			userModel.addProperty("hideUserGuideProductBacklog", boolean.class);
			userModel.addProperty("hideUserGuideCourtroom", boolean.class);
			userModel.addProperty("hideUserGuideQualityBacklog", boolean.class);
			userModel.addProperty("hideUserGuideReleases", boolean.class);
			userModel.addProperty("hideUserGuideRisks", boolean.class);
			userModel.addProperty("hideUserGuideSprintBacklog", boolean.class);
			userModel.addProperty("hideUserGuideWhiteboard", boolean.class);
			userModel.addStringProperty("loginToken").setUnique(true);
			getApplicationModel().addCreateAction(userModel);
			userModel.addAction("DeleteUser");
			userModel.addAction("DisableUser");
			userModel.addAction("EnableUser");
			userModel.addAction("ConfirmUserEmail");
			userModel.addAction("ResetUserPassword");
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
			issueModel.addReference("story", getRequirementModel());
			issueModel.addProperty("number", int.class);
			issueModel.addStringProperty("type").setOptionRestricted(true);
			issueModel.addProperty("date", DateAndTime.class).setMandatory(true);
			issueModel.addReference("creator", getUserModel()).setTooltip("User, who created this issue.");
			issueModel.addStringProperty("label").setMandatory(true).setSearchable(true)
					.setTooltip(createLabelTooltipText("Issue"));
			issueModel
					.addStringProperty("description")
					.setRichtext(true)
					.setSearchable(true)
					.setTooltip(
						"The description of an issue should give enough information for other people to understand "
								+ "what the issue is about. That contains information on how to reproduce an issue and "
								+ "what symptoms are, as well as suggestions on how to fix it.");
			issueModel
					.addStringProperty("statement")
					.setRichtext(true)
					.setSearchable(true)
					.setTooltip(
						"Official statement from the Scrum Team to the public about this issue. This could be a "
								+ " workaround description, the reason or some other information about status of the "
								+ " issue.");
			issueModel.addStringProperty("issuerName").setTooltip("Name of the person that created this issue.")
					.setTooltip("The person who filed this issue.");
			issueModel.addStringProperty("issuerEmail").setTooltip(
				"E-Mail address of the person, who filed this issue.");
			issueModel.addProperty("acceptDate", Date.class);
			issueModel.addProperty("urgent", boolean.class);
			issueModel
					.addProperty("severity", int.class)
					.setOptionRestricted(true)
					.setTooltip(
						"The level of this bug's impact. A minor bug might be a cosmetic failure, "
								+ "a normal bug encumbers the user's work, "
								+ "a severe bug might lead to loss of data or property, "
								+ "a critical bug makes product usage impossible.");
			issueModel.addReference("owner", getUserModel()).setTooltip(
				"The Team member that is currently working on this issue.");
			issueModel.addProperty("fixDate", Date.class);
			issueModel.addProperty("closeDate", Date.class);
			issueModel.addProperty("suspendedUntilDate", Date.class);
			issueModel.addSetReference("affectedReleases", getReleaseModel());
			issueModel.addSetReference("fixReleases", getReleaseModel());
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
			subjectModel.addStringProperty("label").setMandatory(true).setSearchable(true)
					.setTooltip("The subject this discussion will be listed under in the project's forum.");
			subjectModel.addStringProperty("text").setRichtext(true).setSearchable(true)
					.setTooltip("Notes that give background information and summarize discussion results.");
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
			projectEventModel.addStringProperty("label").setMandatory(true).setSearchable(true)
					.setEditablePredicate("editable").setSearchable(true);
			projectEventModel.addProperty("dateAndTime", DateAndTime.class).setMandatory(true)
					.setEditablePredicate("editable");
		}
		return projectEventModel;
	}

	private EntityModel blogEntryModel;

	public EntityModel getBlogEntryModel() {
		if (blogEntryModel == null) {
			blogEntryModel = createEntityModel("BlogEntry", "pr");
			blogEntryModel.setGwtSupport(true);
			blogEntryModel.addReference("project", getProjectModel()).setMaster(true);
			blogEntryModel.addProperty("number", int.class).setMandatory(true);
			blogEntryModel.addSetReference("authors", getUserModel()).setTooltip(
				"All people that have cantributed to create this blog entry.");
			blogEntryModel.addStringProperty("title").setMandatory(true).setSearchable(true)
					.setTooltip("The title that will appear in the blog.");
			blogEntryModel.addStringProperty("text").setRichtext(true).setSearchable(true)
					.setTooltip("The text that will appear in the blog.");
			blogEntryModel.addProperty("dateAndTime", DateAndTime.class).setTooltip(
				"The time that indicates when the blog entry was released.");
			blogEntryModel.addSetReference("releases", getReleaseModel());
			blogEntryModel.addProperty("published", boolean.class);
			getApplicationModel().addCreateAction(blogEntryModel);
			blogEntryModel.addAction("DeleteBlogEntry");
			blogEntryModel.addAction("PublishBlogEntry");
			blogEntryModel.addAction("UnpublishBlogEntry");
		}
		return blogEntryModel;
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
		new GwtApplicationGenerator(getApplicationModel()).generate();
		new GwtDaoGenerator(getApplicationModel(), getFinalEntityModels(false)).generate();
		new GwtImageBundleGenerator("scrum.client.img").generate();
	}

	private void generateActions(List<ActionModel> actions) {
		for (ActionModel action : actions) {
			new GwtActionGenerator(action).generate();
			new GwtActionTemplateGenerator(action).generate();
		}
	}

	private String createLabelTooltipText(String entityName) {
		return "The label should be short (as it appears where the " + entityName + " is referenced), "
				+ "yet give a hint strong enough to make the content of it come to mind.";
	}
}
