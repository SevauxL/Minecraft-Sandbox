package com.minecraft.sandbox.listeners;

import com.minecraft.mobs.npcs.Elf;
import org.bukkit.block.Chest;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;

import java.util.Objects;

public class InventoryOpenedListener implements Listener {
    @EventHandler
    @SuppressWarnings("deprecation") // no alternatives to getCustomName
    public void onChestOpened(InventoryOpenEvent event) {
        if (event.getInventory().getHolder() instanceof Chest chest) { // FIXME: Not working with DoubleChest - for some reason it doesn't have a method to get name
             if (Objects.equals(chest.getCustomName(), "Prison")) {
                 HumanEntity player = event.getPlayer();

                 Elf.spawn(chest.getLocation().add(0, 0, -1), player.getWorld());
                 Elf.setTarget(player.getLocation());
             }
        }
    }
}
