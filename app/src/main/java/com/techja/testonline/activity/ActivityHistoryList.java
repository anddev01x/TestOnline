package com.techja.testonline.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.techja.testonline.viewapdater.AdapterHistory;
import com.techja.testonline.databinding.ActivityMain2Binding;
import com.techja.testonline.dbhelper.DBHelperHistory;
import com.techja.testonline.models.Result;

import java.util.List;

public class ActivityHistoryList extends AppCompatActivity {

    private ActivityMain2Binding binding;
    private DBHelperHistory dbHelperHistory;
    private List<Result> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dbHelperHistory = new DBHelperHistory(this);
        list = dbHelperHistory.getAllResult();
        if (!list.isEmpty()) {
            AdapterHistory adapterHistory = new AdapterHistory(getApplicationContext(), list);
            binding.recyclerViewHistory.setAdapter(adapterHistory);
            binding.recyclerViewHistory.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        }

        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}