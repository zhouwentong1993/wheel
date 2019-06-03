package com.wentong.howtomcatworks.utils;

import org.junit.Test;

public class StringManagerTest {

    @Test
    public void testGetString() {
        StringManager manager = StringManager.getManager("com.wentong.howtomcatworks.utils");
        String test = manager.getString("httpConnector.alreadyStarted");
        System.out.println(test);
    }

}
