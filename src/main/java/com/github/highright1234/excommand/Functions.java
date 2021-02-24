package com.github.highright1234.excommand;


import com.google.gson.JsonObject;

import static com.github.highright1234.excommand.Vars.jsonObject;


public class Functions {
    static void saveJson() {

    }
    static void writeAndSave(String property, String value) {
        jsonObject.addProperty(property, value);
        saveJson();
    }
}
