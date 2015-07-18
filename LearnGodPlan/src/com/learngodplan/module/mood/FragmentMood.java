package com.learngodplan.module.mood;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.learngodplan.R;
import com.learngodplan.module.home.MainActivity;

public class FragmentMood extends Fragment {
        public static  FragmentTransaction transaction;
        
        public static ListView moodLv;
		private ImageButton refreshBt;
		private ImageButton newMoodBt;
		
		public static SimpleAdapter spAdp;
		public static Handler mHandler;
		
	    
		public void initViewMember(View v){
			moodLv = (ListView)v.findViewById(R.id.moodList);
			refreshBt = (ImageButton)v.findViewById(R.id.refreshButton);
			refreshBt.setOnClickListener(new OnClickListener(){
				public void onClick(View v){
					//按下刷新键刷新页面
					LoadMoodController.getDataFromServer();
				}
			});
			newMoodBt = (ImageButton)v.findViewById(R.id.newMoodButton);
			newMoodBt.setOnClickListener(new OnClickListener(){
				public void onClick(View v){
					//进入发表心情的页面
					Intent it = new Intent(MainActivity.mainAcContext, AddMoodActivity.class);
					startActivity(it);
				}
			});
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
			View view = inflater.inflate(R.layout.fragment_mood, container,false);
	        ActionBar acb = ((Activity) MainActivity.mainAcContext).getActionBar();
	        acb.setTitle("心情墙");
			
			initViewMember(view);
			mHandler = new LoadMoodController();
			LoadMoodController.getDataFromServer();
			
			return view;
		}	
		
}