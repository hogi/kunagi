package scrum.client.journal;

import ilarkesto.gwt.client.ATextWidget;
import scrum.client.admin.User;
import scrum.client.collaboration.Wiki;
import scrum.client.common.AScrumWidget;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ChangeWidget extends AScrumWidget {

	private Change change;
	private Label date;

	public ChangeWidget(Change change) {
		super();
		this.change = change;
	}

	@Override
	protected Widget onInitialization() {
		User changer = change.getUser();
		Label changerLabel = new Label(changer.getName());
		changerLabel.setStyleName("ChangeWidget-header-author");
		String color = getCurrentProject().getUserConfig(changer).getColor();
		changerLabel.getElement().getStyle().setProperty("color", color);

		date = new Label();
		date.setStyleName("ChangeWidget-header-date");

		FlowPanel header = new FlowPanel();
		header.setStyleName("ChangeWidget-header");
		header.add(date);
		header.add(changerLabel);

		FlowPanel panel = new FlowPanel();
		panel.setStyleName("ChangeWidget");
		panel.add(header);
		panel.add(createBody());

		return panel;
	}

	@Override
	protected void onUpdate() {
		date.setText(change.getDateAndTime().getPeriodToNow().toShortestString() + " ago");
		super.onUpdate();
	}

	private Widget createBody() {
		ATextWidget valueWidget = new ATextWidget() {

			@Override
			protected void onUpdate() {
				setHtml(Wiki.toHtml(change.getValue()));
			}
		};

		FlowPanel panel = new FlowPanel();
		panel.add(new Label(change.getLabel()));
		panel.add(valueWidget);
		return panel;
	}

}
