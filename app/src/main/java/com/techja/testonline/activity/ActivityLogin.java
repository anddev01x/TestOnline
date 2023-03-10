package com.techja.testonline.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.techja.testonline.R;
import com.techja.testonline.databinding.ActLoginBinding;
import com.techja.testonline.dbhelper.DBHelper;
import com.techja.testonline.models.User;

import java.util.List;

public class ActivityLogin extends AppCompatActivity implements View.OnClickListener {
    private ActLoginBinding binding;
    private DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActLoginBinding.inflate(getLayoutInflater());
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(binding.getRoot());

        initViews();
    }

    private void initViews() {
        binding.tvSignup.setOnClickListener(this);
        binding.btLogin.setOnClickListener(this);
        DB = new DBHelper(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_signup) {
            doClickTvSignUp();
        }
        if (view.getId() == R.id.bt_login) {
            doClickBtLogIn();
        }
    }

    private void doClickBtLogIn() {
        String user = binding.edtUsername.getText().toString();
        String pass = binding.edtPassword.getText().toString();
        if (user.equals("") || pass.equals(""))
            Toast.makeText(this, "Nhập tài khoản và mật khẩu", Toast.LENGTH_SHORT).show();
        else {
            if (DB.checkusernamepassword(user,pass)) {
                Intent i = new Intent(this, ActivityHome.class);
                i.putExtra("name",user);
                startActivity(i);
            } else {
                Toast.makeText(this, "User và pass không tồn tại !!!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void doClickTvSignUp() {
        Intent i = new Intent(this, ActivitySignUp.class);
        startActivity(i);
    }
}