package com.github.highright1234.excommand;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scoreboard.ScoreboardManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

import static com.github.highright1234.excommand.Functions.languageSetting;

public class Vars {
    public static ScoreboardManager manager = Bukkit.getScoreboardManager();
    public static File configFile = new File(ExCommand.getPlugin(ExCommand.class).getDataFolder(), "config.yml");
    public static FileConfiguration config;
    public static Gson gsonObj = new Gson();
    public static JsonObject jsonObject = new JsonObject();
    public static String now_language = "en_us";
    public static File objectiveDataFileDirectory = new File(ExCommand.getPlugin(ExCommand.class).getDataFolder(),"objectiveData.json");
    public static ArrayList<String> events = new ArrayList<>();
    public static HashMap<String, String> language = new HashMap<>();
    public static HashMap<String, String> objectiveData;

    public static void setup() {
        config = ExCommand.getPlugin(ExCommand.class).getConfig();
        languageSetting();
        languageInit();
        reloadObjectData();
    }
    public static void reloadObjectData() {
        Gson gson = new Gson();
        try {
            JsonReader reader = new JsonReader(new FileReader(objectiveDataFileDirectory));
            objectiveData = gson.fromJson(reader, HashMap.class);
            if (objectiveData==null) {
                objectiveData = new HashMap<>();
            }
        } catch (FileNotFoundException e) {
            objectiveData = new HashMap<>();
        }
    }



    public static void languageInit() {
        language.clear();
        if (now_language.equals("ko_kr")) {
            language.put("add", "/ec add <오브젝티브> <기준> [표시이름]");
            language.put("remove", "/ec remove <오브젝티브>");
            language.put("reloadSuccessfully", "리로드가 성공적으로 완료되었습니다!");
            language.put("reloadFailed", "리로드를 실패하였습니다");
            language.put("addComplete", "성공적으로 오브젝티브를 만들었습니다!");
            language.put("removeComplete", "성공적으로 오브젝티브를 제거하였습니다!");
            language.put("objectAlreadyName","이미 있는 오브젝티브 이름입니다!");
            language.put("unknownName", "알수없는 이름입니다");
        } else {
            language.put("add", "/ec add <objective> <criteria> [displayName]");
            language.put("remove", "/ec remove <objective>");
            language.put("reloadSuccessfully", "reload is Successfully Complete!");
            language.put("reloadFailed", "reload is Failed");
            language.put("addComplete", "Completed a add objective!");
            language.put("removeComplete", "Completed a remove objective!");
            language.put("objectAlreadyName","The object name already exists!");
            language.put("unknownName", "unknown Name");
        }
    }
}
