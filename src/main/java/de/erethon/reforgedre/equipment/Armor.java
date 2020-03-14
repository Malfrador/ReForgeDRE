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

import com.google.common.collect.ImmutableMap;
import static de.erethon.reforgedre.equipment.Component.*;
import static de.erethon.reforgedre.equipment.EquipmentType.*;
import java.util.Random;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * @author Daniel Saukel
 */
public enum Armor implements Equipment {

    PLATE_CHEST("Plattenrüstung", CHESTPLATE, 15.0, 0.0, new AdvancedRecipe(new ImmutableMap.Builder<Integer, Component>()
            .put(0, METAL)
            .put(1, METAL)
            .put(5, METAL)
            .put(6, METAL) // 1st Row
            .put(9, METAL)
            .put(10, METAL)
            .put(11, METAL)
            .put(13, METAL)
            .put(14, METAL)
            .put(15, METAL) // 2nd Row
            .put(19, METAL)
            .put(20, METAL)
            .put(21, METAL)     // Center
            .put(22, METAL)
            .put(23, METAL) // 3rd Row
            .put(28, METAL)
            .put(29, METAL)
            .put(30, METAL)     // Center
            .put(31, METAL)
            .put(32, METAL) // 4th Row
            .put(37, METAL)
            .put(38, METAL)
            .put(39, METAL)     // Center
            .put(40, METAL)
            .put(41, METAL) // 5th Row
            .put(47, METAL)
            .put(48, METAL)
            .put(49, METAL) // 6th Row
            .build())),

    SCALE_CHEST("Schuppenrüstung", CHESTPLATE, 10.0, 0.0, new AdvancedRecipe(new ImmutableMap.Builder<Integer, Component>()
            .put(0, METAL)
            .put(1, METAL)
            .put(5, METAL)
            .put(6, METAL) // 1st Row
            .put(9, CHAIN)
            .put(10, CHAIN)
            .put(11, CHAIN)
            .put(13, CHAIN)
            .put(14, CHAIN)
            .put(15, CHAIN) // 2nd Row
            .put(19, METAL)
            .put(20, METAL)
            .put(21, METAL)
            .put(22, METAL)
            .put(23, METAL) // 3rd Row
            .put(28, CHAIN)
            .put(29, CHAIN)
            .put(30, CHAIN)
            .put(31, CHAIN)
            .put(32, CHAIN) // 4th Row
            .put(37, METAL)
            .put(38, METAL)
            .put(39, METAL)
            .put(40, METAL)
            .put(41, METAL) // 5th Row
            .put(47, CHAIN)
            .put(48, CHAIN)
            .put(49, CHAIN) // 6th Row
            .build())),

    CHAIN_CHEST("Kettenrüstung", CHESTPLATE, 8.0, 0.0, new AdvancedRecipe(new ImmutableMap.Builder<Integer, Component>()
            .put(0,CHAIN)
            .put(1,CHAIN)
            .put(5,CHAIN)
            .put(6,CHAIN) // 1st Row
            .put(9,CHAIN)
            .put(10, CHAIN)
            .put(11, CHAIN)
            .put(13, CHAIN)
            .put(14, CHAIN)
            .put(15, CHAIN) // 2nd Row
            .put(19, CHAIN)
            .put(20, CHAIN)
            .put(21, CHAIN)     // Center
            .put(22, CHAIN)
            .put(23, CHAIN) // 3rd Row
            .put(28, CHAIN)
            .put(29, CHAIN)
            .put(30, CHAIN)     // Center
            .put(31, CHAIN)
            .put(32, CHAIN) // 4th Row
            .put(37, CHAIN)
            .put(38, CHAIN)
            .put(39, CHAIN)     // Center
            .put(40, CHAIN)
            .put(41, CHAIN) // 5th Row
            .put(47, CHAIN)
            .put(48, CHAIN)
            .put(49, CHAIN) // 6th Row
            .build())),

