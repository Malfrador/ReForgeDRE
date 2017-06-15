/*
 * Copyright (C) 2017 Daniel Saukel
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.dre2n.reforgedre;

import io.github.dre2n.itemsxl.util.commons.item.ArmorSlot;
import io.github.dre2n.itemsxl.util.commons.item.ItemUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * @author Daniel Saukel
 */
public class Weapon {

    public static final String DIAMONDS = ChatColor.GRAY + "Mit Diamanten besetzt.";
    public static final String EMERALDS = ChatColor.GRAY + "Mit Smaragden besetzt.";
    public static final String QUARTZ = ChatColor.GRAY + "Mit Quarz besetzt.";
    public static final String NETHER_STAR = ChatColor.GRAY + "Mit Netherstern besetzt.";
    public static final String PEARLS = ChatColor.GRAY + "Mit Perlen besetzt.";

    static final Set<Weapon> cache = new HashSet<>();

    public enum Type {
        AXE(Material.IRON_AXE, Material.GOLD_AXE),
        SWORD(Material.IRON_SWORD, Material.GOLD_SWORD);

        Material iron;
        Material gold;

        Type(Material iron, Material gold) {
            this.iron = iron;
            this.gold = gold;
        }
    }

    public static final String STAR = "\u2605";

    public String name;
    public Type base;
    public double damage;
    public double speed;

    public Weapon(String name, Map<String, Object> data) {
        cache.add(this);
        this.name = name;
        base = Type.valueOf((String) data.get("base"));
        damage = (double) data.get("damage");
        speed = (double) data.get("speed");
    }

    public ItemStack toItemStack(boolean gold, int quality, String smith) {
        return toItemStack(gold, quality, smith, null);
    }

    public ItemStack toItemStack(boolean gold, int quality, String smith, ItemStack accessory) {
        ItemStack stack = new ItemStack(gold ? base.gold : base.iron);
        double damage = this.damage;
        double speed = this.speed;
        if (quality == 5) {
            damage += damage / 10;
            speed += speed / 10;
            if (new Random().nextInt(100) > 90) {
                stack.addEnchantment(Enchantment.FIRE_ASPECT, 1);
            }
            if (new Random().nextInt(100) > 70) {
                stack.addEnchantment(Enchantment.DURABILITY, 2);
            }
        } else if (quality == 4) {
            damage += damage / 20;
            speed += speed / 20;
            if (new Random().nextInt(100) > 70) {
                stack.addEnchantment(Enchantment.DURABILITY, 1);
            }
        } else if (quality == 2) {
            damage -= damage / 20;
            speed -= speed / 20;
            if (new Random().nextInt(100) > 90) {
                stack.addEnchantment(Enchantment.VANISHING_CURSE, 1);
            } else if (new Random().nextInt() > 60) {
                stack = ItemUtil.setAttribute(stack, Attribute.GENERIC_MOVEMENT_SPEED,
                        new AttributeModifier("DRE2N", -0.1, Operation.MULTIPLY_SCALAR_1), new HashSet<>(Arrays.asList(ArmorSlot.MAIN_HAND)));
            }
        } else if (quality == 1) {
            damage -= damage / 10;
            speed -= speed / 10;
            if (new Random().nextInt(100) > 75) {
                stack.addEnchantment(Enchantment.VANISHING_CURSE, 1);
            } else if (new Random().nextInt() > 60) {
                stack = ItemUtil.setAttribute(stack, Attribute.GENERIC_MOVEMENT_SPEED,
                        new AttributeModifier("DRE2N", -0.2, Operation.MULTIPLY_SCALAR_1), new HashSet<>(Arrays.asList(ArmorSlot.MAIN_HAND)));
            }
        }
        stack = ItemUtil.setAttribute(stack, Attribute.GENERIC_ATTACK_DAMAGE,
                new AttributeModifier("DRE2N", damage, Operation.ADD_NUMBER), new HashSet<>(Arrays.asList(ArmorSlot.MAIN_HAND)));
        stack = ItemUtil.setAttribute(stack, Attribute.GENERIC_ATTACK_SPEED,
                new AttributeModifier("DRE2N", speed, Operation.ADD_NUMBER), new HashSet<>(Arrays.asList(ArmorSlot.MAIN_HAND)));
        ItemMeta meta = stack.getItemMeta();
        String displayName = ChatColor.WHITE + name;
        if (accessory != null && accessory.getItemMeta().hasDisplayName()) {
            displayName = ChatColor.translateAlternateColorCodes('&', accessory.getItemMeta().getDisplayName());
        }
        meta.setDisplayName(displayName);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + name);
        if (accessory != null) {
            switch (accessory.getType()) {
                case DIAMOND:
                    lore.add(DIAMONDS);
                    break;
                case EMERALD:
                    lore.add(EMERALDS);
                    break;
                case QUARTZ:
                    lore.add(QUARTZ);
                    break;
                case NETHER_STAR:
                    lore.add(NETHER_STAR);
                    break;
                case ENDER_PEARL:
                    lore.add(PEARLS);
                    break;
            }
        }
        String stars = new String();
        if (quality == -1) {
            stars = "?";
        }
        while (quality > 0) {
            stars += STAR;
            quality--;
        }
        lore.add(ChatColor.GREEN + "Qualität: " + ChatColor.GOLD + stars);
        lore.add(ChatColor.GREEN + "Schmied: " + ChatColor.GOLD + smith);
        meta.setLore(lore);
        stack.setItemMeta(meta);
        return stack;
    }

}
