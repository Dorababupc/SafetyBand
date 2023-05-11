//package com.jessy.safety;
//
//import android.content.pm.PackageManager;
//import android.location.Location;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.core.app.ActivityCompat;
//
//import com.google.android.gms.location.FusedLocationProviderClient;
//import com.google.android.gms.location.LocationServices;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.functions.FirebaseFunctions;
//import com.google.firebase.functions.HttpsCallableResult;
//import com.google.firebase.messaging.FirebaseMessagingService;
//import com.google.firebase.messaging.RemoteMessage;
//
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class MyFirebaseMessagingService extends FirebaseMessagingService {
//    private static final String TAG = "MyFirebaseMsgService";
//    private FusedLocationProviderClient mFusedLocationClient;
//
//    @Override
//    public void onMessageReceived(RemoteMessage remoteMessage) {
//        super.onMessageReceived(remoteMessage);
//        if (remoteMessage.getData().size() > 0) {
//            String message = remoteMessage.getData().get("location_request");
//            startLocationUpdates();
//        }
//    }
//
//    private void startLocationUpdates() {
//        Log.d("here","called me");
//        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
//        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        mFusedLocationClient.getLastLocation()
//                .addOnCompleteListener(new OnCompleteListener<Location>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Location> task) {
//                        if (task.isSuccessful() && task.getResult() != null) {
//                            Location location = task.getResult();
//                            double latitude = location.getLatitude();
//                            double longitude = location.getLongitude();
//                            Toast.makeText(getApplicationContext(), "Latitude is"+latitude, Toast.LENGTH_LONG).show();
//                            Toast.makeText(getApplicationContext(),"longitude is"+longitude,Toast.LENGTH_LONG).show();
//                            Log.d(TAG, "Latitude: " + latitude + ", Longitude: " + longitude);
//                            // Call cloud function with the location data here
//                            sendLocationToCloudFunction(latitude, longitude);
//                        } else {
//                            Log.w(TAG, "Failed to get location.");
//                        }
//                    }
//                });
//    }
//
//    private void sendLocationToCloudFunction(double latitude, double longitude) {
//        // Prepare data to send to the cloud function
//        Map<String, Object> data = new HashMap<>();
//        data.put("latitude", latitude);
//        data.put("longitude", longitude);
//
//        // Use AsyncTask to send the data to the cloud function in the background
//        new AsyncTask<Map<String, Object>, Void, Void>() {
//            @Override
//            protected Void doInBackground(Map<String, Object>... maps) {
//                FirebaseFunctions.getInstance().getHttpsCallable("updateLocation")
//                        .call(maps[0])
//                        .addOnCompleteListener(new OnCompleteListener<HttpsCallableResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<HttpsCallableResult> task) {
//                                if (task.isSuccessful() && task.getResult() != null) {
//                                    Log.d(TAG, "Location sent to cloud function successfully.");
//                                } else {
//                                    Log.w(TAG, "Failed to send location to cloud function.");
//                                }
//                            }
//                        });
//                return null;
//            }
//        }.execute(data);
//    }
////private void sendLocationToCloudFunction(double latitude, double longitude) {
////    // Prepare data to send to the cloud function
////    Map<String, Object> data = new HashMap<>();
////    data.put("latitude", latitude);
////    data.put("longitude", longitude);
////
////    // Get the FirebaseFunctions instance
////    FirebaseFunctions functions = FirebaseFunctions.getInstance();
////
////    // Call the "updateLocation" function with the data and add a listener for the result
////    functions.getHttpsCallable("updateLocation")
////            .call(data)
////            .addOnCompleteListener(new OnCompleteListener<HttpsCallableResult>() {
////                @Override
////                public void onComplete(@NonNull Task<HttpsCallableResult> task) {
////                    if (task.isSuccessful() && task.getResult() != null) {
////                        Log.d(TAG, "Location sent to cloud function successfully.");
////                    } else {
////                        Log.w(TAG, "Failed to send location to cloud function.");
////                    }
////                }
////            });
////}
//
//
//    @Override
//    public void onNewToken(String token) {
//        // Send the new token to the server here
//        // This method will be called whenever the app receives a new FCM token
//        // You can use this token to send push notifications to the app from your cloud function
//        Log.d("TAG", "Refreshed token: " + token);
//
//        // Save the token to Firebase Realtime Database
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("tokens/" + token);
//        ref.setValue(true);
//    }
//}
package com.jessy.safety;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.functions.FirebaseFunctions;
import com.google.firebase.functions.HttpsCallableResult;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


import java.util.HashMap;
import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";
    private FusedLocationProviderClient mFusedLocationClient;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (remoteMessage.getData().size() > 0) {
            String message = remoteMessage.getData().get("location_request");
            startLocationUpdates();
        }
    }

    private void startLocationUpdates() {
        Log.d("here","called me");
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mFusedLocationClient.getLastLocation()
                .addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            Location location = task.getResult();
                            double latitude = location.getLatitude();
                            double longitude = location.getLongitude();
                            Toast.makeText(getApplicationContext(), "Latitude is"+latitude, Toast.LENGTH_LONG).show();
                            Toast.makeText(getApplicationContext(),"longitude is"+longitude,Toast.LENGTH_LONG).show();
                            Log.d(TAG, "Latitude: " + latitude + ", Longitude: " + longitude);
                            // Call cloud function with the location data here
                            sendLocationToCloudFunction(latitude, longitude);
                        } else {
                            Log.w(TAG, "Failed to get location.");
                        }
                    }
                });
    }

    private void sendLocationToCloudFunction(double latitude, double longitude) {
        // Prepare data to send to the cloud function
        Map<String, Object> data = new HashMap<>();
        data.put("latitude", latitude);
        data.put("longitude", longitude);
        Log.d("check",""+latitude);
        // Send the data to the cloud function using Firebase Functions
        FirebaseFunctions.getInstance().getHttpsCallable("updateLocation")
                .call(data)
                .addOnCompleteListener(new OnCompleteListener<HttpsCallableResult>() {
                    @Override
                    public void onComplete(@NonNull Task<HttpsCallableResult> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            Log.d(TAG, "Location sent to cloud function successfully.");
                        } else {
                            Log.w(TAG, "Failed to send location to cloud function.");
                            if (task.getException() != null) {
                                Log.e(TAG, "Error sending location: " + task.getException().getMessage());
                            }
                        }
                    }
                });
    }

    @Override
    public void onNewToken(String token) {
        // Send the new token to the server here
        // This method will be called whenever the app receives a new FCM token
        // You can use this token to send push notifications to the app from your cloud function
        Log.d("TAG", "Refreshed token: " + token);

//        // Save the token to Firebase Realtime Database
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("tokens/" + token);
//        ref.setValue(true);
        // Save the token to Firebase Realtime Database with a random key
        DatabaseReference tokensRef = FirebaseDatabase.getInstance().getReference("tokens");
        String key = tokensRef.push().getKey(); // generate a random key
        tokensRef.child(key).setValue(token);

    }
}