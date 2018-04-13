package yuvraj.midterm_project.ui.home.events;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import yuvraj.midterm_project.data.web.WebModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import yuvraj.midterm_project.AppActivity;
import yuvraj.midterm_project.R;

/**
 *
 */
public class Webs extends LinearLayout {
	private Button add;
	private RecyclerView recyclerView;
	private WebsAdapter websAdapter;

	public Webs(Context context) {
		this(context, null, 0);
	}

	public Webs(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public Webs(Context context, @Nullable AttributeSet attrs,
                int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Subscribe
	public void onEventBusEvent(OnWebUpdatedEvent onWebUpdatedEvent)
	{
		AppActivity.hideKeyboard(getContext());

		if(onWebUpdatedEvent.webModel!=null)
		{
			// there is something to be added
			if(onWebUpdatedEvent.webModelPosition<0)
			{
				// if a negative value , treat it as a brand new one,
				//which needs to be added at the end of the list
				//TODO
				websAdapter.webs.add(onWebUpdatedEvent.webModel);
				websAdapter.notifyItemInserted(websAdapter.getItemCount()-1);

			}
			else
			{
				//we need to update an existed one with provided data
				//TODO
				websAdapter.webs.set(onWebUpdatedEvent.webModelPosition,onWebUpdatedEvent.webModel);
				websAdapter.notifyItemChanged(onWebUpdatedEvent.webModelPosition);
			}
		}


	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();

		add = findViewById(R.id.add);
		add.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// send to the event bus there is a need to create a new contact
				WebModel emptyContact = new WebModel("", "", "", "");
				EventBus.getDefault().post(new OnWebEvent(emptyContact, -1));
			}
		});

		websAdapter= new WebsAdapter();
		recyclerView = findViewById(R.id.recyclerView);
		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
				LinearLayoutManager.VERTICAL,false));
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		//TODO

		recyclerView.setAdapter(websAdapter);
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
