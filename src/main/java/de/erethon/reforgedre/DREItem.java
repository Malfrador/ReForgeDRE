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

import com.google.common.collect.ImmutableMap;
import de.erethon.caliburn.CaliburnAPI;
import de.erethon.caliburn.item.CustomEquipment;
import de.erethon.reforgedre.accessory.Accessory;
import de.erethon.reforgedre.crafting.AdvancedRecipe;
import de.erethon.reforgedre.equipment.Component;
import static de.erethon.reforgedre.equipment.Component.*;
import de.erethon.reforgedre.equipment.CustomWeapon;
import static de.erethon.reforgedre.equipment.EquipmentType.*;
import de.erethon.reforgedre.equipment.ForgedEquipment;
import java.util.stream.Stream;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;

/**
 * @author Daniel Saukel
 */
public class DREItem {

    public static CustomEquipment DWARF_PICKAXE = (CustomEquipment) new CustomEquipment(CaliburnAPI.getInstance(), "Zwergenspitzhacke").register();
    public static CustomEquipment HOLY_SWORD = (CustomEquipment) new CustomEquipment(CaliburnAPI.getInstance(), "Anderthalbh\u00e4nder").register();
    public static final CustomWeapon DAGGER = (CustomWeapon) new CustomWeapon("Dolch", SWORD, 3.0, 0.0, new AdvancedRecipe(new ImmutableMap.Builder<Integer, Component>()
            .put(20, BLADE)
            .put(29, BLADE)
            .put(37, CROSSGUARD)
            .put(38, HANDLE)
            .put(39, CROSSGUARD)
            .put(47, HANDLE)
            .build())).register();
    public static final CustomWeapon KATANA = (CustomWeapon) new CustomWeapon("Katana", SWORD, 4.8, -2.15, new AdvancedRecipe(new ImmutableMap.Builder<Integer, Component>()
            .put(2, BLADE)
            .put(11, BLADE)
            .put(20, BLADE)
            .put(29, BLADE)
            .put(38, HANDLE)
            .put(47, HANDLE)
            .build())).register();
    public static final CustomWeapon LONGSWORD = (CustomWeapon) new CustomWeapon("Langschwert", SWORD, 7.0, -2.8, new AdvancedRecipe(new ImmutableMap.Builder<Integer, Component>()
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
            .build())).register();
    public static final CustomWeapon PIRATE_SABER = (CustomWeapon) new CustomWeapon("Piratens\u00e4bel", SWORD, 4.2, -1.9).register();
    public static final CustomWeapon RAPIER = (CustomWeapon) new CustomWeapon("Rapier", SWORD, 4.1, -1.7, new AdvancedRecipe(new ImmutableMap.Builder<Integer, Component>()
            .put(2, BLADE)
            .put(11, BLADE)
            .put(20, BLADE)
            .put(27, CROSSGUARD)
            .put(28, CROSSGUARD)
            .put(29, BLADE)
            .put(37, CROSSGUARD)
            .put(38, HANDLE)
            .put(47, HANDLE)
            .build())).register();
    public static final CustomWeapon KNIGHTLY_SWORD = (CustomWeapon) new CustomWeapon("Ritterschwert", SWORD, 5.5, -2.4, new AdvancedRecipe(new ImmutableMap.Builder<Integer, Component>()
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
            .build())).register();
    public static final CustomWeapon LUMBERJACK_AXE = (CustomWeapon) new CustomWeapon("Holzf\u00e4lleraxt", AXE, 4.5, -3.25, new AdvancedRecipe(new ImmutableMap.Builder<Integer, Component>()
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
            .build())).register();
    public static final CustomWeapon BATTLEAXE = (CustomWeapon) new CustomWeapon("Streitaxt", AXE, 8.0, -3.25, new AdvancedRecipe(new ImmutableMap.Builder<Integer, Component>()
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
            .build())).register();

    static {
        DWARF_PICKAXE.addLore(ForgedEquipment.STAR);
        DWARF_PICKAXE.setUnbreakable(true);
        Stream.of(buildLore(5, "unbekannt", "Arachnida")).forEach(l -> DWARF_PICKAXE.addLore(l));

        HOLY_SWORD.setName(ChatColor.WHITE + "Szent Kard");
        HOLY_SWORD.addEnchantment(Enchantment.DAMAGE_UNDEAD, 2);
        Stream.of(buildLore(4, "unbekannt", "Sohothin")).forEach(l -> HOLY_SWORD.addLore(l));
        HOLY_SWORD.addLore(Accessory.RUBIES.getLore());
    }

    public static void register() {
    }

    public static String[] buildLore(int quality, String smith, String origin) {
        String stars = "";
        if (quality == -1) {
            stars = "?";
        }
        while (quality > 0) {
            stars += ForgedEquipment.STAR;
            quality--;
        }
        return buildLore(stars, smith, origin);
    }

    public static String[] buildLore(String quality, String smith, String origin) {
        return new String[]{
            ReConfig.ITEM_META_QUALITY + quality,
            ReConfig.ITEM_META_SMITH + ChatColor.GOLD + smith,
            ReConfig.ITEM_META_ORIGIN + origin
        };
    }

}
