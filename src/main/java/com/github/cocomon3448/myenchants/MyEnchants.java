package com.github.cocomon3448.myenchants;

import com.github.cocomon3448.myenchants.enchants.Homing;
import com.github.cocomon3448.myenchants.merge.Anvil;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

public final class MyEnchants extends JavaPlugin implements Listener {

    //An easier way to provide an instance of the plugin to other classes
    public static MyEnchants PLUGIN;

    //Custom enchantments list
    public static ArrayList<Enchantment> custom_enchants = new ArrayList<>();
    public static Homing homingEnchantments;
    //more enchants under here

    @Override
    public void onEnable() {
        PLUGIN = this;

        //Create an instance of the custom enchants
        homingEnchantments = new Homing("homing");

        //Add each enchantment to list so we can use
        custom_enchants.add(homingEnchantments);

        for (Enchantment enchantment : custom_enchants){
            registerEnchantment(enchantment);
        }
        this.getServer().getPluginManager().registerEvents(new Anvil(),this);
        this.getServer().getPluginManager().registerEvents(homingEnchantments, this);
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Disable the Power enchantment
        try {
            Field keyField = Enchantment.class.getDeclaredField("byKey");

            keyField.setAccessible(true);
            @SuppressWarnings("unchecked")
            HashMap<NamespacedKey, Enchantment> byKey = (HashMap<NamespacedKey, Enchantment>) keyField.get(null);

            for (Enchantment enchantment : custom_enchants){
                if(byKey.containsKey(enchantment.getKey())) {
                    byKey.remove(enchantment.getKey());
                }
            }

            Field nameField = Enchantment.class.getDeclaredField("byName");

            nameField.setAccessible(true);
            @SuppressWarnings("unchecked")
            HashMap<String, Enchantment> byName = (HashMap<String, Enchantment>) nameField.get(null);

            for (Enchantment enchantment : custom_enchants){
                if(byName.containsKey(enchantment.getName())) {
                    byName.remove(enchantment.getName());
                }
            }
        } catch (Exception ignored) { }
    }

    //Load custom enchantments
    public static void registerEnchantment(Enchantment enchantment) {
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            //e.printStackTrace();
        }
        try {
            Enchantment.registerEnchantment(enchantment);
        } catch (IllegalArgumentException e){
        //if this is thrown it means the id is already taken.
        }
    }

    public static MyEnchants getPlugin(){
        return PLUGIN;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        //Give the player a bow with the enchantment when they join
        Player player = e.getPlayer();
        ItemStack bow1 = new ItemStack(Material.BOW, 1);
        ItemMeta m1 = bow1.getItemMeta();
        ArrayList<String> lore1 = new ArrayList<>();
        lore1.add(homingEnchantments.getFormattedName(1));
        m1.setLore(lore1);
        bow1.setItemMeta(m1);
        bow1.addUnsafeEnchantment(homingEnchantments, 1);

        ItemStack bow2 = new ItemStack(Material.BOW, 1);
        ItemMeta m2 = bow2.getItemMeta();
        ArrayList<String> lore2 = new ArrayList<>();
        lore2.add(homingEnchantments.getFormattedName(2));
        m2.setLore(lore2);
        bow2.setItemMeta(m2);
        bow2.addUnsafeEnchantment(homingEnchantments, 2);

        ItemStack bow3 = new ItemStack(Material.BOW, 1);
        ItemMeta m3 = bow3.getItemMeta();
        ArrayList<String> lore3 = new ArrayList<>();
        lore3.add(homingEnchantments.getFormattedName(3));
        m3.setLore(lore3);
        bow3.setItemMeta(m3);
        bow3.addUnsafeEnchantment(homingEnchantments, 3);

        ItemStack bow4 = new ItemStack(Material.BOW, 1);
        ItemMeta m4 = bow4.getItemMeta();
        ArrayList<String> lore4 = new ArrayList<>();
        lore4.add(homingEnchantments.getFormattedName(4));
        m4.setLore(lore4);
        bow4.setItemMeta(m4);
        bow4.addUnsafeEnchantment(homingEnchantments, 4);

        ItemStack bow5 = new ItemStack(Material.BOW, 1);
        ItemMeta m5 = bow5.getItemMeta();
        ArrayList<String> lore5 = new ArrayList<>();
        lore5.add(homingEnchantments.getFormattedName(5));
        m5.setLore(lore5);
        bow5.setItemMeta(m5);
        bow5.addUnsafeEnchantment(homingEnchantments, 5);

        World w = e.getPlayer().getWorld();
        w.dropItemNaturally(player.getLocation(),bow1);
        w.dropItemNaturally(player.getLocation(),bow2);
        w.dropItemNaturally(player.getLocation(),bow3);
        w.dropItemNaturally(player.getLocation(),bow4);
        w.dropItemNaturally(player.getLocation(),bow5);

    }


}
