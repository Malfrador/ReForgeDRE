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

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.projectiles.ProjectileSource;

/**
 * @author Daniel Saukel
 */
public class InvisibilityListener implements Listener {

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        Player attacker = null;
        if (event.getDamager() instanceof Player) {
            attacker = (Player) event.getDamager();
        } else if (event.getDamager() instanceof Projectile && ((Projectile) event.getDamager()).getShooter() instanceof Player) {
            attacker = (Player) ((Projectile) event.getDamager()).getShooter();
        } else {
            return;
        }
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        Player damaged = (Player) event.getEntity();
        if (attacker.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
            attacker.removePotionEffect(PotionEffectType.INVISIBILITY);
        }
        if (damaged.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
            damaged.removePotionEffect(PotionEffectType.INVISIBILITY);
        }
    }

    @EventHandler
    public void onPotionSplash(PotionSplashEvent event) {
        for (Entity entity : event.getAffectedEntities()) {
            if (entity instanceof Player && ((Player) entity).hasPotionEffect(PotionEffectType.INVISIBILITY)) {
                ((Player) entity).removePotionEffect(PotionEffectType.INVISIBILITY);
            }
        }
        ProjectileSource shooter = event.getPotion().getShooter();
        if (shooter instanceof Player && ((Player) shooter).hasPotionEffect(PotionEffectType.INVISIBILITY)) {
            ((Player) shooter).removePotionEffect(PotionEffectType.INVISIBILITY);
        }
    }

}
