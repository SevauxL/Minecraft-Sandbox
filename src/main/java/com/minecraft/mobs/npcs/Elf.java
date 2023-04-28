package com.minecraft.mobs.npcs;

import com.minecraft.sandbox.utils.MathUtils;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.trait.SkinTrait;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

/**
 * The Elf NPC Class.
 */
public final class Elf {
  // NPC settings -----------------------------------
  public static final String NAME = "Lutin";
  static NPC entity = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, NAME);
  // Skin settings ----------------------------------
  // Taken from https://fr.namemc.com/minecraft-skins
  static String signature = "HmFEtuBGnofJPHhxplFZDDUiT00dYNOffLmsokI4cSW5oa1aofeJpEo+SmBPzg0rVk05Lv8o0K2CZiBeqz/fa2w+eGfVNRnPrUjDlaSug4h6qa50zSWclkmfdCeXkngWDtY43/QcnB5x5W0uJ7WDxGef3F1j3lN0Qo5rJv2cTB0PtlWEu47rxZrm+IS1Da5H98HaaGxd6hIZldleWZsLQ8cn7mAk69YjzOxJX+nHglW/kmv8Fu3NpmA+1/Ja+Gi3VBAu9Ar2SbHa2hu7uvgRWHc3Ee4JkQ9Y/+1Lu03dl7tLFiH7BElDrMPgZJJhOvcwCQhmssUFa7KM/Oi2RYwrG9llhzwNOHjna8etw8IZf7nDy/1S6bBC2iyfz9SZvkgEauBnD1b/uieqc3SrIElNouA4YQpNMj8FH1ssGIwKktzQzICcvvmQZHhaEJhqsclXV24QKHVlzODUngUrJs3pGzy5gW7TRn9EBsvQn70bb47UvI2iSfkG3TQjx2e1GdgUft/XjVuKaR7cTwEokFXoAs4BTl4JNlXVMnofQaTPCWYnv9r3sUWpYuzEO/RSKq7hRyekE81KjICsExsqgSQWz5HNDS6gVp4wjd82tGwfbU11f20Y5HMwj5/l126G5maQPO7XUWaMuX8cyERWYhGr763eITqFpB2YmJupEoBJw6A=";
  static String value = "ewogICJ0aW1lc3RhbXAiIDogMTYyNTI1NjI1MDQ2MSwKICAicHJvZmlsZUlkIiA6ICIwYTUzMDU0MTM4YWI0YjIyOTVhMGNlZmJiMGU4MmFkYiIsCiAgInByb2ZpbGVOYW1lIiA6ICJQX0hpc2lybyIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9mNWM3MjUwZWVlMTYyYjE2NTk1NjE3MGNmY2JlMjc1NjYwNDAxOGYyNDkwODVhZTlkNjQ4ZDFmNDIwNzhlMDA1IgogICAgfQogIH0KfQ==";
  // Inventory settings -----------------------------
  public static final Inventory inventory = Bukkit.createInventory(null, 9, "Rewards");
  static Random rng = new Random();
  // -----------------------

  /**
   * Spawns the Elf NPC into the world. Also plays a visual/audio cue.
   * @param location location at which to spawn
   * @param world current world
   */
  public static void spawn(Location location, World world) {
    if (!entity.isSpawned()) {
      entity.setName(NAME);
      SkinTrait skin = entity.getOrAddTrait(SkinTrait.class);
      skin.setSkinPersistent(NAME, signature, value); // sets NPC skin
      setInventory(); // fill NPC inventory with random items

      world.playEffect(location, Effect.POTION_BREAK, 3);
      world.playSound(location, Sound.BLOCK_NOTE_BLOCK_CHIME, 100, 2);

      entity.spawn(location);
    }
  }

  /**
   * Plays a visual/audio cue, then despawns the NPC.
   * @param world current world
   */
  public static void despawn(World world) {
    if (entity.isSpawned()) {
      world.playEffect(entity.getEntity().getLocation(), Effect.MOBSPAWNER_FLAMES, 3);
      world.playSound(entity.getEntity().getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 100, 2);
      entity.despawn();
    }
  }

  /**
   * Sets a path from the current NPC location to the new one, and travels to it.
   * @param location location to travel to
   */
  public static void setTarget(Location location) {
    if (entity.isSpawned()) {
      entity.faceLocation(location);
      // entity.getNavigator().setTarget(location); // FIXME: not working, it instantly teleports.
    }
  }

  /**
   * Generates the NPC inventory. Values are as follows:
   * - Diamond Sword (1): 25% chance
   * - Shield (1): 50% chance
   * - Bow (1): 75% chance
   * - Leather Boots (1): 90% chance
   * - Arrows (up to 50): 100% chance
   * - Map (1): 75% chance
   * - Saddle (1): 90% chance
   */
  public static void setInventory() {
    inventory.setContents(new ItemStack[]{
            new ItemStack(Material.DIAMOND_SWORD, MathUtils.getRandomAmount(rng, 100, 75, 1)),
            new ItemStack(Material.SHIELD, MathUtils.getRandomAmount(rng, 10, 5, 1)),
            new ItemStack(Material.BOW, MathUtils.getRandomAmount(rng, 100, 25, 1)),
            new ItemStack(Material.LEATHER_BOOTS, MathUtils.getRandomAmount(rng, 100, 10, 1)),
            new ItemStack(Material.ARROW, rng.nextInt(50)),
            new ItemStack(Material.MAP, MathUtils.getRandomAmount(rng, 100, 25, 1)),
            new ItemStack(Material.SADDLE, MathUtils.getRandomAmount(rng, 10, 1, 1))
    });
  }

  // -----------------------

  private Elf() { throw new IllegalStateException(Elf.class.getName() + " is an utility class and should not be instantiated."); }
}
