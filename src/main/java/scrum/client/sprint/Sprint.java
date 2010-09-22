package scrum.client.sprint;

import ilarkesto.core.scope.Scope;
import ilarkesto.gwt.client.Date;
import ilarkesto.gwt.client.HyperlinkWidget;
import ilarkesto.gwt.client.TimePeriod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import scrum.client.ScrumGwt;
import scrum.client.admin.Auth;
import scrum.client.admin.User;
import scrum.client.collaboration.ForumSupport;
import scrum.client.collaboration.Wiki;
import scrum.client.common.ReferenceSupport;
import scrum.client.common.ShowEntityAction;
import scrum.client.impediments.Impediment;
import scrum.client.project.Project;
import scrum.client.project.Requirement;
import scrum.client.release.Release;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;

public class Sprint extends GSprint implements ForumSupport, ReferenceSupport {

	public static final String REFERENCE_PREFIX = "spr";

	private Wiki wiki = Scope.get().getComponent(Wiki.class);

	public Sprint(Project project, String label) {
		setProject(project);
		setLabel(label);
	}

	public Sprint(Map data) {
		super(data);
	}

	public Integer getLengthInDays() {
		Date begin = getBegin();
		Date end = getEnd();
		if (begin == null || end == null) return null;
		return begin.getPeriodTo(end).toDays();
	}

	public void setLengthInDays(Integer lenght) {
		if (lenght == null || lenght <= 0) return;
		Date begin = getBegin();
		if (begin == null) return;
		Date end = begin.addDays(lenght);
		setEnd(end);
	}

	public List<Task> getTasksBlockedBy(Impediment impediment) {
		List<Task> ret = new ArrayList<Task>();
		for (Requirement requirement : getRequirements()) {
			ret.addAll(requirement.getTasksBlockedBy(impediment));
		}
		return ret;
	}

	public List<Release> getReleases() {
		List<Release> ret = new ArrayList<Release>();
		for (Release release : getDao().getReleasesByProject(getProject())) {
			if (release.getSprints().contains(this)) ret.add(release);
		}
		return ret;
	}

	public String getChartUrl(int width, int height) {
		return GWT.getModuleBaseURL() + "sprintBurndownChart.png?sprintId=" + getId() + "&width=" + width + "&height="
				+ height;
	}

	public TimePeriod getLength() {
		return getBegin().getPeriodTo(getEnd());
	}

	public boolean isCompleted() {
		return getVelocity() != null;
	}

	public float getEstimatedRequirementWork() {
		float sum = 0;
		for (Requirement requirement : getRequirements()) {
			Float work = requirement.getEstimatedWork();
			if (work != null) sum += work;
		}
		return sum;
	}

	public float getCompletedRequirementWork() {
		float sum = 0;
		for (Requirement requirement : getRequirements()) {
			if (!requirement.isClosed()) continue;
			Float work = requirement.getEstimatedWork();
			if (work != null) sum += work;
		}
		return sum;
	}

	public List<Requirement> getRequirements() {
		return getDao().getRequirementsBySprint(this);
	}

	public List<Task> getUnclaimedTasks(boolean sorted) {
		List<Task> ret = new ArrayList<Task>();
		List<Requirement> requirements = getRequirements();
		if (sorted) Collections.sort(requirements, getProject().getRequirementsOrderComparator());
		for (Requirement requirement : requirements) {
			ret.addAll(requirement.getUnclaimedTasks());
		}
		return ret;
	}

	public List<Task> getTasks(User user) {
		List<Task> ret = new ArrayList<Task>();
		for (Requirement requirement : getRequirements()) {
			for (Task task : requirement.getTasks()) {
				if (user == null) {
					if (!task.isOwnerSet()) {
						ret.add(task);
					}
				} else {
					if (task.isOwner(user)) {
						ret.add(task);
					}
				}
			}
		}
		return ret;
	}

	public int getBurnedWorkInClosedTasks() {
		int sum = 0;
		for (Requirement requirement : getRequirements()) {
			sum += requirement.getBurnedWorkInClosedTasks();
		}
		return sum;
	}

	public int getBurnedWork() {
		return Requirement.sumBurnedWork(getRequirements());
	}

