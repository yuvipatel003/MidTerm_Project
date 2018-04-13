package yuvraj.midterm_project.data.web;

import android.os.Bundle;

/**
 * Created by yuvi0 on 2018-03-08.
 */

public class WebModel {
    private static final String WEBSITE_NAME = "websiteName";
    private static final String WEBSITE_TYPE = "websiteType";
    private static final String RELEASE = "release";
    private static final String WEBSITE = "website";

    private final Bundle bundle;

    public WebModel(String websiteName, String websiteType, String release, String website) {
        bundle = new Bundle();
        bundle.putString(WEBSITE_NAME, websiteName);
        bundle.putString(WEBSITE_TYPE, websiteType);
        bundle.putString(RELEASE, release);
        bundle.putString(WEBSITE, website);
    }

    public WebModel(Bundle bundle) {
        this.bundle = bundle;
    }

    public String getWebsiteName() {
        return bundle.getString(WEBSITE_NAME, "");
    }

    public String getWebsiteType() {
        return bundle.getString(WEBSITE_TYPE, "");
    }

    public String getRelease() {
        return bundle.getString(RELEASE, "");
    }

    public String getWebsite() {
        return bundle.getString(WEBSITE, "");
    }
}
