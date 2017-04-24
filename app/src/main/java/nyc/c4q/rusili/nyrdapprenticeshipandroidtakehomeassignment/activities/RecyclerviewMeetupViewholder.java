package nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.Calendar;

import nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.R;
import nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.fragments.FragmentDetailView;
import nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.utility.network.MillisecondsToDateTime;
import nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.utility.network.models.Result;

public class RecyclerviewMeetupViewholder extends RecyclerView.ViewHolder {
    private Gson gsonConverter;
    private Result result;

    private View view;
    private ImageView imageViewGroupPhoto;
    private TextView textViewName;
    private TextView textViewLocationDate;

    public RecyclerviewMeetupViewholder (View viewParam) {
        super(viewParam);
        this.view = viewParam;
        setViews();
        setOnClickListeners();
    }

    private void setOnClickListeners () {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                inflateFragmentDetail(v);
            }
        });
    }

    private void inflateFragmentDetail (View view) {
        AppCompatActivity appCompatActivity = (AppCompatActivity) view.getContext();
        FragmentDetailView fragmentDetailView = new FragmentDetailView();
        Bundle bundle = new Bundle();
        gsonConverter = new Gson();
        String stringJson = gsonConverter.toJson(result);
        bundle.putString("JSONResult", stringJson);
        fragmentDetailView.setArguments(bundle);

        appCompatActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activitymain_container, fragmentDetailView)
                .commit();
    }


    private void setViews () {
        imageViewGroupPhoto = (ImageView) view.findViewById(R.id.recyclerview_viewholder_event_imageview_group);
        textViewName = (TextView) view.findViewById(R.id.recyclerview_viewholder_event_textview_name);
        textViewLocationDate = (TextView) view.findViewById(R.id.recyclerview_viewholder_event_textview_locationdate);
    }

    public void bind (Result resultParam) {
        this.result = resultParam;

        setGroupPhoto(result);
        String locationDate = getLocationDate(result);

        textViewName.setText(result.getName());
        textViewLocationDate.setText(locationDate);
    }

    private String getLocationDate (Result result) {
        String location = "TBA";
        if (result.getVenue() != null) {
            location = result.getVenue().getCity();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(result.getTime());
        String date = MillisecondsToDateTime.getDate(calendar);

        return (location + ", " + date);
    }

    private void setGroupPhoto (Result result) {
        if (result.getGroup().getGroup_photo() != null) {
            String urlThumb = result.getGroup().getGroup_photo().getPhoto_link();

            Glide.with(view.getContext())
                    .load(urlThumb)
                    .fitCenter()
                    .placeholder(R.drawable.ic_loop_black_24dp)
                    .into(imageViewGroupPhoto);
        } else {
            Glide.with(view.getContext())
                    .load(R.drawable.ic_image_black_24dp)
                    .fitCenter()
                    .into(imageViewGroupPhoto);
        }
    }
}
