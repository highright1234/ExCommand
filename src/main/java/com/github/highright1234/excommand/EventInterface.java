package com.github.highright1234.excommand;

import org.bukkit.entity.Player;

import java.util.HashMap;

public interface EventInterface {
    HashMap<Player, Boolean> onEvent();
}