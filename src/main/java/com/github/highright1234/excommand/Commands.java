package com.github.highright1234.excommand;


import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.scoreboard.Objective;

import java.util.ArrayList;
import java.util.List;

import static com.github.highright1234.excommand.Functions.writeAndSave;
import static com.github.highright1234.excommand.Vars.*;

public class Commands implements CommandExecutor, TabCompleter {
    Objective objective;
    private void printHelp(CommandSender sender,String helpType) {
        if (helpType == null) {
            sender.sendMessage(ChatColor.RED+"/ec <add/remove/reload>");
        } else if (helpType.equals("add")) {
            sender.sendMessage(ChatColor.RED+language.get("add"));
        } else if (helpType.equals("remove")) {
            sender.sendMessage(ChatColor.RED+language.get("remove"));
        } else if (helpType.equals("reload")) {
            sender.sendMessage(ChatColor.RED + "/ec reload <all/config>");
        } else {
            sender.sendMessage(ChatColor.RED+"/ec <add/remove/reload>");
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        switch (args.length) {
            case 0:
                printHelp(sender, null);
                return true;
            case 1:
                printHelp(sender, args[0]);
                return true;
            case 2:
                switch (args[0]) {
                    case "reload":
                        if (args[1].equals("config")) {
                            if (Functions.reloadConfig()) {
                                sender.sendMessage(ChatColor.GREEN + language.get("reloadSuccessfully"));
                            } else {
                                sender.sendMessage(ChatColor.GREEN + language.get("reloadFailed"));
                            }
                        }
                        return true;
                    case "remove":
                        try {
                            objective = manager.getMainScoreboard().getObjective(args[1]);
                            if (objective != null) {
                                objective.unregister();
                            }
                            objectiveData.remove(args[1]);
                        } catch (NullPointerException e) {
                            sender.sendMessage(ChatColor.RED + language.get("unknownName"));
                        }
                        return true;
                    case "list":
                        sender.sendMessage(objectiveData + "");
                        return true;
                }
            case 4:
                if (args[0].equals("add")) {
                    writeAndSave(args[1], args[2]);
                    try {
                        objective = manager.getMainScoreboard().registerNewObjective(args[1], args[2], args[3]);
                    } catch (IllegalArgumentException e) {
                        sender.sendMessage(ChatColor.RED+language.get("objectAlreadyName"));
                        return true;
                    }
                    sender.sendMessage(ChatColor.GREEN+language.get("addComplete"));
                    return true;
                }
            default:
                printHelp(sender, null);
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> tab = new ArrayList<>();
        switch (args.length) {
            case 1:
                tab.add("add");
                tab.add("remove");
                tab.add("reload");
                return tab;
            case 2:
                switch (args[0]) {
                    case "add":
                        tab.add("");
                        return tab;
                    case "remove":
                        tab=new ArrayList<>(objectiveData.keySet());
                        return tab;
                    case "reload":
                        tab.add("all");
                        tab.add("config");
                }
            case 3:
                return events;

        }
        tab.add("");
        return tab;
    }
}
