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

import de.erethon.commons.item.AttributeWrapper;
import de.erethon.commons.item.InternalAttribute;
import de.erethon.commons.item.InternalOperation;
import de.erethon.commons.item.InternalSlot;
import de.erethon.factionsxl.FactionsXL;
import de.erethon.factionsxl.board.Region;
import de.erethon.factionsxl.config.FMessage;
import de.erethon.reforgedre.AdvancedRecipe.MaterialType;
import static de.erethon.reforgedre.AdvancedRecipe.MaterialType.*;
import static de.erethon.reforgedre.Weapon.Type.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * @author Daniel Saukel
 */
public enum Weapon {

    DAGGER("Dolch", SWORD, 3.0, 0.0, new AdvancedRecipe(new HashMap<Integer, MaterialType>() {
        {
            put(20, BLADE);
            put(29, BLADE);
            put(37, CROSSGUARD);
            put(38, HANDLE);
            put(39, CROSSGUARD);
            put(47, HANDLE);
        }
    })),
    KATANA("Katana", SWORD, 4.8, -2.15, new AdvancedRecipe(new HashMap<Integer, MaterialType>() {
        {
            put(2, BLADE);
            put(11, BLADE);
            put(20, BLADE);
            put(29, BLADE);
            put(38, HANDLE);
            put(47, HANDLE);
        }
    })),
    LONGSWORD("Langschwert", SWORD, 7.0, -2.8, new AdvancedRecipe(new HashMap<Integer, MaterialType>() {
        {
            put(2, BLADE);
            put(11, BLADE);
            put(20, BLADE);
            put(27, CROSSGUARD);
            put(28, CROSSGUARD);
            put(29, BLADE);
            put(30, CROSSGUARD);
            put(31, CROSSGUARD);
            put(38, HANDLE);
            put(47, HANDLE);
        }
    })),
    PIRATE_SABER("Piratens\u00e4bel", SWORD, 4.2, -1.9),
    RAPIER("Rapier", SWORD, 4.1, -1.7, new AdvancedRecipe(new HashMap<Integer, MaterialType>() {
        {
            put(2, BLADE);
            put(11, BLADE);
            put(20, BLADE);
            put(27, CROSSGUARD);
            put(28, CROSSGUARD);
            put(29, BLADE);
            put(37, CROSSGUARD);
            put(38, HANDLE);
            put(47, HANDLE);
        }
    })),
    KNIGHTLY_SWORD("Ritterschwert", SWORD, 5.5, -2.4, new AdvancedRecipe(new HashMap<Integer, MaterialType>() {
        {
            put(11, BLADE);
            put(12, BLADE);
            put(20, BLADE);
            put(21, BLADE);
            put(27, CROSSGUARD);
            put(28, CROSSGUARD);
            put(29, BLADE);
            put(30, BLADE);
            put(31, CROSSGUARD);
            put(32, CROSSGUARD);
            put(38, HANDLE);
            put(39, HANDLE);
            put(47, HANDLE);
            put(48, HANDLE);
        }
    })),
    LUMBERJACK_AXE("Holzf\u00e4lleraxt", AXE, 4.5, -3.25, new AdvancedRecipe(new HashMap<Integer, MaterialType>() {
        {
            put(1, BLADE);
            put(3, HANDLE);
            put(10, BLADE);
            put(11, BLADE);
            put(12, HANDLE);
            put(19, BLADE);
            put(21, HANDLE);
            put(30, HANDLE);
            put(39, HANDLE);
            put(48, HANDLE);
        }
    })),
    BATTLEAXE("Streitaxt", AXE, 8.0, -3.25, new AdvancedRecipe(new HashMap<Integer, MaterialType>() {
        {
            put(1, BLADE);
            put(3, HANDLE);
            put(5, BLADE);
            put(10, BLADE);
            put(11, BLADE);
            put(12, HANDLE);
            put(13, BLADE);
            put(14, BLADE);
            put(19, BLADE);
            put(23, BLADE);
            put(21, HANDLE);
            put(30, HANDLE);
            put(39, HANDLE);
            put(48, HANDLE);
        }
    }));

    public String name;
    public Type base;
    public double damage;
    public double speed;
    public AdvancedRecipe recipe;

    Weapon(String name, Type base, double damage, double speed) {
        this.name = name;
        this.base = base;
        this.damage = damage;
        this.speed = speed;
    }

    Weapon(String name, Type base, double damage, double speed, AdvancedRecipe recipe) {
        this.name = name;
        this.base = base;
        this.damage = damage;
        this.speed = speed;
        this.recipe = recipe;
    }

