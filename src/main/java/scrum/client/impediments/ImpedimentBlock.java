package scrum.client.impediments;

import scrum.client.common.AExtensibleBlockWidget;
import scrum.client.common.AScrumAction;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.dnd.TrashSupport;
import scrum.client.img.Img;

import com.google.gwt.user.client.ui.Widget;

public class ImpedimentBlock extends AExtensibleBlockWidget<Impediment> implements TrashSupport {

	@Override
	protected void onCollapsedInitialization() {
		setIcon(Img.bundle.impediment16());
	}

	@Override
	protected void onUpdateHead() {
		Impediment impediment = getObject();
		setBlockTitle(impediment.getReference() + " [" + impediment.getDate() + "] " + impediment.getLabel());
		String style = null;
		if (impediment.isClosed()) {
			style = "ImpedimentBlock-closed";
		} else {
			style = "ImpedimentBlock-open";
		}
		setAdditionalStyleName(style);
		setIcon(Img.bundle.impediment16());
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

	public static BlockWidgetFactory<Impediment> FACTORY = new BlockWidgetFactory<Impediment>() {

		public ImpedimentBlock createBlock() {
			return new ImpedimentBlock();
		}

	};

}
