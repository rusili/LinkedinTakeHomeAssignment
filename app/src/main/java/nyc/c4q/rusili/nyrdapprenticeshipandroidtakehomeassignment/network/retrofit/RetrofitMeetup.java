package nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.network.retrofit;

import android.util.Log;

import nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.network.models.InitialResponse;
import nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.utility.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitMeetup {
    //https://api.meetup.com/2/open_events?&sign=true&photo-host=public&zip=11222&fields=group_photo&page=20&offset=0&key=68627c731f3c13f4d1f2e42172710

    public RetrofitMeetup () {
    }

    public void getEvents () {
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
                }
            }

            @Override
            public void onFailure (Call <InitialResponse> call, Throwable t) {
                Log.d("onFailure: ", t.toString());
            }
        });
    }
}
