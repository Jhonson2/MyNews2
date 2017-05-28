package com.example.mynews;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import android.app.Activity;
import android.os.Bundle;
import android.transition.SidePropagation;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

public class MainActivity extends SlidingFragmentActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		// 必须在setContentView之前设置
		setContentView(R.layout.activity_main);
		setBehindContentView(R.layout.left_menu);
		
		SlidingMenu slidingMenu=getSlidingMenu();
		//全屏触摸
		slidingMenu .setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		//屏幕预留200像素宽度
		slidingMenu.setBehindOffset(200);
		
	}

}
