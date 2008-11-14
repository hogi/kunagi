package scrum.server.impediments;


public class ImpedimentDao extends GImpedimentDao {

	@Override
	public Impediment newEntityInstance() {
		Impediment impediment = super.newEntityInstance();
		impediment.setLabel(scrum.client.impediments.Impediment.INIT_LABEL);
		return impediment;
	}

}
