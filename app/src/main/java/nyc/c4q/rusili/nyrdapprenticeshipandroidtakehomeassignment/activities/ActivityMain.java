package nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.R;
import nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.utility.network.retrofit.RetrofitMeetup;

public class ActivityMain extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testNetwork();
    }

    private void testNetwork(){
        RetrofitMeetup retrofitMeetup = new RetrofitMeetup();
        retrofitMeetup.getEvents();
    }
}
