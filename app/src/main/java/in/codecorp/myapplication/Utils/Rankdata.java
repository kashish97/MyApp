package in.codecorp.myapplication.Utils;

import com.google.gson.annotations.SerializedName;

public class Rankdata {

    @SerializedName("max")
    private String max;

    @SerializedName("avg")
    private String avg;

    @SerializedName("me")
    private String me;

    @SerializedName("rank")
    private int rank;

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getAvg() {
        return avg;
    }

    public void setAvg(String avg) {
        this.avg = avg;
    }

    public String getMe() {
        return me;
    }

    public void setMe(String me) {
        this.me = me;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

}