package com.kmac_enterprises.chialphaapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Kelsey Maclin on 12/23/2015.
 */
public class MainActivity extends AppCompatActivity {

    private static final String PREF_NAME = "name";
    private static final String PREFS = "prefs";

    SharedPreferences mSharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayWelcome();

        Bridge.getCurrentUser().firstLogIn = false;

        Button logout_button = (Button) findViewById(R.id.logout_button);
        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logout = new Intent(MainActivity.this, LoginActivity.class);
                Bridge.clearUser();
                startActivity(logout);
                finish();
            }
        });
    }

    public void displayWelcome() {
        //Access the device's key-value storage
        //MODE_PRIVATE: means only this app can access this info
        //info won't be overwritten by another app with same key
        mSharedPreferences = getSharedPreferences(PREFS, MODE_PRIVATE);

        //read the user's name or an empty string if nothing found
        String name = mSharedPreferences.getString(PREF_NAME, "");
        String welcomeStr = "Welcome back, ";

        if (name.length() > 0) {
            if (!name.equals(Bridge.getCurrentUser().getFirst_name())
                    || Bridge.getCurrentUser().firstLogIn) {
                SharedPreferences.Editor e = mSharedPreferences.edit();
                e.putString(PREF_NAME, Bridge.getCurrentUser().getFirst_name());
                e.commit();

                welcomeStr = "Welcome, ";
                name = mSharedPreferences.getString(PREF_NAME, "");
            }
            //if the name is valid, display a toast welcoming them
            //LENGTH_LONG: length of time toast appears
            Toast.makeText(this, welcomeStr + name + "!", Toast.LENGTH_LONG).show();
        } else {

            SharedPreferences.Editor e = mSharedPreferences.edit();
            e.putString(PREF_NAME, Bridge.getCurrentUser().getFirst_name());
            e.commit();

            Toast.makeText(this, "Welcome, " + Bridge.getCurrentUser().getFirst_name()
                    + "!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
    }
}
