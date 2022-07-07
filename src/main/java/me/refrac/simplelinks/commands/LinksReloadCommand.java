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
package me.refrac.simplelinks.commands;

import me.refrac.simplelinks.SimpleLinks;
import me.refrac.simplelinks.menu.LinksItem;
import me.refrac.simplelinks.utilities.Manager;
import me.refrac.simplelinks.utilities.chat.Color;
import me.refrac.simplelinks.utilities.files.Config;
import me.refrac.simplelinks.utilities.files.Files;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

/**
 * Author:  Zachary (Refrac) Baldwin
 * Created: 2022-7-7
 */
public class LinksReloadCommand extends Manager implements CommandExecutor {

    public LinksReloadCommand(SimpleLinks plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (!sender.hasPermission("simplelinks.reload")) {
            Color.sendMessage(sender, Config.NO_PERMISSION, true, true);
            return true;
        }

        Files.reloadFiles(plugin);
        Config.loadConfig();
        plugin.getLinksMenu().getItems().clear();
        for (String item : Config.MENU_ITEMS.getKeys(false))
            plugin.getLinksMenu().getItems().put(item, new LinksItem(item));
        Color.sendMessage(sender, Config.RELOAD, true, true);
        return true;
    }
}