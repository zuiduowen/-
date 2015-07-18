package com.learngodplan.module.plan;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.learngodplan.R;

public class NewPlanActivity extends Activity {
	//View成员
	public static EditText taskNameEdit;
	public static EditText taskTimeEdit;
	public static DatePicker startDatePicker;
	public static DatePicker endDatePicker;
	public static ImageView star1;
	public static ImageView star2;
	public static ImageView star3;
	public static ImageView star4;
	public static ImageView star5;
	
	public static Context ct;
	public static NewPlanActivity itan;
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.new_plan);  
        
        ActionBar acb = ((Activity) NewPlanActivity.this).getActionBar();
        acb.setTitle("新建计划");
        
        ct = this;
        itan = this;
        
        
        initViewMember();
        initDataMember();
    }
    
    //初始化所有View
    public void initViewMember(){    	
    	taskNameEdit = (EditText)this.findViewById(R.id.newplan_taskNameEdit);
    	taskTimeEdit = (EditText)this.findViewById(R.id.newplan_taskTimeEdit);
    	star1 = (ImageView)this.findViewById(R.id.priorityStar1);
    	star2 = (ImageView)this.findViewById(R.id.priorityStar2);
    	star3 = (ImageView)this.findViewById(R.id.priorityStar3);
    	star4 = (ImageView)this.findViewById(R.id.priorityStar4);
    	star5 = (ImageView)this.findViewById(R.id.priorityStar5);
    }
    	
    
    public void initDataMember(){
    	NewPlanController.initDataMember();
    }
    
    
    public void onStarClick(View v){
    	NewPlanController.clickOnStar(v);
    }
    
   
    public void onAddClick(View v){    	
    	NewPlanController.clickOnAdd();
    }
    
    //获取task的全部属性，插入数据库
    public void getDataAndInsertIntoDB(){
    	NewPlanController.getDataAndInsert();
    }
}
