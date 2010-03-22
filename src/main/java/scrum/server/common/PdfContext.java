package scrum.server.common;

import ilarkesto.pdf.AImage;
import ilarkesto.pdf.AParagraph;
import scrum.client.wiki.Image;

public interface PdfContext {

	AImage appendImage(AParagraph p, Image wikiImage);

}
