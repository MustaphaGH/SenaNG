package com.sena.core.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sena.core.activities.AppActivity;
import com.sena.R;
import com.sena.utils.TypeFaceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by MrClawSs on 10/8/2017.
 */

public class StartFragment extends Fragment {

    public static AppActivity _context;
    ImageView triggerMenu;
    private static final long MOVE_DEFAULT_TIME = 1000;
    private static final long FADE_DEFAULT_TIME = 300;


    @BindView(R.id.sena_start_interpret_button) Button interpretButton;
    @BindView(R.id.sena_logo_start) ImageView logo;
    @BindView(R.id.sena_start_message) TextView startMessage;

    private TextView realmMessage,interpretMessage;
    private Button realmButton;

    public static StartFragment newInstance()
    {
        return new StartFragment();
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_start, container, false);
        ButterKnife.bind(this,view);

        triggerMenu = (ImageView)view.findViewById(R.id.triggerMenu);
        triggerMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppActivity.openCloseDrawer();
            }
        });

        realmMessage = (TextView)view.findViewById(R.id.sena_start_realm);
        interpretMessage = (TextView)view.findViewById(R.id.sena_start_interpret);
        realmButton =(Button) view.findViewById(R.id.sena_start_realm_button);

        startMessage.setTypeface(TypeFaceUtils.create(getActivity()).getRegularFace());
        realmMessage.setTypeface(TypeFaceUtils.create(getActivity()).getLightFace());
        interpretMessage.setTypeface(TypeFaceUtils.create(getActivity()).getLightFace());

        realmButton.setTypeface(TypeFaceUtils.create(getActivity()).getMediumFace());
        interpretButton.setTypeface(TypeFaceUtils.create(getActivity()).getMediumFace());



        return view;
    }



    @OnClick(R.id.sena_start_interpret_button)
    public void navigateToInterpretation(Button button){


        FragmentTransaction firstTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        InterpretFragment interpretFragment = new InterpretFragment();

        //EXIT
        Fade exitFade = new Fade();
        exitFade.setDuration(FADE_DEFAULT_TIME);
        this.setExitTransition(exitFade);


        firstTransaction.replace(R.id.mainContentLayout,interpretFragment)
                .setTransition(R.transition.slide_and_changebounds)
                .addToBackStack("inter")
                .commitAllowingStateLoss();
    }
}
