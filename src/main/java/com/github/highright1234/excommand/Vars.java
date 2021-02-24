package com.github.highright1234.excommand;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.bukkit.configuration.file.FileConfiguration;

public class Vars {
    static FileConfiguration config;
    static Gson gsonObj = new Gson();
    static JsonObject jsonObject = new JsonObject();
}
