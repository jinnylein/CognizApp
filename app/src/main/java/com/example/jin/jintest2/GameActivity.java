package com.example.jin.jintest2;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private JSONArray questions;
    private ArrayList<JSONObject> questionArray = new ArrayList<>();
    private ArrayList<JSONObject> rechtQuestions;
    private ArrayList<JSONObject> scQuestions;
    private int correct, counter = 0;
    private TextView textViewQuestion, textViewCorrect, textViewProgress;
    private Button buttonAnswer0, buttonAnswer1, buttonAnswer2, buttonAnswer3;
    private String correctAnswer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        Intent intent = getIntent();

        textViewQuestion = findViewById(R.id.textViewQuestion);
        textViewCorrect = findViewById(R.id.textViewCorrect);
        textViewProgress =findViewById(R.id.textViewProgress);

        buttonAnswer0 = findViewById(R.id.buttonAnswer0);
        buttonAnswer1 = findViewById(R.id.buttonAnswer1);
        buttonAnswer2 = findViewById(R.id.buttonAnswer2);
        buttonAnswer3 = findViewById(R.id.buttonAnswer3);

        buttonAnswer0.setOnClickListener(this);
        buttonAnswer1.setOnClickListener(this);
        buttonAnswer2.setOnClickListener(this);
        buttonAnswer3.setOnClickListener(this);


        String questionsString = intent.getStringExtra("questions");
        Log.d("STRING", questionsString);
        try {
            questions = new JSONArray(questionsString);
            Log.d("ARRAY", questions.toString());
            Log.d("LENGTH", String.valueOf(questions.length()));

            for (int i = 0; i < questions.length(); i++) {
                JSONObject obj = questions.getJSONObject(i);
                questionArray.add(obj);
            }

            counter += 1;
            JSONObject obj = questionArray.get(0);
            textViewQuestion.setText(obj.getString("question"));
            textViewProgress.setText("Frage " + String.valueOf(counter) + "/" + String.valueOf(questions.length()));
            textViewCorrect.setText(String.valueOf(correct) + "/" + String.valueOf(questions.length() + " Fragen korrekt"));
            correctAnswer = obj.getJSONObject("answers").getString(obj.getString("correct"));
            ArrayList<String> answers = new ArrayList<>();
            JSONObject answersObj = obj.getJSONObject("answers");
            answers.add(answersObj.getString("0"));
            answers.add(answersObj.getString("1"));
            answers.add(answersObj.getString("2"));
            answers.add(answersObj.getString("3"));
            Collections.shuffle(answers);

            buttonAnswer0.setText(answers.get(0));
            buttonAnswer1.setText(answers.get(1));
            buttonAnswer2.setText(answers.get(2));
            buttonAnswer3.setText(answers.get(3));
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onClick(View view) {
        Button button = null;

        switch (view.getId()) {
            case R.id.buttonAnswer0:
                button = buttonAnswer0;
                break;
            case R.id.buttonAnswer1:
                button = buttonAnswer1;
                break;
            case R.id.buttonAnswer2:
                button = buttonAnswer2;
                break;
            case R.id.buttonAnswer3:
                button = buttonAnswer3;
                break;
        }
        if (button.getText() == correctAnswer) {
            correct +=1;
            Toast toast = Toast.makeText(this, "Richtige Antwort!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 455);
            toast.show();
         //   Toast.makeText(this, "Richtige Antwort!",
         //           Toast.LENGTH_SHORT).show();
        } else {
            Toast toast = Toast.makeText(this, "Leider falsch!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 455);
            toast.show();
         //   Toast.makeText(this, "Leider falsch!",
         //           Toast.LENGTH_SHORT).show();
        }

        if (counter < questionArray.size()) {
            nextQuestion(counter);
        } else {
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("correct", correct);
            intent.putExtra("count", questionArray.size());
            System.out.println("Fettich");
            startActivity(intent);
        }

    }

    public void nextQuestion(int i) {
        try {
            counter += 1;
            JSONObject obj = questionArray.get(i);

            textViewQuestion.setText(obj.getString("question"));

            textViewProgress.setText("Frage " + String.valueOf(counter) + "/" + String.valueOf(questions.length()));
            textViewCorrect.setText(String.valueOf(correct) + "/" + String.valueOf(questions.length() + " Fragen korrekt"));
            correctAnswer = obj.getJSONObject("answers").getString(obj.getString("correct"));
            ArrayList<String> answers = new ArrayList<>();
            JSONObject answersObj = obj.getJSONObject("answers");
            answers.add(answersObj.getString("0"));
            answers.add(answersObj.getString("1"));
            answers.add(answersObj.getString("2"));
            answers.add(answersObj.getString("3"));
            Collections.shuffle(answers);

            buttonAnswer0.setText(answers.get(0));
            buttonAnswer1.setText(answers.get(1));
            buttonAnswer2.setText(answers.get(2));
            buttonAnswer3.setText(answers.get(3));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}


