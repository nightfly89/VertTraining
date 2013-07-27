package com.adarq.verttraining;


import java.util.ArrayList;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import android.widget.Toast;


public class ExercisesDialog extends DialogFragment {
	

		            	   
	
	  

	 
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
		
				
				
				
		
		
		        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		        
		        final LayoutInflater inflater = getActivity().getLayoutInflater();
		        
		        final View exercisesDialogLayout = inflater.inflate(R.layout.dialog_add, null);
		        
		
		        
		        builder.setView(exercisesDialogLayout);
		        builder.setMessage(R.string.exerciseTitle);
		        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
			             

						@Override
			               public void onClick(DialogInterface dialog, int id) {
			            	  
			            	   final String name, desc;
			            	   boolean exists;
			            	   
			            	   final EditText et1 = (EditText) exercisesDialogLayout.findViewById(R.id.exerciseNm);
			            	   final EditText et2 = (EditText) exercisesDialogLayout.findViewById(R.id.exerciseDesc);
			   	               
			            	   
			            	   name = et1.getText().toString();
			            	   desc = et2.getText().toString();
			            	   
			            	  
			            	  //Validator 
			            	   
			            	  if( et1.getText().toString().length() == 0 ){
			            		  Toast.makeText(ManageExercise.me.getApplicationContext(),"You need to write a name",Toast.LENGTH_SHORT).show();
			            	  }
			            	  else if( et2.getText().toString().length() == 0 ){
			            		  Toast.makeText(ManageExercise.me.getApplicationContext(),"You need to write a description",Toast.LENGTH_SHORT).show();
			            	  }
			            		   else{
			            		//Updating the list
			            			   		
			            			   		exists = false;
			            			   		
			            			  
			            				   ManageExercise.datasource.open();
			            				   ArrayList<String> ex = new ArrayList<String>();
			            				   ex = ManageExercise.datasource.duplicateExercises();
			            				   
			            				   for(int i=0;i< ex.size();i++){
			            					   String exer = ex.get(i).toString();

			            					   if(exer.equals(name)){
			            					           exists = true;
			            					      }
			            					}
			            				   
			            				   if (exists == false){
			            				   ManageExercise.datasource.createExercise(name, desc);
			            				   }
			            				   else{
			            					   Toast.makeText(ManageExercise.me.getApplicationContext(),"Exercise already exists",Toast.LENGTH_SHORT).show();
			            				   }
			            				   ManageExercise.datasource.close();  
			            				   ManageExercise.me.update();
			            			            			   
			            			   
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
