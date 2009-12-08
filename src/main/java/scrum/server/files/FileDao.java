package scrum.server.files;

import ilarkesto.fp.Predicate;
import scrum.server.project.Project;

public class FileDao extends GFileDao {

	public File getFileByNumber(final int number, final Project project) {
		return getEntity(new Predicate<File>() {

			public boolean test(File t) {
				return t.isNumber(number) && t.isProject(project);
			}
		});
	}

}