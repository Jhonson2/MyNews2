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
		requestWindowFeature(Window.FEATURE_NO_TITLE);// ȥ��������
		// ������setContentView֮ǰ����
		setContentView(R.layout.activity_main);
		setBehindContentView(R.layout.left_menu);
		
		SlidingMenu slidingMenu=getSlidingMenu();
		//ȫ������
		slidingMenu .setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		//��ĻԤ��200���ؿ��
		slidingMenu.setBehindOffset(500);
		
		
		initFragment();
		
	}
	
	//��ʼ��fragment����
	private void initFragment(){
	FragmentManager fm=	getSupportFragmentManager();
	
	//��ʼ����
	FragmentTransaction transaction=fm.beginTransaction();
	
	//��Fragment�滻 �沼�� ��������1���沼�ֵ�����id ����2��Ҫ�滻��Fragment�� ��3�����
	transaction.replace(R.id.fl_left_menu, new LeftMenuFragment(),TAG_LEFT_MENU);
	transaction.replace(R.id.fl_main, new ContentFragment(),TAG_CONTENT);
	
	//�ύ����
	transaction.commit();
	
	//���ݱ��
	//fm.findFragmentByTag(TAG_CONTENT);
	}

}
