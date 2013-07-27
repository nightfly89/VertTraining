package com.adarq.verttraining;

import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class ScheduleInstanceDialog extends DialogFragment {

	static TrainingDataSource datasource;
	static Train trn;
	
	
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
		datasource = ManageSchedule.datasource;
	    datasource.open();

	    List<Train> values = datasource.getAllTraining();
	    
	    final ArrayAdapter<Train> adapter = new ArrayAdapter<Train>(ManageSchedule.ms,android.R.layout.simple_list_item_1, values);
		   
		
	    builder.setTitle(R.string.exerciseTitle)
	           .setAdapter(adapter, new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int which) {
	               
	            	trn = (Train) adapter.getItem(which);
	            	
	               ManageSchedule.datasource.open();
   				 
 				   ManageSchedule.datasource.createTrainingInstance(trn.getId(), ManageSchedule.scheduleId);
 				  
 				   ManageSchedule.datasource.close();  
 				   ManageSchedule.ms.update();
	            	   
	           }
	    });
	    return builder.create();
	}


}
