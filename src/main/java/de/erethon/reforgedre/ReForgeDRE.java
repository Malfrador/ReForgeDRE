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

import de.erethon.reforgedre.accessory.ParticleListener;
import de.erethon.reforgedre.equipment.Equipment;
import de.erethon.reforgedre.equipment.MaterialType;
import de.erethon.reforgedre.equipment.Weapon;
import java.util.Arrays;
import java.util.List;
import org.bukkit.Material;
import static org.bukkit.Material.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
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
        getServer().getPluginManager().registerEvents(new InvisibilityListener(), this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player) || args.length < 1) {
            return false;
        }
        Player player = (Player) sender;
        if (args[0].equalsIgnoreCase("start") && args.length == 2 | sender.isOp()) {
            PlayerInventory inventory = player.getInventory();
            switch (args[1]) {
                case "cuthalorn":
                    inventory.addItem(new ItemStack(IRON_HOE), new ItemStack(BEETROOT_SEEDS),
                            new ItemStack(WHEAT_SEEDS), new ItemStack(PUMPKIN_SEEDS), new ItemStack(MELON_SEEDS));
                    break;
                case "arachnida":
                    inventory.addItem(DREItem.DWARF_PICKAXE);
                    break;
                case "sohothin":
                    inventory.addItem(DREItem.HOLY_SWORD);
                    break;
                case "hohenstein":
                    inventory.addItem(new ItemStack(GOLDEN_APPLE));
                    break;
                case "golvathal":
                    inventory.addItem(new ItemStack(GOLD_INGOT, 64));
                    break;
                case "daoshen":
                    inventory.addItem(Weapon.KATANA.toItemStack(MaterialType.IRON, 4, "unbekannt", "Dao-Shen"));
                    break;
                case "pirate":
                    ItemStack parrot = new ItemStack(PARROT_SPAWN_EGG, 1);
                    inventory.addItem(Weapon.PIRATE_SABER.toItemStack(MaterialType.IRON, 3, "Arrrr!", "7 Weltmeere"), parrot);
                    break;
            }
            inventory.addItem(new ItemStack(LEATHER_CHESTPLATE), new ItemStack(LEATHER_LEGGINGS),
                    new ItemStack(LEATHER_BOOTS), new ItemStack(BREAD, 32));
        }
        if (!sender.isOp()) {
            return false;
        }
        Weapon weapon = null;
        for (Weapon w : Weapon.values()) {
            if (w.getName().equalsIgnoreCase(args[0]) || w.name().equalsIgnoreCase(args[0])) {
                weapon = w;
            }
        }
        int quality = args.length >= 2 ? Integer.parseInt(args[1]) : -1;
        if (weapon != null) {
            ((Player) sender).getInventory().addItem(weapon.toItemStack(MaterialType.IRON, quality, "unbekannt", Equipment.getOrigin(player)));
        } else {
            return false;
        }
        return true;
    }

}
