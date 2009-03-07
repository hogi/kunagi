package scrum.server.project;

public class AttributeDao extends GAttributeDao {

	@Override
	public Attribute newEntityInstance() {
		Attribute attribute = super.newEntityInstance();
		attribute.setLabel(scrum.client.project.Attribute.INITIAL_LABEL);
		return attribute;
	}

	// --- test data ---

	public void createTestAttribute(Project project, int variant) {
		Attribute attribute = newEntityInstance();
		attribute.setProject(project);

		if (variant == 1) {
			attribute.setLabel("Corporate Design");
			attribute.setDescription("All GUI elements must reflect the corporate design.");
		} else if (variant == 2) {
			attribute.setLabel("Coffee shock");
			attribute.setDescription("Developers must consume coffee during work at all costs.");
			attribute.setTestDescription("At least 10 cups per day per developer.");
		}

		saveEntity(attribute);
	}
}