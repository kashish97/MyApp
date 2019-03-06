package in.codecorp.myapplication.Response;

import com.google.gson.annotations.SerializedName;

public class TokenObject {
    @SerializedName("token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
