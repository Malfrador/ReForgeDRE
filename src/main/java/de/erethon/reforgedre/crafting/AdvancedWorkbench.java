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

import de.erethon.caliburn.CaliburnAPI;
import de.erethon.reforgedre.ReConfig;
import de.erethon.reforgedre.equipment.Component;
import de.erethon.reforgedre.equipment.CustomWeapon;
import de.erethon.reforgedre.equipment.ForgedEquipment;
import de.erethon.reforgedre.equipment.MaterialType;
import java.util.HashSet;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Furnace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * @author Daniel Saukel
 */
public class AdvancedWorkbench {

    public static final ItemStack PLACEHOLDER = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
    public static final ItemStack SWITCH = new ItemStack(Material.CRAFTING_TABLE);
    public static final int SWITCH_SLOT = 8;
    public static final int RESULT_SLOT = 35;
    public static final ItemStack[] SWORD_TEMPLATE = new ItemStack[54];
    public static final ItemStack[] AXE_TEMPLATE = new ItemStack[54];
    public static final ItemStack[] HELMET_TEMPLATE = new ItemStack[54];
    public static final ItemStack[] CHESTPLATE_TEMPLATE = new ItemStack[54];
    public static final ItemStack[] LEGGINGS_TEMPLATE = new ItemStack[54];
    public static final ItemStack[] BOOTS_TEMPLATE = new ItemStack[54];

    static {
        ItemMeta swMeta = SWITCH.getItemMeta();
        swMeta.setDisplayName(ReConfig.ANVIL_CHANGE_TEMPLATE);
        SWITCH.setItemMeta(swMeta);
        ItemMeta plMeta = PLACEHOLDER.getItemMeta();
        plMeta.setDisplayName(ChatColor.RESET.toString());
        PLACEHOLDER.setItemMeta(plMeta);

        int i = -1;
        i = -1;
        slots:
        while (i != 53) {
            i++;
            for (int slot : Type.SWORD.craftSlots) {
                if (slot == i) {
                    continue slots;
                }
            }
            if (SWITCH_SLOT != i && RESULT_SLOT != i) {
                SWORD_TEMPLATE[i] = PLACEHOLDER;
            }
        }
        SWORD_TEMPLATE[8] = SWITCH;

        i = -1;
        slots:
        while (i != 53) {
            i++;
            for (int slot : Type.AXE.craftSlots) {
                if (slot == i) {
                    continue slots;
                }
            }
            if (SWITCH_SLOT != i && RESULT_SLOT != i) {
                AXE_TEMPLATE[i] = PLACEHOLDER;
            }
        }
        AXE_TEMPLATE[8] = SWITCH;

        i = -1;
        slots:
        while (i != 53) {
            i++;
            for (int slot : Type.HELMET.craftSlots) {
                if (slot == i) {
                    continue slots;
                }
            }
            if (SWITCH_SLOT != i && RESULT_SLOT != i) {
                HELMET_TEMPLATE[i] = PLACEHOLDER;
            }
        }
        HELMET_TEMPLATE[8] = SWITCH;

        i = -1;
        slots:
        while (i != 53) {
            i++;
            for (int slot : Type.CHESTPLATE.craftSlots) {
                if (slot == i) {
                    continue slots;
                }
            }
            if (SWITCH_SLOT != i && RESULT_SLOT != i) {
                CHESTPLATE_TEMPLATE[i] = PLACEHOLDER;
            }
        }
        CHESTPLATE_TEMPLATE[8] = SWITCH;

        i = -1;
        slots:
        while (i != 53) {
            i++;
            for (int slot : Type.LEGGINGS.craftSlots) {
                if (slot == i) {
                    continue slots;
                }
            }
            if (SWITCH_SLOT != i && RESULT_SLOT != i) {
                LEGGINGS_TEMPLATE[i] = PLACEHOLDER;
            }
        }
        LEGGINGS_TEMPLATE[8] = SWITCH;

        i = -1;
        slots:
        while (i != 53) {
            i++;
            for (int slot : Type.BOOTS.craftSlots) {
                if (slot == i) {
                    continue slots;
                }
            }
            if (SWITCH_SLOT != i && RESULT_SLOT != i) {
                BOOTS_TEMPLATE[i] = PLACEHOLDER;
            }
        }
        BOOTS_TEMPLATE[8] = SWITCH;
    }

    public enum Type {

