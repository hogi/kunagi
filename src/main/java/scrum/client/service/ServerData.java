package scrum.client.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class is for transporting data from the scrum server to the GWT client.
 */
public class ServerData implements Serializable {

	public Map<String, String> project;
	public List<Map<String, String>> impediments;
	public List<String> errors = new ArrayList<String>();

}
