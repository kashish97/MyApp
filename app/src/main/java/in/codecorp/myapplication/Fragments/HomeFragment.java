package in.codecorp.myapplication.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import java.util.List;

import in.codecorp.myapplication.Adapter.CourseAdapter;
import in.codecorp.myapplication.R;
import in.codecorp.myapplication.Utils.Courses;
import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.views.BannerSlider;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private  static final String EXTRA_TEXT="text";
    List<Courses> data;
    RecyclerView recycleCourses;
    LinearLayoutManager layoutManager;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment createFor(String text) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(EXTRA_TEXT, text);
        fragment.setArguments(args);
        return fragment;
    }

    void addToken(){
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            String URL = "http://demos.abhinavsoftware.com/gc/api/list-courses";
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
                            JSONArray array1 = obj.getJSONArray("records");
                        System.out.println("Array is" + array1);

                        for (int i = 0; i < array1.length(); i++) {
                                JSONObject responseObject = array1.getJSONObject(i);
                                String c_id = responseObject.optString("c_id");
                                String c_title = responseObject.optString("c_title");
                                String c_desc = responseObject.optString("c_desc");
                                String c_status = responseObject.optString("c_status");
                                String c_order = responseObject.optString("c_order");

                            Courses courses = new Courses(c_id, c_title, c_desc, c_status, c_order);

                              //  OrderDetails courses= new OrderDetails(order_id, order_amount, order_date_time, order_status, customerName, house_no, street_line2, post_code, city, mobile_number
                                   //     , coupon_code, percentageDiscount, menu_order);


                                data.add(courses);
                                // Toast.makeText(getActivity(), "Restaurant Name is" + cname, Toast.LENGTH_LONG).show();

                            }




                        CourseAdapter adapter = new CourseAdapter(getActivity(), data );


                        //adding the adapter to listview
                        recycleCourses.setAdapter(adapter);

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutManager =new LinearLayoutManager( getActivity(), LinearLayoutManager.HORIZONTAL, false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_home, container, false);
        BannerSlider bannerSlider= (BannerSlider) v.findViewById(R.id.banner_slider1);
        List<Banner> banners=new ArrayList<>();
        //add banner using image url
        //banners.add(new RemoteBanner("Put banner image url here ..."));
        //add banner using resource drawable
        banners.add(new DrawableBanner(R.mipmap.upsc));
        banners.add(new DrawableBanner(R.mipmap.bon));
        banners.add(new DrawableBanner(R.mipmap.hlo));
        banners.add(new DrawableBanner(R.mipmap.member));
        bannerSlider.setBanners(banners);
        recycleCourses = v.findViewById(R.id.recycleCourses);
        recycleCourses.setHasFixedSize(true);

        recycleCourses.setLayoutManager(layoutManager);

        addToken();
        return v;
    }

}
