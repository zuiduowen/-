package com.learngodplan.module.home;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.learngodplan.R;
import com.learngodplan.module.mood.FragmentMood;
import com.learngodplan.module.mood.LogRegActivity;
import com.learngodplan.module.pet.FragmentPet;
import com.learngodplan.module.plan.FragmentPlan;

public class MainActivityController {
	public static boolean selectOnOptionitem(MenuItem item){
	        switch (item.getItemId()) { 
	        case R.id.logout_option: {
	        	MainActivity.transaction = MainActivity.fManager.beginTransaction();			
	
				    SharedPreferences sharedPreferences = MainActivity.mainAcContext.getSharedPreferences("userInfo",  Activity.MODE_PRIVATE);  
				    
					if(sharedPreferences.getBoolean("hasLog", false)){
						Editor editor = sharedPreferences.edit();//获取编辑器  
						editor.putBoolean("hasLog", false);
						editor.commit();//提交修改  
						//返回计划首页
						
				        //重置选项+隐藏所有Fragment  
				        FragmentTransaction transaction = MainActivity.fManager.beginTransaction();    
				        MainActivity.guideImg_Personal.setImageResource(R.drawable.unpressbottom_img_personal);  
				        MainActivity.guideLayout_Personal.setBackgroundColor(MainActivity.whirt);  
				        MainActivity.guideText_Personal.setTextColor(MainActivity.gray);  
						
			            MainActivity.guideImg_Home.setImageResource(R.drawable.pressbottom_img_home);    
			            MainActivity.guideText_Home.setTextColor( MainActivity.blue);  
			            MainActivity.guideLayout_Home.setBackgroundResource(R.drawable.ic_tabbar_bg_click);
			            
			            if ( MainActivity.fgHome == null) {    
			            	 MainActivity.fgHome = new FragmentHome();    
			            	 transaction.add(R.id.content,  MainActivity.fgHome);    
			            } else {    
			            	 MainActivity.fgHome = new FragmentHome(); 
			            	 transaction.replace(R.id.content,  MainActivity.fgHome);
			            	 transaction.show( MainActivity.fgHome);    
			            } 
			            
			            transaction.commit();
			            Toast.makeText(MainActivity.mainAcContext, "注销成功", Toast.LENGTH_SHORT).show();
					}
					else{
						Toast.makeText(MainActivity.mainAcContext, "您目前还没有登陆", Toast.LENGTH_SHORT).show();
					}
	        }
	        break; 
	      
	        default: 
	        break; 
	   } 
	
	   return true; 
	}
	
    //定义一个选中一个item后的处理  
    public static  void setChioceItem(int index)  
    {  
        //重置选项+隐藏所有Fragment  
        FragmentTransaction transaction = MainActivity.fManager.beginTransaction();    
        clearChioce();  
        hideFragments(transaction);  
        switch (index) {  
        case 0:  
        	MainActivity.guideImg_Home.setImageResource(R.drawable.pressbottom_img_home);    
            MainActivity.guideText_Home.setTextColor(MainActivity.blue);  
            MainActivity.guideLayout_Home.setBackgroundResource(R.drawable.ic_tabbar_bg_click);  
            if (MainActivity.fgHome == null) {    
            	MainActivity.fgHome = new FragmentHome();    
                transaction.add(R.id.content, MainActivity.fgHome);    
            } else {    
            	MainActivity.fgHome = new FragmentHome(); 
            	transaction.replace(R.id.content, MainActivity.fgHome);
                transaction.show(MainActivity.fgHome);    
            }    
            break;    
  
        case 1:  
        	MainActivity.guideImg_Plan.setImageResource(R.drawable.pressbottom_img_plan);    
        	MainActivity.guideText_Plan.setTextColor(MainActivity.blue);  
        	MainActivity.guideLayout_Plan.setBackgroundResource(R.drawable.ic_tabbar_bg_click);  
            if (MainActivity.fgPlan == null) {    
            	MainActivity.fgPlan = new FragmentPlan();    
                transaction.add(R.id.content, MainActivity.fgPlan);    
            } else {     
            	MainActivity.fgPlan= new FragmentPlan(); 
            	transaction.replace(R.id.content, MainActivity.fgPlan);
                transaction.show(MainActivity.fgPlan);  
            }    
            break;        
          
         case 2:  
        	 MainActivity.guideImg_Pet.setImageResource(R.drawable.pressbottom_img_pet);    
            MainActivity.guideText_Pet.setTextColor(MainActivity.blue);  
            MainActivity.guideLayout_Pet.setBackgroundResource(R.drawable.ic_tabbar_bg_click); 
            if (MainActivity.fgPet == null) {      
            	MainActivity.fgPet = new FragmentPet();    
                transaction.add(R.id.content, MainActivity.fgPet);    
            } else {    
            	MainActivity.fgPet = new FragmentPet(); 
            	transaction.replace(R.id.content, MainActivity.fgPet);
                transaction.show(MainActivity.fgPet);  
            }    
            break;
            
         case 3:  
             //进入心情墙前，检查用户是否登陆，登陆则转入心情墙，未登录转入注册或登陆界面
        	 SharedPreferences preferences = MainActivity.mainAcContext.getSharedPreferences("userInfo",   Activity.MODE_PRIVATE);  
             boolean login = preferences.getBoolean("hasLog", false);
             if(login){
            	 MainActivity.guideImg_Personal.setImageResource(R.drawable.pressbottom_img_personal);    
                 MainActivity.guideText_Personal.setTextColor(MainActivity.blue);  
                 MainActivity.guideLayout_Personal.setBackgroundResource(R.drawable.ic_tabbar_bg_click); 
                 
                 if (MainActivity.fgPersonal == null) { 
                	 MainActivity.fgPersonal = new FragmentMood();    
                     transaction.add(R.id.content, MainActivity.fgPersonal);    
                 } else {     
                	 MainActivity.fgPersonal = new FragmentMood(); 
                 	 transaction.replace(R.id.content, MainActivity.fgPersonal);
                     transaction.show(MainActivity.fgPersonal);  
                 }   	 
             }
             else{
//                 guideImg_Home.setImageResource(R.drawable.pressbottom_img_home);    
//                 guideText_Home.setTextColor(blue);  
//                 guideLayout_Home.setBackgroundResource(R.drawable.ic_tabbar_bg_click);  
                 
                 if (MainActivity.fgHome == null) { 
                	 MainActivity.fgHome = new FragmentHome();    
                     transaction.add(R.id.content, MainActivity.fgHome);    
                 } else {     
                	 MainActivity.fgHome = new FragmentHome(); 
                 	 transaction.replace(R.id.content, MainActivity.fgHome);
                     transaction.show(MainActivity.fgHome);  
                 }   	
                 
            	 Intent it = new Intent(MainActivity.mainAcContext ,LogRegActivity.class);
            	 ((Activity) MainActivity.mainAcContext).startActivityForResult(it , 3);
             }
             break;     
        }  
        transaction.commit();  
    }  
    
