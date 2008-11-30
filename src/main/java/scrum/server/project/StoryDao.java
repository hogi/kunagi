package scrum.server.project;

public class StoryDao extends GStoryDao {

	@Override
	public Story newEntityInstance() {
		Story story = super.newEntityInstance();
		story.setLabel(scrum.client.project.Story.INIT_LABEL);
		return story;
	}

}
