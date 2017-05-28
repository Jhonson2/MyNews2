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
		requestWindowFeature(Window.FEATURE_NO_TITLE);// ȥ��������
		// ������setContentView֮ǰ����
		setContentView(R.layout.activity_main);
		setBehindContentView(R.layout.left_menu);
		
		SlidingMenu slidingMenu=getSlidingMenu();
		//ȫ������
		slidingMenu .setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		//��ĻԤ��200���ؿ��
		slidingMenu.setBehindOffset(200);
		
	}

}
