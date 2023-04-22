package com.minecraft.sandbox;

import com.minecraft.sandbox.commands.HelloCommand;
import com.minecraft.sandbox.listeners.BlockBreakListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class MinecraftSandbox extends JavaPlugin {
    @Override
    public void onEnable() {
        enableCommands();
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
    }

    @Override
    public void onDisable() {}

    // -----------------------

    private void enableCommands() {
        this.getCommand("hello").setExecutor(new HelloCommand());
    }
}
