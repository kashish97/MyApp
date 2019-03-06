package in.codecorp.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import in.codecorp.myapplication.Adapter.AlltestAdapter;
import in.codecorp.myapplication.Adapter.CourseAdapter;
import in.codecorp.myapplication.Adapter.TestAdapter1;
import in.codecorp.myapplication.Utils.Courses;
import in.codecorp.myapplication.Utils.Test;
import in.codecorp.myapplication.Utils.mTest;

public class TestDetailActivity extends AppCompatActivity {
    String token;
    String levelid;
    SessionManager sessionManager;
    List<Test> data, data2;
    List<mTest> data1;
    List<String> ab, ac;
    RecyclerView recycleCourses, recycle2;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> user = sessionManager.getUserDetails();
        progressDialog = new ProgressDialog(TestDetailActivity.this);
        progressDialog.setMessage("Loading");
        progressDialog.show();
        progressDialog.setCancelable(false);
        token = user.get(SessionManager.KEY_TOKEN);
        if(token!=null){
            Intent rcv = getIntent();
            levelid = rcv.getStringExtra("levelid");
            //recycleCourses = (RecyclerView) findViewById(R.id.recycle1) ;
            //recycleCourses.setLayoutManager(new LinearLayoutManager(this));
            recycle2 = (RecyclerView) findViewById(R.id.recycle2) ;
            recycle2.setLayoutManager(new LinearLayoutManager(this));
            getTest();
         //   getTest1();
        }
         else {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            finish();
        }

    }



    void getTest(){

        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            String URL = "http://demos.abhinavsoftware.com/gc/api/list-tests?token="+token+"&level_id="+levelid;

           // System.out.println("url is "+ URL);
            //JSONObject map = new JSONObject();
            //map.put("TokenKey", uuid);
            //map.put("Customer_Type", "Restaurant");
            //map.put("CallingChannel","App");
            //final String requestBody = map.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    data = new ArrayList<>();
                    // Log.i("VOLLEY", response);
             //       System.out.println();
                        System.out.println("Response is "+ response);
                    try {
                        JSONObject obj = new JSONObject(response);
                        //String array = obj.getString("records");
                        //System.out.println();
                        //System.out.println("Array is" + array);

                        //JSONObject obj1 = new JSONObject(array);
                        JSONArray array1 = obj.getJSONArray("records");
                        //  System.out.println("Array is" + array1);

                        for (int i = 0; i < array1.length(); i++) {

                            JSONObject array2 = array1.getJSONObject(i);


                            String t_id2 = array2.optString("t_id");
                            String t_title = array2.optString("t_title");
                            String t_duration = array2.optString("t_duration");
                            String t_description = array2.optString("t_description");
                            String t_instructions = array2.optString("t_instructions");
                            String t_order = array2.optString("t_order");
                            String t_qtype = array2.optString("t_qtype");
                            String t_auto_sourcedata = array2.optString("subscriptions");
                            String t_status = array2.optString("t_status");
                            String level_id = array2.optString("level_id");
                            String c_id = array2.optString("c_id");


                            Test courses = new Test(t_id2, t_title, t_duration, t_description, t_instructions,
                                    t_order, t_qtype, t_auto_sourcedata, t_status, level_id, c_id
                            );



                            //  OrderDetails courses= new OrderDetails(order_id, order_amount, order_date_time, order_status, customerName, house_no, street_line2, post_code, city, mobile_number
                            //     , coupon_code, percentageDiscount, menu_order);


                            data.add(courses);
                            // Toast.makeText(getActivity(), "Restaurant Name is" + cname, Toast.LENGTH_LONG).show();

                        }

                        AlltestAdapter adapter = new AlltestAdapter(TestDetailActivity.this, data );

                        if(data.size()==0){

                        }
                        else {
                            //Toast.makeText(TestDetailActivity.this,data.size()+" total Tests", Toast.LENGTH_LONG).show();
                            System.out.println(data.size()+ "Total Tests");
                        }
                        progressDialog.dismiss();
                        //adding the adapter to listview
                        recycle2.setAdapter(adapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.toString());
                }
            })

                    ;

            requestQueue.add(stringRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void getTest1(){

        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            String URL = "http://demos.abhinavsoftware.com/gc/api/list-subscribed-tests?token="+token+"&level_id="+levelid;
            //JSONObject map = new JSONObject();
            //map.put("TokenKey", uuid);
            //map.put("Customer_Type", "Restaurant");
            //map.put("CallingChannel","App");
            //final String requestBody = map.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    data1 = new ArrayList<>();
                    data2 = new ArrayList<>();

                    // Log.i("VOLLEY", response);
                    System.out.println();
                    //    System.out.println("Response is "+ response);
                    try {
                        JSONObject obj = new JSONObject(response);
                        //String array = obj.getString("records");
                        //System.out.println();
                        //System.out.println("Array is" + array);

                        //JSONObject obj1 = new JSONObject(array);
                        JSONArray array1 = obj.getJSONArray("records");
                        //  System.out.println("Array is" + array1);

                        for (int i = 0; i < array1.length(); i++) {

                            JSONObject responseObject = array1.getJSONObject(i);
                            String mt_id = responseObject.optString("mt_id");
                            String user_id = responseObject.optString("user_id");
                            String t_id = responseObject.optString("t_id");
                            String mt_status = responseObject.optString("mt_status");
                            String mt_start_date = responseObject.optString("mt_start_date");
                            String mt_expiry_date = responseObject.optString("mt_expiry_date");
                            String mt_total_marks = responseObject.optString("mt_total_marks");
                            String mt_marks_obt = responseObject.optString("mt_marks_obt");
                            String mt_percentage = responseObject.optString("mt_percentage");
                            String mt_result_date = responseObject.optString("mt_result_date");
                            String mt_minutes_passed = responseObject.optString("mt_minutes_passed");
                            String test = responseObject.optString("test");
                            JSONObject array2 = responseObject.getJSONObject("test");
                           // System.out.println("Test is" + array2);

                            String t_id2 = array2.optString("t_id");
                            String t_title = array2.optString("t_title");
                            String t_duration = array2.optString("t_duration");
                            String t_description = array2.optString("t_description");
                            String t_instructions = array2.optString("t_instructions");
                            String t_order = array2.optString("t_order");
                            String t_qtype = array2.optString("t_qtype");
                            String t_auto_sourcedata = array2.optString("t_auto_sourcedata");
                            String t_status = array2.optString("t_status");
                            String level_id = array2.optString("level_id");
                            String c_id = array2.optString("c_id");



                            mTest courses = new mTest(mt_id, user_id, t_id, mt_status, mt_start_date, mt_expiry_date, mt_total_marks, mt_marks_obt,
                                    mt_percentage, mt_result_date,mt_minutes_passed, test, t_id2, t_title, t_duration, t_description, t_instructions,
                                    t_order, t_qtype, t_auto_sourcedata, t_status, level_id, c_id
                            );

                         //   data2.add(new Test(t_id2, t_title, t_duration, t_description, t_instructions, t_order, t_qtype, t_auto_sourcedata, t_status, level_id, c_id));

                            //  OrderDetails courses= new OrderDetails(order_id, order_amount, order_date_time, order_status, customerName, house_no, street_line2, post_code, city, mobile_number
                            //     , coupon_code, percentageDiscount, menu_order);



                            data1.add(courses);
                            // Toast.makeText(getActivity(), "Restaurant Name is" + cname, Toast.LENGTH_LONG).show();

                        }




                        TestAdapter1 adapter = new TestAdapter1(TestDetailActivity.this, data1 );




                        //adding the adapter to listview
                        recycleCourses.setAdapter(adapter);
                        if(data1.size()==0){
                            //recycleCourses.setVisibility(View.GONE);
                            // ll.setVisibility(View.VISIBLE);
                            //  Toast.makeText(TestDetailActivity.this,"No subscribed Tests", Toast.LENGTH_LONG).show();

                        }
                        else {
                            //Toast.makeText(TestDetailActivity.this,data1.size()+" subscribed Tests", Toast.LENGTH_LONG).show();
                            System.out.println(data1.size()+ " Subscribed Tests");
                            System.out.println(recycleCourses.getAdapter().getItemCount()+ " Subscribed Tests");
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.toString());
                }
            })

                    ;

            requestQueue.add(stringRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
