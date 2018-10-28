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

import de.erethon.caliburn.CaliburnAPI;
import de.erethon.reforgedre.accessory.ParticleListener;
import de.erethon.reforgedre.crafting.AnvilListener;
import de.erethon.reforgedre.crafting.RecipeListener;
import de.erethon.reforgedre.equipment.CustomWeapon;
import de.erethon.reforgedre.equipment.ForgedEquipment;
import de.erethon.reforgedre.equipment.MaterialType;
import java.util.Arrays;
import java.util.List;
import org.bukkit.Material;
import static org.bukkit.Material.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Daniel Saukel
 */
public class ReForgeDRE extends JavaPlugin {

    public List<Material> disabledRecipes = Arrays.asList(DIAMOND_SWORD, IRON_SWORD, GOLDEN_SWORD, DIAMOND_AXE, IRON_AXE, GOLDEN_AXE, STONE_AXE, WOODEN_AXE,
            DIAMOND_HELMET, DIAMOND_CHESTPLATE, DIAMOND_LEGGINGS, DIAMOND_BOOTS, IRON_HELMET, IRON_CHESTPLATE, IRON_LEGGINGS, IRON_BOOTS,
            GOLDEN_HELMET, GOLDEN_CHESTPLATE, GOLDEN_LEGGINGS, GOLDEN_BOOTS);

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new AnvilListener(this), this);
        getServer().getPluginManager().registerEvents(new RecipeListener(this), this);
        getServer().getPluginManager().registerEvents(new ParticleListener(), this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player) || args.length < 1) {
            return false;
        }
        if (!sender.isOp()) {
            return false;
        }
        CustomWeapon weapon = null;
        for (CustomWeapon w : CaliburnAPI.getInstance().getExItems(CustomWeapon.class)) {
            if (w.getName().equalsIgnoreCase(args[0])) {
                weapon = w;
            }
        }
        int quality = args.length >= 2 ? Integer.parseInt(args[1]) : -1;
        if (weapon != null) {
            ((Player) sender).getInventory().addItem(weapon.toItemStack(MaterialType.IRON, quality, ReConfig.ITEM_META_UNKNOWN, ForgedEquipment.getOrigin((Player) sender)));
        } else {
            return false;
        }
        return true;
    }

}
