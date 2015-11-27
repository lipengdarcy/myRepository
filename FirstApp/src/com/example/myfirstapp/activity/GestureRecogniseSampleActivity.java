package com.example.myfirstapp.activity;

import java.util.ArrayList;



import android.app.Activity;
import android.app.AlertDialog;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class GestureRecogniseSampleActivity extends Activity {
	GestureOverlayView gestureView;
	GestureLibrary gestureLibrary;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.gesture);
        gestureView = new GestureOverlayView(this);
        gestureLibrary = GestureLibraries.fromFile(Environment.getExternalStorageDirectory().getPath() + "/mygestures");
        if(gestureLibrary.load()){
        	Toast.makeText(GestureRecogniseSampleActivity.this, "装载成功", Toast.LENGTH_SHORT).show();
        }else{
        	Toast.makeText(GestureRecogniseSampleActivity.this, "装载失败", Toast.LENGTH_SHORT).show();
        }
        gestureView.addOnGesturePerformedListener(new OnGesturePerformedListener() {
			@Override
			public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
				// TODO Auto-generated method stub
				ArrayList<Prediction> prediction = gestureLibrary.recognize(gesture);
				ArrayList<String> list = new ArrayList<String>();
				for(Prediction p: prediction){
					String showMsg = "与手势[" + p.name + "]相似度为：" + p.score;
					list.add(showMsg);
				}
				if(list.size() > 0){
					ArrayAdapter aa = new ArrayAdapter(GestureRecogniseSampleActivity.this, android.R.layout.simple_dropdown_item_1line, list.toArray());
					new AlertDialog.Builder(GestureRecogniseSampleActivity.this).setAdapter(aa, null).setPositiveButton("确定", null).show();
				}else{
					Toast.makeText(GestureRecogniseSampleActivity.this, "没有找到匹配的手势", Toast.LENGTH_SHORT).show();
				}
			}
		});
    }
}