package com.techja.testonline.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.techja.testonline.databinding.ActivityMainBinding;
import com.techja.testonline.dbhelper.DBHelperHistory;
import com.techja.testonline.models.Result;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ActivityResult extends AppCompatActivity {
    private ActivityMainBinding binding;
    private DBHelperHistory dbHelperHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dbHelperHistory = new DBHelperHistory(this);

        Intent intent = getIntent();

        if (intent != null) {
            binding.txtText1.setText(intent.getIntExtra("resual", 0) + " / " + intent.getIntExtra("size", 0));
        }

        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), ActivityHome.class);
                startActivity(intent1);
                ActivityResult.this.finish();
            }
        });

        binding.linnearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Đáp án");
                    String shareMessage = "Tôi đã trả lời đúng " + intent.getIntExtra("resual", 0) + " câu hỏi.”.";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch (Exception e) {
                    //e.toString();
                }
            }
        });

        binding.linnearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Calendar cal = Calendar.getInstance();
                String monhoc ="";

                if (intent.getStringExtra("monhoc").equals("lichsu")){
                    monhoc = "Lịch sử";
                }else if (intent.getStringExtra("monhoc").equals("dialy")){
                    monhoc = "Địa lý";
                }else if (intent.getStringExtra("monhoc").equals("vatly")){
                    monhoc = "Vật lý";
                }
                else if (intent.getStringExtra("monhoc").equals("tinhoc")){
                    monhoc = "Tin học";
                }
                else if (intent.getStringExtra("monhoc").equals("tienganh")){
                    monhoc = "Tiếng anh";
                }
                else if (intent.getStringExtra("monhoc").equals("toan")){
                    monhoc = "Toán";
                }

                Result result = new Result(dateFormat.format(cal.getTime()).toString(), intent.getIntExtra("resual", 0) + "", monhoc);
                dbHelperHistory.addResult(result);
                Toast.makeText(ActivityResult.this, "Thành công !!!", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(getApplicationContext(), ActivityHome.class);
                startActivity(intent1);
                ActivityResult.this.finish();
            }
        });

    }
}