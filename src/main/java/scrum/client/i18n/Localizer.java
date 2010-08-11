package scrum.client.i18n;

public class Localizer extends GLocalizer {

	private TextBundleViews textBundleViews = new TextBundleViews();
	private TextBundleFields textBundleFields = new TextBundleFields();

	public TextBundleViews views() {
		return textBundleViews;
	}

	public TextBundleFields fields() {
		return textBundleFields;
	}
}
