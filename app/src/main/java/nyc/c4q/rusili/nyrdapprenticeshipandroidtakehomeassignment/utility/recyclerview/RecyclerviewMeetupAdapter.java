package nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.utility.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.R;
import nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.activities.RecyclerviewMeetupViewholder;
import nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.utility.network.models.Result;

public class RecyclerviewMeetupAdapter extends RecyclerView.Adapter {
    private List <Result> listofResults;
    private RecyclerviewMeetupViewholder.InflateFragmentListener inflateFragmentListener;

    public RecyclerviewMeetupAdapter (List <Result> listParam, RecyclerviewMeetupViewholder.InflateFragmentListener inflateFragmentListenerParam) {
        this.listofResults = listParam;
        inflateFragmentListener = inflateFragmentListenerParam;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recyclerview_viewholder_event, parent, false);
        RecyclerviewMeetupViewholder recyclerviewMeetupViewholder = new RecyclerviewMeetupViewholder(view);

        return recyclerviewMeetupViewholder;
    }

    @Override
    public void onBindViewHolder (RecyclerView.ViewHolder holder, int position) {
        RecyclerviewMeetupViewholder recyclerviewViewHolder = (RecyclerviewMeetupViewholder) holder;
        recyclerviewViewHolder.giveListener(inflateFragmentListener);
        recyclerviewViewHolder.bind(listofResults.get(position));
    }

    @Override
    public int getItemCount () {
        return listofResults.size();
    }

}
