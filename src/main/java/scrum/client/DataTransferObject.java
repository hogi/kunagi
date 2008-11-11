package scrum.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class is for transporting data from the scrum server to the GWT client.
 */
public class DataTransferObject implements Serializable {

	public Map project;
	public List<Map> impediments;
	public List<Map> backlogItems;
	public List<String> errors = new ArrayList<String>();

}
