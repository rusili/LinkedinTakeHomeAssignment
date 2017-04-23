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

import nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.R;
import nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.fragments.FragmentDetailView;
import nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.utility.network.retrofit.RetrofitMeetup;

public class ActivityMain extends AppCompatActivity {
    private RecyclerView recyclerviewEvents;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    private void initialize(){
        isConnectedToInternet();
        setViews();
        getMeetupInfo();
    }

    private void setViews(){
        recyclerviewEvents = (RecyclerView) findViewById(R.id.activitymain_recyclerview_events);
        recyclerviewEvents.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getMeetupInfo (){
        RetrofitMeetup retrofitMeetup = new RetrofitMeetup();
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
}
