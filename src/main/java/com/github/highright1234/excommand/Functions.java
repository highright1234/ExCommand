package com.github.highright1234.excommand;


import java.io.*;

import static com.github.highright1234.excommand.Vars.jsonObject;

public class Functions {
    static void loadJson() throws FileNotFoundException {
        FileReader fr = new FileReader("");
        jsonObject.getAsJsonObject(fr.toString());
    }
    static void saveJson() {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("sample.txt"), 1);
            bufferedWriter.write(jsonObject.toString());
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static void writeAndSave(String property, String value) {
        jsonObject.addProperty(property, value);
        saveJson();
    }
}
