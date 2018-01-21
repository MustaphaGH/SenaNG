package com.sena.ui.activities;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sena.R;
import com.sena.ui.fragments.StartFragment;
import com.sena.utils.TypeFaceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AppActivity extends AppCompatActivity {

    private static DrawerLayout drawer;
    private static NavigationView navigationViewMenu;

    private FrameLayout mainLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private FragmentManager mFragmentManager;

    private Handler mDelayedTransactionHandler = new Handler();
    private Runnable mRunnable = this::performTransition;
    private Unbinder unbinder;

    @BindView(R.id.menu_realm_button)  TextView realmMenuButton;
    @BindView(R.id.menu_start_button)  TextView startMenuButton;
    @BindView(R.id.menu_settings_button)  TextView settingsMenuButton;
    @BindView(R.id.menu_profile_button)  TextView profileMenuButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentManager = getSupportFragmentManager();
        unbinder = ButterKnife.bind(this, this);

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
        mDrawerToggle = new ActionBarDrawerToggle(this, drawer, null,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        {
            @SuppressLint("NewApi")
            public void onDrawerSlide(View drawerView, float slideOffset)
            {
                super.onDrawerSlide(drawerView, slideOffset);
                float moveFactor = ((navigationViewMenu.getWidth()-50)* slideOffset) * -1;


                mainLayout.setTranslationX(moveFactor);

            }
        };

        drawer.addDrawerListener(mDrawerToggle);
    }

    private void performTransition()
    {
        //TODO: This will make animation after the Transition.
    }

    @OnClick(R.id.closeDrawerButton)
    public void onCloseButtonClicked(){
        openCloseDrawer();
    }



    public  static void openCloseDrawer(){
        if(drawer != null)
            if(drawer.isDrawerOpen(navigationViewMenu))
            drawer.closeDrawer(navigationViewMenu,true);
            else
            drawer.openDrawer(navigationViewMenu,true);
    }

    private void loadInitialFragment()
    {

        FragmentTransaction firstTransaction = mFragmentManager.beginTransaction();
        performTransition();

        firstTransaction.replace(R.id.mainContentLayout,StartFragment.getInstance()).commit();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onBackPressed() {

        if(drawer.isDrawerOpen(navigationViewMenu))
            drawer.closeDrawer(navigationViewMenu,true);
        else
           super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        mDelayedTransactionHandler.removeCallbacks(mRunnable);
    }



}
