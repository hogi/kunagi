package scrum.client.impediments;

import scrum.client.common.AItemFieldsWidget;
import scrum.client.common.editable.AEditableTextWidget;
import scrum.client.common.editable.AEditableTextareaWidget;

public class ImpedimentFieldsWidget extends AItemFieldsWidget<Impediment> {

	public ImpedimentFieldsWidget(Impediment item) {
		super(item);
	}

	@Override
	protected void build() {
		addField("Label", new LabelWidget());
		addField("Description", new DescriptionWidget());
		addField("Solution", new SolutionWidget());
	}

	class LabelWidget extends AEditableTextWidget {

		@Override
		protected String getText() {
			return item.label;
		}

		@Override
		protected void setText(String text) {
			if (text == null || text.length() == 0) text = "unlabeled";
			item.label = text;
		}

	}

	class DescriptionWidget extends AEditableTextareaWidget {

		@Override
		protected String getText() {
			return item.description;
		}

		@Override
		protected void setText(String text) {
			item.description = text;
		}

	}

	class SolutionWidget extends AEditableTextareaWidget {

		@Override
		protected String getText() {
			return item.solution;
		}

		@Override
		protected void setText(String text) {
			item.solution = text;
		}

	}
}
