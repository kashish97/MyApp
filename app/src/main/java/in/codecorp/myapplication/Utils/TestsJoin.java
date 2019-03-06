package in.codecorp.myapplication.Utils;

import com.google.gson.annotations.SerializedName;

/**
 * Created by skushwa1 on 12/3/2017.
 */
public class TestsJoin {
    @SerializedName("join_id")
    private String join_id;

    @SerializedName("t_id")
    private String t_id;

    @SerializedName("q_id")
    private String q_id;

    @SerializedName("sub_id")
    private String sub_id;

    @SerializedName("user_id")
    private String user_id;

    @SerializedName("join_marks")
    private String join_marks;

    @SerializedName("join_minus_marks")
    private String join_minus_marks;

    @SerializedName("subject")
    private TestSubjects subject;

    public String getJoin_id() {
        return join_id;
    }

    public void setJoin_id(String join_id) {
        this.join_id = join_id;
    }

    public String getT_id() {
        return t_id;
    }

    public void setT_id(String t_id) {
        this.t_id = t_id;
    }

    public String getQ_id() {
        return q_id;
    }

    public void setQ_id(String q_id) {
        this.q_id = q_id;
    }

    public String getSub_id() {
        return sub_id;
    }

    public void setSub_id(String sub_id) {
        this.sub_id = sub_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getJoin_marks() {
        return join_marks;
    }

    public void setJoin_marks(String join_marks) {
        this.join_marks = join_marks;
    }

    public String getJoin_minus_marks() {
        return join_minus_marks;
    }

    public void setJoin_minus_marks(String join_minus_marks) {
        this.join_minus_marks = join_minus_marks;
    }

    public TestSubjects getSubject() {
        return subject;
    }

    public void setSubject(TestSubjects subject) {
        this.subject = subject;
    }
}
