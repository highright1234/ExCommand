package com.github.highright1234.excommand;


import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

import static com.github.highright1234.excommand.EventsManager.addEvent;
import static com.github.highright1234.excommand.Vars.setup;

public final class ExCommand extends JavaPlugin {
    private void defaultEventsSetting() {
        Bukkit.getPluginManager().registerEvents(new Events(), this);
        addEvent("onMainHandRightClickEvent");
        addEvent("onMainHandLeftClickEvent");
        addEvent("onOffHandRightClickEvent");
        addEvent("onOffHandLeftClickEvent");
    }
    @Override
    public void onEnable() {
        setup();
        defaultEventsSetting();
        Objects.requireNonNull(getCommand("excommand")).setExecutor(new Commands());
        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        this.saveDefaultConfig();
        // Plugin shutdown logic
    }
}