package com.github.highright1234.excommand;


import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scoreboard.Objective;

import static com.github.highright1234.excommand.Functions.writeAndSave;
import static com.github.highright1234.excommand.Vars.*;

public class Commands implements CommandExecutor {
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
                    try {
                        manager.getMainScoreboard().getObjective(args[1]);
                        objectiveData.remove(args[1]);
                    } catch(NullPointerException e) {
                        sender.sendMessage(ChatColor.RED+language.get("unknownName"));
                    }
                    return true;
                }
                return true;
            case 4:
                if (args[0].equals("add")) {
                    sender.sendMessage(ChatColor.GREEN+args[1]+" "+args[2]+" "+args[3]);
                    sender.sendMessage(objectiveData+"");
                    writeAndSave(args[1], args[2]);
                    try {
                        objective = manager.getMainScoreboard().registerNewObjective(args[1], args[2], args[3]);
                    } catch (IllegalArgumentException e) {
                        sender.sendMessage(ChatColor.RED+"이미 있는 오브젝티브이름입니다!");
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
}
