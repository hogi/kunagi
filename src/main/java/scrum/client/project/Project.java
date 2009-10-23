package scrum.client.project;

import ilarkesto.gwt.client.AGwtEntity;
import ilarkesto.gwt.client.Gwt;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import scrum.client.UsersStatus;
import scrum.client.admin.ProjectUserConfig;
import scrum.client.admin.User;
import scrum.client.collaboration.Wikipage;
import scrum.client.impediments.Impediment;
import scrum.client.issues.Issue;
import scrum.client.risks.Risk;
import scrum.client.sprint.Sprint;
import scrum.client.sprint.Task;

public class Project extends GProject {

	private static final String effortUnit = "StoryPoints";
	public static final String INIT_LABEL = "New Project";

	private transient Comparator<Requirement> requirementsOrderComparator;

	public Project(User creator) {
		setLabel(INIT_LABEL);
		addAdmin(creator);
	}

	public Project(Map data) {
		super(data);
	}

	public List<Wikipage> getWikipages() {
		return cm.getDao().getWikipages(); // TODO filter
	}

	public Wikipage getWikipage(String name) {
		name = name.toLowerCase();
		for (Wikipage page : getDao().getWikipagesByProject(this)) {
			if (page.getName().toLowerCase().equals(name)) return page;
		}
		return null;
	}

	public ProjectUserConfig getUserConfig(User user) {
		for (ProjectUserConfig config : getDao().getProjectUserConfigsByProject(this)) {
			if (config.isUser(user)) { return config; }
		}
		throw new IllegalStateException("User has no project config: " + user);
	}

	public boolean isPig(User user) {
		return isProductOwner(user) || isScrumMaster(user) || isTeamMember(user);
	}

	public boolean isParticipant(User user) {
		return getParticipants().contains(user);
	}

	public boolean isTeamMember(User user) {
		return getTeamMembers().contains(user);
	}

	public boolean isScrumMaster(User user) {
		return getScrumMasters().contains(user);
	}

	public boolean isProductOwner(User user) {
		return getProductOwners().contains(user);
	}

	public void updateRequirementsOrder() {
		List<Requirement> requirements = getRequirements();
		Collections.sort(requirements, getRequirementsOrderComparator());
		updateRequirementsOrder(requirements);
	}

	public void updateRequirementsOrder(List<Requirement> requirements) {
		setRequirementsOrderIds(Gwt.getIdsAsList(requirements));
	}

	public void setParticipantsConfigured(Collection<User> users) {
		users.addAll(getTeamMembers());
		users.addAll(getAdmins());
		users.addAll(getProductOwners());
		users.addAll(getScrumMasters());
		setParticipants(users);
	}

	public String getEffortUnit() {
		return effortUnit;
	}

	public Wikipage createNewWikipage(String name) {
		Wikipage page = new Wikipage(this, name);
		getDao().createWikipage(page);
		return page;
	}

	public Issue createNewIssue() {
		Issue issue = new Issue(this);
		getDao().createIssue(issue);
		return issue;
	}

	public Impediment createNewImpediment() {
		Impediment impediment = new Impediment(this);
		getDao().createImpediment(impediment);
		return impediment;
	}

	public void deleteImpediment(Impediment impediment) {
		getDao().deleteImpediment(impediment);
	}

	public void deleteIssue(Issue issue) {
		getDao().deleteIssue(issue);
	}

	public void deleteRisk(Risk risk) {
		getDao().deleteRisk(risk);
	}

	public List<Impediment> getImpediments() {
		return getDao().getImpedimentsByProject(this);
	}

	public List<Issue> getIssues() {
		return getDao().getIssuesByProject(this);
	}

	public List<Risk> getRisks() {
		return getDao().getRisksByProject(this);
	}

	public Risk createNewRisk() {
		Risk risk = new Risk(this);
		getDao().createRisk(risk);
		return risk;
	}

	public Requirement createNewRequirement() {
		Requirement item = new Requirement(this);
		getDao().createRequirement(item);
		updateRequirementsOrder();
		return item;
	}

	public Quality createNewQuality() {
		Quality item = new Quality(this);
		getDao().createQuality(item);
		return item;
	}

	public void deleteRequirement(Requirement item) {
		getDao().deleteRequirement(item);
	}

	public void deleteQuality(Quality item) {
		getDao().deleteQuality(item);
	}

	public List<Requirement> getRequirements() {
		return getDao().getRequirementsByProject(this);
	}

	public List<Requirement> getRequirementsOrdered() {
		List<Requirement> requirements = getRequirements();
		Collections.sort(requirements, getRequirementsOrderComparator());
		return requirements;
	}

	public List<Quality> getQualitys() {
		return getDao().getQualitysByProject(this);
	}

	public Sprint createNewSprint() {
		Sprint sprint = new Sprint(this, "New Sprint");
		getDao().createSprint(sprint);
		return sprint;
	}

	public List<Sprint> getSprints() {
		return getDao().getSprintsByProject(this);
	}

	public boolean deleteTask(Task task) {
		for (Requirement requirement : getRequirements()) {
			boolean b = requirement.getTasks().remove(task);
			if (b) return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return getLabel();
	}

	public Comparator<Requirement> getRequirementsOrderComparator() {
		if (requirementsOrderComparator == null) requirementsOrderComparator = new Comparator<Requirement>() {

			public int compare(Requirement a, Requirement b) {
				List<String> order = getRequirementsOrderIds();
				int additional = order.size();
				int ia = order.indexOf(a.getId());
				if (ia < 0) {
					ia = additional;
					additional++;
				}
				int ib = order.indexOf(b.getId());
				if (ib < 0) {
					ib = additional;
					additional++;
				}
				return ia - ib;
			}
		};
		return requirementsOrderComparator;
	}

	public String formatEfford(Integer i) {
		if (i == null || i == 0) return "nothing";
		String unit = getEffortUnit();
		if (i == 1) {
			if (unit.endsWith("s")) unit = unit.substring(0, unit.length() - 2);
			return "1 " + unit;
		}
		return i + " " + unit;
	}

	public static final Comparator<Project> LABEL_COMPARATOR = new Comparator<Project>() {

		public int compare(Project a, Project b) {
			return a.getLabel().compareTo(b.getLabel());
		}
	};

	public Set<User> getUsersSelecting(AGwtEntity entity) {
		UsersStatus usersStatus = cm.getUsersStatus();
		Set<User> users = new HashSet<User>();
		for (User user : getParticipants()) {
			if (usersStatus.getSelectedEntitysIds(user).contains(entity.getId())) {
				users.add(user);
			}
		}
		return users;
	}

	private List<ProjectUserConfig> getUserConfigs() {
		return getDao().getProjectUserConfigsByProject(this);
	}

}
