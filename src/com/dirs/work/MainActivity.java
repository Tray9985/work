package com.dirs.work;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	private LoadImageHelper mLoadHelper = null;
	private boolean isZoom = true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Bundle mBundle = this.getIntent().getExtras();
		isZoom = mBundle.getBoolean("Mode");
		String Size = mBundle.getString("Size");
		int num = mBundle.getInt("Number");
		String str = "图片是否缩放:" + isZoom + " 图片质量:" + Size + " 显示数量:" + num;
		this.setTitle(str);
		ListView lv = (ListView)findViewById(R.id.list);
		List<String> data = new ArrayList<String>();
		//在这里指定图片的相对路径
		for(int i=0;i<num;++i){
			data.add(Size +"/test" + i +".jpg");
		}
		
		ImageAdapter adapter = new ImageAdapter(data,getApplicationContext(),isZoom);
		lv.setAdapter(adapter);
	}
	

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		//停止加载线程
		mLoadHelper.stop();
		mLoadHelper.clear();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mLoadHelper = LoadImageHelper.getInstance(isZoom);
		mLoadHelper.start();
	}
}
