package com.jessy.safety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.jessy.safety.R;

import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences prefs = getSharedPreferences("myPrefs", MODE_PRIVATE);
        String intentToGoogleMapsString = prefs.getString("intentToGoogleMaps", null);
        if (intentToGoogleMapsString != null) {
            Intent intentToGoogleMaps = null;
            try {
                intentToGoogleMaps = Intent.parseUri(intentToGoogleMapsString, Intent.URI_INTENT_SCHEME);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            startActivity(intentToGoogleMaps);
            prefs.edit().remove("intentToGoogleMaps").apply();
        }
        else{
            Log.d("noidea","nullji");
        }
    }
}