    LINEN_CHEST("Leinenrüstung", CHESTPLATE, 5.0, 0.0, new AdvancedRecipe(new ImmutableMap.Builder<Integer, Component>()
            .put(0, FABRIC)
            .put(1, FABRIC)
            .put(5, FABRIC)
            .put(6, FABRIC) // 1st Row
            .put(9, FABRIC)
            .put(10, FABRIC)
            .put(11, FABRIC)
            .put(13, FABRIC)
            .put(14, FABRIC)
            .put(15, FABRIC) // 2nd Row
            .put(19, FABRIC)
            .put(20, FABRIC)
            .put(21, FABRIC)     // Center
            .put(22, FABRIC)
            .put(23, FABRIC) // 3rd Row
            .put(28, FABRIC)
            .put(29, FABRIC)
            .put(30, FABRIC)     // Center
            .put(31, FABRIC)
            .put(32, FABRIC) // 4th Row
            .put(37, FABRIC)
            .put(38, FABRIC)
            .put(39, FABRIC)     // Center
            .put(40, FABRIC)
            .put(41, FABRIC) // 5th Row
            .put(47, FABRIC)
            .put(48, FABRIC)
            .put(49, FABRIC) // 6th Row
            .build())),

    // ^ Chest ^

    PLATE_LEGS("Plattenrüstung", LEGGINGS , 10.0, 0.0, new AdvancedRecipe(new ImmutableMap.Builder<Integer, Component>()
            .put(0, METAL)
            .put(1, METAL)
            .put(2, METAL)
            .put(3, METAL)
            .put(4, METAL)
            .put(5, METAL) // 1st Row
            .put(9, METAL)
            .put(10, METAL)
            .put(11, METAL)
            .put(12, METAL)
            .put(13, METAL)
            .put(14, METAL) // 2nd Row
            .put(18, METAL)
            .put(19, METAL)
            .put(22, METAL)
            .put(23, METAL)
            .put(27, METAL)
            .put(28, METAL)
            .put(31, METAL)
            .put(32, METAL)
            .put(36, METAL)
            .put(37, METAL)
            .put(40, METAL)
            .put(41, METAL)
            .put(45, METAL)
            .put(46, METAL)
            .put(49, METAL)
            .put(50, METAL)
            .build())),

    SCALE_LEGS("Schuppenrüstung", LEGGINGS, 9.0, 0.0, new AdvancedRecipe(new ImmutableMap.Builder<Integer, Component>()
            .put(0, METAL)
            .put(1, METAL)
            .put(2, METAL)
            .put(3, METAL)
            .put(4, METAL)
            .put(5, METAL) // 1st Row
            .put(9, METAL)
            .put(10, METAL)
            .put(11, METAL)
            .put(12, METAL)
            .put(13, METAL)
            .put(14, METAL) // 2nd Row
            .put(18, CHAIN)
            .put(19, CHAIN)
            .put(22, CHAIN)
            .put(23, CHAIN)
            .put(27, CHAIN)
            .put(28, CHAIN)
            .put(31, CHAIN)
            .put(32, CHAIN)
            .put(36, CHAIN)
            .put(37, CHAIN)
            .put(40, CHAIN)
            .put(41, CHAIN)
            .put(45, CHAIN)
            .put(46, CHAIN)
            .put(49, CHAIN)
            .put(50, CHAIN)
            .build())),
    CHAIN_LEGS("Kettenrüstung", LEGGINGS, 7.5, 0.0, new AdvancedRecipe(new ImmutableMap.Builder<Integer, Component>()
            .put(0, CHAIN)
            .put(1, CHAIN)
            .put(2, CHAIN)
            .put(3, CHAIN)
            .put(4, CHAIN)
            .put(5, CHAIN) // 1st Row
            .put(9, CHAIN)
            .put(10, CHAIN)
            .put(11, CHAIN)
            .put(12, CHAIN)
            .put(13, CHAIN)
            .put(14, CHAIN) // 2nd Row
            .put(18, CHAIN)
            .put(19, CHAIN)
            .put(22, CHAIN)
            .put(23, CHAIN)
            .put(27, CHAIN)
            .put(28, CHAIN)
            .put(31, CHAIN)
            .put(32, CHAIN)
            .put(36, CHAIN)
            .put(37, CHAIN)
            .put(40, CHAIN)
            .put(41, CHAIN)
            .put(45, CHAIN)
            .put(46, CHAIN)
            .put(49, CHAIN)
            .put(50, CHAIN)
            .build())),
    LINEN_LEGS("Leinenrüstung", LEGGINGS, 5.0, 0.0, new AdvancedRecipe(new ImmutableMap.Builder<Integer, Component>()
            .put(0, FABRIC)
            .put(1, FABRIC)
            .put(2, FABRIC)
            .put(3, FABRIC)
            .put(4, FABRIC)
            .put(5, FABRIC) // 1st Row
            .put(9, FABRIC)
            .put(10, FABRIC)
            .put(11, FABRIC)
            .put(12, FABRIC)
            .put(13, FABRIC)
            .put(14, FABRIC) // 2nd Row
            .put(18, FABRIC)
            .put(19, FABRIC)
            .put(22, FABRIC)
            .put(23, FABRIC)
            .put(27, FABRIC)
            .put(28, FABRIC)
            .put(31, FABRIC)
            .put(32, FABRIC)
            .put(36, FABRIC)
            .put(37, FABRIC)
            .put(40, FABRIC)
            .put(41, FABRIC)
            .put(45, FABRIC)
            .put(46, FABRIC)
            .put(49, FABRIC)
            .put(50, FABRIC)
            .build())),
    // ^ Leg slots: 0, 1, 2, 3, 4, 5, 9, 10, 11, 12, 13, 14, 18, 19, 22, 23, 27, 28, 31, 32, 36, 37, 40, 41, 45, 46, 49, 50 ^

