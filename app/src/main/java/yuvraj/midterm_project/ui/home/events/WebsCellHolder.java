package yuvraj.midterm_project.ui.home.events;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import yuvraj.midterm_project.R;
import yuvraj.midterm_project.data.web.WebModel;
import yuvraj.midterm_project.ui.home.events.OnWebEvent;
import yuvraj.midterm_project.data.web.WebModel;

/**
 * View holder for R.layout.home_contacts_cell layout
 */

public class WebsCellHolder extends RecyclerView.ViewHolder{

    private TextView text;
    private TextView text_type;
    private TextView release_year;
    private Button get_Website;
    private Button edit;
    private WebModel webModel;
    private RelativeLayout recycleLayout;
    public String website;

    WebsCellHolder(ViewGroup recyclerView)
    {
        super(LayoutInflater.from(recyclerView.getContext())
                .inflate(R.layout.home_webs_cell,recyclerView,false));
        recycleLayout=(RelativeLayout) itemView;

       text=itemView.findViewById(R.id.website_name);
       text_type=itemView.findViewById(R.id.website_type);
       release_year=itemView.findViewById(R.id.release_year);
       get_Website=itemView.findViewById(R.id.getWebsite);
       edit=itemView.findViewById(R.id.edit);

        edit.setOnClickListener(view->{
            if(webModel!=null)
            {
                //dispatch blindly the model and its position
                EventBus.getDefault().post(new OnWebEvent(webModel,getAdapterPosition()));
            }
        });


        get_Website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!website.isEmpty()) {
                    // there is some url
                    if (!website.startsWith("http://")) {
                        // append "http://" at the url if there is no such in advance
                        website = "http://" + website;
                    }

                    // TODO Add intent to open page

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(website));
                    itemView.getContext().startActivity(intent);
                }
            }
        });

    }

    void bind(WebModel webModel)
    {
        this.webModel=webModel;
        if(webModel!=null)
        {
            String textValue= webModel.getWebsiteName();
            text.setText(textValue);

            String web_type=webModel.getWebsiteType();
            text_type.setText(web_type);

            String text_year = webModel.getRelease();
            release_year.setText(text_year);

            website = webModel.getWebsite();

            edit.setText("Edit "+textValue);
        }
    }
}
