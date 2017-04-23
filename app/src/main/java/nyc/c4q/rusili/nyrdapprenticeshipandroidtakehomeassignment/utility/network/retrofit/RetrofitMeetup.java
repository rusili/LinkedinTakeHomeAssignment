package nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.utility.network.retrofit;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.utility.Constants;
import nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.utility.network.models.InitialResponse;
import nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.utility.network.models.Result;
import nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.utility.recyclerview.RecyclerviewMeetupAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitMeetup {
    //https://api.meetup.com/2/open_events?&sign=true&photo-host=public&zip=11222&fields=group_photo&page=20&offset=0&key=68627c731f3c13f4d1f2e42172710
    private List<Result> listofResults;

    public RetrofitMeetup () {}

    public void getEvents (final RecyclerView recyclerviewEvents) {
        listofResults = new ArrayList <>();

        Retrofit retrofitMeetup = new Retrofit.Builder()
                .baseUrl(Constants.Network.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServiceMeetup service = retrofitMeetup.create(ServiceMeetup.class);
        Call <InitialResponse> getInitialResponse = service.getResponse();
        getInitialResponse.enqueue(new Callback <InitialResponse>() {
            @Override
            public void onResponse (Call <InitialResponse> call, Response <InitialResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("onResponse: ", "Successful");

                    InitialResponse initialResponse = response.body();
                    for (Result result: initialResponse.getResult()){
                        listofResults.add(result);
                    }

                    recyclerviewEvents.setAdapter(new RecyclerviewMeetupAdapter(listofResults));
                }
            }

            @Override
            public void onFailure (Call <InitialResponse> call, Throwable t) {
                Log.d("onFailure: ", t.toString());
            }
        });
    }
}
