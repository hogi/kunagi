package scrum.server.project;

import java.util.HashSet;
import java.util.Set;

import scrum.server.admin.User;
import scrum.server.impediments.Impediment;
import scrum.server.impediments.ImpedimentDao;
import scrum.server.sprint.Sprint;

public class Project extends GProject {

	// --- dependencies ---

	private static ImpedimentDao impedimentDao;
	private static StoryDao storyDao;

	public Project(GProject template) {
		super(template);
	}

	public Project() {
		super(null);
	}

	public static void setImpedimentDao(ImpedimentDao impedimentDao) {
		Project.impedimentDao = impedimentDao;
	}

	public static void setStoryDao(StoryDao storyDao) {
		Project.storyDao = storyDao;
	}

	// --- ---

	public Set<User> getMembers() {
		Set<User> ret = new HashSet<User>();
		ret.add(getProductOwner());
		ret.add(getScrumMaster());
		ret.addAll(getTeamMembers());
		ret.addAll(getAdmins());
		return ret;
	}

	public boolean isMember(User user) {
		return containsTeamMember(user) || containsAdmin(user) || isProductOwner(user) || isScrumMaster(user);
	}

	public Set<Sprint> getSprints() {
		return sprintDao.getSprintsByProject(this);
	}

	public Set<Impediment> getImpediments() {
		return impedimentDao.getImpedimentsByProject(this);
	}

	public Set<Story> getBacklogItems() {
		return storyDao.getStorysByProject(this);
	}

	@Override
	public String toString() {
		return getLabel();
	}

}
