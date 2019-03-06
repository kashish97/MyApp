package in.codecorp.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


import java.util.HashMap;

import in.codecorp.myapplication.Response.AnswerSheetResponse;

;

public class AnswerSheetActivity extends AppCompatActivity {
    public static String MID_ID = "MID_ID";
    private WebView webView;
    private String m_id;
    SessionManager sessionManager;
    String token;
    private AnswerSheetResponse answerSheetResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_answer_sheet);
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> user = sessionManager.getUserDetails();
        token = user.get(SessionManager.KEY_TOKEN);
        webView = (WebView) findViewById(R.id.webDescription);
        webView.getSettings().setUseWideViewPort(false);
        webView.setHorizontalScrollBarEnabled(false);
        getSupportActionBar().setTitle("Answer Sheet");
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            m_id = extras.getString(MID_ID);
            populateData();
        } else {
       //     Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
            finish();
        }
    }


    private void populateData() {
        StringBuilder webData = prepareData();
        webView.loadUrl(webData.toString());
        webView.setWebViewClient(new LoadingWebClient());
    }

    private class LoadingWebClient extends WebViewClient
    {
        // Override page so it's load on my view only


        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);

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

    private StringBuilder prepareData() {
        StringBuilder html1 = new StringBuilder("http://demos.abhinavsoftware.com/gc/user/answer-sheet/?id=");
        html1.append(m_id);
        html1.append("&token=");
        html1.append(token);
        return html1;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
