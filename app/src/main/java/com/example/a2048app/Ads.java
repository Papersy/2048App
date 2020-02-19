package com.example.a2048app;

import com.example.a2048app.mainGameWindow.Statistic;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

public class Ads implements RewardedVideoAdListener {
    private int index;
    private ShopActivity activity;
    private RewardedVideoAd mRewardedVideoAd;

    Ads(ShopActivity activity){
        this.activity = activity;

        MobileAds.initialize(activity, "ca-app-pub-3010679320408675~3651137414");
        // Use an activity context to get the rewarded video instance.
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(activity);
        mRewardedVideoAd.setRewardedVideoAdListener(this);

        loadRewardedVideoAd();
    }

    private void loadRewardedVideoAd() {
        mRewardedVideoAd.loadAd("ca-app-pub-3010679320408675/5289676237", new AdRequest.Builder().build());//ca-app-pub-3010679320408675/5289676237
    }

    void showAds(int index){
        this.index = index;
        try{
            if (mRewardedVideoAd.isLoaded()) {
                mRewardedVideoAd.show();
            }
            else{
                loadRewardedVideoAd();
            }
        }
        catch (Exception ignored){
        }
    }

    public boolean isLoaded(){
        return mRewardedVideoAd.isLoaded();
    }


    @Override
    public void onRewardedVideoAdLoaded() {
    }

    @Override
    public void onRewardedVideoAdOpened() {
    }

    @Override
    public void onRewardedVideoStarted() {
    }

    @Override
    public void onRewardedVideoAdClosed() {
        loadRewardedVideoAd();
    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
        Statistic statistic = new Statistic(activity);
        switch (index){ // 1 - double, 2 - position, 3 - delete
            case 1:
                statistic.addDoubleCount(1);
                break;
            case 2:
                statistic.addPositionCount(2);
                break;
            case 3:
                statistic.addDeleteCount(3);
                break;
        }


        loadRewardedVideoAd();
    }


    @Override
    public void onRewardedVideoAdLeftApplication() {
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {
    }

    @Override
    public void onRewardedVideoCompleted() {
    }
}
