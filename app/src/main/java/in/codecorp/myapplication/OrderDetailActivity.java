package in.codecorp.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.TimeZone;

import in.codecorp.myapplication.Response.LoggedInOrderDetailResponse;
import in.codecorp.myapplication.Utils.LoggedInRecord;
import in.codecorp.myapplication.Utils.OrderDetail;
import in.codecorp.myapplication.Utils.TopRankerPreference;
import in.codecorp.myapplication.rest.ApiClient;
import in.codecorp.myapplication.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

;

public class OrderDetailActivity extends AppCompatActivity {
    public static String ORDER_ID = "ORDER_ID";
    private TextView txtOrderId;
    private TextView txtDate;
    private TextView txtStatus;
    private TextView txtId;
    private TextView txtDetail;
    private TextView txtAmount;
    TextView detail;
  //  private WebView webView;
    private int order_id;
    private LoggedInOrderDetailResponse loggedInOrderDetailResponse;
    SessionManager sessionManager;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_order_detail);
        getSupportActionBar().setTitle("Order Detail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initialiseView();
        super.onCreate(savedInstanceState);
    }

    public void initialiseView() {
        txtOrderId = (TextView) findViewById(R.id.txtOrderId);
        txtDate = (TextView) findViewById(R.id.txtDate);
        txtStatus = (TextView) findViewById(R.id.txtStatus);
        txtId = (TextView) findViewById(R.id.txtId);
        txtDetail = (TextView) findViewById(R.id.txtDetail);
        txtAmount = (TextView) findViewById(R.id.txtAmount);
        detail =(TextView) findViewById(R.id.textView3);
      //  webView = (WebView) findViewById(R.id.webDescription);
        sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> user = sessionManager.getUserDetails();
        token = user.get(SessionManager.KEY_TOKEN);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            order_id = extras.getInt(ORDER_ID);
            getOrderDetail();
        } else {
            Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void getOrderDetail() {
       // DialogUtitlity.showLoading(this);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<LoggedInOrderDetailResponse> callClient = apiService.orderDetail(token, order_id + "");
        callClient.enqueue(new Callback<LoggedInOrderDetailResponse>() {
            @Override
            public void onResponse(Call<LoggedInOrderDetailResponse> call, Response<LoggedInOrderDetailResponse> response) {
               // DialogUtitlity.hideLoading();
                int statusCode = response.code();
                if (statusCode == 200) {
                    if (response.body() instanceof LoggedInOrderDetailResponse) {
                        loggedInOrderDetailResponse = response.body();
                        populateData();
                    } else {
                        Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoggedInOrderDetailResponse> call, Throwable t) {
                //DialogUtitlity.hideLoading();
                Toast.makeText(getApplicationContext(), "Fail error" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateData() {
        if (loggedInOrderDetailResponse != null) {
            LoggedInRecord loggedInRecord = loggedInOrderDetailResponse.getData();
            if (loggedInRecord != null) {
                OrderDetail orderDetail = loggedInRecord.getOrderPackages().get(0);
                if (orderDetail != null) {
                    SimpleDateFormat format = new SimpleDateFormat("MMMM dd,yyyy hh:mm a");
                    format.setTimeZone(TimeZone.getTimeZone("IST"));
                    txtOrderId.setText("#"+loggedInRecord.getOdId());
                    txtDate.setText(format.format(loggedInRecord.getOdPayDate()));
                    txtStatus.setText("Paid On " + format.format(loggedInRecord.getOdPayDate()));
                    txtId.setText("#"+orderDetail.getOpkgId());
                    txtDetail.setText(orderDetail.getOpkgTitle());
                    txtAmount.setText(loggedInRecord.getOdAmount());
                    detail.setText(Html.fromHtml(orderDetail.getOpkgDesc()));
                   // webView.loadData(orderDetail.getOpkgDesc(), "text/html; charset=utf-8", "UTF-8");
                }
            }
        }
    }

    public void showNextScreen() {
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
