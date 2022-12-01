package com.magicdeveloper.advertisementunity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.unity3d.ads.IUnityAdsInitializationListener;
import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.banners.IUnityBannerListener;
import com.unity3d.services.banners.UnityBanners;

public class MainActivity extends AppCompatActivity implements IUnityAdsInitializationListener {

    MainActivity mContext = MainActivity.this;
    String GameID = "4981145"; /* ID Application */
    Boolean TestMode = true; /* Mode Testing */
    String Banner = "Banner_Android"; /* ID Banner */
    String Interstitial = "Interstitial_Android"; /* ID Interstitial */
    String Rewarded = "Rewarded_Android"; /* ID Video Rewarded */
    MaterialButton ButtonBanner, ButtonInterstitial, ButtonRewarded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialize the SDK:
        UnityAds.initialize(getApplicationContext(), GameID, TestMode, this);

        ButtonBanner = findViewById(R.id.button_ad_banner);
        ButtonInterstitial = findViewById(R.id.button_ad_interstitial);
        ButtonRewarded = findViewById(R.id.button_ad_rewarded);

        ButtonBanner.setOnClickListener(view -> initUnityBanner());
        ButtonInterstitial.setOnClickListener(view -> initUnityInterstitial());
        ButtonRewarded.setOnClickListener(view -> initUnityRewarded());
    }

    @Override
    public void onInitializationComplete() {
        initUnityInterstitial();
    }

    @Override
    public void onInitializationFailed(UnityAds.UnityAdsInitializationError error, String message) {
        Toast.makeText(mContext, "Unity Ads initialization failed with error: [" + error + "] " + message, Toast.LENGTH_SHORT).show();
    }

    /* Unity Ads */
    private void initUnityBanner() {
        IUnityBannerListener mBanner = new IUnityBannerListener() {
            @Override
            public void onUnityBannerLoaded(String s, View view) {
                ((ViewGroup) findViewById(R.id.banner_ad_view)).removeView(view);
                ((ViewGroup) findViewById(R.id.banner_ad_view)).addView(view);
            }

            @Override
            public void onUnityBannerUnloaded(String s) {

            }

            @Override
            public void onUnityBannerShow(String s) {

            }

            @Override
            public void onUnityBannerClick(String s) {

            }

            @Override
            public void onUnityBannerHide(String s) {

            }

            @Override
            public void onUnityBannerError(String s) {

            }
        };
        UnityBanners.setBannerListener(mBanner);
        UnityBanners.loadBanner(MainActivity.this, Banner);
    }


    private void initUnityInterstitial() {
        IUnityAdsListener mInterstitial = new IUnityAdsListener() {
            @Override
            public void onUnityAdsReady(String s) {
                // ready
            }

            @Override
            public void onUnityAdsStart(String s) {
                // start
            }

            @Override
            public void onUnityAdsFinish(String s, UnityAds.FinishState finishState) {
                // finish
            }

            @Override
            public void onUnityAdsError(UnityAds.UnityAdsError unityAdsError, String s) {
                // error
            }
        };
        UnityAds.setListener(mInterstitial);
        UnityAds.load(Interstitial);

        if (UnityAds.isReady(Interstitial)) {
            UnityAds.show(MainActivity.this, Interstitial);
        }
    }

    private void initUnityRewarded() {
        IUnityAdsListener mRewarded = new IUnityAdsListener() {
            @Override
            public void onUnityAdsReady(String s) {

            }

            @Override
            public void onUnityAdsStart(String s) {

            }

            @Override
            public void onUnityAdsFinish(String s, UnityAds.FinishState finishState) {
                if (finishState.equals(UnityAds.FinishState.COMPLETED)) {
                    // Video Completed
                } else if (finishState == UnityAds.FinishState.SKIPPED) {
                    // Video Skipped
                } else if (finishState == UnityAds.FinishState.ERROR) {
                    // Error
                }
            }

            @Override
            public void onUnityAdsError(UnityAds.UnityAdsError unityAdsError, String s) {
                Toast.makeText(MainActivity.this, "Error " + unityAdsError, Toast.LENGTH_SHORT).show();
            }
        };
        UnityAds.setListener(mRewarded);
        UnityAds.load(Rewarded);
        if (UnityAds.isReady(Rewarded)) {
            UnityAds.show(MainActivity.this, Rewarded);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        UnityBanners.loadBanner(mContext, Banner);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}