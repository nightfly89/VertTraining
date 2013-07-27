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



public class LoadTraining extends ListActivity {

	static TrainingDataSource datasource;
	static LoadTraining lt;
	static ArrayAdapter<Train> adapter;
	List<Train> values = new ArrayList<Train>();
	Train training;
	static Train trng;
	
	
	
	 public void onCreate(Bundle savedInstanceState) {
		    super.onCreate(savedInstanceState);
		    lt = this;
		    datasource = new TrainingDataSource(this);
		    datasource.open();

		    List<Train> values = datasource.getAllTraining();

		    // Use the SimpleCursorAdapter to show the
		    // elements in a ListView
		    ArrayAdapter<Train> adapter = new ArrayAdapter<Train>(this,
		        android.R.layout.simple_list_item_1, values);
		    setListAdapter(adapter);
		    
		  }
	 
	
	 
	 @Override
	  protected void onResume() {
		 
			super.onResume();    
		    datasource.open();
		    @SuppressWarnings("unchecked")
			ArrayAdapter<Train> adapter = (ArrayAdapter<Train>) getListAdapter();
		    adapter.clear();
		    adapter.addAll(datasource.getAllTraining());
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
		    adapter.addAll(datasource.getAllTraining());
		    adapter.notifyDataSetChanged();
		    datasource.close();
		 
	 	}
	 
	 @Override
	 protected void onListItemClick(ListView l, View v, int position, long id) {
		
			//super.onListItemClick(l, v, position, id);
			final Intent in ;
		 
			trng = (Train) l.getItemAtPosition(position);
			in = new Intent(getApplicationContext(), ManageTraining.class);
			startActivity(in);
			ManageTraining.setTraining(trng.getId());
			ManageTraining.setTrainingName(trng.getTrainingName());
		
		    }
	  
		 
		 
	 }



