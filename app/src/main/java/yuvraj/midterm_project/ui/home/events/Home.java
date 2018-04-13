package yuvraj.midterm_project.ui.home.events;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import yuvraj.midterm_project.R;
import yuvraj.midterm_project.ui.home.events.OnWebEvent;
import yuvraj.midterm_project.ui.home.events.OnWebUpdatedEvent;

public class Home extends ViewPager {
	private static final int POSITION_CONTACTS = 0;
	private static final int POSITION_CONTACT = 1;

	public Home(Context context) {
		this(context, null);
	}

	public Home(Context context, AttributeSet attrs) {
		super(context, attrs);
	}


	@Subscribe
	public void onEventBusEvent(OnWebEvent onContactEvent)
	{
		setCurrentItem(POSITION_CONTACT);
	}

	@Subscribe
	public void onEventBusEvent(OnWebUpdatedEvent onContactUpdatedEvent)
	{
		setCurrentItem(POSITION_CONTACTS);
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();

		// set the two pages

		List<HomeAdapterPage> pages = new ArrayList<>(2);
		pages.add(new HomeAdapterPage(R.layout.home_webs,getContext().getString(R.string.webs)));
		pages.add(new HomeAdapterPage(R.layout.home_web,getContext().getString(R.string.website_form)));

		setAdapter(new HomeAdapter(getContext(),pages));
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();

		//TODO register to listen for any events from the bus

		EventBus.getDefault().register(this);

	}

	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();

		//TODO unregister to listen for any events

		EventBus.getDefault().unregister(this);
	}
}