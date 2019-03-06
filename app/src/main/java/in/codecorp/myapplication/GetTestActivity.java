package in.codecorp.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GetTestActivity extends AppCompatActivity {

    String levelid;
    TextView stest, atest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_test);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Intent rcv = getIntent();
        levelid = rcv.getStringExtra("levelid");

        stest = findViewById(R.id.stest);
        atest = findViewById(R.id.atest);


        stest.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(GetTestActivity.this, SubscribedTestActivity.class);
            //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("levelid", levelid );
            startActivity(intent);
        }
    });
        atest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GetTestActivity.this, TestDetailActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("levelid", levelid );
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
