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

import io.github.dre2n.itemsxl.util.commons.misc.NumberUtil;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Daniel Saukel
 */
public class ReForgeDRE extends JavaPlugin {

    private static ReForgeDRE instance;

    public List<String> disabledRecipes;

    @Override
    public void onEnable() {
        instance = this;
        getServer().getPluginManager().registerEvents(new AnvilListener(), this);
        getServer().getPluginManager().registerEvents(new RecipeListener(), this);
        getServer().getPluginManager().registerEvents(new ParticleListener(), this);
        disabledRecipes = getConfig().getStringList("disabledRecipes");
        for (Entry<String, Object> entry : getConfig().getConfigurationSection("weapons").getValues(false).entrySet()) {
            new Weapon(entry.getKey(), ((ConfigurationSection) entry.getValue()).getValues(false));
        }
        for (Entry<String, Object> entry : getConfig().getConfigurationSection("recipes").getValues(false).entrySet()) {
            new AdvancedRecipe(entry.getKey(), ((ConfigurationSection) entry.getValue()).getValues(false));
        }
    }

    public static ReForgeDRE getInstance() {
        return instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player) || args.length < 1) {
            return false;
        }
        Weapon weapon = null;
        for (Weapon w : Weapon.cache) {
            if (w.name.equalsIgnoreCase(args[0])) {
                weapon = w;
            }
        }
        int quality = args.length >= 2 ? NumberUtil.parseInt(args[1]) : -1;
        if (weapon != null) {
            ((Player) sender).getInventory().addItem(weapon.toItemStack(false, quality, "unbekannt"));
        } else {
            return false;
        }
        return true;
    }

}
