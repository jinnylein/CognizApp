package com.example.jin.jintest2;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ResultActivity extends AppCompatActivity {
    private Context context;
    private double percent = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        Intent intent = getIntent();
        context = this;

        int correct = intent.getIntExtra("correct", 0);
        int count = intent.getIntExtra("count", 0);
        percent = 100/count*correct;

        if (percent >= 80) {
            ((TextView) findViewById(R.id.textView)).setText("Gratulation!");
        } else {
            ((TextView) findViewById(R.id.textView)).setText("Dranbleiben!");
            ((TextView) findViewById(R.id.textView2)).setText("Versuche es erneut, um besser abzuschneiden.");
        }

        ((TextView) findViewById(R.id.textViewCorrect)).setText("Du hast insgesamt " + String.valueOf(correct) + " von " + String.valueOf(count) + " Fragen richtig beantwortet.");

        ((Button) findViewById(R.id.backButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);

            }
        });
    }
}
