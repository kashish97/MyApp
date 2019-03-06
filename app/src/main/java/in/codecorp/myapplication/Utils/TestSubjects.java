package in.codecorp.myapplication.Utils;

import com.google.gson.annotations.SerializedName;

public class TestSubjects {
    @SerializedName("sub_id")
    private int subId;

    @SerializedName("sub_title")
    private String subTitle;

    @SerializedName("sub_lockperiod")
    private int subLockperiod;

    @SerializedName("sub_order")
    private int subOrder;

    @SerializedName("t_id")
    private int tId;

    public int getSubId() {
        return subId;
    }

    public void setSubId(int subId) {
        this.subId = subId;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public int getSubLockperiod() {
        return subLockperiod;
    }

    public void setSubLockperiod(int subLockperiod) {
        this.subLockperiod = subLockperiod;
    }

    public int getSubOrder() {
        return subOrder;
    }

    public void setSubOrder(int subOrder) {
        this.subOrder = subOrder;
    }

    public int getTId() {
        return tId;
    }

    public void setTId(int tId) {
        this.tId = tId;
    }

}