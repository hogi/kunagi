package scrum.client.project;

import scrum.client.common.AEntity;

public class BacklogItem extends AEntity {

	private String label;
	private String description;
	private String testDescription;
	private boolean done;

	public BacklogItem(String id, String label) {
		super(id);
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public BacklogItem setLabel(String label) {
		this.label = label;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public BacklogItem setDescription(String description) {
		this.description = description;
		return this;
	}

	public String getTestDescription() {
		return testDescription;
	}

	public BacklogItem setTestDescription(String test) {
		this.testDescription = test;
		return this;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}
}
