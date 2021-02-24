package com.github.highright1234.excommand;


import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import static com.github.highright1234.excommand.Vars.language;
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
                if (args[0].equals("reload"))
                    if (args[1].equals("config"))
                        Functions.reloadConfig();
//            ScoreboardManager manager = Bukkit.getScoreboardManager();
//            Scoreboard board = Objects.requireNonNull(manager).getNewScoreboard();
//            Objective objective = board.registerNewObjective(args[1], args[2], args[3]);
//
//            writeAndSave("name", args[4]);
        }
        return true;
    }
}
