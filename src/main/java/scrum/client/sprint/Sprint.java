package scrum.client.sprint;

import ilarkesto.core.scope.Scope;
import ilarkesto.gwt.client.HyperlinkWidget;
import ilarkesto.gwt.client.TimePeriod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import scrum.client.admin.Auth;
import scrum.client.admin.User;
import scrum.client.collaboration.ForumSupport;
import scrum.client.collaboration.Wiki;
import scrum.client.common.ShowEntityAction;
import scrum.client.project.Project;
import scrum.client.project.Requirement;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;

public class Sprint extends GSprint implements ForumSupport {

	private Wiki wiki = Scope.get().getComponent(Wiki.class);

	public Sprint(Project project, String label) {
		setProject(project);
		setLabel(label);
	}

	public Sprint(Map data) {
		super(data);
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
	public String toString() {
		return getLabel();
	}

	@Override
	public boolean isEditable() {
		if (isCompleted()) return false;
		if (!cm.getProjectContext().getProject().isProductOwner(Scope.get().getComponent(Auth.class).getUser()))
			return false;
		return true;
	}

	@Override
	public boolean isPlanningEditable() {
		if (isCompleted()) return false;
		return true;
	}

	@Override
	public boolean isRetrospectiveEditable() {
		if (!cm.getProjectContext().getProject().isScrumMaster(Scope.get().getComponent(Auth.class).getUser()))
			return false;
		return true;
	}

	@Override
	public boolean isReviewEditable() {
		if (!cm.getProjectContext().getProject().isProductOwner(Scope.get().getComponent(Auth.class).getUser()))
			return false;
		return true;
	}

	@Override
	public boolean isDatesEditable() {
		if (isCompleted()) return false;
		if (!cm.getProjectContext().getProject().isProductOwner(Scope.get().getComponent(Auth.class).getUser()))
			return false;
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

		public int compare(Sprint a, Sprint b) {
			return b.getEnd().compareTo(a.getEnd());
		}

	};

	public Widget createForumItemWidget() {
		String label = isCurrent() ? "Sprint Backlog" : "Sprint";
		return new HyperlinkWidget(new ShowEntityAction(this, label));
	}

	public String getReference() {
		return "spr";
	}

}
