package nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.utility.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Calendar;

import nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.R;
import nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.utility.network.MillisecondsToDateTime;
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
        setGroupPhoto(result);
        String locationDate = getLocationDate(result);

        textViewName.setText(result.getName());
        textViewLocationDate.setText(locationDate);
    }

    private String getLocationDate (Result result) {
        String location = "TBA";
        if (result.getVenue() != null){
            location = result.getVenue().getCity();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(result.getTime());
        String date = MillisecondsToDateTime.getDate(calendar);

        return (location + ", " + date);
    }

    private void setGroupPhoto (Result result){
        if (result.getGroup().getGroup_photo() != null) {
            String urlThumb = result.getGroup().getGroup_photo().getPhoto_link();

            Glide.with(mView.getContext())
                    .load(urlThumb)
                    .fitCenter()
                    .placeholder(R.drawable.ic_image_black_24dp)
                    .into(imageViewGroupPhoto);
        }
    }
}
