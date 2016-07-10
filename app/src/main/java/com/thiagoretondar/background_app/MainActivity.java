package com.thiagoretondar.background_app;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListMap;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private UsageStatsManager mUsageStatsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mUsageStatsManager = (UsageStatsManager) this.getSystemService("usagestats"); //Context.USAGE_STATS_SERVICE

        List<CustomUsageStats> listOfApps = getFullList();
    }

    // Start the  service
    public void startNewService(View view) {

        startService(new Intent(this, MyService.class));
    }

    // Stop the  service
    public void stopNewService(View view) {

        stopService(new Intent(this, MyService.class));
    }


    private List<UsageStats> getUsageStatistics() {
        // Get the app statistics since one year ago from the current time.
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -1);

        List<UsageStats> queryUsageStats = mUsageStatsManager.queryUsageStats(
                UsageStatsManager.INTERVAL_DAILY,
                cal.getTimeInMillis(),
                System.currentTimeMillis());

        return queryUsageStats;
    }

    public List<CustomUsageStats> getFullList() {

        List<UsageStats> usageStatsList = getUsageStatistics();
        List<CustomUsageStats> customUsageStatsList = new ArrayList<>();
        PackageManager pm = this.getPackageManager();

        for (int i = 0; i < usageStatsList.size(); i++) {
            CustomUsageStats customUsageStats = new CustomUsageStats();
            customUsageStats.setUsageStats(usageStatsList.get(i));

            String packageName = customUsageStats.getUsageStats().getPackageName();

            try {
                Drawable appIcon = pm.getApplicationIcon(packageName);
                ApplicationInfo applicationInfo = pm.getApplicationInfo(packageName, 0);
                String appName = pm.getApplicationLabel(applicationInfo).toString();
                customUsageStats.setAppIcon(appIcon);
                customUsageStats.setAppName(appName);
            } catch (PackageManager.NameNotFoundException e) {
                Log.w(TAG, String.format("App Icon is not found for %s",
                        customUsageStats.getUsageStats().getPackageName()));
                customUsageStats.setAppIcon(this.getDrawable(R.drawable.ic_default_app_launcher));
            }
            customUsageStatsList.add(customUsageStats);
        }
        return customUsageStatsList;
    }

}
