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

import de.erethon.reforgedre.AdvancedRecipe.MaterialType;
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
    public static final ItemStack SWITCH = new ItemStack(Material.WORKBENCH);
    public static final int RESULT_SLOT = 35;
    public static final int[] CRAFT_SLOTS = {2, 3, 11, 12, 20, 21, 27, 28, 29, 30, 31, 32, 37, 38, 39, 40, 47, 48};
    public static final ItemStack[] SWORD_TEMPLATE = new ItemStack[54];
    public static final ItemStack[] AXE_TEMPLATE = new ItemStack[54];
    public static final ItemStack[] HELMET_TEMPLATE = new ItemStack[54];
    public static final ItemStack[] CHESTPLATE_TEMPLATE = new ItemStack[54];
    public static final ItemStack[] LEGGINGS_TEMPLATE = new ItemStack[54];
    public static final ItemStack[] BOOTS_TEMPLATE = new ItemStack[54];

    static {
        ItemMeta swMeta = SWITCH.getItemMeta();
        swMeta.setDisplayName(ChatColor.YELLOW + "Template wechseln");
        SWITCH.setItemMeta(swMeta);
        ItemMeta plMeta = PLACEHOLDER.getItemMeta();
        plMeta.setDisplayName(ChatColor.RESET.toString());
        PLACEHOLDER.setItemMeta(plMeta);
        SWORD_TEMPLATE[0] = PLACEHOLDER;
        SWORD_TEMPLATE[1] = PLACEHOLDER;
        SWORD_TEMPLATE[4] = PLACEHOLDER;
        SWORD_TEMPLATE[5] = PLACEHOLDER;
        SWORD_TEMPLATE[6] = PLACEHOLDER;
        SWORD_TEMPLATE[7] = PLACEHOLDER;
        SWORD_TEMPLATE[8] = SWITCH;
        SWORD_TEMPLATE[9] = PLACEHOLDER;
        SWORD_TEMPLATE[10] = PLACEHOLDER;
        SWORD_TEMPLATE[13] = PLACEHOLDER;
        SWORD_TEMPLATE[14] = PLACEHOLDER;
        SWORD_TEMPLATE[15] = PLACEHOLDER;
        SWORD_TEMPLATE[16] = PLACEHOLDER;
        SWORD_TEMPLATE[17] = PLACEHOLDER;
        SWORD_TEMPLATE[18] = PLACEHOLDER;
        SWORD_TEMPLATE[19] = PLACEHOLDER;
        SWORD_TEMPLATE[22] = PLACEHOLDER;
        SWORD_TEMPLATE[23] = PLACEHOLDER;
        SWORD_TEMPLATE[24] = PLACEHOLDER;
        SWORD_TEMPLATE[25] = PLACEHOLDER;
        SWORD_TEMPLATE[26] = PLACEHOLDER;
        SWORD_TEMPLATE[33] = PLACEHOLDER;
        SWORD_TEMPLATE[34] = PLACEHOLDER;
        SWORD_TEMPLATE[36] = PLACEHOLDER;
        SWORD_TEMPLATE[41] = PLACEHOLDER;
        SWORD_TEMPLATE[42] = PLACEHOLDER;
        SWORD_TEMPLATE[43] = PLACEHOLDER;
        SWORD_TEMPLATE[44] = PLACEHOLDER;
        SWORD_TEMPLATE[45] = PLACEHOLDER;
        SWORD_TEMPLATE[46] = PLACEHOLDER;
        SWORD_TEMPLATE[49] = PLACEHOLDER;
        SWORD_TEMPLATE[50] = PLACEHOLDER;
        SWORD_TEMPLATE[51] = PLACEHOLDER;
        SWORD_TEMPLATE[52] = PLACEHOLDER;
        SWORD_TEMPLATE[53] = PLACEHOLDER;
    }

    public enum Type {
        SWORD(SWORD_TEMPLATE),
        AXE(AXE_TEMPLATE),
        HELMET(HELMET_TEMPLATE),
        CHESTPLATE(CHESTPLATE_TEMPLATE),
        LEGGINGS(LEGGINGS_TEMPLATE),
        BOOTS(BOOTS_TEMPLATE);
        ItemStack[] template;

        Type(ItemStack[] template) {

        }
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
        gui.setContents(SWORD_TEMPLATE);
    }

    public void checkRecipes() {
        Boolean gold = null;
        Weapon checked = null;
        for (Weapon weapon : Weapon.values()) {
            for (int slot : CRAFT_SLOTS) {
                MaterialType type = weapon.recipe.ingredients.get(slot);
                ItemStack ingredient = gui.getItem(slot);
                if (ingredient == null && type == null) {
                    continue;
                }
                if (ingredient == null & type != null || type == null & ingredient != null) {
                    checked = null;
                    gold = null;
                    break;
                }
                if (ingredient.getAmount() != 1 || !type.materials.contains(ingredient.getType())) {
                    checked = null;
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
                        checked = null;
                        gold = null;
                        break;
                    }
                }
                if (checked == null) {
                    checked = weapon;
                }
            }
            if (checked != null && gold != null) {
                break;
            }
        }
        weapon = checked;
        this.gold = gold;
        if (weapon != null && gold != null) {
            gui.setItem(RESULT_SLOT, weapon.toItemStack(gold, -1, player.getName(), Weapon.getOrigin(player), accessory));
        } else {
            gui.setItem(RESULT_SLOT, null);
        }
    }

}
