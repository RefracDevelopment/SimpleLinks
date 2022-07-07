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
package me.refrac.simplelinks;

import lombok.Getter;
import me.refrac.simplelinks.commands.LinksCommand;
import me.refrac.simplelinks.commands.LinksReloadCommand;
import me.refrac.simplelinks.listeners.JoinListener;
import me.refrac.simplelinks.menu.Links;
import me.refrac.simplelinks.utilities.files.Config;
import me.refrac.simplelinks.utilities.files.Files;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

import me.refrac.simplelinks.utilities.*;

/**
 * Author:  Zachary (Refrac) Baldwin
 * Created: 2022-7-7
 */
@Getter
public class SimpleLinks extends JavaPlugin {
    private Links linksMenu;

    @Override
    public void onEnable() {
        // Plugin startup logic
        long startTiming = System.currentTimeMillis();

        Files.loadFiles(this);
        Config.loadConfig();

        getCommand("links").setExecutor(new LinksCommand(this));
        getCommand("linksreload").setExecutor(new LinksReloadCommand(this));

        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        this.linksMenu = new Links(this);

        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            Logger.NONE.out("&bHooked into PlaceholderAPI.");
        }

        new Metrics(this, 13613);

        Logger.NONE.out("&8&m==&c&m=====&f&m======================&c&m=====&8&m==");
        Logger.NONE.out("&e" + Settings.getName + " has been enabled. (" + (System.currentTimeMillis() - startTiming) + "ms)");
        Logger.NONE.out(" &f[*] &6Version&f: &b" + Settings.getVersion);
        Logger.NONE.out(" &f[*] &6Name&f: &b" + Settings.getName);
        Logger.NONE.out(" &f[*] &6Author&f: &b" + Settings.getDeveloper);
        Logger.NONE.out("&8&m==&c&m=====&f&m======================&c&m=====&8&m==");
    }
}