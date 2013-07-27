package com.adarq.verttraining;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ExerciseInstanceAdapter extends ArrayAdapter<ExerciseInstance> {
	
	Context context;

	public ExerciseInstanceAdapter(Context context, int resource,
			List<ExerciseInstance> objects) {
		super(context, resource, objects);
		this.context = context;
	}

	private class ViewHolder {
		
		TextView name;
		TextView dist;
		TextView reps;
		TextView sets;
		
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        ExerciseInstance exerciseInstance = getItem(position);
 
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.nm);
            holder.dist = (TextView) convertView.findViewById(R.id.di);
            holder.sets = (TextView) convertView.findViewById(R.id.se);
            holder.reps = (TextView) convertView.findViewById(R.id.re);
            convertView.setTag(holder);
        } else 
            holder = (ViewHolder) convertView.getTag();
 
        holder.name.setText(String.valueOf(exerciseInstance.getExerciseInstName()));
        holder.dist.setText(" Distance: "+String.valueOf(exerciseInstance.getExerciseInstDist()));
        holder.sets.setText(" No. Sets: "+String.valueOf(exerciseInstance.getExerciseInstSets()));
        holder.reps.setText(" No. Reps: "+String.valueOf(exerciseInstance.getExerciseInstReps()));
        
        return convertView;
    }
	

}
