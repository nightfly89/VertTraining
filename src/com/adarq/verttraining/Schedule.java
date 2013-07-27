package com.adarq.verttraining;

public class Schedule {

	private long id;
	private String scheduleName;
	
	public long getId(){
		
		return id;
		
	}
	
	public void setId(long id){
		
		this.id = id;
		
	}
	
	public String getScheduleName(){
		
		return scheduleName;
		
	}
	
	public void setScheduleName(String scheduleName){
		this.scheduleName = scheduleName;
	}
	
	@Override
	public String toString() {
		
		return scheduleName;

}

}
