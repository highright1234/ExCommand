package com.github.highright1234.excommand;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.Argument;
import dev.jorel.commandapi.arguments.GreedyStringArgument;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

import static com.github.highright1234.excommand.Commands.commands;

public final class ExCommand extends JavaPlugin {

    @Override
    public void onLoad() {
        CommandAPI.onLoad(true); //Load with verbose output
        commands();
        List<Argument> arguments = new ArrayList<>();
        arguments.add(new GreedyStringArgument("message"));
        new CommandAPICommand("broadcastmsg")
                .withArguments(arguments)                     // The arguments
                .withAliases("broadcast", "broadcastmessage") // Command aliases
                .executes((sender, args) -> {
                    String message = (String) args[0];
                    Bukkit.getServer().broadcastMessage(message);
                }).register();
    }

    @Override
    public void onEnable() {
        CommandAPI.onEnable(this);
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
