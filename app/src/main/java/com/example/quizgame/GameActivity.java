package com.example.quizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.quizgame.model.WordItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    Button[] button;
    private String mAnswerWord;
    List<WordItem> words;
    int count;
    int point;
    TextView textViewScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        WordItem []  items = WordListActivity.items;
        words = new ArrayList<>(Arrays.asList(items));


        button = new Button[4];
        button[0] = findViewById(R.id.choice_1_button);
        button[1] = findViewById(R.id.choice_2_button);
        button[2] = findViewById(R.id.choice_3_button);
        button[3] = findViewById(R.id.choice_4_button);

        button[0].setOnClickListener(this);
        button[1].setOnClickListener(this);
        button[2].setOnClickListener(this);
        button[3].setOnClickListener(this);


        textViewScore = findViewById(R.id.text_view_score);
        textViewScore.setText("0 คะแนน");
        nextQuiz();


    }

    private void nextQuiz() {
        if(count>=5){


            AlertDialog.Builder dialog = new AlertDialog.Builder(GameActivity.this);
            dialog.setTitle("สรุปผล");
            dialog.setMessage("คุณได้ "+point+" คะแนน\nต้องการเล่นเกมใหม่หรือไม่");

            dialog.setPositiveButton("ํYES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    words = new ArrayList<>(Arrays.asList(WordListActivity.items));
                    count = 0;
                    point = 0;
                    textViewScore.setText("0 คะแนน");
                    nextQuiz();
                }
            });

            dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(GameActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });
            dialog.show();
        }else if(words.size() == 3){
            Toast.makeText(GameActivity.this, "EndGame", Toast.LENGTH_SHORT).show();
        }else{
            Random r = new Random();


            //WordItem item = items[answerIndex];
            Collections.shuffle(words);
            int answerIndex = r.nextInt(words.size() );
            ImageView imageView = findViewById(R.id.qusetion_image_view);
            imageView.setImageResource(words.get(answerIndex).imageResId);
            mAnswerWord = words.get(answerIndex).word;
            System.out.println(words.size());
            int randomButton = r.nextInt(4);
            button[randomButton].setText(mAnswerWord);
            words.remove(words.get(answerIndex));



            System.out.println(words.size());

            for(int i=0; i<4; i++){
                if(i!=randomButton){
                    button[i].setText(words.get(i).word);
                }

            }



        }

    }

    @Override
    public void onClick(View view) {
        Button b = findViewById(view.getId());
        String result;
        if(b.getText().equals(mAnswerWord)){
            point++;
            result = "Very Good";

        }else{
            result = "Don't Cry";
        }
        textViewScore.setText(point+" คะแนน");
        count++;
        //Toast.makeText(GameActivity.this, result, Toast.LENGTH_SHORT).show();
        nextQuiz();
    }
}

