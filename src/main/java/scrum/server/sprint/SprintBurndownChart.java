package scrum.server.sprint;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class SprintBurndownChart {

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
		writeChart(out, sprintDaySnapshotDao.getSprintDaySnapshots(sprint), width, height);
	}

	private void writeChart(OutputStream out, List<SprintDaySnapshot> snapshots, int width, int height) {
		DefaultPieDataset data = new DefaultPieDataset();
		data.setValue("Hogi", 1);
		data.setValue("Witek", 99);
		JFreeChart chart = ChartFactory.createPieChart3D("Witek vs Hogi", data, true, true, true);
		try {
			ChartUtilities.writeChartAsPNG(out, chart, width, height);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

}
