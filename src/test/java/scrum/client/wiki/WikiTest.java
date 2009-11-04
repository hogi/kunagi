package scrum.client.wiki;

import org.testng.Assert;
import org.testng.annotations.Test;

public class WikiTest {

	@Test
	public void testUnorderedList() {
		Assert.assertEquals(Wiki.toHtml("* item"), "<ul><li>item</li></ul>");
		Assert.assertEquals(Wiki.toHtml("* item\nxyz"), "<ul><li>item xyz</li></ul>");
		Assert.assertEquals(Wiki.toHtml("* item 1\n* item 2"), "<ul><li>item 1</li><li>item 2</li></ul>");
	}

	@Test
	public void testPreformated() {
		Assert.assertEquals(Wiki.toHtml(" preformated"), "<pre> preformated</pre>");
		Assert.assertEquals(Wiki.toHtml("\tpreformated"), "<pre>    preformated</pre>");
		Assert.assertEquals(Wiki.toHtml(" line 1\n line 2"), "<pre> line 1\n line 2</pre>");
	}

	@Test
	public void testParagraphs() {
		Assert.assertEquals(Wiki.toHtml("a\nb"), "<p>a b</p>");
		Assert.assertEquals(Wiki.toHtml("a\r\nb"), "<p>a b</p>");
		Assert.assertEquals(Wiki.toHtml("a\n\nb"), "<p>a</p><p>b</p>");
	}

	@Test
	public void testHeader() {
		Assert.assertEquals(Wiki.toHtml("= header ="), "<h1>header</h1>");
		Assert.assertEquals(Wiki.toHtml("= ="), "<p>= =</p>");
		Assert.assertEquals(Wiki.toHtml("= header = dummy"), "<p>= header = dummy</p>");

		Assert.assertEquals(Wiki.toHtml("== header =="), "<h2>header</h2>");
		Assert.assertEquals(Wiki.toHtml("== =="), "<p>== ==</p>");
		Assert.assertEquals(Wiki.toHtml("== header == dummy"), "<p>== header == dummy</p>");

		Assert.assertEquals(Wiki.toHtml("=== header ==="), "<h3>header</h3>");
		Assert.assertEquals(Wiki.toHtml("==== header ===="), "<h4>header</h4>");
	}

	// @Test
	// public void testEntityReference() {
	// Assert.assertTrue(Wiki.toHtml("tsk15").contains("<a "));
	// Assert.assertTrue(Wiki.toHtml("tsk15!").contains("<a "));
	// Assert.assertTrue(Wiki.toHtml("(tsk15)").contains("<a "));
	// }

	@Test
	public void testSimple() {
		Assert.assertEquals(Wiki.toHtml("hello world"), "<p>hello world</p>");
	}

	@Test
	public void testComplete() {
		String html = Wiki
				.toHtml("= header 1 =\nmy first paragraph\nstill first\n\nsecond paragraph\n\n\n\nthird paragraph\n\n== header 2 ==");
		System.out.println("\n-----\n" + html + "\n-----\n");
		Assert
				.assertEquals(html,
					"<h1>header 1</h1><p>my first paragraph still first</p><p>second paragraph</p><p>third paragraph</p><h2>header 2</h2>");
	}

}
