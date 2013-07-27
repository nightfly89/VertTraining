package com.adarq.verttraining;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;



public class DeleteDialog extends DialogFragment {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
			
	
			
	        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	        builder.setMessage(R.string.exerciseDelete);
	        
	        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
		               @Override
		               public void onClick(DialogInterface dialog, int id) {

		            	   			ManageExercise.datasource.open();  
		            	   			ManageExercise.datasource.deleteExercise(ManageExercise.me.exercise);
		            	   			ManageExercise.datasource.close();  
		            	   			
		            	   			ManageExercise.me.update();
		    		            			            	   
		               }
		           })
		     .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
		               public void onClick(DialogInterface dialog, int id) {
		                  
		               }
		           }); 
	      	
	    	
	       
		// Create the AlertDialog object and return it
		return builder.create();
		}
	
	

}
