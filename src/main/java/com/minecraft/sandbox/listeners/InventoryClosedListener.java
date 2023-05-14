package com.minecraft.sandbox.listeners;

import com.minecraft.mobs.npcs.Elf;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryClosedListener implements Listener {
  /**
   * When Elf inventory is closed and empty, sends a chat message and despawns Elf from world.
   * @param event
   */
  @EventHandler
  public void onElfInventoryClosed(InventoryCloseEvent event) {
    if (event.getView().getTitle().equals("Rewards") && event.getInventory().isEmpty()) {
      event.getPlayer().sendMessage(ChatColor.GREEN + Elf.NAME + ": " + ChatColor.WHITE + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
      Elf.despawn(event.getPlayer().getWorld());
    }
  }
}
