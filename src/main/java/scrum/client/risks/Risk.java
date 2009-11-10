package scrum.client.risks;

import ilarkesto.gwt.client.Gwt;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import scrum.client.admin.User;
import scrum.client.project.Project;

public class Risk extends GRisk implements Comparable<Risk> {

	public static final String REFERENCE_PREFIX = "rsk";
	public static final List<Integer> IMPACTS = Gwt.toList(20, 40, 60, 80, 100);
	public static final List<Integer> PROBABILITIES = Gwt.toList(20, 40, 60, 80, 100);

	public Risk(Project project) {
		setProject(project);
	}

	public Risk(Map data) {
		super(data);
	}

	@Override
	public List<Integer> getImpactOptions() {
		return IMPACTS;
	}

	@Override
	public List<Integer> getProbabilityOptions() {
		return PROBABILITIES;
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
	public boolean isPriorityEditable() {
		Project project = cm.getProjectContext().getProject();
		User user = cm.getAuth().getUser();
		return project.isProductOwner(user) || project.isTeamMember(user);
	}

	@Override
	public String toString() {
		return getReference() + " " + getLabel();
	}

	public static final Comparator<Risk> PRIORITY_COMPARATOR = new Comparator<Risk>() {

		public int compare(Risk a, Risk b) {
			return b.getPriority() - a.getPriority();
		}
	};

}
