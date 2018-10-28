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
import java.util.UUID;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * @author Daniel Saukel
 */
public enum Weapon implements Equipment {

    DAGGER("Dolch", SWORD, 3.0, 0.0, new AdvancedRecipe(new ImmutableMap.Builder<Integer, Component>()
            .put(20, BLADE)
            .put(29, BLADE)
            .put(37, CROSSGUARD)
            .put(38, HANDLE)
            .put(39, CROSSGUARD)
            .put(47, HANDLE)
            .build())),
    KATANA("Katana", SWORD, 4.8, -2.15, new AdvancedRecipe(new ImmutableMap.Builder<Integer, Component>()
            .put(2, BLADE)
            .put(11, BLADE)
            .put(20, BLADE)
            .put(29, BLADE)
            .put(38, HANDLE)
            .put(47, HANDLE)
            .build())),
    LONGSWORD("Langschwert", SWORD, 7.0, -2.8, new AdvancedRecipe(new ImmutableMap.Builder<Integer, Component>()
            .put(2, BLADE)
            .put(11, BLADE)
            .put(20, BLADE)
            .put(27, CROSSGUARD)
            .put(28, CROSSGUARD)
            .put(29, BLADE)
            .put(30, CROSSGUARD)
            .put(31, CROSSGUARD)
            .put(38, HANDLE)
            .put(47, HANDLE)
            .build())),
    PIRATE_SABER("Piratens\u00e4bel", SWORD, 4.2, -1.9),
    RAPIER("Rapier", SWORD, 4.1, -1.7, new AdvancedRecipe(new ImmutableMap.Builder<Integer, Component>()
            .put(2, BLADE)
            .put(11, BLADE)
            .put(20, BLADE)
            .put(27, CROSSGUARD)
            .put(28, CROSSGUARD)
            .put(29, BLADE)
            .put(37, CROSSGUARD)
            .put(38, HANDLE)
            .put(47, HANDLE)
            .build())),
    KNIGHTLY_SWORD("Ritterschwert", SWORD, 5.5, -2.4, new AdvancedRecipe(new ImmutableMap.Builder<Integer, Component>()
            .put(11, BLADE)
            .put(12, BLADE)
            .put(20, BLADE)
            .put(21, BLADE)
            .put(27, CROSSGUARD)
            .put(28, CROSSGUARD)
            .put(29, BLADE)
            .put(30, BLADE)
            .put(31, CROSSGUARD)
            .put(32, CROSSGUARD)
            .put(38, HANDLE)
            .put(39, HANDLE)
            .put(47, HANDLE)
            .put(48, HANDLE)
            .build())),
    LUMBERJACK_AXE("Holzf\u00e4lleraxt", AXE, 4.5, -3.25, new AdvancedRecipe(new ImmutableMap.Builder<Integer, Component>()
            .put(1, BLADE)
            .put(3, HANDLE)
            .put(10, BLADE)
            .put(11, BLADE)
            .put(12, HANDLE)
            .put(19, BLADE)
            .put(21, HANDLE)
            .put(30, HANDLE)
            .put(39, HANDLE)
            .put(48, HANDLE)
            .build())),
    BATTLEAXE("Streitaxt", AXE, 8.0, -3.25, new AdvancedRecipe(new ImmutableMap.Builder<Integer, Component>()
            .put(1, BLADE)
            .put(3, HANDLE)
            .put(5, BLADE)
            .put(10, BLADE)
            .put(11, BLADE)
            .put(12, HANDLE)
            .put(13, BLADE)
            .put(14, BLADE)
            .put(19, BLADE)
            .put(23, BLADE)
            .put(21, HANDLE)
            .put(30, HANDLE)
            .put(39, HANDLE)
            .put(48, HANDLE)
            .build()));

    private String name;
    private EquipmentType base;
    private double damage;
    private double speed;
    private AdvancedRecipe recipe;

    private Weapon(String name, EquipmentType base, double damage, double speed) {
        this.name = name;
        this.base = base;
        this.damage = damage;
        this.speed = speed;
    }

    private Weapon(String name, EquipmentType base, double damage, double speed, AdvancedRecipe recipe) {
        this.name = name;
        this.base = base;
        this.damage = damage;
        this.speed = speed;
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

    public double getDamage() {
        return damage;
    }

    public double getSpeed() {
        return speed;
    }

    @Override
    public AdvancedRecipe getRecipe() {
        return recipe;
    }

    @Override
    public ItemStack toItemStack(MaterialType type, int quality, String smith, String origin, ItemStack accessory) {
        ItemStack stack = new ItemStack(base.getMaterial(type));
        double damage = this.damage;
        double speed = this.speed;
        ItemMeta meta = stack.getItemMeta();
        if (quality == 5) {
            damage += damage / 10;
            speed += speed / 10;
            if (new Random().nextInt(100) > 90) {
                meta.addEnchant(Enchantment.FIRE_ASPECT, 1, true);
            }
            if (new Random().nextInt(100) > 70) {
                meta.addEnchant(Enchantment.DURABILITY, 2, true);
            }
        } else if (quality == 4) {
            damage += damage / 20;
            speed += speed / 20;
            if (new Random().nextInt(100) > 70) {
                meta.addEnchant(Enchantment.DURABILITY, 1, true);
            }
        } else if (quality == 2) {
            damage -= damage / 20;
            speed -= speed / 20;
            if (new Random().nextInt(100) > 90) {
                meta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
            } else if (new Random().nextInt() > 60) {
                AttributeModifier mod = new AttributeModifier(UUID.randomUUID(), "reforgedre.curse1", -0.1, Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HAND);
                meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, mod);
            }
        } else if (quality == 1) {
            damage -= damage / 10;
            speed -= speed / 10;
            if (new Random().nextInt(100) > 75) {
                meta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
            } else if (new Random().nextInt() > 60) {
                AttributeModifier mod = new AttributeModifier(UUID.randomUUID(), "reforgedre.curse2", -0.2, Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HAND);
                meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, mod);
            }
        }
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "reforgedre.attackDamage", damage, Operation.ADD_NUMBER, EquipmentSlot.HAND));
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "reforgedre.attackSpeed", speed, Operation.ADD_NUMBER, EquipmentSlot.HAND));
        applyVisualMeta(meta, quality, smith, origin, accessory);
        stack.setItemMeta(meta);
        return stack;
    }

}
