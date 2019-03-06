package in.codecorp.myapplication.Response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import in.codecorp.myapplication.Utils.TestData;


public class TestResponse extends BaseResponse {

    @SerializedName("data")
    private TestData data;

    @SerializedName("records")
    private List<Object> records;

    public TestData getData() {
        return data;
    }

    public void setData(TestData data) {
        this.data = data;
    }

    public List<Object> getRecords() {
        return records;
    }

    public void setRecords(List<Object> records) {
        this.records = records;
    }
}