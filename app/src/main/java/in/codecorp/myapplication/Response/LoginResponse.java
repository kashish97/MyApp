package in.codecorp.myapplication.Response;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class LoginResponse extends BaseResponse {

    @SerializedName("data")
    private TokenObject data;

    @SerializedName("records")
    private List<Object> records;

    public TokenObject getData() {
        return data;
    }

    public void setData(TokenObject data) {
        this.data = data;
    }

    public List<Object> getRecords() {
        return records;
    }

    public void setRecords(List<Object> records) {
        this.records = records;
    }
}
