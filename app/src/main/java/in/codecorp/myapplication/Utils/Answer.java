package in.codecorp.myapplication.Utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Answer {
    @SerializedName("a_id")
    @Expose
    private String aId;
    @SerializedName("a_flag")
    @Expose
    private String aFlag;
    @SerializedName("a_value")
    @Expose
    private String aValue;
    @SerializedName("a_marks_obt")
    @Expose
    private float aMarksObt;
    @SerializedName("a_datetime")
    @Expose
    private String aDatetime;
    @SerializedName("a_qdata")
    @Expose
    private String aQdata;
    @SerializedName("q_id")
    @Expose
    private String qId;
    @SerializedName("t_id")
    @Expose
    private String tId;
    @SerializedName("mt_id")
    @Expose
    private String mtId;
    @SerializedName("user_id")
    @Expose
    private String userId;

    public String getAId() {
        return aId;
    }

    public void setAId(String aId) {
        this.aId = aId;
    }

    public String getAFlag() {
        return aFlag;
    }

    public void setAFlag(String aFlag) {
        this.aFlag = aFlag;
    }

    public String getAValue() {
        return aValue;
    }

    public void setAValue(String aValue) {
        this.aValue = aValue;
    }

    public float getAMarksObt() {
        return aMarksObt;
    }

    public void setAMarksObt(float aMarksObt) {
        this.aMarksObt = aMarksObt;
    }

    public String getADatetime() {
        return aDatetime;
    }

    public void setADatetime(String aDatetime) {
        this.aDatetime = aDatetime;
    }

    public String getAQdata() {
        return aQdata;
    }

    public void setAQdata(String aQdata) {
        this.aQdata = aQdata;
    }

    public String getQId() {
        return qId;
    }

    public void setQId(String qId) {
        this.qId = qId;
    }

    public String getTId() {
        return tId;
    }

    public void setTId(String tId) {
        this.tId = tId;
    }

    public String getMtId() {
        return mtId;
    }

    public void setMtId(String mtId) {
        this.mtId = mtId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
