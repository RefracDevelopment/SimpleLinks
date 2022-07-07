/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2022 RefracDevelopment
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
 * FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package me.refrac.simplelinks.menu;

import me.refrac.simplelinks.SimpleLinks;
import me.refrac.simplelinks.utilities.ItemBuilder;
import me.refrac.simplelinks.utilities.Manager;
import me.refrac.simplelinks.utilities.chat.Color;
import me.refrac.simplelinks.utilities.files.Config;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.List;

/**
 * Author:  Zachary (Refrac) Baldwin
 * Created: 2022-7-7
 */
public class LinksGUI extends Manager implements Listener {

    public LinksGUI(SimpleLinks plugin) {
        super(plugin);
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }

    private String homeTitle;
    private int homeSize;

    public void openInventory(Player player) {
        homeTitle = Color.translate(player, Config.MENU_TITLE);
        homeSize = Config.MENU_SIZE;
        Inventory inv = Bukkit.createInventory(null, homeSize, homeTitle);

        for (LinksItem item : plugin.getLinksMenu().getItems().values()) {
            inv.setItem(item.getSlot(), item.getItem(player));
        }

        for (int i = 0; i < homeSize; i++) {
            if (inv.getItem(i) == null) {
                List<String> lore = Config.MENU_FILL_LORE;
                String name = Config.MENU_FILL_NAME;
                Material material = Config.MENU_FILL_MATERIAL;
                int data = Config.MENU_FILL_DATA;
                ItemBuilder item = new ItemBuilder(material);

                item.setName(Color.translate(player, name));
                for (String s : lore) {
                    item.addLoreLine(Color.translate(player, s));
                }
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
            for (LinksItem item : plugin.getLinksMenu().getItems().values()) {
                if (item.getSlot() == event.getRawSlot()) {
                    item.sendMessage(player);
                }
            }
        }
    }
}