package com.adarq.verttraining;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
//import android.database.Cursor;
import android.os.Bundle;

public class DeleteTrainingDialog extends DialogFragment {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
			
			
			
	        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	        builder.setMessage(R.string.exerciseDelete);
	        
	        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
		               @Override
		               public void onClick(DialogInterface dialog, int id) {

		            	   			ManageSchedule.datasource.open();  
		            	   			
		            	   			ManageSchedule.datasource.deleteTrainingInstance(ManageSchedule.ms.training.getId());
		            	   			ManageSchedule.datasource.close();  
		            	   			
		            	   			ManageSchedule.ms.update();
		    		            			            	   
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
