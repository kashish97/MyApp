package in.codecorp.myapplication.Utils;

public class Package {

   String pkg_id,
            pkg_title,
            pkg_desc,
            pkg_sdesc,
            pkg_photo,
            pkg_price,
            pkg_expiry_days,
            pkg_status,
            pkg_order,
            pcat_id;

    public Package(String pkg_id, String pkg_title, String pkg_desc, String pkg_sdesc, String pkg_photo, String pkg_price, String pkg_expiry_days, String pkg_status, String pkg_order, String pcat_id) {
        this.pkg_id = pkg_id;
        this.pkg_title = pkg_title;
        this.pkg_desc = pkg_desc;
        this.pkg_sdesc = pkg_sdesc;
        this.pkg_photo = pkg_photo;
        this.pkg_price = pkg_price;
        this.pkg_expiry_days = pkg_expiry_days;
        this.pkg_status = pkg_status;
        this.pkg_order = pkg_order;
        this.pcat_id = pcat_id;
    }

    public String getPkg_id() {
        return pkg_id;
    }

    public void setPkg_id(String pkg_id) {
        this.pkg_id = pkg_id;
    }

    public String getPkg_title() {
        return pkg_title;
    }

    public void setPkg_title(String pkg_title) {
        this.pkg_title = pkg_title;
    }

    public String getPkg_desc() {
        return pkg_desc;
    }

    public void setPkg_desc(String pkg_desc) {
        this.pkg_desc = pkg_desc;
    }

    public String getPkg_sdesc() {
        return pkg_sdesc;
    }

    public void setPkg_sdesc(String pkg_sdesc) {
        this.pkg_sdesc = pkg_sdesc;
    }

    public String getPkg_photo() {
        return pkg_photo;
    }

    public void setPkg_photo(String pkg_photo) {
        this.pkg_photo = pkg_photo;
    }

    public String getPkg_price() {
        return pkg_price;
    }

    public void setPkg_price(String pkg_price) {
        this.pkg_price = pkg_price;
    }

    public String getPkg_expiry_days() {
        return pkg_expiry_days;
    }

    public void setPkg_expiry_days(String pkg_expiry_days) {
        this.pkg_expiry_days = pkg_expiry_days;
    }

    public String getPkg_status() {
        return pkg_status;
    }

    public void setPkg_status(String pkg_status) {
        this.pkg_status = pkg_status;
    }

    public String getPkg_order() {
        return pkg_order;
    }

    public void setPkg_order(String pkg_order) {
        this.pkg_order = pkg_order;
    }

    public String getPcat_id() {
        return pcat_id;
    }

    public void setPcat_id(String pcat_id) {
        this.pcat_id = pcat_id;
    }
}

