package com.minecraft.sandbox;

import com.minecraft.sandbox.commands.HelloCommand;
import com.minecraft.sandbox.listeners.BlockBreakListener;
import com.minecraft.sandbox.listeners.InventoryClosedListener;
import com.minecraft.sandbox.listeners.InventoryOpenedListener;
import com.minecraft.sandbox.listeners.NPCRightClickListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class MinecraftSandbox extends JavaPlugin {
    @Override
    public void onEnable() {
        enableCommands();
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryOpenedListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryClosedListener(), this);
        getServer().getPluginManager().registerEvents(new NPCRightClickListener(), this);
    }

    @Override
    public void onDisable() {}

    // -----------------------

    private void enableCommands() {
        this.getCommand("hello").setExecutor(new HelloCommand());
    }
}
