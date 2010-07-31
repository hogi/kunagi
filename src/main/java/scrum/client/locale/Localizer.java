package scrum.client.locale;

import ilarkesto.core.base.Str;

public class Localizer extends GLocalizer {

	public long currentTimeMillis() {
		return System.currentTimeMillis();
	}

	public String string(String key, String... parameters) {
		return "@" + key + " " + Str.format(parameters);
	}

}
