package com.github.highright1234.excommand;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.util.HashMap;

public class Vars {
    public static File configFile = new File("config.yml");
    public static FileConfiguration config;
    public static Gson gsonObj = new Gson();
    public static JsonObject jsonObject = new JsonObject();
    public static String now_language = "en_us";
    public static HashMap<String, String> language = new HashMap<>();

    public static void language_setup() {
        language.clear();
        if (now_language.equals("ko_kr")) {
            language.put("create", "/ec create <목표> <기준> [표시이름]");
            language.put("remove", "/ec remove <목표>");
        } else if (now_language.equals("en_us")) {
            language.put("create", "/ec create <objective> <criteria> [displayName]");
            language.put("remove", "/ec remove <objective>");
        } else {
            language.put("create", "/ec create <objective> <criteria> [displayName]");
            language.put("remove", "/ec remove <objective>");
        }
    }
}
