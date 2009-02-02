package scrum.server.project;

public class RequirementDao extends GRequirementDao {

	@Override
	public Requirement newEntityInstance() {
		Requirement requirement = super.newEntityInstance();
		requirement.setLabel(scrum.client.project.Requirement.INIT_LABEL);
		return requirement;
	}

}
