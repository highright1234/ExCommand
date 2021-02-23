package com.github.highright1234.excommand;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Vars {
    static Gson gsonObj;
    {
        gsonObj = new Gson();
    }
    static JsonObject jsonObject = new JsonObject();
}
