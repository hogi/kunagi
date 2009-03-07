package scrum.mda;

import ilarkesto.base.time.Date;
import ilarkesto.di.app.ApplicationStarter;
import ilarkesto.mda.AGeneratorApplication;
import ilarkesto.mda.gen.GwtApplicationGenerator;
import ilarkesto.mda.gen.GwtDaoGenerator;
import ilarkesto.mda.gen.GwtEntityGenerator;
import ilarkesto.mda.gen.GwtEntityTemplateGenerator;
import ilarkesto.mda.gen.GwtImageBundleGenerator;
import ilarkesto.mda.gen.GwtServiceAsyncInterfaceGenerator;
import ilarkesto.mda.gen.GwtServiceImplementationGenerator;
import ilarkesto.mda.gen.GwtServiceInterfaceGenerator;
import ilarkesto.mda.model.ApplicationModel;
import ilarkesto.mda.model.BeanModel;
import ilarkesto.mda.model.DatobModel;
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
			projectModel.setGwtSupport(true);
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
			requirementModel.setGwtSupport(true);
			requirementModel.addReference("project", getProjectModel()).setMaster(true);
			requirementModel.addReference("sprint", getSprintModel());
			requirementModel.addSetReference("attributes", getAttributeModel());
			requirementModel.addProperty("label", String.class);
			requirementModel.addProperty("description", String.class);
			requirementModel.addProperty("testDescription", String.class);
			requirementModel.addProperty("estimatedWork", Integer.class);
			requirementModel.addProperty("closed", boolean.class);
		}
		return requirementModel;
	}

	private EntityModel attributeModel;

	public EntityModel getAttributeModel() {
		if (attributeModel == null) {
			attributeModel = createEntityModel("Attribute", "project");
			autowire(attributeModel);
			attributeModel.setGwtSupport(true);
			attributeModel.addReference("project", getProjectModel()).setMaster(true);
			attributeModel.addProperty("label", String.class);
			attributeModel.addProperty("description", String.class);
			attributeModel.addProperty("testDescription", String.class);
		}
		return attributeModel;
	}

	private EntityModel sprintModel;

	public EntityModel getSprintModel() {
		if (sprintModel == null) {
			sprintModel = createEntityModel("Sprint", "sprint");
			autowire(sprintModel);
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
			taskModel.setGwtSupport(true);
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
			impedimentModel.setGwtSupport(true);
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
			riskModel.setGwtSupport(true);
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
			userModel.setGwtSupport(true);
			userModel.setSuperbean(super.getUserModel());
			userModel.addProperty("name", String.class);
		}
		return userModel;
	}

	// ---------------
	// --- service ---
	// ---------------

	private GwtServiceModel gwtServiceModel;

	public GwtServiceModel getGwtServiceModel() {
		if (gwtServiceModel == null) {
			gwtServiceModel = createGwtServiceModel("scrum");
			autowire(gwtServiceModel);
			gwtServiceModel.addMethod("ping");
			gwtServiceModel.addMethod("login").addParameter("username", String.class).addParameter("password",
				String.class);
			gwtServiceModel.addMethod("selectProject").addParameter("projectId", String.class);
			gwtServiceModel.addMethod("requestImpediments");
			gwtServiceModel.addMethod("requestRisks");
			gwtServiceModel.addMethod("requestRequirements");
			gwtServiceModel.addMethod("requestCurrentSprint");
			gwtServiceModel.addMethod("changeProperties").addParameter("entityId", String.class).addParameter(
				"properties", Map.class);
			gwtServiceModel.addMethod("createEntity").addParameter("type", String.class).addParameter("properties",
				Map.class);
			gwtServiceModel.addMethod("deleteEntity").addParameter("entityId", String.class);
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
			autowire(applicationModel);
			applicationModel.addDaosAsComposites(getFinalEntityModels(true));
			applicationModel.addGwtService(getGwtServiceModel());
		}
		return applicationModel;
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
				autowire(new GwtEntityGenerator()).generate(datobModel, getApplicationModel());
				autowire(new GwtEntityTemplateGenerator()).generate(datobModel);
			}
		}
	}

	@Override
	protected void onGeneration() {
		super.onGeneration();
		autowire(new GwtServiceInterfaceGenerator()).generate(getGwtServiceModel());
		autowire(new GwtServiceAsyncInterfaceGenerator()).generate(getGwtServiceModel());
		autowire(new GwtServiceImplementationGenerator()).generate(getGwtServiceModel());
		autowire(new GwtApplicationGenerator()).generate(getApplicationModel());
		autowire(new GwtDaoGenerator()).generate(getApplicationModel(), getFinalEntityModels(false));
		autowire(new GwtImageBundleGenerator()).generate("scrum.client.img");
	}
}
