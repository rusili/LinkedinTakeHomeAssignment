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
import com.google.gson.Gson;

import nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.R;
import nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.utility.network.MillisecondsToDateTime;
import nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.utility.network.models.Result;

public class FragmentDetailView extends Fragment {
    private Gson gsonConverter;
    private Result result;

    private View view;
    private TextView textViewEventName;
    private TextView textViewTimeVenue;
    private TextView textViewAddress;
    private ImageView imageViewGroupPhotoLarge;
    private WebView webViewDescription;

    @Override
    public void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gsonConverter = new Gson();
        Bundle bundle = this.getArguments();
        String jsonString = bundle.getString("JSONResult");
        result = gsonConverter.fromJson(jsonString, Result.class);
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
        showDetailedEvent(result);
    }

    private void setViews () {
        textViewEventName = (TextView) view.findViewById(R.id.fragment_detailview_textview_name);
        textViewTimeVenue = (TextView) view.findViewById(R.id.fragment_detailview_textview_timevenue);
        textViewAddress = (TextView) view.findViewById(R.id.fragment_detailview_textview_address);
        imageViewGroupPhotoLarge = (ImageView) view.findViewById(R.id.fragment_detailview_imageview_groupphoto);
        webViewDescription = (WebView) view.findViewById(R.id.fragment_detailview_webview_description);
    }

    private void showDetailedEvent (Result resultParam) {
        setGroupPhoto(resultParam);
        setWebView(resultParam);
        textViewEventName.setText(resultParam.getName());
        textViewTimeVenue.setText("TBA");
        textViewAddress.setText(Result.getVenue(resultParam));
        textViewTimeVenue.setText(Result.getLocation(resultParam) + ", " + MillisecondsToDateTime.getTime(resultParam));
    }

    private void setGroupPhoto (Result result) {
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
        webViewDescription.loadData(resultParam.getDescription(), "text/html", null);
    }

    @Override
    public void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("JSONResult", result.getName());
    }
}
