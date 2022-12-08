package com.github.sakakiaruka.itemtransfer.itemtransfer.listener;

import com.github.sakakiaruka.itemtransfer.itemtransfer.somethingelse.MessageUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static com.github.sakakiaruka.itemtransfer.itemtransfer.manager.forManager.opening;

public class CloseInventory implements Listener {
    @EventHandler
    public void onCloseInventory(InventoryCloseEvent event){
        Player player = (Player) event.getPlayer();
        if(opening.contains(player)){
            Inventory inv = event.getInventory();
            for(int i=0;i<27;i++){
                ItemStack item = inv.getItem(i);
                player.getWorld().dropItem(player.getLocation(),item);
            }
            player.sendMessage(new MessageUtil().warn("Catch an event \"close inventory\"."));
            opening.remove(player);
        }
    }
}
