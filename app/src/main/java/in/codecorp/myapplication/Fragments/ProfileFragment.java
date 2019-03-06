package in.codecorp.myapplication.Fragments;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import in.codecorp.myapplication.LoginActivity;
import in.codecorp.myapplication.R;
import in.codecorp.myapplication.Request.UpdateProfileRequest;
import in.codecorp.myapplication.Response.BaseResponse;
import in.codecorp.myapplication.Response.ProfileResponse;
import in.codecorp.myapplication.SessionManager;
import in.codecorp.myapplication.SubscribedTestActivity;
import in.codecorp.myapplication.Utils.DataContainer;
import in.codecorp.myapplication.Utils.Profile;
import in.codecorp.myapplication.Utils.TopRankerPreference;
import in.codecorp.myapplication.Utils.UserProfile;
import in.codecorp.myapplication.rest.ApiClient;
import in.codecorp.myapplication.rest.ApiInterface;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

import static android.app.Activity.RESULT_OK;


public class ProfileFragment extends Fragment {


    private  static final String EXTRA_TEXT="text";
    private EditText edtFullName;
    private EditText edtQualification;
    private EditText edtCollegeName;
    private EditText edtFatherName;
    private EditText edtCollegeCity;
    private EditText edtPassingYear;
    private EditText edtAddress;
    private EditText edtPercent;
    private EditText edtPin;
    private EditText edtMobile;
    private EditText edtCity;

    private Button btnDOB;
    private Button btnGender;
    private Button btnState;
    private Button btnState1;
    private Button btnRegister;
    private Button btnPhoto;

    private ProfileResponse profileResponse;
    private File photoPic;
    SessionManager sessionManager;
    String token;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment createFor(String text) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(EXTRA_TEXT, text);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        sessionManager = new SessionManager(getActivity());
        HashMap<String, String> user = sessionManager.getUserDetails();
        token = user.get(SessionManager.KEY_TOKEN);
        System.out.println("Token "+ token);
        if (token!=null){
            getProfile();

        }
        else {
            Intent i = new Intent(getActivity(), LoginActivity.class);
            startActivity(i);
        }
        edtFullName = (EditText) v.findViewById(R.id.edtFullName);
        edtQualification = (EditText) v.findViewById(R.id.edtQualification);
        edtCollegeName = (EditText) v.findViewById(R.id.edtCollegeName);
        edtFatherName = (EditText) v.findViewById(R.id.edtFatherName);
        edtCollegeCity = (EditText) v.findViewById(R.id.edtCollegeCity);
        edtPassingYear = (EditText) v.findViewById(R.id.edtPassingYear);
        edtAddress = (EditText) v.findViewById(R.id.edtAddress);
        edtPercent = (EditText) v.findViewById(R.id.edtPercent);
        edtPin = (EditText) v.findViewById(R.id.edtPin);
        edtMobile = (EditText) v.findViewById(R.id.edtMobile);
        edtCity = (EditText) v.findViewById(R.id.edtCity);

        btnDOB = (Button) v.findViewById(R.id.btnDOB);
        btnGender = (Button) v.findViewById(R.id.btnGender);
        btnState = (Button) v.findViewById(R.id.btnState);
        btnState1 = (Button) v.findViewById(R.id.btnState1);
        btnPhoto = (Button) v.findViewById(R.id.btnPhoto);

        btnRegister = (Button) v.findViewById(R.id.btnRegister);

        btnDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
                        String date = simpleDateFormat.format(newDate.getTime());
                        btnDOB.setText(date);
                    }

                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        btnGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] gender = getResources().getStringArray(R.array.gender);
                PopupMenu menu = new PopupMenu(getActivity(), btnGender);
                for (int i = 0; i < gender.length; i++) {
                    menu.getMenu().add(0, i, 0, gender[i]);
                }
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        btnGender.setText(item.getTitle());
                        btnGender.setTag(R.string.app_name, item.getItemId());
                        return true;
                    }
                });
                menu.show();
            }
        });

        btnState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] state = getResources().getStringArray(R.array.state);
                PopupMenu menu = new PopupMenu(getActivity(), btnState);
                for (int i = 0; i < state.length; i++) {
                    menu.getMenu().add(0, i, 0, state[i]);
                }
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        btnState.setText(item.getTitle());
                        btnState.setTag(R.string.app_name, item.getItemId());
                        return true;
                    }
                });
                menu.show();
            }
        });


       btnState1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String[] state = getResources().getStringArray(R.array.state);
               PopupMenu menu = new PopupMenu(getActivity(), btnState1);
               for (int i = 0; i < state.length; i++) {
                   menu.getMenu().add(0, i, 0, state[i]);
               }
               menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                   @Override
                   public boolean onMenuItemClick(MenuItem item) {
                       btnState1.setText(item.getTitle());
                       btnState1.setTag(R.string.app_name, item.getItemId());
                       return true;
                   }
               });
               menu.show();
           }
       });
       btnRegister.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               registerAndValidate();
           }
       });

       btnPhoto.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
               photoPickerIntent.setType("image/*");
               startActivityForResult(photoPickerIntent, 5000);
           }
       });


        return v;
    }

    private void getProfile() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ProfileResponse> callClient = apiService.getProfile(token);
        callClient.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, retrofit2.Response<ProfileResponse> response) {

                int statusCode = response.code();
                if (statusCode == 200) {
                    if (response.body() instanceof ProfileResponse) {
                        profileResponse = response.body();
                        DataContainer.setProfileResponse(profileResponse);
                        getImage();
                        populateData();
                    } else {
                        Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {

                Toast.makeText(getActivity(), "Fail error" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getImage() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBody> callClient = apiService.getImage("http://ssgcp.in/uploads/" + profileResponse.getData().getUpPhoto());
        callClient.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                int statusCode = response.code();
                if (statusCode == 200) {
                    if (response.body() instanceof ResponseBody) {
                        ResponseBody responseBody = (ResponseBody) response.body();
                        byte[] bytes = new byte[1];
                        try {
                            bytes = responseBody.bytes();
                        } catch (IOException e) {
                        }
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        Drawable d = new BitmapDrawable(getResources(), bitmap);
                        btnPhoto.setBackgroundDrawable(d);
                    } else {
                        Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getActivity(), "Fail error" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void populateData() {
        Profile profile = profileResponse.getData();
        edtFullName.setText(profile.getUpFullname());
        edtQualification.setText(profile.getUpQualification());
        edtCollegeName.setText(profile.getUpCollegeName());
        edtFatherName.setText(profile.getUpFname());
        edtCollegeCity.setText(profile.getUpCollegeCity());
        edtPassingYear.setText(profile.getUpPassingYear());
        edtAddress.setText(profile.getUpAddress());
        edtPercent.setText(profile.getUpPercent());
        edtPin.setText(profile.getUpPin());
        edtMobile.setText(profile.getUpMobile());
        edtCity.setText(profile.getUpCity());
        edtCollegeName.setText(profile.getUpCollegeName());
        btnDOB.setText(profile.getUpDob());
        btnGender.setText(profile.getUpGender());
        btnState.setText(profile.getUpState());
        btnState1.setText(profile.getUpCollegeState());

    }

    private void registerAndValidate() {



        String fullName = edtFullName.getText().toString().trim();
        String qualification = edtQualification.getText().toString().trim();
        String collageName = edtCollegeName.getText().toString().trim();
        String fatherName = edtFatherName.getText().toString().trim();
        String collegeCity = edtCollegeCity.getText().toString().trim();
        String passingYear = edtPassingYear.getText().toString().trim();
        String address = edtAddress.getText().toString().trim();
        String percent = edtPercent.getText().toString().trim();
        String mobile = edtMobile.getText().toString().trim();
        String pin = edtPin.getText().toString().trim();
        String city = edtCity.getText().toString().trim();


        String gender = btnGender.getTag(R.string.app_name) == null ? null : btnGender.getTag(R.string.app_name).toString();
        String state = btnState.getTag(R.string.app_name) == null ? null : btnState.getTag(R.string.app_name).toString();
        String state1 = btnState1.getTag(R.string.app_name) == null ? null : btnState1.getTag(R.string.app_name).toString();
        String dob = btnDOB.getText().toString();

        boolean cancel = false;
        View focusView = null;
        if (TopRankerPreference.isTextEmpty(fullName)) {
            edtFullName.setError("Field is Mandatory.");
            focusView = edtFullName;
            cancel = true;
        }
        if (TopRankerPreference.isTextEmpty(qualification)) {
            edtQualification.setError("Field is Mandatory.");
            focusView = edtQualification;
            cancel = true;
        } else if (TopRankerPreference.isTextEmpty(collageName)) {
            edtCollegeName.setError("Field is Mandatory.");
            focusView = edtCollegeName;
            cancel = true;
        }
        if (TopRankerPreference.isTextEmpty(mobile)) {
            edtMobile.setError("Field is Mandatory.");
            focusView = edtMobile;
            cancel = true;
        } else if (mobile.length() < 10) {
            edtMobile.setError("Please enter 10 digit mobile number.");
            focusView = edtMobile;
            cancel = true;
        }
        if (TopRankerPreference.isTextEmpty(collegeCity)) {
            edtCollegeCity.setError("Field is Mandatory.");
            focusView = edtCollegeCity;
            cancel = true;
        }
        if (TopRankerPreference.isTextEmpty(passingYear)) {
            edtPassingYear.setError("Field is Mandatory.");
            focusView = edtPassingYear;
            cancel = true;
        }
        if (TopRankerPreference.isTextEmpty(address)) {
            edtAddress.setError("Field is Mandatory.");
            focusView = edtAddress;
            cancel = true;
        }
        if (TopRankerPreference.isTextEmpty(collageName)) {
            edtCollegeName.setError("Field is Mandatory.");
            focusView = edtCollegeName;
            cancel = true;
        }
        if (TopRankerPreference.isTextEmpty(percent)) {
            edtPercent.setError("Field is Mandatory.");
            focusView = edtPercent;
            cancel = true;
        }
        if (TopRankerPreference.isTextEmpty(fatherName)) {
            edtFatherName.setError("Field is Mandatory.");
            focusView = edtFatherName;
            cancel = true;
        }

        if (TopRankerPreference.isTextEmpty(city)) {
            edtCity.setError("Field is Mandatory.");
            focusView = edtCity;
            cancel = true;
        }
        if (TopRankerPreference.isTextEmpty(dob)) {
            btnDOB.setError("Field is Mandatory.");
            focusView = btnDOB;
            cancel = true;
        }
        if (TopRankerPreference.isTextEmpty(gender)) {
            btnGender.setError("Field is Mandatory.");
            focusView = btnGender;
            cancel = true;
        }
        if (TopRankerPreference.isTextEmpty(state)) {
            btnState.setError("Field is Mandatory.");
            focusView = btnState;
            cancel = true;
        }
        if (TopRankerPreference.isTextEmpty(state1)) {
            btnState1.setError("Field is Mandatory.");
            focusView = btnState1;
            cancel = true;
        }
        if (photoPic == null) {
            /*btnState1.setError("Field is Mandatory.");
            focusView = btnState1;
            cancel = true;*/
        }

        if (cancel) {
            focusView.requestFocus();
            // Toast.makeText(getApplicationContext(), "Please correct the errors", Toast.LENGTH_SHORT).show();
        } else {
            UpdateProfileRequest updateProfileRequest = new UpdateProfileRequest();
            UserProfile userProfile = new UserProfile();
            updateProfileRequest.setToken(TopRankerPreference.getInstance().getStringData(TopRankerPreference.TOKEN_ID));
            userProfile.setUpFullname(fullName);
            userProfile.setUpQualification(qualification);
            userProfile.setUpCollegeName(collageName);
            userProfile.setUpFname(fatherName);
            userProfile.setUpCollegeCity(collegeCity);
            userProfile.setUpPassingYear(passingYear);
            userProfile.setUpAddress(address);
            userProfile.setUpPercent(percent);
            userProfile.setUpPin(pin);
            userProfile.setUpMobile(mobile);
            userProfile.setUpCity(city);
            userProfile.setUpCollegeName(collageName);
            userProfile.setUpId(profileResponse.getData().getUpId());
            userProfile.setUpDob(dob);
            userProfile.setUpState(state1);
            userProfile.setUpCollegeState(state);
            userProfile.setUpGender(gender);
            userProfile.setFile(photoPic);
            updateProfileRequest.setUserProfile(userProfile);
            submitRequest(updateProfileRequest);
        }
    }


    private void submitRequest(UpdateProfileRequest updateProfileRequest) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        String registerJSON = new Gson().toJson(updateProfileRequest);
        Log.e("profile ",registerJSON );
        Call<BaseResponse> callClient = apiService.saveProfile(registerJSON);
        callClient.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, retrofit2.Response<BaseResponse> response) {
                int statusCode = response.code();
                if (statusCode == 200) {
                    if (response.body() instanceof BaseResponse) {
                        BaseResponse BaseResponse = response.body();
                        if (BaseResponse.getStatus()) {
                            getActivity().finish();
                            //Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                        } else {
                            //Toast.makeText(getApplicationContext(), BaseResponse.getValidationErrors(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        //Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
             //   DialogUtitlity.hideLoading();
                //Toast.makeText(getApplicationContext(), "Fail error" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void imageFile(Bitmap bitmap) throws Exception {
        Calendar rightNow = Calendar.getInstance();
        long offset = rightNow.get(Calendar.ZONE_OFFSET) + rightNow.get(Calendar.DST_OFFSET);
        String sinceMidnight = Long.toString((rightNow.getTimeInMillis() + offset) % (24 * 60 * 60 * 1000));
        String fileName = sinceMidnight + "_profile.png";
        photoPic = new File(getActivity().getCacheDir(), fileName);
        photoPic.createNewFile();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
        byte[] bitmapdata = bos.toByteArray();
        FileOutputStream fos = new FileOutputStream(photoPic);
        fos.write(bitmapdata);
        fos.flush();
        fos.close();
    }


    private Bitmap decodeUri(Uri selectedImage) throws FileNotFoundException {
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(selectedImage), null, o);
        final int REQUIRED_SIZE = 140;
        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;
        while (true) {
            if (width_tmp / 2 < REQUIRED_SIZE
                    || height_tmp / 2 < REQUIRED_SIZE) {
                break;
            }
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        return BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(selectedImage), null, o2);

    }


}
