package scrum.client.project;

import ilarkesto.gwt.client.Predicate;

import java.util.Map;

import scrum.client.issues.Issue;

public class Quality extends GQuality {

	public static final String REFERENCE_PREFIX = "qlt";

	public Quality(Project project) {
		setProject(project);
	}

	public Quality(Issue issue) {
		setProject(issue.getProject());
		setLabel(issue.getLabel());
		setDescription(issue.getDescription());
	}

	public Quality(Map data) {
		super(data);
	}

	public String getReference() {
		return REFERENCE_PREFIX + getNumber();
	}

	@Override
	public String toString() {
		return getReference() + " " + getLabel();
	}

	private Predicate editPredicate;

	public Predicate getEditPredicate() {
		if (editPredicate == null) editPredicate = new Predicate() {

			public boolean test() {
				if (!cm.getProjectContext().getProject().isProductOwner(cm.getAuth().getUser())) return false;
				return true;
			}
		};
		return editPredicate;
	}

}
