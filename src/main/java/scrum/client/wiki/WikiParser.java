package scrum.client.wiki;

/**
 * http://de.wikipedia.org/wiki/Hilfe:Textgestaltung
 */
public class WikiParser {

	public static void main(String[] args) {
		WikiParser wikiParser = new WikiParser("tsk17, (req122)\n");
		wikiParser.addPlugin(new EntityReferencePlugin());
		WikiModel model = wikiParser.parse();
		System.out.println(model);
	}

	private WikiMasterPlugin plugins = new WikiMasterPlugin();
	private String input;
	private WikiModel model;

	public WikiParser(String input) {
		this.input = input;
	}

	private void nextPart() {
		nextLine = null;

		// empty
		if (input.length() == 0) {
			input = null;
			return;
		}

		// new paragraph
		if (input.startsWith("\n")) {
			String line = getNextLine();
			while (line.trim().length() == 0) {
				burn(line.length() + 1);
				line = getNextLine();
			}
			return;
		}

		// h1
		if (input.startsWith("= ")) {
			String line = getNextLine();
			if (line.length() > 4 && line.endsWith(" =")) {
				model.addHeader(line.substring(2, line.length() - 2), 1);
				burn(line.length() + 1);
				return;
			}
		}

		// h2
		if (input.startsWith("== ")) {
			String line = getNextLine();
			if (line.length() > 6 && line.endsWith(" ==")) {
				model.addHeader(line.substring(3, line.length() - 3), 2);
				burn(line.length() + 1);
				return;
			}
		}

		// h3
		if (input.startsWith("=== ")) {
			String line = getNextLine();
			if (line.length() > 8 && line.endsWith(" ===")) {
				model.addHeader(line.substring(4, line.length() - 4), 3);
				burn(line.length() + 1);
				return;
			}
		}

		// h4
		if (input.startsWith("==== ")) {
			String line = getNextLine();
			if (line.length() > 10 && line.endsWith(" ====")) {
				model.addHeader(line.substring(5, line.length() - 5), 4);
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
		if (input.startsWith("* ")) {
			UnorderedList list = new UnorderedList();
			ListItem item = null;
			String line = getNextLine();
			while (!line.startsWith("\n") && line.length() > 0) {
				if (line.startsWith("* ")) {
					item = new ListItem(line.substring(2));
					list.add(item);
				} else {
					item.append(" ");
					item.append(line);
				}
				burn(line.length() + 1);
				line = getNextLine();
			}
			model.add(list);
			return;
		}

		// paragraph
		model.beginParagraph();
		String paragraph = cutParagraph();
		model.addText(paragraph);
		model.endParagraph();
		// List<String> words = cutParagraphAsWords();
		// for (String word : words) {
		// model.addText(word + " ");
		// }
	}

	public WikiModel parse() {
		model = new WikiModel();

		input = input.replace("\r", "");
		input = input.replace("\t", "    ");

		while (input != null) {
			nextPart();
		}

		return model;
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

	public void addPlugin(WikiPlugin plugin) {
		this.plugins.addPlugin(plugin);
	}

}
