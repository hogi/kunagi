package scrum.mda.framework;

import ilarkesto.base.Str;
import ilarkesto.mda.gen.AClassGenerator;

import java.util.HashSet;
import java.util.Set;

public class GwtServiceAsyncInterfaceGenerator extends AClassGenerator {

	private GwtServiceModel model;

	public void setModel(GwtServiceModel model) {
		this.model = model;
	}

	public void generate(GwtServiceModel model) {
		this.model = model;
		generate();
	}

	@Override
	protected String getName() {
		return "G" + Str.uppercaseFirstLetter(model.getName()) + "ServiceAsync";
	}

	@Override
	protected String getPackage() {
		return model.getPackageModel().getName().replace(".server", ".client");
	}

	@Override
	protected boolean isInterface() {
		return true;
	}

	@Override
	protected boolean isOverwrite() {
		return true;
	}

	@Override
	protected void writeContent() {
		for (GwtServiceMethodModel method : model.getMethods()) {
			ln();
			StringBuilder parameters = new StringBuilder();
			for (GwtServiceMethodParameterModel parameter : method.getParameters()) {
				parameters.append(parameter.getType().getName()).append(" ").append(parameter.getName());
				parameters.append(", ");
			}
			ln("    void", method.getName() + "(" + parameters + "AsyncCallback<DataTransferObject> callback);");
		}
	}

	@Override
	protected Set<String> getImports() {
		Set<String> ret = new HashSet<String>(super.getImports());
		ret.add("com.google.gwt.user.client.rpc.AsyncCallback");
		return ret;
	}

}
