package com.adarq.verttraining;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ManageSchedule extends ListActivity {

	static TrainingDataSource datasource;
	static ManageSchedule ms;
	static ArrayAdapter<Train> adapter;
	List<Train> values = new ArrayList<Train>();
	Train training;
	TrainInstance trainingInstance;
	static long scheduleId;
	static String scheduleName;
	
	 public void onCreate(Bundle savedInstanceState) {
		    super.onCreate(savedInstanceState);
		    ms = this;
		    setContentView(R.layout.manage_exercises_view);
		    datasource = new TrainingDataSource(this);
		    datasource.open();
		    scheduleId = LoadSchedule.schd.getId();
		    scheduleName = LoadSchedule.schd.getScheduleName();
		    setTitle(scheduleName);

		    List<Train> values = datasource.getAllTrainingInstance(scheduleId);

		    // Use the SimpleCursorAdapter to show the
		    // elements in a ListView
		    ArrayAdapter<Train> adapter = new ArrayAdapter<Train>(this, android.R.layout.simple_list_item_1, values);
		    setListAdapter(adapter);
		    
		  }
	
	 public void onClick(View view) {
		   
		 @SuppressWarnings("unchecked")
		ArrayAdapter<Train> adapter = (ArrayAdapter<Train>) getListAdapter();
		    
		    if (view.getId() == R.id.add) {
		    	
		    	 DialogFragment newFragment = new ScheduleInstanceDialog();
		    	 newFragment.show(getFragmentManager(), "scheduleInstance");
		    	 
			               
			    }
			    
		    adapter.notifyDataSetChanged();
		  }
	 
	 @Override
	  protected void onResume() {
		 
			super.onResume();    
		    datasource.open();
		    
		    @SuppressWarnings("unchecked")
			ArrayAdapter<Train> adapter = (ArrayAdapter<Train>) getListAdapter();
		    adapter.clear();
		    adapter.addAll(datasource.getAllTrainingInstance(scheduleId));
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
			ArrayAdapter<Train> adapter = (ArrayAdapter<Train>) getListAdapter();
		    adapter.clear();
		    adapter.addAll(datasource.getAllTrainingInstance(scheduleId));
		    adapter.notifyDataSetChanged();
		    datasource.close();
		 
	 	}
	 
	 @Override
	 protected void onListItemClick(ListView l, View v, int position, long id) {
		
			//super.onListItemClick(l, v, position, id);
		 
		 training = (Train) l.getItemAtPosition(position);
		 new AlertDialog.Builder(ManageSchedule.this)
			.setTitle("View or delete exercise")
			.setMessage("Training "+training.toString())
			.setPositiveButton("View", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dlg, int sumthin) {
		        	
		        	final Intent in ;
		   		 
					
					in = new Intent(getApplicationContext(), ManageTraining.class);
					startActivity(in);
					ManageTraining.setTraining(training.getId());
					ManageTraining.setTrainingName(training.getTrainingName());
					
		        }
		        })
		     .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dlg, int sumthin) {
			        	
			        	DialogFragment newFragment = new DeleteTrainingDialog();
				    	 newFragment.show(getFragmentManager(), "deleteTraining");
			        }    
		    }).show();
	 }
	 
}
	 
