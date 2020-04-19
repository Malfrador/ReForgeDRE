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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static org.bukkit.Material.*;
import static org.bukkit.Material.WOODEN_AXE;

/**
 * @author Daniel Saukel
 */
public class RecipeListener implements Listener {

    private ReForgeDRE plugin;

    public RecipeListener(ReForgeDRE plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCraftItem(CraftItemEvent event) {
        ItemStack result = event.getInventory().getResult();
        if (plugin.disabledRecipes.contains(result.getType())) {
            if ((result.getType() == Material.STONE_AXE) || result.getType() == Material.WOODEN_AXE || result.getType() == IRON_AXE || result.getType() == GOLDEN_AXE || result.getType() == DIAMOND_AXE) {
                AttributeModifier mod = new AttributeModifier(UUID.randomUUID(), "reforgedre.disabled", -1, AttributeModifier.Operation.ADD_SCALAR);
                ItemMeta meta = result.getItemMeta();
                meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, mod);
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                List<String> lore = new ArrayList<>();
                lore.add(ChatColor.DARK_PURPLE + "Werkzeug");
                lore.add("" + ChatColor.GRAY + ChatColor.ITALIC + "Verursacht keinen Schaden");
                meta.setLore(lore);
                result.setItemMeta(meta);
                return;
            }
            ItemMeta meta = result.getItemMeta();
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.DARK_RED + "Aufwendigere Schwerter und R\u00fcstungen");
            lore.add(ChatColor.DARK_RED + "werden auf " + ChatColor.ITALIC + "Die Reiche Erethons" + ChatColor.DARK_RED + " am");
            lore.add(ChatColor.DARK_RED + "Amboss geschmiedet! Platziere dazu einen");
            lore.add(ChatColor.DARK_RED + "Amboss direkt neben oder auf einem Ofen.");
            meta.setLore(lore);
            result.setItemMeta(meta);
            event.setCancelled(true);
        }
    }

}
