package com.minecraft.sandbox.listeners;

import com.minecraft.mobs.npcs.Elf;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Objects;

public class NPCRightClickListener implements Listener {
  /**
   * Sends a chat message and opens the Elf's inventory when interacted with right click.
   * @param event
   */
  @EventHandler
  public void onElfInteracted(NPCRightClickEvent event) {
    if (Objects.equals(event.getNPC().getName(), Elf.NAME)) {
      Player player = event.getClicker();
      player.sendMessage(ChatColor.GREEN + Elf.NAME + ": " + ChatColor.WHITE + "*Incomprehensible noises*");
      player.openInventory(Elf.inventory);
    }
  }
}
