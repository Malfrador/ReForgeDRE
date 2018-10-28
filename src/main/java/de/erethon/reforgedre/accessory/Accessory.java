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
package de.erethon.reforgedre.accessory;

import de.erethon.reforgedre.ReConfig;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;

/**
 * @author Daniel Saukel
 */
public enum Accessory {

    DIAMONDS(Material.DIAMOND, ReConfig.ACCESSORY_DIAMONDS, .01f, 1f, .75f),
    EMERALDS(Material.EMERALD, ReConfig.ACCESSORY_EMERALDS, .01f, 1f, 0f),
    QUARTZ(Material.QUARTZ, ReConfig.ACCESSORY_QUARTZ, .95f, .675f, .675f),
    NETHER_STAR(Material.NETHER_STAR, ReConfig.ACCESSORY_NETHER_STAR, 0f, 1f, 0f),
    PEARLS(Material.ENDER_PEARL, ReConfig.ACCESSORY_PEARLS, .01f, .25f, .1f),
    RUBIES(null, ReConfig.ACCESSORY_RUBIES, 0f, 0f, 0f);

    private Material craftingMaterial;
    private String lore;
    private float r, g, b;

    private Accessory(Material craftingMaterial, String lore, float r, float b, float g) {
        this.craftingMaterial = craftingMaterial;
        this.lore = lore;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Material getCraftingMaterial() {
        return craftingMaterial;
    }

    public String getLore() {
        return lore;
    }

    public void spawnParticles(Location location) {
        World world = location.getWorld();
        world.spawnParticle(Particle.REDSTONE, location.clone().add(0, 2, 0), 0, r, g, b);
        world.spawnParticle(Particle.REDSTONE, location.clone().add(1, 1.75, 0), 0, r, g, b);
        world.spawnParticle(Particle.REDSTONE, location.clone().add(0, 1.5, 1), 0, r, g, b);
        world.spawnParticle(Particle.REDSTONE, location.clone().add(-1, 1.25, 0), 0, r, g, b);
        world.spawnParticle(Particle.REDSTONE, location.clone().add(0, 1, -1), 0, r, g, b);
    }

    public static Accessory getByCraftingMaterial(Material material) {
        for (Accessory accessory : values()) {
            if (accessory.craftingMaterial == material) {
                return accessory;
            }
        }
        return null;
    }

    public static Accessory getByLore(String lore) {
        for (Accessory accessory : values()) {
            if (accessory.lore.equals(lore)) {
                return accessory;
            }
        }
        return null;
    }

    public static Accessory getByLore(ItemStack item) {
        if (item == null || !item.hasItemMeta() || !item.getItemMeta().hasLore() || item.getItemMeta().getLore().size() < 2) {
            return null;
        }
        return getByLore(item.getItemMeta().getLore().get(1));
    }

}
