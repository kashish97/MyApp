package in.codecorp.myapplication.Request;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import in.codecorp.myapplication.Utils.UserProfile;

public class UpdateProfileRequest {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("UserProfile")
    @Expose
    private UserProfile userProfile;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

}