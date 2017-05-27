package com.example.mynews;

import untils.PrefUntils;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;
/*
 * 
 * 
 * */

public class SplashActivity extends Activity {
	private  RelativeLayout rlRoot;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		rlRoot=(RelativeLayout) findViewById(R.id.rl_root);
		
		//��ת����
		RotateAnimation animRotate=new RotateAnimation(0, 360, 
				Animation.RELATIVE_TO_SELF, 0.5f, 
				Animation.RELATIVE_TO_SELF, 0.5f);
		animRotate.setDuration(1000);//������תʱ��1s
		animRotate.setFillAfter(true);//���ֽ���״̬
		
		
		//����
		ScaleAnimation animScale=new ScaleAnimation(0, 1,0,1,
				ScaleAnimation.RELATIVE_TO_SELF,0.5f,
				ScaleAnimation.RELATIVE_TO_SELF,0.5f);
		animScale.setDuration(1000);
		animScale.setFillAfter(true);
		
		//����
		AlphaAnimation animAlpha=new AlphaAnimation(0, 1);
		animAlpha.setDuration(1000);
		animAlpha.setFillAfter(true);
		
		//��������
		AnimationSet set=new AnimationSet(true);
		set.addAnimation(animRotate);
		set.addAnimation(animScale);
		set.addAnimation(animAlpha);
		
		//��������
		rlRoot.startAnimation(set);
		
		set.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
			
				
			}
			
			public void onAnimationRepeat(Animation animation) {
				
				
			}
			
			public void onAnimationEnd(Animation animation) {
				//������������תҳ��
					//�����һ�Σ���ת����������
					//������ת��ҳ��
				
				boolean isFirstEnter=PrefUntils.getBoolean(SplashActivity.this,
						"is_first_enter", true);
				
				Intent intent;
				if(isFirstEnter){
					//��ת����������
						 intent=new Intent(getApplicationContext(),GuideActivity.class);
				}else{
					//������ת��ҳ��	
					 intent=new Intent(getApplicationContext(),MainActivity.class);
				
					}
				startActivity(intent);
				finish();
			}
		});
		}

	
}
