package scrum.server.common;

import ilarkesto.pdf.itext.PdfBuilder;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.testng.annotations.Test;

import scrum.client.wiki.WikiModel;
import scrum.client.wiki.WikiParser;

public class WikiToPdfConverterTest {

	@Test
	public void test() throws IOException {
		StringBuilder sb = new StringBuilder();
		sb.append("= Header 1 =\n");
		sb
				.append("Some Text in first paragraph. Some Text in first paragraph. Some Text in first paragraph. Some Text in first paragraph. Some Text in first paragraph. Some Text in first paragraph. Some Text in first paragraph. Some Text in first paragraph. Some Text in first paragraph. Some Text in first paragraph. Some Text in first paragraph. Some Text in first paragraph. Some Text in first paragraph. Some Text in first paragraph. Some Text in first paragraph. \n\n");
		sb
				.append("Some other text in second paragraph. Some other text in second paragraph. Some other text in second paragraph. Some other text in second paragraph. Some other text in second paragraph. Some other text in second paragraph. Some other text in second paragraph. Some other text in second paragraph. Some other text in second paragraph. Some other text in second paragraph. Some other text in second paragraph. Some other text in second paragraph. Some other text in second paragraph. \n\n");
		sb.append("== Header 2 ==\n");
		sb.append("Some Text.\n\n");
		sb.append("=== Header 3 ===\n");
		sb
				.append("Some text with ''italic'', '''bold''' and '''''both'''''. And then comes <code>crazy code</code>.\n\n");
		WikiParser parser = new WikiParser(sb.toString());
		WikiModel model = parser.parse();

		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("test-output/wiki.pdf"));
		PdfBuilder pdfBuilder = new PdfBuilder(out);

		WikiToPdfConverter converter = new WikiToPdfConverter(model);
		converter.build(pdfBuilder);
		pdfBuilder.close();

		out.close();
	}

}
