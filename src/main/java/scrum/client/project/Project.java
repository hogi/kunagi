package scrum.client.project;

import ilarkesto.core.logging.Log;
import ilarkesto.core.scope.Scope;
import ilarkesto.gwt.client.AGwtEntity;
import ilarkesto.gwt.client.Date;
import ilarkesto.gwt.client.DateAndTime;
import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.HyperlinkWidget;
import ilarkesto.gwt.client.Time;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import scrum.client.admin.Auth;
import scrum.client.admin.ProjectUserConfig;
import scrum.client.admin.User;
import scrum.client.calendar.SimpleEvent;
import scrum.client.collaboration.Comment;
import scrum.client.collaboration.ForumSupport;
import scrum.client.collaboration.Subject;
import scrum.client.collaboration.UsersStatus;
import scrum.client.collaboration.Wikipage;
import scrum.client.common.ShowEntityAction;
import scrum.client.files.File;
import scrum.client.impediments.Impediment;
import scrum.client.issues.Issue;
import scrum.client.journal.ProjectEvent;
import scrum.client.risks.Risk;
import scrum.client.sprint.Sprint;
import scrum.client.sprint.Task;

import com.google.gwt.user.client.ui.Widget;

public class Project extends GProject implements ForumSupport {

	private static transient final Log LOG = Log.get(Project.class);

	private static final String effortUnit = "pts";
	public static final String INIT_LABEL = "New Project";

	private transient Comparator<Requirement> requirementsOrderComparator;
	private transient Comparator<Issue> issuesOrderComparator;

	public Project(User creator) {
		setLabel(INIT_LABEL);
		addAdmin(creator);
	}

	public Project(Map data) {
		super(data);
	}

	@Override
	public boolean isEditable() {
		return isAdmin(Scope.get().getComponent(Auth.class).getUser());
	}

	public List<Issue> getClaimedBugs(User user) {
		List<Issue> issues = new ArrayList<Issue>();
		for (Issue issue : getBugs()) {
			if (issue.isOwner(user) && !issue.isFixed()) issues.add(issue);
		}
		return issues;
	}

	public List<Task> getClaimedTasks(User user) {
		List<Task> tasks = new ArrayList<Task>();
		for (Requirement req : getRequirements()) {
			tasks.addAll(req.getClaimedTasks(user));
		}
		return tasks;
	}

	public String getVelocitiesFromLastSprints() {
		StringBuilder sb = new StringBuilder();
		List<Sprint> sprints = getCompletedSprints();
		Collections.sort(sprints, Sprint.END_DATE_COMPARATOR);
		float sum = 0;
		int count = 0;
		for (Sprint sprint : sprints) {
			Float velocity = sprint.getVelocity();
			if (velocity == null) continue;
			String velocityString = velocity < 1 ? velocity.toString() : String.valueOf(velocity.intValue());
			sb.insert(0, velocityString + ", ");
			sum += velocity;
			count++;
			if (count >= 12) break;
		}
		float avarage = sum / count;
		String avarageString = String.valueOf(avarage);
		int idx = avarageString.lastIndexOf('.');
		if (idx > 0 && avarageString.length() > idx + 1) {
			avarageString = avarageString.substring(0, idx + 2);
		}
		sb.append("[ ").append(avarageString).append(" avg. ]");
		return sb.toString();
	}

	public Float getVelocityFromLastSprint() {
		Sprint latest = getLatestCompletedSprint();
		return latest == null ? null : latest.getVelocity();
	}

	public Sprint getLatestCompletedSprint() {
		Sprint latest = null;
		for (Sprint sprint : getSprints()) {
			if (!sprint.isCompleted()) continue;
			if (latest == null || sprint.getEnd().isAfter(latest.getEnd())) latest = sprint;
		}
		return latest;
	}

	public List<Sprint> getCompletedSprints() {
		List<Sprint> ret = new ArrayList<Sprint>();
		for (Sprint sprint : getSprints()) {
			if (sprint.isCompleted()) ret.add(sprint);
		}
		return ret;
	}

	public List<SimpleEvent> getSimpleEvents() {
		return getDao().getSimpleEventsByProject(this);
	}

	public List<ProjectEvent> getProjectEvents() {
		return getDao().getProjectEventsByProject(this);
	}

	public List<ProjectEvent> getLatestProjectEvents(int min) {
		List<ProjectEvent> events = getProjectEvents();
		Collections.sort(events, ProjectEvent.DATE_AND_TIME_COMPARATOR);

		DateAndTime deadline = new DateAndTime(Date.today().prevDay(), Time.now());
		List<ProjectEvent> ret = new ArrayList<ProjectEvent>();
		int count = 0;
		for (ProjectEvent event : events) {
			ret.add(event);
			count++;
			DateAndTime dateAndTime = event.getDateAndTime();
			if (count > min && dateAndTime.isBefore(deadline)) break;
		}
		return ret;
	}

	public List<Wikipage> getWikipages() {
		return getDao().getWikipagesByProject(this);
	}

