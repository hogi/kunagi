package scrum.client.wiki;

/**
 * http://en.wikipedia.org/wiki/Wikipedia:Cheatsheet
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

		// internal links
		int begin = text.indexOf("[[");
		if (begin >= 0 && begin < text.length() - 4) {
			int end = text.indexOf("]]", begin);
			if (end > begin) {
				String prefix = text.substring(0, begin);
				String content = text.substring(begin + 2, end);
				String suffix = text.substring(end + 2);
				if (content.trim().length() > 0) {
					appendText(p, prefix);
					int spaceIdx = content.indexOf('|');
					if (spaceIdx > 0) {
						String name = content.substring(0, spaceIdx);
						String label = content.substring(spaceIdx + 1);
						p.add(new EntityReference("[[" + name + "]]", label));
					} else {
						p.add(new EntityReference("[[" + content + "]]", content));
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
		if (!s.startsWith("req") && !s.startsWith("tsk") && !s.startsWith("qlt") && !s.startsWith("iss")
				&& !s.startsWith("imp") && !s.startsWith("rsk")) return false;
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
				sb.append(' ');
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

}
