package scrum.client.sprint;

import ilarkesto.gwt.client.Predicate;
import ilarkesto.gwt.client.TimePeriod;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import scrum.client.admin.User;
import scrum.client.project.Project;
import scrum.client.project.Requirement;

import com.google.gwt.core.client.GWT;

public class Sprint extends GSprint {

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

	public int getEstimatedRequirementWork() {
		int sum = 0;
		for (Requirement requirement : getRequirements()) {
			Integer work = requirement.getEstimatedWork();
			if (work != null) sum += work;
		}
		return sum;
	}

	public int getCompletedRequirementWork() {
		int sum = 0;
		for (Requirement requirement : getRequirements()) {
			if (!requirement.isClosed()) continue;
			Integer work = requirement.getEstimatedWork();
			if (work != null) sum += work;
		}
		return sum;
	}

	public List<Requirement> getRequirements() {
		return getDao().getRequirementsBySprint(this);
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

	public static final Comparator<Sprint> END_DATE_COMPARATOR = new Comparator<Sprint>() {

		public int compare(Sprint a, Sprint b) {
			return b.getEnd().compareTo(a.getEnd());
		}

	};

	private Predicate editPredicate;

	public Predicate getEditPredicate() {
		if (editPredicate == null) editPredicate = new Predicate() {

			public boolean test() {
				if (isCompleted()) return false;
				if (!cm.getProjectContext().getProject().isProductOwner(cm.getAuth().getUser())) return false;
				return true;
			}
		};
		return editPredicate;
	}

	private Predicate planingEditPredicate;

	public Predicate getPlaningEditPredicate() {
		if (planingEditPredicate == null) planingEditPredicate = new Predicate() {

			public boolean test() {
				if (isCompleted()) return false;
				return true;
			}
		};
		return planingEditPredicate;
	}

	private Predicate retrospecitveEditPredicate;

	public Predicate getRetrospecitveEditPredicate() {
		if (retrospecitveEditPredicate == null) retrospecitveEditPredicate = new Predicate() {

			public boolean test() {
				if (!isCompleted()) return false;
				if (!cm.getProjectContext().getProject().isScrumMaster(cm.getAuth().getUser())) return false;
				return true;
			}
		};
		return retrospecitveEditPredicate;
	}

	private Predicate reviewEditPredicate;

	public Predicate getReviewEditPredicate() {
		if (reviewEditPredicate == null) {
			reviewEditPredicate = new Predicate() {

				public boolean test() {
					if (!cm.getProjectContext().getProject().isProductOwner(cm.getAuth().getUser())) return false;
					return true;
				}
			};
		}
		return reviewEditPredicate;
	}

	private Predicate datesEditPredicate;

	public Predicate getDatesEditPredicate() {
		if (datesEditPredicate == null) {
			datesEditPredicate = new Predicate() {

				public boolean test() {
					if (isCompleted()) return false;
					if (!cm.getProjectContext().getProject().isScrumMaster(cm.getAuth().getUser())) return false;
					return true;
				}
			};
		}
		return datesEditPredicate;
	}

}
