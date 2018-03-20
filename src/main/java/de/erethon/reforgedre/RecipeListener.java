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
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * @author Daniel Saukel
 */
public class RecipeListener implements Listener {

    @EventHandler
    public void onCraftItem(CraftItemEvent event) {
        ItemStack result = event.getInventory().getResult();
        if (ReForgeDRE.getInstance().disabledRecipes.contains(result.getType())) {
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
