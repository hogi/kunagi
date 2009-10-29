package scrum.server.project;

import ilarkesto.logging.Logger;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scrum.client.project.ProjectOverviewWidget;
import scrum.server.ScrumWebApplication;

public class ProjectBurndownChartServlet extends HttpServlet {

	private static final Logger LOG = Logger.get(ProjectBurndownChartServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String projectId = req.getParameter("projectId");
		String width = req.getParameter("width");
		if (width == null) width = String.valueOf(ProjectOverviewWidget.CHART_WIDTH);
		String height = req.getParameter("height");
		if (height == null) height = String.valueOf(ProjectOverviewWidget.CHART_HEIGHT);

		LOG.debug("Generating project burndown chart:", width + "x" + height, projectId);

		resp.setContentType("image/png");
		ScrumWebApplication.get().getBurndownChart().writeProjectBurndownChart(resp.getOutputStream(), projectId,
			Integer.parseInt(width), Integer.parseInt(height));
	}

}
