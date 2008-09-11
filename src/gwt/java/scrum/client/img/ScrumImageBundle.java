package scrum.client.img;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ImageBundle;

public interface ScrumImageBundle extends ImageBundle {

	@Resource(value = "impediment32.png")
	public AbstractImagePrototype impedimentIcon32();

	@Resource(value = "impedimentSolved32.png")
	public AbstractImagePrototype impedimentSolvedIcon32();

}
