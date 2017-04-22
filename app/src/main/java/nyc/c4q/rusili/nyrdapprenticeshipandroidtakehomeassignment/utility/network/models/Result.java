package nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.utility.network.models;

public class Result {
    private String name;
    private long time;
    //private String description;
    private Group group;
    private Venue venue;

    public String getName () {
        return name;
    }

    public long getTime () {
        return time;
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
        String addess;
        String city;
        String name;
        String state;

        public String getAddess () {
            return addess;
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
