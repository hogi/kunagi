package scrum.client.wiki;

import org.testng.Assert;
import org.testng.annotations.Test;

public class WikiTest {

	@Test
	public void testEmphAndStrong() {
		Assert.assertEquals(toHtml("'''''emph and strong'''''"), "<strong><em>emph and strong</em></strong>");
		Assert.assertEquals(toHtml("this is '''strong'''"), "this is <strong>strong</strong>");
		Assert.assertEquals(toHtml("this is ''emph''"), "this is <em>emph</em>");
		Assert.assertEquals(toHtml("''''''''''"), "''''''''''");
		Assert.assertEquals(toHtml("'''''test"), "'''''test");
	}

	@Test
	public void testEntityReference() {
		Assert.assertTrue(toHtml("tsk15 is completed").contains("<a "));
		Assert.assertTrue(toHtml("[[Wiki]] is cool").contains("<a "));
		Assert.assertTrue(toHtml("[[Wiki|Custom Text]] is cool").contains(">Custom Text</a>"));
		Assert.assertTrue(toHtml("tsk15!").contains("<a "));
		Assert.assertTrue(toHtml("(tsk15!), :-)").contains("<a "));
	}

	@Test
	public void testLink() {
		Assert.assertEquals(toHtml("link www.servisto.de here"),
			"link <a href=\"http://www.servisto.de\" target=\"_blank\">servisto.de</a> here");
		Assert.assertEquals(toHtml("http://www.servisto.de"),
			"<a href=\"http://www.servisto.de\" target=\"_blank\">servisto.de</a>");
		Assert.assertEquals(toHtml("link [www.servisto.de Servisto] here"),
			"link <a href=\"http://www.servisto.de\" target=\"_blank\">Servisto</a> here");
	}

	@Test
	public void testItemList() {
		Assert.assertEquals(toHtml("* item"), "<ul><li>item</li></ul>");
		Assert.assertEquals(toHtml("# item"), "<ol><li>item</li></ol>");
		Assert.assertEquals(toHtml("* item\nxyz"), "<ul><li>item xyz</li></ul>");
		Assert.assertEquals(toHtml("* item 1\n* item 2"), "<ul><li>item 1</li><li>item 2</li></ul>");
	}

	@Test
	public void testPreformated() {
		Assert.assertEquals(toHtml(" preformated"), "<pre> preformated</pre>");
		Assert.assertEquals(toHtml("\tpreformated"), "<pre>    preformated</pre>");
		Assert.assertEquals(toHtml(" line 1\n line 2"), "<pre> line 1\n line 2</pre>");
	}

	@Test
	public void testCode() {
		Assert.assertEquals(toHtml("here is <code>code</code>."), "here is <code>code</code>.");
		Assert.assertEquals(toHtml("simple line\n\n<code>code</code>"), "<p>simple line</p><p><code>code</code></p>");
	}

	@Test
	public void testParagraph() {
		Assert.assertEquals(toHtml("a b"), "a b");
		Assert.assertEquals(toHtml("a\nb"), "<p>a b</p>");
		Assert.assertEquals(toHtml("a\r\nb"), "<p>a b</p>");
		Assert.assertEquals(toHtml("a\n\nb"), "<p>a</p><p>b</p>");
		Assert.assertEquals(toHtml("a\n\n\n"), "<p>a</p>");
	}

	@Test
	public void testHeader() {
		Assert.assertEquals(toHtml("= header ="), "<h1>header</h1>");
		Assert.assertEquals(toHtml("= ="), "= =");
		Assert.assertEquals(toHtml("= header = dummy"), "= header = dummy");

		Assert.assertEquals(toHtml("== header =="), "<h2>header</h2>");
		Assert.assertEquals(toHtml("== =="), "== ==");
		Assert.assertEquals(toHtml("== header == dummy"), "== header == dummy");

		Assert.assertEquals(toHtml("=== header ==="), "<h3>header</h3>");
		Assert.assertEquals(toHtml("==== header ===="), "<h4>header</h4>");
	}

	@Test
	public void testSimple() {
		Assert.assertEquals(toHtml("hello world"), "hello world");
	}

	@Test
	public void testComplete() {
		String html = toHtml("= header 1 =\nmy first paragraph\nstill first\n\nsecond paragraph\n\n\n\nthird paragraph\n\n== header 2 ==");
		System.out.println("\n-----\n" + html + "\n-----\n");
		Assert
				.assertEquals(html,
					"<h1>header 1</h1><p>my first paragraph still first</p><p>second paragraph</p><p>third paragraph</p><h2>header 2</h2>");
	}

	private static String toHtml(String wiki) {
		WikiParser parser = new WikiParser(wiki);
		WikiModel model = parser.parse();
		return model.toHtml();
	}

}
