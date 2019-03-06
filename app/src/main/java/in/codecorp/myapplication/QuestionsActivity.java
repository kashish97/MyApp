package in.codecorp.myapplication;

import android.content.Intent;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.codecorp.myapplication.Fragments.QuestionFragment;
import in.codecorp.myapplication.Response.TestResponse;
import in.codecorp.myapplication.Response.TestSubjectResponse;
import in.codecorp.myapplication.Utils.DataContainer;
import in.codecorp.myapplication.Utils.QuestionModel;
import in.codecorp.myapplication.Utils.TestSubjects;
import in.codecorp.myapplication.Utils.TopRankerPreference;
import in.codecorp.myapplication.Utils.mTest;
import in.codecorp.myapplication.rest.ApiClient;
import in.codecorp.myapplication.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionsActivity extends AppCompatActivity {
    public TestResponse testResponse;
    SessionManager sessionManager;
    public String title;
  public   mTest testLevel;
    public int[] questionCounts;
    public int questionIndex = 1;
    public Map<String, Map<String, QuestionModel>> questionModelHashMaps = new HashMap<>();
    public Map<String, Map<String, String>> answerStringMaps = new HashMap<>();
    public Map<String, Map<String, String>> answerTimeMaps = new HashMap<>();
    private FragmentTabHost mTabHost;
    private List<TestSubjects> testSubjectses;
  public   String token, tid, mtid, ins, dur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_questions);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        testLevel = DataContainer.getTestLevel();
        Intent rcv = getIntent();

        tid = rcv.getStringExtra("tid");
        mtid = rcv.getStringExtra("mtid");
        ins = rcv.getStringExtra("ins");
        dur = rcv.getStringExtra("dur");

        sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> user = sessionManager.getUserDetails();
        token = user.get(SessionManager.KEY_TOKEN);

        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                title = tabId;
                questionIndex = mTabHost.getCurrentTab();
            }
        });

        super.onCreate(savedInstanceState);
    }



    @Override
    public void onResume() {
        super.onResume();
        getSubjectLines();
    }

    private void getSubjectLines() {
     //   DialogUtitlity.showLoading(QuestionActivity.this);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<TestSubjectResponse> callClient = apiService.getSubjects(token, tid);
        callClient.enqueue(new Callback<TestSubjectResponse>() {
            @Override
            public void onResponse(Call<TestSubjectResponse> call, Response<TestSubjectResponse> response) {
                int statusCode = response.code();
                if (statusCode == 200) {
                    if (response.body() instanceof TestSubjectResponse) {
                        TestSubjectResponse testSubjectResponse = response.body();
                        testSubjectses = testSubjectResponse.getRecords();
                        getTest();
                       // Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                    } else {
                      //  Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    //Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TestSubjectResponse> call, Throwable t) {
               // Toast.makeText(getApplicationContext(), "Fail error" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getTest() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<TestResponse> callClient = apiService.getTest(token, mtid);
        callClient.enqueue(new Callback<TestResponse>() {
            @Override
            public void onResponse(Call<TestResponse> call, Response<TestResponse> response) {
               // DialogUtitlity.hideLoading();
                int statusCode = response.code();
                if (statusCode == 200) {
                    if (response.body() instanceof TestResponse) {
                        testResponse = response.body();
                        System.out.print("\n");
                        System.out.println(testResponse.getData().getQuestionModels());
                     //   Toast.makeText(getApplicationContext(),response.body().getData().getQuestionCount()+ " Questions Found" ,Toast.LENGTH_LONG).show();
                        populateTabHost();
                  //      Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Please contact Support", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please contact Support", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TestResponse> call, Throwable t) {
                Intent intent = new Intent(QuestionsActivity.this, AnswerSheetActivity.class);
                intent.putExtra(AnswerSheetActivity.MID_ID,mtid);
                startActivity(intent);
                finish();
             //   Toast.makeText(getApplicationContext(), "Test Finished" , Toast.LENGTH_SHORT).show();
                //finish();
            }
        });
    }

    private void populateTabHost() {
        if(testSubjectses.size()>0) {

            questionCounts = new int[testSubjectses.size()];
            for (TestSubjects testSubjects : testSubjectses) {
                mTabHost.addTab(
                        mTabHost.newTabSpec(testSubjects.getSubTitle()).setIndicator(testSubjects.getSubTitle()),
                        QuestionFragment.class, null);
            }
        }
        else
        {

            questionCounts = new int[1];
            if(testResponse!=null && testResponse.getData()!=null && testResponse.getData().getQuestionModels()!=null && !testResponse.getData().getQuestionModels().isEmpty() && testResponse.getData().getQuestionModels().get(0).getTestsJoin()!=null &&!testResponse.getData().getQuestionModels().get(0).getTestsJoin().isEmpty()&&testResponse.getData().getQuestionModels().get(0).getTestsJoin().get(0).getSubject()!=null)
            {
                String title = testResponse.getData().getQuestionModels().get(0).getTestsJoin().get(0).getSubject().getSubTitle();
                mTabHost.addTab(
                        mTabHost.newTabSpec(title).setIndicator(title),
                        QuestionFragment.class, null);
            }
            else
            {
                Toast.makeText(getApplicationContext(), "No Questions found Please contact Support", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
