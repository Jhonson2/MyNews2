package com.example.mynews;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

public class GuideActivity extends Activity{
	private ViewPager mViewPage;
	
	private ArrayList<ImageView> mImageViewList;//mImageViewList集合
	
	//引导页图片id数组
	private int[] mImageIds=new int[]{R.drawable.guide_1,
			R.drawable.guide_2,R.drawable.guide_3};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏 必须在setContentView之前设置
		setContentView(R.layout.activity_guide);
		
		 mViewPage=(ViewPager) findViewById(R.id.vp_guide);
		 initData();
		 mViewPage.setAdapter(new GuideAdapter());//设置数据
	}
	
	private void initData(){
		mImageViewList=new ArrayList<ImageView>();
		for(int i=0;i<mImageIds.length;i++){
			ImageView view=new ImageView(this);
			view.setBackgroundResource(mImageIds[i]);
			
			mImageViewList.add(view);
		}
		
	}
	
	class GuideAdapter extends PagerAdapter{
			//items的个数
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mImageViewList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0==arg1;
		}
		
		//初始化items的布局
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView view=mImageViewList.get(position);
			container.addView(view);
			return view;
		}
			
		//销毁items的布局
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
		
	}

}
