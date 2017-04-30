package nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.utility.network.retrofit;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.utility.Constants;
import nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.utility.network.models.InitialResponse;
import nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.utility.network.models.Result;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitMeetup {
    private static RetrofitMeetup instance = null;
    private onResponseListener onResponseListener;
    private List <Result> listofResults;

    protected RetrofitMeetup () {
    }

    public static RetrofitMeetup getInstance () {
        if (instance == null) {
            instance = new RetrofitMeetup();
        }
        return instance;
    }

    public void GiveListener (onResponseListener onResponseListenerParam) {
        this.onResponseListener = onResponseListenerParam;
    }

    public void getEvents () {
        listofResults = new ArrayList <>();

        Retrofit retrofitMeetup = new Retrofit.Builder()
                .baseUrl(Constants.Network.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServiceMeetup service = retrofitMeetup.create(ServiceMeetup.class);
        Call <InitialResponse> getInitialResponse = service.getResponse(Constants.Network.ZIPCODE, Constants.Network.API_KEY);
        getInitialResponse.enqueue(new Callback <InitialResponse>() {
            @Override
            public void onResponse (Call <InitialResponse> call, Response <InitialResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("onResponse: ", "Successful");

                    InitialResponse initialResponse = response.body();
                    for (Result result : initialResponse.getResult()) {
                        listofResults.add(result);
                    }

                    onResponseListener.giveInitialResponse(initialResponse);
                }
            }

            @Override
            public void onFailure (Call <InitialResponse> call, Throwable t) {
                Log.d("onFailure: ", t.toString());
            }
        });
    }

    public interface onResponseListener {
        void giveInitialResponse (InitialResponse initialResponse);
    }
}
