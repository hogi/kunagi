package scrum.server.impediments;


public class Impediment extends GImpediment {

	// --- dependencies ---

	public Impediment(Impediment template) {
		super(template);
	}

	public Impediment() {
		super(null);
	}

	// --- ---

	@Override
	public String toString() {
		return getLabel();
	}
}
