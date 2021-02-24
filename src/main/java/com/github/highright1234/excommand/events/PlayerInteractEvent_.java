package com.github.highright1234.excommand.events;

import com.github.highright1234.excommand.EventInterface;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;

public class PlayerInteractEvent_ implements EventInterface, Listener {
    private HashMap<Player, Boolean> hashMap = new HashMap<>();
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        hashMap.put(e.getPlayer(), true);
}

    @Override
    public HashMap<Player, Boolean> onEvent() {
        return hashMap;
    }

}
