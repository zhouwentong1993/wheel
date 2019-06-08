package com.wentong.howtomcatworks.utils;

import java.util.*;

/**
 * Tomcat 字符串管理
 */
public class StringManager {

    private ResourceBundle resourceBundle;

    private StringManager(String packageName) {
        try {
            resourceBundle = ResourceBundle.getBundle(packageName + ".LocalStrings");
        } catch (MissingResourceException e) {
            System.out.println(e);
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            if (cl != null) {
                try {
                    resourceBundle = ResourceBundle.getBundle(packageName + ".LocalStrings", Locale.getDefault(), cl);
                } catch (MissingResourceException ex2) {
                    // Ignore
                }
            }
        }

    }

    private static Map<String, StringManager> cache = new HashMap<>();

    public static StringManager getManager(String packageName) {
        StringManager stringManager = cache.get(packageName);
        if (stringManager == null) {
            stringManager = new StringManager(packageName);
            cache.put(packageName, stringManager);
            return stringManager;
        } else {
            return stringManager;
        }
    }



    public String getString(String key) {
        Objects.requireNonNull(key, "非法的 key 值");
        try {
            return resourceBundle.getString(key);
        } catch (MissingResourceException mre) {
            return "Cannot find message associated with key '" + key + "'";
        }
    }

    private StringManager(String packageName, Locale locale) {
        String bundleName = packageName + ".LocalStrings";
        try {
            resourceBundle = ResourceBundle.getBundle(bundleName, Locale.JAPANESE,this.getClass().getClassLoader());
        } catch (MissingResourceException ex) {
            // Try from the current loader (that's the case for trusted apps)
            // Should only be required if using a TC5 style classloader structure
            // where common != shared != server
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            if (cl != null) {
                try {
                    resourceBundle = ResourceBundle.getBundle(bundleName, locale, cl);
                } catch (MissingResourceException ex2) {
                    // Ignore
                }
            }
        }
    }

    public static void main(String[] args) {
        StringManager stringManager = new StringManager("com.wentong.howtomcatworks.ex03", Locale.JAPAN);
//        StringManager stringManager = new StringManager("com.wentong.howtomcatworks.ex03", Locale.getDefault());
        String test = stringManager.getString("httpConnector.alreadyStarted");
        System.out.println(test);
    }





}
