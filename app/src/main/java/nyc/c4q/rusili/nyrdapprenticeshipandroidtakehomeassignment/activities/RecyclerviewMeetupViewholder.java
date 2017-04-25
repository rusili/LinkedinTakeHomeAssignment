package nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.activities;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.R;
import nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.utility.network.MillisecondsToDateTime;
import nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.utility.network.models.Result;

public class RecyclerviewMeetupViewholder extends RecyclerView.ViewHolder {
    private InflateFragmentListener inflateFragmentListener;

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

    public void giveListener (InflateFragmentListener inflateFragmentListenerParam){
        this.inflateFragmentListener = inflateFragmentListenerParam;
    }

    private void setOnClickListeners () {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                inflateFragmentListener.DetailView(result);
            }
        });
    }

    private void setViews () {
        imageViewGroupPhoto = (ImageView) view.findViewById(R.id.recyclerview_viewholder_event_imageview_group);
        textViewName = (TextView) view.findViewById(R.id.recyclerview_viewholder_event_textview_name);
        textViewLocationDate = (TextView) view.findViewById(R.id.recyclerview_viewholder_event_textview_locationdate);
    }

    public void bind (Result resultParam) {
        this.result = resultParam;

        setGroupPhoto(result);

        textViewName.setText(result.getName());
        textViewLocationDate.setText(Result.getLocation(resultParam) + ", " + MillisecondsToDateTime.getDate(resultParam));
    }

    private void setGroupPhoto (Result resultParam) {
        if (resultParam.getGroup().getGroup_photo() != null) {
            String urlThumb = resultParam.getGroup().getGroup_photo().getPhoto_link();

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

    public interface InflateFragmentListener {
        public void DetailView(Result result);
    }
}
