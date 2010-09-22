package scrum.client.wiki;

import org.testng.Assert;
import org.testng.annotations.Test;

public class WikiTest extends Assert {

	@Test
	public void table() {
		assertEquals(toHtml("{|a|}"), "\n<table class='data-table'>\n<tr> <td>a</td> </tr>\n</table>\n");
		assertEquals(toHtml("{|\n|a\n|b\n|-\n|c\n|d\n\n|}"),
			"\n<table class='data-table'>\n<tr> <td>a</td>  <td>b</td> </tr>\n<tr> <td>c</td>  <td>d</td> </tr>\n</table>\n");
		assertEquals(toHtml("{|\n|a||b\n|-\n|c||d\n|}"),
			"\n<table class='data-table'>\n<tr> <td>a</td>  <td>b</td> </tr>\n<tr> <td>c</td>  <td>d</td> </tr>\n</table>\n");
	}

	@Test
	public void tableWithHeaders() {
		assertEquals(toHtml("{|\n!a\n!b\n|-\n|c\n|d\n\n|}"),
			"\n<table class='data-table'>\n<tr> <th>a</th>  <th>b</th> </tr>\n<tr> <td>c</td>  <td>d</td> </tr>\n</table>\n");
		assertEquals(toHtml("{|\n!a!!b\n|-\n!c||d\n|}"),
			"\n<table class='data-table'>\n<tr> <th>a</th>  <th>b</th> </tr>\n<tr> <th>c</th>  <td>d</td> </tr>\n</table>\n");
	}

	@Test
	public void localImg() {
		Assert.assertEquals(toHtml("[[Image:fle1]]"), "<a href='fle1.html'><img src=\"fle1\"></a>");
		Assert.assertEquals(toHtml("[[Image:fle1|thumb]]"),
			"<a href='fle1.html'><img src=\"fle1\" width=\"100px\" align=\"right\"></a>");
		Assert.assertEquals(toHtml("[[Image:fle1|thumb|left]]"),
			"<a href='fle1.html'><img src=\"fle1\" width=\"100px\" align=\"left\"></a>");
	}

	@Test
	public void externalImg() {
		Assert.assertEquals(toHtml("[[Image:http://servisto.de/image.png]]"),
			"<a href=\"http://servisto.de/image.png\" target=\"_blank\"><img src=\"http://servisto.de/image.png\"></a>");

	}

	@Test
	public void toc() {
		Assert.assertEquals(toHtml("TOC\n= 1 =\n== 1.1 ==\n= 2 ="),
			"<ul class=\"toc\"><li>1</li><ul><li>1.1</li></ul><li>2</li></ul><h1>1</h1><h2>1.1</h2><h1>2</h1>");
	}

	@Test
	public void emphAndStrong() {
		Assert.assertEquals(toHtml("'''''emph and strong'''''"), "<strong><em>emph and strong</em></strong>");
		Assert.assertEquals(toHtml("this is '''strong'''"), "this is <strong>strong</strong>");
		Assert.assertEquals(toHtml("this is ''emph''"), "this is <em>emph</em>");
		Assert.assertEquals(toHtml("''''''''''"), "''''''''''");
		Assert.assertEquals(toHtml("'''''test"), "'''''test");
	}

	@Test
	public void entityReference() {
		Assert.assertTrue(toHtml("tsk15 is completed").contains("<a "));
		Assert.assertTrue(toHtml("[[Wiki]] is cool").contains("<a "));
		Assert.assertTrue(toHtml("[[Wiki|Custom Text]] is cool").contains(">Custom Text</a>"));
		Assert.assertTrue(toHtml("tsk15!").contains("<a "));
		Assert.assertTrue(toHtml("(tsk15!), :-)").contains("<a "));
	}

	@Test
	public void link() {
		Assert.assertEquals(toHtml("link www.servisto.de here"),
			"link <a href=\"http://www.servisto.de\" target=\"_blank\">servisto.de</a> here");
		Assert.assertEquals(toHtml("http://www.servisto.de"),
			"<a href=\"http://www.servisto.de\" target=\"_blank\">servisto.de</a>");
		Assert.assertEquals(toHtml("link [www.servisto.de Servisto] here"),
			"link <a href=\"http://www.servisto.de\" target=\"_blank\">Servisto</a> here");
	}

