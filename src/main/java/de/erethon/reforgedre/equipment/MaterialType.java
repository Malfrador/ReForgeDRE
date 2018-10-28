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

import java.util.Arrays;
import java.util.List;
import org.bukkit.Material;

/**
 * @author Daniel Saukel
 */
public enum MaterialType {

    CHAINMAIL,
    GOLD(Material.GOLD_INGOT, Material.GOLD_NUGGET),
    IRON(Material.IRON_INGOT, Material.IRON_NUGGET);

    private List<Material> materials;

    private MaterialType(Material... materials) {
        this.materials = Arrays.asList(materials);
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public static MaterialType getByMaterial(Material material) {
        for (MaterialType type : values()) {
            if (type.materials.contains(material)) {
                return type;
            }
        }
        return null;
    }

}
