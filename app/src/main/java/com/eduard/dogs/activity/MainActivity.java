package com.eduard.dogs.activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.eduard.dogs.fragment.DogsListFragment.TAG;
import com.eduard.dogs.R;
import com.eduard.dogs.fragment.DogsDetailFragment;
import com.eduard.dogs.fragment.DogsListFragment;
import com.eduard.dogs.model.AlertErrorMessage;

public class MainActivity extends AppCompatActivity {

    private AlertErrorMessage alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInetMessg = findViewById(R.id.tvInetMessg);


        if(isOnline()){
            addFragment();
        }else {
           alertDialog =new AlertErrorMessage();
           alertDialog.showDialog( this,"No signal! \n Please check your internet connection");
        }
    }

            tvInetMessg.setVisibility(View.VISIBLE);
        }

    }

    //checking internet connection
    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }

    private void addFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        DogsListFragment dogsListFragment = new DogsListFragment();
        transaction.add(R.id.fl_container,dogsListFragment,TAG);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void replaceFragment(String breed,String subbreed) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        DogsDetailFragment detailFragment = DogsDetailFragment.newInstance(breed,subbreed);
        transaction.replace(R.id.fl_container,detailFragment,TAG);
        transaction.commit();
    }
}
