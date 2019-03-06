package in.codecorp.myapplication.Utils;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyTestModel {

    @SerializedName("mt_id")
    private String mtId;

    @SerializedName("user_id")
    private String userId;

    @SerializedName("t_id")
    private String tId;

    @SerializedName("mt_status")
    private String mtStatus;

    @SerializedName("mt_start_date")
    private String mtStartDate;

    @SerializedName("mt_expiry_date")
    private String mtExpiryDate;

    @SerializedName("mt_total_marks")
    private float mtTotalMarks;

    @SerializedName("mt_marks_obt")
    private float mtMarksObt;

    @SerializedName("mt_percentage")
    private float mtPercentage;

    @SerializedName("mt_result_date")
    private String mtResultDate;

    @SerializedName("mt_minutes_passed")
    private String mtMinutesPassed;

    @SerializedName("test")
    private Test test;

    @SerializedName("answers")
    private List<Answer> answers = null;

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

    public String getTId() {
        return tId;
    }

    public void setTId(String tId) {
        this.tId = tId;
    }

    public String getMtStatus() {
        return mtStatus;
    }

    public void setMtStatus(String mtStatus) {
        this.mtStatus = mtStatus;
    }

    public String getMtStartDate() {
        return mtStartDate;
    }

    public void setMtStartDate(String mtStartDate) {
        this.mtStartDate = mtStartDate;
    }

    public String getMtExpiryDate() {
        return mtExpiryDate;
    }

    public void setMtExpiryDate(String mtExpiryDate) {
        this.mtExpiryDate = mtExpiryDate;
    }

    public float getMtTotalMarks() {
        return mtTotalMarks;
    }

    public void setMtTotalMarks(float mtTotalMarks) {
        this.mtTotalMarks = mtTotalMarks;
    }

    public float getMtMarksObt() {
        return mtMarksObt;
    }

    public void setMtMarksObt(float mtMarksObt) {
        this.mtMarksObt = mtMarksObt;
    }

    public float getMtPercentage() {
        return mtPercentage;
    }

    public void setMtPercentage(float mtPercentage) {
        this.mtPercentage = mtPercentage;
    }

    public String getMtResultDate() {
        return mtResultDate;
    }

    public void setMtResultDate(String mtResultDate) {
        this.mtResultDate = mtResultDate;
    }

    public String getMtMinutesPassed() {
        return mtMinutesPassed;
    }

    public void setMtMinutesPassed(String mtMinutesPassed) {
        this.mtMinutesPassed = mtMinutesPassed;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

}