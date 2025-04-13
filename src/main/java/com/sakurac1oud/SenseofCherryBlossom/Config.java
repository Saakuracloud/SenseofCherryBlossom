package com.sakurac1oud.SenseofCherryBlossom;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

    public static String greeting = "Hello World";
    public static boolean DEFAULT_BATCH_MODE = false;

    public static void synchronizeConfiguration(File configFile) {

        Configuration configuration = new Configuration(configFile);

    }
}
