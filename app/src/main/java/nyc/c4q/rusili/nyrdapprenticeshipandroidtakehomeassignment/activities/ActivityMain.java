package nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.R;
import nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.fragments.FragmentDetailView;
import nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.utility.network.models.InitialResponse;
import nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.utility.network.models.Result;
import nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.utility.network.retrofit.RetrofitMeetup;
import nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.utility.recyclerview.RecyclerviewMeetupAdapter;

public class ActivityMain extends AppCompatActivity {
    private InitialResponse initialResponseSavedInstanceState;
    private Gson gsonConverter;

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerviewEvents;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            initialize();
        } else {
            setViews();
            onResumeLoad(savedInstanceState);
        }
    }

    private void initialize () {
        isConnectedToInternet();
        setViews();
        getMeetupInfo();
    }

    private void onResumeLoad (Bundle savedInstanceState) {
        setViews();
        gsonConverter = new Gson();
        String jsonString = savedInstanceState.getString("JSONResponse");
        initialResponseSavedInstanceState = gsonConverter.fromJson(jsonString, InitialResponse.class);

        updateRecyclerviewMeetupAdapter(parseInitialResponse(initialResponseSavedInstanceState));
    }

    private void setViews () {
        recyclerviewEvents = (RecyclerView) findViewById(R.id.activitymain_recyclerview_events);
        recyclerviewEvents.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getMeetupInfo () {
        RetrofitMeetup retrofitMeetup = createRetrofitSingleton();
        retrofitMeetup.getEvents();
    }

    @NonNull
    private RetrofitMeetup createRetrofitSingleton () {
        RetrofitMeetup retrofitMeetup = RetrofitMeetup.getInstance();
        retrofitMeetup.GiveListener(new RetrofitMeetup.onResponseListener() {
            @Override
            public void giveInitialResponse (InitialResponse initialResponseParam) {
                initialResponseSavedInstanceState = initialResponseParam;
                updateRecyclerviewMeetupAdapter(parseInitialResponse(initialResponseParam));
            }
        });
        return retrofitMeetup;
    }

    private List <Result> parseInitialResponse (InitialResponse initialResponseParam) {
        List <Result> listOfResults = new ArrayList <>();

        for (Result result : initialResponseParam.getResult()) {
            listOfResults.add(result);
        }
        return listOfResults;
    }

    private void updateRecyclerviewMeetupAdapter (List <Result> listOfResultsParam) {
        recyclerviewEvents.setAdapter(new RecyclerviewMeetupAdapter(listOfResultsParam, new RecyclerviewMeetupViewholder.InflateFragmentListener() {
            @Override
            public void DetailView (Result result) {
                inflateFragmentDetailView(result);
            }
        }));
    }

    private void inflateFragmentDetailView (Result result) {
        FragmentDetailView fragmentDetailView = new FragmentDetailView();
        Bundle bundle = new Bundle();
        gsonConverter = new Gson();
        String stringJson = gsonConverter.toJson(result);
        bundle.putString("JSONResult", stringJson);
        fragmentDetailView.setArguments(bundle);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activitymain_container, fragmentDetailView)
                .commit();
    }

    private void isConnectedToInternet () {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (activeNetwork == null) {
            Toast.makeText(this, "Connection Unavailable", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed () {
        Fragment currentFragment = getSupportFragmentManager()
                .findFragmentById(R.id.activitymain_container);

        if (currentFragment instanceof FragmentDetailView) {
            getSupportFragmentManager().beginTransaction()
                    .remove(currentFragment)
                    .commit();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState (Bundle outState) {
        gsonConverter = new Gson();
        String string = gsonConverter.toJson(initialResponseSavedInstanceState);
        outState.putString("JSONResponse", string);
        super.onSaveInstanceState(outState);
    }
}
