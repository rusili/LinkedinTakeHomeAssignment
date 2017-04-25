package nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.utility.network.models;

import nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.utility.network.models.result.Group;
import nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.utility.network.models.result.Venue;

public class Result {
    private String name;
    private long time;
    private String description;
    private Group group;
    private Venue venue;

    public String getName () {
        return name;
    }

    public long getTime () {
        return time;
    }

    public String getDescription () {
        return description;
    }

    public Group getGroup () {
        return group;
    }

    public Venue getVenue () {
        return venue;
    }

    public static String getLocation(Result result){
        String location = "TBA";
        if (result.getVenue() != null){
            location = result.getVenue().getCity();
        }
        return location;
    }

    public static String getVenue(Result result){
        String venue = "TBA";
        if (result.getVenue() != null){
            venue = result.getVenue().getAddress();
        }
        return venue;
    }
}
