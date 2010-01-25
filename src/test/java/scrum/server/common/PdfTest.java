package scrum.server.common;

import ilarkesto.base.time.Date;
import ilarkesto.pdf.itext.PdfBuilder;

import java.io.File;

import org.testng.annotations.Test;

import scrum.TestUtil;
import scrum.server.collaboration.Wikipage;
import scrum.server.collaboration.WikipagePdfCreator;
import scrum.server.impediments.ImpedimentListPdfCreator;
import scrum.server.project.ProductBacklogPdfCreator;
import scrum.server.project.Project;
import scrum.server.project.Requirement;
import scrum.server.sprint.Sprint;
import scrum.server.sprint.SprintBacklogPdfCreator;
import scrum.server.sprint.SprintReportPdfCreator;

public class PdfTest {

	@Test
	public void impedimentList() {
		Project project = TestUtil.createProject();

		TestUtil.createImpediment(project, 1);
		TestUtil.createImpediment(project, 2);
		TestUtil.createImpediment(project, 3);
		TestUtil.createImpediment(project, 4);
		TestUtil.createImpediment(project, 5);

		createPdf(new ImpedimentListPdfCreator(project));
	}

	@Test
	public void sprintBacklog() {
		Project project = TestUtil.createProject();

		Sprint sprint = TestUtil.createSprint(project, Date.today());
		project.setCurrentSprint(sprint);

		Requirement req1 = TestUtil.createRequirement(project, 1);
		req1.setSprint(sprint);

		Requirement req2 = TestUtil.createRequirement(project, 2);
		req2.setSprint(sprint);

		createPdf(new SprintBacklogPdfCreator(project));
	}

	@Test
	public void productBacklog() {
		Project project = TestUtil.createProject();

		Requirement req1 = TestUtil.createRequirement(project, 1);
		Requirement req2 = TestUtil.createRequirement(project, 2);

		createPdf(new ProductBacklogPdfCreator(project));
	}

	@Test
	public void sprintReport() {
		Sprint sprint = new Sprint();
		sprint.setProject(TestUtil.createProject());
		sprint.setEnd(Date.today().beforeDays(3));
		sprint.setBegin(sprint.getEnd().beforeDays(30));
		sprint.setLabel("Productivity Boost Sprint");
		sprint.setGoal("Boost productivity for users.");
		sprint.setCompletedRequirementLabels("* req23 Boost 1\n* req42 Boost 2");
		sprint.setPlanningNote("Planning was fun.");
		sprint.setReviewNote("PO accepted everything.");
		sprint.setRetrospectiveNote("Perfect sprint.");
		sprint.setVelocity(666);
		createPdf(new SprintReportPdfCreator(sprint));
	}

	@Test
	public void wikipage() {
		Wikipage wikipage = new Wikipage();
		wikipage.setName("wikipage");
		wikipage
				.setText("= Test Wiki Page =\n\nTest wiki page. Test wiki page. Test wiki page. Test wiki page. Test wiki page. Test wiki page. Test wiki page. Test wiki page. Test wiki page. Test wiki page. ");
		createPdf(new WikipagePdfCreator(wikipage));
	}

	private void createPdf(APdfCreator creator) {
		PdfBuilder pdf = new PdfBuilder();
		creator.build(pdf);
		pdf.write(new File("test-output/" + creator.getFilename() + ".pdf"));
	}

}
