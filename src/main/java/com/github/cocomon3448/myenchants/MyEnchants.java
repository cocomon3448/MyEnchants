package com.github.cocomon3448.myenchants;

import com.github.cocomon3448.myenchants.commands.ApplyCustomEnchantsCommand;
import com.github.cocomon3448.myenchants.commands.CheakEnchantsCommand;
import com.github.cocomon3448.myenchants.enchants.Homing;
import com.github.cocomon3448.myenchants.merge.Anvil;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
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
        //register events
        this.getServer().getPluginManager().registerEvents(new Anvil(),this);
        this.getServer().getPluginManager().registerEvents(homingEnchantments, this);
        this.getServer().getPluginManager().registerEvents(this, this);

        //register commands
        this.getCommand("cheakenc").setExecutor(new CheakEnchantsCommand());
        this.getCommand("applyenc").setExecutor(new ApplyCustomEnchantsCommand());
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
}
