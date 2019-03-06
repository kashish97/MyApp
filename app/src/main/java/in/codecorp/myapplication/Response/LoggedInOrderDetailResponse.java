package in.codecorp.myapplication.Response;

import com.google.gson.annotations.SerializedName;

import in.codecorp.myapplication.Utils.LoggedInRecord;


public class LoggedInOrderDetailResponse extends BaseResponse {

    @SerializedName("data")
    private LoggedInRecord data;

    @SerializedName("records")
    private Object records;

    public LoggedInRecord getData() {
        return data;
    }

    public void setData(LoggedInRecord data) {
        this.data = data;
    }

    public Object getRecords() {
        return records;
    }

    public void setRecords(Object records) {
        this.records = records;
    }
}