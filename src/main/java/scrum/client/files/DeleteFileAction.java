package scrum.client.files;

import ilarkesto.gwt.client.Gwt;
import scrum.client.common.TooltipBuilder;

public class DeleteFileAction extends GDeleteFileAction {

	public DeleteFileAction(scrum.client.files.File file) {
		super(file);
	}

	@Override
	public String getLabel() {
		return "Delete";
	}

	@Override
	public String getTooltip() {
		TooltipBuilder tb = new TooltipBuilder("Delete this File permanently.");
		if (!file.getProject().isScrumTeamMember(getCurrentUser()))
			tb.addRemark(TooltipBuilder.NOT_SCRUMTEAM);

		return tb.getTooltip();
	}

	@Override
	public boolean isExecutable() {
		return true;
	}

	@Override
	public boolean isPermitted() {
		if (!getCurrentProject().isScrumTeamMember(getCurrentUser())) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		if (!Gwt.confirm("Deleting a file is unrecoverable. Delete it anyway?")) return;
		getCurrentProject().deleteFile(file);
	}

}