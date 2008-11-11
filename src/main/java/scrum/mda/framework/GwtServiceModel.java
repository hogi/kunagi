package scrum.mda.framework;

import ilarkesto.mda.model.AModel;
import ilarkesto.mda.model.PackageModel;

import java.util.ArrayList;
import java.util.List;

public class GwtServiceModel extends AModel {

	private PackageModel packageModel;
	private List<GwtServiceMethodModel> methods = new ArrayList<GwtServiceMethodModel>();

	public GwtServiceModel(String name, PackageModel package_) {
		super(name);
		this.packageModel = package_;
	}

	public GwtServiceMethodModel addMethod(String name) {
		GwtServiceMethodModel methodModel = new GwtServiceMethodModel(name);
		methods.add(methodModel);
		return methodModel;
	}

	public PackageModel getPackageModel() {
		return packageModel;
	}

	public List<GwtServiceMethodModel> getMethods() {
		return methods;
	}

}
