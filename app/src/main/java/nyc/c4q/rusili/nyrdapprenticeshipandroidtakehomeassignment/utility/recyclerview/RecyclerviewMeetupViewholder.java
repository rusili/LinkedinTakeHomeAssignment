package nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.utility.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.R;
import nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.utility.network.models.Result;

public class RecyclerviewMeetupViewholder extends RecyclerView.ViewHolder {
    private static View mView;
    private ImageView imageViewGroupPhoto;
    private TextView textViewName;
    private TextView textViewLocationDate;

    public RecyclerviewMeetupViewholder (ViewGroup parent) {
        super(inflateView(parent));
        setViews();
    }

    private static View inflateView (ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        mView = inflater.inflate(R.layout.recyclerview_viewholder_event, parent, false);
        return mView;
    }

    private void setViews () {
        imageViewGroupPhoto = (ImageView) mView.findViewById(R.id.recyclerview_viewholder_event_imageview_group);
        textViewName = (TextView) mView.findViewById(R.id.recyclerview_viewholder_event_textview_name);
        textViewLocationDate = (TextView) mView.findViewById(R.id.recyclerview_viewholder_event_textview_locationdate);
    }

    public void bind (Result result) {
        textViewName.setText(result.getName());
    }
}
