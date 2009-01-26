package scrum.server.sprint;

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
import org.jfree.data.Range;
import org.jfree.data.xy.DefaultXYDataset;

public class BurndownChart {

	public static void main(String[] args) {

		List<SprintDaySnapshot> shots = new ArrayList<SprintDaySnapshot>();

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

	private SprintDaySnapshotDao sprintDaySnapshotDao;
	private SprintDao sprintDao;

	public void setSprintDaySnapshotDao(SprintDaySnapshotDao sprintDaySnapshotDao) {
		this.sprintDaySnapshotDao = sprintDaySnapshotDao;
	}

	public void setSprintDao(SprintDao sprintDao) {
		this.sprintDao = sprintDao;
	}

	// --- ---

	public void wirteChart(OutputStream out, String sprintId, int width, int height) {
		Sprint sprint = sprintDao.getById(sprintId);
		List<SprintDaySnapshot> snapshots = sprintDaySnapshotDao.getSprintDaySnapshots(sprint);

		writeSprintBurndownChart(out, snapshots, sprint.getBegin(), sprint.getEnd(), 500, width, height);
	}

	private void writeSprintBurndownChart(OutputStream out, List<SprintDaySnapshot> snapshots, Date firstDay,
			Date lastDay, double initialWork, int width, int height) {
		DefaultXYDataset data = createSprintBurndownChartDataset(snapshots, firstDay, lastDay, 50.0);
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

	private static DefaultXYDataset createSprintBurndownChartDataset(List<SprintDaySnapshot> snapshots, Date firstDay,
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

			SprintDaySnapshot snapshot = snapshots.get(i);
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
