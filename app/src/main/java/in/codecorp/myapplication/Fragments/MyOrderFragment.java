package in.codecorp.myapplication.Fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import in.codecorp.myapplication.Adapter.CourseAdapter;
import in.codecorp.myapplication.Adapter.OrderAdapter;
import in.codecorp.myapplication.LoginActivity;
import in.codecorp.myapplication.OrderDetailActivity;
import in.codecorp.myapplication.R;
import in.codecorp.myapplication.Response.LoggedInOrderResponse;
import in.codecorp.myapplication.SessionManager;
import in.codecorp.myapplication.Utils.Courses;
import in.codecorp.myapplication.Utils.LoggedInRecord;
import in.codecorp.myapplication.Utils.Order;
import in.codecorp.myapplication.Utils.TopRankerPreference;
import in.codecorp.myapplication.rest.ApiClient;
import in.codecorp.myapplication.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * A simple {@link Fragment} subclass.
 */


public class MyOrderFragment extends Fragment {
    private View v;
    private ListView listView;
    private CourseAdapter courseAdapter;
    SessionManager sessionManager;
    String token;
    private  static final String EXTRA_TEXT="text";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_my_order, container, false);
        sessionManager = new SessionManager(getActivity());
        HashMap<String, String> user = sessionManager.getUserDetails();
        token = user.get(SessionManager.KEY_TOKEN);
        if(token!=null){
            listView = (ListView) v.findViewById(R.id.listOrder);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (courseAdapter != null) {
                        Intent intent = new Intent(getActivity(), OrderDetailActivity.class);
                        intent.putExtra(OrderDetailActivity.ORDER_ID, courseAdapter.getItem(position).getOdId());
                        startActivity(intent);
                    }
                }
            });
            getTest();
        }
        else {
            Intent i = new Intent(getActivity(), LoginActivity.class);
            startActivity(i);
        }


        return v;
    }

    public static MyOrderFragment createFor(String text) {
        MyOrderFragment fragment = new MyOrderFragment();
        Bundle args = new Bundle();
        args.putString(EXTRA_TEXT, text);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onResume() {
        super.onResume();
        //((DashboardActivity) getActivity()).getSupportActionBar().setTitle("Orders");
    }

    private void getTest() {
        //DialogUtitlity.showLoading(getActivity());
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<LoggedInOrderResponse> callClient = apiService.listOrder(token, "3");
        callClient.enqueue(new Callback<LoggedInOrderResponse>() {
            @Override
            public void onResponse(Call<LoggedInOrderResponse> call, retrofit2.Response<LoggedInOrderResponse> response) {
               // DialogUtitlity.hideLoading();
                int statusCode = response.code();
                if (statusCode == 200) {
                    if (response.body() instanceof LoggedInOrderResponse) {
                        LoggedInOrderResponse loggedInOrderResponse = response.body();
                        courseAdapter = new CourseAdapter(getActivity(), R.layout.row_course, loggedInOrderResponse.getRecords());
                        listView.setAdapter(courseAdapter);
                        Toast.makeText(getActivity().getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoggedInOrderResponse> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), "Fail error" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class CourseAdapter extends ArrayAdapter<LoggedInRecord> {
        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");

        public CourseAdapter(Context context, int layoutResourceId, List<LoggedInRecord> data) {
            super(context, layoutResourceId, data);
            format.setTimeZone(TimeZone.getTimeZone("IST"));
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            CourseHolder holder = null;
            if (row == null) {
                LayoutInflater inflater = getActivity().getLayoutInflater();
                row = inflater.inflate(R.layout.row_order, parent, false);
                holder = new CourseHolder();
                holder.rowLayout = (LinearLayout) row.findViewById(R.id.rowLayout);
                holder.txtId = (TextView)row.findViewById(R.id.txtId);
                holder.txtDate = (TextView)row.findViewById(R.id.txtDate);
                holder.txtAmount = (TextView)row.findViewById(R.id.txtAmount);
                holder.txtStatus = (TextView)row.findViewById(R.id.txtStatus);
                row.setTag(holder);
            } else {
                holder = (CourseHolder) row.getTag();
            }

            final LoggedInRecord loggedInRecord = getItem(position);
            holder.txtId.setText("#" + loggedInRecord.getOdId());
            holder.txtDate.setText(format.format(loggedInRecord.getOdPayDate()));
            holder.txtAmount.setText("₹ " + loggedInRecord.getOdAmount() + " INR");

            return row;
        }


        class CourseHolder {
            LinearLayout rowLayout;
            TextView txtId;
            TextView txtDate;
            TextView txtAmount;
            TextView txtStatus;
        }
    }
}