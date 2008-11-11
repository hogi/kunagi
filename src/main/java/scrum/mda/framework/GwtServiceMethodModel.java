package scrum.mda.framework;

import ilarkesto.mda.model.AModel;

import java.util.ArrayList;
import java.util.List;

public class GwtServiceMethodModel extends AModel {

	private List<GwtServiceMethodParameterModel> parameters = new ArrayList<GwtServiceMethodParameterModel>();

	public GwtServiceMethodModel(String name) {
		super(name);
	}

	public GwtServiceMethodModel addParameter(String name, Class type) {
		GwtServiceMethodParameterModel parameter = new GwtServiceMethodParameterModel(name, type);
		parameters.add(parameter);
		return this;
	}

	public List<GwtServiceMethodParameterModel> getParameters() {
		return parameters;
	}
}
