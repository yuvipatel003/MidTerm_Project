package yuvraj.midterm_project.ui.home.events;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import yuvraj.midterm_project.data.web.WebModel;

/**
 * RecyclerView adapter for Contacts
 */

public class WebsAdapter extends RecyclerView.Adapter<WebsCellHolder>{
  List<WebModel> webs;

    WebsAdapter()
    {
        webs = new ArrayList<>();
    }


    @NonNull
    @Override
    public WebsCellHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        return new WebsCellHolder(recyclerView);
    }

    @Override
    public void onBindViewHolder(@NonNull WebsCellHolder holder, int position) {

        WebModel webModel = webs.get(position);
        holder.bind(webModel);
    }

    @Override
    public int getItemCount() {
        return webs.size();
    }
}
