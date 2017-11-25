package com.sena.utils;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;



    /*
    | ---------------
    | Class for permission asking
    | ---------------
    */


/**
 *
 * <p> A simple utility file for seeking permission for both {@link Manifest.permission#CAMERA} and
 * {@link Manifest.permission#READ_EXTERNAL_STORAGE}</p>
 */
public class PermissionUtility {
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 130;
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 131;
    public static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 132;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static boolean checkExternalStoragePermission(final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;

        if (currentAPIVersion >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle("Permission to ACCESS STORAGE");
                    alertBuilder.setMessage("This permission is required in order to access the device storage to pick the image");
                    alertBuilder.setPositiveButton(android.R.string.yes,
                            new DialogInterface.OnClickListener() {

                                @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                                public void onClick(DialogInterface dialog, int which) {
                                    ActivityCompat.requestPermissions((Activity) context,
                                            new String[]{
                                                    Manifest.permission.READ_EXTERNAL_STORAGE
                                            },
                                            MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                                }
                            });

                    AlertDialog alert = alertBuilder.create();
                    alert.show();
                } else {
                    ActivityCompat.requestPermissions((Activity) context,
                            new String[]{
                                    Manifest.permission.READ_EXTERNAL_STORAGE
                            },
                            MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static boolean checkCameraPermission(final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;

        if (currentAPIVersion >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.CAMERA)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle("Permission to TAKE PHOTO");
                    alertBuilder.setMessage("This permission is required in order to access the device storage to pick the image");
                    alertBuilder.setPositiveButton(android.R.string.yes,
                            new DialogInterface.OnClickListener() {

                                @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                                public void onClick(DialogInterface dialog, int which) {
                                    ActivityCompat.requestPermissions((Activity) context,
                                            new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
                                }
                            });

                    AlertDialog alert = alertBuilder.create();
                    alert.show();
                } else {
                    ActivityCompat.requestPermissions((Activity) context,
                            new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static boolean checkSendSMSMPermission(final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;

        if (currentAPIVersion >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS)
                    != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.SEND_SMS)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle("Permission to Send sms");
                    alertBuilder.setMessage("This permission is required in order to Send an SMS to complete the client registration process.");
                    alertBuilder.setPositiveButton(android.R.string.yes,
                            new DialogInterface.OnClickListener() {

                                @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                                public void onClick(DialogInterface dialog, int which) {
                                    ActivityCompat.requestPermissions((Activity) context,
                                            new String[]{Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_SEND_SMS);
                                }
                            });

                    AlertDialog alert = alertBuilder.create();
                    alert.show();
                } else {
                    ActivityCompat.requestPermissions((Activity) context,
                            new String[]{Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_SEND_SMS);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public static boolean checkLocationPermisssions(final Context context, final int request_code) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                boolean shouldRequestPermission = ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.ACCESS_FINE_LOCATION) ||
                        ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.ACCESS_COARSE_LOCATION);
                if (shouldRequestPermission) {
                    /* Ask for permission */
                    new AlertDialog.Builder(context)
                            .setCancelable(true)
                            .setTitle("Location Permission")
                            .setMessage("This permission will allow the application to acquire your current location.")
                            .setPositiveButton(
                                    android.R.string.yes,
                                    new DialogInterface.OnClickListener() {

                                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                                        public void onClick(DialogInterface dialog, int which) {
                                            ActivityCompat.requestPermissions((Activity) context,
                                                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, request_code);
                                        }
                                    })
                            .setNegativeButton(android.R.string.cancel, null)
                            .create().show();
                }else {
                    ActivityCompat.requestPermissions((Activity) context,
                            new String[]{
                                    Manifest.permission.ACCESS_FINE_LOCATION,
                                    Manifest.permission.ACCESS_COARSE_LOCATION}, request_code);
                }
                return false;
            }
        }return true;
    }

    /**
     * <p>Check if runtime permissions have been granted. If not, requests the permissions and perhaps
     * show an alert on why it's required. </p>
     *
     * @param context     {@link Context}
     * @param p           The Permissions to check for.
     * @param title       The title of the permissions alert dialog, if required
     * @param message     The message to show on the alert, when required
     * @param requestCode The Request Code for this permission
     * @return True if granted, False otherwise.
     */
    public static boolean seekPermission(final Context context, String title, String message,
                                         final int requestCode, final String... p) throws Exception {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (p.length == 1) {
                int isGranted = ContextCompat.checkSelfPermission(context, p[0]);
                boolean shouldRequestPermissionRationale = ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, p[0]);

                if (isGranted == PackageManager.PERMISSION_GRANTED) {
                    return true;
                } else if (isGranted != PackageManager.PERMISSION_GRANTED && shouldRequestPermissionRationale) {
                    new AlertDialog.Builder(context)
                            .setCancelable(true)
                            .setTitle(title)
                            .setMessage(message)
                            .setPositiveButton(
                                    android.R.string.yes,
                                    new DialogInterface.OnClickListener() {

                                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                                        public void onClick(DialogInterface dialog, int which) {
                                            ActivityCompat.requestPermissions((Activity) context,
                                                    p, requestCode);
                                        }
                                    })
                            .setNegativeButton(android.R.string.cancel, null)
                            .create().show();
                } else {
                    ActivityCompat.requestPermissions((Activity) context,
                            p, requestCode);
                }
            } else if (p.length > 1) {
                if (ContextCompat.checkSelfPermission(context, p[0]) == PackageManager.PERMISSION_GRANTED &&
                        ContextCompat.checkSelfPermission(context, p[1]) == PackageManager.PERMISSION_GRANTED) {
                    return true;
                } else if (ContextCompat.checkSelfPermission(context, p[0]) == PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(context, p[1]) == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(context, p[0]) != PackageManager.PERMISSION_GRANTED) {
                        if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, p[0])) {
                            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                            alertBuilder.setCancelable(true);
                            alertBuilder.setTitle(title);
                            alertBuilder.setMessage(message);
                            alertBuilder.setPositiveButton(
                                    android.R.string.yes,
                                    new DialogInterface.OnClickListener() {

                                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                                        public void onClick(DialogInterface dialog, int which) {
                                            ActivityCompat.requestPermissions((Activity) context,
                                                    new String[]{p[0]}, requestCode);
                                        }
                                    });

                            alertBuilder.create().show();
                        }else {
                            ActivityCompat.requestPermissions((Activity) context,
                                    new String[]{p[0]}, requestCode);
                        }
                    } else if (ContextCompat.checkSelfPermission(context, p[1]) != PackageManager.PERMISSION_GRANTED) {
                        if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, p[1])) {
                            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                            alertBuilder.setCancelable(true);
                            alertBuilder.setTitle(title);
                            alertBuilder.setMessage(message);
                            alertBuilder.setPositiveButton(
                                    android.R.string.yes,
                                    new DialogInterface.OnClickListener() {

                                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                                        public void onClick(DialogInterface dialog, int which) {
                                            ActivityCompat.requestPermissions((Activity) context,
                                                    new String[]{p[1]}, requestCode);
                                        }
                                    });
                            alertBuilder.create().show();
                        }else {
                            ActivityCompat.requestPermissions((Activity) context,
                                    new String[]{p[1]}, requestCode);
                        }
                    }
                } else {
                    ActivityCompat.requestPermissions((Activity) context,
                            p, requestCode);
                }
            }
        }else return true;
        return false;
    }

}
