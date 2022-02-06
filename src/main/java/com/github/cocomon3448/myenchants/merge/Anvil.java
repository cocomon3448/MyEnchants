package com.github.cocomon3448.myenchants.merge;


import com.github.cocomon3448.myenchants.tools.MergedEnchants;
import org.bukkit.Material;
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
            if(isBow(fItem) == true && isBow(sItem) == true) {
                System.out.println("BOW&BOW");
                int fHomNo = fItem.getItemMeta().getEnchantLevel(homingEnchantments);
                int sHomNo = sItem.getItemMeta().getEnchantLevel(homingEnchantments);
                int rHomNo = MergedEnchants.getLevel(fHomNo, sHomNo, homingEnchantments);

                ItemMeta mata = result.getItemMeta();
                lores.add(homingEnchantments.getFormattedName(rHomNo));
                mata.setLore(lores);
                result.setItemMeta(mata);
                result.addUnsafeEnchantment(homingEnchantments, rHomNo);
                System.out.println(fHomNo+" "+sHomNo);
                e.setResult(result);
            }
            if(isBow(fItem) == true && isBook(sItem) == true) {
                System.out.println("BOW&BOOK");
                int fHomNo = fItem.getItemMeta().getEnchantLevel(homingEnchantments);
                int sHomNo = sItem.getItemMeta().getEnchantLevel(homingEnchantments);
                int rHomNo = MergedEnchants.getLevel(fHomNo, sHomNo, homingEnchantments);

                ItemMeta mata = result.getItemMeta();
                lores.add(homingEnchantments.getFormattedName(rHomNo));
                mata.setLore(lores);
                result.setItemMeta(mata);
                result.addUnsafeEnchantment(homingEnchantments, rHomNo);
                System.out.println(fHomNo+" "+sHomNo);
                e.setResult(result);
            }
        }
    }

    public static boolean isBook(ItemStack item) {
        if(item.getType().equals(Material.ENCHANTED_BOOK)) {
            return true;
        } else {
            System.out.println("Is Book = false "+ item.getType());
            return false;
        }
    }
    public static boolean isBow(ItemStack item) {
        if(item.getType().equals(Material.BOW)) {
            return true;
        } else {
            System.out.println("Is Bow = false "+ item.getType());
            return false;
        }
    }
}
