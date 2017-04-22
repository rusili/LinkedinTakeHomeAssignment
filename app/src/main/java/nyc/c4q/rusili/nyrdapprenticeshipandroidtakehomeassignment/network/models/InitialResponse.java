package nyc.c4q.rusili.nyrdapprenticeshipandroidtakehomeassignment.network.models;

import java.util.List;

public class InitialResponse {
    public List<Result> result;
    public Meta meta;

    public List <Result> getResult () {
        return result;
    }

    public Meta getMeta () {
        return meta;
    }
}
