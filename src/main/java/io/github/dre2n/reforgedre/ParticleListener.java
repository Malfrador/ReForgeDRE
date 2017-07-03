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

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

/**
 * @author Daniel Saukel
 */
public class ParticleListener implements Listener {

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) {
            return;
        }
        ItemStack item = ((Player) event.getDamager()).getInventory().getItemInMainHand();
        if (item == null || !item.hasItemMeta() || !item.getItemMeta().hasLore() || item.getItemMeta().getLore().size() < 2) {
            return;
        }
        String accessory = item.getItemMeta().getLore().get(1);
        float r = 0, g = 0, b = 0;
        if (accessory.equals(Weapon.DIAMONDS)) {
            g = 1f;
            b = 0.75f;
            r = 0.01f;
        } else if (accessory.equals(Weapon.EMERALDS)) {
            r = 0.01f;
            g = 1;
        } else if (accessory.equals(Weapon.QUARTZ)) {
            r = 0.95f;
            g = 0.675f;
            b = 0.675f;
        } else if (accessory.equals(Weapon.NETHER_STAR)) {
            g = 1;
        } else if (accessory.equals(Weapon.PEARLS)) {
            r = 0.01f;
            b = 0.1f;
            g = 0.25f;
        } else if (!accessory.equals(Weapon.RUBIES)) {
            return;
        }
        World world = event.getEntity().getWorld();
        Location location = event.getEntity().getLocation();
        world.spawnParticle(Particle.REDSTONE, location.clone().add(0, 2, 0), 0, r, g, b);
        world.spawnParticle(Particle.REDSTONE, location.clone().add(1, 1.75, 0), 0, r, g, b);
        world.spawnParticle(Particle.REDSTONE, location.clone().add(0, 1.5, 1), 0, r, g, b);
        world.spawnParticle(Particle.REDSTONE, location.clone().add(-1, 1.25, 0), 0, r, g, b);
        world.spawnParticle(Particle.REDSTONE, location.clone().add(0, 1, -1), 0, r, g, b);
    }

}
