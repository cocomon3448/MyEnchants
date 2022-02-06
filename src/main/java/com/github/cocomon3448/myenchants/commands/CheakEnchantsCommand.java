package com.github.cocomon3448.myenchants.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class CheakEnchantsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            ItemStack pInv = player.getInventory().getItemInMainHand();
            if(pInv != null && !pInv.getType().equals(Material.AIR)) {
                Map<Enchantment, Integer> enchants = pInv.getEnchantments();
                player.sendMessage(String.valueOf(enchants));
            }
        }
        return true;
    }
}