    //隐藏所有的Fragment,避免fragment混乱  
    public static void hideFragments(FragmentTransaction transaction) {    
        if (MainActivity.fgHome != null) {    
            transaction.hide(MainActivity.fgHome);    
        }    
        if (MainActivity.fgPlan != null) {    
            transaction.hide(MainActivity.fgPlan);    
        }    
        if (MainActivity.fgPet != null) {    
            transaction.hide(MainActivity.fgPet);    
        }
        if (MainActivity.fgPersonal != null) {    
            transaction.hide(MainActivity.fgPersonal);    
        }
    }  
    
    //定义一个重置所有选项的方法  
    public static void clearChioce()  
    {  
    	MainActivity.guideImg_Home.setImageResource(R.drawable.unpressbottom_img_home);  
    	MainActivity.guideLayout_Home.setBackgroundColor(MainActivity.whirt);  
    	MainActivity.guideText_Home.setTextColor(MainActivity.gray);
        
    	MainActivity.guideImg_Plan.setImageResource(R.drawable.unpressbottom_img_plan);  
    	MainActivity.guideLayout_Plan.setBackgroundColor(MainActivity.whirt);  
    	MainActivity.guideText_Plan.setTextColor(MainActivity.gray);  
        
    	MainActivity.guideImg_Pet.setImageResource(R.drawable.unpressbottom_img_pet);  
    	MainActivity.guideLayout_Pet.setBackgroundColor(MainActivity.whirt);  
    	MainActivity.guideText_Pet.setTextColor(MainActivity.gray);  
        
    	MainActivity.guideImg_Personal.setImageResource(R.drawable.unpressbottom_img_personal);  
    	MainActivity.guideLayout_Personal.setBackgroundColor(MainActivity.whirt);  
    	MainActivity.guideText_Personal.setTextColor(MainActivity.gray);  
    } 
    
    public static void controlOnActivityResult(int requestCode, int resultCode, Intent data){
        Log.e("ee","call here in mainactivity onActivityResult");
    	if(resultCode == Activity.RESULT_OK && requestCode == 3){
            //重置选项+隐藏所有Fragment    
            clearChioce();  
            hideFragments(MainActivity.transaction); 
            Log.d("on ActivityResult is called", "when log activity is finished");
            MainActivity.guideImg_Personal.setImageResource(R.drawable.pressbottom_img_personal);    
            MainActivity.guideText_Personal.setTextColor(MainActivity.blue);  
            MainActivity.guideLayout_Personal.setBackgroundResource(R.drawable.ic_tabbar_bg_click); 

            if (MainActivity.fgPersonal == null) { 
            	MainActivity.fgPersonal = new FragmentMood();    
            	MainActivity.transaction.add(R.id.content, MainActivity.fgPersonal);    
            } else {     
            	MainActivity.fgPersonal = new FragmentMood(); 
            	MainActivity.transaction.replace(R.id.content, MainActivity.fgPersonal);
            	MainActivity. transaction.show(MainActivity.fgPersonal);  
            } 
            MainActivity.transaction.commitAllowingStateLoss();  
            return;
    	}
    	
		//如果当时启动的是选择宠物的Activity,就更新当前显示的宠物,更新BasicInfo中的basic_type列
		if(requestCode == FragmentPet.REQUEST_CHOOSE_PET && resultCode == Activity.RESULT_OK){
             //调用宠物fragment的onActivityResult
			MainActivity.fgPet.onActivityResult(requestCode, resultCode, data);
		}
    }
}
