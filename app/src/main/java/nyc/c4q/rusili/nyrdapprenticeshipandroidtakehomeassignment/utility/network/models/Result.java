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
}
