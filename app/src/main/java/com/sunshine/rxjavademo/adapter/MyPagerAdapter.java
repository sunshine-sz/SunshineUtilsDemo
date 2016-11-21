package com.sunshine.rxjavademo.adapter;

import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;

public class MyPagerAdapter extends PagerAdapter {

	private Context context;
	private List<SimpleDraweeView> imageViews;
	public MyPagerAdapter(Context context,List<SimpleDraweeView> imageViews){
		this.context = context;
		this.imageViews = imageViews;
	}
	
	@Override
	public int getCount() {
		return imageViews.size()<=1?1:Integer.MAX_VALUE;//imageViews.size();//5
	}
	
	/**
	 * 类似于getView方法();
	 * container:他是ViewPager,容器的作用
	 * position：对应页面的位置
	 */
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		
		ImageView iv = imageViews.get(position%imageViews.size());
        //如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
        ViewParent vp =iv.getParent();
        if (vp!=null){
            ViewGroup parent = (ViewGroup)vp;
            parent.removeView(iv);
        }
		//把页面添加到容器中
		container.addView(iv);
		return iv;
	}

	/**
	 * 比较view是不是上面instantiateItem返回的值
	 * com.sunshine.rxjavademo.view:当前页面
	 * object：instantiateItem方法的结果
	 */
	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}



	/**
	 * container:ViewPager
	 * position:要移除页面的下标
	 * object：要移除的页面
	 */
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		
//		super.destroyItem(container, position, object);
//		container.removeView((View)object);
	}

}
