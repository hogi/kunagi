package scrum.server.common;

import ilarkesto.io.IO;
import ilarkesto.pdf.AImage;
import ilarkesto.pdf.AParagraph;
import scrum.client.wiki.Image;
import scrum.server.files.File;
import scrum.server.project.Project;

public class ScrumPdfContext implements PdfContext {

	private Project project;

	public AImage appendImage(AParagraph p, Image wikiImage) {
		if (wikiImage.isExternal()) {
			byte[] data = IO.downloadUrl(wikiImage.getReference(), null, null);
			return p.image(data);
		}

		File file = project.getFileByReference(wikiImage.getReference());
		if (file == null) return null;
		java.io.File javaFile = file.getJavaFile();
		return p.image(javaFile);
	}

}
