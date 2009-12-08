package scrum.client.files;

import java.util.Map;

public class File extends GFile {

	public File(Map data) {
		super(data);
	}

	@Override
	public String toString() {
		return getFilename();
	}

}