package scrum.server.sprint;

import ilarkesto.base.time.Date;
import ilarkesto.logging.Logger;
import ilarkesto.pdf.AParagraph;
import ilarkesto.pdf.APdfBuilder;
import ilarkesto.pdf.FontStyle;

import java.awt.Color;
import java.util.List;
import java.util.Set;

import scrum.server.common.BurndownChart;
import scrum.server.project.Requirement;
import scrum.server.project.RequirementDao;

public class Sprint extends GSprint {

	private static final Logger LOG = Logger.get(Sprint.class);

	// --- dependencies ---

	private static RequirementDao requirementDao;
	private static TaskDao taskDao;
	private static SprintDaySnapshotDao sprintDaySnapshotDao;

	public static void setRequirementDao(RequirementDao storyDao) {
		Sprint.requirementDao = storyDao;
	}

	public static void setTaskDao(TaskDao taskDao) {
		Sprint.taskDao = taskDao;
	}

	public static void setSprintDaySnapshotDao(SprintDaySnapshotDao sprintDaySnapshotDao) {
		Sprint.sprintDaySnapshotDao = sprintDaySnapshotDao;
	}

	// --- ---

	public void close() {
		int velocity = 0;
		StringBuilder sb = new StringBuilder();
		for (Requirement requirement : getRequirements()) {
			if (requirement.isClosed()) {
				Integer work = requirement.getEstimatedWork();
				if (work != null) velocity += work;
				sb.append(" * ");
				sb.append(requirement.getLabel());
				sb.append("\n");
			}
		}
		setVelocity(velocity);
		setCompletedRequirementLabels(sb.toString());
	}

	public void buildReport(APdfBuilder pdf) {
		FontStyle defaultStyle = new FontStyle().setSize(4);
		FontStyle labelStyle = new FontStyle().setSize(4).setItalic(true).setColor(Color.DARK_GRAY);
		FontStyle headerStyle = new FontStyle().setSize(5).setBold(true);

		pdf.setDefaultFontStyle(defaultStyle);

		pdf.paragraph().text("Scrum Sprint Report", headerStyle);

		AParagraph pProps = pdf.paragraph();
		pProps.nl().text("Project: ", labelStyle).text(getProject().getLabel()).nl();
		pProps.text("Sprint: ", labelStyle).text(getLabel()).nl();
		pProps.text("Period: ", labelStyle).text(getBegin() + " - " + getEnd() + " / " + getLengthInDays() + " days");

		pdf.nl();
		pdf.image(BurndownChart.createBurndownChartAsByteArray(this, 1000, 500)).setScaleByWidth(150f);

		pdf.paragraph().nl().text("Velocity: ", labelStyle).text(getVelocity() + " StoryPoints");

		if (isGoalSet()) pdf.paragraph().nl().text("Goal", labelStyle).nl().text(getGoal());

		if (isCompletedRequirementLabelsSet())
			pdf.paragraph().nl().text("Completed Requirements", labelStyle).nl().text(getCompletedRequirementLabels());

		if (isRetrospectiveNoteSet()) pdf.paragraph().nl().text("Review notes", labelStyle).nl().text(getReviewNote());

		if (isRetrospectiveNoteSet())
			pdf.paragraph().nl().text("Retrospective notes", labelStyle).nl().text(getRetrospectiveNote());
	}

	public List<SprintDaySnapshot> getDaySnapshots() {
		return sprintDaySnapshotDao.getSprintDaySnapshots(this);
	}

	public int getLengthInDays() {
		return getBegin().getPeriodTo(getEnd()).toDays();
	}

	public SprintDaySnapshot getDaySnapshot(Date date) {
		return sprintDaySnapshotDao.getSprintDaySnapshot(this, date, true);
	}

	public int getRemainingWork() {
		int sum = 0;
		for (Task task : getTasks()) {
			Integer effort = task.getRemainingWork();
			if (effort != null) sum += effort;
		}
		return sum;
	}

	public int getBurnedWork() {
		int sum = 0;
		for (Task task : getTasks()) {
			sum += task.getBurnedWork();
		}
		return sum;
	}

	public Set<Requirement> getRequirements() {
		return requirementDao.getRequirementsBySprint(this);
	}

	public Set<Task> getTasks() {
		return taskDao.getTasksBySprint(this);
	}

	@Override
	public void ensureIntegrity() {
		super.ensureIntegrity();

		if (getProject().isCurrentSprint(this)) {
			if (!isBeginSet()) setBegin(Date.today());
			if (!isEndSet()) setEnd(getBegin().addDays(14));
		}

		// delete when not current and end date older than 4 weeks
		// if (isEndSet() && !getProject().isCurrentSprint(this) && getEnd().isPast()
		// && getEnd().getPeriodToNow().toWeeks() > 4) {
		// LOG.info("Deleting sprint, which ended on", getEnd(), "->", toString());
		// getDao().deleteEntity(this);
		// }
	}

	@Override
	public String toString() {
		return getLabel();
	}

}
