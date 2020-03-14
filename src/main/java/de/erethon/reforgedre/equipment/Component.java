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
public enum Component {

    HANDLE(Material.STICK, Material.DIAMOND, Material.EMERALD, Material.QUARTZ, Material.NETHER_STAR, Material.ENDER_PEARL),
    CROSSGUARD(Material.GOLD_NUGGET, Material.IRON_NUGGET),
    METAL(Material.IRON_INGOT, Material.GOLD_INGOT),
    CHAIN(Material.IRON_NUGGET, Material.GOLD_NUGGET),
    FABRIC(Material.LEATHER, Material.WHITE_WOOL),
    BLADE(Material.GOLD_INGOT, Material.IRON_INGOT);

    private List<Material> materials;

    private Component(Material... materials) {
        this.materials = Arrays.asList(materials);
    }

    public List<Material> getMaterials() {
        return materials;
    }

}
