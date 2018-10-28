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

import org.bukkit.Material;

/**
 * @author Daniel Saukel
 */
public enum EquipmentType {

    AXE(Material.IRON_AXE, Material.GOLDEN_AXE),
    SWORD(Material.IRON_SWORD, Material.GOLDEN_SWORD),
    HELMET(Material.IRON_HELMET, Material.GOLDEN_HELMET, Material.CHAINMAIL_HELMET),
    CHESTPLATE(Material.IRON_CHESTPLATE, Material.GOLDEN_CHESTPLATE, Material.CHAINMAIL_CHESTPLATE),
    LEGGINGS(Material.IRON_LEGGINGS, Material.GOLDEN_LEGGINGS, Material.CHAINMAIL_LEGGINGS),
    BOOTS(Material.IRON_BOOTS, Material.GOLDEN_BOOTS, Material.CHAINMAIL_BOOTS);

    private Material iron;
    private Material gold;
    private Material chainmail;

    private EquipmentType(Material iron, Material gold) {
        this.iron = iron;
        this.gold = gold;
    }

    private EquipmentType(Material iron, Material gold, Material chainmail) {
        this(iron, gold);
        this.chainmail = chainmail;
    }

    public Material getMaterial(MaterialType type) {
        if (type == MaterialType.CHAINMAIL) {
            return chainmail;
        } else if (type == MaterialType.GOLD) {
            return gold;
        } else if (type == MaterialType.IRON) {
            return iron;
        } else {
            return null;
        }
    }

    public Material getIronMaterial() {
        return iron;
    }

    public Material getGoldMaterial() {
        return gold;
    }

    public Material getChainmailMaterial() {
        return chainmail;
    }

    public boolean isArmor() {
        return chainmail != null;
    }

}
