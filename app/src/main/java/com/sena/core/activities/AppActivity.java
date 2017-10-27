package com.sena.core.activities;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.transition.ChangeBounds;
import android.transition.Fade;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.sena.R;
import com.sena.core.fragments.InterpretFragment;
import com.sena.core.fragments.StartFragment;
import com.sena.utils.TypeFaceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AppActivity extends AppCompatActivity {

    private static DrawerLayout drawer;
    private static NavigationView navigationViewMenu;

    private StartFragment startFragment = new StartFragment();

    private static final long MOVE_DEFAULT_TIME = 1000;
    private static final long FADE_DEFAULT_TIME = 300;

    private FrameLayout mainLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private FragmentManager mFragmentManager;

    private Handler mDelayedTransactionHandler = new Handler();
    private Runnable mRunnable = this::performTransition;

    @BindView(R.id.menu_realm_button)  TextView realmMenuButton;
    @BindView(R.id.menu_start_button)  TextView startMenuButton;
    @BindView(R.id.menu_settings_button)  TextView settingsMenuButton;
    @BindView(R.id.menu_profile_button)  TextView profileMenuButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentManager = getSupportFragmentManager();

        drawer = (DrawerLayout)findViewById(R.id.drawer);
        navigationViewMenu = (NavigationView)findViewById(R.id.navigationViewMenu);

        ButterKnife.bind(this,navigationViewMenu);

        mainLayout = (FrameLayout)findViewById(R.id.mainContentLayout);


        realmMenuButton.setTypeface(TypeFaceUtils.create(this).getRegularFace());
        startMenuButton.setTypeface(TypeFaceUtils.create(this).getRegularFace());
        settingsMenuButton.setTypeface(TypeFaceUtils.create(this).getRegularFace());
        profileMenuButton.setTypeface(TypeFaceUtils.create(this).getRegularFace());



        initDrawer();







        loadInitialFragment();

       // mDelayedTransactionHandler.postDelayed(mRunnable, 1000);
    }

    private void initDrawer(){
        /**
         * @Initialisation of the Drawer
         * @Disable  Shadow & DropShadow
         */
        drawer.setScrimColor(getResources().getColor(android.R.color.transparent));
        drawer.setDrawerElevation(0);


        /**
         * @Summaruy: Make the mainLayout move to X position according to the slided drawerMenu
         */
        mDrawerToggle = new ActionBarDrawerToggle(this, drawer, null, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        {
            @SuppressLint("NewApi")
            public void onDrawerSlide(View drawerView, float slideOffset)
            {
                super.onDrawerSlide(drawerView, slideOffset);
                float moveFactor = ((navigationViewMenu.getWidth()-50)* slideOffset) * -1;


                mainLayout.setTranslationX(moveFactor);

            }
        };

        drawer.setDrawerListener(mDrawerToggle);
    }

    private void performTransition()
    {
        Slide slideTransition = new Slide(Gravity.TOP);
        slideTransition.setDuration(1000);

        Slide slideTransitionEnter = new Slide(Gravity.TOP);
        slideTransitionEnter.setDuration(1000);


        Transition changeBoundsTransition = TransitionInflater.from(this).inflateTransition(R.transition.changebounds_with_arcmotion);
        startFragment.setSharedElementEnterTransition(changeBoundsTransition);

        //startFragment.setEnterTransition(slideTransitionEnter);
        //startFragment.setExitTransition(slideTransition);
    }

    @OnClick(R.id.closeDrawerButton)
    public void onCloseButtonClicked(){
        openCloseDrawer();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDelayedTransactionHandler.removeCallbacks(mRunnable);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }


    public  void goToIntere(){



    }

    public  static void openCloseDrawer(){

        if(drawer.isDrawerOpen(navigationViewMenu))
            drawer.closeDrawer(navigationViewMenu,true);
        else
            drawer.openDrawer(navigationViewMenu,true);

    }

    private void loadInitialFragment()
    {

        FragmentTransaction firstTransaction = mFragmentManager.beginTransaction();

        performTransition();

        StartFragment._context = this;
        firstTransaction.replace(R.id.mainContentLayout,startFragment).commit();

    }


}
