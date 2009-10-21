package scrum.client.impediments;

import scrum.client.common.ABlockWidget;
import scrum.client.common.AScrumAction;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.dnd.TrashSupport;

import com.google.gwt.user.client.ui.Widget;

public class ImpedimentBlock extends ABlockWidget<Impediment> implements TrashSupport {

	@Override
	protected void onCollapsedInitialization() {}

	@Override
	protected void onUpdateHead() {
		Impediment impediment = getObject();
		setIcon(impediment.getReference());
		setBlockTitle("[" + impediment.getDate() + "] " + impediment.getLabel());
		addMenuAction(new CloseImpedimentAction(impediment));
		addMenuAction(new DeleteImpedimentAction(impediment));
	}

	@Override
	protected Widget onExtendedInitialization() {
		return new ImpedimentWidget(getObject());
	}

	public AScrumAction getTrashAction() {
		return new DeleteImpedimentAction(getObject());
	}

	public static final BlockWidgetFactory<Impediment> FACTORY = new BlockWidgetFactory<Impediment>() {

		public ImpedimentBlock createBlock() {
			return new ImpedimentBlock();
		}

	};

}
