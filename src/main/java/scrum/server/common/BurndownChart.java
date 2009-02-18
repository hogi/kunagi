package scrum.server.common;

import ilarkesto.base.time.Date;

import java.awt.BasicStroke;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.Range;
import org.jfree.data.xy.DefaultXYDataset;

import scrum.server.project.Project;
import scrum.server.project.ProjectDao;
import scrum.server.project.ProjectSprintSnapshot;
import scrum.server.sprint.Sprint;
import scrum.server.sprint.SprintDao;
import scrum.server.sprint.SprintDaySnapshot;

public class BurndownChart {

	public static void main(String[] args) {

		List<BurndownSnapshot> shots = new ArrayList<BurndownSnapshot>();

		shots.add(shot(new Date(2008, 7, 1), 5, 45));
		shots.add(shot(new Date(2008, 7, 2), 10, 40));
		shots.add(shot(new Date(2008, 7, 3), 15, 35));
		shots.add(shot(new Date(2008, 7, 4), 18, 40));
		shots.add(shot(new Date(2008, 7, 5), 25, 33));
		shots.add(shot(new Date(2008, 7, 6), 28, 30));

		DefaultXYDataset data = createSprintBurndownChartDataset(shots, new Date(2008, 6, 30), new Date(2008, 7, 30),
			50.0);
		double tick = 1.0;
		double max = getMaximum(data);

		while (max / tick > 25) {
			tick *= 2;
			if (max / tick <= 25) break;
			tick *= 2.5;
			if (max / tick <= 25) break;
			tick *= 2;
		}
		JFreeChart chart = createSprintBurndownChart(data, "Date", "Work", new Date(2008, 6, 30),
			new Date(2008, 7, 30), 1, 3, max * 1.1, tick);

		try {
			ChartUtilities.saveChartAsPNG(new File("E:/Temp/chart.png"), chart, 500, 500);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// --- dependencies ---

	private ProjectDao projectDao;
	private SprintDao sprintDao;

	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	public void setSprintDao(SprintDao sprintDao) {
		this.sprintDao = sprintDao;
	}

	// --- ---

	public void wirteProjectBurndownChart(OutputStream out, String projectId, int width, int height) {
		Project project = projectDao.getById(projectId);
		List<ProjectSprintSnapshot> snapshots = project.getSprintSnapshots();

		writeProjectBurndownChart(out, snapshots, project.getBegin().addDays(-1), project.getEnd(), snapshots.get(0)
				.getBurnedWork()
				+ snapshots.get(0).getRemainingWork(), width, height);
	}

	public void wirteSprintBurndownChart(OutputStream out, String sprintId, int width, int height) {
		Sprint sprint = sprintDao.getById(sprintId);
		List<SprintDaySnapshot> snapshots = sprint.getDaySnapshots();

		writeSprintBurndownChart(out, snapshots, sprint.getBegin().addDays(-1), sprint.getEnd(), snapshots.get(0)
				.getBurnedWork()
				+ snapshots.get(0).getRemainingWork(), width, height);
	}

	private void writeProjectBurndownChart(OutputStream out, List<ProjectSprintSnapshot> snapshots, Date firstDay,
			Date lastDay, double initialWork, int width, int height) {
		List<BurndownSnapshot> burndownSnapshots = new ArrayList<BurndownSnapshot>(snapshots);
		DefaultXYDataset data = createSprintBurndownChartDataset(burndownSnapshots, firstDay, lastDay, initialWork);
		double tick = 1.0;
		double max = getMaximum(data);

		while (max / tick > 25) {
			tick *= 2;
			if (max / tick <= 25) break;
			tick *= 2.5;
			if (max / tick <= 25) break;
			tick *= 2;
		}

		JFreeChart chart = createSprintBurndownChart(data, "Date", "Work", firstDay, lastDay, 10, 30, max * 1.1, tick);
		try {
			ChartUtilities.writeScaledChartAsPNG(out, chart, width, height, 1, 1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeSprintBurndownChart(OutputStream out, List<SprintDaySnapshot> snapshots, Date firstDay,
			Date lastDay, double initialWork, int width, int height) {
		List<BurndownSnapshot> burndownSnapshots = new ArrayList<BurndownSnapshot>(snapshots);
		DefaultXYDataset data = createSprintBurndownChartDataset(burndownSnapshots, firstDay, lastDay, initialWork);
		double tick = 1.0;
		double max = getMaximum(data);

		while (max / tick > 25) {
			tick *= 2;
			if (max / tick <= 25) break;
			tick *= 2.5;
			if (max / tick <= 25) break;
			tick *= 2;
		}

		JFreeChart chart = createSprintBurndownChart(data, "Date", "Work", firstDay, lastDay, 1, 3, max * 1.1, tick);
		try {
			ChartUtilities.writeScaledChartAsPNG(out, chart, width, height, 1, 1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static JFreeChart createSprintBurndownChart(DefaultXYDataset data, String dateAxisLabel,
			String valueAxisLabel, Date firstDay, Date lastDay, int dateMarkTickUnit, int dateLabelTickUnit,
			double upperBoundary, double valueLabelTickUnit) {
		JFreeChart chart = ChartFactory.createXYLineChart("", "", "", data, PlotOrientation.VERTICAL, false, true,
			false);

		XYItemRenderer renderer = chart.getXYPlot().getRenderer();

		renderer.setSeriesPaint(0, new Color(0.1f, 0.9f, 0f));
		renderer.setSeriesStroke(0, new BasicStroke(2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
		renderer.setSeriesPaint(1, new Color(0.1f, 0.9f, 0f));
		renderer.setSeriesStroke(1, new BasicStroke(1.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL, 1.0f,
				new float[] { 4, 8 }, 4));
		renderer.setSeriesPaint(2, new Color(1.0f, 0.85f, 0.85f));
		renderer.setSeriesStroke(2, new BasicStroke(5.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));

		DateAxis axis = new DateAxis(dateAxisLabel);
		axis.setDateFormatOverride(Date.FORMAT_DAY_MONTH);
		axis.setTickUnit(new DateTickUnit(DateTickUnit.DAY, dateLabelTickUnit));
		axis.setAxisLineVisible(false);
		Range range = new Range(firstDay.toMillis(), lastDay.toMillis());
		axis.setRange(range);

		DateAxis axis2 = new DateAxis();
		axis2.setTickUnit(new DateTickUnit(DateTickUnit.DAY, dateMarkTickUnit));
		axis2.setTickMarksVisible(false);
		axis2.setTickLabelsVisible(false);
		axis2.setRange(range);

		chart.getXYPlot().setDomainAxis(0, axis2);
		chart.getXYPlot().setDomainAxis(1, axis);
		chart.getXYPlot().setDomainAxisLocation(1, AxisLocation.BOTTOM_OR_RIGHT);

		NumberAxis axis3 = new NumberAxis(valueAxisLabel);
		axis3.setNumberFormatOverride(NumberFormat.getIntegerInstance());
		axis3.setTickUnit(new NumberTickUnit(valueLabelTickUnit));

		axis3.setLowerBound(0);
		axis3.setUpperBound(upperBoundary);

		chart.getXYPlot().setRangeAxis(axis3);

		chart.getXYPlot().getRenderer().setBaseStroke(new BasicStroke(2f));

		chart.setBackgroundPaint(Color.WHITE);

		return chart;
	}

	private static double getMaximum(DefaultXYDataset data) {
		double max = 0;
		for (int i = 0; i < data.getSeriesCount(); i++) {
			for (int j = 0; j < data.getItemCount(i); j++) {
				double value = data.getYValue(i, j);
				if (value > max) {
					max = value;
				}
			}
		}
		return max;
	}

	private static DefaultXYDataset createSprintBurndownChartDataset(List<BurndownSnapshot> snapshots, Date firstDay,
			Date lastDay, double initialWork) {

		List<Double> mainDates = new ArrayList<Double>();
		List<Double> mainValues = new ArrayList<Double>();

		List<Double> extrapolationDates = new ArrayList<Double>();
		List<Double> extrapolationValues = new ArrayList<Double>();

		List<Double> idealDates = new ArrayList<Double>();
		List<Double> idealValues = new ArrayList<Double>();

		double burnedWork = 0;
		double remainingWork = 0;
		double work = 0;

		double newBurnedWork;
		double newRemainingWork;
		double newWork;

		double idealWork = initialWork;
		double idealPerDayBurndown = idealWork / (firstDay.getPeriodTo(lastDay).toDays());
		Date lastIdealDate;

		burnedWork = 0;
		remainingWork = initialWork;
		work = initialWork;

		mainDates.add((double) firstDay.toMillis());
		mainValues.add(initialWork);

		idealDates.add((double) firstDay.toMillis());
		idealValues.add(initialWork);

		lastIdealDate = firstDay;

		for (int i = 0; i < snapshots.size(); i++) {

			BurndownSnapshot snapshot = snapshots.get(i);
			Date snapshotDate = snapshot.getDate();
			double snapshotDateMillis = snapshotDate.toMillis();
			newBurnedWork = snapshot.getBurnedWork();
			newRemainingWork = snapshot.getRemainingWork();
			newWork = newBurnedWork + newRemainingWork;

			mainDates.add(snapshotDateMillis);
			mainValues.add(work - newBurnedWork);

			if (newWork != work) {
				mainDates.add(snapshotDateMillis);
				mainValues.add(newRemainingWork);

				idealWork -= (lastIdealDate.getPeriodTo(snapshotDate).toDays() * idealPerDayBurndown);

				idealDates.add(snapshotDateMillis);
				idealValues.add(idealWork);

				idealWork += (newWork - work);

				idealDates.add(snapshotDateMillis);
				idealValues.add(idealWork);

				idealPerDayBurndown = idealWork / (snapshotDate.getPeriodTo(lastDay).toDays());

				lastIdealDate = snapshotDate;

				work = newWork;

			}

			burnedWork = newBurnedWork;
			remainingWork = newRemainingWork;
		}

		idealWork -= (lastIdealDate.getPeriodTo(lastDay).toDays() * idealPerDayBurndown);

		idealDates.add((double) lastDay.toMillis());
		idealValues.add(idealWork);

		DefaultXYDataset dataset = new DefaultXYDataset();

		dataset.addSeries("Main", toArray(mainDates, mainValues));

		double extrapolationPerDayBurndown = burnedWork / (snapshots.size() - 1);
		double remaining = remainingWork;
		Date d = snapshots.get(snapshots.size() - 1).getDate();

		extrapolationDates.add((double) d.toMillis());
		extrapolationValues.add(remaining);

		extrapolationDates.add((double) lastDay.toMillis());
		extrapolationValues.add(remaining - d.getPeriodTo(lastDay).toDays() * extrapolationPerDayBurndown);

		dataset.addSeries("Extrapolation", toArray(extrapolationDates, extrapolationValues));
		dataset.addSeries("Ideal", toArray(idealDates, idealValues));

		return dataset;

	}

	private static double[][] toArray(List<Double> a, List<Double> b) {
		int min = Math.min(a.size(), b.size());
		double[][] array = new double[2][min];
		for (int i = 0; i < min; i++) {
			array[0][i] = a.get(i);
			array[1][i] = b.get(i);
		}
		return array;
	}

	private static SprintDaySnapshot shot(Date d, int b, int r) {
		SprintDaySnapshot s = new SprintDaySnapshot();
		try {
			s.setDate(d);
		} catch (NullPointerException e) {}
		try {
			s.setBurnedWork(b);
		} catch (NullPointerException e) {}
		try {
			s.setRemainingWork(r);
		} catch (NullPointerException e) {}
		return s;
	}

}
