package com.luseen.yandexsummerschool.ui.activity.root;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.jakewharton.rxbinding.support.v4.view.RxViewPager;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationItem;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView;
import com.luseen.luseenbottomnavigation.BottomNavigation.OnBottomNavigationItemClickListener;
import com.luseen.yandexsummerschool.R;
import com.luseen.yandexsummerschool.base_mvp.api.ApiActivity;
import com.luseen.yandexsummerschool.ui.adapter.MainPagerAdapter;

import butterknife.BindView;
import rx.Subscription;

public class RootActivity extends ApiActivity<RootActivityContract.View, RootActivityContract.Presenter>
        implements RootActivityContract.View,
        OnBottomNavigationItemClickListener {

    @BindView(R.id.main_view_pager)
    ViewPager mainViewPager;

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private Subscription viewPagerSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpBottomNavigation();
        setUpViewPager();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
    }

    private void setUpViewPager() {
        MainPagerAdapter pagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        mainViewPager.setAdapter(pagerAdapter);
        mainViewPager.setOffscreenPageLimit(3);
        viewPagerSubscription = RxViewPager.pageSelections(mainViewPager)
                .subscribe(bottomNavigationView::selectTab);
    }

    private void setUpBottomNavigation() {
        BottomNavigationItem translationTab = new BottomNavigationItem("Translate", R.color.blue, R.drawable.ic_tab_translate);
        BottomNavigationItem favTab = new BottomNavigationItem("Favourite", R.color.blue, R.drawable.ic_tab_fav);
        BottomNavigationItem settingsTab = new BottomNavigationItem("Settings", R.color.blue, R.drawable.ic_tab_settings);
        bottomNavigationView.addTab(translationTab);
        bottomNavigationView.addTab(favTab);
        bottomNavigationView.addTab(settingsTab);
        bottomNavigationView.isColoredBackground(false);
        //int activeColor = ContextCompat.getColor(this, R.color.colorPrimary);
        int activeColor = Color.GREEN;
        bottomNavigationView.setItemActiveColorWithoutColoredBackground(activeColor);
        bottomNavigationView.setOnBottomNavigationItemClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (viewPagerSubscription != null) {
            viewPagerSubscription.unsubscribe();
        }
    }

    @NonNull
    @Override
    public RootActivityContract.Presenter createPresenter() {
        return new RootActivityPresenter();
    }

    @Override
    public void onNavigationItemClick(int index) {
        mainViewPager.setCurrentItem(index);
    }
}