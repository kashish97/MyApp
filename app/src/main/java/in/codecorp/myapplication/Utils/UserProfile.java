package in.codecorp.myapplication.Utils;

import com.google.gson.annotations.SerializedName;

import java.io.File;

public class UserProfile {

    @SerializedName("up_fullname")
    private String upFullname;

    @SerializedName("up_gender")
    private String upGender;

    @SerializedName("up_fname")
    private String upFname;

    @SerializedName("up_dob")
    private String upDob;

    @SerializedName("up_mobile")
    private String upMobile;

    @SerializedName("up_address")
    private String upAddress;

    @SerializedName("up_city")
    private String upCity;

    @SerializedName("up_pin")
    private String upPin;

    @SerializedName("up_state")
    private String upState;

    @SerializedName("up_qualification")
    private String upQualification;

    @SerializedName("up_college_name")
    private String upCollegeName;

    @SerializedName("up_college_city")
    private String upCollegeCity;

    @SerializedName("up_college_state")
    private String upCollegeState;

    @SerializedName("up_passing_year")
    private String upPassingYear;

    @SerializedName("up_percent")
    private String upPercent;

    @SerializedName("file")
    private File file;

    @SerializedName("up_id")
    private int upId;

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

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public int getUpId() {
        return upId;
    }

    public void setUpId(int upId) {
        this.upId = upId;
    }

}