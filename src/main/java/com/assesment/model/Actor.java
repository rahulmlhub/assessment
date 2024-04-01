package com.assesment.model;

public class Actor {

	private int actorId;
	private String actorName;
	private String firstName;
	private String lastName;
	
	
	public int getActorId() {
		return actorId;
	}
	public void setActorId(int actorId) {
		this.actorId = actorId;
	}
	public String getActorName() {
		return firstName+" "+lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Override
	public String toString() {
		return "Actor [actorName=" + actorName + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	
}
