package com.xingyu.util.adb;

import org.im4java.core.ConvertCmd;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class AdbUtil {
    public static final File DIC = new File(System.getProperty("user.dir"));
    public static final File SCREENSHOTS = new File(DIC, "screenshot.png");
    private static final ConvertCmd cmd = new ConvertCmd();

    static {
        if (!SCREENSHOTS.exists()) {
            try {
                SCREENSHOTS.createNewFile();
            } catch (IOException ignored) {
            }
        }
    }

    public static boolean isConnect(String port, String address) throws IOException {
        if ("".equals(address)) {
            Scanner sc = new Scanner(Runtime.getRuntime().exec("adb devices").getInputStream());
            sc.nextLine();
            return sc.hasNext();
        }
        Scanner sc = new Scanner(Runtime.getRuntime().exec("adb connect " + address + ":" + port).getInputStream());
        return !sc.nextLine().startsWith("unable");
    }

    public static void getScreenshots() throws Exception {
        Runtime.getRuntime().exec("adb shell /system/bin/screencap -p /sdcard/screenshot.png").waitFor();
        Runtime.getRuntime().exec("adb pull /sdcard/screenshot.png " + SCREENSHOTS.getAbsolutePath()).waitFor();
    }
}
