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
package me.refrac.simplelinks.utilities.files;

import me.refrac.simplelinks.SimpleLinks;
import me.refrac.simplelinks.utilities.Logger;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

/**
 * Author:  Zachary (Refrac) Baldwin
 * Created: 2022-7-7
 */
public class Files {
    private static File configFile;
    private static FileConfiguration config;

    public static void loadFiles(SimpleLinks links) {
        if (!links.getDataFolder().exists()) {
            links.getDataFolder().mkdirs();
        }

        configFile = new File(links.getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            links.saveResource("config.yml", false);
        }
        config = YamlConfiguration.loadConfiguration(configFile);

        Logger.NONE.out("&c==========================================");
        Logger.NONE.out("&aAll files have been loaded correctly!");
        Logger.NONE.out("&c==========================================");
    }

    public static FileConfiguration getConfig() {
        return config;
    }

    public static void reloadFiles(SimpleLinks links) {
        configFile = new File(links.getDataFolder(), "config.yml");
        try {
            config = YamlConfiguration.loadConfiguration(configFile);
        } catch (Exception e) {
            Logger.ERROR.out("Failed to reload the config file!");
            e.printStackTrace();
        }

        Logger.NONE.out("&c==========================================");
        Logger.NONE.out("&aAll files have been loaded correctly!");
        Logger.NONE.out("&c==========================================");
    }
}