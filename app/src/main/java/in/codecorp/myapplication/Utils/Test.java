package in.codecorp.myapplication.Utils;

public class Test {
     String t_id,
             t_title,
             t_duration,
             t_description,
             t_instructions,
             t_order,
             t_qtype,
             t_auto_sourcedata,
             t_status,
             level_id,
             c_id;

    public Test(String t_id, String t_title, String t_duration, String t_description, String t_instructions, String t_order, String t_qtype, String t_auto_sourcedata, String t_status, String level_id, String c_id) {
        this.t_id = t_id;
        this.t_title = t_title;
        this.t_duration = t_duration;
        this.t_description = t_description;
        this.t_instructions = t_instructions;
        this.t_order = t_order;
        this.t_qtype = t_qtype;
        this.t_auto_sourcedata = t_auto_sourcedata;
        this.t_status = t_status;
        this.level_id = level_id;
        this.c_id = c_id;
    }

    public String getT_id() {
        return t_id;
    }

    public void setT_id(String t_id) {
        this.t_id = t_id;
    }

    public String getT_title() {
        return t_title;
    }

    public void setT_title(String t_title) {
        this.t_title = t_title;
    }

    public String getT_duration() {
        return t_duration;
    }

    public void setT_duration(String t_duration) {
        this.t_duration = t_duration;
    }

    public String getT_description() {
        return t_description;
    }

    public void setT_description(String t_description) {
        this.t_description = t_description;
    }

    public String getT_instructions() {
        return t_instructions;
    }

    public void setT_instructions(String t_instructions) {
        this.t_instructions = t_instructions;
    }

    public String getT_order() {
        return t_order;
    }

    public void setT_order(String t_order) {
        this.t_order = t_order;
    }

    public String getT_qtype() {
        return t_qtype;
    }

    public void setT_qtype(String t_qtype) {
        this.t_qtype = t_qtype;
    }

    public String getT_auto_sourcedata() {
        return t_auto_sourcedata;
    }

    public void setT_auto_sourcedata(String t_auto_sourcedata) {
        this.t_auto_sourcedata = t_auto_sourcedata;
    }

    public String getT_status() {
        return t_status;
    }

    public void setT_status(String t_status) {
        this.t_status = t_status;
    }

    public String getLevel_id() {
        return level_id;
    }

    public void setLevel_id(String level_id) {
        this.level_id = level_id;
    }

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }
}
