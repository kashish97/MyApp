package in.codecorp.myapplication.Utils;

import com.google.gson.annotations.SerializedName;

import in.codecorp.myapplication.Response.BaseResponse;


public class OrderDetail extends BaseResponse {

    @SerializedName("opkg_id")
    private String opkgId;

    @SerializedName("opkg_title")
    private String opkgTitle;

    @SerializedName("opkg_desc")
    private String opkgDesc;

    @SerializedName("opkg_photo")
    private String opkgPhoto;

    @SerializedName("opkg_price")
    private String opkgPrice;

    @SerializedName("pkg_id")
    private String pkgId;

    @SerializedName("od_id")
    private String odId;

    public String getOpkgId() {
        return opkgId;
    }

    public void setOpkgId(String opkgId) {
        this.opkgId = opkgId;
    }

    public String getOpkgTitle() {
        return opkgTitle;
    }

    public void setOpkgTitle(String opkgTitle) {
        this.opkgTitle = opkgTitle;
    }

    public String getOpkgDesc() {
        return opkgDesc;
    }

    public void setOpkgDesc(String opkgDesc) {
        this.opkgDesc = opkgDesc;
    }

    public String getOpkgPhoto() {
        return opkgPhoto;
    }

    public void setOpkgPhoto(String opkgPhoto) {
        this.opkgPhoto = opkgPhoto;
    }

    public String getOpkgPrice() {
        return opkgPrice;
    }

    public void setOpkgPrice(String opkgPrice) {
        this.opkgPrice = opkgPrice;
    }

    public String getPkgId() {
        return pkgId;
    }

    public void setPkgId(String pkgId) {
        this.pkgId = pkgId;
    }

    public String getOdId() {
        return odId;
    }

    public void setOdId(String odId) {
        this.odId = odId;
    }
}