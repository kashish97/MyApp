package in.codecorp.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import in.codecorp.myapplication.Request.Register;
import in.codecorp.myapplication.Response.RegisterResponse;
import in.codecorp.myapplication.Utils.DialogUtility;
import in.codecorp.myapplication.Utils.TopRankerPreference;
import in.codecorp.myapplication.rest.ApiClient;
import in.codecorp.myapplication.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class  RegisterActivity extends AppCompatActivity  {

    private EditText edtEmail;
    private EditText edtMobileNumber;
    private EditText edtPwd;
   // private EditText edtConfirmPwd;
    private Button btnRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtMobileNumber = (EditText) findViewById(R.id.edtMobile);
        edtPwd = (EditText) findViewById(R.id.edtPassword);
        //edtConfirmPwd = (EditText) findViewById(R.id.edtConfirmPassword);

        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerAndValidate();
            }
        });
    }

    private void registerAndValidate() {
        edtEmail.setError(null);
        edtMobileNumber.setError(null);
        edtPwd.setError(null);
      //  edtConfirmPwd.setError(null);



        String email = edtEmail.getText().toString().trim();
        String mobile = edtMobileNumber.getText().toString().trim();
        String pwd = edtPwd.getText().toString().trim();
     //   String confirmPwd = edtConfirmPwd.getText().toString().trim();


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
        if (TopRankerPreference.isTextEmpty(mobile)) {
            edtMobileNumber.setError("Field is Mandatory.");
            focusView = edtMobileNumber;
            cancel = true;
        } else if (mobile.length() < 10) {
            edtMobileNumber.setError("Please enter 10 digit mobile number.");
            focusView = edtMobileNumber;
            cancel = true;
        }
        if (TopRankerPreference.isTextEmpty(pwd)) {
            edtPwd.setError("Field is Mandatory.");
            focusView = edtPwd;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
            Toast.makeText(getApplicationContext(), "Please correct the errors", Toast.LENGTH_SHORT).show();
        } else {
            Register registrationRequest = new Register();
            registrationRequest.setEmail(email);
            registrationRequest.setMobile(mobile);
            registrationRequest.setPassword(pwd);
            registrationRequest.setPassword2(pwd);


            submitRequest(registrationRequest);
        }
    }

    private void submitRequest(Register registrationRequest) {
        DialogUtility.showLoading(this);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        String registerJSON = new Gson().toJson(registrationRequest);
        Call<RegisterResponse> callClient = apiService.registerUser(registerJSON);
        callClient.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                DialogUtility.hideLoading();
                int statusCode = response.code();
                if (statusCode == 200) {
                    if (response.body() instanceof RegisterResponse) {
                        RegisterResponse registerResponse = response.body();
                        if (registerResponse.getStatus()) {
                            Intent registrationIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(registrationIntent);
                            finish();              //              Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), registerResponse.getValidationErrors(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                DialogUtility.hideLoading();
                Toast.makeText(getApplicationContext(), "Fail error" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}

