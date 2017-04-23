package nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Calendar;

import nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.R;
import nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.utility.network.MillisecondsToDateTime;
import nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.utility.network.models.Result;

public class FragmentDetailView extends Fragment{
    private View view;
    private TextView textViewEventName;
    private TextView textViewTimeVenue;
    private TextView textViewAddress;
    private ImageView imageViewGroupPhotoLarge;
    private WebView webViewDescription;

    private Result result;

    public void giveResult(Result resultParam){
        this.result = resultParam;
    }

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detailview, container, false);
        initialize();
        return view;
    }

    private void initialize () {
        setViews();
        showResult(result);
    }

    private void setViews(){
        textViewEventName = (TextView) view.findViewById(R.id.fragment_detailview_textview_name);
        textViewTimeVenue = (TextView) view.findViewById(R.id.fragment_detailview_textview_timevenue);
        textViewAddress = (TextView) view.findViewById(R.id.fragment_detailview_textview_address);
        imageViewGroupPhotoLarge = (ImageView) view.findViewById(R.id.fragment_detailview_imageview_groupphoto);
        webViewDescription = (WebView) view.findViewById(R.id.fragment_detailview_webview_description);
    }

    private void showResult (Result resultParam) {
        setGroupPhoto(resultParam);
        setWebView(resultParam);
        textViewEventName.setText(resultParam.getName());
        if (result.getVenue() != null) {
            textViewTimeVenue.setText(getLocationDate(resultParam));
            textViewAddress.setText(resultParam.getVenue().getAddress());
        } else {
            textViewTimeVenue.setText("TBA");
            textViewAddress.setText("TBA");
        }
    }

    private void setGroupPhoto (Result result){
        if (result.getGroup().getGroup_photo() != null) {
            String urlThumb = result.getGroup().getGroup_photo().getPhoto_link();

            Glide.with(getContext())
                    .load(urlThumb)
                    .fitCenter()
                    .placeholder(R.drawable.ic_loop_black_24dp)
                    .into(imageViewGroupPhotoLarge);
        } else {
            Glide.with(getContext())
                    .load(R.drawable.ic_image_black_24dp)
                    .fitCenter()
                    .into(imageViewGroupPhotoLarge);
        }
    }

    private void setWebView (Result resultParam) {
        String description = resultParam.getDescription();
        webViewDescription.loadData(description, "text/html", null);
    }

    private String getLocationDate (Result result) {
        String venue = "TBA";
        if (result.getVenue() != null){
            venue = result.getVenue().getName();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(result.getTime());
        String time = MillisecondsToDateTime.getTime(calendar);

        return (time + ", " + venue);
    }

    @Override
    public void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("Name", result.getName());
    }
}
