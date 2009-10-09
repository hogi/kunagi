package scrum.mda;

import ilarkesto.base.time.Date;
import ilarkesto.base.time.DateAndTime;
import ilarkesto.di.app.ApplicationStarter;
import ilarkesto.mda.AGeneratorApplication;
import ilarkesto.mda.gen.gwt.GwtActionGenerator;
import ilarkesto.mda.gen.gwt.GwtActionTemplateGenerator;
import ilarkesto.mda.gen.gwt.GwtApplicationGenerator;
import ilarkesto.mda.gen.gwt.GwtComponentTemplateGenerator;
import ilarkesto.mda.gen.gwt.GwtComponentManagerGenerator;
import ilarkesto.mda.gen.gwt.GwtDaoGenerator;
import ilarkesto.mda.gen.gwt.GwtEntityGenerator;
import ilarkesto.mda.gen.gwt.GwtEntityTemplateGenerator;
import ilarkesto.mda.gen.gwt.GwtEventBusGenerator;
import ilarkesto.mda.gen.gwt.GwtEventListenerGenerator;
import ilarkesto.mda.gen.gwt.GwtImageBundleGenerator;
import ilarkesto.mda.gen.gwt.GwtServiceAsyncInterfaceGenerator;
import ilarkesto.mda.gen.gwt.GwtServiceImplementationGenerator;
import ilarkesto.mda.gen.gwt.GwtServiceInterfaceGenerator;
import ilarkesto.mda.model.ActionModel;
import ilarkesto.mda.model.ApplicationModel;
import ilarkesto.mda.model.BeanModel;
import ilarkesto.mda.model.ComponentModel;
import ilarkesto.mda.model.CompositeModel;
import ilarkesto.mda.model.DatobModel;
import ilarkesto.mda.model.EntityModel;
import ilarkesto.mda.model.EventModel;
import ilarkesto.mda.model.GwtEventBusModel;
import ilarkesto.mda.model.GwtServiceModel;

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
			gwtComponentsModel.addComposite("Pinger");
			gwtComponentsModel.addComposite("Auth");
			gwtComponentsModel.addComposite("Ui");
			gwtComponentsModel.addComposite("ProjectContext");
			gwtComponentsModel.addComposite("PublicContext");
			gwtComponentsModel.addComposite("HomeContext");
			gwtComponentsModel.addComposite("DndManager");
			gwtComponentsModel.addComposite("Chat");
			gwtComponentsModel.addComposite("UsersStatus");
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
			gwtEventBusModel.addEvent("ServerDataReceived").addParameter("data", "DataTransferObject").setQuiet(true);
			gwtEventBusModel.addEvent("Login");
			gwtEventBusModel.addEvent("Logout");
			gwtEventBusModel.addEvent("ProjectOpened");
			gwtEventBusModel.addEvent("ProjectClosed");
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
			gwtServiceModel.addMethod("requestIssues");
			gwtServiceModel.addMethod("requestRisks");
			gwtServiceModel.addMethod("requestComments").addParameter("parentId", String.class);
			gwtServiceModel.addMethod("changeProperties").addParameter("entityId", String.class).addParameter(
				"properties", Map.class);
			gwtServiceModel.addMethod("createEntity").addParameter("type", String.class).addParameter("properties",
				Map.class);
			gwtServiceModel.addMethod("deleteEntity").addParameter("entityId", String.class);
			gwtServiceModel.addMethod("requestEntityByReference").addParameter("reference", String.class);
			gwtServiceModel.addMethod("setSelectedEntitysIds").addParameter("ids", Set.class);
			gwtServiceModel.addMethod("sleep").addParameter("millis", long.class);
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
		}
		return applicationModel;
	}

	// ----------------
	// --- entities ---
	// ----------------

	private EntityModel projectModel;

	public EntityModel getProjectModel() {
		if (projectModel == null) {
			projectModel = createEntityModel("Project", "project");
			projectModel.setViewProtected(true);
			projectModel.setDeleteProtected(true);
			projectModel.setGwtSupport(true);
			projectModel.addProperty("label", String.class).setMandatory(true).setSearchable(true);
			projectModel.addProperty("description", String.class);
			projectModel.addProperty("begin", Date.class);
			projectModel.addProperty("end", Date.class);
			projectModel.addSetReference("participants", getUserModel());
			projectModel.addSetReference("admins", getUserModel());
			projectModel.addSetReference("productOwners", getUserModel());
			projectModel.addSetReference("scrumMasters", getUserModel());
			projectModel.addSetReference("teamMembers", getUserModel());
			projectModel.addReference("currentSprint", getSprintModel());
			projectModel.addReference("nextSprint", getSprintModel());
			projectModel.addListProperty("requirementsOrderIds", String.class);
			projectModel.addProperty("lastTaskNumber", int.class);
			projectModel.addProperty("lastRequirementNumber", int.class);
			projectModel.addProperty("lastQualityNumber", int.class);
			projectModel.addProperty("lastRiskNumber", int.class);
			projectModel.addProperty("lastIssueNumber", int.class);
			projectModel.addProperty("lastImpedimentNumber", int.class);
			getApplicationModel().addCreateAction(projectModel);
			projectModel.addAction("DeleteProject");
			projectModel.addAction("OpenProject");
		}
		return projectModel;
	}

	private EntityModel projectSprintSnapshotModel;

	public EntityModel getProjectSprintSnapshotModel() {
		if (projectSprintSnapshotModel == null) {
			projectSprintSnapshotModel = createEntityModel("ProjectSprintSnapshot", "project");
			projectSprintSnapshotModel.addReference("sprint", getSprintModel()).setUnique(true);
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
			requirementModel.addReference("project", getProjectModel()).setMaster(true);
			requirementModel.addReference("sprint", getSprintModel());
			requirementModel.addProperty("number", int.class);
			requirementModel.addSetReference("qualitys", getQualityModel());
			requirementModel.addProperty("label", String.class);
			requirementModel.addProperty("description", String.class);
			requirementModel.addProperty("testDescription", String.class);
			requirementModel.addProperty("estimatedWork", Integer.class);
			requirementModel.addProperty("closed", boolean.class);
			requirementModel.addProperty("dirty", boolean.class);
			getApplicationModel().addCreateAction(requirementModel);
			requirementModel.addAction("DeleteRequirement");
			requirementModel.addAction("AddRequirementToCurrentSprint");
			requirementModel.addAction("RemoveRequirementFromSprint");
			requirementModel.addAction("SetRequirementDirty");
			requirementModel.addAction("SetRequirementClean");
			requirementModel.addAction("CloseRequirement");
			requirementModel.addAction("ReopenRequirement");
		}
		return requirementModel;
	}

	private EntityModel qualityModel;

	public EntityModel getQualityModel() {
		if (qualityModel == null) {
			qualityModel = createEntityModel("Quality", "project");
			qualityModel.setGwtSupport(true);
			qualityModel.addReference("project", getProjectModel()).setMaster(true);
			qualityModel.addProperty("number", int.class);
			qualityModel.addProperty("label", String.class);
			qualityModel.addProperty("description", String.class);
			qualityModel.addProperty("testDescription", String.class);
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
			sprintModel.addReference("project", getProjectModel()).setMaster(true);
			sprintModel.addProperty("label", String.class);
			sprintModel.addProperty("goal", String.class);
			sprintModel.addProperty("begin", Date.class);
			sprintModel.addProperty("end", Date.class);
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
			taskModel.addReference("requirement", getRequirementModel()).setMaster(true);
			taskModel.addProperty("number", int.class);
			taskModel.addProperty("label", String.class);
			taskModel.addProperty("description", String.class);
			taskModel.addProperty("remainingWork", int.class);
			taskModel.addProperty("burnedWork", int.class);
			// taskModel.addProperty("notice", String.class);
			taskModel.addReference("owner", getUserModel());
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
			impedimentModel.addProperty("label", String.class).setMandatory(true);
			impedimentModel.addProperty("date", Date.class).setMandatory(true);
			impedimentModel.addProperty("description", String.class);
			impedimentModel.addProperty("solution", String.class);
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
			riskModel.addProperty("number", int.class);
			riskModel.addProperty("label", String.class).setSearchable(true);
			riskModel.addProperty("description", String.class).setSearchable(true);
			riskModel.addProperty("mitigationPlans", String.class).setSearchable(true);
			riskModel.addProperty("probability", int.class);
			riskModel.addProperty("impact", int.class);
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
			userModel.addProperty("name", String.class).setSearchable(true);
			userModel.addProperty("admin", boolean.class);
			userModel.addProperty("email", String.class).setSearchable(true);
			userModel.addReference("currentProject", getProjectModel());
			userModel.addProperty("color", String.class);
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
			projectUserConfigModel.addReference("project", getProjectModel()).setMaster(true);
			projectUserConfigModel.addReference("user", getUserModel()).setMaster(true);
			projectUserConfigModel.addProperty("color", String.class);
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
			issueModel.addProperty("type", String.class).setMandatory(true);
			issueModel.addProperty("date", Date.class).setMandatory(true);
			issueModel.addProperty("label", String.class).setMandatory(true).setSearchable(true);
			issueModel.addProperty("description", String.class).setSearchable(true);
			getApplicationModel().addCreateAction(issueModel);
			issueModel.addAction("DeleteIssue");
			issueModel.addAction("ConvertIssueToRequirement");
			issueModel.addAction("ConvertIssueToQuality");
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
			chatMessageModel.addProperty("text", String.class);
			chatMessageModel.addProperty("dateAndTime", DateAndTime.class);
		}
		return chatMessageModel;
	}

	private EntityModel commentModel;

	public EntityModel getCommentModel() {
		if (commentModel == null) {
			commentModel = createEntityModel("Comment", "collaboration");
			commentModel.setGwtSupport(true);
			getApplicationModel().addCreateAction(commentModel);
			commentModel.addReference("parent", getEntityModel()).setMaster(true);
			commentModel.addReference("author", getUserModel());
			commentModel.addProperty("text", String.class);
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
			wikipageModel.addProperty("name", String.class).setMandatory(true).setSearchable(true);
			wikipageModel.addProperty("text", String.class).setSearchable(true);
			getApplicationModel().addCreateAction(wikipageModel);
			wikipageModel.addAction("DeleteWikipage");
		}
		return wikipageModel;
	}

	@Override
	protected String getBasePackageName() {
		return "scrum.server";
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
