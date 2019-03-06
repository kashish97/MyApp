package in.codecorp.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import in.codecorp.myapplication.Adapter.PackageAdapter2;
import in.codecorp.myapplication.Utils.Package;

public class TestActivity extends AppCompatActivity {

    ListView listView;
    List<Package> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setContentView(R.layout.activity_test);
        ExpandableTextView expTv1;
        listView = findViewById(R.id.listViw);
        init();
    }

    void init(){



// IMPORTANT - call setText on the ExpandableTextView to set the text content to display
       // expTv1.setText(getString(R.string.dummy_text1));
        Intent rcv = getIntent();
        int id = Integer.parseInt(rcv.getStringExtra("pcatid"));

        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            String URL = "http://demos.abhinavsoftware.com/gc/api/list-packages?cat_id="+id;
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
                    System.out.println();
                    System.out.println("Response is "+ response);
                    try {
                        JSONObject obj = new JSONObject(response);
                        //String array = obj.getString("records");
                        //System.out.println();
                        //System.out.println("Array is" + array);

                        //JSONObject obj1 = new JSONObject(array);
                        JSONArray array1 = obj.getJSONArray("data");
                        System.out.println("Array is" + array1);


                        for (int i = 0; i < array1.length(); i++) {
                            JSONObject responseObject = array1.getJSONObject(i);
                            String pkg_id = responseObject.optString("pkg_id");
                            String pkg_title = responseObject.optString("pkg_title");
                            String pkg_desc = responseObject.optString("pkg_desc");
                            String pkg_sdesc = responseObject.optString("pkg_sdesc");
                            String pkg_photo = responseObject.optString("pkg_photo");
                            String pkg_price = responseObject.optString("pkg_price");
                            String pkg_expiry_days = responseObject.optString("pkg_expiry_days");
                            String pkg_status = responseObject.optString("pkg_status");
                            String pkg_order = responseObject.optString("pkg_order");
                            String pcat_id = responseObject.optString("pcat_id");

                            Package aPackage = new Package(pkg_id, pkg_title, pkg_desc, pkg_sdesc, pkg_photo,pkg_price, pkg_expiry_days, pkg_status, pkg_order, pcat_id);
                            //  Courses courses = new Courses(c_id, c_title, c_desc, c_status, c_order);

                            //  OrderDetails courses= new OrderDetails(order_id, order_amount, order_date_time, order_status, customerName, house_no, street_line2, post_code, city, mobile_number
                            //     , coupon_code, percentageDiscount, menu_order);


                            data.add(aPackage);
                            // Toast.makeText(getActivity(), "Restaurant Name is" + cname, Toast.LENGTH_LONG).show();

                        }



                        PackageAdapter2 adapter = new PackageAdapter2(TestActivity.this,R.layout.layout_package_test, data );


                        //adding the adapter to listview
                      listView.setAdapter(adapter);

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
            /*{
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }


            }*/
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
