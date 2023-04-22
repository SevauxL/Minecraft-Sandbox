package com.minecraft.sandbox.listeners;

import com.minecraft.sandbox.utils.ChatUtils;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.ArrayList;

public class BlockBreakListener implements Listener {
    /**
     * Checks if the destroyed block is a DIAMOND block, and moves it according to the player's position.
     * If the block below the new position is a GOLD block, plays an animation.
     * @param event
     */
    @EventHandler
    public void moveDiamondBlock(BlockBreakEvent event) {
        Block block = event.getBlock();
        Player player = event.getPlayer();

        if (block.getType() == Material.DIAMOND_BLOCK) {
            Location newLocation = setBlockDirection(player.getFacing(), block.getLocation()); // process new location

            if (newLocation.getBlock().getType() == Material.AIR) { // if new location is a valid slot
                while (checkForBlockRelative(newLocation, BlockFace.DOWN, Material.AIR)) { newLocation.add(0, -1f, 0); } // check if space below is also valid and keep moving down until it's not

                if (checkForBlockRelative(newLocation, BlockFace.DOWN, Material.WATER, Material.LAVA)) { // cancel event if new location is illegal space
                    player.getWorld().playSound(newLocation, Sound.BLOCK_NOTE_BLOCK_BASS, 100, 0);
                    return;
                }

                newLocation.getBlock().setType(Material.DIAMOND_BLOCK); // set block at new location

                if (checkForBlockRelative(newLocation, BlockFace.DOWN, Material.GOLD_BLOCK)) { // check if the block below new location is the goal block
                    player.getWorld().playEffect(newLocation, Effect.DRAGON_BREATH, 0);
                    player.getWorld().playSound(newLocation, Sound.BLOCK_NOTE_BLOCK_CHIME, 100, 2);
                    player.sendMessage(ChatUtils.printRainbowText("GOAL!")); // if so, play sfx/vfx and print to chat
                }
            } else { // if not a valid slot, cancel event
                player.getWorld().playSound(newLocation, Sound.BLOCK_NOTE_BLOCK_BASS, 100, 0);
            }
        }
    }

    // -----------------------

    /**
     * Checks every given Material object at the given BlockFace relative for the specified Location
     * @param location Current block location
     * @param face BlockFace to check
     * @param blockTypes Material(s) to check for
     * @return true if there is a match, false otherwise
     */
    private boolean checkForBlockRelative(Location location, BlockFace face, Material... blockTypes) {
        ArrayList<Boolean> checkList = new ArrayList<>();

        for (Material blockType : blockTypes) {
            checkList.add(location.getBlock().getRelative(face).getType() == blockType);
        }

        return checkList.contains(true);
    }

    /**
     * Computes a new location according to a given BlockFace. (Moves it one block ahead)
     * @param face BlockFace to check
     * @param location Location to compute
     * @return The new location
     */
    private Location setBlockDirection(BlockFace face, Location location) { // FIXME: UP and DOWN not working for some reason. getFacing() only works with cardinals.
        switch (face) { // move location one block ahead of player facing
            case SOUTH -> location.add(0, 0, 1f);
            case NORTH -> location.add(0, 0, -1f);
            case EAST -> location.add(1f, 0, 0);
            case WEST -> location.add(-1f, 0, 0);
            default -> { return location; }
        }
        return location;
    }
}
