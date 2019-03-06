package in.codecorp.myapplication.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import in.codecorp.myapplication.AnswerSheetActivity;
import in.codecorp.myapplication.QuestionsActivity;
import in.codecorp.myapplication.R;
import in.codecorp.myapplication.Request.FinishTestRequest;
import in.codecorp.myapplication.Response.BaseResponse;
import in.codecorp.myapplication.SessionManager;
import in.codecorp.myapplication.Utils.DialogUtility;
import in.codecorp.myapplication.Utils.QuestionModel;
import in.codecorp.myapplication.Utils.TestData;
import in.codecorp.myapplication.Utils.TestsJoin;
import in.codecorp.myapplication.rest.ApiClient;
import in.codecorp.myapplication.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionFragment extends Fragment {
    SessionManager sessionManager;
    String token;
    private QuestionsActivity questionActivity;
    private QuestionCountAdapter questionCountAdapter;
    private View v;
    private GridView gridView;
    private RelativeLayout layoutMore;
    private RelativeLayout layoutWeb;
    private Button btnClose;
    private Button btnCloseWeb;
    private Button btnAttempt;
    private Button btnNotAttempt;
    private Button btnSelected;
    private Button btnFlag;
    private TextView btnNext;
    private TextView btnMarkReview;
    private TextView btnClear;
    private TextView btnFinish;
    private TextView txtTimer;
    private TextView txtWelcome;
    private TextView btnInstruction;
    private TextView btnQuestions;
    private TextView txtSubject;
    private WebView webQA;
    private WebView webOther;
    private int index = 0;
    private String tabTitle;
    private int questionCountValue = 1;
    private Map<Integer, Integer> questionIndexId = new HashMap<>();
    private Map<String, QuestionModel> questionModelHashMap = new HashMap<>();
    private Map<String, String> answerStringMap = new HashMap<>();
    private Map<String, String> answerTimeMap = new HashMap<>();
    private Map<String, QuestionModel> reviewStringMap = new HashMap<>();
    String link="<link href='https://fonts.googleapis.com/css?family=Average Sans' rel='stylesheet'/>";
    private QuestionModel questionModel;
    private String question = "<p>Q No ";
    private String description;
    private String pleft2 = "</font>";
    private String marks = "<p>(Mark :";
    private String font = "<font size=\"3\" face= \"Average Sans\" color=\"#01203e\">";
    private String addLabelEnd = "</p>";
    private String optionA = "<input type=\"radio\" name=\"OPTION\" value=\"OPTION_A_VALUE\" id=\"OPTION_A_ID\" onclick=\"showAndroidToast('A')\"><label for=\"OPTION_A_ID\">";
    private String optionB = "<input type=\"radio\" name=\"OPTION\" value=\"OPTION_B_VALUE\" id=\"OPTION_B_ID\" onclick=\"showAndroidToast('B')\"><label for=\"OPTION_B_ID\">";
    private String optionC = "<input type=\"radio\" name=\"OPTION\" value=\"OPTION_C_VALUE\" id=\"OPTION_C_ID\" onclick=\"showAndroidToast('C')\"><label for=\"OPTION_C_ID\">";
    private String optionD = "<input type=\"radio\" name=\"OPTION\" value=\"OPTION_D_VALUE\" id=\"OPTION_D_ID\" onclick=\"showAndroidToast('D')\"><label for=\"OPTION_D_ID\">";
    private String optionE = "<input type=\"radio\" name=\"OPTION\" value=\"OPTION_E_VALUE\" id=\"OPTION_E_ID\" onclick=\"showAndroidToast('E')\"><label for=\"OPTION_E_ID\">";
    private String addEnd = "</label></input><br>";
    private String scriptJava = "<script> function showAndroidToast(str) { Android.showToast(str); } </script>";
    private StringBuilder questionsText = new StringBuilder();
    private CountDownTimer cTimer = null;
    private long currentDuration = 0;

    @SuppressLint("JavascriptInterface")
    private void loadQuestion() {
        questionModel = questionModelHashMap.get(questionIndexId.get(index) + "");
        if(questionModel!=null) {
            int questionNo = questionCountValue + index;
            if(questionModel.getQDescription().toString().equals("")){
                description = "";
            }
            else {
                description = "Description:";
            }
            String webData = "<html>" +link+
                    "<style>p{overflow:scroll; display:inline; word-wrap: break-word;}</style><body><b>" + marks
                    + questionModel.getQMarks() + ")" +"   (Negative Marks : " + questionModel.getQMinusMarks() + ")"+ addLabelEnd +
                    "</b><br><br>" +question + questionNo+ ": "+ questionModel.getQTitle()
                    + questionModel.getQDescription() + addLabelEnd+
                    "<br><br>" + optionA + questionModel.getQOpt1() + addEnd + optionB + questionModel.getQOpt2() + addEnd +
                    optionC + questionModel.getQOpt3() + addEnd + optionD + questionModel.getQOpt4() + addEnd + scriptJava
                    +"</body></html>";
         //   btnNext.setText(Html.fromHtml(questionModel.getQTitle()));
            webQA.loadData(webData, "text/html; charset=utf-8", "UTF-8");
            webQA.getSettings().setJavaScriptEnabled(true);
            webQA.setWebViewClient(new WebViewClient());

            questionCountAdapter = new QuestionCountAdapter(getActivity(), R.layout.row_question, questionModelHashMap);
            gridView.setAdapter(questionCountAdapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    index = position;
                    layoutMore.setVisibility(View.GONE);
                    populateData();
                }
            });

            addJavaScript();
            cancelTimer();
            startTimer();
        }
        else
        {
            Toast.makeText(getActivity().getApplicationContext(), "Some thing went wrong", Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionManager = new SessionManager(getActivity());
        HashMap<String, String> user = sessionManager.getUserDetails();
        token = user.get(SessionManager.KEY_TOKEN);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_questions, container, false);
        setHasOptionsMenu(true);
        initialiseView();
        return v;
    }

    private void initialiseView() {
        gridView = (GridView) v.findViewById(R.id.gridview);
        layoutMore = (RelativeLayout) v.findViewById(R.id.layoutMore);
        layoutWeb = (RelativeLayout) v.findViewById(R.id.layoutWeb);
        layoutMore.setVisibility(View.GONE);
        btnClose = (Button) v.findViewById(R.id.btnClose);
        btnCloseWeb = (Button) v.findViewById(R.id.btnCloseWeb);
        btnAttempt = (Button) v.findViewById(R.id.btnAttempt);
        btnNotAttempt = (Button) v.findViewById(R.id.btnNotAttempt);
        btnSelected = (Button) v.findViewById(R.id.btnSelected);
        btnFlag = (Button) v.findViewById(R.id.btnFlag);

        btnNext = (TextView) v.findViewById(R.id.btnNext);
        btnMarkReview = (TextView) v.findViewById(R.id.btnReview);
        btnClear = (TextView) v.findViewById(R.id.btnClear);
        btnFinish = (TextView) v.findViewById(R.id.btnFinish);
        txtTimer = (TextView) v.findViewById(R.id.txtTimer);

        btnInstruction = (TextView) v.findViewById(R.id.btnInstruction);
        btnQuestions = (TextView) v.findViewById(R.id.btnQuestions);
        txtWelcome = (TextView) v.findViewById(R.id.txtWelcome);
        txtWelcome.setText("Welcome : Muskan");
        txtSubject = (TextView) v.findViewById(R.id.txtSubject);

        webQA = (WebView) v.findViewById(R.id.webQA);
        webOther = (WebView) v.findViewById(R.id.webOther);
        initialiseEvent();
        Handler handler = new Handler();
        DialogUtility.showLoading(getActivity());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                populateData();
                DialogUtility.hideLoading();
            }
        }, 1000);
    }

    private void initialiseEvent() {
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutMore.setVisibility(View.GONE);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index++;
                loadQuestion();
                btnAttempt.setText(index + "");
                btnNotAttempt.setText(questionModelHashMap.size() - answerStringMap.size() + "");
                btnSelected.setText(answerStringMap.size() + "");
                btnFlag.setText(reviewStringMap.size() + "");
            }
        });

        btnMarkReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answerStringMap.containsKey(questionModel.getQId() + "")) {
                    answerStringMap.remove(questionModel.getQId() + "");
                }
                reviewStringMap.put(questionModel.getQId() + "", questionModel);
                index++;
                loadQuestion();
                btnAttempt.setText(index + "");
                btnNotAttempt.setText(questionModelHashMap.size() - answerStringMap.size() + "");
                btnSelected.setText(answerStringMap.size() + "");
                btnFlag.setText(reviewStringMap.size() + "");
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answerStringMap.containsKey(questionModel.getQId() + "")) {
                    answerStringMap.remove(questionModel.getQId() + "");
                }
                loadQuestion();
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishTest();
            }
        });

        btnCloseWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutMore.setVisibility(View.GONE);
                layoutWeb.setVisibility(View.GONE);
            }
        });

        btnInstruction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutMore.setVisibility(View.GONE);
                layoutWeb.setVisibility(View.VISIBLE);
                String webData = questionActivity.ins;
                webOther.loadData(webData, "text/html; charset=utf-8", "UTF-8");
            }
        });

        btnQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutMore.setVisibility(View.GONE);
                layoutWeb.setVisibility(View.VISIBLE);
                webOther.loadData(questionsText.toString(), "text/html; charset=utf-8", "UTF-8");
            }
        });
        questionActivity = (QuestionsActivity) getActivity();
        for (int i = 0; i < questionActivity.questionIndex; i++) {
            questionCountValue += questionActivity.questionCounts[i];
        }
    }

    private void finishTest() {
        DialogUtility.showLoading(getActivity());
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        FinishTestRequest finishTestRequest = new FinishTestRequest();
        finishTestRequest.setToken(token);
        finishTestRequest.settId(questionActivity.tid);
        finishTestRequest.setMtId(questionActivity.mtid);
        Map<String, String> timeMap = new HashMap<>();
        Iterator it = questionActivity.answerTimeMaps.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            timeMap.putAll((Map<String, String>) pair.getValue());
            it.remove();
        }
        Map<String, String> answerMap = new HashMap<>();
        Iterator it1 = questionActivity.answerStringMaps.entrySet().iterator();
        while (it1.hasNext()) {
            Map.Entry pair = (Map.Entry) it1.next();
            answerMap.putAll((Map<String, String>) pair.getValue());
            it1.remove();
        }
        finishTestRequest.setqTime(timeMap);
        finishTestRequest.setqTime(answerMap);
        String loginJSON = new Gson().toJson(finishTestRequest);
        Call<BaseResponse> callClient = apiService.evaluate(loginJSON);
        callClient.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                DialogUtility.hideLoading();
                int statusCode = response.code();
                if (statusCode == 200) {
                    if (response.body() instanceof BaseResponse) {
                        Toast.makeText(getActivity().getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), AnswerSheetActivity.class);
                        intent.putExtra(AnswerSheetActivity.MID_ID,questionActivity.mtid);
                        startActivity(intent);
                        getActivity().finish();
                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                DialogUtility.hideLoading();
                Toast.makeText(getActivity().getApplicationContext(), "Fail error" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_more, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_more:
                layoutMore.setVisibility(View.VISIBLE);
                break;

        }
        return true;
    }

    private void populateData() {
        tabTitle = questionActivity.title;
        txtSubject.setText(tabTitle);
        layoutWeb.setVisibility(View.GONE);
        filterData(questionActivity.testResponse.getData());
        loadQuestion();
    }


    @SuppressLint("JavascriptInterface")
    private void addJavaScript() {
        webQA.addJavascriptInterface(new WebAppInterface(getContext()), "Android");
        webQA.setWebChromeClient(new WebChromeClient());
    }

    private void filterData(TestData data) {
        int i = 0;
        for (QuestionModel questionModel : data.getQuestionModels()) {
            List<TestsJoin> testsJoins = questionModel.getTestsJoin();
            if (testsJoins.get(0).getSubject().getSubTitle().equalsIgnoreCase(questionActivity.title)) {
                questionIndexId.put(i,questionModel.getQId());
                questionModelHashMap.put(questionModel.getQId() + "", questionModel);
                questionsText.append(questionModel.getQTitle());
                questionsText.append(questionModel.getQDescription() + "<br>");
                i++;
            }
        }
        questionActivity.questionCounts[questionActivity.questionIndex] = i;
    }

    @Override
    public void onStop() {
        super.onStop();
        saveAnswer();
        cancelTimer();
    }

    private void saveAnswer() {
        if (questionActivity.questionModelHashMaps.containsKey(tabTitle)) {
            questionActivity.questionModelHashMaps.remove(tabTitle);
        }
        if (questionActivity.answerStringMaps.containsKey(tabTitle)) {
            questionActivity.answerStringMaps.remove(tabTitle);
            questionActivity.answerTimeMaps.remove(tabTitle);
        }
        questionActivity.questionModelHashMaps.put(tabTitle, questionModelHashMap);
        questionActivity.answerStringMaps.put(tabTitle, answerStringMap);
        questionActivity.answerTimeMaps.put(tabTitle, answerTimeMap);
    }

    void startTimer() {
        final long time = ((Integer.parseInt(questionActivity.dur )/ questionCountAdapter.getCount()) * 60 * 1000);
        if(time>0) {
            cTimer = new CountDownTimer(time, 1000) {
                public void onTick(long millisUntilFinished) {
                    String text = String.format(Locale.getDefault(), "%02d: %02d",
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60,
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60);
                    txtTimer.setText(text);
                    currentDuration = time - millisUntilFinished;
                }

                public void onFinish() {
                    index++;
                    loadQuestion();
                }
            };
            cTimer.start();
        }
        else
        {
            txtTimer.setVisibility(View.GONE);
        }
    }

    void cancelTimer() {
        if (cTimer != null) {
            cTimer.cancel();
            cTimer = null;
        }
    }

    public class WebAppInterface {
        Context mContext;

        WebAppInterface(Context c) {
            mContext = c;
        }

        @JavascriptInterface
        public void showToast(String toast) {
            if (answerStringMap.containsKey(questionModel.getQId() + "")) {
                answerStringMap.remove(questionModel.getQId() + "");
                answerTimeMap.remove(questionModel.getQId() + "");
            }
            answerStringMap.put(questionModel.getQId() + "", toast);
            answerTimeMap.put(questionModel.getQId() + "", currentDuration + "");
            saveAnswer();
            questionCountAdapter.notifyDataSetChanged();
        }
    }

    public class QuestionCountAdapter extends ArrayAdapter<Map<String, QuestionModel>> {
        private Map<String, QuestionModel> questionModelMap;

        public QuestionCountAdapter(Context context, int layoutResourceId, Map<String, QuestionModel> data) {
            super(context, layoutResourceId);
            this.questionModelMap = data;
        }

        @Override
        public int getCount() {
            return questionModelMap.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            QuestionCountAdapterHolder holder = null;
            if (row == null) {
                LayoutInflater inflater = getActivity().getLayoutInflater();
                row = inflater.inflate(R.layout.row_question, parent, false);
                holder = new QuestionCountAdapterHolder();
                holder.btn = (TextView)row.findViewById(R.id.btn);
                row.setTag(holder);
            } else {
                holder = (QuestionCountAdapterHolder) row.getTag();
            }
            final QuestionModel questionModel = questionModelMap.get(questionIndexId.get(position) + "");
            if (answerStringMap.containsKey(questionModel.getQId() + "")) {
                holder.btn.setBackgroundColor(getResources().getColor(R.color.colorGreen));
            } else if (reviewStringMap.containsKey(questionModel.getQId() + "")) {
                holder.btn.setBackgroundColor(getResources().getColor(R.color.colorOrange));
            } else {
                holder.btn.setBackgroundColor(getResources().getColor(R.color.mdtp_white));
            }
            holder.btn.setText((questionCountValue + position) + "");
            return row;
        }


        class QuestionCountAdapterHolder {
            TextView btn;
        }
    }
}