package com.github.sakakiaruka.itemtransfer.itemtransfer.listener;

import com.github.sakakiaruka.itemtransfer.itemtransfer.somethingelse.MessageUtil;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.ShulkerBox;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;

import static com.github.sakakiaruka.itemtransfer.itemtransfer.manager.forManager.opening;

public class ClickInventory implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        if(opening.contains(player)){
            int clicked = event.getRawSlot();
            if(36<=clicked && 45<clicked && clicked!=44){
             event.setCancelled(true);
            }else if(clicked==44){
                Location location = player.getLocation();
                ItemStack stack = this.getShulker(event.getClickedInventory());
                World world = player.getWorld();
                world.dropItem(location,stack);
                new MessageUtil().info("The item that zipped was dropped.");
                return;
            }

        }
    }

    private ItemStack getShulker(Inventory inv){
        ItemStack[] stacks = new ItemStack[27];
        for(int i=0;i<27;i++){
            stacks[i] = inv.getItem(i);
        }
        ItemStack box = new ItemStack(Material.SHULKER_BOX);
        ItemMeta meta = box.getItemMeta();
        meta.addEnchant(Enchantment.MENDING,1,false);
        box.setItemMeta(meta);
        BlockStateMeta bsm = (BlockStateMeta) box.getItemMeta();
        ShulkerBox shulkerBox = (ShulkerBox) bsm.getBlockState();
        shulkerBox.getInventory().setContents(stacks);

        return (ItemStack) shulkerBox;
    }
}
