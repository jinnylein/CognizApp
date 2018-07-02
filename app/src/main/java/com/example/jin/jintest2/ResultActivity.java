package com.example.jin.jintest2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ResultActivity extends AppCompatActivity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        context = this;

        int correct = intent.getIntExtra("correct", 0);
        int count = intent.getIntExtra("count", 0);

        ((TextView) findViewById(R.id.textViewCorrect)).setText(String.valueOf(correct) + " Fragen richtig beantwortet");
        ((TextView) findViewById(R.id.textViewCount)).setText("Insgesamt " + String.valueOf(count) + " Fragen");

        ((Button) findViewById(R.id.backButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);

            }
        });
    }
}
