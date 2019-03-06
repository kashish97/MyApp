package in.codecorp.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;



;import java.util.HashMap;

import in.codecorp.myapplication.Response.AnswerSheetResponse;

public class PaidCourseActivity extends AppCompatActivity {
    public static String MID_ID = "MID_ID";
    private WebView webView;
    private String m_id;
    private AnswerSheetResponse answerSheetResponse;
    SessionManager sessionManager;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_answer_sheet);
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> user = sessionManager.getUserDetails();
        token = user.get(SessionManager.KEY_TOKEN);
        initialiseView();
    }


    public void initialiseView() {
        webView = (WebView) findViewById(R.id.webDescription);
        getSupportActionBar().setTitle("Buy Package");
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            m_id = extras.getString(MID_ID);
            populateData();
        } else {
            Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void populateData() {
        webView.getSettings().setJavaScriptEnabled(true);
        System.out.println(token);
        webView.loadUrl("http://demos.abhinavsoftware.com/gc/fast-checkout/index?pkg_id="+m_id+"&token="+token);
        webView.setWebViewClient(new LoadingWebClient());
    }

    private class LoadingWebClient extends WebViewClient
    {
           // Override page so it's load on my view only
            public LoadingWebClient() {
              //  DialogUtitlity.showLoading(PaidCourseActivity.this);
            }


            @Override
            public void onPageFinished(WebView view, String url) {
                // TODO Auto-generated method stub
                super.onPageFinished(view, url);
               // DialogUtitlity.hideLoading();
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                // This line we let me load only pages with an anchor tag
                if ( url.contains("url") == true )
                    //Load new URL Don't override URL Link
                    return false;

                // Return true to override url loading (In this case do nothing).
                return false;
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
