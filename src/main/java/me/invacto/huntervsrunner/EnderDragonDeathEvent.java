package me.invacto.huntervsrunner;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.EnderDragon;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EnderDragonDeathEvent implements Listener {

    @EventHandler
    public void onEnderDragonDeath(EntityDeathEvent event) {
        EnderDragon enderDragon = (EnderDragon) event.getEntity();

        if (enderDragon.isDead()) {
            Bukkit.getServer().getOnlinePlayers().forEach(p -> p.sendTitle(ChatColor.GOLD + "The " + ChatColor.RED + "Runner" + ChatColor.GOLD + " has won!", ChatColor.GOLD + "GG's", 2, 100, 2));
            Bukkit.getServer().getOnlinePlayers().forEach(p -> p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 3.0F, 0.5F));

        }
    }
}
