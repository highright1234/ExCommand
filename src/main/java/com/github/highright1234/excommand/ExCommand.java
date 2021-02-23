package com.github.highright1234.excommand;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class ExCommand extends JavaPlugin {

    @Override
    public void onEnable() {
        Objects.requireNonNull(getCommand("excommand")).setExecutor(new Commands());
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
