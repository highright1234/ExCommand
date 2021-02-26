package com.github.highright1234.excommand;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class Events implements Listener {

    @EventHandler
    public void onMainHandRightClickEvent(PlayerInteractEvent e) {
        if (e.getHand() == EquipmentSlot.HAND) {
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK ) {
                EventsManager.eventMethod("onMainHandRightClickEvent", e.getPlayer());
            } else {
                EventsManager.eventMethod("onMainHandLeftClickEvent", e.getPlayer());
            }
        } else {
            if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
                EventsManager.eventMethod("onOffHandRightClickEvent", e.getPlayer());
            } else {
                EventsManager.eventMethod("onOffHandLeftClickEvent", e.getPlayer());
            }
        }
    }
}
