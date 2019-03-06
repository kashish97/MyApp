package in.codecorp.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.vatsal.imagezoomer.ImageZoomButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import in.codecorp.myapplication.LoginActivity;
import in.codecorp.myapplication.PaidCourseActivity;
import in.codecorp.myapplication.R;
import in.codecorp.myapplication.Response.BaseResponse;
import in.codecorp.myapplication.SessionManager;
import in.codecorp.myapplication.Utils.Package;
import in.codecorp.myapplication.Utils.TopRankerPreference;
import in.codecorp.myapplication.rest.ApiClient;
import in.codecorp.myapplication.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PackageAdapter2 extends ArrayAdapter<Package> {
    Context context;
    int resource;
    int a;
    LinearLayout ll1;
    List<String> keys;
    List<Package> cartList1, cartList2;
    SessionManager sessionManager;
    String token;


    public PackageAdapter2(Context context, int resource, List<Package> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        cartList1 = objects;
        cartList2 = new ArrayList<>();
        cartList2.addAll(cartList1);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;
        view = LayoutInflater.from(context).inflate(resource, parent, false);
        keys = new ArrayList<>();
        sessionManager = new SessionManager(context);
        HashMap<String, String> user = sessionManager.getUserDetails();
         token = user.get(SessionManager.KEY_TOKEN);


        TextView txtName = (TextView) view.findViewById(R.id.textViewName);
        TextView txtPrice = (TextView) view.findViewById(R.id.textViewPrice);
        TextView tp1 = (TextView) view.findViewById(R.id.tp1);
        TextView tp2 = (TextView) view.findViewById(R.id.tp2);
        TextView sdesc = (TextView) view.findViewById(R.id.textViewSdesc);
        // ll1 = (LinearLayout) view.findViewById(R.id.ll1);



    //    ExpandableTextView expTv1 = (ExpandableTextView) view.findViewById(R.id.expand_text_view);
        //  TextView txtDesc = (TextView) view.findViewById(R.id.textViewDesc);
         ImageView imageZoom = view.findViewById(R.id.img);


        final Package cart = cartList1.get(position);
        txtName.setText(cart.getPkg_title());
        if(cart.getPkg_sdesc().equals("")){
//            ll1.setVisibility(View.INVISIBLE);
        }
        if(cart.getPkg_price().equals("0")){
            tp1.setText("");
            tp2.setText("");
            txtPrice.setText("Free Test");


        }
        else{
            txtPrice.setText(cart.getPkg_price());

        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(token!=null){
                    if(cart.getPkg_price().equals("0")){
                        addCourse(cart.getPcat_id());
                    }
                    else {
                        Intent intent = new Intent(context, PaidCourseActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("MID_ID", cart.getPkg_id());
                        context.startActivity(intent);
                    }
                }
                else {
                    Intent intent = new Intent(context, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                   // intent.putExtra("MID_ID", cart.getPcat_id());
                    context.startActivity(intent);
                }

            }
        });


        //expTv1.setText(Html.fromHtml(cart.getPkg_desc()));
//        sdesc.setText(Html.fromHtml(cart.getPkg_sdesc()));
        String url = "http://ssgcp.in/uploads/"+ cart.getPkg_photo();
        Glide.with(context)
               .load(url)
                .into(imageZoom);

        return view;
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
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Test already added to dashboard", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Toast.makeText(context, "Fail error" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}