	public int getBurnedWorkInClaimedTasks() {
		int sum = 0;
		for (Requirement requirement : getRequirements()) {
			sum += requirement.getBurnedWorkInClaimedTasks();
		}
		return sum;
	}

	public int getRemainingWorkInClaimedTasks() {
		int sum = 0;
		for (Requirement requirement : getRequirements()) {
			sum += requirement.getRemainingWorkInClaimedTasks();
		}
		return sum;
	}

	public int getRemainingWorkInUnclaimedTasks() {
		int sum = 0;
		for (Requirement requirement : getRequirements()) {
			sum += requirement.getRemainingWorkInUnclaimedTasks();
		}
		return sum;
	}

	public int getRemainingWork() {
		int sum = 0;
		for (Requirement requirement : getRequirements()) {
			sum += requirement.getRemainingWork();
		}
		return sum;
	}

	@Override
	public String toHtml() {
		return ScrumGwt.toHtml(getReference(), getLabel());
	}

	@Override
	public String toString() {
		return getReference() + " " + getLabel();
	}

	@Override
	public boolean isEditable() {
		if (isCompleted()) return false;
		if (!getProject().isProductOwner(Scope.get().getComponent(Auth.class).getUser())) return false;
		return true;
	}

	@Override
	public boolean isPlanningEditable() {
		if (isCompleted()) return false;
		return true;
	}

	@Override
	public boolean isRetrospectiveEditable() {
		if (!getProject().isScrumMaster(Scope.get().getComponent(Auth.class).getUser())) return false;
		return true;
	}

	@Override
	public boolean isReviewEditable() {
		if (!getProject().isProductOwner(Scope.get().getComponent(Auth.class).getUser())) return false;
		return true;
	}

	@Override
	public boolean isDatesEditable() {
		if (isCompleted()) return false;
		if (!getProject().isProductOwner(Scope.get().getComponent(Auth.class).getUser())) return false;
		return true;
	}

	@Override
	public String getGoalTemplate() {
		return wiki.getTemplate("sprint.goal");
	}

	@Override
	public String getPlanningNoteTemplate() {
		return wiki.getTemplate("sprint.planning");
	}

	@Override
	public String getRetrospectiveNoteTemplate() {
		return wiki.getTemplate("sprint.retrospective");
	}

	@Override
	public String getReviewNoteTemplate() {
		return wiki.getTemplate("sprint.review");
	}

	public boolean isCurrent() {
		return getProject().isCurrentSprint(this);
	}

	public static final Comparator<Sprint> END_DATE_COMPARATOR = new Comparator<Sprint>() {

		@Override
		public int compare(Sprint a, Sprint b) {
			return b.getEnd().compareTo(a.getEnd());
		}

	};

	@Override
	public Widget createForumItemWidget() {
		String label = isCurrent() ? "Sprint Backlog" : "Sprint";
		return new HyperlinkWidget(new ShowEntityAction(this, label));
	}

	@Override
	public String getReference() {
		return REFERENCE_PREFIX + getNumber();
	}

	private transient LengthInDaysModel lengthInDaysModel;

	public LengthInDaysModel getLengthInDaysModel() {
		if (lengthInDaysModel == null) lengthInDaysModel = new LengthInDaysModel();
		return lengthInDaysModel;
	}

	protected class LengthInDaysModel extends ilarkesto.gwt.client.editor.AIntegerEditorModel {

		@Override
		public String getId() {
			return "Sprint_lengthInDays";
		}

		@Override
		public java.lang.Integer getValue() {
			return getLengthInDays();
		}

		@Override
		public void setValue(java.lang.Integer value) {
			setLengthInDays(value);
		}

		@Override
		public void increment() {
			setLengthInDays(getLengthInDays() + 1);
		}

		@Override
		public void decrement() {
			setLengthInDays(getLengthInDays() - 1);
		}

		@Override
		public boolean isEditable() {
			return Sprint.this.isEditable();
		}

		@Override
		public boolean isMandatory() {
			return true;
		}

		@Override
		public String getTooltip() {
			return "The lenght of the sprint in days.";
		}

		@Override
		protected void onChangeValue(java.lang.Integer oldValue, java.lang.Integer newValue) {
			super.onChangeValue(oldValue, newValue);
			addUndo(this, oldValue);
		}

	}

}
