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

import de.erethon.reforgedre.equipment.CustomWeapon;
import de.erethon.reforgedre.equipment.ForgedEquipment;
import de.erethon.reforgedre.equipment.MaterialType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

/**
 * @author Daniel Saukel
 */
public class ForgingGame {

    public static final ItemStack FURNACE = new ItemStack(Material.FURNACE);
    public static final ItemStack WATER = new ItemStack(Material.WATER_BUCKET);
    public static final ItemStack[] TEMPLATE = new ItemStack[54];

    static {
        ItemMeta meta = WATER.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_RED + "Falten... Schmelzen... Abschrecken!");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Versuche, die beste Waffe");
        lore.add(ChatColor.GRAY + "zu erwischen, bevor sie bricht!");
        meta.setLore(lore);
        WATER.setItemMeta(meta);
        FURNACE.setItemMeta(meta);
        TEMPLATE[0] = FURNACE;
        TEMPLATE[31] = WATER;
    }

    static final Set<ForgingGame> cache = new HashSet<>();

    private ReForgeDRE plugin;

    public Inventory gui;
    public BukkitTask task;

    public Player player;
    public CustomWeapon weapon;
    public MaterialType materialType;
    public ItemStack accessory;

    public ForgingGame(ReForgeDRE plugin, Player player, CustomWeapon weapon, MaterialType materialType, ItemStack accessory) {
        this.plugin = plugin;
        cache.add(this);
        this.player = player;
        this.weapon = weapon;
        this.materialType = materialType;
        this.accessory = accessory;
        gui = Bukkit.createInventory(null, 54, "Schmieden...");
        gui.setContents(TEMPLATE);
    }

    public void start() {
        player.openInventory(gui);
        task = new GameTask().runTaskTimer(plugin, 0, 8);
    }

    public void finish(ItemStack item) {
        player.closeInventory();
        player.getWorld().dropItem(player.getLocation(), item);
        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 1, 1);
        task.cancel();
    }

    public class GameTask extends BukkitRunnable {

        ItemStack item1 = weapon.toItemStack(materialType, 1, player.getName(), ForgedEquipment.getOrigin(player), accessory);
        ItemStack item2 = weapon.toItemStack(materialType, 2, player.getName(), ForgedEquipment.getOrigin(player), accessory);
        ItemStack item3 = weapon.toItemStack(materialType, 3, player.getName(), ForgedEquipment.getOrigin(player), accessory);
        ItemStack item4 = weapon.toItemStack(materialType, 4, player.getName(), ForgedEquipment.getOrigin(player), accessory);
        ItemStack item5 = weapon.toItemStack(materialType, 5, player.getName(), ForgedEquipment.getOrigin(player), accessory);

        int i, i2, i3, i4, i5 = 0;
        int edgeUpperLeft = 0;
        int edgeUpperRight = 8;
        int edgeLowerLeft = 45;
        int edgeLowerRight = 53;
        byte line = 0;

        @Override
        public void run() {
            if (i != 0) {
                gui.setItem(i, null);
            }
            if (i == 32) {
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_DESTROY, 1, 1);
                cancel();
                player.closeInventory();
            } else {
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 1);
            }

            if (line == 0) {
                i++;
            } else if (line == 1) {
                i = i + 9;
            } else if (line == 2) {
                i--;
            } else if (line == 3) {
                i = i - 9;
            }

            if (i == edgeUpperLeft && i != 0) {
                edgeUpperLeft = edgeUpperLeft + 10;
                line = 0;
            } else if (i == edgeUpperRight) {
                edgeUpperRight = edgeUpperRight + 8;
                if (i == 8) {
                    edgeUpperLeft = edgeUpperLeft + 9;
                }
                line = 1;
            } else if (i == edgeLowerRight) {
                edgeLowerRight = edgeLowerRight - 10;
                line = 2;
            } else if (i == edgeLowerLeft) {
                edgeLowerLeft = edgeLowerLeft - 8;
                line = 3;
            }

            int s = new Random().nextInt(5);
            ItemStack stack = null;
            switch (s) {
                case 0:
                    stack = item1;
                    break;
                case 1:
                    stack = item2;
                    break;
                case 2:
                    stack = item3;
                    break;
                case 3:
                    stack = item4;
                    break;
                case 4:
                    stack = item5;
                    break;
            }
            gui.setItem(i, stack);
        }

    }

}
