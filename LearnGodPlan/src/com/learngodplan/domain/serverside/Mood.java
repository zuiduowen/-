package com.learngodplan.domain.serverside;

import cn.bmob.v3.BmobObject;

//Mood Bean
@SuppressWarnings("serial")
public class Mood extends BmobObject{
	private String moodAuthor;
	private String moodContent;
	
	public String getMoodAuthor() {
		return moodAuthor;
	}
	public void setMoodAuthor(String moodAuthor) {
		this.moodAuthor = moodAuthor;
	}
	public String getMoodContent() {
		return moodContent;
	}
	public void setMoodContent(String moodContent) {
		this.moodContent = moodContent;
	}
}
