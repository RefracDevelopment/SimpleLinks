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

import ca.tweetzy.skulls.Skulls;
import ca.tweetzy.skulls.api.interfaces.Skull;
import me.arcaniax.hdb.api.HeadDatabaseAPI;
import me.refrac.simplelinks.utilities.ItemBuilder;
import me.refrac.simplelinks.utilities.chat.Color;
import me.refrac.simplelinks.utilities.files.Config;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Author:  Zachary (Refrac) Baldwin
 * Created: 2022-7-7
 */
public class LinksItem {
    private final Material material;
    private final String skullOwner;
    private final boolean skulls, headDatabase;
    private final int data;
    private final String name;
    private final List<String> lore;
    private final boolean messageEnabled;
    private final List<String> messages;
    private final int slot;

    public LinksItem(String item) {
        this.material = Material.getMaterial(Config.MENU_ITEMS.getString(item + ".material"));
        if (Config.MENU_ITEMS.getBoolean(item + ".head-database")) {
            this.headDatabase = Config.MENU_ITEMS.getBoolean(item + ".head-database", false);
        } else {
            this.headDatabase = false;
        }
        if (Config.MENU_ITEMS.getBoolean(item + ".skulls")) {
            this.skulls = Config.MENU_ITEMS.getBoolean(item + ".skulls", false);
        } else {
            this.skulls = false;
        }
        this.skullOwner = Config.MENU_ITEMS.getString(item + ".skullOwner");
        this.data = Config.MENU_ITEMS.getInt(item + ".data");
        this.name = Config.MENU_ITEMS.getString(item + ".name");
        this.lore = Config.MENU_ITEMS.getStringList(item + ".lore");
        this.messageEnabled = Config.MENU_ITEMS.getBoolean(item + ".message.enabled");
        this.messages = Config.MENU_ITEMS.getStringList(item + ".message.text");
        this.slot = Config.MENU_ITEMS.getInt(item + ".slot");
    }

    public String getName() {
        return this.name;
    }

    public int getSlot() {
        return this.slot;
    }

    public void sendMessage(Player player) {
        if (this.messageEnabled) {
            for (String message : this.messages)
                Color.sendMessage(player, message, true, true);
        }
    }

    public ItemStack getItem(Player player) {
        if (headDatabase) {
            HeadDatabaseAPI api = new HeadDatabaseAPI();
            ItemBuilder item = new ItemBuilder(api.getItemHead(this.skullOwner));

            item.setName(Color.translate(player, this.name));
            for (String s : this.lore) {
                item.addLoreLine(Color.translate(player, s));
            }

            return item.toItemStack();
        } else if (skulls) {
            Skull api = Skulls.getAPI().getSkull(Integer.parseInt(this.skullOwner));
            ItemBuilder item = new ItemBuilder(api.getItemStack());

            item.setName(Color.translate(player, this.name));
            for (String s : this.lore) {
                item.addLoreLine(Color.translate(player, s));
            }

            return item.toItemStack();
        } else {
            ItemBuilder item = new ItemBuilder(this.material);

            item.setName(Color.translate(player, this.name));
            for (String s : this.lore) {
                item.addLoreLine(Color.translate(player, s));
            }
            item.setDurability(this.data);
            item.setSkullOwner(this.skullOwner);

            return item.toItemStack();
        }
    }
}