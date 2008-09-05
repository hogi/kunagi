package scrum.client.impediments;

import scrum.client.Client;
import scrum.client.common.BlockListWidget;

import com.google.gwt.user.client.ui.Composite;

public class Impediments2Widget extends Composite {

	private BlockListWidget list;

	public Impediments2Widget() {
		list = new BlockListWidget();
		for (Impediment impediment : Client.impediments) {
			list.addBlock(new ImpedimentWidget(impediment));
		}
		initWidget(list);
	}

}
