package scrum.client.wiki;

import scrum.client.ScrumGwtApplication;

/**
 * http://en.wikipedia.org/wiki/Wikipedia:Cheatsheet http://en.wikipedia.org/wiki/Help:Table
 */
public class WikiParser {

	public static void main(String[] args) {
		WikiParser wikiParser = new WikiParser("tsk17, (req122)\n");
		WikiModel model = wikiParser.parse();
		System.out.println(model);
	}

	private String input;
	private WikiModel model;
	private boolean oneliner;

	public WikiParser(String input) {
		assert input != null;
		this.input = input;
	}

	private void appendWord(Paragraph p, String word) {
		if (isUrl(word)) {
			p.add(new Link(word));
			return;
		}

		if (word.length() > 1 && isIgnorableWordPrefix(word.charAt(0))) {
			p.add(new Text(word.substring(0, 1)));
			word = word.substring(1);
		}

		StringBuilder suffix = null;
		for (int i = word.length() - 1; i >= 0; i--) {
			if (isIgnorableWordSuffix(word.charAt(i))) {
				if (suffix == null) suffix = new StringBuilder();
				suffix.insert(0, word.charAt(i));
			} else {
				break;
			}
		}
		if (suffix != null) word = word.substring(0, word.length() - suffix.length());

		if (isEntityReference(word)) {
			p.add(new EntityReference(word));
			if (suffix != null) p.add(new Text(suffix.toString()));
			return;
		}

		p.add(new Text(word));
		if (suffix != null) p.add(new Text(suffix.toString()));
	}

	private Paragraph appendText(Paragraph p, String text) {
		int begin;

		// code
		begin = text.indexOf("<code>");
		if (begin >= 0 && begin < text.length() - 7) {
			int end = text.indexOf("</code>", begin);
			if (end > begin) {
				String prefix = text.substring(0, begin);
				String content = text.substring(begin + 6, end);
				String suffix = text.substring(end + 7);
				if (content.trim().length() > 0) {
					appendText(p, prefix);
					p.add(new Code(content));
					appendText(p, suffix);
					return p;
				}
			}
		}

		text = text.replace('\n', ' ');

		// internal links
		begin = text.indexOf("[[");
		if (begin >= 0 && begin < text.length() - 4) {
			int end = text.indexOf("]]", begin);
			if (end > begin) {
				String prefix = text.substring(0, begin);
				String content = text.substring(begin + 2, end);
				String suffix = text.substring(end + 2);
				if (content.trim().length() > 0) {
					appendText(p, prefix);
					if (content.startsWith("Image:")) {
						createImage(p, content.substring(6));
					} else {
						createEntityReference(p, content);
					}
					appendText(p, suffix);
					return p;
				}
			}
		}

		// external links
		begin = text.indexOf("[");
		if (begin >= 0 && begin < text.length() - 3) {
			if (text.charAt(begin + 1) != '[') {
				int end = text.indexOf("]", begin);
				if (end > begin) {
					String prefix = text.substring(0, begin);
					String link = text.substring(begin + 1, end);
					String suffix = text.substring(end + 1);
					appendText(p, prefix);
					int spaceIdx = link.indexOf(' ');
					if (spaceIdx > 0) {
						String href = link.substring(0, spaceIdx);
						String label = link.substring(spaceIdx + 1);
						p.add(new Link(href, label));
					} else {
						p.add(new Link(link));
					}
					appendText(p, suffix);
					return p;
				}
			}
		}

		// strong end emph
		begin = text.indexOf("'''''");
		if (begin >= 0 && begin < text.length() - 5) {
			int end = text.indexOf("'''''", begin + 5);
			if (end >= 0) {
				String prefix = text.substring(0, begin);
				String content = text.substring(begin + 5, end);
				String suffix = text.substring(end + 5);
				if (content.trim().length() > 0) {
					appendText(p, prefix);
					Highlight h = new Highlight(true, true);
					appendText(h, content);
					p.add(h);
					appendText(p, suffix);
					return p;
				}
			}
		} else {
			begin = text.indexOf("'''");
			if (begin >= 0 && begin < text.length() - 3) {
				int end = text.indexOf("'''", begin + 3);
				if (end >= 0) {
					String prefix = text.substring(0, begin);
					String content = text.substring(begin + 3, end);
					String suffix = text.substring(end + 3);
					if (content.trim().length() > 0) {
						appendText(p, prefix);
						Highlight h = new Highlight(false, true);
						appendText(h, content);
						p.add(h);
						appendText(p, suffix);
						return p;
					}
				}
			} else {
				begin = text.indexOf("''");
				if (begin >= 0 && begin < text.length() - 2) {
					int end = text.indexOf("''", begin + 2);
					if (end >= 0) {
						String prefix = text.substring(0, begin);
						String content = text.substring(begin + 2, end);
						String suffix = text.substring(end + 2);
						if (content.trim().length() > 0) {
							appendText(p, prefix);
							Highlight h = new Highlight(true, false);
							appendText(h, content);
							p.add(h);
							appendText(p, suffix);
							return p;
						}
					}
				}
			}
		}

		while (text.length() > 0) {
			int idx = text.indexOf(' ');
			if (idx < 0) {
				appendWord(p, text);
				text = "";
			} else {
				appendWord(p, text.substring(0, idx));
				p.add(Text.SPACE);
				text = text.substring(idx + 1);
			}
		}
		p.add(new Text(text));
		return p;
	}

