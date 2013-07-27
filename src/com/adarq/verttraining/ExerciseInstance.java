package com.adarq.verttraining;

public class ExerciseInstance {

	private long id;
	private String exerciseInstName;
	private int exerciseInstDist;
	private int exerciseInstSets;
	private int exerciseInstReps;
	private long exerciseId;
	private long trainingId;
	
	public long getId(){
		
		return id;
		
	}
	
	public void setId(long id){
		
		this.id = id;
		
	}
	
	public String getExerciseInstName(){
		
		return exerciseInstName;
		
	}
	
	public void setExerciseInstName(String exerciseInstName){
		this.exerciseInstName = exerciseInstName;
	}

	@Override
	public String toString() {
		
		return exerciseInstName;
		
	}
	
	public int getExerciseInstDist(){
		
		return exerciseInstDist;
		
	}
	
	public void setExerciseInstDist(int exerciseInstDist){
		this.exerciseInstDist = exerciseInstDist;
	}
	
	public int getExerciseInstSets(){
		
		return exerciseInstSets;
		
	}
	
	public void setExerciseInstSets(int exerciseInstSets){
		
		this.exerciseInstSets = exerciseInstSets;
	}
	
	public int getExerciseInstReps(){
		
		return exerciseInstReps;
		
	}
	
	public void setExerciseInstReps(int exerciseInstReps){
		this.exerciseInstReps = exerciseInstReps;
	}
	
	public long getExerciseId(){
		
		return exerciseId;
		
	}
	
	public void setExerciseId(long exerciseId){
		this.exerciseId = exerciseId;
	}
	
public long getTrainingId(){
		
		return trainingId;
		
	}
	
	public void setTrainingId(long trainingId){
		this.trainingId = trainingId;
	}
	
	
	}

