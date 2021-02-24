package com.github.highright1234.excommand;


import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scoreboard.Objective;

import java.util.Objects;

import static com.github.highright1234.excommand.Vars.*;
import static com.github.highright1234.excommand.Functions.writeAndSave;

public class Commands implements CommandExecutor {

    private void printHelp(CommandSender sender,String helpType) {
        if (helpType == null) {
            sender.sendMessage(ChatColor.RED+"/ec <create/remove/reload>");
        } else if (helpType.equals("create")) {
            sender.sendMessage(ChatColor.RED+language.get("create"));
        } else if (helpType.equals("remove")) {
            sender.sendMessage(ChatColor.RED+language.get("remove"));
        } else if (helpType.equals("reload")) {
            sender.sendMessage(ChatColor.RED + "/ec reload <all/config>");
        } else {
            sender.sendMessage(ChatColor.RED+"/ec <create/remove/reload>");
        }
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        switch (args.length) {
            case 0:
                printHelp(sender, null);
                return true;
            case 1:
                printHelp(sender, args[0]);
                return true;
            case 2:
                if (args[0].equals("reload")) {
                    if (args[1].equals("config")) {
                        if (Functions.reloadConfig()) {
                            sender.sendMessage(ChatColor.GREEN + language.get("reloadSuccessfully"));
                        } else {
                            sender.sendMessage(ChatColor.GREEN + language.get("reloadFailed"));
                        }
                    }
                } else if (args[0].equals("remove")) {
                    Objects.requireNonNull(manager.getMainScoreboard().getObjective(args[1])).unregister();

                    return true;
                }
                return true;
            case 4:
                if (args[0].equals("add")) {
                    writeAndSave(args[1], args[2]);
                    Objective objective = manager.getMainScoreboard().registerNewObjective(args[1], args[2], args[3]);
                    sender.sendMessage(objective.getCriteria());

                    return true;
                }
            default:
                printHelp(sender, null);
        }
        return true;
    }
}
