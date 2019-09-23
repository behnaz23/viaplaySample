package com.viaplay.utils;

import java.net.MalformedURLException;
import java.net.URL;

public class SectionUtils {

    public static String extractSectionUri(String url) {
        try {
            URL urlObject = new URL(url);
            return urlObject.getFile().replace("{?dtg}", "");
        } catch (MalformedURLException e) {
            return url;
        }
    }
}