	private void createImage(Paragraph p, String code) {
		boolean thumb = code.contains("|thumb");
		if (thumb) {
			code = code.replace("|thumb", "");
		}
		boolean left = code.contains("|left");
		if (left) {
			code = code.replace("|left", "");
		}
		p.add(new Image(code, thumb, left));
	}

	private void createEntityReference(Paragraph p, String code) {
		int sepIdx = code.indexOf('|');
		if (sepIdx > 0) {
			String name = code.substring(0, sepIdx);
			String label = code.substring(sepIdx + 1);
			p.add(new EntityReference("[[" + name + "]]", label));
		} else {
			p.add(new EntityReference("[[" + code + "]]", code));
		}
	}

	private void nextPart() {
		nextLine = null;

		// empty
		if (input.length() == 0) {
			input = null;
			return;
		}

		// empty lines
		if (input.startsWith("\n")) {
			String line = getNextLine();
			while (line.trim().length() == 0 && input.length() > 0) {
				burn(line.length() + 1);
				line = getNextLine();
			}
			return;
		}

		// h1
		if (input.startsWith("= ")) {
			String line = getNextLine();
			if (line.length() > 4 && line.endsWith(" =")) {
				model.add(new Header(line.substring(2, line.length() - 2), 1));
				burn(line.length() + 1);
				return;
			}
		}

		// h2
		if (input.startsWith("== ")) {
			String line = getNextLine();
			if (line.length() > 6 && line.endsWith(" ==")) {
				model.add(new Header(line.substring(3, line.length() - 3), 2));
				burn(line.length() + 1);
				return;
			}
		}

		// h3
		if (input.startsWith("=== ")) {
			String line = getNextLine();
			if (line.length() > 8 && line.endsWith(" ===")) {
				model.add(new Header(line.substring(4, line.length() - 4), 3));
				burn(line.length() + 1);
				return;
			}
		}

		// h4
		if (input.startsWith("==== ")) {
			String line = getNextLine();
			if (line.length() > 10 && line.endsWith(" ====")) {
				model.add(new Header(line.substring(5, line.length() - 5), 4));
				burn(line.length() + 1);
				return;
			}
		}

		// pre
		if (input.startsWith(" ")) {
			StringBuilder sb = new StringBuilder();
			String line = getNextLine();
			boolean first = true;
			while (line.startsWith(" ")) {
				if (first) {
					first = false;
				} else {
					sb.append("\n");
				}
				sb.append(line);
				burn(line.length() + 1);
				line = getNextLine();
			}
			model.add(new Pre(sb.toString()));
			return;
		}

		// unordered list
		if (input.startsWith("* ") || input.startsWith("# ")) {
			boolean ordered = input.startsWith("#");
			ItemList list = new ItemList(ordered);
			Paragraph item = null;
			String line = getNextLine();
			while (!line.startsWith("\n") && line.length() > 0) {
				if (line.startsWith(ordered ? "# " : "* ")) {
					item = new Paragraph(false);
					appendText(item, line.substring(2));
					list.add(item);
				} else {
					item.add(Text.SPACE);
					appendText(item, line);
				}
				burn(line.length() + 1);
				line = getNextLine();
			}
			model.add(list);
			return;
		}

		// table
		if (input.startsWith("{|")) {
			burn(2);

			Table table = new Table();
			model.add(table);
			Paragraph p = null;

			while (true) {
				boolean lastLine = false;
				String line = getNextLine();
				burn(line.length() + 1);
				if (line.length() == 0 && input.length() == 0) return;
				int closeTagIndex = line.indexOf("|}");
				if (closeTagIndex >= 0) {
					line = line.substring(0, closeTagIndex);
					lastLine = true;
				}

				if (line.startsWith("|-")) {
					table.addCell(p);
					table.nextRow();
					p = null;
					continue;
				}

				if (line.startsWith("|")) {
					table.addCell(p);
					p = null;
					line = line.substring(1);
				}

				if (line.length() > 0) {
					if (p == null) {
						p = new Paragraph(false);
					} else {
						p.add(new Text("\n"));
					}
					appendText(p, line);
				}

				if (lastLine) {
					table.addCell(p);
					return;
				}
			}

		}

		// toc
		if (input.startsWith("TOC")) {
			String line = getNextLine();
			if (line.equals("TOC")) {
				burn(line.length() + 1);
				model.add(new Toc(model));
				return;
			}
		}

		// paragraph
		model.add(appendText(new Paragraph(!oneliner), cutParagraph()));
	}

