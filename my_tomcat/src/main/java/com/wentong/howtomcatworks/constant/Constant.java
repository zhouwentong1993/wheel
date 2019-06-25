package com.wentong.howtomcatworks.constant;

import java.io.File;

public class Constant {

    private Constant(){}

    public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "my_tomcat" + File.separator + "WEB-ROOT";

}
