package com.adarq.verttraining;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

	static final String dbName="vertDB";
	static final int dbVersion = 1;
	
	static final String exerciseTable = "Exercise";
	static final String colExerciseID = "IdExercise";
	static final String colExerciseName = "ExerciseName";
	static final String colExerciseDesc = "Description";
	
	static final String exerciseInstanceTable = "Exercise_Instance";
	static final String colExerciseInstID = "IdExerciseInstance";
	static final String colExerciseInstName = "ExerciseInstanceName";
	static final String colExerciseInstDistance = "Distance";
	static final String colExerciseInstSets = "ExerciseS";
	static final String colExerciseInstReps = "Reps";
	static final String colExerciseInstExercise = "ExerciseInstanceEx";
	static final String colExerciseInstTraining = "TrainingInstanceEx";
	
	static final String trainingTable= "Training";
	static final String colTrainingID = "IdTraining";
	static final String colTrainingName = "Name";
	
	static final String trainingInstanceTable = "Training_Instance";
	static final String colTrainingInstID = "IdTrainingInstance";
	static final String colTrainingInstTraining = "TrainingInstanceTr";
	static final String colTrainingInstSchedule = "Schedule";
	
	
	static final String scheduleTable= "Schedule";
	static final String colScheduleID = "IdSchedule";
	static final String colScheduleName = "Name";
			
	static final String viewEx = "ViewEx";
	static final String viewTraining = "ViewTraining";
	
	
	
	public DatabaseHelper(Context context, String dbName, int dbVersion) {
		
		super(context, dbName, null, dbVersion);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		db.execSQL("CREATE TABLE "+ exerciseTable+" ("+colExerciseID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
														colExerciseName +" TEXT ,"+ colExerciseDesc+" TEXT "+");");
		
		db.execSQL("CREATE TABLE "+ trainingTable+" ("+colTrainingID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
				colTrainingName+" TEXT " +");");
		
		
		db.execSQL("CREATE TABLE "+ scheduleTable+" ("+colScheduleID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
				colScheduleName+" TEXT " +");");
		
		db.execSQL("CREATE TABLE "+ exerciseInstanceTable +" ("+colExerciseInstID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
				colExerciseInstDistance +" INTEGER ,"+ colExerciseInstSets+" INTEGER ,"+colExerciseInstReps+" INTEGER ," + 
				colExerciseInstName+" TEXT ,"+ colExerciseInstTraining + " INTEGER ," + 
				colExerciseInstExercise + " INTEGER, FOREIGN KEY (" +colExerciseInstExercise +") REFERENCES " + exerciseTable  +" ("+colExerciseID+") ON UPDATE CASCADE ON DELETE SET NULL "+" FOREIGN KEY (" +
				colExerciseInstTraining +") REFERENCES " + trainingTable +" ("+colTrainingID+") ON UPDATE CASCADE ON DELETE SET NULL );");
		
		db.execSQL("CREATE TABLE "+ trainingInstanceTable+" ("+colTrainingInstID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
				colTrainingInstSchedule+" INTEGER , "+
				colTrainingInstTraining+" INTEGER, FOREIGN KEY(" +
				colTrainingInstTraining +") REFERENCES " + trainingTable +" ("+colTrainingID+") ON UPDATE CASCADE ON DELETE SET NULL "+", FOREIGN KEY ("+colTrainingInstSchedule+") REFERENCES "+ scheduleTable+" ("+
				colScheduleID+") ON UPDATE CASCADE ON DELETE SET NULL );");
		
		/*
		db.execSQL("CREATE TRIGGER fk_exiexerc_exerciseid " +
				    " BEFORE INSERT "+
				    " ON "+exerciseInstanceTable+
				    
				    " FOR EACH ROW BEGIN"+
				    " SELECT CASE WHEN ((SELECT "+colExerciseID+" FROM "+exerciseTable+ 
				    "WHERE "+colExerciseID+"=new."+colExerciseInstExercise+" ) IS NULL)"+
				    " THEN RAISE (ABORT,'Foreign Key Violation') END;"+
				    "  END;");
		
		db.execSQL("CREATE TRIGGER fk_exitrain_trainingid " +
			    " BEFORE INSERT "+
			    " ON "+exerciseInstanceTable+
			    
			    " FOR EACH ROW BEGIN"+
			    " SELECT CASE WHEN ((SELECT "+colTrainingID+" FROM "+trainingTable+ 
			    "WHERE "+colTrainingID+"=new."+colExerciseInstTraining+" ) IS NULL)"+
			    " THEN RAISE (ABORT,'Foreign Key Violation') END;"+
			    "  END;");
		
		db.execSQL("CREATE TRIGGER fk_tritrain_scheduleid " +
			    " BEFORE INSERT "+
			    " ON "+trainingInstanceTable+
			    
			    " FOR EACH ROW BEGIN"+
			    " SELECT CASE WHEN ((SELECT "+colScheduleID+" FROM "+scheduleTable+ 
			    "WHERE "+colScheduleID+"=new."+colTrainingInstSchedule+" ) IS NULL)"+
			    " THEN RAISE (ABORT,'Foreign Key Violation') END;"+
			    "  END;");
			    
			*/  
		
		 db.execSQL("CREATE VIEW "+viewEx+
				    " AS SELECT "+exerciseTable+"."+colExerciseID+" AS _id,"+
				    " "+exerciseTable+"."+colExerciseName+","+
				    " "+exerciseTable+"."+colExerciseDesc+" "+
				    " FROM "+exerciseTable+" ;"
				    );
		 
		 db.execSQL("CREATE VIEW "+viewTraining+
				    " AS SELECT "+trainingTable+"."+colTrainingID+" AS _id,"+
				    " "+trainingTable+"."+colTrainingName+" "+
				    " FROM "+trainingTable+" ;"
				    );


	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
		if (oldVersion == 1 && newVersion == 2) {
			
		
		 db.execSQL("DROP TABLE IF EXISTS "+exerciseTable);
		 db.execSQL("DROP TABLE IF EXISTS "+exerciseInstanceTable);
		 db.execSQL("DROP TABLE IF EXISTS "+trainingInstanceTable);
		 db.execSQL("DROP TABLE IF EXISTS "+scheduleTable);
		 db.execSQL("DROP TABLE IF EXISTS "+trainingTable);
		 
		 
		/*
		 db.execSQL("DROP TRIGGER IF EXISTS fk_exiexerc_exerciseid");
		 db.execSQL("DROP TRIGGER IF EXISTS fk_exitrain_trainingid");
		 db.execSQL("DROP TRIGGER IF EXISTS fk_tritrain_scheduleid");
		*/
		 
		 db.execSQL("DROP VIEW IF EXISTS "+viewEx);
		 db.execSQL("DROP VIEW IF EXISTS "+viewTraining);
		 onCreate(db);
		}
		 
		
		
		
	}
	
	

	
	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);
		if (!db.isReadOnly()) {
			// Enable foreign key constraints
			db.execSQL("PRAGMA foreign_keys=ON;");
			}
	}

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
