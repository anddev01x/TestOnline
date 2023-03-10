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
import com.techja.testonline.databinding.ActSignupBinding;
import com.techja.testonline.dbhelper.DBHelper;
import com.techja.testonline.models.User;

public class ActivitySignUp extends AppCompatActivity implements View.OnClickListener {
    private ActSignupBinding binding;
    private DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActSignupBinding.inflate(getLayoutInflater());
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(binding.getRoot());

        initViews();
    }

    private void initViews() {
        binding.tvLogin.setOnClickListener(this);
        binding.btSignup.setOnClickListener(this);
        DB = new DBHelper(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_login) {
            doClickLogin();
        }
        if (view.getId() == R.id.bt_signup) {
            doClickBtSignup();
        }
    }

    private void doClickLogin() {
        Intent i = new Intent(this, ActivityLogin.class);
        startActivity(i);
    }

    private void doClickBtSignup() {
        String user = binding.edtNewuser.getText().toString();
        String pass = binding.edtNewpassword.getText().toString();
        String repass = binding.edtRepassword.getText().toString();
        if (user.equals("") || pass.equals("") || repass.equals("")) {
            Toast.makeText(this, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        } else {
            if (pass.equals(repass)) {
                if (DB.checkusernamepassword(user, pass) == false) {
                    User user1 = new User(user, pass);
                    DB.addStudent(user1);
                    onBackPressed();
                    Toast.makeText(this, "Thành công!!!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Tồn tại!!!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
