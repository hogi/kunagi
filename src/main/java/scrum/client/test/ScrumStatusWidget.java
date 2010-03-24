package scrum.client.test;

import ilarkesto.core.scope.Scope;
import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.TableBuilder;

import java.util.Map;

import scrum.client.Dao;
import scrum.client.common.AScrumWidget;
import scrum.client.workspace.PagePanel;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class ScrumStatusWidget extends AScrumWidget {

	private Dao dao;

	private SimplePanel entityCountWrapper;

	@Override
	protected Widget onInitialization() {
		dao = Scope.get().getComponent(Dao.class);

		entityCountWrapper = new SimplePanel();

		PagePanel page = new PagePanel();
		page.addHeader("Entities");
		page.addSection(entityCountWrapper);

		return page;
	}

	@Override
	protected void onUpdate() {
		TableBuilder tb = new TableBuilder();
		tb.setWidth(null);
		for (Map.Entry<String, Integer> entry : dao.getEntityCounts().entrySet()) {
			tb.addRow(Gwt.createFieldLabel(entry.getKey()), new Label(String.valueOf(entry.getValue())));
		}
		entityCountWrapper.setWidget(tb.createTable());
		super.onUpdate();
	}
}
