package com.github.highright1234.excommand;


import org.bukkit.plugin.java.JavaPlugin;
import java.util.Objects;

import static com.github.highright1234.excommand.Vars.config;

public final class ExCommand extends JavaPlugin {
    @Override
    public void onEnable() {
        config = this.getConfig();
        config.addDefault("language", "en_us");
        config.options().copyDefaults(true);
        saveConfig();
        Objects.requireNonNull(getCommand("excommand")).setExecutor(new Commands());
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        this.saveDefaultConfig();
        // Plugin shutdown logic
    }
}
