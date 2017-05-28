package com.example.mynews;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import fragment.ContentFragment;
import fragment.LeftMenuFragment;

import android.R.fraction;
import android.app.Activity;
import android.content.ContentValues;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

public class MainActivity extends SlidingFragmentActivity {
	private static final String TAG_LEFT_MENU="TAG_LEFT_MENU";
	private static final String TAG_CONTENT="TAG_CONTENT";

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
		slidingMenu.setBehindOffset(500);
		
		
		initFragment();
		
	}
	
	//初始化fragment创建
	private void initFragment(){
	FragmentManager fm=	getSupportFragmentManager();
	
	//开始事物
	FragmentTransaction transaction=fm.beginTransaction();
	
	//用Fragment替换 真布局 参数（参1：真布局的容器id ，参2：要替换的Fragment； 参3：标记
	transaction.replace(R.id.fl_left_menu, new LeftMenuFragment(),TAG_LEFT_MENU);
	transaction.replace(R.id.fl_main, new ContentFragment(),TAG_CONTENT);
	
	//提交事务
	transaction.commit();
	
	//根据标记
	//fm.findFragmentByTag(TAG_CONTENT);
	}

}