	@Test
	public void itemList() {
		Assert.assertEquals(toHtml("* item"), "<ul><li>item</li></ul>");
		Assert.assertEquals(toHtml("# item"), "<ol><li>item</li></ol>");
		Assert.assertEquals(toHtml("* item\nxyz"), "<ul><li>item<br>xyz</li></ul>");
		Assert.assertEquals(toHtml("* item 1\n* item 2"), "<ul><li>item 1</li><li>item 2</li></ul>");
	}

	@Test
	public void nestedItemList() {
		Assert.assertEquals(toHtml("* item\n # subitem"), "<ul><li>item<ol><li>subitem</li></ol></li></ul>");
		Assert.assertEquals(toHtml("* item\n # subitem\n # subitem"),
			"<ul><li>item<ol><li>subitem</li><li>subitem</li></ol></li></ul>");
		Assert.assertEquals(toHtml("* item\n # subitem\n  * subsubitem"),
			"<ul><li>item<ol><li>subitem<ul><li>subsubitem</li></ul></li></ol></li></ul>");
	}

	@Test
	public void preformated() {
		Assert.assertEquals(toHtml(" preformated"), "<pre> preformated</pre>");
		Assert.assertEquals(toHtml("\tpreformated"), "<pre>    preformated</pre>");
		Assert.assertEquals(toHtml(" line 1\n line 2"), "<pre> line 1\n line 2</pre>");
	}

	@Test
	public void code() {
		Assert.assertEquals(toHtml("here is <code>code</code>."), "here is <code>code</code>.");
		Assert.assertEquals(toHtml("here is <code>multiword code</code>."), "here is <code>multiword&nbsp;code</code>.");
		Assert.assertEquals(toHtml("here is <code>multiline\ncode</code>."),
			"<p>here is <code>multiline<br>code</code>.</p>");
		Assert.assertEquals(toHtml("simple line\n\n<code>code</code>"), "<p>simple line</p><p><code>code</code></p>");
		Assert.assertEquals(toHtml("<code>\n# enum\n# enum\n</code>"),
			"<p><code><br>#&nbsp;enum<br>#&nbsp;enum<br></code></p>");
	}

	@Test
	public void paragraph() {
		Assert.assertEquals(toHtml("a b"), "a b");
		Assert.assertEquals(toHtml("a\nb"), "<p>a<br>b</p>");
		Assert.assertEquals(toHtml("a\r\nb"), "<p>a<br>b</p>");
		Assert.assertEquals(toHtml("a\n\nb"), "<p>a</p><p>b</p>");
		Assert.assertEquals(toHtml("a\n\n\n"), "<p>a</p>");
	}

	@Test
	public void header() {
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
	public void specialChars() {
		Assert.assertEquals(toHtml("ü ä ß"), "ü ä ß");
		Assert.assertEquals(toHtml("& #"), "&amp; #");
		Assert.assertEquals(toHtml("< >"), "&lt; &gt;");
	}

	@Test
	public void simple() {
		Assert.assertEquals(toHtml("hello world"), "hello world");
	}

	@Test
	public void complete() {
		String html = toHtml("= header 1 =\nmy first paragraph\nstill first\n\nsecond paragraph\n\n\n\nthird paragraph\n\n== header 2 ==");
		// System.out.println("\n-----\n" + html + "\n-----\n");
		Assert.assertEquals(
			html,
			"<h1>header 1</h1><p>my first paragraph<br>still first</p><p>second paragraph</p><p>third paragraph</p><h2>header 2</h2>");
	}

	private static String toHtml(String wiki) {
		WikiParser parser = new WikiParser(wiki);
		WikiModel model = parser.parse(true);
		return model.toHtml(new TestHtmlContext());
	}

	static class TestHtmlContext implements HtmlContext {

		@Override
		public String getEntityReferenceHrefOrOnclickAParameter(String reference) {
			return "href='" + reference + ".html'";
		}

		@Override
		public String getDownloadUrlByReference(String reference) {
			return reference;
		}

		@Override
		public String getEntityLabelByReference(String reference) {
			return null;
		}

	}

}
