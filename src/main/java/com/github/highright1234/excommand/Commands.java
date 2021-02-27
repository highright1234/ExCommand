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
    private void printHelp(CommandSender sender) {
        sender.sendMessage(ChatColor.RED+"/ec <variable/scoreboard/reload>");
    }
    private void scoreboardHelp(CommandSender sender, String helpType) {
            if (helpType.equals("add")) {
                sender.sendMessage(ChatColor.RED+language.get("objectiveAdd"));
            } else if (helpType.equals("remove")) {
                sender.sendMessage(ChatColor.RED+language.get("objectiveRemove"));
        }
    }
    private void reloadHelp(CommandSender sender) {
        sender.sendMessage(ChatColor.RED + "/ec reload <all/config>");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        switch (args.length) {
            case 0:
                printHelp(sender);
                return true;
            case 1:
                if (args[0].equals("scoreboard")) {
                    scoreboardHelp(sender, args[0]);
                    return true;
                } else if (args[0].equals("reload")) {
                    reloadHelp(sender);
                } else {
                        printHelp(sender);
                        return true;
                    }
            case 2:
                if (args[1].equals("config")) {
                    if (Functions.reloadConfig()) {
                        sender.sendMessage(ChatColor.GREEN + language.get("reloadSuccessfully"));
                    } else {
                        sender.sendMessage(ChatColor.GREEN + language.get("reloadFailed"));
                    }
                } else if (args[1].equals("all")) {
                    if (Functions.reloadConfig()) {
                        sender.sendMessage(ChatColor.GREEN + language.get("reloadSuccessfully"));
                    } else {
                        sender.sendMessage(ChatColor.GREEN + language.get("reloadFailed"));
                    }
                }
                return true;
            case 3:
                switch (args[0]) {
                    case "scoreboard":
                        switch (args[1]) {
                            case "remove":
                                try {
                                    objective = manager.getMainScoreboard().getObjective(args[1]);
                                    if (objective != null) {
                                        objective.unregister();
                                    }
                                    objectiveData.remove(args[2]);
                                    sender.sendMessage(ChatColor.GREEN + language.get("removeComplete"));
                                } catch (NullPointerException e) {
                                    sender.sendMessage(ChatColor.RED + language.get("unknownName"));
                                }
                                return true;
                            case "list":
                                sender.sendMessage(objectiveData + "");
                                return true;
                            case "info":
                                objective = manager.getMainScoreboard().getObjective(args[2]);
                                if (objective != null) {
                                    sender.sendMessage("==================================================");
                                    sender.sendMessage(objective.getName());
                                    sender.sendMessage(objectiveData.get(objective.getName()));
                                    sender.sendMessage(objective.getDisplayName());
                                    sender.sendMessage("==================================================");
                                    return true;
                                } else {
                                    sender.sendMessage(objectiveData.get("unknownName"));
                                }
                        }
                }
            case 4:
                if (args[0].equals("scoreboard")) {
                    if (args[1].equals("add")) {
                        writeAndSave(args[2], args[3]);
                        try {
                            objective = manager.getMainScoreboard().registerNewObjective(args[2], "dummy", args[2]);
                        } catch (IllegalArgumentException e) {
                            sender.sendMessage(ChatColor.RED + language.get("objectAlreadyName"));
                            return true;
                        }
                        sender.sendMessage(ChatColor.GREEN + language.get("addComplete"));
                        return true;
                    }
                }
            case 5:
                if (args[0].equals("scoreboard")) {
                    if (args[1].equals("add")) {
                        writeAndSave(args[2], args[3]);
                        try {
                            objective = manager.getMainScoreboard().registerNewObjective(args[2], "dummy", args[4]);
                        } catch (IllegalArgumentException e) {
                            sender.sendMessage(ChatColor.RED + language.get("objectAlreadyName"));
                            return true;
                        }
                        sender.sendMessage(ChatColor.GREEN + language.get("addComplete"));
                        return true;
                    }
                }
            default:
                printHelp(sender);
        }
        return true;
    }

    private String slice_range_add(String s, String arg, int endIndex) {
        if (arg == null) {return s;}
        if (endIndex < 0) endIndex = s.length() + endIndex;
        if (s.substring(0, endIndex).equals(arg)) {
            return s;
        }
        return null;
    }

    private List<String> slice_range_add_arraylist(List<String> s, String arg) {
        List<String> output = new ArrayList<>();
        if (arg.isEmpty()) {
            return s;
        }
        for (String a : s) {
            if (a.length() >= arg.length()) {
                if (slice_range_add(a, arg, arg.length()) != null) {
                    output.add(slice_range_add(a, arg, arg.length()));
                }
            }
        }
        return output;
    }

    private void indexOfAdd(String nowArg, ArrayList<String> arrayList, List<String> list) {
        for (String input: arrayList) {
            if (input.contains(nowArg)) {
                list.add(input);
            }
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> tab = new ArrayList<>();
        switch (args.length) {
            case 1:
                tab.add("scoreboard");
                tab.add("function");
                tab.add("reload");
                return slice_range_add_arraylist(tab, args[0]);
            case 2:
                switch (args[0]) {
                    case "scoreboard":
                        tab.add("add");
                        tab.add("remove");
                        tab.add("reload");
                        tab.add("list");
                        tab.add("info");
                        return slice_range_add_arraylist(tab, args[1]);
                    case "reload":
                        tab.add("all");
                        tab.add("config");
                        return slice_range_add_arraylist(tab, args[1]);
                }
            case 3:
                if (args[0].equals("scoreboard")) {
                    switch (args[1]) {
                        case "add":
                            tab.add("");
                            return tab;
                        case "remove":
                        case "info":
                            return slice_range_add_arraylist(new ArrayList<>(objectiveData.keySet()), args[1]);
                    }
                }
            case 4:
                if (args[0].equals("scoreboard")) {
                    if (args[1].equals("add")) {
                        indexOfAdd(args[3], events, tab);
                        return tab;
                    }
                }

        }
        tab.add("");
        return tab;
    }
}
