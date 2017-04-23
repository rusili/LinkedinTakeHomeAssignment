package nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
    private RecyclerView recyclerviewEvents;
    private InitialResponse initialResponse;
    private Gson gsonConverter;

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

    private void initialize(){
        isConnectedToInternet();
        setViews();
        getMeetupInfo();
    }

    private void onResumeLoad (Bundle savedInstanceState){
        setViews();
        gsonConverter = new Gson();
        String jsonString = savedInstanceState.getString("JSONResponse");
        initialResponse = gsonConverter.fromJson(jsonString, InitialResponse.class);
        List<Result> listOfResults = new ArrayList <>();
        for (Result result: initialResponse.getResult()){
            listOfResults.add(result);
        }
        recyclerviewEvents.setAdapter(new RecyclerviewMeetupAdapter(listOfResults));
    }

    private void setViews(){
        recyclerviewEvents = (RecyclerView) findViewById(R.id.activitymain_recyclerview_events);
        recyclerviewEvents.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getMeetupInfo (){
        RetrofitMeetup retrofitMeetup = new RetrofitMeetup();
        retrofitMeetup.GiveListener(new RetrofitMeetup.onResponseListener() {
            @Override
            public void giveInitialResponse (InitialResponse initialResponseParam) {
                initialResponse = initialResponseParam;
            }
        });
        retrofitMeetup.getEvents(recyclerviewEvents);
    }

    public void isConnectedToInternet() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (activeNetwork == null){
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
        String string = gsonConverter.toJson(initialResponse);
        outState.putString("JSONResponse", string);
        super.onSaveInstanceState(outState);
    }
}
