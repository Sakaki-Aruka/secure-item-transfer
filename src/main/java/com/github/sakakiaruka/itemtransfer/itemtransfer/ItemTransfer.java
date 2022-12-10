package com.github.sakakiaruka.itemtransfer.itemtransfer;

import com.github.sakakiaruka.itemtransfer.itemtransfer.listener.BlockPlace;
import com.github.sakakiaruka.itemtransfer.itemtransfer.listener.ClickInventory;
import com.github.sakakiaruka.itemtransfer.itemtransfer.manager.forManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class ItemTransfer extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("for").setExecutor(new forManager());
        getServer().getPluginManager().registerEvents(new ClickInventory(),this);
        getServer().getPluginManager().registerEvents(new BlockPlace(),this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
