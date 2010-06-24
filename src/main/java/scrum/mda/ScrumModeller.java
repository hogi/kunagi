package scrum.mda;

import ilarkesto.core.scope.Scope;
import ilarkesto.di.app.ApplicationStarter;
import ilarkesto.mda.model.CsvFileModelSource;
import ilarkesto.mda.model.Model;
import ilarkesto.mda.model.ModelProcessor;
import ilarkesto.mda.model.ModellingSession;
import ilarkesto.mda.swingeditor.Starter;
import ilarkesto.mda.swingeditor.Workspace;

import java.io.File;

public class ScrumModeller extends Starter {

	public static void main(String[] args) {
		Scope scope = createModellerScope();

		ModellingSession modellingSession = scope.getComponent(ModellingSession.class);
		modellingSession.addProcessor(new LegacyGeneration());
		modellingSession.load(new CsvFileModelSource(new File("src/model.csv")));

		scope.getComponent(Workspace.class).showJFrame();
	}

	static class LegacyGeneration implements ModelProcessor {

		public void processModel(Model model) {
			ApplicationStarter.startApplication(ScrumModelApplication.class).generateClasses().shutdown();
		}

	}

}
