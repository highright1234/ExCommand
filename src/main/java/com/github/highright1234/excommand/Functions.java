package com.github.highright1234.excommand;


import org.bukkit.ChatColor;

import java.util.Objects;

import static com.github.highright1234.excommand.Vars.*;

public class Functions {

    public static void saveJson() {

    }

    public static void writeAndSave(String property, String value) {
        jsonObject.addProperty(property, value);
        saveJson();
    }

    public static void languageSetting() {

        if (Objects.equals(config.getString("language"), "en_us")) {
            now_language = "en_us";
            System.out.println(ChatColor.GREEN+"한국어 적용이 완료되었습니다");
        } else if (Objects.equals(config.getString("language"), "ko_kr")) {
            now_language = "ko_kr";
            System.out.println(ChatColor.GREEN+"English has been applied");
        } else {
            System.out.println(ChatColor.RED+config.getString("language") + " language is a don't supported. :(");
            System.out.println(ChatColor.RED+"now language: "+config.getString("language"));
            System.out.println(ChatColor.RED+"supported language: en_us, ko_kr");
        }
    }

    public static void reloadConfig() {
        config = ExCommand.getPlugin(ExCommand.class).getConfig();
        languageSetting();
        language_setup();
    }
}
