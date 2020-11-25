package com.test.zdrop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fragment=new ProductFragment();

        if (savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();

        }
    }

    public void arrowClick(View view) {
        Toast.makeText(this, "arrow click", Toast.LENGTH_SHORT).show();
    }

    public void imgViewClick(View view) {
        Toast.makeText(this, "image click", Toast.LENGTH_SHORT).show();
    }
}