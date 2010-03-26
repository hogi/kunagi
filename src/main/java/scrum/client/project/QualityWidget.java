package scrum.client.project;

import ilarkesto.gwt.client.TableBuilder;
import scrum.client.ScrumGwt;
import scrum.client.common.AScrumWidget;

import com.google.gwt.user.client.ui.Widget;

public class QualityWidget extends AScrumWidget {

	private Quality quality;

	public QualityWidget(Quality quality) {
		super();
		this.quality = quality;
	}

	@Override
	protected Widget onInitialization() {
		TableBuilder tb = ScrumGwt.createFieldTable();

		tb.addFieldRow("Label", quality.getLabelModel());
		tb.addFieldRow("Description", quality.getDescriptionModel());
		tb.addFieldRow("Test", quality.getTestDescriptionModel());

		return TableBuilder.row(20, tb.createTable(), ScrumGwt.createEmoticonsAndComments(quality));
	}
}
