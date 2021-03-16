package me.invacto.huntervsrunner;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class ItemPickUpEvent implements Listener {

    @EventHandler
    public void onItemPickUp(EntityPickupItemEvent event) {

        ItemStack item = event.getItem().getItemStack();
        Player player = (Player) event.getEntity();

        if (event.getEntity().getType() == EntityType.PLAYER) {
            if (item.getType() == Material.COMPASS) {
                player.getInventory().remove(Material.COMPASS);
            }
        }

    }
}
