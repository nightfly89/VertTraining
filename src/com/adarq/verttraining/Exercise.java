package com.adarq.verttraining;

public class Exercise {

	private long id;
	private String exerciseName;
	private String exerciseDesc;
	
	public long getId(){
		
		return id;
		
	}
	
public void setId(long id){
		
	this.id = id;

	}
	
	
	public String getExerciseName(){
		
		return exerciseName;
		
	}
	
	public void setExerciseName(String exerciseName){
		this.exerciseName = exerciseName;
	}
	
	public String getExerciseDesc(){
		
		return exerciseDesc;
		
	}
	
	public void setExerciseDesc(String exerciseDesc){
		this.exerciseDesc = exerciseDesc;
	}

	@Override
	public String toString() {
		
		return exerciseName;
		
	}
	
	
	
	
}
