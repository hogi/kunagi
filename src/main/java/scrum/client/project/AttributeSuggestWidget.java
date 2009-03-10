package scrum.client.project;

import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.ButtonWidget;

import java.util.LinkedList;
import java.util.List;

import scrum.client.ScrumGwtApplication;
import scrum.client.img.Img;
import scrum.client.workspace.ClipboardItemWidget;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.Widget;

public class AttributeSuggestWidget extends AWidget {

	private Requirement requirement;
	private FlexTable table;
	private List<Attribute> attributes = new LinkedList<Attribute>();
	private SuggestBox suggest;
	private MultiWordSuggestOracle oracle;

	public AttributeSuggestWidget(Requirement requirement) {
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
		add.addClickListener(new ClickListener() {

			public void onClick(Widget sender) {
				List<Attribute> attributes = requirement.getDao().getAttributesByLabel(suggest.getText());
				if (attributes.size() > 0) {
					addAttribute(attributes.get(0));
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

		for (Attribute a : requirement.getAttributes()) {
			addAttribute(a);
		}

		oracle.clear();
		for (Attribute a : ScrumGwtApplication.get().getProject().getAttributes()) {
			oracle.add(a.getLabel());
		}
	}

	private void addAttribute(final Attribute a) {
		int row = table.getRowCount() - 1;
		attributes.add(a);
		table.insertRow(row);
		table.setWidget(row, 0, createAttributeWidget(a));

		ButtonWidget delete = new ButtonWidget(Img.bundle.delete16().createImage(), null);
		delete.addClickListener(new ClickListener() {

			public void onClick(Widget sender) {
				int row = attributes.indexOf(a);
				table.removeRow(row);
				attributes.remove(row);
			}
		});
		table.setWidget(row, 1, delete.update());
	}

	private Widget createAttributeWidget(Attribute a) {
		AttributeBlock block = new AttributeBlock();
		block.setObject(a);
		return new ClipboardItemWidget(block).update();
	}
}
