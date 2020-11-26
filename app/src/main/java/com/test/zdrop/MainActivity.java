package com.test.zdrop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.test.zdrop.utility.BackBtnPress;

public class MainActivity extends AppCompatActivity implements ProductFragment.OpenProductOption, BackBtnPress {

    private static final String TAG = "MainActivity";
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState == null) {
            fragment = new ProductFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();

        }
    }

    public void arrowClick(View view) {
        Toast.makeText(this, "arrow click", Toast.LENGTH_SHORT).show();
    }

    public void imgViewClick(View view) {
        Toast.makeText(this, "image click", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onOptionClicked() {
        fragment = new ProductOptionFragment1();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();
    }

    @Override
    public void onNavBackBtnPress(Fragment fragment) {

        int count = getSupportFragmentManager().getBackStackEntryCount();
        Log.d("", "onNavBackBtnPress: " + count);
        if (count == 0) {
            super.onBackPressed();
        }else if (fragment instanceof ProductOptionFragment2){
            Log.d(TAG, "elseif: ");
            getSupportFragmentManager().popBackStack();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProductFragment()).commit();
        }
        else {
            onBackPressed();
            Log.d(TAG, "else: ");
        }

    }
}