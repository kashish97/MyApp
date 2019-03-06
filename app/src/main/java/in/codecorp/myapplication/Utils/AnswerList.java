package in.codecorp.myapplication.Utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AnswerList {

    @SerializedName("3433")
    @Expose
    private Answer answer3433;
    @SerializedName("3437")
    @Expose
    private Answer answer3437;

    public Answer getAnswer3433() {
        return answer3433;
    }

    public void setAnswer3433(Answer answer3433) {
        this.answer3433 = answer3433;
    }

    public Answer getAnswer3437() {
        return answer3437;
    }

    public void setAnswer3437(Answer answer3437) {
        this.answer3437 = answer3437;
    }
}