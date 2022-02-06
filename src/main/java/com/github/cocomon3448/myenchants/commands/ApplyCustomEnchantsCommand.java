package com.github.cocomon3448.myenchants.commands;

import com.github.cocomon3448.myenchants.MyEnchants;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ApplyCustomEnchantsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            int eLvl = Integer.parseInt(args[1]);
            ItemStack pInv = player.getInventory().getItemInMainHand();
            ItemMeta meta = pInv.getItemMeta();
            ArrayList<String> lores = new ArrayList<>();
            if(args[0].equalsIgnoreCase("homing")) {
                lores.add(MyEnchants.homingEnchantments.getFormattedName(eLvl));
                meta.setLore(lores);
                pInv.setItemMeta(meta);
                pInv.addUnsafeEnchantment(MyEnchants.homingEnchantments,eLvl);
            }

        }
        return true;
    }
}
