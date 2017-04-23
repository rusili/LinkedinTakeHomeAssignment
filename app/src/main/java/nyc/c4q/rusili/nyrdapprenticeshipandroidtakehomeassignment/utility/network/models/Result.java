package nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.utility.network.models;

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

    public class Group{
        public GroupPhoto group_photo;

        public GroupPhoto getGroup_photo () {
            return group_photo;
        }

        public class GroupPhoto{
            String highres_link;
            String photo_link;
            String thumb_link;

            public String getHighres_link () {
                return highres_link;
            }

            public String getPhoto_link () {
                return photo_link;
            }

            public String getThumb_link () {
                return thumb_link;
            }
        }
    }

    public class Venue{
        String address_1;
        String city;
        String name;
        String state;

        public String getAddress () {
            return address_1;
        }

        public String getCity () {
            return city;
        }

        public String getName () {
            return name;
        }

        public String getState () {
            return state;
        }
    }
}
