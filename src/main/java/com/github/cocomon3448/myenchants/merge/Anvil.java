package com.github.cocomon3448.myenchants.merge;


import com.github.cocomon3448.myenchants.tools.MergedEnchants;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

import static com.github.cocomon3448.myenchants.MyEnchants.homingEnchantments;

public class Anvil implements Listener {

    @EventHandler
    public void onAnvil(PrepareAnvilEvent e) {
        System.out.println(e.getInventory().getItem(0)+" "+e.getInventory().getItem(1));
        if(e.getInventory().getItem(0) == null) {
            return;
        }
        if(e.getInventory().getItem(1) == null) {
            return;
        }
        ItemStack fItem = e.getInventory().getItem(0);
        ItemStack sItem = e.getInventory().getItem(1);
        if(!fItem.getType().equals(sItem.getType())) {
            return;
        }

        ItemStack result = new ItemStack(fItem.getType(),1);
        ArrayList<String> lores = new ArrayList<>();

        if(fItem.getItemMeta().hasEnchant(homingEnchantments) && sItem.getItemMeta().hasEnchant(homingEnchantments)) {
            int fHNo = fItem.getItemMeta().getEnchantLevel(homingEnchantments);
            int sHNo = sItem.getItemMeta().getEnchantLevel(homingEnchantments);
            int rHomNo = MergedEnchants.getLevel(fHNo,sHNo);

            ItemMeta mata = result.getItemMeta();
            lores.add(homingEnchantments.getFormattedName(rHomNo));
            mata.setLore(lores);
            result.setItemMeta(mata);
            result.addUnsafeEnchantment(homingEnchantments, rHomNo);
            System.out.println(fHNo+" "+sHNo);
            e.setResult(result);
        }
    }

}
