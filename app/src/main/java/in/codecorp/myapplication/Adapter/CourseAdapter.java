package in.codecorp.myapplication.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

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

import in.codecorp.myapplication.R;
import in.codecorp.myapplication.Utils.Courses;
import in.codecorp.myapplication.Utils.Level;


public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ProductViewHolder> {

    private Context mCtx;
    private String pp, charges;
    private List<Courses> orderList;
    List<Level> data;
    String subtotal, discount;

    public CourseAdapter(Context mCtx, List<Courses> orderList) {
        this.mCtx = mCtx;
        this.orderList = orderList;
        setHasStableIds(true);

    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_course_adaptor, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        final Courses courses = orderList.get(position);
        holder.name.setText(courses.getCtitle().toString().toUpperCase());
        int a = Integer.parseInt(courses.getCid());
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(mCtx);
            String URL = "http://demos.abhinavsoftware.com/gc/api/list-levels?c_id="+a;
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
                            String level_id = responseObject.optString("level_id");
                            String level_title = responseObject.optString("level_title");
                            String level_status = responseObject.optString("level_status");
                            String level_order = responseObject.optString("level_order");
                            String c_id = responseObject.optString("c_id");

                            Level level = new Level(level_id, level_title, level_status, level_order, c_id);
                          //  Courses courses = new Courses(c_id, c_title, c_desc, c_status, c_order);

                            //  OrderDetails courses= new OrderDetails(order_id, order_amount, order_date_time, order_status, customerName, house_no, street_line2, post_code, city, mobile_number
                            //     , coupon_code, percentageDiscount, menu_order);


                            data.add(level);
                            // Toast.makeText(getActivity(), "Restaurant Name is" + cname, Toast.LENGTH_LONG).show();

                        }



                         ListLevelAdapter adapter = new ListLevelAdapter(mCtx,R.layout.layout_list_level, data );

                        for (int i=0; i<data.size(); i++){
                            Level cart1= data.get(i);
                            View view= View.inflate(mCtx,R.layout.layout_list_level, null);
                            holder.listView.addView(adapter.getView(i, view, null));

                        }
                        //adding the adapter to listview
                     //   holder.listView.setAdapter(adapter);


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
    public int getItemCount () {
        return orderList.size();
    }



    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        LinearLayout listView;
        Button view;

        public ProductViewHolder(View itemView) {
            super(itemView);
           name = itemView.findViewById(R.id.textView2);
           listView = itemView.findViewById(R.id.listLevel);


        }
    }
}

