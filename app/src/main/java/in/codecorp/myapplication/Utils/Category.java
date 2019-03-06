package in.codecorp.myapplication.Utils;

public class Category {
    String pcat_id;
    String pcat_title;
    String pcat_desc;
    String pcat_image;
    String pcat_order;

    public Category(String pcat_id, String pcat_title, String pcat_desc, String pcat_image, String pcat_order) {
        this.pcat_id = pcat_id;
        this.pcat_title = pcat_title;
        this.pcat_desc = pcat_desc;
        this.pcat_image = pcat_image;
        this.pcat_order = pcat_order;
    }

    public String getPcat_id() {
        return pcat_id;
    }

    public void setPcat_id(String pcat_id) {
        this.pcat_id = pcat_id;
    }

    public String getPcat_title() {
        return pcat_title;
    }

    public void setPcat_title(String pcat_title) {
        this.pcat_title = pcat_title;
    }

    public String getPcat_desc() {
        return pcat_desc;
    }

    public void setPcat_desc(String pcat_desc) {
        this.pcat_desc = pcat_desc;
    }

    public String getPcat_image() {
        return pcat_image;
    }

    public void setPcat_image(String pcat_image) {
        this.pcat_image = pcat_image;
    }

    public String getPcat_order() {
        return pcat_order;
    }

    public void setPcat_order(String pcat_order) {
        this.pcat_order = pcat_order;
    }
}
