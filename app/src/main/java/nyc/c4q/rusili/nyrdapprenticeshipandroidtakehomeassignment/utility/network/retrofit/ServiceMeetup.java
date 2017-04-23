package nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.utility.network.retrofit;

import nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.utility.network.models.InitialResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceMeetup {

    @GET ("/2/open_events?&sign=true&photo-host=public&zip=11222&fields=group_photo&page=20&offset=0&key=")
    Call <InitialResponse> getResponse (@Query("zip") int zipcodeParam, @Query ("key") String apikeyParam);
}
