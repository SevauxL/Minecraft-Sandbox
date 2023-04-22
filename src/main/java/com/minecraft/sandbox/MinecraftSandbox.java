package com.minecraft.sandbox;

import com.minecraft.sandbox.listeners.BlockBreakListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class MinecraftSandbox extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
    }
}
