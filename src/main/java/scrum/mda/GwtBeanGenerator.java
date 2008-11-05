package scrum.mda;

import ilarkesto.base.Str;
import ilarkesto.mda.gen.ABeanModelClassGenerator;
import ilarkesto.mda.model.BeanModel;
import ilarkesto.mda.model.PropertyModel;

public class GwtBeanGenerator extends ABeanModelClassGenerator<BeanModel> {

	@Override
	protected String getName() {
		return "G" + bean.getName();
	}

	@Override
	protected String getPackage() {
		return bean.getPackageModel().getName().replace(".server.", ".client.");
	}

	@Override
	protected boolean isInterface() {
		return false;
	}

	@Override
	protected boolean isOverwrite() {
		return true;
	}

	@Override
	protected String getSuperclass() {
		String s = getPackage();
		int idx = s.lastIndexOf('.');
		s = s.substring(0, idx);
		return s + ".common.AEntity";
	}

	@Override
	protected void writeContent() {
		constructors();
		properties();
		updatePropertiesMethod();
	}

	private void constructors() {
		ln();
		ln("    public", "G" + bean.getName() + "() {}");
		ln();
		ln("    public", "G" + bean.getName() + "(Map data) {");
		ln("        super(data);");
		ln("        updateProperties(data);");
		ln("    }");
	}

	private void properties() {
		for (PropertyModel p : bean.getProperties()) {
			String nameUpper = Str.uppercaseFirstLetter(p.getName());
			ln();
			comment(p.getName());
			ln();
			if (p.isReference()) {
				ln("    private String", p.getName() + "Id;");
			} else {
				ln("    private", p.getType(), p.getName(), ";");
				ln();
				ln("    public final", p.getType(), (p.isBoolean() ? "is" : "get") + nameUpper + "() {");
				ln("        return this." + p.getName(), ";");
				ln("    }");
				ln("");
				ln("    public final", bean.getName(), "set" + nameUpper + "(" + p.getType(), p.getName() + ") {");
				ln("        this." + p.getName(), "=", p.getName(), ";");
				ln("        return (" + bean.getName() + ")this;");
				ln("    }");
			}
		}
	}

	private void updatePropertiesMethod() {
		ln();
		comment("update properties by map");
		ln();
		ln("    public void updateProperties(Map props) {");
		for (PropertyModel p : bean.getProperties()) {
			if (p.isReference()) {
				ln("        " + p.getName() + "Id = (String) props.get(\"id\");");
			} else {
				String type = p.getType();
				if (type.equals("boolean")) type = "Boolean";
				ln("        " + p.getName(), " = (" + type + ") props.get(\"" + p.getName() + "\");");
			}
		}
		ln("    }");
	}
}
