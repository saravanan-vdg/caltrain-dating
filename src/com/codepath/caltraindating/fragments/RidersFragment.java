package com.codepath.caltraindating.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codepath.caltraindating.R;
import com.codepath.caltraindating.R.layout;
import com.codepath.caltraindating.models.Callback;
import com.codepath.caltraindating.models.Checkin;
import com.codepath.caltraindating.models.Schedule;
import com.codepath.caltraindating.models.Train;
import com.parse.ParseUser;

public class RidersFragment extends Fragment{
	Train currentTrain = null;
	Listener listener = null;
	
	public interface Listener{
		ParseUser getUser();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View v = inflater.inflate(R.layout.fragment_riders, container,false);
		loadRiders(currentTrain);
		return v;
		
	}
	
	private void loadRiders(Train t){
		Checkin.getCheckins(listener.getUser(), currentTrain, Schedule.getNow(), new Callback<ArrayList<Checkin>>(){
			@Override
			public void complete(ArrayList<Checkin> checkins) {
				for(Checkin c: checkins){
					Log.d("tag","got checkin: "+c.getUser().getObjectId());
//					Log.d("tag","got checkin: "+c.getUser().getString("firstName"));
//					Log.d("tag","got checkin: "+c.getUser().getString("lastName"));
//					Log.d("tag","got checkin: "+c.getUser().getString("birthday"));
				}
			}
		});
	}

	public Train getCurrentTrain() {
		return currentTrain;
	}

	public void setCurrentTrain(Train currentTrain) {
		this.currentTrain = currentTrain;
	}

	public Listener getListener() {
		return listener;
	}

	public void setListener(Listener listener) {
		this.listener = listener;
	}
}
