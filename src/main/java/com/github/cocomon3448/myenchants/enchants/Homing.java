package com.github.cocomon3448.myenchants.enchants;

import com.github.cocomon3448.myenchants.MyEnchants;
import com.github.cocomon3448.myenchants.task.HomingTask;
import com.github.cocomon3448.myenchants.tools.RomanNumber;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import static com.github.cocomon3448.myenchants.MyEnchants.homingEnchantments;

public class Homing extends Enchantment implements Listener {

    public Homing(String namespace){
        super(new NamespacedKey(MyEnchants.getPlugin(), namespace));
    }

    //Define what happens for the enchantment
    @EventHandler
    public void eventArrowFired(EntityShootBowEvent e)
    {
        if (e.getEntity() instanceof Player) {
            Player p = ((Player) e.getEntity()).getPlayer();
            //see if they are holding a sword with the enchant
            ItemStack item = p.getInventory().getItemInMainHand();
            if (item.getEnchantments().containsKey(Enchantment.getByKey(homingEnchantments.getKey()))) {
                int enchLvl = item.getEnchantmentLevel(homingEnchantments);

                if (e.getProjectile() instanceof Arrow)
                {
                    double[] range = {enchLvl*10.0D,enchLvl*10.0D,enchLvl*10.0D};
                    LivingEntity player = e.getEntity();

                    double minAngle = 6.283185307179586D;
                    Entity minEntity = null;
                    for (Entity entity : player.getNearbyEntities(range[0], range[1], range[2])) {
                        if ((player.hasLineOfSight(entity)) && ((entity instanceof LivingEntity)) && (!entity.isDead()))
                        {
                            Vector toTarget = entity.getLocation().toVector().clone().subtract(player.getLocation().toVector());
                            double angle = e.getProjectile().getVelocity().angle(toTarget);
                            if (angle < minAngle)
                            {
                                minAngle = angle;
                                minEntity = entity;
                            }
                        }
                    }
                    if (minEntity != null) {
                        new HomingTask((Arrow)e.getProjectile(), (LivingEntity)minEntity, MyEnchants.getPlugin());
                    }
                }
            }
        }
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.BOW;
    }

    @Override
    public String getName() {
        return "Homing";
    }

    public String getFormattedName(int level) {
        return ChatColor.GRAY + this.getName() + " " + RomanNumber.toRoman(level);
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public NamespacedKey getKey() {
        return super.getKey();
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        return true;
    }
}
