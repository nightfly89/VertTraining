package com.adarq.verttraining;

import android.app.DialogFragment;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;



public class CreateExerciseInstanceDialog extends DialogFragment {





		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
	        // Use the Builder class for convenient dialog construction
			
					
					
					
			
			
			        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			        
			        final LayoutInflater inflater = getActivity().getLayoutInflater();
			        
			        final View exDialogLayout = inflater.inflate(R.layout.dialog_exercise_instance_add, null);
			        
			
			        
			        builder.setView(exDialogLayout);
			        builder.setMessage(ExerciseInstanceDialog.exrs.getExerciseName());
			        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
				             

							@Override
				               public void onClick(DialogInterface dialog, int id) {
				            	  
				            	   final int setts, rep, dis;
				            	   
				            	   
				            	   
				            	   final EditText et1 = (EditText) exDialogLayout.findViewById(R.id.exerciseSets);
				            	   final EditText et2 = (EditText) exDialogLayout.findViewById(R.id.exerciseReps);
				            	   final EditText et3 = (EditText) exDialogLayout.findViewById(R.id.exerciseDist);
				            	   
				   	               
				            	   
				            	   if (et1.getText().toString().length() != 0) {
				            		   
				            		   setts = Integer.valueOf(et1.getText().toString());
				            	   }
				            	   else setts = 0;
				            	   
				            	   if (et2.getText().toString().length() != 0) {
				            	   rep = Integer.valueOf(et2.getText().toString());
				            	   }
				            	   else rep = 0;
				            	   
				            	   if (et3.getText().toString().length() != 0) {
				            	   dis = Integer.valueOf(et3.getText().toString());
				            	   }
				            	   else dis = 0;
				            	   
				            	  
				            	  //Validator 
				            	   
				            	  
				            			   		
				            			  
				            				   ManageTraining.datasource.open();
				            				 
				            				   ExerciseInstance a = ManageTraining.datasource.createExerciseInstance(ExerciseInstanceDialog.exrs.getId(), ManageTraining.trainingId, 
				            						   ExerciseInstanceDialog.exrs.getExerciseName(), dis, setts, rep);
				            				   System.out.println("value of setts after insert"+setts);
				            				   System.out.println("value of setts at update"+a.getExerciseInstSets());
				            				  
				            				   
				            				  
				            				   ManageTraining.datasource.updateExerciseSets(setts, a);
				            				   
				            				   
				            				   
				            				   ManageTraining.datasource.close();  
				            	         			            	
				            				   ManageTraining.mt.update();
				            				            			            	   
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


