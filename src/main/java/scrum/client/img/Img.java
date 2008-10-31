package scrum.client.img;

import com.google.gwt.core.client.GWT;

public class Img {

	public static ScrumImageBundle bundle = GWT.create(ScrumImageBundle.class);

	public static ScrumImageBundle icons() {
		return bundle;
	}

}
