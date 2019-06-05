package com.wentong.howtomcatworks.ex03.connector.http;
import java.io.File;

public final class Constants {
  public static final String WEB_ROOT =
    System.getProperty("user.dir") + File.separator  + "WEB-ROOT";
  public static final String PACKAGE = "com.wentong.howtomcatworks.ex03.connector.http";
  public static final int DEFAULT_CONNECTION_TIMEOUT = 60000;
  public static final int PROCESSOR_IDLE = 0;
  public static final int PROCESSOR_ACTIVE = 1;
}
