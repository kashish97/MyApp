package in.codecorp.myapplication.Utils;

import com.google.gson.annotations.SerializedName;

public class AnswerAnalysis {

    @SerializedName("total")
    private String total;

    @SerializedName("attempt")
    private String attempt;

    @SerializedName("skipped")
    private int skipped;

    @SerializedName("incorrect")
    private String incorrect;

    @SerializedName("correct")
    private int correct;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getAttempt() {
        return attempt;
    }

    public void setAttempt(String attempt) {
        this.attempt = attempt;
    }

    public int getSkipped() {
        return skipped;
    }

    public void setSkipped(int skipped) {
        this.skipped = skipped;
    }

    public String getIncorrect() {
        return incorrect;
    }

    public void setIncorrect(String incorrect) {
        this.incorrect = incorrect;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

}