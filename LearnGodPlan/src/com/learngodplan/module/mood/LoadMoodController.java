package com.learngodplan.module.mood;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.learngodplan.R;
import com.learngodplan.domain.serverside.Mood;
import com.learngodplan.module.home.MainActivity;
import com.learngodplan.webservice.LoadMoodRunnable;

public class LoadMoodController  extends Handler{
    private List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
    
    //用于生成Map
    private String[] moodContent={"","","","","","","","","",""};
    private String[] moodDate={"","","","","","","","","",""};
    private String[] moodName={"","","","","","","","","",""};
	
	public void initMap(Message msg){
		@SuppressWarnings("unchecked")
		List<Mood> moodList = (List<Mood>) msg.obj;
		int j =0; 
        for (Mood mood : moodList) {
            //获得playerName的信息
        	moodDate[j] = mood.getCreatedAt();
			moodName[j] =   mood.getMoodAuthor();
			moodContent[j] = mood.getMoodContent();
            //获得createdAt数据创建时间（注意是：createdAt，不是createAt）
            mood.getCreatedAt();
            j++;
        }
		
//		//根据"|"拆分服务器返回的心情串
//		String[] firstSplit = moodStr.split("\\|");
//		Log.d("xxx","hhhh");
//		//根据“&”拆分每条心情的 用户 时间 内容
//		Log.d("firstSplit.length", String.valueOf(firstSplit.length));
//		
//		for(int j = 0; j < firstSplit.length; j++){
//			String[] temp = firstSplit[j].split("&");
//			Log.d("temp length", String.valueOf(temp.length));
//			
//			moodName[j] =   "From: " + temp[0];
//			moodDate[j] =     temp[1];
//			moodContent[j] = temp[2];
//		}
		
		items = new ArrayList<Map<String, Object>>();  
		for (int i = 0; i < moodList.size(); i++) {  
		    Map<String, Object> listItem = new HashMap<String, Object>();  
		    listItem.put("moodName", moodName[i]);  
		    listItem.put("moodDate", moodDate[i]);  
		    listItem.put("moodContent", moodContent[i]);
		    
		    items.add(listItem);  
		}
	}
	
	public static void getDataFromServer(){			
		Thread thr = new Thread(new LoadMoodRunnable());
		thr.start();
	}
	
	@Override
	public void handleMessage(Message msg){
		if(msg.what == LoadMoodRunnable.SUCCESS_CODE){
			Log.d("load mood success", "test");
		
			initMap(msg);
			
	        /*SimpleAdapter的参数说明 
	         * 第一个参数 表示访问整个android应用程序接口，基本上所有的组件都需要 
	         * 第二个参数表示生成一个Map(String ,Object)列表选项 
	         * 第三个参数表示界面布局的id  表示该文件作为列表项的组件 
	         * 第四个参数表示该Map对象的哪些key对应value来生成列表项 
	         * 第五个参数表示来填充的组件 Map对象key对应的资源一依次填充组件 顺序有对应关系 
	         * */
			FragmentMood.spAdp = new SimpleAdapter(
					               MainActivity.mainAcContext, 
					               items, 
					               R.layout.mood_list_item, 
					               new String[]{"moodName", "moodDate", "moodContent"}, 
					               new int[] {R.id.moodFrom, R.id.moodTime, R.id.moodContent }
					        );
			
			FragmentMood.moodLv.setAdapter(FragmentMood.spAdp);
		}
		else{
			Toast.makeText(MainActivity.mainAcContext, "加载失败"+msg.obj.toString(), Toast.LENGTH_SHORT).show();
		}
	}
}
