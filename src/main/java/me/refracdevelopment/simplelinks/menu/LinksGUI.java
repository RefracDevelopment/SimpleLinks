package me.refracdevelopment.simplelinks.menu;

import me.refracdevelopment.simplelinks.SimpleLinks;
import me.refracdevelopment.simplelinks.utilities.ItemBuilder;
import me.refracdevelopment.simplelinks.utilities.chat.Color;
import me.refracdevelopment.simplelinks.utilities.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class LinksGUI implements Listener {

    public LinksGUI() {
        Bukkit.getServer().getPluginManager().registerEvents(this, SimpleLinks.getInstance());
    }

    private String homeTitle;
    private int homeSize;

    public void openInventory(Player player) {
        homeTitle = Color.translate(player, Config.MENU_TITLE);
        homeSize = Config.MENU_SIZE;
        Inventory inv = Bukkit.createInventory(null, homeSize, homeTitle);

        SimpleLinks.getInstance().getMenu().getItems().values().forEach(item -> {
            inv.setItem(item.getSlot(), item.getItem(player));
        });

        for (int i = 0; i < homeSize; i++) {
            if (inv.getItem(i) == null) {
                String name = Config.MENU_FILL_NAME;
                Material material = Config.MENU_FILL_MATERIAL;
                int data = Config.MENU_FILL_DATA;
                ItemBuilder item = new ItemBuilder(material);

                item.setName(Color.translate(player, name));
                item.setDurability(data);

                inv.setItem(i, item.toItemStack());
            }
        }

        player.openInventory(inv);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().equals(homeTitle)) {
            event.setCancelled(true);
            SimpleLinks.getInstance().getMenu().getItems().values().forEach(item -> {
                if (item.getSlot() == event.getRawSlot()) {
                    item.sendMessage(player);
                }
            });
        }
    }
}