	public List<File> getFiles() {
		return getDao().getFilesByProject(this);
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

	// TODO: guten namen für die methode finden.. hieß vorher isPig(), allerdings ist der PO eig. kein Pig,
	// oder?
	public boolean isProductOwnerOrScrumMasterOrTeamMember(User user) {
		return isProductOwner(user) || isScrumMaster(user) || isTeamMember(user);
	}

	public boolean isProductOwnerOrScrumMaster(User user) {
		return isProductOwner(user) || isScrumMaster(user);
	}

	public boolean isProductOwnerOrTeamMember(User user) {
		return isProductOwner(user) || isTeamMember(user);
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

	public boolean isAdmin(User user) {
		return getAdmins().contains(user);
	}

	public String getUsersRolesAsString(User user, String prefix, String suffix) {
		StringBuilder sb = new StringBuilder();
		List<String> roles = new ArrayList<String>();
		if (isProductOwner(user)) roles.add("PO");
		if (isScrumMaster(user)) roles.add("SM");
		if (isTeamMember(user)) roles.add("T");
		boolean first = true;
		if (!roles.isEmpty()) {
			for (String role : roles) {
				if (first) {
					first = false;
					if (prefix != null) sb.append(prefix);
				} else {
					sb.append(",");
				}
				sb.append(role);
			}
			if (suffix != null) sb.append(suffix);
		}
		return sb.toString();
	}

	private void updateRequirementsOrder() {
		List<Requirement> requirements = getRequirements();
		Collections.sort(requirements, getRequirementsOrderComparator());
		updateRequirementsOrder(requirements);
	}

	public void updateRequirementsOrder(List<Requirement> requirements) {
		setRequirementsOrderIds(Gwt.getIdsAsList(requirements));
	}

	public void updateUrgentIssuesOrder(List<Issue> issues) {
		setUrgentIssuesOrderIds(Gwt.getIdsAsList(issues));
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

	public Subject createNewSubject() {
		Subject subject = new Subject(this);
		getDao().createSubject(subject);
		return subject;
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

	public void deleteFile(File file) {
		getDao().deleteFile(file);
	}

	public void deleteIssue(Issue issue) {
		getDao().deleteIssue(issue);
	}

	public void deleteRisk(Risk risk) {
		getDao().deleteRisk(risk);
	}

	public Set<ForumSupport> getEntitiesWithComments() {
		Set<ForumSupport> ret = new HashSet<ForumSupport>();
		ret.addAll(getSubjects());
		for (Comment comment : getDao().getComments()) {
			AGwtEntity entity = comment.getParent();
			if (!(entity instanceof ForumSupport)) {
				LOG.error(entity.getClass().getName() + " needs to implement ForumSupport");
				continue;
			}
			ret.add((ForumSupport) comment.getParent());
		}
		return ret;
	}

	public List<Impediment> getOpenImpediments() {
		List<Impediment> ret = new ArrayList<Impediment>();
		for (Impediment impediment : getImpediments()) {
			if (impediment.isClosed()) continue;
			ret.add(impediment);
		}
		return ret;
	}

	public List<Impediment> getImpediments() {
		return getDao().getImpedimentsByProject(this);
	}

	public List<Subject> getSubjects() {
		return getDao().getSubjectsByProject(this);
	}

	public List<Issue> getOpenIssues(boolean includeSuspended) {
		List<Issue> ret = new ArrayList<Issue>();
		for (Issue issue : getIssues()) {
			if (!issue.isOpen()) continue;
			if (!includeSuspended && issue.isSuspended()) continue;
			ret.add(issue);
		}
		return ret;
	}

	public List<Issue> getIdeas() {
		List<Issue> ret = new ArrayList<Issue>();
		for (Issue issue : getIssues()) {
			if (issue.isIdea()) ret.add(issue);
		}
		return ret;
	}

	public List<Issue> getUnclaimedBugs(int severity) {
		List<Issue> ret = new ArrayList<Issue>();
		for (Issue issue : getBugs()) {
			if (issue.getOwner() == null && issue.isSeverity(severity)) ret.add(issue);
		}
		return ret;
	}

	public List<Issue> getBugs() {
		List<Issue> ret = new ArrayList<Issue>();
		for (Issue issue : getIssues()) {
			if (issue.isBug()) ret.add(issue);
		}
		return ret;
	}

	public List<Issue> getClosedIssues() {
		List<Issue> ret = new ArrayList<Issue>();
		for (Issue issue : getIssues()) {
			if (issue.isClosed()) ret.add(issue);
		}
		return ret;
	}

	public List<Issue> getIssues() {
		return getDao().getIssuesByProject(this);
	}

	public List<Risk> getRisks() {
		return getDao().getRisksByProject(this);
	}

	public List<Risk> getHighestRisks(int max) {
		List<Risk> ret = getRisks();
		Collections.sort(ret, Risk.PRIORITY_COMPARATOR);
		while (ret.size() > max) {
			ret.remove(ret.size() - 1);
		}
		return ret;
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

	public List<Requirement> getProductBacklogRequirements() {
		List<Requirement> ret = new ArrayList<Requirement>();
		for (Requirement requirement : getRequirements()) {
			if (requirement.isClosed() && !requirement.isInCurrentSprint()) continue;
			ret.add(requirement);
		}
		return ret;
	}

	public List<Requirement> getRequirements() {
		return getDao().getRequirementsByProject(this);
	}

	public List<Task> getTasks() {
		return getDao().getTasks();
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

	public Comparator<Issue> getIssuesOrderComparator() {
		if (issuesOrderComparator == null) issuesOrderComparator = new Comparator<Issue>() {

			public int compare(Issue a, Issue b) {
				List<String> order = getUrgentIssuesOrderIds();
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
		return issuesOrderComparator;
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

	public String formatEfford(Float i) {
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
		UsersStatus usersStatus = Scope.get().getComponent(UsersStatus.class);
		Set<User> users = new HashSet<User>();
		for (User user : getParticipants()) {
			if (usersStatus.getSelectedEntitysIds(user).contains(entity.getId())) {
				users.add(user);
			}
		}
		return users;
	}

	public Widget createForumItemWidget() {
		return new HyperlinkWidget(new ShowEntityAction(this, "Project Dashboard"));
	}

	public String getReference() {
		return "prj";
	}

}
