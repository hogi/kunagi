package scrum.client.impediments;

import scrum.client.ScrumGwtApplication;
import scrum.client.common.AItemFieldsWidget;
import scrum.client.common.editable.AEditableTextWidget;
import scrum.client.common.editable.AEditableTextareaWidget;

public class ImpedimentFieldsWidget extends AItemFieldsWidget<Impediment> {

	@Override
	protected void build() {
		addField("Label", new LabelWidget());
		addField("Description", new DescriptionWidget());
	}

	class LabelWidget extends AEditableTextWidget {

		@Override
		protected String getText() {
			return item.label;
		}

		@Override
		protected void setText(String text) {
			item.label = text;
			ScrumGwtApplication.impediments.table.rebuild();
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
}
