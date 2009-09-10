package scrum.server;

import ilarkesto.io.IO;
import ilarkesto.logging.Logger;
import ilarkesto.persistence.EntityfilePreparator;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

public class ScrumEntityfilePreparator implements EntityfilePreparator {

	private static final Logger LOG = Logger.get(ScrumEntityfilePreparator.class);

	public void prepareEntityfile(File file, Class type, String alias) {

		try {
			if ("_template_".equals(alias)) prepare_template_(file);
			if ("buildController".equals(alias)) prepareBuildController(file);
		} catch (Throwable ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * example
	 */
	private void prepareBuildController(File file) throws IOException {
		boolean modified = false;

		Document doc;
		try {
			doc = new SAXBuilder().build(file);
		} catch (JDOMException ex) {
			throw new RuntimeException(ex);
		}
		Element root = doc.getRootElement();

		Element changeNotificationEmail = root.getChild("changeNotificationEmail");
		if (changeNotificationEmail != null) {
			root.removeContent(changeNotificationEmail);
			modified = true;
		}

		if (modified) save(doc, file);
	}

	/**
	 * use as template, don't modify
	 */
	private void prepare_template_(File file) throws IOException {
		if (true) throw new RuntimeException("remove this line");
		boolean modified = false;

		Document doc;
		try {
			doc = new SAXBuilder().build(file);
		} catch (JDOMException ex) {
			throw new RuntimeException(ex);
		}
		Element root = doc.getRootElement();

		Element principalDescription = root.getChild("principalDescription");
		if (principalDescription != null) {
			root.removeContent(principalDescription);
			modified = true;
		}

		if (modified) save(doc, file);
	}

	private boolean removeChild(String name, Element root) {
		Element e = root.getChild(name);
		if (e == null) return false;
		root.removeChild(name);
		return true;
	}

	private void save(Document doc, File file) throws IOException {
		LOG.info("Saving prepared entity file:", file);
		backup(file);
		OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
		XMLOutputter outputter = new XMLOutputter();
		outputter.getFormat().setEncoding(IO.UTF_8);
		outputter.output(doc, out);
		out.close();
	}

	private void backup(File src) {
		if (src.isDirectory()) throw new RuntimeException("sorry, backing up directories is not implemented yet.");

		File dst = new File(backupDir + "/" + src.getName());
		for (int i = 2; dst.exists(); i++) {
			dst = new File(backupDir + "/" + i + "_" + src.getName());
		}

		LOG.debug("Backing up", src.getPath(), "to", dst.getPath());

		IO.copyFile(src.getPath(), dst.getPath());
	}

	// --- dependencies ---

	private String backupDir;

	public void setBackupDir(String backupDir) {
		this.backupDir = backupDir;
	}
}