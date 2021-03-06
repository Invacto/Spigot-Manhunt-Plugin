package me.invacto.huntervsrunner.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class GlobalModifiersMenu implements InventoryHolder {

    public static Inventory inv;
    public static UUID uuid = UUID.randomUUID();

    public static Map<String, ItemStack[]> globalMenu = new HashMap<>();

    public GlobalModifiersMenu() {
        inv = Bukkit.createInventory(this, 45, "Global Modifiers Menu");
        init();
    }

    public void init() {
        ItemStack fill = createItem(Material.LIGHT_GRAY_STAINED_GLASS_PANE, " ", null);

        for (int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, fill);
        }

        ///////////////////////////////////////////////////////////////////////////////////////////////

        ItemStack recipes = createItem(Material.ENCHANTED_BOOK, "Custom Recipes", Collections.singletonList("Enables custom recipes"));
        inv.setItem(4, recipes);

        ItemStack recipeBook = createItem(Material.BOOK, "Recipe Book", Collections.singletonList("Shows all of the custom recipes"));
        inv.setItem(5, recipeBook);

        ItemStack stoneTools = createItem(Material.STONE_PICKAXE, "Stone Tools", Collections.singletonList("All players start with stone tools"));
        ItemMeta stoneToolsMeta = stoneTools.getItemMeta();
        assert stoneToolsMeta != null;
        stoneToolsMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        stoneTools.setItemMeta(stoneToolsMeta);
        inv.setItem(21, stoneTools);

        ItemStack leatherArmor = createItem(Material.LEATHER_CHESTPLATE, "Armorer", Collections.singletonList("All players start with leather armor"));
        ItemMeta leatherArmorMeta = leatherArmor.getItemMeta();
        assert leatherArmorMeta != null;
        leatherArmorMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        leatherArmor.setItemMeta(leatherArmorMeta);
        inv.setItem(23, leatherArmor);

        ItemStack goBack = createItem(Material.ARROW, "Go back", null);
        inv.setItem(40, goBack);

        ///////////////////////////////////////////////////////////////////////////////////////////////

    }

    public ItemStack createItem(Material mat, String name, List<String> lore) {
        ItemStack item = new ItemStack(mat);
        ItemMeta meta = item.getItemMeta();

        assert meta != null;
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    @Override
    public Inventory getInventory() { return inv; }

}
