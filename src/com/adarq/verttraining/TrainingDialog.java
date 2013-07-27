package com.adarq.verttraining;



import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import android.widget.Toast;

public class TrainingDialog extends DialogFragment {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
		
				
				
				
		
		
		        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		        
		        final LayoutInflater inflater = getActivity().getLayoutInflater();
		        
		        final View trainingDialogLayout = inflater.inflate(R.layout.dialog_training_add, null);
		        
		
		        
		        builder.setView(trainingDialogLayout);
		        builder.setMessage(R.string.trainingTitle);
		        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
			             

						@Override
			               public void onClick(DialogInterface dialog, int id) {
			            	  
			            	   final String name;
			            	   boolean exists;
			            	   final Intent in ;
			            	   
			            	   
			            	   final EditText et1 = (EditText) trainingDialogLayout.findViewById(R.id.trainingNm);
			            	   
			   	               
			            	   
			            	   name = et1.getText().toString();
			            	   
			            	  
			            	  //Validator 
			            	   
			            	  if( et1.getText().toString().length() == 0 ){
			            		  Toast.makeText(Training.trainingActivity.getApplicationContext(),"You need to write a name",Toast.LENGTH_SHORT).show();
			            	  }
			            	    else{
			            		//Updating the list
			            			   		
			            			   		exists = false;
			            			   		
			            			  
			            				   Training.datasource.open();
			            				   ArrayList<String> ex = new ArrayList<String>();
			            				   ex = Training.datasource.duplicateTraining();
			            				   
			            				   for(int i=0;i< ex.size();i++){
			            					   String exer = ex.get(i).toString();

			            					   if(exer.equals(name)){
			            					           exists = true;
			            					      }
			            					}
			            				   
			            				   if (exists == false){
			            				   Training.datasource.createTraining(name);
			            				   }
			            				   else{
			            					   Toast.makeText(Training.trainingActivity.getApplicationContext(),"Training already exists",Toast.LENGTH_SHORT).show();
			            				   }
			            				   Training.datasource.close();  
			            				   
			            				   in = new Intent(Training.trainingActivity.getApplicationContext(), LoadTraining.class);
			           					   startActivity(in);
			            			            			   
			            			   
			            		   }
			            	         			            	
			            	
			            				            			            	   
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
