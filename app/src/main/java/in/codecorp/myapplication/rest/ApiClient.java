package in.codecorp.myapplication.rest;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;


import in.codecorp.myapplication.Utils.Answer;
import in.codecorp.myapplication.Utils.TimeSpent;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class ApiClient {

    private static final String BASE_URL = "http://demos.abhinavsoftware.com/gc/api/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
            logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
            builder.addInterceptor(new MyOkHttpInterceptor());
            builder.connectTimeout(1, TimeUnit.MINUTES);
            builder.readTimeout(1, TimeUnit.MINUTES);
            builder.writeTimeout(1, TimeUnit.MINUTES);
            OkHttpClient okHttpClient = builder.build();

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(TimeSpent.class, new TimeSpentDeserializer())
                    .registerTypeAdapter(Date.class, new DateTypeDeserializer())
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    //.addConverterFactory(new MyJsonConverter(gson))
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }


    public static class DateTypeDeserializer implements JsonDeserializer<Date> {
        private static final String[] DATE_FORMATS = new String[]{
                "EEE dd MMM yyyy HH:mm:ss z",
                "yyyy-MM-dd'T'HH:mm:ssZ",
                "yyyy-MM-dd'T'HH:mm:ss",
                "yyyy-MM-dd",
                "EEE MMM dd HH:mm:ss z yyyy",
                "MM/dd/yyyy HH:mm:ss aaa",
                "yyyy-MM-dd'T'HH:mm:ss.SSSSSS",
                "yyyy-MM-dd'T'HH:mm:ss.SSSSSSS",
                "yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'",
                "MMM d',' yyyy H:mm:ss a",
                "yyyy-MM-dd HH:mm:ss.z",
                "yyyy-MM-dd HH:mm:ss.a",
                "yyyy-dd-MM HH:mm:ss"
        };

        @Override
        public final Date deserialize(JsonElement jsonElement, Type typeOF, JsonDeserializationContext context) throws JsonParseException {
            for (String format : DATE_FORMATS) {
                try {
                    return new SimpleDateFormat(format, Locale.US).parse(jsonElement.getAsString());
                } catch (ParseException e) {
                    Log.e("ApiClient", e.toString());
                }
            }
            throw new JsonParseException("Unparseable date: \"" + jsonElement.getAsString()
                    + "\". Supported formats: \n" + Arrays.toString(DATE_FORMATS));
        }
    }

    public static class TimeSpentDeserializer implements JsonDeserializer<TimeSpent> {

        @Override
        public final TimeSpent deserialize(JsonElement jsonElement, Type typeOF, JsonDeserializationContext context) throws JsonParseException {
            Map<String,Answer> answers = new HashMap<>();
            if(jsonElement.isJsonObject()) {
                String json = jsonElement.getAsJsonObject().toString();
                answers = new Gson().fromJson(json, Map.class);
            }
            TimeSpent timespent = new TimeSpent();
            timespent.setAns(answers);
            return timespent;
        }
    }

    public static class MyOkHttpInterceptor implements Interceptor {
        public static final String HEADER_CONTENT_TYPE = "application/x-www-form-urlencoded";

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            Request newRequest = originalRequest.newBuilder()
                    .header("Content-Type", HEADER_CONTENT_TYPE)
                    .build();
            return chain.proceed(newRequest);
        }
    }
}

