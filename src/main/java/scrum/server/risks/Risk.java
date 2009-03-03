package scrum.server.risks;

public class Risk extends GRisk {

	public Risk(Risk template) {
		super(template);
	}

	public Risk() {
		super(null);
	}

	@Override
	public String toString() {
		return getLabel();
	}

}
