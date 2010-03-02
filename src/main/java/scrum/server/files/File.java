package scrum.server.files;

import ilarkesto.io.IO;
import scrum.server.admin.User;

public class File extends GFile {

	// --- dependencies ---

	// --- ---

	public void deleteFile() {
		IO.delete(getJavaFile());
	}

	public java.io.File getJavaFile() {
		return new java.io.File(getProject().getFileRepositoryPath() + "/" + getFilename());
	}

	public void updateNumber() {
		if (isNumber(0)) setNumber(getProject().generateFileNumber());
	}

	public boolean isVisibleFor(User user) {
		return getProject().isVisibleFor(user);
	}

	public String getReferenceAndLabel() {
		return getReference() + " (" + getLabel() + ")";
	}

	public String getReference() {
		return scrum.client.files.File.REFERENCE_PREFIX + getNumber();
	}

	public boolean isEditableBy(User user) {
		return getProject().isEditableBy(user);
	}

	@Override
	public String toString() {
		return getReferenceAndLabel();
	}

	@Override
	public void ensureIntegrity() {
		super.ensureIntegrity();
		updateNumber();
	}

}