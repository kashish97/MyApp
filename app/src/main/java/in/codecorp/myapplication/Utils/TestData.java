package in.codecorp.myapplication.Utils;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TestData {
    @SerializedName("answerList")
    private List<AnswerList> answerLists;

    @SerializedName("timeSpentList")
    private TimeSpent timespents;

    @SerializedName("questionCount")
    private String questionCount;

    @SerializedName("questions")
    private List<QuestionModel> questionModels = null;



    public List<AnswerList> getAnswerLists() {
        return answerLists;
    }

    public void setAnswerLists(List<AnswerList> answerLists) {
        this.answerLists = answerLists;
    }

    public TimeSpent getTimespents() {
        return timespents;
    }

    public void setTimespents(TimeSpent timespents) {
        this.timespents = timespents;
    }

    public String getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(String questionCount) {
        this.questionCount = questionCount;
    }

    public List<QuestionModel> getQuestionModels() {
        return questionModels;
    }

    public void setQuestionModels(List<QuestionModel> questionModels) {
        this.questionModels = questionModels;
    }


}