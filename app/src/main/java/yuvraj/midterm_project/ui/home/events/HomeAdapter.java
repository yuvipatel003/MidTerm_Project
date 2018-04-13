package yuvraj.midterm_project.ui.home.events;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by yuvi0 on 2018-03-06.
 */

public class HomeAdapter extends PagerAdapter {
    private final LayoutInflater layoutInflater;
    private final List<HomeAdapterPage> pages;

    HomeAdapter(Context context,List<HomeAdapterPage> pages)
    {
        layoutInflater = LayoutInflater.from(context);
        this.pages=pages;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup viewPager, int position) {
        HomeAdapterPage page = pages.get(position);

        View view = layoutInflater.inflate(page.getLayoutId(),viewPager,false);
        viewPager.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup viewPager, int position, @NonNull Object pageView) {
        viewPager.removeView((View)pageView);
    }

    @Override
    public int getCount() {
        return pages.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return pages.get(position).getTitle();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return object==view;
    }
}
