package com.github.highright1234.excommand;


import org.bukkit.plugin.java.JavaPlugin;
import java.util.Objects;

import static com.github.highright1234.excommand.Functions.languageSetting;
import static com.github.highright1234.excommand.Vars.config;
import static com.github.highright1234.excommand.Vars.language_setup;

public final class ExCommand extends JavaPlugin {

    @Override
    public void onEnable() {
        config = getConfig();
        languageSetting();
        language_setup();
        Objects.requireNonNull(getCommand("excommand")).setExecutor(new Commands());
        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        this.saveDefaultConfig();
        // Plugin shutdown logic
    }
}