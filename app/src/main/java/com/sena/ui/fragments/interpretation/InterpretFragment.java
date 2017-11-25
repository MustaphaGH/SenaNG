package com.sena.ui.fragments.interpretation;


import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sena.R;
import com.sena.ui.fragments.BaseFragment;
import com.sena.utils.TypeFaceUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindColor;
import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by MrClawSs on 10/9/2017.
 */

public class InterpretFragment extends BaseFragment {

    @BindView(R.id.tags_message) TextView tagsMessage;
    @BindView(R.id.inter_message) TextView interMessage;
    @BindView(R.id.user_dream) EditText userDream;
    @BindView(R.id.inter_button) Button interButton;
    @BindView(R.id.tags_button) Button tagButton;
    @BindView(R.id.sena_logo_inter) ImageView logo;
    @BindView(R.id.toolbarTitle) TextView toolbarTitle;

    @BindColor(R.color.clouds)
    public ColorStateList cloudsColor;
    @BindDrawable(R.drawable.button_round_solid)
    public Drawable buttonBackgoud;

    private Unbinder unbinder;

    private List<Integer> list = new ArrayList<>();



    public static InterpretFragment getInstance()
    {
        return new InterpretFragment();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_interpret, container, false);
        ButterKnife.bind(this,root);
        unbinder = ButterKnife.bind(this, root);


        interMessage.setTypeface(TypeFaceUtils.create(getActivity()).getRegularFace());
        tagsMessage.setTypeface(TypeFaceUtils.create(getActivity()).getLightFace());
        interButton.setTypeface(TypeFaceUtils.create(getActivity()).getLightFace());
        tagButton.setTypeface(TypeFaceUtils.create(getActivity()).getLightFace());
        toolbarTitle.setTypeface(TypeFaceUtils.create(getActivity()).getRegularFace());
        userDream.setTypeface(TypeFaceUtils.create(getActivity()).getLightFace());


        initToolbar();

        return root;
    }


    @OnClick(R.id.sena_logo_inter)
    public void clickLogo(ImageView image){

    }


    @Override
    public void onToolbarBack() {
        getParent().onBackPressed();
    }
}
