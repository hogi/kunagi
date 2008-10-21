package scrum.model;

import ilarkesto.di.app.ApplicationStarter;
import ilarkesto.mda.gen.AGeneratorApplication;
import ilarkesto.mda.model.ApplicationModel;
import ilarkesto.mda.model.EntityModel;
import ilarkesto.mda.model.PackageModel;

public class ScrumModelApplication extends AGeneratorApplication {

	// ----------------
	// --- entities ---
	// ----------------

	private EntityModel projectModel;

	public EntityModel getProjectModel() {
		if (projectModel == null) {
			projectModel = createEntityModel("Project", "project");
			autowire(projectModel);
			projectModel.addProperty("label", String.class).setMandatory(true).setSearchable(true);
		}
		return projectModel;
	}

	private EntityModel userModel;

	@Override
	public EntityModel getUserModel() {
		if (userModel == null) {
			userModel = createEntityModel("User", "admin");
			autowire(userModel);
			userModel.setSuperbean(super.getUserModel());
		}
		return userModel;
	}

	// -------------------
	// --- application ---
	// -------------------

	private ApplicationModel applicationModel;

	public ApplicationModel getApplicationModel() {
		if (applicationModel == null) {
			applicationModel = createWebApplicationModel("Scrum");
			autowire(applicationModel);
			applicationModel.addDaosAsComposites(getFinalEntityModels());
		}
		return applicationModel;
	}

	@Override
	protected String getBasePackageName() {
		return "scrum";
	}

	private PackageModel basePackageModel;

	@Override
	public PackageModel getBasePackageModel() {
		if (basePackageModel == null) {
			basePackageModel = new PackageModel(getBasePackageName()).getSubPackageModel("server");
			autowire(basePackageModel);
		}
		return basePackageModel;
	}

	public static void main(String[] args) throws InterruptedException {
		ApplicationStarter.startApplication(ScrumModelApplication.class).generateClasses();
		Thread.sleep(1000);
		System.exit(0);
	}

}
