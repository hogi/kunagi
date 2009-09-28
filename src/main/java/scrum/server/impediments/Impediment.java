package scrum.server.impediments;

import ilarkesto.base.time.Date;

public class Impediment extends GImpediment {

	@Override
	public void ensureIntegrity() {
		super.ensureIntegrity();
		if (!isDateSet()) setDate(Date.today());
	}

	@Override
	public String toString() {
		return getLabel();
	}
}
