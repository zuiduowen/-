package com.learngodplan.module.plan;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;
import android.widget.ImageView;

import com.example.learngodplan.R;
import com.learngodplan.db.FoodDBO;
import com.learngodplan.db.TaskDBO;
import com.learngodplan.module.home.MainActivity;

public class InstantTaskActivity extends ActionBarActivity implements OnChronometerTickListener{
	public static ImageView bottomBtIv;
	public static ImageView halfView;
	public static ImageView oneView;
	public static ImageView twoView;
	public static ImageView threeView;
	public static ImageView chestView;
    public static Chronometer ch;
    
    public static Context ct;
    public static InstantTaskActivity itan;
    
    @Override
    protected void onPause(){
    	super.onPause();
    	InstantTaskController.toPause();
    }
    
    @Override
    protected void onDestroy(){
    	super.onDestroy();
    	InstantTaskController.toDestroy();
    }
    
	@Override
    protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shortplan);
		
		ActionBar acb = this.getActionBar();
		acb.setTitle("即时学习任务");
		
		ct = this;
		itan = this;
		
		InstantTaskController.foodDBO = new FoodDBO(MainActivity.mainAcContext);
		InstantTaskController.allFood = InstantTaskController.foodDBO.getAllFood();
		
		//tf = Typeface.createFromAsset(getAssets(), "DigitalClock.ttf");
		ch = (Chronometer)findViewById(R.id.chronometer);
		//ch.setTypeface(tf);
		ch.setOnChronometerTickListener(this);
		ch.setText("00:00:00");
        bottomBtIv = (ImageView)findViewById(R.id.shortPlanBottomButton);
        halfView = (ImageView)findViewById(R.id.halfBt);
        oneView = (ImageView)findViewById(R.id.oneBt);
        twoView = (ImageView)findViewById(R.id.twoBt);
        threeView = (ImageView)findViewById(R.id.threeBt);
        chestView = (ImageView)findViewById(R.id.shortPlanChest);
        
        //获取来自Fragment的Task数据
        InstantTaskController.getTaskDataFromIntent();
        //初始化数据库接口
        InstantTaskController.tDBO = new TaskDBO(MainActivity.mainAcContext);
	}
    
	public void onBottomButtonClick(View v){
		InstantTaskController.clickOnBottomButton();
	}
	
	public void onHalfClick(View v){
		InstantTaskController.clickOnHalf();
	}
	
	public void onOneClick(View v){
		InstantTaskController.clickOnone();
	}
	
	public void onTwoClick(View v){
		InstantTaskController.clickOnTwo();
	}
	
	public void onThreeClick(View v){
		InstantTaskController.clickOnThree();
	}
	
	//点击宝箱之后，弹出随机奖励的食物，重置所有按钮，隐藏宝箱
	public void onChestClick(View v){
		LayoutInflater popInflater = (LayoutInflater)InstantTaskActivity.ct.getSystemService(LAYOUT_INFLATER_SERVICE);
		InstantTaskController.clickOnChest(popInflater);
	}
	
	@Override
	public void onChronometerTick(Chronometer ch) {
		InstantTaskController.ticker(ch);
	}
	
	
    @Override
    public  boolean onKeyDown(int keyCode, KeyEvent event) {
    	if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
    		//按下返回键时，弹出窗口提示是否退出学习模式
    		new AlertDialog.Builder(InstantTaskActivity.ct)
			.setTitle("确认退出学霸模式")
			.setMessage("现在退出学习模式将不能得到奖励")
			.setNegativeButton("取消", new OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				}
			})
			.setPositiveButton("确定", new OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					InstantTaskActivity.itan.finish();
				}
			})
			.show();
    		return false;
    	}
    	else{
        	return super.onKeyDown(keyCode, event);
    	}
    } 
	
}
