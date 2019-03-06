package in.codecorp.myapplication.Response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import in.codecorp.myapplication.Utils.Profile;

public class ProfileResponse extends BaseResponse {

    @SerializedName("data")
    private Profile data;

    @SerializedName("records")
    private List<Object> records;

    public Profile getData() {
        return data;
    }

    public void setData(Profile data) {
        this.data = data;
    }

    public List<Object> getRecords() {
        return records;
    }

    public void setRecords(List<Object> records) {
        this.records = records;
    }
}