package com.techja.testonline.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import com.google.android.material.navigation.NavigationView;
import com.techja.testonline.Contants;
import com.techja.testonline.R;
import com.techja.testonline.databinding.ActHomeBinding;

public class ActivityHome extends AppCompatActivity implements View.OnClickListener {
    private ActHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActHomeBinding.inflate(getLayoutInflater());
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(binding.getRoot());

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        View headView = navigationView.getHeaderView(0);
        ((TextView) headView.findViewById(R.id.txtTitle)).setText(getIntent().getStringExtra("name"));
        binding.RelativeLayoutLichSu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogExam(Contants.LichSu.KEY, Contants.LichSu.Values.LICH_SU, Contants.LichSu.Values.PATH);
        }
        });

        binding.RelativeLayoutDiaLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogExam(Contants.DiaLy.KEY, Contants.DiaLy.Values.DIA_LY, Contants.DiaLy.Values.PATH);
            }
        });

        binding.RelativeLayoutVatLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogExam(Contants.VatLy.KEY, Contants.VatLy.Values.VAT_LY, Contants.VatLy.Values.PATH);
            }
        });

        binding.RelativeLayoutTinHoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogExam(Contants.TinHoc.KEY, Contants.TinHoc.Values.TIN_HOC, Contants.TinHoc.Values.PATH);
            }
        });

        binding.RelativeLayoutEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogExam(Contants.TiengAnh.KEY, Contants.TiengAnh.Values.TIENG_ANH, Contants.TiengAnh.Values.PATH);
            }
        });

        binding.RelativeLayoutToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogExam(Contants.Toan.KEY, Contants.Toan.Values.TOAN, Contants.Toan.Values.PATH);
            }
        });

        initViews();

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        Intent intent = null;
                        switch (menuItem.getItemId()) {
                            case R.id.menu_home:
                                binding.drawerLayout.closeDrawers();
                                return true;
                            case R.id.menu_history:
                                intent = new Intent(getApplicationContext(), ActivityHistoryList.class);
                                startActivity(intent);
                                return true;

                            case R.id.menu_logout:
                                intent = new Intent(getApplicationContext(), ActivityLogin.class);
                                startActivity(intent);
                                ActivityHome.this.finish();
                                return true;
                        }

                        return true;
                    }
                });

    }

    private void showDialogExam(String keySubject, String valueSubject, int path) {
        AlertDialog alertDialog = new AlertDialog.Builder(ActivityHome.this).create();
        alertDialog.setTitle("THÔNG BÁO");
        alertDialog.setMessage("Vào thi môn " + valueSubject);
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", (dialogInterface, i) -> {
            alertDialog.show();
            Intent intent = new Intent(getApplicationContext(), ActivityQuestion.class);
            intent.putExtra("monhoc", keySubject);
            intent.putExtra("monhocTitle", valueSubject);
            intent.putExtra("pathData", path);
            startActivity(intent);
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCEL", (dialogInterface, i) -> {
            alertDialog.dismiss();
        });
        alertDialog.show();
    }

    private void initViews() {
        binding.navView.bringToFront();
        setSupportActionBar(binding.toolbar);
        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        binding.drawerLayout.addDrawerListener(toogle);
        toogle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View view) {

    }

}
