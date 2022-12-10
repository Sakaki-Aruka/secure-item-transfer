package com.github.sakakiaruka.itemtransfer.itemtransfer.listener;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class BlockPlace implements Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        ItemStack item = event.getItemInHand();
        if(item.getType().equals(Material.SHULKER_BOX)
        && item.containsEnchantment(Enchantment.MENDING)){
            event.setCancelled(true);
        }
    }
}
