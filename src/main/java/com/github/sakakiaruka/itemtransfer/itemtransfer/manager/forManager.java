package com.github.sakakiaruka.itemtransfer.itemtransfer.manager;

import com.github.sakakiaruka.itemtransfer.itemtransfer.somethingelse.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class forManager implements CommandExecutor {
    public static List<Player> opening;
    @Override
    public boolean onCommand(CommandSender sender, Command command,String label,String[] args){
        Player player = (Player) sender;
        String type = this.type(args[0]);
        if(type == null)return false;

        if(type.equalsIgnoreCase("zip")){
            player.openInventory(this.createScreen(27,player));
            return true;
        }else if(type.equalsIgnoreCase("send")){

            ItemStack item = player.getInventory().getItemInMainHand();
            Player p;
            if(!item.containsEnchantment(Enchantment.MENDING) || item.getType().name().contains("SHULKER_BOX")) return false;
            if((p = this.exitPlayer(args[1]))==null){
                return false;
            }
            if(p.isInWater() || p.getLocation().getBlock().isLiquid()){
                player.sendMessage(new MessageUtil().warn("The destination condition is bad to receive items. This request was cancelled."));
                return false;
            }
            p.getWorld().dropItem(p.getLocation(),item);
            player.sendMessage(new MessageUtil().info("Transfer Successful!! Thank you to use. Please use in next time."));
            p.sendMessage(new MessageUtil().info("Notice~ Notice~ We delivered an item to you."));
            return true;
        }
        return false;
    }

    private String type(String arg){
        if(arg.equalsIgnoreCase("zip")){
            return "zip";
        }else if(arg.equalsIgnoreCase("send")){
            return "send";
        }else{
            return null;
        }
    }

    private Player exitPlayer(String in){
        for(Player p:Bukkit.getOnlinePlayers()){
            if(p.getName().equals(in)){
                return p;
            }
        }
        return null;
    }

    private Inventory createScreen(int size,Player player){
        Inventory inv = Bukkit.createInventory(null,(size+9),"zip box");
        for(int i=size;i<(size+9);i++){
            //blank
            inv.setItem(i,new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
        }
        ItemStack item = new ItemStack(Material.SHULKER_BOX);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Click and zip!!");
        item.setItemMeta(meta);
        inv.setItem(44,item);
        opening.add(player);
        return inv;
    }
}
