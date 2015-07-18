package com.learngodplan.module.mood;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.learngodplan.R;
import com.learngodplan.module.home.MainActivity;

public class AddMoodActivity extends Activity {
	public static int SUCCESS_CODE = 1;
	public static int FAIL_CODE = 2;
	
	public static Context ct;
	public static AddMoodController mHandler;
	public static EditText moodText;
	
	public String dbg = "debug";
	
    @Override  
    protected void onCreate(Bundle savedInstanceState) {     
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.new_mood_layout);  
        
        ActionBar acb = ((Activity) MainActivity.mainAcContext).getActionBar();
        acb.setTitle("新心情");
        
        ct = this;
        
        moodText = (EditText)findViewById(R.id.moodText);
		mHandler = new AddMoodController();

	}
    
    public void onSendMoodClick(View v){
    	AddMoodController.addMood();
    }
    
}
