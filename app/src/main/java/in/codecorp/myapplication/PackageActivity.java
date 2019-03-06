package in.codecorp.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import in.codecorp.myapplication.Adapter.AlltestAdapter;
import in.codecorp.myapplication.Response.BaseResponse;
import in.codecorp.myapplication.Utils.Package;
import in.codecorp.myapplication.Utils.Test;
import in.codecorp.myapplication.rest.ApiClient;
import in.codecorp.myapplication.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PackageActivity extends AppCompatActivity {

    int resource;
    int a;
    LinearLayout ll1;
    List<String> keys;
    List<Package> cartList1, cartList2;
    SessionManager sessionManager;
    String token;
    String pkgid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        pkgid = getIntent().getStringExtra("pkgid");
        sessionManager = new SessionManager(PackageActivity.this);
        HashMap<String, String> user = sessionManager.getUserDetails();
        token = user.get(SessionManager.KEY_TOKEN);
        Intent rcv = getIntent();
        cartList1 = new ArrayList<>();
        cartList1.add(new Package(rcv.getStringExtra("id"),rcv.getStringExtra("title"), rcv.getStringExtra("desc"),
                rcv.getStringExtra("sdesc"), rcv.getStringExtra("photo"), rcv.getStringExtra("price"), rcv.getStringExtra("days"),
                rcv.getStringExtra("status"), rcv.getStringExtra("order"), rcv.getStringExtra("c_id")));
        TextView txtName = (TextView) findViewById(R.id.textViewName);
        TextView txtPrice = (TextView) findViewById(R.id.textViewPrice);
        TextView tp1 = (TextView) findViewById(R.id.tp1);
        TextView tp2 = (TextView) findViewById(R.id.tp2);
      //  TextView sdesc = (TextView) findViewById(R.id.textViewSdesc);
        //ll1 = (LinearLayout) findViewById(R.id.ll1);



      //  ExpandableTextView expTv1 = (ExpandableTextView) findViewById(R.id.expand_text_view);
        //  TextView txtDesc = (TextView) view.findViewById(R.id.textViewDesc);
        ImageView imageZoom = findViewById(R.id.img);
        int position = 0;
        final Package cart = cartList1.get(position);
        txtName.setText(cart.getPkg_title());
//        if(cart.getPkg_sdesc().equals("")){
  //          ll1.setVisibility(View.INVISIBLE);
    //    }
        if(cart.getPkg_price().equals("0")){
            tp1.setText("");
            tp2.setText("");
            txtPrice.setText("Free Test");
        }
        else{
            txtPrice.setText(cart.getPkg_price());

        }
        txtPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(token!=null){
                    if(cart.getPkg_price().equals("0")){
                        addCourse(cart.getPcat_id());
                    }
                    else {
                        Intent intent = new Intent(PackageActivity.this, PaidCourseActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("MID_ID", cart.getPkg_id());
                        startActivity(intent);
                    }
                }
                else {
                    Intent intent = new Intent(PackageActivity.this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    // intent.putExtra("MID_ID", cart.getPcat_id());
                    startActivity(intent);
                }

            }
        });


      //  expTv1.setText(Html.fromHtml(cart.getPkg_desc()));
       // sdesc.setText(Html.fromHtml(cart.getPkg_sdesc()));
        String url = "http://ssgcp.in/uploads/"+ cart.getPkg_photo();
        Glide.with(PackageActivity.this)
                .load(url)
                .into(imageZoom);
    }

    private void addCourse(String id) {
        // DialogUtitlity.showLoading(this);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        //{"token":"5a43aff5ab88a","id":38}
        String json = "{\"token\":\""+token+"\",\"id\":"+id+"}";

        Call<BaseResponse> callClient = apiService.addFreeCourse(json);
        callClient.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                // DialogUtitlity.hideLoading();
                int statusCode = response.code();
                BaseResponse baseResponse = (BaseResponse)response.body();
                if (statusCode == 200 && baseResponse.getStatus()) {
                 //   Toast.makeText(PackageActivity.this, "Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PackageActivity.this, "Test not Found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Toast.makeText(PackageActivity.this, "Fail error" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
