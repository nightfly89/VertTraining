package com.adarq.verttraining;

import java.util.ArrayList;
import java.util.List;


import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ManageExercise extends ListActivity {
	
	static TrainingDataSource datasource;
	static ManageExercise me;
	static ArrayAdapter<Exercise> adapter;
	List<Exercise> values = new ArrayList<Exercise>();
	Exercise exercise;
	
	
	
	 public void onCreate(Bundle savedInstanceState) {
		    super.onCreate(savedInstanceState);
		    me = this;
		    setContentView(R.layout.manage_exercises_view);
		    datasource = new TrainingDataSource(this);
		    datasource.open();

		    List<Exercise> values = datasource.getAllExercises();

		    // Use the SimpleCursorAdapter to show the
		    // elements in a ListView
		    ArrayAdapter<Exercise> adapter = new ArrayAdapter<Exercise>(this,
		        android.R.layout.simple_list_item_1, values);
		    setListAdapter(adapter);
		    
		  }
	 
	 public void onClick(View view) {
		    @SuppressWarnings("unchecked")
		    ArrayAdapter<Exercise> adapter = (ArrayAdapter<Exercise>) getListAdapter();
		    
		    if (view.getId() == R.id.add) {
		    	
		    	 DialogFragment newFragment = new ExercisesDialog();
		    	 newFragment.show(getFragmentManager(), "exercises");
		    	 
			               
			    }
			    
		    adapter.notifyDataSetChanged();
		  }
	 
	 @Override
	  protected void onResume() {
		 
			super.onResume();    
		    datasource.open();
		    @SuppressWarnings("unchecked")
			ArrayAdapter<Exercise> adapter = (ArrayAdapter<Exercise>) getListAdapter();
		    adapter.clear();
		    adapter.addAll(datasource.getAllExercises());
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
		 	@SuppressWarnings("unchecked")
			ArrayAdapter<Exercise> adapter = (ArrayAdapter<Exercise>) getListAdapter();
		    adapter.clear();
		    adapter.addAll(datasource.getAllExercises());
		    adapter.notifyDataSetChanged();
		    datasource.close();
		 
	 	}
	 
	 @Override
	 protected void onListItemClick(ListView l, View v, int position, long id) {
		
			//super.onListItemClick(l, v, position, id);
		 
		 exercise = (Exercise) l.getItemAtPosition(position);
		 new AlertDialog.Builder(ManageExercise.this)
			.setTitle("Delete exercise")
			.setMessage("Exercise "+exercise.toString()+ " described as "+exercise.getExerciseDesc())
			.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dlg, int sumthin) {
		        	
		        	DialogFragment newFragment = new DeleteDialog();
			    	 newFragment.show(getFragmentManager(), "delete");
		        	
		        }
		    }).show();
	  
		 
		 
	 }

	
	

}
