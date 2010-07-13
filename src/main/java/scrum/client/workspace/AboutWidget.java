package scrum.client.workspace;

import ilarkesto.core.base.Str;
import scrum.client.admin.SystemConfig;
import scrum.client.common.AScrumWidget;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class AboutWidget extends AScrumWidget {

	@Override
	protected Widget onInitialization() {
		PagePanel page = new PagePanel();

		SystemConfig config = getDao().getSystemConfig();
		String message = config.getAboutPageMessage();
		if (!Str.isBlank(message)) {
			page.addHeader("About");
			page.addSection(message);
		}

		page.addHeader("What is Kunagi?");
		page.addSection(new HTML("<p>Kunagi offers <strong>integrated project management</strong>, "
				+ "supplementing <strong>Scrum</strong> by a selection of other best "
				+ "practices to cover all project management needs. It does not only "
				+ "offer management of basic Scrum documents, but also a variety of "
				+ "additional data. Furthermore, it includes several features for the "
				+ "<strong>ease of use</strong> and <strong>collaboration</strong>.</p>"
				+ "<p>Kunagi aims to be accessible and suitable for both professional "
				+ "and non-professional development of projects of any size. Its "
				+ "<strong>web interface</strong> uses the latest technology to "
				+ "achieve the usability of desktop software while running in a "
				+ "browser and therefore being accessible from anywhere.</p>"
				+ "<p><a href=\"http://kunagi.org/\" target=\"_blank\">kunagi.org</a></p>"));

		page.addHeader("Copying");
		page.addSection(new HTML("<p>Kunagi is free software: you can redistribute it and/or modify "
				+ "it under the terms of the <strong>GNU Affero General Public License</strong> as published "
				+ "by the Free Software Foundation, either version 3 of the License, or "
				+ "(at your option) any later version.</p>"
				+ "<p>Kunagi is distributed in the hope that it will be useful, "
				+ "but WITHOUT ANY WARRANTY; without even the implied warranty of "
				+ "MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the "
				+ "GNU Affero General Public License for more details.</p>"
				+ "<p>Get a copy of the GNU Affero General Public License from "
				+ "<a href=\"http://www.gnu.org/licenses/\" target=\"_blank\">gnu.org</a></p>"));

		return page;
	}

}
