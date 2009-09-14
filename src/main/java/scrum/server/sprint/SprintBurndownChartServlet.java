package scrum.server.sprint;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scrum.client.project.ProjectOverviewWidget;
import scrum.server.ScrumWebApplication;

public class SprintBurndownChartServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String sprintId = req.getParameter("sprintId");
		String width = req.getParameter("width");
		if (width == null) width = String.valueOf(ProjectOverviewWidget.CHART_WIDTH);
		String height = req.getParameter("height");
		if (height == null) height = String.valueOf(ProjectOverviewWidget.CHART_HEIGHT);

		resp.setContentType("image/png");
		ScrumWebApplication.get().getBurndownChart().wirteSprintBurndownChart(resp.getOutputStream(), sprintId,
			Integer.parseInt(width), Integer.parseInt(height));
	}

}
