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
package de.erethon.reforgedre.equipment;

import de.erethon.factionsxl.FactionsXL;
import de.erethon.factionsxl.board.Region;
import de.erethon.factionsxl.config.FMessage;
import de.erethon.reforgedre.accessory.Accessory;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * @author Daniel Saukel
 */
public interface ForgedEquipment {

    static final String STAR = "\u2605";

    public static String getOrigin(Player player) {
        if (Bukkit.getPluginManager().getPlugin("FactionsXL") != null) {
            Region region = FactionsXL.getInstance().getBoard().getByLocation(player.getLocation());
            return region != null ? region.getName() : FMessage.MISC_WILDERNESS.getMessage();
        } else {
            return player.getWorld().getName();
        }
    }

    String getName();

    EquipmentType getBaseType();

    AdvancedRecipe getRecipe();

    default ItemStack toItemStack(MaterialType type, int quality, String smith, String origin) {
        return toItemStack(type, quality, smith, origin, null);
    }

    ItemStack toItemStack(MaterialType type, int quality, String smith, String origin, ItemStack accessory);

    default void applyVisualMeta(ItemMeta meta, int quality, String smith, String origin, ItemStack accessory) {
        String displayName = ChatColor.WHITE + getName();
        if (accessory != null && accessory.getItemMeta().hasDisplayName()) {
            displayName = ChatColor.translateAlternateColorCodes('&', accessory.getItemMeta().getDisplayName());
        }
        meta.setDisplayName(displayName);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + getName());
        if (accessory != null) {
            switch (accessory.getType()) {
                case DIAMOND:
                    lore.add(Accessory.DIAMONDS.getLore());
                    break;
                case EMERALD:
                    lore.add(Accessory.EMERALDS.getLore());
                    break;
                case QUARTZ:
                    lore.add(Accessory.QUARTZ.getLore());
                    break;
                case NETHER_STAR:
                    lore.add(Accessory.NETHER_STAR.getLore());
                    break;
                case ENDER_PEARL:
                    lore.add(Accessory.PEARLS.getLore());
                    break;
            }
        }
        String stars = "";
        if (quality == -1) {
            stars = "?";
        }
        while (quality > 0) {
            stars += STAR;
            quality--;
        }
        lore.add(ChatColor.GREEN + "Qualit\u00e4t: " + ChatColor.GOLD + stars);
        lore.add(ChatColor.GREEN + "Schmied: " + ChatColor.GOLD + smith);
        lore.add(ChatColor.GREEN + "Herkunft: " + ChatColor.GOLD + origin);
        meta.setLore(lore);
    }

}
