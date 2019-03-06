package in.codecorp.myapplication.Response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import in.codecorp.myapplication.Utils.TestSubjects;

public class TestSubjectResponse extends BaseResponse {

    @SerializedName("data")
    private List<Object> data;

    @SerializedName("records")
    private List<TestSubjects> records;

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

    public List<TestSubjects> getRecords() {
        return records;
    }

    public void setRecords(List<TestSubjects> records) {
        this.records = records;
    }
}