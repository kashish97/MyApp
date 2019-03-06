package in.codecorp.myapplication.Response;

import com.google.gson.annotations.SerializedName;

public class BaseResponse {

    @SerializedName("authentication")
    private Boolean authentication;

    @SerializedName("authorization")
    private Boolean authorization;

    @SerializedName("status")
    private Boolean status;

    @SerializedName("html")
    private String html;

    @SerializedName("msg")
    private String msg;

    @SerializedName("total")
    private int total;

    @SerializedName("validation_status")
    private Boolean validationStatus;

    @SerializedName("validation_errors")
    private String validationErrors;

    @SerializedName("error")
    private Boolean error;

    public Boolean getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Boolean authentication) {
        this.authentication = authentication;
    }

    public Boolean getAuthorization() {
        return authorization;
    }

    public void setAuthorization(Boolean authorization) {
        this.authorization = authorization;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Boolean getValidationStatus() {
        return validationStatus;
    }

    public void setValidationStatus(Boolean validationStatus) {
        this.validationStatus = validationStatus;
    }

    public String getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(String validationErrors) {
        this.validationErrors = validationErrors;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }
}