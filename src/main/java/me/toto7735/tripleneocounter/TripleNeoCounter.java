package me.toto7735.tripleneocounter;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TripleNeoCounter extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new Listener(), this);
        Map<UUID, Integer> map = new HashMap<>();
        if (this.getConfig().getConfigurationSection("succeed") != null) for (String key : this.getConfig().getConfigurationSection("succeed").getKeys(false)) map.put(UUID.fromString(key), this.getConfig().getInt("succeed." + key));

        Map<UUID, Integer> map2 = new HashMap<>();
        if (this.getConfig().getConfigurationSection("fails") != null) for (String key : this.getConfig().getConfigurationSection("fails").getKeys(false)) map2.put(UUID.fromString(key), this.getConfig().getInt("fails." + key));

        Utils.setup(map, map2);
        new BukkitRunnable() {
            public void run() {
                for (Player onlinePlayer : Bukkit.getOnlinePlayers())
                    onlinePlayer.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§eYou have succeed §c§l" + Utils.getTripleNeoCount(onlinePlayer.getUniqueId()) + " Triple Neo §eso far! §7(And, failed " + Utils.getFailCount(onlinePlayer.getUniqueId()) + " times!)"));
            }
        }.runTaskTimerAsynchronously(this, 0, 1);
    }

    @Override
    public void onDisable() {
        Utils.getTripleNeoCounts().keySet().forEach(uuid -> this.getConfig().set("succeed." + uuid.toString(), Utils.getTripleNeoCount(uuid)));
        Utils.getFailCounts().keySet().forEach(uuid -> this.getConfig().set("fails." + uuid.toString(), Utils.getFailCount(uuid)));
        this.saveConfig();
    }

}
