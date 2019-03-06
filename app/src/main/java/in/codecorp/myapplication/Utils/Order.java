package in.codecorp.myapplication.Utils;

public class Order {
    String od_id,
            od_init_date,
            od_pay_date,
            od_status,
            od_pay_method,
            od_receipt,
            od_receipt_details,
            od_init_amount,
            od_coupon,
            od_discount,
            od_amount,
            user_id;

    public Order(String od_id, String od_init_date, String od_pay_date, String od_status, String od_pay_method, String od_receipt, String od_receipt_details, String od_init_amount, String od_coupon, String od_discount, String od_amount, String user_id) {
        this.od_id = od_id;
        this.od_init_date = od_init_date;
        this.od_pay_date = od_pay_date;
        this.od_status = od_status;
        this.od_pay_method = od_pay_method;
        this.od_receipt = od_receipt;
        this.od_receipt_details = od_receipt_details;
        this.od_init_amount = od_init_amount;
        this.od_coupon = od_coupon;
        this.od_discount = od_discount;
        this.od_amount = od_amount;
        this.user_id = user_id;
    }

    public String getOd_id() {
        return od_id;
    }

    public void setOd_id(String od_id) {
        this.od_id = od_id;
    }

    public String getOd_init_date() {
        return od_init_date;
    }

    public void setOd_init_date(String od_init_date) {
        this.od_init_date = od_init_date;
    }

    public String getOd_pay_date() {
        return od_pay_date;
    }

    public void setOd_pay_date(String od_pay_date) {
        this.od_pay_date = od_pay_date;
    }

    public String getOd_status() {
        return od_status;
    }

    public void setOd_status(String od_status) {
        this.od_status = od_status;
    }

    public String getOd_pay_method() {
        return od_pay_method;
    }

    public void setOd_pay_method(String od_pay_method) {
        this.od_pay_method = od_pay_method;
    }

    public String getOd_receipt() {
        return od_receipt;
    }

    public void setOd_receipt(String od_receipt) {
        this.od_receipt = od_receipt;
    }

    public String getOd_receipt_details() {
        return od_receipt_details;
    }

    public void setOd_receipt_details(String od_receipt_details) {
        this.od_receipt_details = od_receipt_details;
    }

    public String getOd_init_amount() {
        return od_init_amount;
    }

    public void setOd_init_amount(String od_init_amount) {
        this.od_init_amount = od_init_amount;
    }

    public String getOd_coupon() {
        return od_coupon;
    }

    public void setOd_coupon(String od_coupon) {
        this.od_coupon = od_coupon;
    }

    public String getOd_discount() {
        return od_discount;
    }

    public void setOd_discount(String od_discount) {
        this.od_discount = od_discount;
    }

    public String getOd_amount() {
        return od_amount;
    }

    public void setOd_amount(String od_amount) {
        this.od_amount = od_amount;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
