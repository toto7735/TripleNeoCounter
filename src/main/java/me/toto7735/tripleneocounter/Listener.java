package me.toto7735.tripleneocounter;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

public class Listener implements org.bukkit.event.Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (!player.getGameMode().equals(GameMode.ADVENTURE)) return;
        if (event.getTo().getY() <= 99) {
            player.teleport(new Location(Bukkit.getWorld("world"), -0.5, 100, 0.5));
            Utils.addFailCount(player.getUniqueId(), 1);
            player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);
        } else if (event.getTo().getX() > 7 && event.getTo().getY() > 99) {
            player.teleport(new Location(Bukkit.getWorld("world"), -0.5, 100, 0.5));
            Utils.addTripleNeoCount(player.getUniqueId(), 1);
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
        }
    }
}
