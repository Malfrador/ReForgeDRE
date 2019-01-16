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

import de.erethon.caliburn.CaliburnAPI;
import de.erethon.caliburn.item.CustomEquipment;
import de.erethon.reforgedre.crafting.AdvancedRecipe;
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
public class CustomWeapon extends CustomEquipment implements ForgedEquipment {

    private EquipmentType base;
    private double damage;
    private double speed;
    private AdvancedRecipe recipe;

    public CustomWeapon(String name, EquipmentType base, double damage, double speed) {
        super(CaliburnAPI.getInstance(), name);
        this.base = base;
        this.damage = damage;
        this.speed = speed;
    }

    public CustomWeapon(String name, EquipmentType base, double damage, double speed, AdvancedRecipe recipe) {
        this(name, base, damage, speed);
        this.recipe = recipe;
    }

    @Override
    public String getName() {
        return super.getId();
    }

    @Override
    public EquipmentType getBaseType() {
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
