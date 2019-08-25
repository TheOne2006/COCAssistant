package com.xingyu.util.persistence;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.xingyu.bean.Config;
import com.xingyu.util.adb.AdbUtil;

import java.io.*;

public class ConfigPersistenceUtil {
    private static final File JSON = new File(AdbUtil.DIC, "config.json");

    static {
        if (!JSON.exists()) {
            try {
                JSON.createNewFile();
            } catch (IOException ignored) {
            }
        }
    }

    public static void persistence(Config config) throws FileNotFoundException {
        PrintStream printStream = new PrintStream(JSON);
        String json = new Gson().toJson(config);
        printStream.println(json);
        printStream.close();
    }

    public static Config rePersistence() throws FileNotFoundException {
        JsonReader jsonReader = new JsonReader(new FileReader(JSON));
        return new Gson().fromJson(jsonReader, Config.class);
    }
}
