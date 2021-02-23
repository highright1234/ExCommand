package com.github.highright1234.excommand;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;



import static com.github.highright1234.excommand.Vars.jsonObject;
public class Commands implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective objective = board.registerNewObjective(args[0], args[1], args[2]);
        
        jsonObject.addProperty("name", "makesomething");
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("sample.txt"), 1);
            bufferedWriter.write(jsonObject.toString());
            bufferedWriter.close();
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}