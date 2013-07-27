package com.adarq.verttraining;

import android.app.Activity;
//import android.app.AlertDialog;
import android.app.DialogFragment;
//import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;


public class Training extends Activity {
	
	static TrainingDataSource datasource;
	static Training trainingActivity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_training);
		
		trainingActivity = this;
		datasource = new TrainingDataSource(this);
		
		//add list view
		ListView meniuLista= (ListView) findViewById(R.id.list);

		
//		//introducere resurse string in vector
		String[] mainMenu = getResources().getStringArray(R.array.mainMenu);
//		
//		//legarea resurselor vectorului in adaptor de lista
		
		ListAdapter adaptorLista = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mainMenu);
		meniuLista.setAdapter(adaptorLista);
		registerForContextMenu(meniuLista);
		
		meniuLista.setClickable(true);
		meniuLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			
			public void onItemClick(AdapterView<?> parent, View view, int position, long id){
				
				final Intent i ;
				
				
				switch (position){
				case 0:
					DialogFragment newFragment = new TrainingDialog();
			    	newFragment.show(getFragmentManager(), "training");
					break;
				case 1:
					i = new Intent(getApplicationContext(), LoadTraining.class);
					startActivity(i);
					break;
					
					
				case 2:
					DialogFragment newFragment1 = new ScheduleDialog();
			    	newFragment1.show(getFragmentManager(), "schedule");
					break;
					
				case 3:
					i = new Intent(getApplicationContext(), LoadSchedule.class);
					startActivity(i);
					break;
				case 4: i = new Intent(getApplicationContext(), ManageExercise.class);
						startActivity(i);
						break;
						
				
				
				}
				
				
			}
		});
		
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_training, menu);
		return true;
	}

}
