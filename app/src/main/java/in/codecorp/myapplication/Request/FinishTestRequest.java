package in.codecorp.myapplication.Request;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class FinishTestRequest {

    @SerializedName("token")
    private String token;

    @SerializedName("mt_id")
    private String mtId;

    @SerializedName("t_id")
    private String tId;

    @SerializedName("q_time")
    private Map<String, String> qTime;

    @SerializedName("ans")
    private Map<String, String> ans;

    @SerializedName("time")
    private int time;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMtId() {
        return mtId;
    }

    public void setMtId(String mtId) {
        this.mtId = mtId;
    }

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
    }

    public Map<String, String> getqTime() {
        return qTime;
    }

    public void setqTime(Map<String, String> qTime) {
        this.qTime = qTime;
    }

    public Map<String, String> getAns() {
        return ans;
    }

    public void setAns(Map<String, String> ans) {
        this.ans = ans;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}