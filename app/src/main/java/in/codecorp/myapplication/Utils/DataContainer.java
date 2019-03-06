package in.codecorp.myapplication.Utils;

import in.codecorp.myapplication.Response.ProfileResponse;

public class DataContainer {
    private static mTest testLevel;
    private static ProfileResponse profileResponse;
    private static Category category;

    public static Category getCategory() {
        return category;
    }

    public static void setCategory(Category category) {
        DataContainer.category = category;
    }

    public static ProfileResponse getProfileResponse() {
        return profileResponse;
    }

    public static void setProfileResponse(ProfileResponse profileResponse) {
        DataContainer.profileResponse = profileResponse;
    }

    public static mTest getTestLevel() {
       return testLevel;
    }

    public static void setTestLevel(mTest testLevel) {
        DataContainer.testLevel = testLevel;
    }
}