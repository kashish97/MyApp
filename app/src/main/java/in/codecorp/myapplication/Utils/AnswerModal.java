package in.codecorp.myapplication.Utils;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AnswerModal {
    @SerializedName("myTestModel")
    private MyTestModel myTestModel;

    @SerializedName("timespent")
    private String timespent;

    @SerializedName("questionModels")
    private List<QuestionModel> questionModels = null;

    @SerializedName("answerList")
    private String answerList;

    @SerializedName("rankdata")
    private Rankdata rankdata;

    @SerializedName("answerAnalysis")
    private AnswerAnalysis answerAnalysis;

    public MyTestModel getMyTestModel() {
        return myTestModel;
    }

    public void setMyTestModel(MyTestModel myTestModel) {
        this.myTestModel = myTestModel;
    }

    public String getTimespent() {
        return timespent;
    }

    public void setTimespent(String timespent) {
        this.timespent = timespent;
    }

    public List<QuestionModel> getQuestionModels() {
        return questionModels;
    }

    public void setQuestionModels(List<QuestionModel> questionModels) {
        this.questionModels = questionModels;
    }

    public String getAnswerList() {
        return answerList;
    }

    public void setAnswerList(String answerList) {
        this.answerList = answerList;
    }

    public Rankdata getRankdata() {
        return rankdata;
    }

    public void setRankdata(Rankdata rankdata) {
        this.rankdata = rankdata;
    }

    public AnswerAnalysis getAnswerAnalysis() {
        return answerAnalysis;
    }

    public void setAnswerAnalysis(AnswerAnalysis answerAnalysis) {
        this.answerAnalysis = answerAnalysis;
    }
}