package scrum.mda.framework;

import ilarkesto.mda.model.AModel;

public class GwtServiceMethodParameterModel extends AModel {

	private Class type;

	public GwtServiceMethodParameterModel(String name, Class type) {
		super(name);
		this.type = type;
	}

	public Class getType() {
		return type;
	}

}
