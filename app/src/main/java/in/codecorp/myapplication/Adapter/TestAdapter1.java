package in.codecorp.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import in.codecorp.myapplication.QuestionsActivity;
import in.codecorp.myapplication.R;
import in.codecorp.myapplication.TestActivity;
import in.codecorp.myapplication.Utils.Courses;
import in.codecorp.myapplication.Utils.Level;
import in.codecorp.myapplication.Utils.Test;
import in.codecorp.myapplication.Utils.mTest;


public class TestAdapter1 extends RecyclerView.Adapter<TestAdapter1.ProductViewHolder> {

    private Context mCtx;
    private String pp, charges;
    private List<mTest> orderList;
    List<Test> data;
    String subtotal, discount;

    public TestAdapter1(Context mCtx, List<mTest> orderList) {
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
        final mTest courses = orderList.get(position);

        holder.t1.setText(courses.getT_id());
        holder.t2.setText(courses.getT_title());
        holder.t3.setText(Html.fromHtml(courses.getT_description()));
        holder.t4.setText(courses.getT_duration());
        holder.t5.setText(Html.fromHtml(courses.getT_instructions()));
        holder.t6.setText(courses.getMt_total_marks());
        holder.t7.setText(courses.getT_qtype());
        holder.t8.setText(courses.getMt_start_date());
        holder.t9.setText(courses.getMt_minutes_passed());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mCtx, QuestionsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("tid", courses.getT_id());
                intent.putExtra("mtid", courses.getMt_id());
                intent.putExtra("ins", courses.getT_instructions());
                intent.putExtra("dur", courses.getT_duration());


                mCtx.startActivity(intent);
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
