package com.adarq.verttraining;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.ListActivity;
import android.content.DialogInterface;
//import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
//import android.widget.ListView;
import android.widget.ListView;

public class ManageTraining extends ListActivity {

	static TrainingDataSource datasource;
	static ManageTraining mt;
	static ArrayAdapter<Exercise> adapter;
	List<Exercise> values = new ArrayList<Exercise>();
	Exercise exercise;
	ExerciseInstance exerciseInstance;
	static long trainingId;
	static String trainingName;
	
	 static void setTraining(long trainingsId){
		 
		 trainingId = trainingsId;
		 
	 }
	 
	 static void setTrainingName(String trainingsNm){
		 
		 trainingName = trainingsNm;
		 
	 }
	 
	
	 public void onCreate(Bundle savedInstanceState) {
		    super.onCreate(savedInstanceState);
		    mt = this;
		    setContentView(R.layout.manage_exercises_view);
		    datasource = new TrainingDataSource(this);
		    datasource.open();
		    setTitle(trainingName);

		    List<ExerciseInstance> values = datasource.getAllExercisesInstance(trainingId);

		    // Use the SimpleCursorAdapter to show the
		    // elements in a ListView
		    ExerciseInstanceAdapter adapter = new ExerciseInstanceAdapter(this, R.layout.list_item, values);
		    setListAdapter(adapter);
		    
		  }
	
	 public void onClick(View view) {
		   
		    ExerciseInstanceAdapter adapter = (ExerciseInstanceAdapter) getListAdapter();
		    
		    if (view.getId() == R.id.add) {
		    	
		    	 DialogFragment newFragment = new ExerciseInstanceDialog();
		    	 newFragment.show(getFragmentManager(), "exerciseInstance");
		    	 
			               
			    }
			    
		    adapter.notifyDataSetChanged();
		  }
	 
	 @Override
	  protected void onResume() {
		 
			super.onResume();    
		    datasource.open();
		    
		    ExerciseInstanceAdapter adapter = (ExerciseInstanceAdapter) getListAdapter();
		    adapter.clear();
		    adapter.addAll(datasource.getAllExercisesInstance(trainingId));
		    adapter.notifyDataSetChanged();
		    
		  }
		 
	 @Override
	  protected void onPause() {
		 
		    datasource.close();
		    
		    super.onPause();
	  	}
	 
	 //list updater
	 
	 protected void update() {
		
		 	datasource.open();
		 	
		 	ExerciseInstanceAdapter adapter = (ExerciseInstanceAdapter) getListAdapter();
		    adapter.clear();
		    adapter.addAll(datasource.getAllExercisesInstance(trainingId));
		    adapter.notifyDataSetChanged();
		    datasource.close();
		 
	 	}
	 
	 @Override
	 protected void onListItemClick(ListView l, View v, int position, long id) {
		
			//super.onListItemClick(l, v, position, id);
		 
		 exerciseInstance = (ExerciseInstance) l.getItemAtPosition(position);
		 new AlertDialog.Builder(ManageTraining.this)
			.setTitle("Delete exercise")
			.setMessage("Exercise "+exerciseInstance.toString())
			.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dlg, int sumthin) {
		        	
		        	DialogFragment newFragment = new DeleteInstanceDialog();
			    	 newFragment.show(getFragmentManager(), "deleteInstance");
		        	
		        }
		    }).show();
	 }
	
	  

}
