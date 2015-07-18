package com.learngodplan.module.pet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

public class PetAdoptController {
	public static void onChoosePet(AdapterView<?> parent, View view, int position, long id){
        // 绑定选择的宠物的类型,结束当前的Activity
        Intent intent = new Intent();
        Bundle bd = new Bundle();
        bd.putInt("petType", position+1);
        intent.putExtras(bd);

        // Set result and finish this Activity
        PetAdoptActivity.itan.setResult(Activity.RESULT_OK, intent);
        PetAdoptActivity.itan.finish();
	}
}
