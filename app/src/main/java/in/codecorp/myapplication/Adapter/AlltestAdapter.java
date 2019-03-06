package in.codecorp.myapplication.Adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import in.codecorp.myapplication.PackageActivity;
import in.codecorp.myapplication.QuestionsActivity;
import in.codecorp.myapplication.R;
import in.codecorp.myapplication.SubscribedTestActivity;
import in.codecorp.myapplication.Utils.Test;

public class AlltestAdapter extends RecyclerView.Adapter<AlltestAdapter.ProductViewHolder> {

    private Context mCtx;
    private String pp, charges;
    private List<Test> orderList;
    String subtotal, discount;

    public AlltestAdapter(Context mCtx, List<Test> orderList) {
        this.mCtx = mCtx;
        this.orderList = orderList;
        setHasStableIds(true);

    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_test, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        final Test courses = orderList.get(position);

        holder.t1.setText(courses.getT_id());
        holder.t2.setText(courses.getT_title());
        holder.t3.setText(Html.fromHtml(courses.getT_description()));
        holder.t4.setText(courses.getT_duration());
        holder.t5.setText(Html.fromHtml(courses.getT_instructions()));
        holder.t6.setText(courses.getLevel_id());
        holder.t7.setText(courses.getT_qtype());
        holder.t8.setText(courses.getC_id());
        holder.t9.setText(courses.getT_auto_sourcedata());

           // holder.t9.setText("Test not Subscribed");

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RequestQueue requestQueue = Volley.newRequestQueue(mCtx);
                    String url = "http://demos.abhinavsoftware.com/gc/api/list-package-by-test-id?t_id="+ courses.getT_id();
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            System.out.println("hi"+ response);
                            JSONObject obj = null;
                            try {
                                obj = new JSONObject(response);
                                JSONArray array1 = obj.getJSONArray("records");
                                for ( int i = 0; i<array1.length(); i++){
                                    JSONObject jsonObject = array1.getJSONObject(i);
                                    //System.out.println(jsonObject.optString("pkg_title"));
                                    Intent intent = new Intent(mCtx, PackageActivity.class);
                                    intent.putExtra("id",jsonObject.optString("pkg_id"));
                                    intent.putExtra("title",jsonObject.optString("pkg_title"));
                                    intent.putExtra("desc",(jsonObject.optString("pkg_desc")));
                                    intent.putExtra("sdesc",(jsonObject.optString("pkg_sdesc")));
                                    intent.putExtra("photo",jsonObject.optString("pkg_photo"));
                                    intent.putExtra("price",jsonObject.optString("pkg_price"));
                                    intent.putExtra("days",jsonObject.optString("pkg_expiry_days"));
                                    intent.putExtra("status",jsonObject.optString("pkg_status"));
                                    intent.putExtra("order",jsonObject.optString("pkg_order"));
                                    intent.putExtra("c_id",jsonObject.optString("pcat_id"));
                                    mCtx.startActivity(intent);
                                }
                                //  JSONObject jsonObject = array1.getJSONObject(0);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            //String array = obj.getString("records");
                            //System.out.println();
                            //System.out.println("Array is" + array);

                            //JSONObject obj1 = new JSONObject(array);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    requestQueue.add(stringRequest);
                }
            });
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
        TextView t1, t2, t3, t4, t5, t6, t7, t8, t9;
        ListView listView;
        Button view;

        public ProductViewHolder(View itemView) {
            super(itemView);
            t1 = itemView.findViewById(R.id.textView5);
            t2 = itemView.findViewById(R.id.textView6);
            t3 = itemView.findViewById(R.id.textView7);
            t4 = itemView.findViewById(R.id.textView8);
            t5 = itemView.findViewById(R.id.textView9);
            t6 = itemView.findViewById(R.id.textView10);
            t7 = itemView.findViewById(R.id.textView11);
            t8 = itemView.findViewById(R.id.textView12);
            t9 = itemView.findViewById(R.id.textView13);


        }
    }
}
