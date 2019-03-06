package in.codecorp.myapplication.Utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profile {
    @SerializedName("up_id")
    @Expose
    private int upId;
    @SerializedName("up_fullname")
    @Expose
    private String upFullname;
    @SerializedName("up_gender")
    @Expose
    private String upGender;
    @SerializedName("up_fname")
    @Expose
    private String upFname;
    @SerializedName("up_dob")
    @Expose
    private String upDob;
    @SerializedName("up_mobile")
    @Expose
    private String upMobile;
    @SerializedName("up_address")
    @Expose
    private String upAddress;
    @SerializedName("up_city")
    @Expose
    private String upCity;
    @SerializedName("up_pin")
    @Expose
    private String upPin;
    @SerializedName("up_state")
    @Expose
    private String upState;
    @SerializedName("up_qualification")
    @Expose
    private String upQualification;
    @SerializedName("up_college_name")
    @Expose
    private String upCollegeName;
    @SerializedName("up_college_city")
    @Expose
    private String upCollegeCity;
    @SerializedName("up_college_state")
    @Expose
    private String upCollegeState;
    @SerializedName("up_passing_year")
    @Expose
    private String upPassingYear;
    @SerializedName("up_percent")
    @Expose
    private String upPercent;
    @SerializedName("up_photo")
    @Expose
    private String upPhoto;
    @SerializedName("user_id")
    @Expose
    private int userId;

    public int getUpId() {
        return upId;
    }

    public void setUpId(int upId) {
        this.upId = upId;
    }

    public String getUpFullname() {
        return upFullname;
    }

    public void setUpFullname(String upFullname) {
        this.upFullname = upFullname;
    }

    public String getUpGender() {
        return upGender;
    }

    public void setUpGender(String upGender) {
        this.upGender = upGender;
    }

    public String getUpFname() {
        return upFname;
    }

    public void setUpFname(String upFname) {
        this.upFname = upFname;
    }

    public String getUpDob() {
        return upDob;
    }

    public void setUpDob(String upDob) {
        this.upDob = upDob;
    }

    public String getUpMobile() {
        return upMobile;
    }

    public void setUpMobile(String upMobile) {
        this.upMobile = upMobile;
    }

    public String getUpAddress() {
        return upAddress;
    }

    public void setUpAddress(String upAddress) {
        this.upAddress = upAddress;
    }

    public String getUpCity() {
        return upCity;
    }

    public void setUpCity(String upCity) {
        this.upCity = upCity;
    }

    public String getUpPin() {
        return upPin;
    }

    public void setUpPin(String upPin) {
        this.upPin = upPin;
    }

    public String getUpState() {
        return upState;
    }

    public void setUpState(String upState) {
        this.upState = upState;
    }

    public String getUpQualification() {
        return upQualification;
    }

    public void setUpQualification(String upQualification) {
        this.upQualification = upQualification;
    }

    public String getUpCollegeName() {
        return upCollegeName;
    }

    public void setUpCollegeName(String upCollegeName) {
        this.upCollegeName = upCollegeName;
    }

    public String getUpCollegeCity() {
        return upCollegeCity;
    }

    public void setUpCollegeCity(String upCollegeCity) {
        this.upCollegeCity = upCollegeCity;
    }

    public String getUpCollegeState() {
        return upCollegeState;
    }

    public void setUpCollegeState(String upCollegeState) {
        this.upCollegeState = upCollegeState;
    }

    public String getUpPassingYear() {
        return upPassingYear;
    }

    public void setUpPassingYear(String upPassingYear) {
        this.upPassingYear = upPassingYear;
    }

    public String getUpPercent() {
        return upPercent;
    }

    public void setUpPercent(String upPercent) {
        this.upPercent = upPercent;
    }

    public String getUpPhoto() {
        return upPhoto;
    }

    public void setUpPhoto(String upPhoto) {
        this.upPhoto = upPhoto;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}