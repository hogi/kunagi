package scrum.client.files;

import ilarkesto.gwt.client.Gwt;

import java.util.Comparator;
import java.util.Map;

import scrum.client.ScrumJs;

public class File extends GFile {

	public static final String REFERENCE_PREFIX = "fle";

	public File(Map data) {
		super(data);
	}

	public String getReference() {
		return REFERENCE_PREFIX + getNumber();
	}

	@Override
	public String toHtml() {
		return ScrumJs.createShowEntityByReferenceLink(getReference()) + " " + Gwt.escapeHtml(getLabel());
	}

	@Override
	public String toString() {
		return getFilename();
	}

	public static final Comparator<File> UPLOAD_TIME_COMPARATOR = new Comparator<File>() {

		public int compare(File a, File b) {
			return a.getUploadTime().compareTo(b.getUploadTime());
		}
	};

	public static final Comparator<File> REVERSE_UPLOAD_TIME_COMPARATOR = new Comparator<File>() {

		public int compare(File a, File b) {
			return UPLOAD_TIME_COMPARATOR.compare(b, a);
		}
	};

}