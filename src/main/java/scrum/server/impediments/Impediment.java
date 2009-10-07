package scrum.server.impediments;

import ilarkesto.base.time.Date;

public class Impediment extends GImpediment {

	@Override
	public void ensureIntegrity() {
		super.ensureIntegrity();
		if (!isDateSet()) setDate(Date.today());

		// delete when closed and older than 4 weeks
		if (isClosed() && getDate().getPeriodToNow().toWeeks() > 4) getDao().deleteEntity(this);

	}

	@Override
	public String toString() {
		return getLabel();
	}
}
