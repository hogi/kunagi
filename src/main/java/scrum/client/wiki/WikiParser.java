package scrum.client.wiki;

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
	private int length;
	private int index;
	private char ch;
	private StringBuilder word;
	private StringBuilder text;

	public WikiParser(String input) {
		super();
		this.input = input;
	}

	private void onWordEnd(String w) {
		System.out.println("word: " + w);
		boolean processed = plugins.processWord(w, this, model);
		if (processed) return;
		appendToText(w);
	}

	private void onLineEnd() {
		flushText();
		model.addNewLine();
	}

	private void onSpace() {
		flushWord();
		appendToText(' ');
	}

	private void onSpecialChar() {
		flushWord();
		appendToText(ch);
	}

	private void onChar() {
		if (ch == '\r') return;
		if (ch == ' ') {
			onSpace();
			return;
		}
		if (isNewLine(ch)) {
			onLineEnd();
			return;
		}
		if (!isLetterOrDigit(ch)) {
			onSpecialChar();
			return;
		}
		appendToWord(ch);
	}

	private boolean isLetterOrDigit(char ch) {
		return Character.isLetterOrDigit(ch);
	}

	private boolean isNewLine(char ch) {
		return ch == '\n';
	}

	private void appendToWord(char ch) {
		if (word == null) word = new StringBuilder();
		word.append(ch);
	}

	private void appendToText(String s) {
		if (text == null) text = new StringBuilder();
		text.append(s);
	}

	private void appendToText(char c) {
		if (text == null) text = new StringBuilder();
		text.append(c);
	}

	private void onEnd() {
		flushText();
	}

	public void flushText() {
		flushWord();
		if (text == null) return;
		model.addText(text.toString());
		text = null;
	}

	public void clearWord() {
		word = null;
	}

	public void flushWord() {
		if (word == null) return;
		onWordEnd(word.toString());
		word = null;
	}

	public WikiModel parse() {
		model = new WikiModel();

		length = input.length();

		for (index = 0; index < length; index++) {
			ch = input.charAt(index);
			onChar();
		}
		onEnd();

		return model;
	}

	public void addPlugin(WikiPlugin plugin) {
		this.plugins.addPlugin(plugin);
	}

}
