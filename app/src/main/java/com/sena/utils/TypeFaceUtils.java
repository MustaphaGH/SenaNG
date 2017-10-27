package com.sena.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by MrClawSs on 10/9/2017.
 */

public class TypeFaceUtils {

    private static Context _context;
    private static TypeFaceUtils instance;

    public static TypeFaceUtils create(Context context){
        _context = context;
        if(instance!=null)
        return  instance;
        else
            return new TypeFaceUtils();
    }
    public  Typeface getBoldFace(){
        return Typeface.createFromAsset(_context.getAssets(),"Quicksand-Bold.ttf");
    }
    public  Typeface getMediumFace(){
      return Typeface.createFromAsset(_context.getAssets(),"Quicksand-Medium.ttf");
    }
    public Typeface getRegularFace(){

        return Typeface.createFromAsset(_context.getAssets(),"Quicksand-Regular.ttf");

    }
    public Typeface getLightFace(){
        return Typeface.createFromAsset(_context.getAssets(),"Roboto-Light.ttf");
    }
}
