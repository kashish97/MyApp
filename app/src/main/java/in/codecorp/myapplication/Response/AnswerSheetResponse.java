package in.codecorp.myapplication.Response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import in.codecorp.myapplication.Utils.AnswerModal;


public class AnswerSheetResponse extends BaseResponse {

    @SerializedName("data")
    private AnswerModal data;

    @SerializedName("records")
    private List<Object> records;

    public AnswerModal getData() {
        return data;
    }

    public void setData(AnswerModal data) {
        this.data = data;
    }

    public List<Object> getRecords() {
        return records;
    }

    public void setRecords(List<Object> records) {
        this.records = records;
    }
}