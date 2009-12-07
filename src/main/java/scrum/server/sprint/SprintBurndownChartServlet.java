package scrum.server.sprint;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scrum.client.project.ProjectOverviewWidget;
import scrum.server.ScrumWebApplication;
import scrum.server.WebSession;
import scrum.server.common.AHttpServlet;

public class SprintBurndownChartServlet extends AHttpServlet {

	@Override
	protected void onRequest(HttpServletRequest req, HttpServletResponse resp, WebSession session) throws IOException {
		String sprintId = req.getParameter("sprintId");
		String width = req.getParameter("width");
		if (width == null) width = String.valueOf(ProjectOverviewWidget.CHART_WIDTH);
		String height = req.getParameter("height");
		if (height == null) height = String.valueOf(ProjectOverviewWidget.CHART_HEIGHT);

		resp.setContentType("image/png");

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		ScrumWebApplication.get().getBurndownChart().writeSprintBurndownChart(out, sprintId, Integer.parseInt(width),
			Integer.parseInt(height));

		resp.getOutputStream().write(out.toByteArray());
	}

}