        SWORD(SWORD_TEMPLATE, 2, 3, 11, 12, 20, 21, 27, 28, 29, 30, 31, 32, 37, 38, 39, 40, 47, 48),
        AXE(AXE_TEMPLATE, 1, 3, 5, 10, 11, 12, 13, 14, 19, 21, 23, 30, 39, 48),
        HELMET(HELMET_TEMPLATE, 0, 1, 2, 3, 4, 9, 10, 11, 12, 13, 18, 19, 20, 21, 22),
        CHESTPLATE(CHESTPLATE_TEMPLATE, 0, 1, 5, 6, 9, 10, 11, 13, 14, 15, 19, 20, 21, 22, 23, 28, 29, 30, 31, 32, 37, 38, 39, 40, 41, 47, 48, 49),
        LEGGINGS(LEGGINGS_TEMPLATE, 0, 1, 2, 3, 4, 5, 9, 10, 11, 12, 13, 14, 18, 19, 22, 23, 27, 28, 31, 32, 36, 37, 40, 41, 45, 46, 49, 50),
        BOOTS(BOOTS_TEMPLATE, 0, 1, 9, 10, 11, 18, 19, 20, 21, 22, 27, 28, 29, 30, 31);

        ItemStack[] template;
        int[] craftSlots;

        private Type(ItemStack[] template, int... craftSlots) {
            this.template = template;
            this.craftSlots = craftSlots;
        }

    }

    static final Set<AdvancedWorkbench> cache = new HashSet<>();

    public Type type;
    public Inventory gui;
    public Player player;
    public CustomWeapon weapon;
    public MaterialType materialType;
    public ItemStack accessory;

    public AdvancedWorkbench(Player player) {
        cache.add(this);
        this.player = player;
        gui = Bukkit.createInventory(null, 54, ReConfig.ANVIL_NAME);
        applyType(Type.SWORD);
    }

    public void applyType(Type type) {
        this.type = type;
        gui.clear();
        gui.setContents(type.template);
    }

    public void nextType() {
        for (Type next : Type.values()) {
            if (type == next) {
                type = null;
            } else if (type == null) {
                applyType(next);
            }
        }
        if (type == null) {
            applyType(Type.SWORD);
        }
    }

    public void checkRecipes() {
        MaterialType materialType = null;
        CustomWeapon checked = null;
        for (CustomWeapon weapon : CaliburnAPI.getInstance().getExItems(CustomWeapon.class)) {
            if (weapon.getRecipe() == null) {
                continue;
            }
            for (int slot : type.craftSlots) {
                Component type = weapon.getRecipe().getIngredients().get(slot);
                ItemStack ingredient = gui.getItem(slot);
                if (ingredient == null && type == null) {
                    continue;
                }
                if (ingredient == null & type != null || type == null & ingredient != null) {
                    checked = null;
                    materialType = null;
                    break;
                }
                if (ingredient.getAmount() != 1 || !type.getMaterials().contains(ingredient.getType())) {
                    checked = null;
                    materialType = null;
                    break;
                }
                if (type == Component.HANDLE && Component.HANDLE.getMaterials().contains(ingredient.getType()) && ingredient.getType() != Material.STICK) {
                    accessory = ingredient;
                }
                if (type == Component.BLADE) {
                    if (materialType == null) {
                        materialType = MaterialType.getByMaterial(ingredient.getType());
                    } else if (!materialType.getMaterials().contains(ingredient.getType())) {
                        checked = null;
                        materialType = null;
                        break;
                    }
                }
                if (checked == null) {
                    checked = weapon;
                }
            }
            if (checked != null && materialType != null) {
                break;
            }
        }
        weapon = checked;
        this.materialType = materialType;
        if (weapon != null && materialType != null) {
            gui.setItem(RESULT_SLOT, weapon.toItemStack(materialType, -1, player.getName(), ForgedEquipment.getOrigin(player), accessory));
        } else {
            gui.setItem(RESULT_SLOT, null);
        }
    }

    public static boolean isAdvancedWorkbench(Block block) {
        if (block.getType() == Material.ANVIL) {
            return block.getRelative(BlockFace.EAST).getState() instanceof Furnace || block.getRelative(BlockFace.WEST).getState() instanceof Furnace
                    || block.getRelative(BlockFace.NORTH).getState() instanceof Furnace || block.getRelative(BlockFace.SOUTH).getState() instanceof Furnace
                    || block.getRelative(BlockFace.DOWN).getState() instanceof Furnace;
        } else if (block.getState() instanceof Furnace) {
            return block.getRelative(BlockFace.EAST).getType() == Material.ANVIL || block.getRelative(BlockFace.WEST).getType() == Material.ANVIL
                    || block.getRelative(BlockFace.NORTH).getType() == Material.ANVIL || block.getRelative(BlockFace.SOUTH).getType() == Material.ANVIL
                    || block.getRelative(BlockFace.DOWN).getType() == Material.ANVIL;
        } else {
            return false;
        }
    }

}
