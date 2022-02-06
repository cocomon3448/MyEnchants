package com.github.cocomon3448.myenchants.tools;

import com.github.cocomon3448.myenchants.MyEnchants;
import org.bukkit.enchantments.Enchantment;

public class MergedEnchants {
    public static int getLevel(int first, int second, Enchantment enc) {
        if(first>second) {
            return first;
        }
        if(second>first) {
            return second;
        }
        if(first == second) {
            if((first+1) > enc.getMaxLevel()) {
                return enc.getMaxLevel();
            } else{
                return first + 1;
            }
        }
        return -1;
    }
}
