package com.zl.demo.config;

import java.io.IOException;

/**
 * 项目启动类
 */
public class MyCommandRunner{

    public static void LoginBrowser() {

        int port = 8080;
        try {
            Runtime.getRuntime().exec("cmd /c start http://localhost:" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
