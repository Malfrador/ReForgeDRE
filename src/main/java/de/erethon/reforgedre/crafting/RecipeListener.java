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
package de.erethon.reforgedre.crafting;

import de.erethon.reforgedre.ReConfig;
import de.erethon.reforgedre.ReForgeDRE;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
            ItemMeta meta = result.getItemMeta();
            List<String> lore = new ArrayList<>();
            lore.add(ReConfig.CRAFTING_BLOCKED_1);
            lore.add(ReConfig.CRAFTING_BLOCKED_2);
            lore.add(ReConfig.CRAFTING_BLOCKED_3);
            lore.add(ReConfig.CRAFTING_BLOCKED_4);
            meta.setLore(lore);
            result.setItemMeta(meta);
            event.setCancelled(true);
        }
    }

}
