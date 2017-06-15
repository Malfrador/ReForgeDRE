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

import io.github.dre2n.reforgedre.AdvancedRecipe.MaterialType;
import java.util.HashSet;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * @author Daniel Saukel
 */
public class AdvancedWorkbench {

    public static final ItemStack PLACEHOLDER = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
    public static final int RESULT_SLOT = 35;
    public static final int[] CRAFT_SLOTS = {2, 3, 11, 12, 20, 21, 27, 28, 29, 30, 31, 32, 37, 38, 39, 40, 47, 48};
    public static final ItemStack[] TEMPLATE = new ItemStack[54];

    static {
        ItemMeta meta = PLACEHOLDER.getItemMeta();
        meta.setDisplayName(ChatColor.RESET.toString());
        PLACEHOLDER.setItemMeta(meta);
        TEMPLATE[0] = PLACEHOLDER;
        TEMPLATE[1] = PLACEHOLDER;
        TEMPLATE[4] = PLACEHOLDER;
        TEMPLATE[5] = PLACEHOLDER;
        TEMPLATE[6] = PLACEHOLDER;
        TEMPLATE[7] = PLACEHOLDER;
        TEMPLATE[8] = PLACEHOLDER;
        TEMPLATE[9] = PLACEHOLDER;
        TEMPLATE[10] = PLACEHOLDER;
        TEMPLATE[13] = PLACEHOLDER;
        TEMPLATE[14] = PLACEHOLDER;
        TEMPLATE[15] = PLACEHOLDER;
        TEMPLATE[16] = PLACEHOLDER;
        TEMPLATE[17] = PLACEHOLDER;
        TEMPLATE[18] = PLACEHOLDER;
        TEMPLATE[19] = PLACEHOLDER;
        TEMPLATE[22] = PLACEHOLDER;
        TEMPLATE[23] = PLACEHOLDER;
        TEMPLATE[24] = PLACEHOLDER;
        TEMPLATE[25] = PLACEHOLDER;
        TEMPLATE[26] = PLACEHOLDER;
        TEMPLATE[33] = PLACEHOLDER;
        TEMPLATE[34] = PLACEHOLDER;
        TEMPLATE[36] = PLACEHOLDER;
        TEMPLATE[41] = PLACEHOLDER;
        TEMPLATE[42] = PLACEHOLDER;
        TEMPLATE[43] = PLACEHOLDER;
        TEMPLATE[44] = PLACEHOLDER;
        TEMPLATE[45] = PLACEHOLDER;
        TEMPLATE[46] = PLACEHOLDER;
        TEMPLATE[49] = PLACEHOLDER;
        TEMPLATE[50] = PLACEHOLDER;
        TEMPLATE[51] = PLACEHOLDER;
        TEMPLATE[52] = PLACEHOLDER;
        TEMPLATE[53] = PLACEHOLDER;
    }

    static final Set<AdvancedWorkbench> cache = new HashSet<>();

    public Inventory gui;
    public Player player;
    public Weapon weapon;
    public Boolean gold;
    public ItemStack accessory;

    public AdvancedWorkbench(Player player) {
        cache.add(this);
        this.player = player;
        gui = Bukkit.createInventory(null, 54, "Amboss");
        gui.setContents(TEMPLATE);
    }

    public void checkRecipes() {
        Weapon weapon = null;
        Boolean gold = null;
        for (AdvancedRecipe recipe : AdvancedRecipe.cache) {
            for (int slot : CRAFT_SLOTS) {
                MaterialType type = recipe.ingredients.get(slot);
                ItemStack ingredient = gui.getItem(slot);
                if (ingredient == null && type == null) {
                    continue;
                }
                if (ingredient == null & type != null || type == null & ingredient != null) {
                    weapon = null;
                    gold = null;
                    break;
                }
                if (ingredient.getAmount() != 1 || !type.materials.contains(ingredient.getType())) {
                    weapon = null;
                    gold = null;
                    break;
                }
                if (type == MaterialType.HANDLE && MaterialType.HANDLE.materials.contains(ingredient.getType()) && ingredient.getType() != Material.STICK) {
                    accessory = ingredient;
                }
                if (type == MaterialType.BLADE) {
                    if (gold == null) {
                        gold = ingredient.getType() == Material.GOLD_INGOT;
                    } else if (gold & ingredient.getType() != Material.GOLD_INGOT || !gold && ingredient.getType() == Material.GOLD_INGOT) {
                        weapon = null;
                        gold = null;
                        break;
                    }
                }
                if (weapon == null) {
                    weapon = recipe.result;
                }
            }
            if (weapon != null && gold != null) {
                break;
            }
        }
        this.weapon = weapon;
        this.gold = gold;
        if (weapon != null && gold != null) {
            gui.setItem(RESULT_SLOT, weapon.toItemStack(gold, -1, player.getName(), accessory));
        } else {
            gui.setItem(RESULT_SLOT, null);
        }
    }

}
