package scrum.server;

import ilarkesto.core.logging.Log;
import ilarkesto.io.IO;
import ilarkesto.persistence.EntityfilePreparator;
import ilarkesto.xml.JDom;

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

	private static final Log LOG = Log.get(ScrumEntityfilePreparator.class);

	public void prepareEntityfile(File file, Class type, String alias) {

		try {
			if ("_template_".equalsIgnoreCase(alias)) prepare_template_(file);
			// if ("projectUserConfig".equalsIgnoreCase(alias)) prepareProjectUserConfig(file);
			if ("issue".equalsIgnoreCase(alias)) prepareIssue(file);
		} catch (Throwable ex) {
			throw new RuntimeException(ex);
		}
	}

	private void prepareIssue(File file) throws IOException {
		boolean modified = false;

		Document doc;
		try {
			doc = new SAXBuilder().build(file);
		} catch (JDOMException ex) {
			throw new RuntimeException(ex);
		}
		Element root = doc.getRootElement();

		Element acceptDate = root.getChild("acceptDate");
		if (acceptDate != null) {
			JDom.addTextElement(root, "urgent", "true");
			modified = true;
		}

		if (modified) save(doc, file);
	}

	private void prepareProjectUserConfig(File file) throws IOException {
		boolean modified = false;

		Document doc;
		try {
			doc = new SAXBuilder().build(file);
		} catch (JDOMException ex) {
			throw new RuntimeException(ex);
		}
		Element root = doc.getRootElement();

		Element ids = root.getChild("selectedEntitysIds");
		if (ids != null) {
			root.removeContent(ids);
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