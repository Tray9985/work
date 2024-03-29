package com.dirs.work;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.Toast;

public class Setting extends Activity {
	
	
	private RadioGroup RG_DisplayMode;
	private RadioGroup RG_ImageSize;
	private RadioButton RB_ZoomMode;
	private RadioButton RB_1MP;
	private RadioButton RB_2MP;
	private Button Btn_Start;
	private Spinner mSpinner;
	private int ImageNum;
	private String DisplayMode = "";
	private String ImageSize = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		RG_DisplayMode = (RadioGroup) findViewById(R.id.DisplayMode);
		RG_ImageSize = (RadioGroup) findViewById(R.id.ImageSize);
		RB_ZoomMode = (RadioButton) findViewById(R.id.Mode1);
		RB_1MP = (RadioButton) findViewById(R.id.one_MP);
		RB_2MP = (RadioButton) findViewById(R.id.two_MP);
		Btn_Start = (Button)findViewById(R.id.Start);
		Btn_Start.setOnClickListener(new onButtonClick());
		onClick cl = new onClick();
		RG_DisplayMode.setOnCheckedChangeListener(cl);
		RG_ImageSize.setOnCheckedChangeListener(cl);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.ImageNumberArray,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mSpinner = (Spinner)findViewById(R.id.ImageNum);
		mSpinner.setAdapter(adapter);
		mSpinner.setSelection(5);
		mSpinner.setPrompt("设置图片数量");
		mSpinner.setOnItemSelectedListener(new onSpinnerClick());
	}

	class onClick implements OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			if (RG_DisplayMode == group) {
				if (RB_ZoomMode.getId() == checkedId) {
					Toast.makeText(getApplicationContext(), "选择Java处理缩放",
							Toast.LENGTH_SHORT).show();
					DisplayMode = "Java";
				} else {
					Toast.makeText(getApplicationContext(), "选择Native处理缩放",
							Toast.LENGTH_SHORT).show();
					DisplayMode = "Native";
				}
			} else {
				if (RB_1MP.getId() == checkedId) {
					Toast.makeText(getApplicationContext(), "图片质量为一百万像素",
							Toast.LENGTH_SHORT).show();
					ImageSize = "1MP";
				} else if (RB_2MP.getId() == checkedId) {
					Toast.makeText(getApplicationContext(), "图片质量为两百万像素",
							Toast.LENGTH_SHORT).show();
					ImageSize = "2MP";
				} else {
					Toast.makeText(getApplicationContext(), "图片质量为三百万像素",
							Toast.LENGTH_SHORT).show();
					ImageSize = "3MP";
				}
			}
		}

	}
	
	class onButtonClick implements OnClickListener{

		@Override
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.Start:
				if(DisplayMode.length() == 0 || ImageSize.length() == 0){
					Toast.makeText(getApplicationContext(), "必须选择显示模式和图片质量!",Toast.LENGTH_LONG).show();
				}else{
					Intent intent = new Intent();
					Bundle mBundle = new Bundle();
					if(DisplayMode.equals("Java")){
						mBundle.putBoolean("Mode",true);
					}else{
						mBundle.putBoolean("Mode",false);
					}
					mBundle.putString("Size",ImageSize);
					mBundle.putInt("Number",ImageNum);
					intent.putExtras(mBundle);
					intent.setClass(getApplicationContext(), MainActivity.class);
					startActivity(intent);
							
				}
				break;
			}
			// TODO Auto-generated method stub
		}
		
	}
	
	class onSpinnerClick implements OnItemSelectedListener{

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			ImageNum = Integer.parseInt(arg0.getItemAtPosition(arg2).toString());
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			Toast.makeText(getApplicationContext(), "没有选择，使用默认值",Toast.LENGTH_LONG).show();
		}
		
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add("清空内存缓存");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		//清空内存缓存
		CacheHelper.getInstance().clearCache();
		Toast.makeText(getApplicationContext(), "内存缓存已清除",Toast.LENGTH_LONG).show();
		return super.onMenuItemSelected(featureId, item);
	}
	
	

}
