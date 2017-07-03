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
import io.github.dre2n.sakura.SakuraItem;
import java.util.List;
import java.util.Map.Entry;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.SpawnEggMeta;
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
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
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
        Player player = (Player) sender;
        if (args[0].equalsIgnoreCase("start") && args.length == 2 && JoinListener.cache.contains(player.getUniqueId()) | sender.isOp()) {
            PlayerInventory inventory = player.getInventory();
            switch (args[1]) {
                case "cuthalorn":
                    inventory.addItem(new ItemStack(Material.IRON_HOE), new ItemStack(Material.BEETROOT_SEEDS),
                            new ItemStack(Material.SEEDS), new ItemStack(Material.PUMPKIN_SEEDS), new ItemStack(Material.MELON_SEEDS));
                    break;
                case "arachnida":
                    inventory.addItem(DREItem.DWARF_PICKAXE);
                    break;
                case "sohothin":
                    inventory.addItem(DREItem.HOLY_SWORD);
                    break;
                case "hohenstein":
                    inventory.addItem(new ItemStack(Material.GOLDEN_APPLE, 1, (short) 1));
                    break;
                case "golvathal":
                    inventory.addItem(new ItemStack(Material.GOLD_INGOT, 64));
                    break;
                case "daoshen":
                    Weapon weapon = null;
                    for (Weapon w : Weapon.cache) {
                        if (w.name.equalsIgnoreCase("Katana")) {
                            weapon = w;
                        }
                    }
                    inventory.addItem(weapon.toItemStack(false, 4, "Aus Dao-Shen"), SakuraItem.SAPLING);
                    break;
                case "pirate":
                    ItemStack parrot = new ItemStack(Material.MONSTER_EGG, 1);
                    SpawnEggMeta meta = (SpawnEggMeta) parrot.getItemMeta();
                    meta.setSpawnedType(EntityType.PARROT);
                    parrot.setItemMeta(meta);
                    inventory.addItem(DREItem.PIRATE_SABER, parrot);
                    break;
            }
            inventory.addItem(new ItemStack(Material.LEATHER_CHESTPLATE), new ItemStack(Material.LEATHER_LEGGINGS),
                    new ItemStack(Material.LEATHER_BOOTS), new ItemStack(Material.BREAD, 32));
            JoinListener.cache.remove(player.getUniqueId());
        }
        if (!sender.isOp()) {
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
