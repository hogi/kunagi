package scrum.mda;

import ilarkesto.base.time.Date;
import ilarkesto.di.app.ApplicationStarter;
import ilarkesto.mda.AGeneratorApplication;
import ilarkesto.mda.gen.GwtApplicationGenerator;
import ilarkesto.mda.gen.GwtDaoGenerator;
import ilarkesto.mda.gen.GwtEntityGenerator;
import ilarkesto.mda.gen.GwtImageBundleGenerator;
import ilarkesto.mda.gen.GwtServiceAsyncInterfaceGenerator;
import ilarkesto.mda.gen.GwtServiceImplementationGenerator;
import ilarkesto.mda.gen.GwtServiceInterfaceGenerator;
import ilarkesto.mda.model.ApplicationModel;
import ilarkesto.mda.model.BeanModel;
import ilarkesto.mda.model.EntityModel;
import ilarkesto.mda.model.GwtServiceModel;

import java.util.Map;

public class ScrumModelApplication extends AGeneratorApplication {

	public static void main(String[] args) throws InterruptedException {
		ApplicationStarter.startApplication(ScrumModelApplication.class).generateClasses().shutdown();
	}

	// ----------------
	// --- entities ---
	// ----------------

	private EntityModel projectModel;

	public EntityModel getProjectModel() {
		if (projectModel == null) {
			projectModel = createEntityModel("Project", "project");
			autowire(projectModel);
			projectModel.addProperty("label", String.class).setMandatory(true).setSearchable(true);
			projectModel.addProperty("description", String.class);
			projectModel.addProperty("begin", Date.class);
			projectModel.addProperty("end", Date.class);
			projectModel.addSetReference("admins", getUserModel());
			projectModel.addReference("productOwner", getUserModel());
			projectModel.addReference("scrumMaster", getUserModel());
			projectModel.addSetReference("teamMembers", getUserModel());
			projectModel.addReference("currentSprint", getSprintModel());
			projectModel.addReference("nextSprint", getSprintModel());
		}
		return projectModel;
	}

	private EntityModel projectSprintSnapshotModel;

	public EntityModel getProjectSprintSnapshotModel() {
		if (projectSprintSnapshotModel == null) {
			projectSprintSnapshotModel = createEntityModel("ProjectSprintSnapshot", "project");
			autowire(projectSprintSnapshotModel);
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
			autowire(requirementModel);
			requirementModel.addReference("project", getProjectModel()).setMaster(true);
			requirementModel.addReference("sprint", getSprintModel());
			requirementModel.addProperty("label", String.class);
			requirementModel.addProperty("description", String.class);
			requirementModel.addProperty("testDescription", String.class);
			requirementModel.addProperty("estimatedWork", Integer.class);
			requirementModel.addProperty("closed", boolean.class);
		}
		return requirementModel;
	}

	private EntityModel sprintModel;

	public EntityModel getSprintModel() {
		if (sprintModel == null) {
			sprintModel = createEntityModel("Sprint", "sprint");
			autowire(sprintModel);
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
			autowire(sprintDaySnapshotModel);
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
			autowire(taskModel);
			taskModel.addReference("requirement", getRequirementModel()).setMaster(true);
			taskModel.addProperty("label", String.class);
			taskModel.addProperty("remainingWork", int.class);
			taskModel.addProperty("burnedWork", int.class);
			taskModel.addProperty("notice", String.class);
			taskModel.addReference("owner", getUserModel());
		}
		return taskModel;
	}

	private EntityModel impedimentModel;

	public EntityModel getImpedimentModel() {
		if (impedimentModel == null) {
			impedimentModel = createEntityModel("Impediment", "impediments");
			autowire(impedimentModel);
			impedimentModel.addReference("project", getProjectModel()).setMaster(true);
			impedimentModel.addProperty("label", String.class);
			impedimentModel.addProperty("date", Date.class);
			impedimentModel.addProperty("description", String.class);
			impedimentModel.addProperty("solution", String.class);
			impedimentModel.addProperty("solveDate", Date.class);
		}
		return impedimentModel;
	}

	private EntityModel riskModel;

	public EntityModel getRiskModel() {
		if (riskModel == null) {
			riskModel = createEntityModel("Risk", "risks");
			autowire(riskModel);
			riskModel.addReference("project", getProjectModel()).setMaster(true);
			riskModel.addProperty("label", String.class).setSearchable(true);
			riskModel.addProperty("description", String.class).setSearchable(true);
			riskModel.addProperty("probability", int.class);
			riskModel.addProperty("impact", int.class);
		}
		return riskModel;
	}

	private EntityModel userModel;

	@Override
	public EntityModel getUserModel() {
		if (userModel == null) {
			userModel = createEntityModel("User", "admin");
			autowire(userModel);
			userModel.setSuperbean(super.getUserModel());
			userModel.addProperty("name", String.class);
		}
		return userModel;
	}

	// ---------------
	// --- service ---
	// ---------------

	private GwtServiceModel serviceModel;

	public GwtServiceModel getServiceModel() {
		if (serviceModel == null) {
			serviceModel = createGwtServiceModel("scrum");
			autowire(serviceModel);
			serviceModel.addMethod("ping");
			serviceModel.addMethod("login").addParameter("username", String.class).addParameter("password",
				String.class);
			serviceModel.addMethod("selectProject").addParameter("projectId", String.class);
			serviceModel.addMethod("requestImpediments");
			serviceModel.addMethod("requestRisks");
			serviceModel.addMethod("requestRequirements");
			serviceModel.addMethod("requestCurrentSprint");
			serviceModel.addMethod("changeProperties").addParameter("entityId", String.class).addParameter(
				"properties", Map.class);
			serviceModel.addMethod("createEntity").addParameter("type", String.class).addParameter("properties",
				Map.class);
			serviceModel.addMethod("deleteEntity").addParameter("entityId", String.class);
			serviceModel.addMethod("sleep").addParameter("millis", long.class);
		}
		return serviceModel;
	}

	// -------------------
	// --- application ---
	// -------------------

	private ApplicationModel applicationModel;

	public ApplicationModel getApplicationModel() {
		if (applicationModel == null) {
			applicationModel = createWebApplicationModel("Scrum");
			autowire(applicationModel);
			applicationModel.addDaosAsComposites(getFinalEntityModels(true));
			applicationModel.addService(getServiceModel());
		}
		return applicationModel;
	}

	@Override
	protected String getBasePackageName() {
		return "scrum.server";
	}

	@Override
	protected void generate(BeanModel beanModel) {
		super.generate(beanModel);
		if (beanModel instanceof EntityModel) {
			autowire(new GwtEntityGenerator()).generate((EntityModel) beanModel, getApplicationModel());
		}
	}

	@Override
	protected void onGeneration() {
		super.onGeneration();
		autowire(new GwtServiceInterfaceGenerator()).generate(getServiceModel());
		autowire(new GwtServiceAsyncInterfaceGenerator()).generate(getServiceModel());
		autowire(new GwtServiceImplementationGenerator()).generate(getServiceModel());
		autowire(new GwtApplicationGenerator()).generate(getApplicationModel());
		autowire(new GwtDaoGenerator()).generate(getApplicationModel(), getFinalEntityModels(false));
		autowire(new GwtImageBundleGenerator()).generate("scrum.client.img");
	}
}
