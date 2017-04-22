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

import nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.R;
import nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.utility.network.models.Result;

public class FragmentDetailView extends Fragment{
    private View view;
    private TextView textViewEventName;
    private TextView textViewTimeVenue;
    private ImageView imageViewGroupPhotoLarge;
    private WebView webViewDescription;

    private Result result;

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detailview, container, false);
        initialize();
        return view;
    }

    private void initialize () {
        setViews();
    }

    private void setViews(){
        textViewEventName = (TextView) view.findViewById(R.id.fragment_detailview_textview_name);
        textViewTimeVenue = (TextView) view.findViewById(R.id.fragment_detailview_textview_timevenue);
        imageViewGroupPhotoLarge = (ImageView) view.findViewById(R.id.fragment_detailview_imageview_groupphoto);
        webViewDescription = (WebView) view.findViewById(R.id.fragment_detailview_webview_description);
    }

    public void giveResult(Result resultParam){
        this.result = resultParam;
    }
}
