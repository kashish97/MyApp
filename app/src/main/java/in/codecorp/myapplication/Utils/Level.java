package in.codecorp.myapplication.Utils;

public class Level {
    String levelid;
    String levelTitle;
    String levelStatus;
    String levelOrder;
    String cId;

    public Level(String levelid, String levelTitle, String levelStatus, String levelOrder, String cId) {
        this.levelid = levelid;
        this.levelTitle = levelTitle;
        this.levelStatus = levelStatus;
        this.levelOrder = levelOrder;
        this.cId = cId;
    }

    public String getLevelid() {
        return levelid;
    }

    public void setLevelid(String levelid) {
        this.levelid = levelid;
    }

    public String getLevelTitle() {
        return levelTitle;
    }

    public void setLevelTitle(String levelTitle) {
        this.levelTitle = levelTitle;
    }

    public String getLevelStatus() {
        return levelStatus;
    }

    public void setLevelStatus(String levelStatus) {
        this.levelStatus = levelStatus;
    }

    public String getLevelOrder() {
        return levelOrder;
    }

    public void setLevelOrder(String levelOrder) {
        this.levelOrder = levelOrder;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }
}
