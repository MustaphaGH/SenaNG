package com.sena.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sena.ui.activities.AppActivity;
import com.sena.R;
import com.sena.ui.fragments.interpretation.InterpretFragment;
import com.sena.utils.TypeFaceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by MrClawSs on 10/8/2017.
 */

public class StartFragment extends Fragment {

    private static final long MOVE_DEFAULT_TIME = 1000;
    private static final long FADE_DEFAULT_TIME = 300;

    @BindView(R.id.sena_logo_start) ImageView logo;
    @BindView(R.id.triggerMenu) ImageView triggerMenu;

    @BindView(R.id.sena_start_message) TextView startMessage;
    @BindView(R.id.sena_start_realm) TextView realmMessage;
    @BindView(R.id.sena_start_interpret) TextView interpretMessage;

    @BindView(R.id.sena_start_realm_button) Button realmButton;
    @BindView(R.id.sena_start_interpret_button) Button interpretButton;


    public static StartFragment getInstance()
    {
        return new StartFragment();
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_start, container, false);
        ButterKnife.bind(this,view);

        startMessage.setTypeface(TypeFaceUtils.create(getActivity()).getRegularFace());
        realmMessage.setTypeface(TypeFaceUtils.create(getActivity()).getLightFace());
        interpretMessage.setTypeface(TypeFaceUtils.create(getActivity()).getLightFace());

        realmButton.setTypeface(TypeFaceUtils.create(getActivity()).getMediumFace());
        interpretButton.setTypeface(TypeFaceUtils.create(getActivity()).getMediumFace());

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        triggerMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppActivity.openCloseDrawer();
            }
        });
    }

    @OnClick(R.id.sena_start_interpret_button)
    public void navigateToInterpretation(Button button){


        FragmentTransaction firstTransaction = getActivity().getSupportFragmentManager().beginTransaction();


        //EXIT
        Fade exitFade = new Fade();
        exitFade.setDuration(FADE_DEFAULT_TIME);
        this.setExitTransition(exitFade);


        firstTransaction.replace(R.id.mainContentLayout,InterpretFragment.getInstance())
                .setTransition(R.transition.slide_and_changebounds)
                .addToBackStack("inter")
                .commitAllowingStateLoss();
    }
}
