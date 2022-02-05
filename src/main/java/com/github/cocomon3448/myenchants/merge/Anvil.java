package com.github.cocomon3448.myenchants.merge;

import com.github.cocomon3448.myenchants.tools.RomanNumber;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

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
        System.out.println("1");
        ItemStack result = new ItemStack(fItem.getType(),1);
        ArrayList<String> lores = new ArrayList<String>();

        if(fItem.getItemMeta().hasLore() && sItem.getItemMeta().hasLore()) {
            int fHNo = 0;
            int sHNo = 0;
            if(fItem.getItemMeta().getLore().toString().contains("Homing")) {
                String str = fItem.getItemMeta().getLore().toString().replace("[","").replace("]","");
                String romanNo = str.split(" ")[1];
                fHNo = RomanNumber.toInt(romanNo);
            }
            if(sItem.getItemMeta().getLore().toString().contains("Homing")) {
                String str = sItem.getItemMeta().getLore().toString().replace("[","").replace("]","");
                String romanNo = str.split(" ")[1];
                sHNo = RomanNumber.toInt(romanNo);
            }

            lores.add(fItem.getItemMeta().getLore().toString());
            lores.add(sItem.getItemMeta().getLore().toString());
            ItemMeta mata = result.getItemMeta();
            mata.setLore(lores);
            result.setItemMeta(mata);
            System.out.println(fHNo+" "+sHNo);
            e.setResult(result);
        }
    }

}
