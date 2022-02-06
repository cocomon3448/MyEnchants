package com.github.cocomon3448.myenchants.tools;

public class MergedEnchants {
    public static int getLevel(int first, int second) {
        if(first>second) {
            return first;
        }
        if(second>first) {
            return second;
        }
        if(first == second) {
            return first + 1;
        }
        return -1;
    }
}
