package com.adarq.verttraining;

public class Train {

	private long id;
	private String trainingName;
	
	public long getId(){
		
		return id;
		
	}
	
	public void setId(long id){
		
		this.id = id;
		
	}
	
	public String getTrainingName(){
		
		return trainingName;
		
	}
	
	public void setTrainingName(String trainingName){
		this.trainingName = trainingName;
	}
	
	@Override
	public String toString() {
		
		return trainingName;

}
	
}