package com.sena.ui.fragments;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.sena.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.sena.utils.PermissionUtility.MY_PERMISSIONS_REQUEST_CAMERA;

/**
 * Created by MrClawSs on 11/25/2017.
 */

public abstract class BaseFragment extends Fragment {

    public View root;

    public Activity getParent() {
        return  getActivity();
    }

    public static String readFromAssets(Context context, String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open(filename)));

        // do reading, usually loop until end of file reading
        StringBuilder sb = new StringBuilder();
        String mLine = reader.readLine();
        while (mLine != null) {
            sb.append(mLine); // process line
            mLine = reader.readLine();
        }
        reader.close();
        return sb.toString();
    }

    public void toast(String message) {
        Toast.makeText(getParent(), message, Toast.LENGTH_LONG).show();
    }


    public void initToolbar() {
        Toolbar tlb = (Toolbar) root.findViewById(R.id.toolbar);
        tlb.setNavigationIcon(R.drawable.ic_back_navigation);

        tlb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onToolbarBack();
            }
        });
    }


    public Toolbar getToolbar() {
        return (Toolbar) root.findViewById(R.id.toolbar);
    }

    public void hideToolbar() {
        root.findViewById(R.id.toolbar).setVisibility(View.GONE);
    }

    /**
     * Triggered when the toolbar back button is clicked
     */
    public abstract void onToolbarBack();

    public boolean seekPermission(String title, String message,
                                  final int requestCode, final String... p) throws Exception {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (p.length == 1) {
                int isGranted = ContextCompat.checkSelfPermission(getContext(), p[0]);
                boolean shouldRequestPermissionRationale = shouldShowRequestPermissionRationale(p[0]);

                if (isGranted == PackageManager.PERMISSION_GRANTED) {
                    return true;
                } else if (isGranted != PackageManager.PERMISSION_GRANTED && shouldRequestPermissionRationale) {
                    new AlertDialog.Builder(getContext())
                            .setCancelable(true)
                            .setTitle(title)
                            .setMessage(message)
                            .setPositiveButton(
                                    android.R.string.yes,
                                    new DialogInterface.OnClickListener() {

                                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                                        public void onClick(DialogInterface dialog, int which) {
                                            requestPermissions(p, requestCode);
                                        }
                                    })
                            .setNegativeButton(android.R.string.cancel, null)
                            .create().show();
                } else {
                    requestPermissions(p, requestCode);
                }
            } else if (p.length > 1) {
                if (ContextCompat.checkSelfPermission(getContext(), p[0]) == PackageManager.PERMISSION_GRANTED &&
                        ContextCompat.checkSelfPermission(getContext(), p[1]) == PackageManager.PERMISSION_GRANTED) {
                    return true;
                } else if (ContextCompat.checkSelfPermission(getContext(), p[0]) == PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(getContext(), p[1]) == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(getContext(), p[0]) != PackageManager.PERMISSION_GRANTED) {
                        if (shouldShowRequestPermissionRationale(p[0])) {
                            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getContext());
                            alertBuilder.setCancelable(true);
                            alertBuilder.setTitle(title);
                            alertBuilder.setMessage(message);
                            alertBuilder.setPositiveButton(
                                    android.R.string.yes,
                                    new DialogInterface.OnClickListener() {

                                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                                        public void onClick(DialogInterface dialog, int which) {
                                            requestPermissions(new String[]{p[0]}, requestCode);
                                        }
                                    });

                            alertBuilder.create().show();
                        }else {
                            requestPermissions( new String[]{p[0]}, requestCode);
                        }
                    } else if (ContextCompat.checkSelfPermission(getContext(), p[1]) != PackageManager.PERMISSION_GRANTED) {
                        if (shouldShowRequestPermissionRationale(p[1])) {
                            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getContext());
                            alertBuilder.setCancelable(true);
                            alertBuilder.setTitle(title);
                            alertBuilder.setMessage(message);
                            alertBuilder.setPositiveButton(
                                    android.R.string.yes,
                                    new DialogInterface.OnClickListener() {

                                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                                        public void onClick(DialogInterface dialog, int which) {
                                            requestPermissions(new String[]{p[1]}, requestCode);
                                        }
                                    });
                            alertBuilder.create().show();
                        }else {
                            requestPermissions(new String[]{p[1]}, requestCode);
                        }
                    }
                } else {
                    requestPermissions(p, requestCode);
                }
            }
        }else return true;
        return false;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public boolean checkCameraPermission() {
        int currentAPIVersion = Build.VERSION.SDK_INT;

        if (currentAPIVersion >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getContext());
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle("Permission to TAKE PHOTO");
                    alertBuilder.setMessage("This permission is required in order to access the device storage to pick the image");
                    alertBuilder.setPositiveButton(android.R.string.yes,
                            new DialogInterface.OnClickListener() {

                                @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                                public void onClick(DialogInterface dialog, int which) {
                                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
                                }
                            });

                    AlertDialog alert = alertBuilder.create();
                    alert.show();
                } else {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public boolean checkLocationPermisssions(final int request_code) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                boolean shouldRequestPermission = shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) ||
                        shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION);
                if (shouldRequestPermission) {
                    /* Ask for permission */
                    new AlertDialog.Builder(getContext())
                            .setCancelable(true)
                            .setTitle("Location Permission")
                            .setMessage("This permission will allow the application to acquire your current location.")
                            .setPositiveButton(
                                    android.R.string.yes,
                                    new DialogInterface.OnClickListener() {

                                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                                        public void onClick(DialogInterface dialog, int which) {
                                            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, request_code);
                                        }
                                    })
                            .setNegativeButton(android.R.string.cancel, null)
                            .create().show();
                }else {
                    requestPermissions(new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION}, request_code);
                }
                return false;
            }
        }return true;
    }

}