    PLATE_BOOTS("Plattenrüstung", BOOTS, 7.0, 0.0, new AdvancedRecipe(new ImmutableMap.Builder<Integer, Component>()
            .put(0, METAL)
            .put(1, METAL)    // 1st Row
            .put(9, METAL)
            .put(10, METAL)
            .put(11, METAL)     // 2nd
            .put(18, METAL)
            .put(19, METAL)
            .put(20, METAL)
            .put(21, METAL)
            .put(22, METAL)     // 3rd
            .put(27, METAL)
            .put(28, METAL)
            .put(29, METAL)
            .put(30, METAL)
            .put(31, METAL)
            .build())),

    SCALE_BOOTS("Schuppenrüstung", BOOTS, 5.0, 0.0, new AdvancedRecipe(new ImmutableMap.Builder<Integer, Component>() // Funktioniert nicht
            .put(0, CHAIN)
            .put(1, CHAIN)    // 1st Row
            .put(9, CHAIN)
            .put(10, CHAIN)
            .put(11, CHAIN)     // 2nd
            .put(18, CHAIN)
            .put(19, CHAIN)
            .put(20, CHAIN)
            .put(21, CHAIN)
            .put(22, CHAIN)     // 3rd
            .put(27, METAL)
            .put(28, METAL)
            .put(29, METAL)
            .put(30, METAL)
            .put(31, METAL)
            .build())),

    CHAIN_BOOTS("Kettenrüstung", BOOTS, 4.0, 0.0, new AdvancedRecipe(new ImmutableMap.Builder<Integer, Component>()
            .put(0, CHAIN)
            .put(1, CHAIN)    // 1st Row
            .put(9, CHAIN)
            .put(10, CHAIN)
            .put(11, CHAIN)     // 2nd
            .put(18, CHAIN)
            .put(19, CHAIN)
            .put(20, CHAIN)
            .put(21, CHAIN)
            .put(22, CHAIN)     // 3rd
            .put(27, CHAIN)
            .put(28, CHAIN)
            .put(29, CHAIN)
            .put(30, CHAIN)
            .put(31, CHAIN)
            .build())),

    LINEN_BOOTS("Leinenrüstung", BOOTS, 3.0, 0.0, new AdvancedRecipe(new ImmutableMap.Builder<Integer, Component>()
            .put(0, FABRIC)
            .put(1, FABRIC)    // 1st Row
            .put(9, FABRIC)
            .put(10, FABRIC)
            .put(11, FABRIC)     // 2nd
            .put(18, FABRIC)
            .put(19, FABRIC)
            .put(20, FABRIC)
            .put(21, FABRIC)
            .put(22, FABRIC)     // 3rd
            .put(27, FABRIC)
            .put(28, FABRIC)
            .put(29, FABRIC)
            .put(30, FABRIC)
            .put(31, FABRIC)
            .build())),


    // ^ Boot Slots: 0, 1, 9, 10, 11, 18, 19, 20, 21, 22, 27, 28, 29, 30, 31 ^

