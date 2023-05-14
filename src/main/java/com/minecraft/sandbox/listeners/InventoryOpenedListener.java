package com.minecraft.sandbox.listeners;

import com.minecraft.mobs.npcs.Elf;
import net.citizensnpcs.api.CitizensAPI;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;

import java.util.Objects;

@SuppressWarnings("deprecation")
public class InventoryOpenedListener implements Listener {
  private static final String CHEST_NAME = "Prison";
  // -----------------------
  @EventHandler
  public void onChestOpened(InventoryOpenEvent event) {
    if (event.getInventory().getHolder() != null && event.getInventory().getType() == InventoryType.CHEST) {
      if (Objects.equals(event.getView().getTitle(), CHEST_NAME) && event.getInventory().getLocation() != null) {
        if (!Elf.getEntity().isSpawned()) event.setCancelled(true); // close inventory immediately if NPC is about to spawn
        Elf.spawn(event.getInventory().getLocation().add(0, 0, -1), event.getPlayer().getWorld());
        Elf.setTarget(event.getPlayer().getLocation());
      }
    }
  }
}
