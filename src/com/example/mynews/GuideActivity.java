package com.example.mynews;

import java.util.ArrayList;

import untils.PrefUntils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class GuideActivity extends Activity {
	private ViewPager mViewPage;
	private LinearLayout llContainer;
	private ImageView ivRedPoint;
	private Button btnStart;

	private ArrayList<ImageView> mImageViewList;// mImageViewList集合

	// 引导页图片id数组
	private int[] mImageIds = new int[] { R.drawable.guide_1,
			R.drawable.guide_2, R.drawable.guide_3 };
	// 小红点的移动距离
	private int mPointDis;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
														// 必须在setContentView之前设置
		setContentView(R.layout.activity_guide);

		mViewPage = (ViewPager) findViewById(R.id.vp_guide);
		llContainer = (LinearLayout) findViewById(R.id.ll_container);
		ivRedPoint = (ImageView) findViewById(R.id.iv_red_point);
		btnStart = (Button) findViewById(R.id.btn_start);

		initData();

		mViewPage.setAdapter(new GuideAdapter());// 设置数据
		// 设置viewpager的滑动监听事件
		mViewPage.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// 某个页面被选中
				//最后页面显示体验的按钮
				if (position == mImageViewList.size() - 1) {
					btnStart.setVisibility(View.VISIBLE);
				} else {
					btnStart.setVisibility(View.INVISIBLE);
				}
			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
				// 当页面滑动过程中的回调 ————参数：当前位置 移动的偏 移量的百分比 移动偏移量的像素
				System.out.println("当前位置" + position + "移量的百分比"
						+ positionOffset + "移动偏移量的像素" + positionOffsetPixels);

				// 更新小红圆点的距离=两个小红点的间距*移动的偏 移量的百分比
				int leftMargin = (int) (mPointDis * positionOffset) + mPointDis
						* position;// 当前小红点的左边距
				RelativeLayout.LayoutParams params = (LayoutParams) ivRedPoint
						.getLayoutParams();

				params.leftMargin = leftMargin;// 修改左边距
				ivRedPoint.setLayoutParams(params);// 重新布置布局参数
			}

			@Override
			public void onPageScrollStateChanged(int state) {
				// 当页面状态变化的回调

			}
		});

		// 计算两个小圆点的距离
		// 移动距离=第二个小圆点left值-第一个小圆点left值
		// measure->>layout->>draw(在onCreate方法结束后才执行)
		/*
		 * mPointDis=llContainer.getChildAt(1).getLeft()
		 * -llContainer.getChildAt(0).getLeft();
		 * 
		 * System.out.println("圆点距离"+mPointDis);
		 */
		// 监听layout方法结束的事件，位置确定好之后再来获取圆点间距
		// 视图树
		ivRedPoint.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {

					@Override
					public void onGlobalLayout() {
						// layout方法执行结束的回调
						ivRedPoint.getViewTreeObserver()
								.removeGlobalOnLayoutListener(this);
						// ivRedPoint.getViewTreeObserver().removeOnGlobalLayoutListener(this);

						mPointDis = llContainer.getChildAt(1).getLeft()
								- llContainer.getChildAt(0).getLeft();

						System.out.println("圆点距离" + mPointDis);
					}
				});
		
		//按钮点击事件
		btnStart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//更新sp,不是第一次进入
				PrefUntils.setBoolean(getApplicationContext(), "is_first_enter", false);
				
				//跳转主页
				Intent intent=new Intent(getApplicationContext(),MainActivity.class);
				startActivity(intent);
				finish();
				
				
			}
		});

	}

	// 初始化数据
	private void initData() {
		// 初始化新手图片
		mImageViewList = new ArrayList<ImageView>();
		for (int i = 0; i < mImageIds.length; i++) {
			ImageView view = new ImageView(this);
			view.setBackgroundResource(mImageIds[i]);

			mImageViewList.add(view);

			// 初始化小圆点
			ImageView point = new ImageView(this);
			point.setImageResource(R.drawable.shape_point_gray);// 设置小圆点图片

			// 初始化布局参数 宽高包裹内容 父控件是谁 就是谁的声明的布局参数
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);

			if (i > 0) {
				// 从第二个小圆点设置左边距
				params.leftMargin = 10;
			}
			point.setLayoutParams(params);// 设置圆点参数

			llContainer.addView(point);// 给容器添加小圆点
		}

	}

	class GuideAdapter extends PagerAdapter {
		// items的个数
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mImageViewList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		// 初始化items的布局
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView view = mImageViewList.get(position);
			container.addView(view);
			return view;
		}

		// 销毁items的布局
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

	}

}
