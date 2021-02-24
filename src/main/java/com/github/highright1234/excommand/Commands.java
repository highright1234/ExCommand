package com.github.highright1234.excommand;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.github.highright1234.excommand.Functions.writeAndSave;

public class Commands implements CommandExecutor {

    private void printHelp(CommandSender sender,String helpType) {
        if (helpType == null) {
            sender.sendMessage(ChatColor.RED+"/ex <create/remove/reload>");
        } else if (helpType.equals("create")) {
            sender.sendMessage(ChatColor.RED+"/ex create <>");
        }
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        switch (args.length) {
            case 0:
                printHelp(sender, null);
            case 1:
                printHelp(sender, args[0]);
            ScoreboardManager manager = Bukkit.getScoreboardManager();
            Scoreboard board = Objects.requireNonNull(manager).getNewScoreboard();
            Objective objective = board.registerNewObjective(args[1], args[2], args[3]);

            writeAndSave("name", args[4]);
        }
        return true;
    }
}
