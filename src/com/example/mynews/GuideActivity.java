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

	private ArrayList<ImageView> mImageViewList;// mImageViewList����

	// ����ҳͼƬid����
	private int[] mImageIds = new int[] { R.drawable.guide_1,
			R.drawable.guide_2, R.drawable.guide_3 };
	// С�����ƶ�����
	private int mPointDis;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// ȥ��������
														// ������setContentView֮ǰ����
		setContentView(R.layout.activity_guide);

		mViewPage = (ViewPager) findViewById(R.id.vp_guide);
		llContainer = (LinearLayout) findViewById(R.id.ll_container);
		ivRedPoint = (ImageView) findViewById(R.id.iv_red_point);
		btnStart = (Button) findViewById(R.id.btn_start);

		initData();

		mViewPage.setAdapter(new GuideAdapter());// ��������
		// ����viewpager�Ļ��������¼�
		mViewPage.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// ĳ��ҳ�汻ѡ��
				//���ҳ����ʾ����İ�ť
				if (position == mImageViewList.size() - 1) {
					btnStart.setVisibility(View.VISIBLE);
				} else {
					btnStart.setVisibility(View.INVISIBLE);
				}
			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
				// ��ҳ�滬�������еĻص� ����������������ǰλ�� �ƶ���ƫ �����İٷֱ� �ƶ�ƫ����������
				System.out.println("��ǰλ��" + position + "�����İٷֱ�"
						+ positionOffset + "�ƶ�ƫ����������" + positionOffsetPixels);

				// ����С��Բ��ľ���=����С���ļ��*�ƶ���ƫ �����İٷֱ�
				int leftMargin = (int) (mPointDis * positionOffset) + mPointDis
						* position;// ��ǰС������߾�
				RelativeLayout.LayoutParams params = (LayoutParams) ivRedPoint
						.getLayoutParams();

				params.leftMargin = leftMargin;// �޸���߾�
				ivRedPoint.setLayoutParams(params);// ���²��ò��ֲ���
			}

			@Override
			public void onPageScrollStateChanged(int state) {
				// ��ҳ��״̬�仯�Ļص�

			}
		});

		// ��������СԲ��ľ���
		// �ƶ�����=�ڶ���СԲ��leftֵ-��һ��СԲ��leftֵ
		// measure->>layout->>draw(��onCreate�����������ִ��)
		/*
		 * mPointDis=llContainer.getChildAt(1).getLeft()
		 * -llContainer.getChildAt(0).getLeft();
		 * 
		 * System.out.println("Բ�����"+mPointDis);
		 */
		// ����layout�����������¼���λ��ȷ����֮��������ȡԲ����
		// ��ͼ��
		ivRedPoint.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {

					@Override
					public void onGlobalLayout() {
						// layout����ִ�н����Ļص�
						ivRedPoint.getViewTreeObserver()
								.removeGlobalOnLayoutListener(this);
						// ivRedPoint.getViewTreeObserver().removeOnGlobalLayoutListener(this);

						mPointDis = llContainer.getChildAt(1).getLeft()
								- llContainer.getChildAt(0).getLeft();

						System.out.println("Բ�����" + mPointDis);
					}
				});
		
		//��ť����¼�
		btnStart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//����sp,���ǵ�һ�ν���
				PrefUntils.setBoolean(getApplicationContext(), "is_first_enter", false);
				
				//��ת��ҳ
				Intent intent=new Intent(getApplicationContext(),MainActivity.class);
				startActivity(intent);
				finish();
				
				
			}
		});

	}

	// ��ʼ������
	private void initData() {
		// ��ʼ������ͼƬ
		mImageViewList = new ArrayList<ImageView>();
		for (int i = 0; i < mImageIds.length; i++) {
			ImageView view = new ImageView(this);
			view.setBackgroundResource(mImageIds[i]);

			mImageViewList.add(view);

			// ��ʼ��СԲ��
			ImageView point = new ImageView(this);
			point.setImageResource(R.drawable.shape_point_gray);// ����СԲ��ͼƬ

			// ��ʼ�����ֲ��� ��߰������� ���ؼ���˭ ����˭�������Ĳ��ֲ���
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);

			if (i > 0) {
				// �ӵڶ���СԲ��������߾�
				params.leftMargin = 10;
			}
			point.setLayoutParams(params);// ����Բ�����

			llContainer.addView(point);// ���������СԲ��
		}

	}

	class GuideAdapter extends PagerAdapter {
		// items�ĸ���
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mImageViewList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		// ��ʼ��items�Ĳ���
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView view = mImageViewList.get(position);
			container.addView(view);
			return view;
		}

		// ����items�Ĳ���
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

	}

}
