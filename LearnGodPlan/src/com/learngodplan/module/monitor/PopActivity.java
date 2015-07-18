package com.learngodplan.module.monitor;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;

import com.example.learngodplan.R;

public class PopActivity extends Activity{
	public static PopActivity popInstance;
	public static Context ct;
	
    protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pop_activity_layout);
		
		popInstance = this;
		
    }
    
    public void onImWrongClick(View v){
    	PopActivityController.imWrong();
    }
    
    public void onIChangeMindClick(View v){
		new AlertDialog.Builder(PopActivity.this)
		.setTitle("确认退出学霸模式吗?")
		.setMessage("现在退出学习模式将不能得到奖励")
		.setNegativeButton("取消", new OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which) {
				PopActivityController.toDestop();
			}
		})
		.setPositiveButton("确定", new OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which) {
				PopActivityController.exitLearn();
			}
		})
		.show();
    }

}
