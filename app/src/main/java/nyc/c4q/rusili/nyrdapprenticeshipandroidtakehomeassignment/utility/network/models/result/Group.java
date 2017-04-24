package nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.utility.network.models.result;

public class Group {
    public GroupPhoto group_photo;

    public GroupPhoto getGroup_photo () {
        return group_photo;
    }

    public class GroupPhoto {
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
