package yuvraj.midterm_project.ui.home.events;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import yuvraj.midterm_project.AppActivity;
import yuvraj.midterm_project.R;
import yuvraj.midterm_project.data.web.WebModel;

/**
 * The view details will be inflated in a layout file.
 *
 * This view is merely a holder of all logic, and its view content will be fleshed out in a layout, res/layout/home_web.xml
 */
public class Web extends ScrollView {
	private TextView websiteName;
	private TextView websiteType;
	private TextView release;
	private TextView website;

	private WebModel webModel;
	private int webModelPosition = -1;

	public Web(Context context) {
		super(context);
	}

	public Web(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public Web(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Subscribe
	public void onEventBusEvent(OnWebEvent onWebEvent)
	{
		webModel= onWebEvent.webModel;
		webModelPosition= onWebEvent.webModelPosition;
		updateUi();

		AppActivity.showKeyboard(getContext(), websiteName);
	}


	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();

		// this method will be called at the end of the layout inflation.
		// It is safe to look for any view children

		websiteName = findViewById(R.id.websiteName);
		websiteType = findViewById(R.id.websiteType);
		release = findViewById(R.id.release);
		website = findViewById(R.id.website);

		Button web = findViewById(R.id.web);
		web.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String websiteVallue = website.getText().toString();
				if (!websiteVallue.isEmpty()) {
					// there is some url
					if (!websiteVallue.startsWith("http://")) {
						// append "http://" at the url if there is no such in advance
						websiteVallue = "http://" + websiteVallue;
					}

					// TODO Add intent to open page

					Intent intent = new Intent(Intent.ACTION_VIEW);
					intent.setData(Uri.parse(websiteVallue));
					getContext().startActivity(intent);
				}
			}
		});

		Button cancel = findViewById(R.id.cancel);
		cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO post null as contact add position as -1

				EventBus.getDefault().post(new OnWebUpdatedEvent(null,-1));

				webModel= null;
				webModelPosition= -1;
				updateUi();
			}
		});

		Button update = findViewById(R.id.update);
		update.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String firstNameValue = websiteName.getText().toString();
				String websiteValue = website.getText().toString();

				if (firstNameValue.isEmpty() || websiteValue.isEmpty()) {
					// empty value for 'websitename' or 'website'
					Toast
						.makeText(getContext(), getContext().getString(R.string.website_name_and_website_cannot_be_empty), Toast.LENGTH_SHORT)
						.show();
				}
				else {
					// TODO
					if(webModel!=null)
					{
						webModel=new WebModel(firstNameValue,websiteType.getText().toString(),release.getText().toString(),websiteValue);
					}

					EventBus.getDefault().post(new OnWebUpdatedEvent(webModel,webModelPosition));
					webModel= null;
					webModelPosition= -1;
					updateUi();
				}
			}
		});
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();

		EventBus.getDefault().register(this);
	}

	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();

		EventBus.getDefault().unregister(this);
	}

	/**
	 * Update UI from 'webModel'
	 */
	private void updateUi() {
		// default as empty, nothing
		String websiteNameValue = "";
		String websiteTypeNameValue = "";
		String websiteReleaseValue = "";
		String websiteValue = "";

		if (webModel != null) {
			websiteNameValue = webModel.getWebsiteName();
			websiteTypeNameValue = webModel.getWebsiteType();
			websiteReleaseValue = webModel.getRelease();
			websiteValue = webModel.getWebsite();
		}

		websiteName.setText(websiteNameValue);
		websiteType.setText(websiteTypeNameValue);
		release.setText(websiteReleaseValue);
		website.setText(websiteValue);
	}
}
