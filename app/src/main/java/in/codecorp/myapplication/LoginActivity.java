package in.codecorp.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import in.codecorp.myapplication.Request.Login;
import in.codecorp.myapplication.Response.LoginResponse;
import in.codecorp.myapplication.Utils.DialogUtility;
import in.codecorp.myapplication.Utils.TopRankerPreference;
import in.codecorp.myapplication.rest.ApiClient;
import in.codecorp.myapplication.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText edtEmail;
    private EditText edtPwd;
    private TextView register;
    private Button btnLogin;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtEmail = (EditText) findViewById(R.id.editTextUname);
        edtPwd = (EditText) findViewById(R.id.editTextPass);
        register = (TextView) findViewById(R.id.textView);
        btnLogin = (Button) findViewById(R.id.button);
        sessionManager = new SessionManager(getApplicationContext());
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginAndValidate();
            }
        });
    }

    private void loginAndValidate() {
        edtEmail.setError(null);
        edtPwd.setError(null);

        String email = edtEmail.getText().toString().trim();
        String pwd = edtPwd.getText().toString().trim();

        boolean cancel = false;
        View focusView = null;
        if (TopRankerPreference.isTextEmpty(email)) {
            edtEmail.setError("Field is Mandatory.");
            focusView = edtEmail;
            cancel = true;
        } else if (!TopRankerPreference.isEmailValid(email)) {
            edtEmail.setError("Please enter correct email id.");
            focusView = edtEmail;
            cancel = true;
        }
        if (TopRankerPreference.isTextEmpty(pwd)) {
            edtPwd.setError("Field is Mandatory.");
            focusView = edtPwd;
            cancel = true;
        }
        if (cancel) {
            focusView.requestFocus();
            // Toast.makeText(getApplicationContext(), "Please correct the errors", Toast.LENGTH_SHORT).show();
        } else {
            Login loginRequest = new Login();
            loginRequest.setUsername(email);
            loginRequest.setPassword(pwd);
            submitRequest(loginRequest);
        }
    }

    private void submitRequest(Login loginRequest) {
        DialogUtility.showLoading(this);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        String loginJSON = new Gson().toJson(loginRequest);
        Call<LoginResponse> callClient = apiService.loginUser(loginJSON);
        callClient.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                DialogUtility.hideLoading();
                int statusCode = response.code();
                if (statusCode == 200) {
                    if (response.body() instanceof LoginResponse) {
                        LoginResponse loginResponse = response.body();
                        if (loginResponse.getStatus()) {
                            sessionManager.createLoginSession(loginResponse.getData().getToken());
                            Intent registrationIntent = new Intent(LoginActivity.this, Main2Activity.class);
                            registrationIntent.putExtra("token", loginResponse.getData().getToken());
                            startActivity(registrationIntent);
                            finish();
                            //    TopRankerPreference.getInstance().saveStringData(TopRankerPreference.TOKEN_ID, loginResponse.getData().getToken());
                            //Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                        } else {
                            //Toast.makeText(getApplicationContext(), loginResponse.getValidationErrors(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        //  Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                DialogUtility.hideLoading();
                Toast.makeText(getApplicationContext(), "Fail error" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
