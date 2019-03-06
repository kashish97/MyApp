package in.codecorp.myapplication.Utils;

public class Courses {
    String cid;
    String ctitle;
    String cdesc;
    String cstatus;
    String corder;

    public Courses(String cid, String ctitle, String cdesc, String cstatus, String corder) {
        this.cid = cid;
        this.ctitle = ctitle;
        this.cdesc = cdesc;
        this.cstatus = cstatus;
        this.corder = corder;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCtitle() {
        return ctitle;
    }

    public void setCtitle(String ctitle) {
        this.ctitle = ctitle;
    }

    public String getCdesc() {
        return cdesc;
    }

    public void setCdesc(String cdesc) {
        this.cdesc = cdesc;
    }

    public String getCstatus() {
        return cstatus;
    }

    public void setCstatus(String cstatus) {
        this.cstatus = cstatus;
    }

    public String getCorder() {
        return corder;
    }

    public void setCorder(String corder) {
        this.corder = corder;
    }
}