    PLATE_HELMET("Plattenrüstung", HELMET, 6.0, 0.0, new AdvancedRecipe(new ImmutableMap.Builder<Integer, Component>()
            .put(0, METAL)
            .put(1, METAL)
            .put(2, METAL)
            .put(3, METAL)
            .put(4, METAL)     // 1st
            .put(9, METAL)
            .put(10, METAL)
            .put(11, METAL)
            .put(12, METAL)
            .put(13, METAL)    // 2nd
            .put(18, METAL)
            .put(19, METAL)
            .put(20, METAL)
            .put(21, METAL)
            .put(22, METAL)
            .build())),

    SCALE_HELMET("Schuppenrüstung", HELMET, 5.0, 0.0, new AdvancedRecipe(new ImmutableMap.Builder<Integer, Component>()
            .put(0, METAL)
            .put(1, METAL)
            .put(2, METAL)
            .put(3, METAL)
            .put(4, METAL)     // 1st
            .put(9, CHAIN)
            .put(10, CHAIN)
            .put(11, CHAIN)
            .put(12, CHAIN)
            .put(13, CHAIN)    // 2nd
            .put(18, METAL)
            .put(19, METAL)
            .put(20, METAL)
            .put(21, METAL)
            .put(22, METAL)
            .build())),

    CHAIN_HELMET("Kettenrüstung", HELMET, 4.0, 0.0, new AdvancedRecipe(new ImmutableMap.Builder<Integer, Component>()
            .put(0, CHAIN)
            .put(1, CHAIN)
            .put(2, CHAIN)
            .put(3, CHAIN)
            .put(4, CHAIN)     // 1st
            .put(9, CHAIN)
            .put(10, CHAIN)
            .put(11, CHAIN)
            .put(12, CHAIN)
            .put(13, CHAIN)    // 2nd
            .put(18, CHAIN)
            .put(19, CHAIN)
            .put(20, CHAIN)
            .put(21, CHAIN)
            .put(22, CHAIN)
            .build())),
    LINEN_HELMET("Leinenrüstung", HELMET, 2.0, 0.0, new AdvancedRecipe(new ImmutableMap.Builder<Integer, Component>()
            .put(0, FABRIC)
            .put(1, FABRIC)
            .put(2, FABRIC)
            .put(3, FABRIC)
            .put(4, FABRIC)     // 1st
            .put(9, FABRIC)
            .put(10, FABRIC)
            .put(11, FABRIC)
            .put(12, FABRIC)
            .put(13, FABRIC)    // 2nd
            .put(18, FABRIC)
            .put(19, FABRIC)
            .put(20, FABRIC)
            .put(21, FABRIC)
            .put(22, FABRIC)
            .build()));

    // ^ Helmet Slots: 0, 1, 2, 3, 4, 9, 10, 11, 12, 13, 18, 19, 20, 21, 22 ^

    private String name;
    private EquipmentType base;
    private double armor;
    private double armorToughness;
    private AdvancedRecipe recipe;

    private Armor(String name, EquipmentType base, double armor, double armorToughness) {
        this.name = name;
        this.base = base;
        this.armor = armor;
        this.armorToughness = armorToughness;
    }

    private Armor(String name, EquipmentType base, double armor, double armorToughness, AdvancedRecipe recipe) {
        this.name = name;
        this.base = base;
        this.armor = armor;
        this.armorToughness = armorToughness;
        this.recipe = recipe;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public EquipmentType getBase() {
        return base;
    }

    @Override
    public AdvancedRecipe getRecipe() {
        return recipe;
    }

    @Override
    public ItemStack toItemStack(MaterialType type, int quality, String smith, String origin, ItemStack accessory) {
        ItemStack stack = new ItemStack(base.getMaterial(type));
        ItemMeta meta = stack.getItemMeta();
        if (quality == 5) {
            if (new Random().nextInt(100) > 90) {
                meta.addEnchant(Enchantment.FIRE_ASPECT, 1, true);
            }
            if (new Random().nextInt(100) > 70) {
                meta.addEnchant(Enchantment.DURABILITY, 2, true);
            }
        }
        else if (quality == 4) {
            if (new Random().nextInt(100) > 70) {
                meta.addEnchant(Enchantment.DURABILITY, 1, true);
            }
        }
        else if (quality == 2) {
            if (new Random().nextInt(100) > 90) {
                meta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
            }
        }
        else if (quality == 1) {
            if (new Random().nextInt(100) > 75) {
                meta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
            }
        }
        applyVisualMeta(meta, quality, smith, origin, accessory);
        stack.setItemMeta(meta);
        return stack;
    }

}

