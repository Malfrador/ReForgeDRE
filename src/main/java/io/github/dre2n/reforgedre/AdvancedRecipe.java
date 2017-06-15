/*
 * Copyright (C) 2017 Daniel Saukel
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
package io.github.dre2n.reforgedre;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.bukkit.Material;

/**
 * @author Daniel Saukel
 */
public class AdvancedRecipe {

    static final Set<AdvancedRecipe> cache = new HashSet<>();

    public enum MaterialType {
        HANDLE(Material.STICK, Material.DIAMOND, Material.EMERALD, Material.QUARTZ, Material.NETHER_STAR, Material.ENDER_PEARL),
        CROSSGUARD(Material.GOLD_NUGGET, Material.IRON_NUGGET),
        BLADE(Material.GOLD_INGOT, Material.IRON_INGOT);

        List<Material> materials;

        MaterialType(Material... materials) {
            this.materials = Arrays.asList(materials);
        }
    }

    public Weapon result;
    public Map<Integer, MaterialType> ingredients = new HashMap<>();

    public AdvancedRecipe(String result, Map<String, Object> ingredients) {
        cache.add(this);
        for (Weapon weapon : Weapon.cache) {
            if (weapon.name.equals(result)) {
                this.result = weapon;
            }
        }
        for (Entry<String, Object> entry : ingredients.entrySet()) {
            this.ingredients.put(Integer.parseInt(entry.getKey()), MaterialType.valueOf((String) entry.getValue()));
        }
    }

}
