package in.codecorp.myapplication.Response;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class RegisterResponse extends BaseResponse {

    @SerializedName("data")
    private List<Object> data;

    @SerializedName("records")
    private List<Object> records;

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

    public List<Object> getRecords() {
        return records;
    }

    public void setRecords(List<Object> records) {
        this.records = records;
    }
}