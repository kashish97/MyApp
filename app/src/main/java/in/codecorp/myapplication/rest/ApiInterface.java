package in.codecorp.myapplication.rest;


import in.codecorp.myapplication.Response.AnswerSheetResponse;
import in.codecorp.myapplication.Response.BaseResponse;
import in.codecorp.myapplication.Response.LoggedInOrderDetailResponse;
import in.codecorp.myapplication.Response.LoggedInOrderResponse;
import in.codecorp.myapplication.Response.LoginResponse;
import in.codecorp.myapplication.Response.ProfileResponse;
import in.codecorp.myapplication.Response.RegisterResponse;
import in.codecorp.myapplication.Response.TestLevelResponse;
import in.codecorp.myapplication.Response.TestResponse;
import in.codecorp.myapplication.Response.TestSubjectResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("register")
    Call<RegisterResponse> registerUser(@Field("data") String registerJson);

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> loginUser(@Field("data") String loginJson);

    @FormUrlEncoded
    @POST("save-profile")
    Call<BaseResponse> saveProfile(@Field("data") String profileJson);

    @FormUrlEncoded
    @POST("evaluate")
    Call<BaseResponse> evaluate(@Field("data") String evaluateJson);

    @FormUrlEncoded
    @POST("heartbeat")
    Call<LoginResponse> heartbeat(@Field("data") String heartJson);

    @FormUrlEncoded
    @POST("add-to-dashboard")
    Call<BaseResponse> addFreeCourse(@Field("data") String heartJson);

    @GET("get-profile?")
    Call<ProfileResponse> getProfile(@Query("token") String token);

    @GET("list-tests?")
    Call<TestLevelResponse> listLevel(@Query("token") String token, @Query("level_id") String l_id);

    @GET("list-subjects?")
    Call<TestSubjectResponse> getSubjects(@Query("token") String token, @Query("t_id") String t_id);


    @GET("get-order?")
    Call<LoggedInOrderDetailResponse> orderDetail(@Query("token") String token, @Query("od_id") String od_id);

    @GET("list-myorders?")
    Call<LoggedInOrderResponse> listOrder(@Query("token") String token, @Query("perpage") String perpage);

    //GET URI
    /*@GET("list-package-categories")
    //Call<CategoryResponse> packageCourses();

    @GET("list-packages?")
    //Call<CategoryDetailResponse> packageCategories(@Query("cat_id") String token, @Query("free") String page);

    @GET("list-courses")
   // Call<CourseResponse> listCourses();

    @GET("list-myorders?")
    Call<LoggedInOrderResponse> listCourses(@Query("token") String token, @Query("page") String page);


    @GET("list-affiliates")
   // Call<AffiliatedResponse> listAffilated();

    @GET("list-levels?")
    //Call<LevelResponse> listLevel(@Query("token") String token);*/

    @GET("get-answersheet?")
    Call<AnswerSheetResponse> getAnswer(@Query("token") String token, @Query("mt_id") String mt_id);

    @GET("get-test?")
    Call<TestResponse> getTest(@Query("token") String token, @Query("mt_id") String mt_id);

    @GET
    Call<ResponseBody> getImage(@Url String url);
}