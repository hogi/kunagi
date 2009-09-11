package scrum.client.project;

import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.ButtonWidget;

import java.util.LinkedList;
import java.util.List;

import scrum.client.ScrumGwtApplication;
import scrum.client.img.Img;
import scrum.client.workspace.ClipboardItemWidget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.Widget;

public class QualitySuggestWidget extends AWidget {

	private Requirement requirement;
	private FlexTable table;
	private List<Quality> qualitys = new LinkedList<Quality>();
	private SuggestBox suggest;
	private MultiWordSuggestOracle oracle;

	public QualitySuggestWidget(Requirement requirement) {
		this.requirement = requirement;
	}

	@Override
	protected Widget onInitialization() {
		table = new FlexTable();
		table.setBorderWidth(0);
		table.setCellPadding(0);
		table.setCellSpacing(0);

		oracle = new MultiWordSuggestOracle();
		suggest = new SuggestBox(oracle);
		table.setWidget(0, 0, suggest);
		ButtonWidget add = new ButtonWidget(Img.bundle.plus16().createImage(), null);
		add.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				List<Quality> qualitys = requirement.getDao().getQualitysByLabel(suggest.getText());
				if (qualitys.size() > 0) {
					addQuality(qualitys.get(0));
				}
				suggest.setText("");
			}
		});
		table.setWidget(0, 1, add.update());

		return table;
	}

	@Override
	protected void onUpdate() {
		for (int i = table.getRowCount() - 1; i > 0; i--) {
			table.removeRow(i);
		}

		for (Quality a : requirement.getQualitys()) {
			addQuality(a);
		}

		oracle.clear();
		for (Quality a : ScrumGwtApplication.get().getProject().getQualitys()) {
			oracle.add(a.getLabel());
		}
	}

	private void addQuality(final Quality a) {
		int row = table.getRowCount() - 1;
		qualitys.add(a);
		table.insertRow(row);
		table.setWidget(row, 0, createQualityWidget(a));

		ButtonWidget delete = new ButtonWidget(Img.bundle.delete16().createImage(), null);
		delete.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				int row = qualitys.indexOf(a);
				table.removeRow(row);
				qualitys.remove(row);
			}
		});
		table.setWidget(row, 1, delete.update());
	}

	private Widget createQualityWidget(Quality a) {
		QualityBlock block = new QualityBlock();
		block.setObject(a);
		return new ClipboardItemWidget(block).update();
	}
}
