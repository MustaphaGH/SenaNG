package com.sena.core.base;

/**
 * Created by MrClawSs on 11/11/2017.
 */

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sena.R;
import com.sena.core.Utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class BaseActivity extends AppCompatActivity implements OnToolbarBack {

    public static final String TAG = "Sena";

    public SweetAlertDialog sweetAlertDialog;

    public void showLoadAlert(){

        hideAlerts();
        sweetAlertDialog = new SweetAlertDialog(this,SweetAlertDialog.PROGRESS_TYPE);

    }

    public void hideAlerts(){
        if(sweetAlertDialog!=null){
            sweetAlertDialog.dismiss();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        d("<============================= onCreate(Bundle) =============================>");

    }







    @Override
    protected void onStop() {
        super.onStop();
        d("<================================= onStop() =================================>");
    }

    @Override
    protected void onResume() {
        super.onResume();
        d("<================================= onResume() =================================>");
    }

    /**
     * Returns a TextView
     *
     * @param id The id of the TextView
     * @return {@link TextView}
     */
    public TextView getTv(int id) {
        return (TextView) findViewById(id);
    }

    public TextView getTv(int id, View v) {
        return (TextView) v.findViewById(id);
    }

    public TextView getTv(int id, Typeface font) {
        TextView tv =  (TextView) findViewById(id);
        tv.setTypeface(font);
        return tv;
    }

    /**
     * Returns a Button
     *
     * @param id The id of the Button
     * @return {@link Button}
     */
    public Button getBt(int id) {
        return (Button) findViewById(id);
    }

    public Button getBt(int id, Typeface font){
        Button bt = getBt(id);
        bt.setTypeface(font);
        return bt;
    }

    /**
     * Returns an EditText
     *
     * @param id The id of the EditText
     * @return {@link EditText}
     */
    public EditText getEd(int id) {
        return (EditText) findViewById(id);
    }

    public EditText getEd(int id, Typeface font){
        EditText ed = getEd(id);
        ed.setTypeface(font);
        return ed;
    }

    /**
     * Returns the value in the {@link EditText} of the given id
     * @param id the ID of the View
     * @return The value in the edit Text
     */
    public String getInputValue(int id){
        return getEd(id).getEditableText().toString().trim();
    }

    /**
     * Returns a Spinner
     *
     * @param id The id of the Spinner
     * @return {@link Spinner}
     */
    public Spinner getSp(int id) {
        return (Spinner) findViewById(id);
    }

    /**
     * Returns an ImageView
     *
     * @param id The id of the ImageView
     * @return {@link ImageView}
     */
    public ImageView getIm(int id) {
        return (ImageView) findViewById(id);
    }

    /**
     * Return a general View
     *
     * @param id The id of the vie
     * @return {@link View}
     */
    public View getView(int id) {
        return findViewById(id);
    }

    /**
     * Show a toast
     *
     * @param message Message to be shown in the toast
     */
    public void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Post a debug message to the LogCat, using the format ClassName : message <br>
     * For example, "OCRActivity : Loading fonts"
     *
     * @param message String message to post
     */
    public void d(String message) {
        Utils.d(this, message);
    }

    /**
     * Post a error message to the LogCat, using the format ClassName : message <br>
     * For example, "OCRActivity : Loading fonts"
     *
     * @param message String message to post
     */
    public void e(String message) {
        Utils.e(this, message);
    }



    public Snackbar getSnackBarBlue(int view, String message) {
        Snackbar s = Snackbar.make(getView(view), message, Snackbar.LENGTH_LONG);

        View snackBarView = s.getView();
        snackBarView.setBackgroundColor(getColorValue(R.color.colorPrimary));
        TextView textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);

        s.show();
        return s;
    }



    public Snackbar getSnackBarOrange(int view, String message) {
        Snackbar s = Snackbar.make(getView(view), message, Snackbar.LENGTH_LONG);

        View snackBarView = s.getView();
        snackBarView.setBackgroundColor(getColorValue(R.color.colorAccent));
        TextView textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);

        s.show();
        return s;
    }



    public int getColorValue(int id) {
        return ContextCompat.getColor(getApplicationContext(), id);
    }

    public Snackbar getSnackBar(int view, String message){
        Snackbar s = Snackbar.make(getView(view), message, Snackbar.LENGTH_LONG);
        return s;
    }



    /**
     * Read a file from the assets folder
     * @param context {@link Context}
     * @param filename Name of the file to read
     * @return the content of the file.
     * @throws IOException
     */
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


    @Override
    public  void onToolbarBack() {    }
}

