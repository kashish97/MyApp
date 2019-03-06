package in.codecorp.myapplication.Utils;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuestionModel {
    @SerializedName("q_id")
    private int qId;

    @SerializedName("q_title")
    private String qTitle;

    @SerializedName("q_description")
    private String qDescription;

    @SerializedName("q_file")
    private String qFile;

    @SerializedName("q_opt1")
    private String qOpt1;

    @SerializedName("q_opt2")
    private String qOpt2;

    @SerializedName("q_opt3")
    private String qOpt3;

    @SerializedName("q_opt4")
    private String qOpt4;

    @SerializedName("q_opt5")
    private String qOpt5;

    @SerializedName("q_opt6")
    private String qOpt6;

    @SerializedName("q_answer")
    private String qAnswer;

    @SerializedName("q_marks")
    private String qMarks;

    @SerializedName("q_minus_marks")
    private String qMinusMarks;

    @SerializedName("q_answer_description")
    private String qAnswerDescription;

    @SerializedName("q_diff_level")
    private String qDiffLevel;

    @SerializedName("testsJoin")
    private List<TestsJoin> testsJoin;

    @SerializedName("translations")
    private List<Object> translations;

    public int getQId() {
        return qId;
    }

    public void setQId(int qId) {
        this.qId = qId;
    }

    public String getQTitle() {
        return qTitle;
    }

    public void setQTitle(String qTitle) {
        this.qTitle = qTitle;
    }

    public String getQDescription() {
        return qDescription;
    }

    public void setQDescription(String qDescription) {
        this.qDescription = qDescription;
    }

    public String getQFile() {
        return qFile;
    }

    public void setQFile(String qFile) {
        this.qFile = qFile;
    }

    public String getQOpt1() {
        return qOpt1;
    }

    public void setQOpt1(String qOpt1) {
        this.qOpt1 = qOpt1;
    }

    public String getQOpt2() {
        return qOpt2;
    }

    public void setQOpt2(String qOpt2) {
        this.qOpt2 = qOpt2;
    }

    public String getQOpt3() {
        return qOpt3;
    }

    public void setQOpt3(String qOpt3) {
        this.qOpt3 = qOpt3;
    }

    public String getQOpt4() {
        return qOpt4;
    }

    public void setQOpt4(String qOpt4) {
        this.qOpt4 = qOpt4;
    }

    public String getQOpt5() {
        return qOpt5;
    }

    public void setQOpt5(String qOpt5) {
        this.qOpt5 = qOpt5;
    }

    public String getQOpt6() {
        return qOpt6;
    }

    public void setQOpt6(String qOpt6) {
        this.qOpt6 = qOpt6;
    }

    public String getQAnswer() {
        return qAnswer;
    }

    public void setQAnswer(String qAnswer) {
        this.qAnswer = qAnswer;
    }

    public String getQMarks() {
        return qMarks;
    }

    public void setQMarks(String qMarks) {
        this.qMarks = qMarks;
    }

    public String getQMinusMarks() {
        return qMinusMarks;
    }

    public void setQMinusMarks(String qMinusMarks) {
        this.qMinusMarks = qMinusMarks;
    }

    public String getQAnswerDescription() {
        return qAnswerDescription;
    }

    public void setQAnswerDescription(String qAnswerDescription) {
        this.qAnswerDescription = qAnswerDescription;
    }

    public String getQDiffLevel() {
        return qDiffLevel;
    }

    public void setQDiffLevel(String qDiffLevel) {
        this.qDiffLevel = qDiffLevel;
    }

    public List<TestsJoin> getTestsJoin() {
        return testsJoin;
    }

    public void setTestsJoin(List<TestsJoin> testsJoin) {
        this.testsJoin = testsJoin;
    }

    public List<Object> getTranslations() {
        return translations;
    }

    public void setTranslations(List<Object> translations) {
        this.translations = translations;
    }
}