package com.thiagoretondar.background_app;

import android.app.usage.UsageStats;
import android.graphics.drawable.Drawable;

/**
 * Created by thiagoretondar on 7/10/16.
 */
public class CustomUsageStats {

    private String appName;
    private UsageStats usageStats;
    private Drawable appIcon;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public UsageStats getUsageStats() {
        return usageStats;
    }

    public void setUsageStats(UsageStats usageStats) {
        this.usageStats = usageStats;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }
}
