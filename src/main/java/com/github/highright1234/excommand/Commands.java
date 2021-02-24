package com.github.highright1234.excommand;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.arguments.Argument;
import dev.jorel.commandapi.arguments.GreedyStringArgument;
import org.bukkit.Bukkit;
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
import static com.github.highright1234.excommand.Vars.jsonObject;

public class Commands implements CommandExecutor {

    private void printHelp(CommandSender sender,String helpType) {
        if (helpType == null) {
            sender.sendMessage("asdf");
        } else if (helpType.equals("reload")) {

        }
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = Objects.requireNonNull(manager).getNewScoreboard();
        Objective objective = board.registerNewObjective(args[0], args[1], args[2]);

        writeAndSave("name", args[3]);

        return true;
    }
}
