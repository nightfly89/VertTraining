package com.adarq.verttraining;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class TrainingDataSource {

	//Declaring the database and full queries
	
	private SQLiteDatabase database;
	private DatabaseHelper dbHelper;
	private String[] allExerciseColumns = { DatabaseHelper.colExerciseID,
	      DatabaseHelper.colExerciseName,  DatabaseHelper.colExerciseDesc };
	private String[] allTrainingColumns = { DatabaseHelper.colTrainingID,
		      DatabaseHelper.colTrainingName};
	private String[] allScheduleColumns = {	DatabaseHelper.colScheduleID,
		      DatabaseHelper.colScheduleName};
	private String[] allExerciseInstanceColumns = {	DatabaseHelper.colExerciseInstID,
		      DatabaseHelper.colExerciseInstExercise, DatabaseHelper.colExerciseInstTraining,
		      DatabaseHelper.colExerciseInstName,
		      DatabaseHelper.colExerciseInstDistance, DatabaseHelper.colExerciseInstSets, 
		      DatabaseHelper.colExerciseInstReps};
	private String[] allTrainingInstanceColumns = {	DatabaseHelper.colTrainingInstID,
		      DatabaseHelper.colTrainingInstSchedule, DatabaseHelper.colTrainingInstTraining};
	private String[] exerciseNames = {DatabaseHelper.colExerciseName};
	private String[] trainingName = {DatabaseHelper.colTrainingName};
	private String[] scheduleName = {DatabaseHelper.colScheduleName};
	
	//declaring the sources, open and close
	
	public TrainingDataSource(Context context) {
	    dbHelper = new DatabaseHelper(context, "vertDB", 2);
	  }
	
	public void open() throws SQLException {
		
	    database = dbHelper.getWritableDatabase();
	    database.execSQL("PRAGMA foreign_keys = ON;");
	    
	  }

	  public void close() {
	    dbHelper.close();
	  }
	  
	  //database manipulation for exercises
	  
	  private Exercise cursorToExercise(Cursor cursor) {
		  	Exercise exercise = new Exercise();
		  	exercise.setId(cursor.getLong(0));
		  	exercise.setExerciseName(cursor.getString(1));
		  	exercise.setExerciseDesc(cursor.getString(2));
		    return exercise;
		  }

	  public Exercise createExercise(String ex, String des) throws SQLException {
		  
		  ContentValues values = new ContentValues();
		  values.put(DatabaseHelper.colExerciseName, ex);
		  values.put(DatabaseHelper.colExerciseDesc, des);
		  long insertId = database.insert(DatabaseHelper.exerciseTable, null, values);
		  Cursor cursor = database.query(DatabaseHelper.exerciseTable, allExerciseColumns, DatabaseHelper.colExerciseID + " = " + insertId, null, null, null, null);
		  cursor.moveToFirst();
		  Exercise newExercise = cursorToExercise(cursor);
		  cursor.close();
		  return newExercise;
		  
	  }
	  
	  public void deleteExercise(Exercise exercise) throws SQLException {
		  
		  long id = exercise.getId();
		  System.out.println("exercise deleted with id: " + id);
		  database.delete(DatabaseHelper.exerciseTable, DatabaseHelper.colExerciseID + " = " +id, null);
		  
	  }
	  
	  public List<Exercise> getAllExercises() throws SQLException {
		    List<Exercise> exercises = new ArrayList<Exercise>();

		    Cursor cursor = database.query(DatabaseHelper.exerciseTable,
		        allExerciseColumns, null, null, null, null, null);

		    cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		    	Exercise exercise = cursorToExercise(cursor);
		    	exercises.add(exercise);
		    	cursor.moveToNext();
		    }
		    // Make sure to close the cursor
		    cursor.close();
		    return exercises;
		  }
	  
	  public ArrayList<String> duplicateExercises() throws SQLException {
		  
		  	
		  	ArrayList<String> exercises = new ArrayList<String>();
		  	
		  	try{
		  	Cursor cursor = database.query(DatabaseHelper.exerciseTable,
			       exerciseNames, null, null, null, null, null);
		   
		  	cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		    	
		    	exercises.add(cursor.getString(0));
		    	cursor.moveToNext();
		    }
		    // Make sure to close the cursor
		    cursor.close();
		    
		  	return exercises;
	  		} catch (Exception e){
	  		e.printStackTrace();
	  		}
		   
		   return exercises;
		  
	  }
	  
	  //database manipulation for training
	  
	  private Train cursorToTraining(Cursor cursor) {
		  	Train training = new Train();
		  	training.setId(cursor.getLong(0));
		  	training.setTrainingName(cursor.getString(1));
		  	return training;
		  }
	  
	  public Train createTraining(String trn) throws SQLException {
		  
			ContentValues values = new ContentValues();
			values.put(DatabaseHelper.colTrainingName, trn);
			long insertId = database.insert(DatabaseHelper.trainingTable, null, values);
			Cursor cursor = database.query(DatabaseHelper.trainingTable, allTrainingColumns, DatabaseHelper.colTrainingID + " = " + insertId, null, null, null, null);
			cursor.moveToFirst();
			Train newTraining = cursorToTraining(cursor);
			cursor.close();
			return newTraining;
		  
	  }
	  
	  public void deleteTraining(Train training) throws SQLException {
			  
			long id = training.getId();
			System.out.println("training session deleted with id: " + id);
			database.delete(DatabaseHelper.trainingTable, DatabaseHelper.colTrainingID + " = " +id, null);
		  
	  }
	  
	  public List<Train> getAllTraining() throws SQLException {
		    List<Train> trainings = new ArrayList<Train>();

		    Cursor cursor = database.query(DatabaseHelper.trainingTable,
		        allTrainingColumns, null, null, null, null, null);

		    cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		    	Train training = cursorToTraining(cursor);
		    	trainings.add(training);
		    	cursor.moveToNext();
		    }
		    // Make sure to close the cursor
		    cursor.close();
		    return trainings;
		  }
	  
	  public ArrayList<String> duplicateTraining() throws SQLException {
		  
		  	
		  	ArrayList<String> trainings = new ArrayList<String>();
		  	
		  	try{
		  	Cursor cursor = database.query(DatabaseHelper.trainingTable,
			       trainingName, null, null, null, null, null);
		   
		  	cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		    	
		    	trainings.add(cursor.getString(0));
		    	cursor.moveToNext();
		    }
		    // Make sure to close the cursor
		    cursor.close();
		    
		  	return trainings;
	  		} catch (Exception e){
	  		e.printStackTrace();
	  		}
		   
		   return trainings;
		  
	  }
	  
	  
	  //data manipulation exercise instance
	  
	  
	  private ExerciseInstance cursorToExerciseInstance(Cursor cursor) {
		  
		  	ExerciseInstance exerciseInstance = new ExerciseInstance();
		  	exerciseInstance.setId(cursor.getLong(0));
		  	exerciseInstance.setExerciseId(cursor.getLong(1));
		  	exerciseInstance.setTrainingId(cursor.getLong(2));
		  	exerciseInstance.setExerciseInstName(cursor.getString(3));
		  	exerciseInstance.setExerciseInstDist(cursor.getInt(4));
		  	exerciseInstance.setExerciseInstReps(cursor.getInt(5));
		  	exerciseInstance.setExerciseInstSets(cursor.getInt(6));

		    return exerciseInstance;
		  }
	  
	  public ExerciseInstance createExerciseInstance(long exId,  long trId, String exName, int dist, int setts, int reps) throws SQLException {
		  
		  ContentValues values = new ContentValues();
		  values.put(DatabaseHelper.colExerciseInstExercise, exId);
		  values.put(DatabaseHelper.colExerciseInstTraining, trId);
		  values.put(DatabaseHelper.colExerciseInstName, exName);
		  values.put(DatabaseHelper.colExerciseInstDistance, dist);		  
		  values.put(DatabaseHelper.colExerciseInstReps, reps);
		  values.put(DatabaseHelper.colExerciseInstSets, setts);
		  long insertId = database.insert(DatabaseHelper.exerciseInstanceTable, null, values);
		  Cursor cursor = database.query(DatabaseHelper.exerciseInstanceTable, allExerciseInstanceColumns, DatabaseHelper.colExerciseInstID + " = " + insertId, null, null, null, null);
		  System.out.println("Value of setts "+setts);
		  cursor.moveToFirst();
		  ExerciseInstance newExerciseInstance = cursorToExerciseInstance(cursor);
		  System.out.println("Value after insert"+newExerciseInstance.getExerciseInstSets());
		  cursor.close();
		  return newExerciseInstance;
		  
	  }
	  
	  public void deleteExerciseInstance(ExerciseInstance exerciseInstance) throws SQLException {
		  
		  long id = exerciseInstance.getId();
		  System.out.println("exerciseInstance deleted with id: " + id);
		  database.delete(DatabaseHelper.exerciseInstanceTable, DatabaseHelper.colExerciseInstID + " = " +id, null);
		  
	  }
  
	  public List<ExerciseInstance> getAllExercisesInstance(long trainingId) throws SQLException {
		    List<ExerciseInstance> exercisesInstance = new ArrayList<ExerciseInstance>();

		    Cursor cursor = database.query(DatabaseHelper.exerciseInstanceTable,
		        allExerciseInstanceColumns, DatabaseHelper.colExerciseInstTraining + " = " + trainingId, null, null, null, null);

		    cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		    	ExerciseInstance exerciseInstance = cursorToExerciseInstance(cursor);
		    	exercisesInstance.add(exerciseInstance);
		    	cursor.moveToNext();
		    }
		    // Make sure to close the cursor
		    cursor.close();
		    return exercisesInstance;
		  }
	  
	  public void updateExerciseSets(int exerciseSets, ExerciseInstance exerciseInstance) {
		  
		  long id = exerciseInstance.getId();
		  
		  ContentValues args = new ContentValues();
		   args.put(DatabaseHelper.colExerciseInstSets, exerciseSets);
		  
		  database.update(DatabaseHelper.exerciseInstanceTable, args, DatabaseHelper.colExerciseInstID + " = " + id, null);
		  
	  }
  
	  
	  //data manipulation schedule
	  
	  
	  
	  private Schedule cursorToSchedule(Cursor cursor) {
		  Schedule schedule = new Schedule();
		  	schedule.setId(cursor.getLong(0));
		  	schedule.setScheduleName(cursor.getString(1));
		  	return schedule;
		  }
	  
	  public Schedule createSchedule(String scd) throws SQLException {
		  
			ContentValues values = new ContentValues();
			values.put(DatabaseHelper.colScheduleName, scd);
			long insertId = database.insert(DatabaseHelper.scheduleTable, null, values);
			Cursor cursor = database.query(DatabaseHelper.scheduleTable, allScheduleColumns, DatabaseHelper.colScheduleID + " = " + insertId, null, null, null, null);
			cursor.moveToFirst();
			Schedule newSchedule = cursorToSchedule(cursor);
			cursor.close();
			return newSchedule;
		  
	  }
	  
	  public void deleteSchedule(Schedule schedule) throws SQLException {
		  
			long id = schedule.getId();
			System.out.println("Schedule deleted with id: " + id);
			database.delete(DatabaseHelper.scheduleTable, DatabaseHelper.colScheduleID + " = " +id, null);
		  
	  }
	  
	  public List<Schedule> getAllSchedule() throws SQLException {
		    List<Schedule> schedules = new ArrayList<Schedule>();

		    Cursor cursor = database.query(DatabaseHelper.scheduleTable,
		        allScheduleColumns, null, null, null, null, null);

		    cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		    	Schedule schedule = cursorToSchedule(cursor);
		    	schedules.add(schedule);
		    	cursor.moveToNext();
		    }
		    // Make sure to close the cursor
		    cursor.close();
		    return schedules;
		  }
	  
	  public ArrayList<String> duplicateSchedule() throws SQLException {
		  
		  	
		  	ArrayList<String> schedules = new ArrayList<String>();
		  	
		  	try{
		  	Cursor cursor = database.query(DatabaseHelper.scheduleTable,
		  			scheduleName, null, null, null, null, null);
		   
		  	cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		    	
		    	schedules.add(cursor.getString(0));
		    	cursor.moveToNext();
		    }
		    // Make sure to close the cursor
		    cursor.close();
		    
		  	return schedules;
	  		} catch (Exception e){
	  		e.printStackTrace();
	  		}
		   
		   return schedules;
		  
	  }
	  
	  //data manipulation training instance
	  
	  private TrainInstance cursorToTrainingInstance(Cursor cursor) {
		  	TrainInstance trainInstance = new TrainInstance();
		  	trainInstance.setId(cursor.getLong(0));
		  	trainInstance.setTrainingId(cursor.getLong(1));
		  	trainInstance.setScheduleId(cursor.getLong(2));
		  	
		  	
		  	return trainInstance;
		  }
	  
	  public TrainInstance createTrainingInstance(long trId,  long schId ) throws SQLException {
	 
			  ContentValues values = new ContentValues();
			  values.put(DatabaseHelper.colTrainingInstTraining, trId);
			  values.put(DatabaseHelper.colTrainingInstSchedule, schId);
			  long insertId = database.insert(DatabaseHelper.trainingInstanceTable, null, values);
			  Cursor cursor = database.query(DatabaseHelper.trainingInstanceTable, allTrainingInstanceColumns, DatabaseHelper.colTrainingInstID + " = " + insertId, null, null, null, null);
			  cursor.moveToFirst();
			  TrainInstance newTrainingInstance = cursorToTrainingInstance(cursor);
			  cursor.close();
			  return newTrainingInstance;
		  
	  }
	  
	  public void deleteTrainingInstance(long idTr) throws SQLException {
		  
		  	long id;
		  
		  	Cursor cursor = database.query(DatabaseHelper.trainingInstanceTable, allTrainingInstanceColumns , DatabaseHelper.colTrainingInstTraining + " = " + idTr,
 					null, null, null, null);
 			cursor.moveToFirst();
 			TrainInstance trainingInstance = cursorToTrainingInstance(cursor);
 			while (!cursor.isAfterLast() && cursor.getCount() > 0) {
 				  id = trainingInstance.getId();
 				  System.out.println("Training Instance deleted with id: " + id);
 				  database.delete(DatabaseHelper.trainingInstanceTable, DatabaseHelper.colTrainingInstID + " = " +id, null);
 				  cursor.moveToNext();
 			}
 			cursor.close();
 			
		  
	  }
	  
	  public List<Train> getAllTrainingInstance(long scheduleId) throws SQLException {
		    
		    List<Train> trainingList = new ArrayList<Train>();
		    String[] trainId = {DatabaseHelper.colTrainingInstTraining};

		    Cursor cursor = database.query(DatabaseHelper.trainingInstanceTable,
		        trainId, DatabaseHelper.colTrainingInstSchedule + " = " + scheduleId, null, null, null, null);
		    
		    cursor.moveToFirst();
		    while (!cursor.isAfterLast() && cursor.getCount() > 0) {
		    	Cursor trainingCursor = database.query(DatabaseHelper.trainingTable,
				        allTrainingColumns, DatabaseHelper.colTrainingID + " = " + cursor.getLong(0), null, null, null, null);
		    	if (trainingCursor.getCount() > 0 ){
		    	trainingCursor.moveToFirst();	
		    	Train training = cursorToTraining(trainingCursor);
		    	trainingList.add(training);
		    	}
		    	cursor.moveToNext();
		    	trainingCursor.close();
		    }
		    
		    // Make sure to close the cursor
		    cursor.close();
		   
		    return trainingList;
		  }

	
	  
}
