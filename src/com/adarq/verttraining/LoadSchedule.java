package com.adarq.verttraining;

import android.app.ListActivity;

import java.util.ArrayList;
import java.util.List;


//import android.app.AlertDialog;
//import android.app.DialogFragment;
//import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;



public class LoadSchedule extends ListActivity {

	static TrainingDataSource datasource;
	static LoadSchedule ls;
	static ArrayAdapter<Schedule> adapter;
	List<Schedule> values = new ArrayList<Schedule>();
	Schedule schedule;
	static Schedule schd;
	
	
	
	 public void onCreate(Bundle savedInstanceState) {
		    super.onCreate(savedInstanceState);
		    ls = this;
		    datasource = new TrainingDataSource(this);
		    datasource.open();

		    List<Schedule> values = datasource.getAllSchedule();

		    // Use the SimpleCursorAdapter to show the
		    // elements in a ListView
		    ArrayAdapter<Schedule> adapter = new ArrayAdapter<Schedule>(this,
		        android.R.layout.simple_list_item_1, values);
		    setListAdapter(adapter);
		    
		  }
	 
	
	 
	 @Override
	  protected void onResume() {
		 
			super.onResume();    
		    datasource.open();
		    @SuppressWarnings("unchecked")
			ArrayAdapter<Schedule> adapter = (ArrayAdapter<Schedule>) getListAdapter();
		    adapter.clear();
		    adapter.addAll(datasource.getAllSchedule());
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
			ArrayAdapter<Schedule> adapter = (ArrayAdapter<Schedule>) getListAdapter();
		    adapter.clear();
		    adapter.addAll(datasource.getAllSchedule());
		    adapter.notifyDataSetChanged();
		    datasource.close();
		 
	 	}
	 
	 @Override
	 protected void onListItemClick(ListView l, View v, int position, long id) {
		
			//super.onListItemClick(l, v, position, id);
			final Intent in ;
		 
			schd = (Schedule) l.getItemAtPosition(position);
			in = new Intent(getApplicationContext(), ManageSchedule.class);
			startActivity(in);
		
		    }
	  
		 
		 
	 }
