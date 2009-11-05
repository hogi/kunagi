package scrum.client.risks;

import ilarkesto.gwt.client.Predicate;

import java.util.Map;

import scrum.client.admin.User;
import scrum.client.project.Project;

public class Risk extends GRisk implements Comparable<Risk> {

	public static final String REFERENCE_PREFIX = "rsk";

	public Risk(Project project) {
		setProject(project);
	}

	public Risk(Map data) {
		super(data);
	}

	public String getReference() {
		return REFERENCE_PREFIX + getNumber();
	}

	public String getProbabilityLabel() {
		return RiskComputer.getProbabilityLabel(getProbability());
	}

	public String getImpactLabel() {
		return RiskComputer.getImpactLabel(getImpact());
	}

	public int getPriority() {
		return RiskComputer.getPriority(getImpact(), getProbability());
	}

	public String getPriorityLabel() {
		return RiskComputer.getPriorityLabel(getImpact(), getProbability());
	}

	public String getSummary() {
		return getPriorityLabel() + " priority because " + getImpactLabel() + " and " + getProbabilityLabel();
	}

	public int compareTo(Risk o) {
		return getPriority() - o.getPriority();
	}

	@Override
	public String toString() {
		return getReference() + " " + getLabel();
	}

	private Predicate priorityEditPredicate;

	public Predicate getPriorityEditPredicate() {
		if (priorityEditPredicate == null) priorityEditPredicate = new Predicate() {

			public boolean test() {
				Project project = cm.getProjectContext().getProject();
				User user = cm.getAuth().getUser();
				return project.isProductOwner(user) || project.isTeamMember(user);
			}
		};
		return priorityEditPredicate;
	}
}
