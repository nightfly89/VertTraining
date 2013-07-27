package com.adarq.verttraining;

import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ArrayAdapter;



public class ExerciseInstanceDialog extends DialogFragment {

	static TrainingDataSource datasource;
	static Exercise exrs;
	
	
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
		datasource = ManageTraining.datasource;
	    datasource.open();

	    List<Exercise> values = datasource.getAllExercises();
	    
	    final ArrayAdapter<Exercise> adapter = new ArrayAdapter<Exercise>(ManageTraining.mt,android.R.layout.simple_list_item_1, values);
		   
		
	    builder.setTitle(R.string.exerciseTitle)
	           .setAdapter(adapter, new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int which) {
	               
	            	exrs = (Exercise) adapter.getItem(which);
	            	
	            	DialogFragment newFragment = new CreateExerciseInstanceDialog();
			    	 newFragment.show(getFragmentManager(), "createExerciseInstance");
	            	   
	           }
	    });
	    return builder.create();
	}

}
