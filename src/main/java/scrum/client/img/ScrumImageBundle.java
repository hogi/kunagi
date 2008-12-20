package scrum.client.img;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ImageBundle;

public interface ScrumImageBundle extends ImageBundle {

	@Resource(value = "story32.png")
	public AbstractImagePrototype storyIcon32();

	@Resource(value = "storyDone32.png")
	public AbstractImagePrototype storyDoneIcon32();

	@Resource(value = "impediment32.png")
	public AbstractImagePrototype impedimentIcon32();

	@Resource(value = "impediment16.png")
	public AbstractImagePrototype impedimentIcon16();

	@Resource(value = "impedimentSolved32.png")
	public AbstractImagePrototype impedimentSolvedIcon32();

	@Resource(value = "sprint32.png")
	public AbstractImagePrototype sprintIcon32();

	@Resource(value = "task32.png")
	public AbstractImagePrototype taskIcon32();

	@Resource(value = "taskDone32.png")
	public AbstractImagePrototype taskDoneIcon32();

	@Resource(value = "trash32.png")
	public AbstractImagePrototype trashIcon32();

	@Resource(value = "drag32.png")
	public AbstractImagePrototype dragHandleIcon32();

	@Resource(value = "project32.png")
	public AbstractImagePrototype projectIcon32();

}
