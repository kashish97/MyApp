package in.codecorp.myapplication.Response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import in.codecorp.myapplication.Utils.LoggedInRecord;


public class LoggedInOrderResponse extends BaseResponse {
    @SerializedName("records")
    private List<LoggedInRecord> records;

    @SerializedName("data")
    private Object data;

    public List<LoggedInRecord> getRecords() {
        return records;
    }

    public void setRecords(List<LoggedInRecord> records) {
        this.records = records;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}