    public static final String DIAMONDS = ChatColor.GRAY + "Mit Diamanten besetzt.";
    public static final String EMERALDS = ChatColor.GRAY + "Mit Smaragden besetzt.";
    public static final String QUARTZ = ChatColor.GRAY + "Mit Quarz besetzt.";
    public static final String NETHER_STAR = ChatColor.GRAY + "Mit Netherstern besetzt.";
    public static final String PEARLS = ChatColor.GRAY + "Mit Perlen besetzt.";
    public static final String RUBIES = ChatColor.GRAY + "Mit Rubinen besetzt.";

    public enum Type {
        AXE(Material.IRON_AXE, Material.GOLD_AXE),
        SWORD(Material.IRON_SWORD, Material.GOLD_SWORD);

        Material iron;
        Material gold;

        Type(Material iron, Material gold) {
            this.iron = iron;
            this.gold = gold;
        }
    }

    public static final String STAR = "\u2605";

    public static String getOrigin(Player player) {
        Region region = FactionsXL.getInstance().getBoard().getByLocation(player.getLocation());
        return region != null ? region.getName() : FMessage.MISC_WILDERNESS.getMessage();
    }

    public ItemStack toItemStack(boolean gold, int quality, String smith, String origin) {
        return toItemStack(gold, quality, smith, origin, null);
    }

    public ItemStack toItemStack(boolean gold, int quality, String smith, String origin, ItemStack accessory) {
        ItemStack stack = new ItemStack(gold ? base.gold : base.iron);
        double damage = this.damage;
        double speed = this.speed;
        if (quality == 5) {
            damage += damage / 10;
            speed += speed / 10;
            if (new Random().nextInt(100) > 90) {
                stack.addEnchantment(Enchantment.FIRE_ASPECT, 1);
            }
            if (new Random().nextInt(100) > 70) {
                stack.addEnchantment(Enchantment.DURABILITY, 2);
            }
        } else if (quality == 4) {
            damage += damage / 20;
            speed += speed / 20;
            if (new Random().nextInt(100) > 70) {
                stack.addEnchantment(Enchantment.DURABILITY, 1);
            }
        } else if (quality == 2) {
            damage -= damage / 20;
            speed -= speed / 20;
            if (new Random().nextInt(100) > 90) {
                stack.addEnchantment(Enchantment.VANISHING_CURSE, 1);
            } else if (new Random().nextInt() > 60) {
                stack = new AttributeWrapper(InternalAttribute.GENERIC_MOVEMENT_SPEED, -0.1, InternalOperation.MULTIPLY_SCALAR_1, InternalSlot.MAIN_HAND).applyTo(stack);
            }
        } else if (quality == 1) {
            damage -= damage / 10;
            speed -= speed / 10;
            if (new Random().nextInt(100) > 75) {
                stack.addEnchantment(Enchantment.VANISHING_CURSE, 1);
            } else if (new Random().nextInt() > 60) {
                stack = new AttributeWrapper(InternalAttribute.GENERIC_MOVEMENT_SPEED, -0.2, InternalOperation.MULTIPLY_SCALAR_1, InternalSlot.MAIN_HAND).applyTo(stack);
            }
        }
        stack = new AttributeWrapper(InternalAttribute.GENERIC_ATTACK_DAMAGE, damage, InternalOperation.ADD_NUMBER, InternalSlot.MAIN_HAND).applyTo(stack);
        stack = new AttributeWrapper(InternalAttribute.GENERIC_ATTACK_SPEED, speed, InternalOperation.ADD_NUMBER, InternalSlot.MAIN_HAND).applyTo(stack);
        ItemMeta meta = stack.getItemMeta();
        String displayName = ChatColor.WHITE + name;
        if (accessory != null && accessory.getItemMeta().hasDisplayName()) {
            displayName = ChatColor.translateAlternateColorCodes('&', accessory.getItemMeta().getDisplayName());
        }
        meta.setDisplayName(displayName);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + name);
        if (accessory != null) {
            switch (accessory.getType()) {
                case DIAMOND:
                    lore.add(DIAMONDS);
                    break;
                case EMERALD:
                    lore.add(EMERALDS);
                    break;
                case QUARTZ:
                    lore.add(QUARTZ);
                    break;
                case NETHER_STAR:
                    lore.add(NETHER_STAR);
                    break;
                case ENDER_PEARL:
                    lore.add(PEARLS);
                    break;
            }
        }
        String stars = new String();
        if (quality == -1) {
            stars = "?";
        }
        while (quality > 0) {
            stars += STAR;
            quality--;
        }
        lore.add(ChatColor.GREEN + "Qualit\u00e4t: " + ChatColor.GOLD + stars);
        lore.add(ChatColor.GREEN + "Schmied: " + ChatColor.GOLD + smith);
        lore.add(ChatColor.GREEN + "Herkunft: " + ChatColor.GOLD + origin);
        meta.setLore(lore);
        stack.setItemMeta(meta);
        return stack;
    }

}
