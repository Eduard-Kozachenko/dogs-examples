package com.eduard.dogs.dogs.activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.eduard.dogs.DogsApp;
import com.eduard.dogs.R;
import com.eduard.dogs.dogs.di.DaggerDogsComponent;
import com.eduard.dogs.dogs.di.PresentationModule;
import com.eduard.dogs.dogs.fragment.DogsDetailFragment;
import com.eduard.dogs.dogs.fragment.DogsListFragment;
import com.eduard.dogs.dogs.model.AlertErrorMessage;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    AlertErrorMessage alertErrorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        onInjection();

        if(isOnline()){
            addFragment();
        }else {
            alertErrorMessage.showDialog( this,"No signal! \n Please check your internet connection");
        }
    }

    //return home
    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1){
            finish();
        }
        else {
            super.onBackPressed();
        }
    }

    //call onBackPressed to return to the dog list
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home) {
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
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

    //builder alert dialog
    protected void onInjection() {
        DaggerDogsComponent.builder()
                .appComponent(DogsApp.getComponent())
                .presentationModule(new PresentationModule())
                .build()
                .inject(this);
    }

    private void addFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        DogsListFragment dogsListFragment = new DogsListFragment();
        transaction.add(R.id.fl_container,dogsListFragment, dogsListFragment.TAG);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void replaceFragment(String breed,String subbreed) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        DogsDetailFragment detailFragment = DogsDetailFragment.newInstance(breed,subbreed);
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        transaction.replace(R.id.fl_container,detailFragment,detailFragment.TAG);
        transaction.addToBackStack(detailFragment.TAG);
        transaction.commit();
    }
}
