package me.invacto.huntervsrunner.events.menuinteract;

import me.invacto.huntervsrunner.inventories.GlobalModifiersMenu;
import me.invacto.huntervsrunner.inventories.ModifiersMenu;
import me.invacto.huntervsrunner.inventories.RecipesMenu;
import me.invacto.huntervsrunner.variables.GlobalModVariables;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class GlobalMenuInteract implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) { return; }
        if (event.getClickedInventory().getHolder() instanceof GlobalModifiersMenu) {

            if (event.getCurrentItem() == null) { return; }
            event.setCancelled(true);

            Player player = (Player) event.getWhoClicked();
            Inventory inventory = event.getInventory();

            if (event.getCurrentItem().getType() == Material.LIGHT_GRAY_STAINED_GLASS_PANE) return;

            if (event.isLeftClick()) {
                ItemStack item = event.getCurrentItem();
                int index = event.getSlot();

                if (item.getType() == Material.ARROW) {
                    ModifiersMenu gui = new ModifiersMenu();
                    player.openInventory(gui.getInventory());
                    return;
                }

                if (item.getType() == Material.BOOK) {
                    RecipesMenu recipesMenu = new RecipesMenu();
                    player.openInventory(recipesMenu.getInventory());
                    return;
                }

                if (Objects.requireNonNull(item.getItemMeta()).hasEnchant(Enchantment.ARROW_DAMAGE)) {
                    item.removeEnchantment(Enchantment.ARROW_DAMAGE);

                    ////////////////////////////////////////////////////////////////////////////////

                    if (item.getType() == Material.ENCHANTED_BOOK) {
                        GlobalModVariables.hasRecipes = false;
                        Bukkit.broadcastMessage(ChatColor.RED + "Custom recipes has been disabled");

                    }

                    if (item.getType() == Material.STONE_PICKAXE) {
                        GlobalModVariables.stoneTools = false;
                        Bukkit.broadcastMessage(ChatColor.RED + "Stone tools has been disabled");

                    }

                    if (item.getType() == Material.LEATHER_CHESTPLATE) {
                        GlobalModVariables.leatherArmor = false;
                        Bukkit.broadcastMessage(ChatColor.RED + "Leather armor has been disabled");

                    }

                    ////////////////////////////////////////////////////////////////////////////////

                } else {

                    item.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1);
                    ItemMeta meta = item.getItemMeta();
                    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    item.setItemMeta(meta);

                    ////////////////////////////////////////////////////////////////////////////////

                    if (item.getType() == Material.ENCHANTED_BOOK) {
                        GlobalModVariables.hasRecipes = true;
                        Bukkit.broadcastMessage(ChatColor.GOLD + "Custom recipes has been enabled");

                    }

                    if (item.getType() == Material.STONE_PICKAXE) {
                        GlobalModVariables.stoneTools = true;
                        Bukkit.broadcastMessage(ChatColor.GOLD + "Stone tools has been enabled");
                    }

                    if (item.getType() == Material.LEATHER_CHESTPLATE) {
                        GlobalModVariables.leatherArmor = true;
                        Bukkit.broadcastMessage(ChatColor.GOLD + "Leather armor has been enabled");

                    }


                    ////////////////////////////////////////////////////////////////////////////////

                }

                inventory.setItem(index, item);
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 3.0F, 1.0F);

            }

        }

    }

}
