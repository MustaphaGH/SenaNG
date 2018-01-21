package com.sena.model;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.Serializable;

/**
 * Created by mustapha on 21/01/18.
 */

public class Session implements Serializable {

    private String token;
    private static User connectedUser;


    public static void initSession(Context cc){
        //TODO
    }


}