	public WikiModel parse() {
		model = new WikiModel();

		input = input.replace("\r", "");
		input = input.replace("\t", "    ");

		oneliner = input.indexOf('\n') < 0;

		while (input != null) {
			nextPart();
		}

		return model;
	}

	private boolean isIgnorableWordPrefix(char c) {
		return c == '(';
	}

	private boolean isIgnorableWordSuffix(char c) {
		return c == '.' || c == ',' || c == '!' || c == '?' || c == ')';
	}

	private boolean isUrl(String s) {
		if (s.startsWith("http://")) return true;
		if (s.startsWith("https://")) return true;
		if (s.startsWith("www.")) return true;
		if (s.startsWith("ftp://")) return true;
		return false;
	}

	private boolean isEntityReference(String s) {
		if (s.length() < 4) return false;
		if (!Character.isDigit(s.charAt(3))) return false;

		boolean prefixOk = false;
		for (String prefix : ScrumGwtApplication.REFERENCE_PREFIXES) {
			if (s.startsWith(prefix)) {
				prefixOk = true;
				break;
			}
		}
		if (!prefixOk) return false;

		int len = s.length();
		for (int i = 3; i < len; i++) {
			if (!Character.isDigit(s.charAt(i))) return false;
		}
		try {
			String number = s.substring(3);
			Integer.parseInt(number);
		} catch (NumberFormatException ex) {
			return false;
		}
		return true;
	}

	private String cutParagraph() {
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		while (input.length() > 0) {
			String line = getNextLine();
			if (line.trim().length() == 0) {
				break;
			}
			if (first) {
				first = false;
			} else {
				sb.append('\n');
			}
			sb.append(line);
			burn(line.length() + 1);
		}
		return sb.toString();
	}

	private void burn(int length) {
		nextLine = null;
		if (length >= input.length()) {
			input = "";
		} else {
			input = input.substring(length);
		}
	}

	private String nextLine;

	private String getNextLine() {
		if (nextLine == null) {
			int idx = input.indexOf('\n');
			if (idx < 0) return input;
			nextLine = input.substring(0, idx);
		}
		return nextLine;
	}

	public static final String SYNTAX_INFO_HTML = "<table class='WikiParser-syntax-table' cellpadding='5px'>"
			+ "<tr><td><i>Italic text</i></td>            <td><code>''italic text''</code></td></tr>"
			+ "<tr><td><b>Bold text</b></td>              <td><code>'''bold text'''</code></td></tr>"
			+ "<tr><td><b><i>Bold and italic</i></b></td> <td><code>'''''bold and italic'''''</td></tr>"
			+ "<tr><td>Internal link</td>                 <td><code>[[Name of page]]<br>[[Name of page|Text to display]]</code></td></tr>"
			+ "<tr><td>External link</td>                 <td><code>[http://servisto.de]<br>[http://servisto.de Text to display]<br>http://servisto.de</code></td></tr>"
			+ "<tr><td><h2>Section headings</h2></td>     <td><code>= heading 1 =<br>== heading 2 ==<br>=== heading 3 ===<br>==== heading 4 ====</code></td></tr>"
			+ "<tr><td>Bulleted list</td>                 <td><code>* Item 1<br>* Item 2<br>* Item 3</code></td></tr>"
			+ "<tr><td>Numbered list</td>                 <td><code># Item<br># Item 2<br># Item 3</code></td></tr>"
			+ "<tr><td>Internal image<br>thumb</td>       <td><code>[[Image:fle3]]<br>[[Image:fle3|thumb]]</code></td></tr>"
			+ "<tr><td>External image<br>thumb</td>       <td><code>[[Image:http://servisto.de/image.jpg]]<br>[[Image:http://servisto.de/image.jpg|thumb|left]]</code></td></tr>"
			+ "<tr><td><code>Code</code></td>             <td><code>&lt;code&gt;Code&lt;/code&gt;</code></td></tr>"
			+ "</table>";

}
