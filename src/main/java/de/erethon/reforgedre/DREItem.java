/*
 * Copyright (C) 2017-2018 Daniel Saukel
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
package de.erethon.reforgedre;

import de.erethon.reforgedre.accessory.Accessory;
import static de.erethon.reforgedre.equipment.Equipment.STAR;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * @author Daniel Saukel
 */
public class DREItem {

    public static ItemStack DWARF_PICKAXE = setup(new ItemStack(Material.IRON_PICKAXE), ChatColor.WHITE + "Zwergenspitzhacke", "Spitzhacke", 5, "unbekannt", "Arachnida");
    public static ItemStack HOLY_SWORD = setup(new ItemStack(Material.IRON_SWORD), ChatColor.WHITE + "Szent Kard", "Anderthalbh\u00e4nder", 4, "unbekannt", "Sohothin");

    static {
        ItemMeta dMeta = DWARF_PICKAXE.getItemMeta();
        dMeta.setUnbreakable(true);
        DWARF_PICKAXE.setItemMeta(dMeta);
        ItemMeta hMeta = HOLY_SWORD.getItemMeta();
        hMeta.addEnchant(Enchantment.DAMAGE_UNDEAD, 2, true);
        List<String> lore = hMeta.getLore();
        lore.add(1, Accessory.RUBIES.getLore());
        hMeta.setLore(lore);
        HOLY_SWORD.setItemMeta(hMeta);
    }

    public static ItemStack setup(ItemStack itemStack, String name, String type, int quality, String smith, String origin) {
        String stars = "";
        if (quality == -1) {
            stars = "?";
        }
        while (quality > 0) {
            stars += STAR;
            quality--;
        }
        return setup(itemStack, name, type, null, stars, smith, origin);
    }

    public static ItemStack setup(ItemStack itemStack, String name, String type, String accessory, String quality, String smith, String origin) {
        itemStack = itemStack.clone();
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(name);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + type);
        if (accessory != null) {
            lore.add(accessory);
        }
        lore.add(ChatColor.GREEN + "Qualit\u00e4t: " + ChatColor.GOLD + quality);
        lore.add(ChatColor.GREEN + "Schmied: " + ChatColor.GOLD + smith);
        lore.add(ChatColor.GREEN + "Herkunft: " + ChatColor.GOLD + origin);
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

}
