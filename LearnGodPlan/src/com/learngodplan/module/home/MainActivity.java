package com.learngodplan.module.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learngodplan.R;
import com.learngodplan.module.mood.FragmentMood;
import com.learngodplan.module.pet.FragmentPet;
import com.learngodplan.module.plan.FragmentPlan;
  
  
  
public class MainActivity extends FragmentActivity implements OnClickListener{  
  
    //定义4个Fragment的对象  
    public static  FragmentHome fgHome;  
    public static  FragmentPlan fgPlan;  
    public static FragmentPet fgPet;
    public static  FragmentMood fgPersonal;
    
    //定义底部导航栏的三个布局  
    public static RelativeLayout guideLayout_Home;  
    public static RelativeLayout guideLayout_Plan;  
    public static RelativeLayout guideLayout_Pet; 
    public static RelativeLayout guideLayout_Personal;
    
    //定义底部导航栏中的ImageView与TextView  
    public static  ImageView guideImg_Home;  
    public static ImageView guideImg_Plan;  
    public static ImageView guideImg_Pet;  
    public static ImageView guideImg_Personal;  
    
    public static  TextView guideText_Home;  
    public static TextView guideText_Plan;  
    public static TextView guideText_Pet;
    public static TextView guideText_Personal;
    
    //定义要用的颜色值  
    public static int whirt = 0xFFFFFFFF;  
    public static int gray = 0xFF7597B3;  
    public static int blue =0xFF0AB2FB;  
    
    public static Context mainAcContext;
    
    //定义FragmentManager对象  
    public static  FragmentManager fManager;  
    public static  FragmentTransaction transaction;
    
    //再按一次退出程序的按钮
    public static long exitTime = 0;
	
    @Override  
    protected void onCreate(Bundle savedInstanceState) {      	
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_main);  
        fManager = getSupportFragmentManager();  
        initViews();  
        mainAcContext = this;
        
        //默认显示home的fragment
        FragmentTransaction transaction = fManager.beginTransaction();    
        MainActivityController.clearChioce();  
        MainActivityController.hideFragments(transaction);  
        guideImg_Home.setImageResource(R.drawable.pressbottom_img_home);    
        guideText_Home.setTextColor(blue);  
        guideLayout_Home.setBackgroundResource(R.drawable.ic_tabbar_bg_click);  
        if (fgHome == null) {    
            // 如果fgHome为空，则创建一个并添加到界面上    
            fgHome = new FragmentHome();    
            transaction.add(R.id.content, fgHome);
            transaction.show(fgHome); 
        } else {    
            //如果MessageFragment不为空，则刷新它
            fgHome = new FragmentHome();   
        	transaction.replace(R.id.content, fgHome);
            transaction.show(fgHome);    
        }
        transaction.commit();
    }  
       
    public boolean onCreateOptionsMenu(Menu menu) { 
        // Inflate the menu; this adds items to the action bar if it is present.  
        getMenuInflater().inflate(R.menu.menu_logout, menu); 
        return true; 
    } 
    
    @Override 
    public boolean onOptionsItemSelected(MenuItem item) { 
    	return MainActivityController.selectOnOptionitem(item);
    }  
    
    //完成导航组件的初始化  
    public void initViews()  
    {  
        guideImg_Home = (ImageView) findViewById(R.id.guideImg_Home);  
        guideImg_Plan = (ImageView) findViewById(R.id.guideImg_Plan);  
        guideImg_Pet = (ImageView) findViewById(R.id.guideImg_Pet);
        guideImg_Personal = (ImageView) findViewById(R.id.guideImg_Personal);
        
        guideText_Home = (TextView) findViewById(R.id.guideText_Home);  
        guideText_Pet = (TextView) findViewById(R.id.guideText_Pet);  
        guideText_Plan = (TextView) findViewById(R.id.guideText_Plan); 
        guideText_Personal = (TextView)findViewById(R.id.guideText_Personal);
        
        guideLayout_Home = (RelativeLayout) findViewById(R.id.guideLayout_Home);  
        guideLayout_Plan = (RelativeLayout) findViewById(R.id.guideLayout_Plan);  
        guideLayout_Pet = (RelativeLayout) findViewById(R.id.guideLayout_Pet);  
        guideLayout_Personal = (RelativeLayout) findViewById(R.id.guideLayout_Personal);  
        
        guideLayout_Home.setOnClickListener(this);  
        guideLayout_Plan.setOnClickListener(this);   
        guideLayout_Pet.setOnClickListener(this);
        guideLayout_Personal.setOnClickListener(this);
        
        transaction = fManager.beginTransaction();  
    }  
      
    //重写onClick事件  
    @Override  
    public  void onClick(View view) {  
        switch (view.getId()) {  
        case R.id.guideLayout_Home:  
        	MainActivityController.setChioceItem(0);  
            break;  
        case R.id.guideLayout_Plan:  
        	MainActivityController.setChioceItem(1);  
            break;  
        case R.id.guideLayout_Pet:  
        	MainActivityController.setChioceItem(2);  
            break;  
        case R.id.guideLayout_Personal:  
        	MainActivityController.setChioceItem(3);  
            break;  
            
        default:  
            break;  
        }  
          
    }  
    
    //"再按一次退出程序"
    @Override
    public  boolean onKeyDown(int keyCode, KeyEvent event) {
	    if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
		    if((System.currentTimeMillis()-exitTime) > 1000){
			    Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
			    exitTime = System.currentTimeMillis();
		    } 
		    else {
			    System.exit(0);
			    finish();
		    }
		    return true;
	    }
	    return super.onKeyDown(keyCode, event);
    } 
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data){ 
    	super.onActivityResult(requestCode, resultCode, data);
    	MainActivityController.controlOnActivityResult(requestCode, resultCode, data);
    }
}