package scrum.client.sprint;

import java.util.ArrayList;
import java.util.List;

import scrum.client.common.AEntity;
import scrum.client.project.BacklogItem;


public class Sprint extends AEntity {

	public enum State {
		Pending, Development, Testing, Production, OnHold
	}
	
	private String label;
	
	private State state;
	
	private List<BacklogItem> backlogItems = new ArrayList<BacklogItem>();
	
	public Sprint(String id, String label) {
		super(id);
		this.label = label;
		state = State.Pending;
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public List<BacklogItem> getBacklogItems() {
		return backlogItems;
	}
	
	public void setBacklogItems(List<BacklogItem> backlogItems) {
		this.backlogItems = backlogItems;
	}

	public State getState() {
		return state;
	}
	
	public String getStateLabel() {
		// TODO i18n
		switch (state) {
			case Development:
				return "Development";
			case Testing:
				return "Testing";
			case Production:
				return "Production";
			case OnHold:
				return "On hold";
			default:
				return "Pending";
		}
	}

	public void setState(State state) {
		this.state = state;
	}

}
