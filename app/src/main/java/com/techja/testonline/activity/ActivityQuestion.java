package com.techja.testonline.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.techja.testonline.R;
import com.techja.testonline.Utils;
import com.techja.testonline.databinding.ActivityQuestionBinding;
import com.techja.testonline.models.Question;

import java.util.List;

public class ActivityQuestion extends AppCompatActivity {
    private int backPressedTime;
    private ActivityQuestionBinding binding;
    private List<Question> list;
    private String json = "";
    private int index = 0;
    private int result = 0;
    private String monhoc = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuestionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        if (intent != null) {
            monhoc = intent.getStringExtra("monhoc");
            int idPath = intent.getIntExtra("pathData", 0);
            if (idPath != 0) {
                json = Utils.getJsonFromAssets(getApplicationContext(), idPath);
                list = Utils.getList(json);
            }

            binding.txtSubject.setText("Đề thi môn " + intent.getStringExtra("monhocTitle"));
            binding.progressbar.setMax(list.size());
            init(index);
        }

        binding.radioA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setViewNextQuestion(binding.radioA);
               binding.radioA.setTextColor(1);
            }
        });

        binding.radioB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setViewNextQuestion(binding.radioB);
            }
        });

        binding.radioC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setViewNextQuestion(binding.radioC);
            }
        });

        binding.radioD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setViewNextQuestion(binding.radioD);
            }
        });

    }

    private void setViewNextQuestion(TextView radio) {
        if (radio.getText().toString().equals(list.get(index).getResult())) {
            result++;
        }
        binding.progressbar.setProgress(index + 1);
        if (index < list.size() - 1) {
            index++;
            init(index);
        } else {
            Intent intent1 = new Intent(getApplicationContext(), ActivityResult.class);
            intent1.putExtra("monhoc", monhoc);
            intent1.putExtra("resual", result);
            intent1.putExtra("size", list.size());
            startActivity(intent1);
            index = 0;
            result = 0;
        }
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime == 0) {
            Toast.makeText(this, "Nhấn back lần nữa để thoát", Toast.LENGTH_SHORT).show();
            backPressedTime++;
            //reset time after 1.5 seconds
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    backPressedTime = 0; // Do something after 1500ms = 1.5s
                }
            }, 2000);
        } else {
            super.onBackPressed();
        }
    }


    public void init(int index) {
        binding.txtQuestion.setText(list.get(index).getQuestion() + "");
        binding.radioA.setText(list.get(index).getAnswerA());
        binding.radioB.setText(list.get(index).getAnswerB());
        binding.radioC.setText(list.get(index).getAnswerC());
        binding.radioD.setText(list.get(index).getAnswerD());
    }

}