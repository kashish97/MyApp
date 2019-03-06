package in.codecorp.myapplication.Response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import in.codecorp.myapplication.Utils.mTest;

public class TestLevelResponse extends BaseResponse {

    @SerializedName("data")
    private List<Object> data;

    @SerializedName("records")
    private List<mTest> records;

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

    public List<mTest> getRecords() {
        return records;
    }

    public void setRecords(List<mTest> records) {
        this.records = records;
    }
}