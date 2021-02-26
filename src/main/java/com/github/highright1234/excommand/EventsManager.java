package com.github.highright1234.excommand;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;

import java.util.ArrayList;
import java.util.HashMap;

import static com.github.highright1234.excommand.Functions.removeAndSave;
import static com.github.highright1234.excommand.Vars.manager;
import static com.github.highright1234.excommand.Vars.objectiveData;

public class EventsManager {
    public static void eventMethod(String eventName, Player player) {
        ArrayList<String> objectives = getKey(objectiveData, eventName);
        if (objectives.size()!=0) {
            Objective objective;
            for (String s : objectives) {
                try {
                    objective = manager.getMainScoreboard().getObjective(s);
                    if (objective != null) {
                        Score score = objective.getScore(player.getName());
                        score.setScore(score.getScore()+1);
                    }
                } catch (NullPointerException e){
                    removeAndSave(s);
                }
            }
        }
        // TODO
    }
    public static void addEvent(String eventName) {
        Vars.events.add(eventName);
    }

    public static <K, V> ArrayList<K> getKey(HashMap<K, V> map, V value) {
        ArrayList<K> arrayList = new ArrayList<>();
        if (map == null) return arrayList;
        for (K key : map.keySet()) {
            if (value.equals(map.get(key))) {
                arrayList.add(key);
            }
        }
        return arrayList;
    }
}