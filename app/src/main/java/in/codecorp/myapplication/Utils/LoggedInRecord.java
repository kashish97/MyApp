package in.codecorp.myapplication.Utils;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Created by skushwa1 on 11/26/2017.
 */
public class LoggedInRecord {

    @SerializedName("od_id")
    private int odId;

    @SerializedName("od_init_date")
    private Date odInitDate;

    @SerializedName("od_pay_date")
    private Date odPayDate;

    @SerializedName("od_status")
    private String odStatus;

    @SerializedName("od_pay_method")
    private String odPayMethod;

    @SerializedName("od_receipt")
    private String odReceipt;

    @SerializedName("od_receipt_details")
    private String odReceiptDetails;

    @SerializedName("od_init_amount")
    private String odInitAmount;

    @SerializedName("od_coupon")
    private String odCoupon;

    @SerializedName("od_discount")
    private String odDiscount;

    @SerializedName("od_amount")
    private String odAmount;

    @SerializedName("user_id")
    private int userId;

    @SerializedName("orderPackages")
    private List<OrderDetail> orderPackages;

    public List<OrderDetail> getOrderPackages() {
        return orderPackages;
    }

    public void setOrderPackages(List<OrderDetail> orderPackages) {
        this.orderPackages = orderPackages;
    }

    public int getOdId() {
        return odId;
    }

    public void setOdId(int odId) {
        this.odId = odId;
    }

    public Date getOdInitDate() {
        return odInitDate;
    }

    public void setOdInitDate(Date odInitDate) {
        this.odInitDate = odInitDate;
    }

    public Date getOdPayDate() {
        return odPayDate;
    }

    public void setOdPayDate(Date odPayDate) {
        this.odPayDate = odPayDate;
    }

    public String getOdStatus() {
        return odStatus;
    }

    public void setOdStatus(String odStatus) {
        this.odStatus = odStatus;
    }

    public String getOdPayMethod() {
        return odPayMethod;
    }

    public void setOdPayMethod(String odPayMethod) {
        this.odPayMethod = odPayMethod;
    }

    public String getOdReceipt() {
        return odReceipt;
    }

    public void setOdReceipt(String odReceipt) {
        this.odReceipt = odReceipt;
    }

    public String getOdReceiptDetails() {
        return odReceiptDetails;
    }

    public void setOdReceiptDetails(String odReceiptDetails) {
        this.odReceiptDetails = odReceiptDetails;
    }

    public String getOdInitAmount() {
        return odInitAmount;
    }

    public void setOdInitAmount(String odInitAmount) {
        this.odInitAmount = odInitAmount;
    }

    public String getOdCoupon() {
        return odCoupon;
    }

    public void setOdCoupon(String odCoupon) {
        this.odCoupon = odCoupon;
    }

    public String getOdDiscount() {
        return odDiscount;
    }

    public void setOdDiscount(String odDiscount) {
        this.odDiscount = odDiscount;
    }

    public String getOdAmount() {
        return odAmount;
    }

    public void setOdAmount(String odAmount) {
        this.odAmount = odAmount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
