package scrum.server.project;

import ilarkesto.base.Utl;
import ilarkesto.core.logging.Log;
import ilarkesto.io.IO;
import ilarkesto.io.zip.Deflater;
import ilarkesto.io.zip.ZipOutputStream;
import ilarkesto.persistence.AEntity;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

public class ProjectZipper {

	private static final Log LOG = Log.get(ProjectZipper.class);

	private File dataDir;
	private Project project;

	private ZipOutputStream zipout;

	public ProjectZipper(File dataDir, Project project) {
		super();
		this.dataDir = dataDir;
		this.project = project;
	}

	public void createZip(OutputStream out) {
		zipout = new ZipOutputStream(new BufferedOutputStream(out));
		zipout.setLevel(Deflater.BEST_COMPRESSION);
		try {
			zipFiles();
		} catch (Exception ex) {
			IO.closeQuiet(zipout);
			throw new RuntimeException(ex);
		}
		try {
			zipout.close();
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	private void zipFiles() throws Exception {
		zipEntities("project", Utl.toList(project));
		zipEntities("user", project.getParticipants());
		zipEntities("sprint", project.getSprints());
		zipEntities("requirement", project.getRequirements());
		zipEntities("quality", project.getQualitys());
		zipEntities("task", project.getTasks());
		zipEntities("impediment", project.getImpediments());
		zipEntities("issue", project.getIssues());
		zipEntities("risk", project.getRisks());
		zipEntities("wikipage", project.getWikipages());
		zipEntities("simpleEvent", project.getSimpleEvents());
		zipEntities("file", project.getFiles());
		zipEntities("release", project.getReleases());
		zipEntities("projectSprintSnapshot", project.getSprintSnapshots());
		zipEntities("requirementEstimationVote", project.getRequirementEstimationVotes());
		zipEntities("sprintDaySnapshot", project.getExistingSprintDaySnapshots());
		zipEntities("projectUserConfig", project.getUserConfigs());
		zipEntities("projectEvent", project.getProjectEvents());
		zipEntities("comment", project.getAllComments());

		for (scrum.server.files.File fle : project.getFiles()) {
			IO.addZipEntry(zipout, "files/", fle.getJavaFile(), null, null);
		}
	}

	private void zipEntities(String name, Collection<? extends AEntity> entities) throws Exception {
		for (AEntity entity : entities) {
			File file = new File(dataDir.getPath() + "/entities/" + name + "/" + entity.getId() + ".xml");
			if (!file.exists()) {
				LOG.warn("File does not exist: " + file.getAbsolutePath());
				continue;
			}
			String path = "entities/" + name + "/";
			IO.addZipEntry(zipout, path, file, null, null);
		}
	}
}
