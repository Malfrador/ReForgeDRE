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
import static io.github.dre2n.reforgedre.Weapon.STAR;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
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
public class DREItem {

    public static Set<ArmorSlot> MAIN_HAND = new HashSet<>(Arrays.asList(ArmorSlot.MAIN_HAND));

    public static ItemStack PIRATE_SABER = setup(new ItemStack(Material.IRON_SWORD), ChatColor.WHITE + "Piratensäbel", "Säbel", 3, "Arrrr!");
    public static ItemStack DWARF_PICKAXE = setup(new ItemStack(Material.IRON_PICKAXE), ChatColor.WHITE + "Zwergenspitzhacke", "Spitzhacke", 5, "Aus Arachnida");
    public static ItemStack HOLY_SWORD = setup(new ItemStack(Material.IRON_SWORD), ChatColor.WHITE + "Szent Kard", "Anderthalbhänder", 4, "Aus Sohothin");

    static {
        PIRATE_SABER = ItemUtil.setAttribute(PIRATE_SABER, Attribute.GENERIC_ATTACK_DAMAGE, mod(3.85), MAIN_HAND);
        PIRATE_SABER = ItemUtil.setAttribute(PIRATE_SABER, Attribute.GENERIC_ATTACK_SPEED, mod(-1.9), MAIN_HAND);
        ItemMeta dMeta = DWARF_PICKAXE.getItemMeta();
        dMeta.setUnbreakable(true);
        DWARF_PICKAXE.setItemMeta(dMeta);
        ItemMeta hMeta = HOLY_SWORD.getItemMeta();
        hMeta.addEnchant(Enchantment.DAMAGE_UNDEAD, 2, true);
        List<String> lore = hMeta.getLore();
        lore.add(1, Weapon.RUBIES);
        hMeta.setLore(lore);
        HOLY_SWORD.setItemMeta(hMeta);
    }

    private static AttributeModifier mod(double value) {
        return new AttributeModifier("DRE2N", value, Operation.ADD_NUMBER);
    }

    private static ItemStack setup(ItemStack itemStack, String name, String type, int quality, String smith) {
        itemStack = itemStack.clone();
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(name);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + type);
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
        itemStack.setItemMeta(meta);
        return itemStack;
    }

}
