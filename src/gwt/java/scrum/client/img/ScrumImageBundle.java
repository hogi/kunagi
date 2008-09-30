package scrum.client.img;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ImageBundle;

public interface ScrumImageBundle extends ImageBundle {

	@Resource(value = "backlogItem32.png")
	public AbstractImagePrototype backlogItemIcon32();

	@Resource(value = "backlogItemDone32.png")
	public AbstractImagePrototype backlogItemDoneIcon32();

	@Resource(value = "impediment32.png")
	public AbstractImagePrototype impedimentIcon32();

	@Resource(value = "impedimentSolved32.png")
	public AbstractImagePrototype impedimentSolvedIcon32();

	@Resource(value = "sprint32.png")
	public AbstractImagePrototype sprintIcon32();

	@Resource(value = "task32.png")
	public AbstractImagePrototype taskIcon32();

	@Resource(value = "trash32.png")
	public AbstractImagePrototype trashIcon32();

	@Resource(value = "drag32.png")
	public AbstractImagePrototype dragHandleIcon32();

}
