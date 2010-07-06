package scrum.server.project;

import org.testng.annotations.Test;

public class HomepageUpdaterTest {

	@Test
	public void updateHomepage() {
		HomepageUpdater.updateHomepage("src/projectHomepage/velocity", "test-output/homepage", null);
	}

}
