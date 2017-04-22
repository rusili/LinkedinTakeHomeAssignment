package nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.utility.network.retrofit;

import nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.utility.network.models.InitialResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ServiceMeetup {

    @GET ("/2/open_events?&sign=true&photo-host=public&zip=11222&fields=group_photo&page=20&offset=0&key=68627c731f3c13f4d1f2e42172710")
    Call <InitialResponse> getResponse ();
}
