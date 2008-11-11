package scrum.mda.framework;

import ilarkesto.base.Str;
import ilarkesto.mda.gen.AClassGenerator;

public class GwtServiceInterfaceGenerator extends AClassGenerator {

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
		return "G" + Str.uppercaseFirstLetter(model.getName()) + "Service";
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
	protected String getSuperclass() {
		return "com.google.gwt.user.client.rpc.RemoteService";
	}

	@Override
	protected void writeContent() {
		for (GwtServiceMethodModel method : model.getMethods()) {
			ln();
			StringBuilder parameters = new StringBuilder();
			boolean first = true;
			for (GwtServiceMethodParameterModel parameter : method.getParameters()) {
				if (first) {
					first = false;
				} else {
					parameters.append(", ");
				}
				parameters.append(parameter.getType().getName()).append(" ").append(parameter.getName());
			}
			ln("    DataTransferObject", method.getName() + "(" + parameters + ");");
		}
	}

}
