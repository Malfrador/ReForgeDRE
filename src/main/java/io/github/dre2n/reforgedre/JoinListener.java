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
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author Daniel Saukel
 */
public class JoinListener implements Listener {

    static Set<UUID> cache = new HashSet<>();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!event.getPlayer().hasPlayedBefore()) {
            cache.add(event.getPlayer().getUniqueId());
        } else if (!cache.contains(event.getPlayer().getUniqueId())) {
            return;
        }
        new JoinItemTask(event.getPlayer()).runTaskLater(ReForgeDRE.getInstance(), 60 * 20);
    }

    public class JoinItemTask extends BukkitRunnable {

        UUID uuid;

        public JoinItemTask(Player player) {
            uuid = player.getUniqueId();
        }

        @Override
        public void run() {
            Player player = Bukkit.getPlayer(uuid);
            if (!player.isOnline()) {
                return;
            }
            player.sendMessage(ChatColor.AQUA + "Willkommen in der Neuen Welt! Euer Gepäck steht bereit.");
            player.sendMessage(ChatColor.AQUA + "Darf ich fragen, woher Ihr stammt?");
            String pre = ChatColor.BLUE + "> " + ChatColor.GREEN;
            BaseComponent[] cuthalorn = TextComponent.fromLegacyText(pre + "Ich bin ein Farmer aus Cuthalorn auf der Flucht vor dem Krieg.");
            BaseComponent[] arachnida = TextComponent.fromLegacyText(pre + "Ich war einst ein Minenarbeiter in Arachnida.");
            BaseComponent[] sohothin = TextComponent.fromLegacyText(pre + "Ich entstamme einem sohothinischen Kleinadelsgeschlecht.");
            BaseComponent[] hohenstein = TextComponent.fromLegacyText(pre + "Ich studierte in Hohenstein das Wort des Herrn.");
            BaseComponent[] golvathal = TextComponent.fromLegacyText(pre + "Ich kam aus Golvathal, um Waren zu handeln.");
            BaseComponent[] daoshen = TextComponent.fromLegacyText(pre + "\u79c1\u306f\u65e5\u672c\u304b\u3089\u6765\u307e\u3057\u305f!");
            BaseComponent[] pirate = TextComponent.fromLegacyText(pre + "Das sollte für Euch nicht weiter von Belang sein.");
            Arrays.stream(cuthalorn).forEach(c -> c.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/rfgive start cuthalorn")));
            Arrays.stream(arachnida).forEach(c -> c.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/rfgive start arachnida")));
            Arrays.stream(sohothin).forEach(c -> c.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/rfgive start sohothin")));
            Arrays.stream(hohenstein).forEach(c -> c.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/rfgive start hohenstein")));
            Arrays.stream(golvathal).forEach(c -> c.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/rfgive start golvathal")));
            Arrays.stream(daoshen).forEach(c -> c.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/rfgive start daoshen")));
            Arrays.stream(pirate).forEach(c -> c.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/rfgive start pirate")));
            player.spigot().sendMessage(cuthalorn);
            player.spigot().sendMessage(arachnida);
            player.spigot().sendMessage(sohothin);
            player.spigot().sendMessage(hohenstein);
            player.spigot().sendMessage(golvathal);
            player.spigot().sendMessage(daoshen);
            player.spigot().sendMessage(pirate);
        }

    }

}
